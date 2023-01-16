package com.project.blogApp.payloads;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class CategoryDto {

	private long category_id;
		
		@NotEmpty
		@Size(min = 2, message = "Category cant be of one letter")
		private String categoryTitle;
		
		@NotEmpty
		private String categoryDescription;
		
}
