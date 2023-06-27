package com.pratham.blog.services;

import java.util.List;

import com.pratham.blog.entities.Post;
import com.pratham.blog.paylods.CategoryDto;
import com.pratham.blog.paylods.PostDto;
import com.pratham.blog.paylods.PostResponse;

public interface PostService {
   
	PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
	
	PostDto updatePost(PostDto postDto,Integer postId);
	
	void deletrPost(Integer postId);
	
	PostResponse getAllPost(Integer pageNumber ,Integer pageSize,String sortBy,String sortDir);
	
	PostDto getPostById(Integer PostId);
	
	List<PostDto> getPostsByCategory(Integer categoryId);
	
	List<PostDto> getPostsByUser(Integer userId);
	
	List<PostDto> searchPosts(String Keyword);
}
