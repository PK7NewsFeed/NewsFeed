package xyz.tomorrowlearncamp.newsfeed.domain.newsfeed.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.tomorrowlearncamp.newsfeed.domain.newsfeed.dto.requestDto.CreateNewsFeedRequestDto;
import xyz.tomorrowlearncamp.newsfeed.domain.newsfeed.dto.requestDto.UpdateNewsFeedRequestDto;
import xyz.tomorrowlearncamp.newsfeed.domain.newsfeed.dto.responseDto.CreateNewsFeedResponseDto;
import xyz.tomorrowlearncamp.newsfeed.domain.newsfeed.dto.responseDto.ReadNewsFeedResponseDto;
import xyz.tomorrowlearncamp.newsfeed.domain.newsfeed.dto.responseDto.UpdateNewsFeedResponseDto;
import xyz.tomorrowlearncamp.newsfeed.domain.newsfeed.entity.NewsFeed;
import xyz.tomorrowlearncamp.newsfeed.domain.newsfeed.enums.SortOrder;
import xyz.tomorrowlearncamp.newsfeed.domain.newsfeed.repository.NewsFeedRepository;
import xyz.tomorrowlearncamp.newsfeed.domain.newsfeedlike.service.NewsFeedLikeService;
import xyz.tomorrowlearncamp.newsfeed.domain.user.entity.Users;
import xyz.tomorrowlearncamp.newsfeed.domain.user.service.UsersService;
import xyz.tomorrowlearncamp.newsfeed.global.exception.NotFoundNewsFeedException;
import xyz.tomorrowlearncamp.newsfeed.global.exception.UnauthorizedWriterException;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class NewsFeedService {

    private final NewsFeedRepository newsFeedRepository;
    private final UsersService usersService;
    private final NewsFeedLikeService newsFeedLikeService;

    @Transactional
    public CreateNewsFeedResponseDto save(CreateNewsFeedRequestDto requestDto, Long userId) {
        Users user = usersService.getUserEntityById(userId);
        NewsFeed newsFeed = newsFeedRepository.save(
                NewsFeed.builder()
                        .title(requestDto.getTitle())
                        .content(requestDto.getContent())
                        .user(user)
                        .build());
        return CreateNewsFeedResponseDto.builder()
                .id(newsFeed.getId())
                .title(newsFeed.getTitle())
                .content(newsFeed.getContent())
                .userId(newsFeed.getUser().getId())
                .createdAt(newsFeed.getCreatedAt())
                .updatedAt(newsFeed.getUpdatedAt())
                .build();
    }

    @Transactional(readOnly = true)
    public Page<ReadNewsFeedResponseDto> getNewsFeeds(int page, int size, SortOrder sortOrder, LocalDate startDate, LocalDate endDate) {
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
    public ReadNewsFeedResponseDto getNewsFeedDtoById(Long newsFeedId) {
        NewsFeed newsFeed = newsFeedRepository.findById(newsFeedId).orElseThrow(NotFoundNewsFeedException::new);

        int likeCount = newsFeedLikeService.getCountNewsFeedLike(newsFeedId);
        return ReadNewsFeedResponseDto.toDto(newsFeed, likeCount);
    }

    @Transactional(readOnly = true)
    public NewsFeed getNewsFeedById(Long postId) {
        return newsFeedRepository.findById(postId).orElseThrow(NotFoundNewsFeedException::new);
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
        return UpdateNewsFeedResponseDto.builder()
                .id(newsFeed.getId())
                .title(newsFeed.getTitle())
                .build();
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
