package com.dwalter.bookingsystem.comment.service;

import com.dwalter.bookingsystem.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentDbService {
    CommentRepository commentRepository;
}
