package ru.kildeev.marketApplication.core.converters;

import org.springframework.stereotype.Component;
import ru.kildeev.marketApplication.core.api.HashtagDto;
import ru.kildeev.marketApplication.core.entities.Hashtag;

@Component
public class HashtagConverter {

    public HashtagDto entityToDto(Hashtag hashtag) {
        HashtagDto hashtagDto = new HashtagDto();
        hashtagDto.setId(hashtag.getId());
        hashtagDto.setTitle(hashtag.getTitle());
        return hashtagDto;
    }
}
