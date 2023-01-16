package com.project.blogApp.services;

import java.util.List;


import com.project.blogApp.payloads.CategoryDto;

public interface CategoryService {

	//create
	public CategoryDto createCategory(CategoryDto categoryDto);
	
	//update
	public CategoryDto updateCategory(CategoryDto categoryDto, long categoryId);
	
	//get All
	public List<CategoryDto> getAllCategory();
	
	//get by id
	public CategoryDto getCategoryById(long categoryId);
	
	//delete
	public void deleteCategory(long categoryId);
}
