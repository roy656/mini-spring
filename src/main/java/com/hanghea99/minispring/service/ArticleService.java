package com.hanghea99.minispring.service;

import com.hanghea99.minispring.dto.ArticleRequestDto;
import com.hanghea99.minispring.dto.ArticleResponseDto;
import com.hanghea99.minispring.model.Article;
import com.hanghea99.minispring.model.Member;
import com.hanghea99.minispring.repository.ArticleRepository;
import com.hanghea99.minispring.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;
    private final MemberService memberService;

    //Error 공유 게시글 생성
    public Article createArticle(ArticleRequestDto articleRequestDto) {
        Member member = memberService.getSinginUser();
        Article article = new Article(articleRequestDto,member);

        member.addArticle(article);
        articleRepository.save(article);
        return article;
    }

    //전체게시물 조회
    public List<ArticleResponseDto> readAllArticle() {
        List<Article> articleList = articleRepository.findAll();
        List<ArticleResponseDto> articleResponseDtoList = new ArrayList<>();

        for (Article article:articleList) {
            articleResponseDtoList.add(new ArticleResponseDto(article));
        }
        return articleResponseDtoList;
    }


    //자바 게시물

    //파이썬게시물

    //JS 게시물

    //게시물 업데이트
    @Transactional
    public String updateArticle(Long id, ArticleRequestDto articleRequestDto) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 존재하지 않습니다."));
        Member member = memberRepository.findById(memberService.getSigninUserId())  //로그인 한 유저만 수정할 수있으니까
                .orElseThrow(()-> new IllegalArgumentException("잘못된 사용자입니다. 다시 로그인 후 시도해주세요."));

        if(member.getUsername().equals(article.getUsername())){
            article.updateArticle(articleRequestDto);
            return article.getId() + "번 게시글 수정 완료";
        }else return "작성자만 수정할 수 있습니다.";
    }

    //게시물 지우기
    public String deleteArticle(Long id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 존재하지 않습니다."));
        Member member = memberRepository.findById(memberService.getSigninUserId())   //로그인 한 유저만 수정할 수있으니까
                .orElseThrow(()-> new IllegalArgumentException("잘못된 사용자입니다. 다시 로그인 후 시도해주세요."));

        if(member.getUsername().equals(article.getUsername())){
            member.removeArticle(article);
            articleRepository.delete(article);
            return article.getId() + "번 게시물 삭제완료.";
        }else return "작성자만 삭제 할 수 있습니다.";
    }
}
