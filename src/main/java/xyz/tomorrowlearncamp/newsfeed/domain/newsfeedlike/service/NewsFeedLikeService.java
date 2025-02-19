package xyz.tomorrowlearncamp.newsfeed.domain.newsfeedlike.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.tomorrowlearncamp.newsfeed.domain.newsFeeds.entity.NewsFeed;
import xyz.tomorrowlearncamp.newsfeed.domain.newsFeeds.repository.NewsFeedRepository;
import xyz.tomorrowlearncamp.newsfeed.domain.newsfeedlike.repository.NewsFeedLikeRepository;
import xyz.tomorrowlearncamp.newsfeed.domain.users.entity.Users;
import xyz.tomorrowlearncamp.newsfeed.domain.users.repository.UsersRepository;
import xyz.tomorrowlearncamp.newsfeed.global.exception.NotFoundNewsFeedException;
import xyz.tomorrowlearncamp.newsfeed.global.exception.NotFoundUserException;

@Service
@RequiredArgsConstructor
public class NewsFeedLikeService {

    private final NewsFeedLikeRepository newsFeedLikeRepository;
    private final UsersRepository usersRepository;
    private final NewsFeedRepository newsFeedRepository;

    @Transactional
    public boolean toggleLike(Long newsFeedId, Long userId) {
        Users user = usersRepository.findById(userId).orElseThrow(NotFoundUserException::new);

        NewsFeed newsFeed = newsFeedRepository.findById(newsFeedId).orElseThrow(NotFoundNewsFeedException::new);

        boolean isLiked = newsFeedLikeRepository.existsByNewsFeedAndUser(newsFeed, user);

        newsFeedLikeRepository.toggle(newsFeed, user);

        return !isLiked;
    }
}
