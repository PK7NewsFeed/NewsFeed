package xyz.tomorrowlearncamp.newsfeed.domain.newsFeeds.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.tomorrowlearncamp.newsfeed.domain.newsFeeds.dto.requestDto.CreateNewsFeedRequestDto;
import xyz.tomorrowlearncamp.newsfeed.domain.newsFeeds.dto.requestDto.UpdateNewsFeedRequestDto;
import xyz.tomorrowlearncamp.newsfeed.domain.newsFeeds.dto.responseDto.CreateNewsFeedResponseDto;
import xyz.tomorrowlearncamp.newsfeed.domain.newsFeeds.dto.responseDto.ReadNewsFeedResponseDto;
import xyz.tomorrowlearncamp.newsfeed.domain.newsFeeds.dto.responseDto.UpdateNewsFeedResponseDto;
import xyz.tomorrowlearncamp.newsfeed.domain.newsFeeds.entity.NewsFeed;
import xyz.tomorrowlearncamp.newsfeed.domain.newsFeeds.enums.SortOrder;
import xyz.tomorrowlearncamp.newsfeed.domain.newsFeeds.repository.NewsFeedRepository;
import xyz.tomorrowlearncamp.newsfeed.domain.newsfeedlike.service.NewsFeedLikeService;
import xyz.tomorrowlearncamp.newsfeed.domain.users.entity.Users;
import xyz.tomorrowlearncamp.newsfeed.domain.users.repository.UsersRepository;
import xyz.tomorrowlearncamp.newsfeed.global.exception.NotFoundNewsFeedException;
import xyz.tomorrowlearncamp.newsfeed.global.exception.NotFoundUserException;
import xyz.tomorrowlearncamp.newsfeed.global.exception.UnauthorizedWriterException;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsFeedService {

    private final NewsFeedRepository newsFeedRepository;
    private final UsersRepository usersRepository;
    private final NewsFeedLikeService newsFeedLikeService;

    @Transactional
    public CreateNewsFeedResponseDto save(CreateNewsFeedRequestDto requestDto, Long userId) {
        Users user = usersRepository.findById(userId).orElseThrow(NotFoundUserException::new);
        NewsFeed newsFeed = new NewsFeed(requestDto.getTitle(), requestDto.getContent(), user);
        NewsFeed savedNewsFeed = newsFeedRepository.save(newsFeed);
        return new CreateNewsFeedResponseDto(
                savedNewsFeed.getId(),
                savedNewsFeed.getTitle(),
                savedNewsFeed.getContent(),
                user.getId(),
                savedNewsFeed.getCreatedAt(),
                savedNewsFeed.getUpdatedAt()
        );
    }

    @Transactional(readOnly = true)
    public Page<ReadNewsFeedResponseDto> findAll(int page, int size, SortOrder sortOrder, LocalDate startDate, LocalDate endDate) {
        Pageable pageable = PageRequest.of(page - 1, size, sortOrder.toSort());

        if (startDate != null && endDate != null) {
            return newsFeedRepository.findAllByCreatedAtBetween(startDate.atStartOfDay(), endDate.atTime(23,59,59), pageable)
                    .map(item -> {
                        int likeCount = newsFeedLikeService.getCountNewsFeedLike(item.getId());
                        return ReadNewsFeedResponseDto.toDto(item, likeCount);
                    });
        }

        if (startDate != null) {
            return newsFeedRepository.findAllByCreatedAtAfter(startDate.atStartOfDay(), pageable)
                    .map(item -> {
                        int likeCount = newsFeedLikeService.getCountNewsFeedLike(item.getId());
                        return ReadNewsFeedResponseDto.toDto(item, likeCount);
                    });
        }

        if (endDate != null) {
            return newsFeedRepository.findAllByCreatedAtBefore(endDate.atTime(23,59,59), pageable)
                    .map(item -> {
                        int likeCount = newsFeedLikeService.getCountNewsFeedLike(item.getId());
                        return ReadNewsFeedResponseDto.toDto(item, likeCount);
                    });
        }

        return newsFeedRepository.findAll(pageable)
                .map(item -> {
                    int likeCount = newsFeedLikeService.getCountNewsFeedLike(item.getId());
                    return ReadNewsFeedResponseDto.toDto(item, likeCount);
                });
    }

    @Transactional(readOnly = true)
    public ReadNewsFeedResponseDto findById(Long newsfeedId) {
        NewsFeed newsFeed = newsFeedRepository.findById(newsfeedId).orElseThrow(NotFoundNewsFeedException::new);

        int likeCount = newsFeedLikeService.getCountNewsFeedLike(newsfeedId);
        return new ReadNewsFeedResponseDto(
                newsFeed.getId(),
                newsFeed.getTitle(),
                newsFeed.getContent(),
                newsFeed.getUser().getId(),
                newsFeed.getCreatedAt(),
                newsFeed.getUpdatedAt(),
                likeCount
        );
    }

    @Transactional
    public UpdateNewsFeedResponseDto update(Long newsfeedId, UpdateNewsFeedRequestDto requestDto, Long userId) {
        NewsFeed newsFeed = newsFeedRepository.findById(newsfeedId).orElseThrow(NotFoundNewsFeedException::new);

        if (newsFeed.getUser().getId() != userId) {
            throw new UnauthorizedWriterException();
        }

        if (requestDto.getTitle() != null && !requestDto.getTitle().isBlank()) {
            newsFeed.updateTitle(requestDto.getTitle());
        }

        if (requestDto.getContent() != null && !requestDto.getContent().isBlank()) {
            newsFeed.updateContent(requestDto.getContent());
        }
        return new UpdateNewsFeedResponseDto(newsFeed.getId(), newsFeed.getTitle());
    }

    @Transactional
    public void deleteById(Long newsfeedId, Long userId) {
        NewsFeed newsFeed = newsFeedRepository.findById(newsfeedId).orElseThrow(NotFoundNewsFeedException::new);

        if (newsFeed.getUser().getId() != userId) {
            throw new UnauthorizedWriterException();
        }

        newsFeed.delete();
    }
}
