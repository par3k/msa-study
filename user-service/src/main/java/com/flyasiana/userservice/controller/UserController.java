package com.flyasiana.userservice.controller;

import com.flyasiana.userservice.dto.UserDto;
import com.flyasiana.userservice.jpa.UserEntity;
import com.flyasiana.userservice.service.UserService;
import com.flyasiana.userservice.vo.Greeting;
import com.flyasiana.userservice.vo.RequestUser;
import com.flyasiana.userservice.vo.ResponseUser;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@Slf4j
public class UserController {

    private Environment env;
    private Greeting greeting;
    private UserService userService;

    @Autowired
    public UserController(Environment env, Greeting greeting, UserService userService) {
        this.env = env;
        this.greeting = greeting;
        this.userService = userService;
    }

    @GetMapping("/health-check")
    public String status() {
        return String.format("It's working in User Service"
                + ", port(local.server.port) =" + env.getProperty("local.server.port")
                + ", port(server.port) =" + env.getProperty("server.port"));
    }

    @GetMapping("/welcome")
    public String welcome(HttpServletRequest request) {
        log.info("users.welcome ip : {}, {}, {}, {}",
                request.getRemoteAddr(), request.getRemoteHost() ,request.getRequestURI(), request.getRequestURL());
        return greeting.getMessage();
    }

    @PostMapping("/users")
    public ResponseEntity<ResponseUser> createUser(@RequestBody RequestUser user) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserDto userDto = mapper.map(user, UserDto.class);
        userService.createUser(userDto);

        ResponseUser responseUser = mapper.map(user, ResponseUser.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseUser);
    }

    @GetMapping("/users")
    public ResponseEntity getUsers() {
        Iterable<UserEntity> userList = userService.getUserByAll();
        return ResponseEntity.status(HttpStatus.OK).body(userList);
    }
}
