package com.example.Login.service;


import com.example.Login.model.UsersData;
import com.example.Login.repo.UserRepo;
import jakarta.servlet.http.HttpSession;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class LoginService {
    @Autowired
    UserRepo userRepo;
//    @Autowired
//    UsersData user;


//    Register
    public UsersData put(String email, String password, long phone,String username) {
     UsersData chkIf = userRepo.findByEmail(email).orElse(null);
     if(chkIf == null){
         UsersData saveData = new UsersData( email, password,  phone,  username);
        return userRepo.save(saveData);

     }
    return null;
    }
//Login
// Login → return success/failure only
public boolean putInfo(String email, String password, HttpSession session) {
    UsersData chk = userRepo.findByEmail(email).orElse(null);

    if (chk == null) return false;
    session.setAttribute("username",chk.getUsername());
    return  true;

}
    @Transactional(readOnly = true)
    public UsersData getUserInfo(String username) {
        Optional<UsersData> usersData = userRepo.findByUsername(username);
        return usersData.orElse(null);
    }
}
