package com.project.blogApp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.blogApp.entities.Category;
import com.project.blogApp.entities.Post;
import com.project.blogApp.entities.User;
import com.project.blogApp.payloads.PostDto;

public interface PostRepository extends JpaRepository<Post, Long>{

	public List<Post> findByUser(User user);
	
	public List<Post> findByCategory(Category category);
	
//	@Query("select p from post p where p.title like : key")
//	public List<Post> getByTitleContaining(@Param("key") String title);
}
