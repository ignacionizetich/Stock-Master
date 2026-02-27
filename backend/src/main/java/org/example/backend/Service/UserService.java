package org.example.backend.Service;

import org.example.backend.Exceptions.UserException;
import org.example.backend.Models.User;
import org.example.backend.Repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(User user) throws UserException {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new UserException("Email already has an account.");
        }
        if (user.getPassword().length() < 6) {
            throw new UserException("Password must be at least 6 characters long.");
        }
        if (user.getName().isBlank() ||  user.getSurname().isBlank() ) {
            throw new UserException("Name and Surname are both required.");
        }

        user.setName(capitalizeNameAndSurname(user.getName()));
        user.setSurname(capitalizeNameAndSurname(user.getSurname()));
        user.setAccStatus(0);  // 0 inactive, 1 active, 2 banned
        user.setRole("USER"); // USER or ADMIN
        user.setPassword(passwordEncoder.encode(user.getPassword()));
         userRepository.save(user);
    }

    /// CAPITALIZE NAME AND SURNAME
    public String capitalizeNameAndSurname(String name) {
        return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }

}
