package com.example.task.service;

import com.example.task.dto.UserDto;
import com.example.task.entity.Task;
import com.example.task.entity.User;
import com.example.task.exception.MyException;
import com.example.task.mapper.UserDtoToUser;
import com.example.task.repository.UserRepository;
import java.util.ArrayList;
import java.util.Collection;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final UserDtoToUser mapper;

    @Override
    public User getUserByUsername(String username) {
        if (!userRepository.existsByUsername(username)) {
            throw new MyException("This username not found", HttpStatus.NOT_FOUND);
        }
        return userRepository.findByUsername(username).get();

    }

    @Override
    public User createUser(UserDto dto) {
        if (!userRepository.existsByUsername(dto.getUsername())) {
            throw new MyException("Invalid, this username already exists", HttpStatus.BAD_REQUEST);
        }
        return userRepository.save(mapper.apply(dto));
    }

    @Override
    @Transactional
    public void addTaskToUser(Task task, String username) {
        if (!userRepository.existsByUsername(username)) {
            throw new MyException("Invalid, this username already exists", HttpStatus.BAD_REQUEST);
        }
        User user = userRepository.findByUsername(username).get();
        user.getListTasks().add(task);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found."));
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach((role) -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attributes.getRequest().getSession(true);

        session.setAttribute("id", user.getId());
        session.setAttribute("username", user.getUsername());

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

}
