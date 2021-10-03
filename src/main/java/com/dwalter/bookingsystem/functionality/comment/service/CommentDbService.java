package com.dwalter.bookingsystem.functionality.comment.service;

import com.dwalter.bookingsystem.functionality.comment.model.Comment;
import com.dwalter.bookingsystem.functionality.comment.controller.dto.CommentDto;
import com.dwalter.bookingsystem.functionality.comment.exceptions.CommentNotFoundByIdException;
import com.dwalter.bookingsystem.functionality.comment.mapper.CommentMapper;
import com.dwalter.bookingsystem.functionality.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentDbService {
    private final CommentRepository commentRepository;

    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    public Optional<Comment> getById(Long id) {
        return commentRepository.findById(id);
    }

    public Comment create(Comment comment) {
        commentRepository.save(comment);
        return comment;
    }

    @Transactional
    public CommentDto update(CommentDto commentDto) {
        Comment comment = commentRepository.findById(commentDto.getId())
                .orElseThrow(() -> new CommentNotFoundByIdException(commentDto.getId()));
        comment.setContent(commentDto.getContent());
        comment.setCreated(commentDto.getCreated());
        return CommentMapper.mapToCommentDto(comment);
    }

    public void delete(Long id) {
        commentRepository.deleteById(id);
    }
}
