package com.hanghea99.minispring.model.dto;


import com.hanghea99.minispring.model.Comment;
import com.hanghea99.minispring.model.Heart;
import com.hanghea99.minispring.model.Language;
import com.hanghea99.minispring.model.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ArticleRequestDto {


    private String username;

    private String title;

    private String content;

    private String imgUrl;

    private int heartCnt;

    private int commentCnt;

    private Member member;

    private Language language;

}
