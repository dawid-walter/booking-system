package com.dwalter.bookingsystem.functionality.comment.mapper;

import com.dwalter.bookingsystem.functionality.comment.entity.Comment;
import com.dwalter.bookingsystem.functionality.comment.dto.CommentDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    CommentDto mapToCommentDto(Comment comment);

    Comment mapToComment(CommentDto commentDto);

    List<CommentDto> mapToCommentsDto(List<Comment> comments);
}
