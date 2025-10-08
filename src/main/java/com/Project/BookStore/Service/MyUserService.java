package com.Project.BookStore.Service;

import com.Project.BookStore.Model.UserPrincipal;
import com.Project.BookStore.Model.Users;
import com.Project.BookStore.Repository.UsersRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserService implements UserDetailsService {

    private UsersRepo repo;
    public MyUserService(UsersRepo repo){
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Users user = repo.findByUsername(username);
       if(user==null)
           throw new UsernameNotFoundException("User not found");
       else
           return new UserPrincipal(user);
    }
}
