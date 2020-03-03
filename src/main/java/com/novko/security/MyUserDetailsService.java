package com.novko.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private JpaUserDao jpaUserDaoImpl;


    @Autowired
    public void setJpaUserDaoImpl(JpaUserDao jpaUserDaoImpl) {
        this.jpaUserDaoImpl = jpaUserDaoImpl;
    }

    //   private JpaUserRepository jpaUserRepository;
//
//    @Autowired
//    public void setJpaUserRepository(JpaUserRepository jpaUserRepository) {
//        this.jpaUserRepository = jpaUserRepository;
//    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<User> user = jpaUserRepository.findByUsername(username);
////        user.orElseThrow( () -> new UsernameNotFoundException("Not found" + username));

        User user = jpaUserDaoImpl.findByUsername(username);
        if(user==null) new UsernameNotFoundException("Not found" + username);

        Roles role = new Roles(user.getRole());
        List<Roles> roles = new ArrayList<>();
        roles.add(role);

        user.setRoles(roles);
        return  user;


//        user.get().setRoles(roles);
//        return user.get();
    }
}
