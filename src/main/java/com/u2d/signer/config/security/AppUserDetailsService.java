package com.u2d.signer.config.security;

import com.u2d.signer.entity.User;
import com.u2d.signer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AppUserDetailsService implements UserDetailsService {

    private UserRepository repository;

    @Autowired
    public AppUserDetailsService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String cpfCnpj) throws UsernameNotFoundException {
        Optional<User> optionalUser = repository.findByCpfCnpj(cpfCnpj);
        User user = optionalUser.orElseThrow(() -> new UsernameNotFoundException("Usuário e/ou senha incorretos"));
        return new org.springframework.security.core.userdetails.User(cpfCnpj, user.getPassword(), getPermissions(user));
    }

    private Collection<? extends GrantedAuthority> getPermissions(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getPermissions().forEach(permissao -> authorities.add(new SimpleGrantedAuthority(permissao.getDescription().toUpperCase())));
        return authorities;
    }
}
