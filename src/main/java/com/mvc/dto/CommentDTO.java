package com.mvc.dto;


import java.util.ArrayList;
import java.util.List;

public class CommentDTO {

    private Long id;

    private Long userId;

    private Long newId;

    private String comment;

    public Long getId() {
        return id;
    }

    private List<CommentDTO> listResult = new ArrayList<>();

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getNewId() {
        return newId;
    }

    public void setNewId(Long newId) {
        this.newId = newId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<CommentDTO> getListResult() {
        return listResult;
    }

    public void setListResult(List<CommentDTO> listResult) {
        this.listResult = listResult;
    }
}
