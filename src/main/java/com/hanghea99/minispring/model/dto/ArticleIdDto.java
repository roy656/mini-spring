package com.hanghea99.minispring.model.dto;

import com.hanghea99.minispring.model.Article;
import com.hanghea99.minispring.model.Comment;
import lombok.Getter;

@Getter
public class ArticleIdDto {
	private Long id;
	private String username;
	private String title;
	private String content;
	private String imgUrl;
	private Comment selectedComment;

	public ArticleIdDto(Article article) {
		this.id = article.getId();
		this.username = article.getUsername();
		this.title = article.getTitle();
		this.content = article.getContent();
		this.imgUrl = article.getImgUrl();
	}

	public void setSelectedComment(Comment selectedComment) {
		this.selectedComment = selectedComment;
	}
}
