package io.jzheaux.springsecurity.resolutions;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.stream.Collectors;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserRepositoryUserDetailsService implements UserDetailsService {

    private final UserRepository users;

    public UserRepositoryUserDetailsService(UserRepository users) {
        this.users = users;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return this.users.findByUsername(username)
                .map(BridgedUser::new)
                .orElseThrow(() -> new UsernameNotFoundException("invalid user"));
    }

    private static class BridgedUser extends User implements UserDetails {
        public BridgedUser(User user) {
            super(user);
        }

        public List<GrantedAuthority> getAuthorities() {
            return this.userAuthorities.stream()
                    .map(UserAuthority::getAuthority)
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
        }

        public boolean isAccountNonExpired() {
            return this.isEnabled();
        }

        public boolean isAccountNonLocked() {
            return this.isEnabled();
        }

        public boolean isCredentialsNonExpired() {
            return this.isEnabled();
        }
    }

}
