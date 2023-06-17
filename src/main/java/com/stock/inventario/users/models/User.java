package com.stock.inventario.users.models;

import com.stock.inventario.permissions.models.Permission;
import com.stock.inventario.roles.models.Role;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Document("users")
public class User implements UserDetails {
    @Id
    private String id;
    private String name;
    private String lastName;
    //private String document;
    private String phone;
    private String email;
    private String password;
    private String username;
    private String photoUrl;

    @DBRef
    private List<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Permission> permissions = new HashSet<>();
        for(Role role: roles){
            permissions.addAll( role.getPermissions() );
        }

        return permissions.stream()
                .map(permission -> new SimpleGrantedAuthority( permission.getName() ))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }
    @Override
    public boolean isAccountNonLocked() {
        return false;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
}
