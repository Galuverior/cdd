package com.github.galuverior.cdd.bill.repository;

import com.github.galuverior.cdd.bill.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
