package com.example.task.mapper;

import com.example.task.dto.RoleDto;
import com.example.task.entity.Role;
import java.util.function.Function;
import org.springframework.stereotype.Component;

@Component
public class RoleDtoToRole implements Function<RoleDto, Role> {

    @Override
    public Role apply(RoleDto dto) {
        
        Role role = new Role();
        role.setName(dto.getName());
        
        return role;
    }
    
    
}
