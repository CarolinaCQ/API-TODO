package com.example.task.service;

import com.example.task.dto.RoleDto;
import com.example.task.entity.Role;
import com.example.task.exception.MyException;
import com.example.task.mapper.RoleDtoToRole;
import com.example.task.repository.RoleRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RoleServiceImplementation implements RoleService{

    private final RoleRepository roleRepository;
    private final RoleDtoToRole mapper; 
    
    @Override
    public Role getRoleById(Long id) {
        if(!roleRepository.existsById(id)){
            throw new MyException("Role not found.", HttpStatus.NOT_FOUND);
        }
        return roleRepository.findById(id).get();
    }

    @Override
    public Role createRole(RoleDto dto) {
        return roleRepository.save(mapper.apply(dto));
    }

    @Override
    @Transactional
    public List<Role> addRoleToUser(List<Long> listRolesId) {
        List<Role> listRoles = listRolesId.stream().map((id) -> getRoleById(id)).collect(Collectors.toList());
        return listRoles;
    }

    @Override
    @Transactional
    public void deleteRole(Long id) {
        if(!roleRepository.existsById(id)){
            throw new MyException("Role not found", HttpStatus.NOT_FOUND);
        }
        roleRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void recoverDeletedRole(Long id) {
        if(!roleRepository.existsById(id)){
            throw new MyException("Role not found", HttpStatus.NOT_FOUND);
        }
        roleRepository.recoverDeletedRole(id);
    }
    
    
}
