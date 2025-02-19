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
import xyz.tomorrowlearncamp.newsfeed.auth.dto.LoginUserResponseDto;
import xyz.tomorrowlearncamp.newsfeed.domain.comment.service.CommentService;
import xyz.tomorrowlearncamp.newsfeed.domain.comment.dto.*;
import xyz.tomorrowlearncamp.newsfeed.global.etc.Const;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CreateCommentResponseDto> save(@Validated @RequestBody CreateCommentRequestDto requestDto,
                                                         @SessionAttribute(name = Const.LOGIN_USER) LoginUserResponseDto sessionUser) {
        CreateCommentResponseDto responseDto = commentService.save(requestDto, sessionUser.getId());
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
                                                           @SessionAttribute(name = Const.LOGIN_USER) LoginUserResponseDto loginUserResponseDto) {
        UpdateCommentResponseDto responseDto = commentService.update(commentId, requestDto.getContent(), loginUserResponseDto.getId());
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> delete(@PathVariable Long commentId,
                                       @SessionAttribute(name = Const.LOGIN_USER) LoginUserResponseDto responseDto) {
        commentService.delete(commentId, responseDto.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
