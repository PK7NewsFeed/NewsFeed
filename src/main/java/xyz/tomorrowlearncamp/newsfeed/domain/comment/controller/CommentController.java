package xyz.tomorrowlearncamp.newsfeed.domain.comment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xyz.tomorrowlearncamp.newsfeed.domain.comment.service.CommentService;
import xyz.tomorrowlearncamp.newsfeed.domain.comment.dto.*;
import xyz.tomorrowlearncamp.newsfeed.global.etc.JwtProperties;
import xyz.tomorrowlearncamp.newsfeed.global.util.JwtUtil;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;
    private final JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<CreateCommentResponseDto> save(@Validated @RequestBody CreateCommentRequestDto requestDto,
                                                         @RequestHeader(JwtProperties.HEADER_STRING) String token) {
        Long userId = jwtUtil.extractUserId(token);
        CreateCommentResponseDto responseDto = commentService.save(requestDto, userId);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<ReadCommentResponseDto>> getCommentsByNewsFeedId(
            @RequestParam Long newsFeedId,
            @PageableDefault(sort = "createdAt", direction = Sort.Direction.ASC) Pageable pageable                                                                    ) {
        Page<ReadCommentResponseDto> responseDtos = commentService.getCommentsByNewsFeedId(newsFeedId, pageable);

        return new ResponseEntity<>(responseDtos, HttpStatus.OK);
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity<UpdateCommentResponseDto> update(@PathVariable Long commentId,
                                                           @Validated @RequestBody UpdateCommentRequestDto requestDto,
                                                           @RequestHeader(JwtProperties.HEADER_STRING) String token) {
        Long userId = jwtUtil.extractUserId(token);
        UpdateCommentResponseDto responseDto = commentService.update(commentId, requestDto.getContent(), userId);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> delete(@PathVariable Long commentId,
                                       @RequestHeader(JwtProperties.HEADER_STRING) String token) {
        Long userId = jwtUtil.extractUserId(token);
        commentService.delete(commentId, userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
