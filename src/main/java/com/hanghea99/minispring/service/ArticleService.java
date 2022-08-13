package com.hanghea99.minispring.service;

import com.hanghea99.minispring.dto.ArticleRequestDto;
import com.hanghea99.minispring.model.Article;
import com.hanghea99.minispring.model.Member;
import com.hanghea99.minispring.repository.ArticleRepository;
import com.hanghea99.minispring.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final MemberService memberService;

    //Error 공유 게시글 생성
    public Article createArticle(ArticleRequestDto articleRequestDto) {
        Member member = memberService.getSinginUser();
        Article article = new Article(articleRequestDto,member);

        member.addArticle(article);
        articleRepository.save(article);
        return article;


    }
}
