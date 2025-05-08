package com.codewithmandeep.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithmandeep.blog.entities.Category;
import com.codewithmandeep.blog.entities.Post;
import com.codewithmandeep.blog.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer> {
	
	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
    List<Post> findByTitleContaining(String title);
}
