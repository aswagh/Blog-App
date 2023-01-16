package com.project.blogApp.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.blogApp.entities.Category;
import com.project.blogApp.exceptions.ResourceNotFoundException;
import com.project.blogApp.payloads.CategoryDto;
import com.project.blogApp.repositories.CategoryRepository;
import com.project.blogApp.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ModelMapper modelMapper;
	private Category category;
	private CategoryDto categoryDto;
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		this.category = this.dtoToCategory(categoryDto);
		return this.categoryToDto(categoryRepository.save(category));
//		return  this.categoryToDto(categoryRepository.save(this.dtoToCategory(categoryDto)));
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, long categoryId) {
		category = this.categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","ID",categoryId));
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		return this.categoryToDto(categoryRepository.save(category));
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category> categories = this.categoryRepository.findAll();
		return categories.stream().map(
				category->this.categoryToDto(category)).collect(Collectors.toList());
	}

	@Override
	public CategoryDto getCategoryById(long categoryId) {
		return this.categoryToDto(
				categoryRepository.findById(
						categoryId).orElseThrow(
								()->new ResourceNotFoundException("Category","ID",categoryId)));
	}

	@Override
	public void  deleteCategory(long categoryId) {
		Category category = categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","ID",categoryId));
		this.categoryRepository.delete(category);
		
	}
	public CategoryDto categoryToDto(Category category) {
		this.categoryDto = this.modelMapper.map(category, CategoryDto.class);
		return this.categoryDto;
	}
	public Category dtoToCategory(CategoryDto categoryDto) {
		this.category = this.modelMapper.map(categoryDto, Category.class);
		return this.category;
	}

}
