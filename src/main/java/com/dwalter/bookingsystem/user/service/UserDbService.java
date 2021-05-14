package com.dwalter.bookingsystem.user.service;

import com.dwalter.bookingsystem.user.domain.User;
import com.dwalter.bookingsystem.user.domain.UserDto;
import com.dwalter.bookingsystem.user.exceptions.UserNotFoundException;
import com.dwalter.bookingsystem.user.mapper.UserMapper;
import com.dwalter.bookingsystem.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDbService implements UserDetailsService {
    private static final String USER_NOT_FOUND = "User not found";
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND));
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public Optional<User> getById(Long id) {
        return userRepository.findById(id);
    }

    public User create(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public UserDto update(UserDto userDto) {
        User user = userRepository.findById(userDto.getId())
                .orElseThrow(() -> new UserNotFoundException(userDto.getId()));
        user.setUsername(userDto.getUsername());
        user.setEmail(user.getEmail());
        user.setPassword(userDto.getPassword());
        return userMapper.mapToUserDto(user);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
