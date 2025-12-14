package com.banking.banking_app.service;

import com.banking.banking_app.dto.UserDTO;
import com.banking.banking_app.model.User;
import com.banking.banking_app.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;

    }

    @Transactional
    public UserDTO createUser(String userName, String email){
        User user = userRepository.save(new User(userName, email));
        return userDTOConverter(user);
    }

    public UserDTO userDTOConverter(User user){
        UserDTO userDTO = new UserDTO(user);
        return userDTO;
    }


}
