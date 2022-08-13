package com.hanghea99.minispring.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.hanghea99.minispring.dto.ArticleRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Article {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String username;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false, length = 1000)
	private String content;

	@JsonIgnore
	private String imgUrl;

	@JsonIgnore
	private int heartCnt;
	private int commentCnt;

	@ManyToOne
	@JoinColumn
	@JsonBackReference
	private Member member;

	@OneToMany(cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Comment> commentList = new ArrayList<>();

	@OneToMany(mappedBy = "article")
	@JsonIgnore
	private List<Heart> heartList = new ArrayList<>();

	//생성자 생성
	public Article(ArticleRequestDto articleRequestDto, Member member) {
		this.username = member.getUsername();
		this.title = articleRequestDto.getTitle();
		this.content = articleRequestDto.getContent();
		this.member = member;
	}
}
