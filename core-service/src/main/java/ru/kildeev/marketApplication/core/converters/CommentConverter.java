package ru.kildeev.marketApplication.core.converters;

import org.springframework.stereotype.Component;
import ru.kildeev.marketApplication.core.api.CommentDto;
import ru.kildeev.marketApplication.core.entities.Comment;

@Component
public class CommentConverter {

    public CommentDto entityToDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setCommentText(comment.getCommentText());
        commentDto.setScore(comment.getScore());
        return commentDto;
    }

    public Comment dtoToEntity(CommentDto commentDto) {
        Comment comment = new Comment();

        comment.setCommentText(commentDto.getCommentText());
        comment.setScore(comment.getScore());
        return comment;
    }
}
