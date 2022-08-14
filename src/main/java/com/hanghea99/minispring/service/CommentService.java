package com.hanghea99.minispring.service;

import com.hanghea99.minispring.dto.CommentRequestDto;
import com.hanghea99.minispring.dto.CommentResponseDto;
import com.hanghea99.minispring.model.Article;
import com.hanghea99.minispring.model.Comment;
import com.hanghea99.minispring.model.Heart;
import com.hanghea99.minispring.model.Member;
import com.hanghea99.minispring.repository.ArticleRepository;
import com.hanghea99.minispring.repository.CommentRepository;
import com.hanghea99.minispring.repository.HeartRepository;
import com.hanghea99.minispring.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;
    private final MemberService memberService;
    private  final HeartRepository heartRepository;

    //comment 작성하기
    public Comment creatComment(Long articleId, CommentRequestDto commentRequestDto) {
        Member member = memberRepository.findById(memberService.getSigningUserId())
                .orElseThrow(() -> new NullPointerException("존재하지 않는 사용자입니다."));

        Article article = articleRepository.findById(articleId)
                .orElseThrow(()-> new NullPointerException("해당 게시물이 존재하지 않습니다."));

        Comment comment = new Comment(commentRequestDto, article, member);


        member.addComment(comment);
        article.addComment(comment);

        commentRepository.save(comment);
        return comment;

    }

    //comment 불러오기
    public List<CommentResponseDto> readAllComment(Long articleId) {
        List<Comment> commentList = commentRepository.findAllByArticle_Id(articleId);
        List<CommentResponseDto> commentResponseList = new ArrayList<>();

        for(Comment comment : commentList){
            commentResponseList.add(new CommentResponseDto(comment));
        }
        return commentResponseList;
    }

    //comment 수정하기
    @Transactional
    public String updateComment(Long commentId, CommentRequestDto commentRequestDto) {
    Comment comment = commentRepository.findById(commentId)
            .orElseThrow(()-> new NullPointerException("해당 아이디가 존재하지 않습니다."));
    Member member = memberRepository.findById(memberService.getSigningUserId())
            .orElseThrow(() -> new NullPointerException("존재하지 않는 사용자입니다."));

		if(member.getUsername().equals(comment.getUsername())){
        comment.updateComment(commentRequestDto);
        return "수정 성공";
    }else return "수정 실패";
}

    //comment 삭제하기
    public String deleteComment(Long commentId) {
        Member member = memberRepository.findById(memberService.getSigningUserId())
                .orElseThrow(() -> new NullPointerException("존재하지 않는 사용자입니다."));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()-> new NullPointerException("해당 댓글이 존재하지 않습니다."));

        Article article = articleRepository.findById(comment.getArticle().getId())
                .orElseThrow(()-> new NullPointerException("해당 게시물이 존재하지 않습니다."));

        if(member.getUsername().equals(comment.getUsername())){
            member.removeComment(comment);
            article.removeComment(comment);
            commentRepository.delete(comment);
            return "삭제 성공";
        }else return "삭제 실패";
    }

    //댓글 좋아요
    public String heartComment(Long commentId) {
        Member member = memberRepository.findById(memberService.getSigningUserId())
                .orElseThrow(() -> new NullPointerException("존재하지 않는 사용자입니다."));
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()-> new NullPointerException("해당 댓글이 존재하지 않습니다."));

        if(heartRepository.findByMemberAndComment(member, comment) == null){
            Heart heart = new Heart(member, comment);
            member.addHeart(heart);
            comment.addHeart(heart);
            comment.setHeartCnt(comment.getHeartList().size());
            heartRepository.save(heart);
            return comment.getId() + "번 댓글 좋아요" + ", 총 좋아요 수 : " + comment.getHeartCnt();
        }else  {
            Heart heart = heartRepository.findByMemberAndComment(member, comment);
            member.removeHeart(heart);
            comment.removeHeart(heart);
            comment.setHeartCnt(comment.getHeartList().size());
            heartRepository.delete(heart);
            return comment.getId() + "번 댓글 좋아요 취소" + ", 총 좋아요 수: " + comment.getHeartCnt();
        }
    }
}