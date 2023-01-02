package com.project.blogApp.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.blogApp.payloads.CommentDto;
import com.project.blogApp.services.impl.CommentServiceImpl;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

		@Autowired
		private CommentServiceImpl commentServiceImpl;
		
		@PostMapping("/post/{post_id}")
		public ResponseEntity<CommentDto> addComment(
				@PathVariable long post_id,
				@RequestBody CommentDto cmCommentDto){
			System.out.println("Post_id =  "+post_id+" commentDto = "+cmCommentDto);
			return new ResponseEntity<CommentDto>
			(this.commentServiceImpl.addComment(cmCommentDto,post_id), HttpStatus.CREATED);
		}
				
		@GetMapping("/post/{post_id}")
		public ResponseEntity<List<CommentDto>> getCommentByPost(@PathVariable long post_id){
			return new ResponseEntity<List<CommentDto>>(
					this.commentServiceImpl.getCommentsByPost(post_id),
					HttpStatus.FOUND);
		}
}
