package first.step.convertcoin.service;

import first.step.convertcoin.dto.UserRequestDto;
import first.step.convertcoin.dto.UserResponseDto;
import first.step.convertcoin.entity.UserEntity;
import first.step.convertcoin.exception.UserNotFoundException;
import first.step.convertcoin.mapper.UserMapper;
import first.step.convertcoin.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserResponseDto> getALlUsers() {
        List<UserEntity> users = userRepository.findAll();
        return UserMapper.listEntityToListResponseDto(users);
    }

    public UserResponseDto findUser(Long id) {
        return userRepository.findById(id)
                .map(UserMapper::userEntityToResponseDTO)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " does not exist"));
    }

    public UserResponseDto findByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(UserMapper::userEntityToResponseDTO)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));
    }

    public UserResponseDto createUser(@Valid UserRequestDto userDto) {
        UserEntity user = userRepository.save(UserMapper.userRequestDtoToUserEntity(userDto));
        return UserMapper.userEntityToResponseDTO(user);
    }

    public UserResponseDto updateUser(@Valid UserRequestDto requestDto, Long id) {
        return userRepository.findById(id)
                .map(user -> {
                        user.setEmail(requestDto.email());
                        user.setName(requestDto.name());
                       return UserMapper.userEntityToResponseDTO(userRepository.save(user));
                })
                .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " does not exist"));
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) throw new UserNotFoundException("User with ID " + id + " does not exist");
        userRepository.deleteById(id);
    }
}