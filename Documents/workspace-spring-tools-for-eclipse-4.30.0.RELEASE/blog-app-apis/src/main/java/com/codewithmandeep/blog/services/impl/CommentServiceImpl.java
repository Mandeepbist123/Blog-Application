package com.codewithmandeep.blog.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codewithmandeep.blog.entities.Comment;
import com.codewithmandeep.blog.entities.Post;
import com.codewithmandeep.blog.exceptions.ResourceNotFoundException;
import com.codewithmandeep.blog.payloads.CommentDto;
import com.codewithmandeep.blog.repositories.CommentRepo;
import com.codewithmandeep.blog.repositories.PostRepo;
import com.codewithmandeep.blog.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private CommentRepo commentRepo;

	@Autowired
	private ModelMapper modelMapper;

	
	
	
	@Override
	public CommentDto createCommet(CommentDto commentDto, Integer postId) {
		// TODO Auto-generated method stub
		Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","post id", postId));
	Comment comment =	this.modelMapper.map(commentDto, Comment.class);
	comment.setPost(post);
	Comment savedComment = this.commentRepo.save(comment);
		return this.modelMapper.map(savedComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		// TODO Auto-generated method stub
		Comment com = this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","CommentId",commentId));
       this.commentRepo.delete(com);
	}

}
