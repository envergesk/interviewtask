package ru.kildeev.marketApplication.core.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HashtagDto {

    private Long id;
    private String title;
}
