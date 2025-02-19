package xyz.tomorrowlearncamp.newsfeed.domain.newsFeeds.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xyz.tomorrowlearncamp.newsfeed.auth.dto.LoginUserResponseDto;
import xyz.tomorrowlearncamp.newsfeed.domain.newsFeeds.dto.requestDto.CreateNewsFeedRequestDto;
import xyz.tomorrowlearncamp.newsfeed.domain.newsFeeds.dto.requestDto.UpdateNewsFeedRequestDto;
import xyz.tomorrowlearncamp.newsfeed.domain.newsFeeds.dto.responseDto.CreateNewsFeedResponseDto;
import xyz.tomorrowlearncamp.newsfeed.domain.newsFeeds.dto.responseDto.ReadNewsFeedResponseDto;
import xyz.tomorrowlearncamp.newsfeed.domain.newsFeeds.dto.responseDto.UpdateNewsFeedResponseDto;
import xyz.tomorrowlearncamp.newsfeed.domain.newsFeeds.enums.SortOrder;
import xyz.tomorrowlearncamp.newsfeed.domain.newsFeeds.service.NewsFeedService;
import xyz.tomorrowlearncamp.newsfeed.global.etc.Const;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/newsfeeds")
public class NewsFeedController {

    private final NewsFeedService newsFeedService;

    @PostMapping
    public ResponseEntity<CreateNewsFeedResponseDto> save(
            @Validated @RequestBody CreateNewsFeedRequestDto requestDto,
            @SessionAttribute(name = Const.LOGIN_USER) LoginUserResponseDto loginUser
            ) {
        return new ResponseEntity<>(newsFeedService.save(requestDto, loginUser.getId()), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<ReadNewsFeedResponseDto>> findAll(
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "10") int size,
            @RequestParam(required = false, defaultValue = "CREATED_AT_DESC") SortOrder sortOrder,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-mm-dd") LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-mm-dd") LocalDate endDate
            ) {
        return new ResponseEntity<>(newsFeedService.findAll(page, size, sortOrder, startDate, endDate), HttpStatus.OK);
    }

    @GetMapping("/{newsfeedId}")
    public ResponseEntity<ReadNewsFeedResponseDto> findOne(@PathVariable Long newsfeedId) {
        return new ResponseEntity<>(newsFeedService.findById(newsfeedId), HttpStatus.OK);
    }

    @PatchMapping("/{newsfeedId}")
    public ResponseEntity<UpdateNewsFeedResponseDto> updateNewsFeed(
            @PathVariable Long newsfeedId,
            @Validated @RequestBody UpdateNewsFeedRequestDto requestDto,
            @SessionAttribute(name = Const.LOGIN_USER) LoginUserResponseDto loginUser
    ) {
        return new ResponseEntity<>(newsFeedService.update(newsfeedId, requestDto, loginUser.getId()), HttpStatus.OK);
    }

    @DeleteMapping("/{newsfeedId}")
    public void delete(
            @PathVariable Long newsfeedId,
            @SessionAttribute(name = Const.LOGIN_USER) LoginUserResponseDto loginUser
    ) {
        newsFeedService.deleteById(newsfeedId, loginUser.getId());
    }
}
