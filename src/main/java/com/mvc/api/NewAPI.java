package com.mvc.api;

import com.mvc.dto.CommentDTO;
import com.mvc.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController(value = "newAPIOfWeb")
public class NewAPI {

    @Autowired
    private ICommentService commentService;

    @PostMapping("/api/comment")
    public CommentDTO createNew(@RequestBody CommentDTO commentDTO) {
        return commentService.save(commentDTO);
    }

}