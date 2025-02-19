package xyz.tomorrowlearncamp.newsfeed.domain.newsfeedlike.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.tomorrowlearncamp.newsfeed.domain.newsFeeds.dto.responseDto.ReadNewsFeedResponseDto;
import xyz.tomorrowlearncamp.newsfeed.domain.newsFeeds.entity.NewsFeed;
import xyz.tomorrowlearncamp.newsfeed.domain.newsFeeds.repository.NewsFeedRepository;
import xyz.tomorrowlearncamp.newsfeed.domain.newsfeedlike.repository.NewsFeedLikeRepository;
import xyz.tomorrowlearncamp.newsfeed.domain.users.entity.Users;
import xyz.tomorrowlearncamp.newsfeed.domain.users.repository.UsersRepository;
import xyz.tomorrowlearncamp.newsfeed.global.exception.NotFoundNewsFeedException;
import xyz.tomorrowlearncamp.newsfeed.global.exception.NotFoundUserException;

import java.util.List;

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

        newsFeedLikeRepository.toggle(newsFeed, user);
    }

    public int getCountNewsFeedLike(Long newsFeedId) {
        return newsFeedLikeRepository.countNewsFeedLikeByNewsFeedId(newsFeedId);
    }

//    public List<ReadNewsFeedResponseDto> getLikeNewsFeeds(Long userId) {
//        return newsFeedLikeRepository.findNewsFeedByUserId(userId);
//    }
}
