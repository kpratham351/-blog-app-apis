package com.pratham.blog.controllers;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.pratham.blog.paylods.ApiResponse;
import com.pratham.blog.paylods.CategoryDto;
import com.pratham.blog.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	@PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@Valid@RequestBody CategoryDto categoryDto){
		CategoryDto createCategory =this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(createCategory ,HttpStatus.CREATED);
	}
	@PutMapping("/{catId}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid@RequestBody CategoryDto categoryDto,@PathVariable Integer catId){
		CategoryDto updateCategory =this.categoryService.updateCategory(categoryDto,catId);
		return new ResponseEntity<CategoryDto>(updateCategory ,HttpStatus.OK);
	}
	@DeleteMapping("/{catId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer catId){
		this.categoryService.deleteCategory(catId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("category is deleted successfully",false) ,HttpStatus.OK);
	}
	@GetMapping("/{catId}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer catId){
	    CategoryDto categoryDto=this.categoryService.getCategory(catId);
	    return new ResponseEntity<CategoryDto>(categoryDto,HttpStatus.OK);
	}
	@GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getCategories(@PathVariable Integer catId){
	    List<CategoryDto> categories =this.categoryService.getCategories();
	    return ResponseEntity.ok(categories);
	}
	
}