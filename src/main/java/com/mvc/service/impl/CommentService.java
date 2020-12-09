package com.mvc.service.impl;






import com.mvc.converter.CommentConverter;
import com.mvc.dto.CommentDTO;
import com.mvc.entity.CommentEntity;
import com.mvc.entity.NewEntity;
import com.mvc.entity.UserEntity;
import com.mvc.repository.CommentRepository;
import com.mvc.repository.NewRepository;
import com.mvc.repository.UserRepository;
import com.mvc.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CommentService implements ICommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private NewRepository newRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentConverter commentConverter;

    @Override
    public CommentDTO save(CommentDTO dto) {

        NewEntity newEntity = newRepository.findOneById(dto.getNewId());

        UserEntity userEntity = userRepository.findOneById(dto.getUserId());

        CommentEntity commentEntity = new CommentEntity();

        commentEntity = commentConverter.toEntity(dto);

        commentEntity.setNewEntity(newEntity);

        commentEntity.setUserEntity(userEntity);

        return commentConverter.toDTO(commentRepository.save(commentEntity));

    }


    @Override
    public List<CommentDTO> getAllByNewId(long newId) {
        List<CommentDTO> result = new ArrayList<>();
        List<CommentEntity> entities = commentRepository.findAllByNewEntity_Id(newId);
        for (CommentEntity item : entities) {
            CommentDTO dto = commentConverter.toDTO(item);
            result.add(dto);
        }
        return result;
    }
}
