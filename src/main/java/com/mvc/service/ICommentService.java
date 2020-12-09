package com.mvc.service;


import com.mvc.dto.CommentDTO;

import java.util.List;

public interface ICommentService {
    CommentDTO save(CommentDTO dto);

    List<CommentDTO> getAllByNewId(long newId);

}
