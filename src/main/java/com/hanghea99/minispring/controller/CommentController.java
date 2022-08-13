package com.hanghea99.minispring.controller;

import com.hanghea99.minispring.dto.CommentRequestDto;
import com.hanghea99.minispring.dto.CommentResponseDto;
import com.hanghea99.minispring.model.Comment;
import com.hanghea99.minispring.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/comment")
public class CommentController {

    private final CommentService commentService;

    //comment 작성하기
    @PostMapping("/{articleId}")
    public Comment creatComment(@PathVariable Long articleId, @RequestBody CommentRequestDto commentRequestDto) {
        return commentService.creatComment(articleId, commentRequestDto);
    }
    //comment 불러오기
    @GetMapping("/{articleId}")
    public List<CommentResponseDto> readAllComment(@PathVariable Long articleId) {
        return commentService.readAllComment(articleId);
    }
    //comment 수정하기
    @PatchMapping("/{commentId}")
    public String updateComment(@PathVariable Long commentId, @RequestBody CommentRequestDto commentRequestDto) {
        return commentService.updateComment(commentId, commentRequestDto);
    }
    //comment 삭제하기
    @DeleteMapping("/{commentId}")
    public String deleteComment(@PathVariable Long commentId) {
        return commentService.deleteComment(commentId);
    }

}
