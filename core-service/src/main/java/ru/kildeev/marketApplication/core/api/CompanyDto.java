package ru.kildeev.marketApplication.core.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kildeev.marketApplication.core.entities.Product;
import ru.kildeev.marketApplication.core.entities.User;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDto {

    private Long id;
    private String title;
    private List<ProductDto> products;
    private String username;
}
