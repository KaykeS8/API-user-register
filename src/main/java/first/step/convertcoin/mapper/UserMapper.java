package first.step.convertcoin.mapper;

import first.step.convertcoin.dto.UserRequestDto;
import first.step.convertcoin.dto.UserResponseDto;
import first.step.convertcoin.entity.UserEntity;
import java.util.List;

public class UserMapper {
    public static UserResponseDto userEntityToResponseDTO(UserEntity user) {
        return new UserResponseDto(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }

    public static UserEntity userRequestDtoToUserEntity(UserRequestDto userDTo) {
        return new UserEntity(
                userDTo.name(),
                userDTo.email()
        );
    }

    public static List<UserResponseDto> listEntityToListResponseDto(List<UserEntity> users) {
        return users.stream().map(UserMapper::userEntityToResponseDTO).toList();
    }
}
