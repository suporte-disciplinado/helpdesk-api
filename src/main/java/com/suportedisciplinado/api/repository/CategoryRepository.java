package com.suportedisciplinado.api.repository;

import com.suportedisciplinado.api.model.Category;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CategoryRepository extends PagingAndSortingRepository<Category, Long> {
}
