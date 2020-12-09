package com.mvc.converter;


import com.mvc.dto.CommentDTO;
import com.mvc.entity.CommentEntity;
import org.springframework.stereotype.Component;

@Component
public class CommentConverter {


    public CommentDTO toDTO(CommentEntity entity) {
        CommentDTO result = new CommentDTO();
        result.setId(entity.getId());
        result.setUserId(entity.getUserEntity().getId());
        result.setNewId(entity.getNewEntity().getId());
        result.setComment(entity.getComment());
        return result;
    }

    public CommentEntity toEntity(CommentDTO dto) {
        CommentEntity result = new CommentEntity();
        result.setId(dto.getId());
        result.setComment(dto.getComment());
        return result;
    }


}
