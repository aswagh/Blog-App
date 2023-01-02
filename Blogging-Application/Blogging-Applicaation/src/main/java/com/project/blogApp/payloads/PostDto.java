package com.project.blogApp.payloads;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PostDto {

	private long post_id;
	@NotEmpty
	private String postTitle;
	@NotEmpty
	private String postContent;

	private String image;
	
	private Date postDate;

	private UserDto user;

	private CategoryDto category;
	 
	private List<CommentDto> comments = new ArrayList<>();

}
