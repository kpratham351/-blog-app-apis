package com.pratham.blog.reposetories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pratham.blog.entities.Category;
public interface CategoryRepo extends JpaRepository<Category,Integer> {

}
