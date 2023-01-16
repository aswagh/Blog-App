package com.project.blogApp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.blogApp.entities.Comment;
import com.project.blogApp.entities.Post;
import com.project.blogApp.entities.User;
import com.project.blogApp.payloads.CommentDto;

public interface CommentRepository extends JpaRepository<Comment, Long>{
	
	public List<Comment> findByPost(Post post);
	
	
}
