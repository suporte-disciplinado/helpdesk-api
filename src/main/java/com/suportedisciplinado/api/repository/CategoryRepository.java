package com.suportedisciplinado.api.repository;

import com.suportedisciplinado.api.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long>
{
    @Override
    Category getOne(Long id);

    @Override
    List<Category> findAll();

    @Override
    void deleteById(Long id);

    @Override
    <S extends Category> S saveAndFlush(S entity);
}
