package com.sunyesle.microservices.common.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;

    public User get(String id) {
        return userMapper.selectById(id);
    }

    public List<User> getUsers(List<String> ids) {
        return userMapper.selectByIds(ids);
    }
}
