package com.sunyesle.microservices.common.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;

    public User get(String id) {
        return userMapper.selectById(id);
    }
}
