package com.hanghea99.minispring.controller;

import com.hanghea99.minispring.dto.ArticleRequestDto;
import com.hanghea99.minispring.model.Article;
import com.hanghea99.minispring.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/article")
public class ArticleController {
    private final ArticleService articleService;


    //Error 공유 게시글 생성
    @PostMapping()
    public Article createArticle(@RequestBody ArticleRequestDto articleRequestDto){
        return articleService.createArticle(articleRequestDto);
    }

}
