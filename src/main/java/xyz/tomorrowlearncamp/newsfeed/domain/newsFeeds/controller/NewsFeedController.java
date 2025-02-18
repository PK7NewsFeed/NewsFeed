package xyz.tomorrowlearncamp.newsfeed.domain.newsFeeds.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.tomorrowlearncamp.newsfeed.domain.newsFeeds.dto.requestDto.NewsFeedRequestDto;
import xyz.tomorrowlearncamp.newsfeed.domain.newsFeeds.dto.responseDto.NewsFeedResponseDto;
import xyz.tomorrowlearncamp.newsfeed.domain.newsFeeds.dto.responseDto.NewsFeedUpdateResponseDto;
import xyz.tomorrowlearncamp.newsfeed.domain.newsFeeds.service.NewsFeedService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class NewsFeedController {

    private final NewsFeedService newsFeedService;

    @PostMapping("/newsfeeds")
    public ResponseEntity<NewsFeedResponseDto> save(@RequestBody NewsFeedRequestDto requestDto) {
        return new ResponseEntity<>(newsFeedService.save(requestDto), HttpStatus.CREATED);
    }

    @GetMapping("/newsfeeds")
    public ResponseEntity<List<NewsFeedResponseDto>> findAll() {
        return new ResponseEntity<>(newsFeedService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/newsfeeds/{newsfeedId}")
    public ResponseEntity<NewsFeedResponseDto> findOne(@PathVariable Long newsfeedId) {
        return new ResponseEntity<>(newsFeedService.findById(newsfeedId), HttpStatus.OK);
    }

    @PatchMapping("/newsfeeds/{newsfeedId}")
    public ResponseEntity<NewsFeedUpdateResponseDto> updateTitle(@PathVariable Long newsfeedId, @RequestBody NewsFeedRequestDto requestDto) {
        return new ResponseEntity<>(newsFeedService.update(newsfeedId, requestDto), HttpStatus.OK);
    }

    @DeleteMapping("/newsfeeds/{newsfeedId}")
    public void delete(@PathVariable Long newsfeedId) {
        newsFeedService.deleteById(newsfeedId);
    }
}
