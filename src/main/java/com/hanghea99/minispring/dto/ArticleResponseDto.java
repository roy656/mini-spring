package com.hanghea99.minispring.dto;

import com.hanghea99.minispring.model.Article;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ArticleResponseDto {

    private LocalDateTime createdDate;
    private Long id;
    private String username;
    private String title;

    private Boolean isDone;

    public ArticleResponseDto(Article article) {
        this.createdDate = article.getCreatedAt();
        this.id = article.getId();
        this.username = article.getUsername();
        this.title = article.getTitle();
        this.isDone = article.getIsDone();
    }


}
