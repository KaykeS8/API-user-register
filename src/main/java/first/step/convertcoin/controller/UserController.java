package first.step.convertcoin.controller;

import first.step.convertcoin.dto.UserRequestDto;
import first.step.convertcoin.dto.UserResponseDto;
import first.step.convertcoin.entity.UserEntity;
import first.step.convertcoin.repository.UserRepository;
import first.step.convertcoin.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        return ResponseEntity.ok().body(userService.getALlUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findUser(@PathVariable Long id) {
        return ResponseEntity.ok().body(userService.findUser(id));
    }

    @GetMapping("/search")
    public ResponseEntity<UserResponseDto> findUserByEmail(@RequestParam(name = "email") @Email String email) {
        return ResponseEntity.ok().body(userService.findByEmail(email));
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody @Valid UserRequestDto userRequestDto) {
       return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userRequestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@RequestBody @Valid UserRequestDto userRequestDto, @PathVariable Long id){
        return ResponseEntity.ok().body(userService.updateUser(userRequestDto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}