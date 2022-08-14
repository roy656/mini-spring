package com.hanghea99.minispring.controller;

import com.hanghea99.minispring.dto.ArticleRequestDto;
import com.hanghea99.minispring.dto.ArticleResponseDto;
import com.hanghea99.minispring.model.Article;
import com.hanghea99.minispring.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/article")
public class ArticleController {
    private final ArticleService articleService;


    //Error 공유 게시글 생성
    @PostMapping("")
    public Article createArticle(@RequestBody ArticleRequestDto articleRequestDto){
        return articleService.createArticle(articleRequestDto);
    }

    //전체 게시물 조회
    @GetMapping("")
    public List<ArticleResponseDto> readAllPost(){
        return articleService.readAllArticle();
    }

    //게시글 상세 페이지

    //자바 게시물

    //파이썬게시물

    //JS 게시물

    //게시물 업데이트
    @PatchMapping("/{articleId}")
    public String updateArticle(@PathVariable Long id, @RequestBody ArticleRequestDto articleRequestDto){
        return articleService.updateArticle(id, articleRequestDto);
    }
    //게시물 지우기
    @DeleteMapping("/{articleId}")
    public String deleteArticle(@PathVariable Long id){
        return  articleService.deleteArticle(id);
    }

    //게시글 좋아요
    @PostMapping("/heart/{articleId}")
    private String heartArticle(@PathVariable Long articleId){
        return articleService.heartArticle(articleId);
    }
}



