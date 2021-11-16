package by.beglyakdehterenok.store.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    ADMIN,USER,MANAGER;

    public Set<GrantedAuthority> getAuthorities(){
        return Set.of(Role.values()).stream()
                .map(role -> new SimpleGrantedAuthority(role.name()))
                .collect(Collectors.toSet());
    }
}
