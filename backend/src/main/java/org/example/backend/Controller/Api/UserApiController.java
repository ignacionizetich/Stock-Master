package org.example.backend.Controller.Api;

import org.example.backend.Exceptions.UserException;
import org.example.backend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.example.backend.Models.User;

@RestController
@RequestMapping("/api/users")
public class UserApiController {

    private final UserService userService;

    @Autowired
    public UserApiController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerRequest) {
        try{
            User registeredUser = new User();
            registeredUser.setName(registerRequest.name());
            registeredUser.setSurname(registerRequest.surname());
            registeredUser.setEmail(registerRequest.email());
            registeredUser.setPassword(registerRequest.password());
            registeredUser.setAccStatus(registerRequest.acc_status());
            registeredUser.setRole(registeredUser.getRole());
            userService.registerUser(registeredUser);
            return ResponseEntity.ok().body("The user has been registered!");
        }catch (UserException e){
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    private record RegisterDTO(String name, String surname, String email, String password, int acc_status, String role) {}
}


