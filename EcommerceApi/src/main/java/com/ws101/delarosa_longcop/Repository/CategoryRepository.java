package com.ws101.delarosa_longcop.repository;

import com.ws101.delarosa_longcop.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;


public interface Categoryrepository extends JpaRepository<Category, Long> {
}