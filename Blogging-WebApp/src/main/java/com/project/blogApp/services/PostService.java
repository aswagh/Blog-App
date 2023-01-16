package com.project.blogApp.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.blogApp.payloads.PostDto;

@Service
public interface PostService {

		public PostDto createPost(PostDto postDto, long user_id, long category_id);
		
		public PostDto updatePost(PostDto postDto, long post_id);
		
		public PostDto getPost(long post_id);
		
		public List<PostDto> getAllPost();

		public List<PostDto> getPosts(Integer pageNumber, Integer pageSize, String sorBy, String sortWith);

		public void deletePost(long post_id);
		
		public List<PostDto> getPostByUser(long user_id);
		
		public List<PostDto> getPostByCategory(long category_id);

//		public List<PostDto> searchPostByTitle(String title);
}
