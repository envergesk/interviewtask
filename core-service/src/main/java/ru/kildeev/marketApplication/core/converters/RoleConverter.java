package ru.kildeev.marketApplication.core.converters;

import org.springframework.stereotype.Component;
import ru.kildeev.marketApplication.core.api.RoleDto;
import ru.kildeev.marketApplication.core.entities.Role;

@Component
public class RoleConverter {

    public RoleDto entityToDto(Role role) {
        RoleDto roleDto = new RoleDto();
        roleDto.setId(role.getId());
        roleDto.setTitle(roleDto.getTitle());
        return roleDto;
    }
}
