package com.example.demo.User;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> getUsers(){
     return userRepository.findAll();
    }

    public void deleteUser(Long id ){
        boolean userExist = userRepository.existsById(id);
        if(!userExist){
            throw new IllegalStateException("User with " + id + "Does not exist");
        }

        userRepository.deleteById(id);
    }

    public void addUser(User user){
        Optional<User> user1 = userRepository.findByName(user.getName());
        if(user1.isPresent()){
            throw new IllegalStateException("User with username : "+ user.getName() + "already exists");
        }
        userRepository.save(user);
    }

    @Transactional
    public void updateUser(Long id, String name,String password, Role role ){
        User user1 = userRepository.findById(id).orElseThrow(() -> new IllegalStateException("User with id : "+ id + "doesnt  exists"));
        if(name.length()> 0){
            user1.setName(name);
        }

        if(role.toString() == "USER"|| role.toString() == "ADMIN"){
            user1.setRole(role);
        }

        if(password.length()>0){
            user1.setPassword(password);
        }


    }

}
