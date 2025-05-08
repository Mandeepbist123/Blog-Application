package com.codewithmandeep.blog.services;

import com.codewithmandeep.blog.payloads.CommentDto;

public interface CommentService {
	
	CommentDto createCommet(CommentDto commentDto, Integer postId);
	void deleteComment(Integer commentId);

}
