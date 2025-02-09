package com.example.demo.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping()
    public List<User> getUsers(){
       return userService.getUsers();
    }

    @DeleteMapping(path = "{userId}")
    public void deleteUser(@PathVariable("userId") Long id){
        userService.deleteUser(id);
    }

    @PostMapping()
    public void AddUser(@RequestBody User user){
        userService.addUser(user);
    }

    @PutMapping(path = "{userId}")
    public void updateUser(@PathVariable("userId") Long id, @RequestParam String name, @RequestParam String password ,@RequestParam Role role){
        userService.updateUser( id, name, password, role);
    }


}
