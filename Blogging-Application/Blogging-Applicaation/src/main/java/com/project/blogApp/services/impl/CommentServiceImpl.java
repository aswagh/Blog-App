package com.project.blogApp.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.blogApp.entities.Comment;
import com.project.blogApp.entities.Post;
import com.project.blogApp.entities.User;
import com.project.blogApp.exceptions.ResourceNotFoundException;
import com.project.blogApp.payloads.CommentDto;
import com.project.blogApp.repositories.CommentRepository;
import com.project.blogApp.repositories.PostRepository;
import com.project.blogApp.repositories.UserRepository;
import com.project.blogApp.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	private PostRepository postRepository;
	@Autowired
	private ModelMapper modelMapper;
	private Post post;
	
	@Override
	public CommentDto addComment(CommentDto commentDto,long post_id) {
	this.post = this.postRepository.findById(post_id).orElseThrow(
				()-> new ResourceNotFoundException("Post", "ID", post_id));
	Comment comment = this.modelMapper.map(commentDto,Comment.class);
	comment.setPost(post);
		
		return this.modelMapper.map(
				this.commentRepository.save(comment), CommentDto.class);
	}

	@Override
	public List<CommentDto> getCommentsByPost(long post_id) {
		this.post = this.postRepository.findById(post_id).orElseThrow(
				()-> new ResourceNotFoundException("Post","ID", post_id));
				
		return this.commentRepository.findByPost(post).stream().map(
				comments-> this.modelMapper.map(
						comments, CommentDto.class)).collect(Collectors.toList());
	}

	@Override
	public List<CommentDto> searchComment(String key) {
		// TODO Auto-generated method stub
		return null;
	}

}
