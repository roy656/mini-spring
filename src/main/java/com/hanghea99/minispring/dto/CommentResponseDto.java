package com.hanghea99.minispring.dto;

import com.hanghea99.minispring.model.Article;
import com.hanghea99.minispring.model.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CommentResponseDto {

    private LocalDateTime createdDate;
    private Long id;
    private String username;
    private String comment;


    public CommentResponseDto(Comment comment) {
        this.createdDate = comment.getCreatedAt();
        this.id = comment.getId();
        this.comment = comment.getContent();
        this.username = comment.getUsername();

        //date???
    }
}
