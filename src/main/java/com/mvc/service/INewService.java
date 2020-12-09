package com.mvc.service;

import com.mvc.dto.NewDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface INewService {
    List<NewDTO> findAll(Pageable pageable);

    List<NewDTO> findAll();

    List<NewDTO> getAllByCategory(String code);

    int getTotalItem();

    NewDTO findById(long id);

    NewDTO save(NewDTO dto);

    void delete(long[] ids);

}




