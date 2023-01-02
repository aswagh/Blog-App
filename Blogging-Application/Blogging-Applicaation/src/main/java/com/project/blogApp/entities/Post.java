package com.project.blogApp.entities;


import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Post {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name="id")
		private long post_id;
		@Column(name = "title", nullable = false)
		private String postTitle;
		@Column(name = "content", length = 1000, nullable = false)
		private String postContent;
		@Column(name="image",nullable = false)
		private String image;
		private Date postDate;
		@ManyToOne
		@JoinColumn(name = "user_id", nullable = false)
		private User user;
		@ManyToOne
		@JoinColumn(name = "category_id")
		private Category category;
		
		@OneToMany(mappedBy = "post", cascade =  CascadeType.ALL)
		private List<Comment> comments;

}
