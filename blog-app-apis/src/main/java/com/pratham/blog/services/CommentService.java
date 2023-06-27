package com.pratham.blog.services;

import org.springframework.stereotype.Service;

import com.pratham.blog.paylods.CommentDto;
public interface CommentService {

	CommentDto createComment(CommentDto commentDto,Integer postId);
	
	void deleteComment (Integer commentId);
}
