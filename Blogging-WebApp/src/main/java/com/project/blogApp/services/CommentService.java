package com.project.blogApp.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.blogApp.payloads.CommentDto;

@Service
public interface CommentService {

		public CommentDto addComment(CommentDto commentDto,long post_id);
				
		public List<CommentDto> getCommentsByPost(long post_id);
		
		public List<CommentDto> searchComment(String key);
}
