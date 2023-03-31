package ru.kildeev.marketApplication.core.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.kildeev.marketApplication.core.api.CommentDto;
import ru.kildeev.marketApplication.core.api.CompanyDto;
import ru.kildeev.marketApplication.core.api.RoleDto;
import ru.kildeev.marketApplication.core.api.UserDto;
import ru.kildeev.marketApplication.core.entities.Comment;
import ru.kildeev.marketApplication.core.entities.Company;
import ru.kildeev.marketApplication.core.entities.Role;
import ru.kildeev.marketApplication.core.entities.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserConverter {

    private final RoleConverter roleConverter;
    private final CompanyConverter companyConverter;
    private final CommentConverter commentConverter;


    public UserDto entityToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setBalance(user.getBalance());

        List<RoleDto> roles = user.getRoles().stream().map(role -> roleConverter.entityToDto(role))
                .collect(Collectors.toList());
        userDto.setRoles(roles);

        List<CompanyDto> companies = user.getCompanies().stream().map(company -> companyConverter.entityToDto(company))
                .collect(Collectors.toList());
        userDto.setCompanies(companies);

        List<CommentDto> comments = user.getComments().stream().map(comment -> commentConverter.entityToDto(comment))
                .collect(Collectors.toList());
        userDto.setComments(comments);

        return userDto;
    }
}
