package com.example.task.mapper;

import com.example.task.dto.UserDto;
import com.example.task.entity.User;
import com.example.task.service.RoleService;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDtoToUser implements Function<UserDto, User> {

    private final BCryptPasswordEncoder encoder;
    private final RoleService roleService;
    
    @Override
    public User apply(UserDto dto) {
        
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(encoder.encode(dto.getPassword()));
        user.setRoles(roleService.addRoleToUser(dto.getListRolesId()));
        
        return user;
    }

}
