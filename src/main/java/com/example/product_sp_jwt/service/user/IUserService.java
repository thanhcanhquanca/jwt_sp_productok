package com.example.product_sp_jwt.service.user;

import com.example.product_sp_jwt.model.User;
import com.example.product_sp_jwt.service.IGenerateService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface IUserService extends IGenerateService<User> {
    User findByUsername(String name);
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
