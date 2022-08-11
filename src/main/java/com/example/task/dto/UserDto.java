package com.example.task.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDto {
    
    private Long id;
    
    private String username;

    private String password;

    private List<Long> listRolesId;

}
