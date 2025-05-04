package org.example.salaryincrement.Service;

import jakarta.transaction.Transactional;
import org.example.salaryincrement.DTO.UserDTO;
import org.example.salaryincrement.Exceptions.UserNotFoundException;
import org.example.salaryincrement.Exceptions.InvalidUserDataException;
import org.example.salaryincrement.Model.User;
import org.example.salaryincrement.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> getAllUsers() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }



    private UserDTO mapToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        return userDTO;
    }


    public UserDTO createUser(UserDTO userDTO) {
        if (userDTO.getFirstName() == null || userDTO.getFirstName().isEmpty()) {
            throw new InvalidUserDataException("First name cannot be empty");
        }

        User user = new User(userDTO.getFirstName(), userDTO.getLastName());
        User savedUser = userRepository.save(user);
        return mapToDTO(savedUser);
    }


    public UserDTO getUserById(Long userId) {
        Optional<User> user = userRepository.findByUserId(userId);
        if (user.isPresent()) {
            return mapToDTO(user.get());
        } else {
            throw new UserNotFoundException("User not found with id: " + userId);
        }
    }
    @Transactional
    public UserDTO updateUser(Long userId, UserDTO userDTO) {
        Optional<User> optionalUser = userRepository.findByUserId(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            User updatedUser = userRepository.save(user);
            return mapToDTO(updatedUser);
        } else {
            throw new UserNotFoundException("User not found with id: " + userId);
        }
    }

    @Transactional
    public void deleteUser(Long userId) {
        if (userRepository.existsByUserId(userId)) {
            userRepository.deleteByUserId(userId);
        } else {
            throw new UserNotFoundException("User not found with id: " + userId);
        }
    }
}