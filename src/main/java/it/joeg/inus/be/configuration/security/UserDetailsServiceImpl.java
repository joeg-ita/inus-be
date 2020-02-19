package it.joeg.inus.be.configuration.security;

import it.joeg.inus.be.domain.entities.user.User;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;


public class UserDetailsServiceImpl implements UserDetailsService {
    
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       
        User user = mongoTemplate.findById(username, User.class);
        if(user != null){
            UserPrincipal principal = new UserPrincipal();
            principal.setUsername(username);
            List<GrantedAuthority> authorities = user.getRoles().stream().map(role
                    -> new SimpleGrantedAuthority(role)
            ).collect(Collectors.toList());
            principal.setAuthorities(authorities);
            return principal;
        } else {
            return null;
        }
        
    }
    
    
}
