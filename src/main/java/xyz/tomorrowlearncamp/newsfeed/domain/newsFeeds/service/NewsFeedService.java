package xyz.tomorrowlearncamp.newsfeed.domain.newsFeeds.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.tomorrowlearncamp.newsfeed.domain.newsFeeds.dto.requestDto.NewsFeedRequestDto;
import xyz.tomorrowlearncamp.newsfeed.domain.newsFeeds.dto.requestDto.NewsFeedUpdateRequestDto;
import xyz.tomorrowlearncamp.newsfeed.domain.newsFeeds.dto.responseDto.NewsFeedResponseDto;
import xyz.tomorrowlearncamp.newsfeed.domain.newsFeeds.dto.responseDto.NewsFeedUpdateResponseDto;
import xyz.tomorrowlearncamp.newsfeed.domain.newsFeeds.entity.NewsFeed;
import xyz.tomorrowlearncamp.newsfeed.domain.newsFeeds.repository.NewsFeedRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsFeedService {

    private final NewsFeedRepository newsFeedRepository;

    @Transactional
    public NewsFeedResponseDto save(NewsFeedRequestDto requestDto) {
        NewsFeed newsFeed = new NewsFeed(requestDto.getTitle(), requestDto.getContent());
        NewsFeed savedNewsFeed = newsFeedRepository.save(newsFeed);
        return new NewsFeedResponseDto(savedNewsFeed.getId(), savedNewsFeed.getTitle(), savedNewsFeed.getContent());
    }

    @Transactional(readOnly = true)
    public List<NewsFeedResponseDto> findAll() {
//        List<NewsFeed> newsFeeds = newsFeedRepository.findAll();
//
//        List<NewsFeedResponseDto> responseDtos = new ArrayList<>();
//        for (NewsFeed newsFeed : newsFeeds) {
//            responseDtos.add(new NewsFeedResponseDto(newsFeed.getId(), newsFeed.getTitle(), newsFeed.getContent()));
//        }
        List<NewsFeedResponseDto> responseDtos = newsFeedRepository.findAll().stream()
                .map(item -> new NewsFeedResponseDto(item.getId(), item.getTitle(), item.getContent()))
                .toList();
        return responseDtos;
    }

    @Transactional(readOnly = true)
    public NewsFeedResponseDto findById(Long postId) {
        NewsFeed newsFeed = newsFeedRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("해당 ID에 맞는 뉴스피드가 없습니다.")
        );

        return new NewsFeedResponseDto(newsFeed.getId(), newsFeed.getTitle(), newsFeed.getContent());
    }

    @Transactional(readOnly = true)
    public NewsFeed findEntityById(Long postId) {
        NewsFeed newsFeed = newsFeedRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID에 맞는 뉴스피드가 없습니다."));
        return newsFeed;
    }

    @Transactional
    public NewsFeedUpdateResponseDto update(Long postId, NewsFeedUpdateRequestDto requestDto) {
        NewsFeed newsFeed = newsFeedRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("해당 ID에 맞는 뉴스피드가 없습니다.")
        );

        newsFeed.updateTitle(requestDto.getTitle());
        return new NewsFeedUpdateResponseDto(newsFeed.getId(), newsFeed.getTitle());
    }

    @Transactional
    public void deleteById(Long postId) {
        if (!newsFeedRepository.existsById(postId)) {
            throw new IllegalArgumentException("해당 ID에 맞는 뉴스피드가 없습니다.");
        }

        newsFeedRepository.deleteById(postId);
    }
}
