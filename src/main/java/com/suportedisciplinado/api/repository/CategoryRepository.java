package com.suportedisciplinado.api.repository;

import com.suportedisciplinado.api.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
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
