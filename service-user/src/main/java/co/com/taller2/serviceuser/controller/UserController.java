package co.com.taller2.serviceuser.controller;


import co.com.taller2.serviceuser.entities.User;
import co.com.taller2.serviceuser.helper.ResponseBuilder;
import co.com.taller2.serviceuser.model.Response;
import co.com.taller2.serviceuser.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ResponseBuilder builder;

    @PostMapping
    public Response save(@RequestBody User user){
        userService.save(user);
        return builder.success(user);
    }
    @DeleteMapping("/{id}")
    public Response  delete(@PathVariable("id") Long id){
        User user = userService.findById(id);
        if(user==null){
            return builder.failed("Not found product");
        }
        userService.delete(user);
        return builder.success(user);
    }
    @GetMapping
    public Response  findAll(){
        List<User> users = userService.findAll();
        if(users.isEmpty()){
            return builder.failed("user is empty");
        }
        return builder.success(users);
    }
    @GetMapping("/{id}")
    public Response  findByd(@PathVariable("id") Long id){
        User user = userService.findById(id);
        if(user==null){
            return builder.failed("Not found product");
        }
        return builder.success(user);

    }

    }


