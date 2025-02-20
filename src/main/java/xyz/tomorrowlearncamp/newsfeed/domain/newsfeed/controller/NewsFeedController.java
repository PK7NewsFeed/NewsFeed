package xyz.tomorrowlearncamp.newsfeed.domain.newsfeed.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xyz.tomorrowlearncamp.newsfeed.domain.newsfeed.dto.request.CreateNewsFeedRequestDto;
import xyz.tomorrowlearncamp.newsfeed.domain.newsfeed.dto.request.UpdateNewsFeedRequestDto;
import xyz.tomorrowlearncamp.newsfeed.domain.newsfeed.dto.response.CreateNewsFeedResponseDto;
import xyz.tomorrowlearncamp.newsfeed.domain.newsfeed.dto.response.ReadNewsFeedResponseDto;
import xyz.tomorrowlearncamp.newsfeed.domain.newsfeed.dto.response.UpdateNewsFeedResponseDto;
import xyz.tomorrowlearncamp.newsfeed.domain.newsfeed.enums.SortOrder;
import xyz.tomorrowlearncamp.newsfeed.domain.newsfeed.service.NewsFeedService;
import xyz.tomorrowlearncamp.newsfeed.global.etc.JwtProperties;
import xyz.tomorrowlearncamp.newsfeed.global.util.JwtUtil;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/newsfeeds")
public class NewsFeedController {

    private final NewsFeedService newsFeedService;

    private final JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<CreateNewsFeedResponseDto> saveNewsFeed(
            @Validated @RequestBody CreateNewsFeedRequestDto requestDto,
            @RequestHeader(JwtProperties.HEADER_STRING) String token
            ) {
        Long userId = jwtUtil.extractUserId(token);
        return new ResponseEntity<>(newsFeedService.saveNewsFeed(requestDto, userId), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<ReadNewsFeedResponseDto>> getNewsFeeds(
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "10") int size,
            @RequestParam(required = false, defaultValue = "CREATED_AT_DESC") SortOrder sortOrder,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-mm-dd") LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-mm-dd") LocalDate endDate
            ) {
        return new ResponseEntity<>(newsFeedService.getNewsFeeds(page, size, sortOrder, startDate, endDate), HttpStatus.OK);
    }

    @GetMapping("/{newsFeedId}")
    public ResponseEntity<ReadNewsFeedResponseDto> getNewsFeedById(@PathVariable Long newsFeedId) {
        return new ResponseEntity<>(newsFeedService.getNewsFeedDtoById(newsFeedId), HttpStatus.OK);
    }

    @PatchMapping("/{newsFeedId}")
    public ResponseEntity<UpdateNewsFeedResponseDto> updateNewsFeed(
            @PathVariable Long newsFeedId,
            @Validated @RequestBody UpdateNewsFeedRequestDto requestDto,
            @RequestHeader(JwtProperties.HEADER_STRING) String token
    ) {
        Long userId = jwtUtil.extractUserId(token);
        return new ResponseEntity<>(newsFeedService.updateNewsFeed(newsFeedId, requestDto, userId), HttpStatus.OK);
    }

    @DeleteMapping("/{newsFeedId}")
    public void deleteNewsFeed(
            @PathVariable Long newsFeedId,
            @RequestHeader(JwtProperties.HEADER_STRING) String token
    ) {
        Long userId = jwtUtil.extractUserId(token);
        newsFeedService.deleteNewsFeed(newsFeedId, userId);
    }
}
