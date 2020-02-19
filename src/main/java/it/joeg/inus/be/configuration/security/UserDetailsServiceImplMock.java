package it.joeg.inus.be.configuration.security;

import it.joeg.inus.be.dataproviders.mocked.InMemoryStore;
import it.joeg.inus.be.domain.entities.user.User;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;


public class UserDetailsServiceImplMock implements UserDetailsService {
    
    InMemoryStore inMemoryStore = InMemoryStore.getInstance();

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(!inMemoryStore.getUsers().containsKey(username)){
            throw new UsernameNotFoundException("Username "+username+ " NOT FOUND!");
        }
        User user = inMemoryStore.getUsers().get(username);
        UserPrincipal principal = new UserPrincipal();
        principal.setUsername(username);
        List<GrantedAuthority> authorities = user.getRoles().stream().map(role
                -> new SimpleGrantedAuthority(role)
        ).collect(Collectors.toList());
        principal.setAuthorities(authorities);
        return principal;
    }
    
    
}
