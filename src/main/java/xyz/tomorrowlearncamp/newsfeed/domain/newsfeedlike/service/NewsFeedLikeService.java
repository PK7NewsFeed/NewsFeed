package xyz.tomorrowlearncamp.newsfeed.domain.newsfeedlike.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.tomorrowlearncamp.newsfeed.domain.newsfeed.dto.response.ReadNewsFeedResponseDto;
import xyz.tomorrowlearncamp.newsfeed.domain.newsfeed.entity.NewsFeed;
import xyz.tomorrowlearncamp.newsfeed.domain.newsfeed.repository.NewsFeedRepository;
import xyz.tomorrowlearncamp.newsfeed.domain.newsfeedlike.repository.NewsFeedLikeRepository;
import xyz.tomorrowlearncamp.newsfeed.domain.user.entity.Users;
import xyz.tomorrowlearncamp.newsfeed.domain.user.repository.UsersRepository;
import xyz.tomorrowlearncamp.newsfeed.global.exception.NotFoundCommentException;
import xyz.tomorrowlearncamp.newsfeed.global.exception.NotFoundNewsFeedException;
import xyz.tomorrowlearncamp.newsfeed.global.exception.NotFoundUserException;
import xyz.tomorrowlearncamp.newsfeed.global.exception.SelfLikeNotAllowedException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NewsFeedLikeService {

    private final NewsFeedLikeRepository newsFeedLikeRepository;
    private final UsersRepository usersRepository;
    private final NewsFeedRepository newsFeedRepository;

    @Transactional
    public void toggleLike(Long newsFeedId, Long userId) {
        Users user = usersRepository.findById(userId).orElseThrow(NotFoundUserException::new);

        NewsFeed newsFeed = newsFeedRepository.findById(newsFeedId).orElseThrow(NotFoundNewsFeedException::new);

        if (user.getId().equals(newsFeed.getUser().getId())) {
            throw new SelfLikeNotAllowedException();
        }

        newsFeedLikeRepository.toggle(newsFeed, user);
    }

    public int getCountNewsFeedLike(Long newsFeedId) {
        return newsFeedLikeRepository.countNewsFeedLikeByNewsFeedId(newsFeedId);
    }

    public List<ReadNewsFeedResponseDto> getLikeNewsFeeds(Long userId) {
        Users user = usersRepository.findById(userId).orElseThrow(NotFoundUserException::new);

        return newsFeedLikeRepository.findByUser(user).stream()
                .map(item -> {
                    int likeCount = getCountNewsFeedLike(item.getId());
                    return ReadNewsFeedResponseDto.toDto(item.getNewsFeed(), likeCount);
                })
                .collect(Collectors.toList());
    }
}
