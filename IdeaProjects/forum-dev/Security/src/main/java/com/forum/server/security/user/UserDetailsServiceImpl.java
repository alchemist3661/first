package com.forum.server.security.user;

import com.forum.server.dao.interfaces.TokensDao;
import com.forum.server.dao.interfaces.UsersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * Created by Arthur on 18.2.16.
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UsersDao usersDao;
    @Autowired
    private TokensDao tokensDao;

    @Override
    public UserDetails loadUserByUsername(String token) throws UsernameNotFoundException {
        if (tokensDao.isExistsToken(token)){
            return new UserDetailsImpl(usersDao.findByToken(token));
        }else{
            return null;
        }
    }
}
