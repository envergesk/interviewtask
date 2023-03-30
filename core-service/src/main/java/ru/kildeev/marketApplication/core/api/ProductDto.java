package ru.kildeev.marketApplication.core.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kildeev.marketApplication.core.entities.Comment;
import ru.kildeev.marketApplication.core.entities.Hashtag;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private Long id;
    private String title;
    private String company;
    private BigDecimal price;
    private Integer remains;
    private String discount;
    private List<CommentDto> comments;
    private List<HashtagDto> hashtags;
    private String specifications;
}
