package com.codewithmandeep.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithmandeep.blog.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment , Integer> {

}
