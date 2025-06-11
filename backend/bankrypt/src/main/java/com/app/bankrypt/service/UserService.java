package com.app.bankrypt.service;

import com.app.bankrypt.dto.UserCreationDTO;
import com.app.bankrypt.enums.UserRoles;
import com.app.bankrypt.model.Users;
import com.app.bankrypt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;
    /*
     * creates (POST):
     * user accounts done
     * admin accounts done
     * data accounts done

     * updates (UPDATE/PUT):
     * passwords
     * emails
     * address

     * deletes (DELETE):
     * account

     * retrieves (GET):
     * passwords ?
     * id
     */

    // post
    // if email has @bankrypt.com / @bankryptdata.com it is an admin, auto put admin/data
    public Users createUser(UserCreationDTO userInfo){
        String adminDomain = "bankrypt.com";
        String dataDomain = "bankryptdata.com";

        String userEnteredEmail = userInfo.getEmail();

        if(userEnteredEmail == null || !userEnteredEmail.contains("@")){
            throw new IllegalArgumentException("Invalid email address");
        }

        String userEmailDomain = userEnteredEmail.substring(userEnteredEmail.indexOf("@")+ 1).toLowerCase();

        String userPassword = userInfo.getPassword();
        String hashedPassword = passwordEncoder.encode(userPassword);

        Users newUser = new Users();

        newUser.setEmail(userInfo.getEmail());
        newUser.setFirstName(userInfo.getFirstName());
        newUser.setLastName(userInfo.getLastName());
        newUser.setUsername(userInfo.getUsername());
        newUser.setPassword(hashedPassword);
        newUser.setContactNumber(userInfo.getContactNumber());
        newUser.setUserRole(UserRoles.CLIENT);

        if(userInfo.getContactNumber() == null) {
            newUser.setContactNumber(null);
        }

        if(userEmailDomain.equals(adminDomain)){
            newUser.setUserRole(UserRoles.ADMIN);
        } else if (userEmailDomain.equals(dataDomain)) {
            newUser.setUserRole(UserRoles.DATA);
        }

        return userRepo.save(newUser);
    }

    public List<Users> findAllUsers(){
        return userRepo.findAll();
    }

    public Optional<Users> findUserById(Long id){
        if(id == null){
            throw new IllegalArgumentException("No id entered");
        }
        return userRepo.findById(id);
    }
}
