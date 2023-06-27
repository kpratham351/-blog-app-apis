package com.pratham.blog.reposetories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pratham.blog.entities.Role;

public interface RoleRepo extends JpaRepository<Role,Integer> {

}
