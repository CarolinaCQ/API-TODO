package com.example.task.service;

import com.example.task.dto.RoleDto;
import com.example.task.entity.Role;
import java.util.List;

public interface RoleService {
    
    Role getRoleById(Long id);
    Role createRole(RoleDto role);
    List<Role> addRoleToUser(List<Long> listRolesId);
    void deleteRole(Long id);
    void recoverDeletedRole(Long id);
}
