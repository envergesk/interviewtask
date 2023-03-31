package ru.kildeev.marketApplication.core.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kildeev.marketApplication.core.entities.Comment;
import ru.kildeev.marketApplication.core.entities.Company;
import ru.kildeev.marketApplication.core.entities.Role;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;
    private String username;
    private String email;
    private BigDecimal balance;
    private List<RoleDto> roles;
    private List<CompanyDto> companies;
    private Boolean isDeprecated;
    private List<CommentDto> comments;
}
