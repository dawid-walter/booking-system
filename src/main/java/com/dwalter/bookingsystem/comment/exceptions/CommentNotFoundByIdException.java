package com.dwalter.bookingsystem.comment.exceptions;

public class CommentNotFoundByIdException extends RuntimeException {
    public CommentNotFoundByIdException(final Long id) {
        super("Could not find comment by id: " + id);
    }
}
