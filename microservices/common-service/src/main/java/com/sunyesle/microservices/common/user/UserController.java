package com.sunyesle.microservices.common.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> get(@PathVariable String id) {
        return ResponseEntity.ok(userService.get(id));
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers(@RequestParam List<String> ids){
        return ResponseEntity.ok(userService.getUsers(ids));
    }
}
