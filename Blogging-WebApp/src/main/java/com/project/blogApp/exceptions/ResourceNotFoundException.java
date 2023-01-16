package com.project.blogApp.exceptions;

public class ResourceNotFoundException extends RuntimeException{
	private String resourceName;
	private String field;
	private long fieldValue;
	public ResourceNotFoundException(String resourceName, String field, long fieldValue) {
		super(String.format("%s not found with %s : %d", resourceName,field,fieldValue));
		this.resourceName = resourceName;
		this.field = field;
		this.fieldValue = fieldValue;
	}
	
}
