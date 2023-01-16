package com.project.blogApp.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.project.blogApp.entities.Category;
import com.project.blogApp.entities.Post;
import com.project.blogApp.entities.User;
import com.project.blogApp.exceptions.ResourceNotFoundException;
import com.project.blogApp.payloads.PostDto;
import com.project.blogApp.repositories.CategoryRepository;
import com.project.blogApp.repositories.PostRepository;
import com.project.blogApp.repositories.UserRepository;
import com.project.blogApp.services.PostService;

@Service
public class PostServiceImpl implements PostService{

	@Autowired
	private PostRepository postRepository;
	@Autowired
	private ModelMapper modelMapper;
	
	private User user;
	private Category category;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private CategoryServiceImpl categoryServiceImpl;
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@Override
	public PostDto createPost(PostDto postDto, long user_id, long category_id) {
		postDto.setUser(
				this.userServiceImpl.userToDto(
						this.userRepository.findById(user_id).orElseThrow(
								()-> new ResourceNotFoundException("User", "ID", user_id))
						)		
				);
		postDto.setCategory(
				this.categoryServiceImpl.categoryToDto(
						this.categoryRepository.findById(category_id).orElseThrow(
								()-> new ResourceNotFoundException("Category", "ID", category_id))));

		postDto.setImage("default.png");
		postDto.setPostDate(new Date());
		return postToPostDto(this.postRepository.save(postDtoToPost(postDto)));
	}

	@Override
	public PostDto updatePost(PostDto postDto, long post_id) {
		Post post = this.postRepository.findById(post_id).orElseThrow(()-> new ResourceNotFoundException("POST","ID",post_id));
		post.setImage(postDto.getImage());
		post.setPostContent(postDto.getPostContent());
		post.setPostTitle(postDto.getPostTitle());
		
		return postToPostDto(this.postRepository.save(post));
	}

	@Override
	public PostDto getPost(long post_id) {
		return this.postToPostDto(
				this.postRepository.findById(post_id).orElseThrow(
						()-> new ResourceNotFoundException("Post","ID", post_id)));
	}

	@Override
	public List<PostDto> getAllPost() {
		List<Post> posts = this.postRepository.findAll();
		return posts.stream().map(post-> this.postToPostDto(post)).collect(Collectors.toList());
	}

	@Override
	public void deletePost(long post_id) {
		this.postRepository.delete(
						this.postRepository.findById(post_id).orElseThrow(
								()-> new ResourceNotFoundException("Post","ID", post_id)));
	}

	@Override
	public List<PostDto> getPostByUser(long user_id) {
		user = this.userRepository.findById(user_id).orElseThrow(
				()-> new ResourceNotFoundException("User", "ID", user_id));
		List<Post> posts = this.postRepository.findByUser(user);
		return posts.stream().map(
				post-> this.postToPostDto(post)).collect(Collectors.toList());
	}

	@Override
	public List<PostDto> getPostByCategory(long category_id) {
		category = this.categoryRepository.findById(category_id).orElseThrow(
				()-> new ResourceNotFoundException("Category", "Id", category_id));
		List<Post> posts = this.postRepository.findByCategory(category);
		
		return posts.stream().map(
				post-> this.postToPostDto(post)).collect(Collectors.toList());
	}

	public Post postDtoToPost(PostDto postDto) {
		return this.modelMapper.map(postDto, Post.class);
	}
	public PostDto postToPostDto(Post post) {
		return this.modelMapper.map(post, PostDto.class);
	}

//	@Override
	public List<PostDto> getPosts(Integer pageNumber, Integer pageSize,String sortBy,String sortWith) {
		Sort sort = null;
		if ( sortWith.equalsIgnoreCase("asc"))
			sort = Sort.by(sortBy).ascending();
		else
			sort = Sort.by(sortBy).descending();
		
		Pageable p = PageRequest.of(pageNumber, pageSize,sort);
		
		Page<Post> pagePost = this.postRepository.findAll(p);
		List<Post> posts = pagePost.getContent();
		
		List<PostDto> postDtos = posts.stream().map(post-> postToPostDto(post)).collect(Collectors.toList());
		
		return postDtos;
	}

//	@Override
//	public List<PostDto> searchPostByTitle(String keyword) {
//		List<PostDto> postDtos =
//				this.postRepository.getByTitleContaining("%"+keyword+"%").stream().map(
//				posts-> postToPostDto(posts)).collect(Collectors.toList());
//		return postDtos;
//	}

}
