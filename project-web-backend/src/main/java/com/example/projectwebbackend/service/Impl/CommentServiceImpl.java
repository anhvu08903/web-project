package com.example.projectwebbackend.service.Impl;

import com.example.projectwebbackend.entity.Comment;
import com.example.projectwebbackend.repository.CoachRepository;
import com.example.projectwebbackend.repository.CommentRepository;
import com.example.projectwebbackend.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired private CommentRepository commentRepository;
    @Override
    public List<Comment> getAllComment() {
        return (List<Comment>) commentRepository.findAll();
    }

    @Override
    public List<Comment> getCommentByAdminId(Long id) {
//        return (List<Comment>) commentRepository.findAllByCommentid(id);
        return (List<Comment>) commentRepository.findAllByAdmin_Adminid(id);

    }
}
