package com.project.blogApp.controllers;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.blogApp.exceptions.ResourceNotFoundException;
import com.project.blogApp.payloads.ApiResponse;
import com.project.blogApp.payloads.PostDto;
import com.project.blogApp.services.impl.PostServiceImpl;

@RestController
@RequestMapping("/api/post")
public class PostController {
		
		@Autowired
		private PostServiceImpl postServiceImpl;
		
		private ApiResponse apiResponse;
		
		@PostMapping("/user/{user_id}/category/{category_id}")
		public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,
				@PathVariable long user_id,
				@PathVariable long category_id) {
			return new ResponseEntity<PostDto>(
					this.postServiceImpl.createPost(postDto, user_id, category_id) ,HttpStatus.CREATED);
		}
		
		@PutMapping("/{post_id}")
		public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable long post_id){
			return new ResponseEntity<PostDto>(
					this.postServiceImpl.updatePost(postDto, post_id),HttpStatus.CREATED);
		}
		@GetMapping("/{post_id}")
		public ResponseEntity<PostDto> getPost(@PathVariable long post_id){
			return new ResponseEntity<PostDto>(this.postServiceImpl.getPost(post_id),HttpStatus.FOUND);
		}
		@DeleteMapping("/{post_id}")
		public ResponseEntity<ApiResponse> deletePost(@PathVariable long  post_id){
			this.postServiceImpl.deletePost(post_id);
			apiResponse = new ApiResponse("Deleted the Post with ID - "+post_id+"Successfully" ,true);
			return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.OK);
		}
		@GetMapping("/")
		public ResponseEntity<List<PostDto>> getPosts(){
			return new ResponseEntity<List<PostDto>>(this.postServiceImpl.getAllPost(),HttpStatus.FOUND);
		}
		@GetMapping("/pages")
		public ResponseEntity<List<PostDto>> getPostsByPage(
				@RequestParam(value = "pageNumber",defaultValue = "0",required = false) Integer pageNumber,
				@RequestParam(value = "pageSize",defaultValue = "10",required = false) Integer pageSize,
				@RequestParam(value = "sortBy", defaultValue = "postContent",required = false) String sortBy,
				@RequestParam(value = "sortWith", defaultValue = "asc", required = false) String sortWith
				){
			return new ResponseEntity<List<PostDto>>(
					this.postServiceImpl.getPosts(pageNumber, pageSize,sortBy,sortWith), HttpStatus.FOUND);
		}
		@GetMapping("/user/{user_id}") 
		public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable long user_id){
			return new ResponseEntity<List<PostDto>>(this.postServiceImpl.getPostByUser(user_id),HttpStatus.FOUND);
		}
		@GetMapping("/category/{category_id}")
		public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable long category_id){
			return new ResponseEntity<List<PostDto>>(this.postServiceImpl.getPostByCategory(category_id),HttpStatus.FOUND);
		}
//		@GetMapping("/search/{keyword}")
//		public ResponseEntity<List<PostDto>> searchByTitle(
//				@PathVariable String keyword){
//			
//			return new ResponseEntity<List<PostDto>>(this.postServiceImpl.searchPostByTitle(keyword),HttpStatus.FOUND);
//		}
}
