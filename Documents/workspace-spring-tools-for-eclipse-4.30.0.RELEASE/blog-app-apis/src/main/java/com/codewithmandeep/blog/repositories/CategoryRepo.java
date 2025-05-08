package com.codewithmandeep.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithmandeep.blog.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
