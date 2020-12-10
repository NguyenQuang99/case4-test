package com.mvc.repository;

import com.mvc.entity.NewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewRepository extends JpaRepository<NewEntity, Long> {
    List<NewEntity> findAllByCategory_Name(String name);
//    List<NewEntity> findAllByCategory(String name);

    NewEntity findOneById(long id);


}
