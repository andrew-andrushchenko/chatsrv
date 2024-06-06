package com.andrii_a.chatsrv.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public void saveUser(UserDto userDto) {
        UserDocument userDocument = new UserDocument();
        userDocument.setNickName(userDto.nickname());
        userDocument.setFullName(userDto.fullName());
        userDocument.setStatus(UserStatus.ONLINE);

        userRepository.save(userDocument);
    }

    public void disconnect(UserDto userDto) {
        var storedUser = userRepository.findById(userDto.nickname()).orElse(null);
        if (storedUser != null) {
            storedUser.setStatus(UserStatus.OFFLINE);
            userRepository.save(storedUser);
        }
    }

    public List<UserDto> findConnectedUsers() {
        return userRepository.findAllByStatus(UserStatus.ONLINE).stream().map(userMapper::mapToDto).collect(Collectors.toList());
    }

}
