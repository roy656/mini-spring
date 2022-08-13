package com.hanghea99.minispring.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String userWriter;

	@Column(nullable = false)
	private String content;

	@ManyToOne
	@JoinColumn
	@JsonBackReference
	private Member member;

	@ManyToOne
	@JoinColumn
	@JsonBackReference
	private Article article;

	private int heartCnt;
	private int commentCnt;

	@OneToMany(mappedBy = "comment", orphanRemoval = true)
	@JsonIgnore
	private List<Heart> heartList = new ArrayList<>();
}
