package com.example.demo.user;

// import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepositry userRepositry;

    public List<User> getAllUsers(){
        return userRepositry.findAll();
    }

    public User getOneUser(int id){
        var user = userRepositry.findById(id).orElse(null);
        return user;
    }


    public List<User> getAllPatients(){
        return userRepositry.findByStatus("Pending");
    }

    // public List<User> getUsersWithStatusUser() {
    //     List<User> allUsers = userRepositry.findAll();
    //     List<User> usersWithStatusUser = new ArrayList<>();
        
    //     System.err.println(allUsers.getFirst().getStatus());
    //     for (User user : allUsers) {
    //         if ("USER".equals(user.getStatus().trim().toString())) {
    //             usersWithStatusUser.add(user);
    //         }
    //     }
        
    //     return usersWithStatusUser;
    // }



    // public List<User> getAllPatients(){
    //     List<User> patients = new ArrayList<>() {
            
    //     };
    //    List<User> AllUsers = userRepositry.findAll();
    //      for (User user : AllUsers) {
    //         if(user.getStatus().toString().equals("USER"))
    //         {
    //                patients.add(user);
    //         }
    //     }
    //         return patients;
            
    //      }
      }

    

