package com.dwalter.bookingsystem.functionality.comment.mapper;

import com.dwalter.bookingsystem.functionality.comment.controller.dto.CreateCommentDto;
import com.dwalter.bookingsystem.functionality.comment.model.Comment;
import com.dwalter.bookingsystem.functionality.comment.controller.dto.CommentDto;
import com.dwalter.bookingsystem.functionality.reservation.controller.dto.ReservationDto;
import com.dwalter.bookingsystem.functionality.reservation.model.Reservation;
import org.mapstruct.Mapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class CommentMapper {
    private CommentMapper() {
    }

    public static Comment mapToComment(CommentDto commentDto) {
        return Comment.builder()
                .id(commentDto.getId())
                .content(commentDto.getContent())
                .created(commentDto.getCreated())
                .build();
    }

    public static CommentDto mapToCommentDto(Comment comment) {
        return CommentDto.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .created(comment.getCreated())
                .build();
    }

    public static List<CommentDto> mapToCommentsDto(List<Comment> comments) {
        return comments.stream()
                .map(CommentMapper::mapToCommentDto)
                .collect(Collectors.toList());
    }

    public static Comment mapFromCreateToComment(CreateCommentDto createCommentDto) {
        return Comment.builder()
                .content(createCommentDto.getContent())
                .created(LocalDateTime.now())
                .roomId(createCommentDto.getRoomId())
                .build();
    }
}
