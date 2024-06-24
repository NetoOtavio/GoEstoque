package com.goestoque.goestoqueservice.users;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity(name = "use_users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "use_id")
    private String id;

    @Column(name = "use_email",
            nullable = false,
            unique = true)
    private String email;

    @Column(name = "use_password",
            nullable = false)
    private String password;

    @Column(name = "use_name",
            nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "use_role",
            nullable = false)
    private UserRole role;

    @Column(name = "use_is_enabled", nullable = false)
    private boolean isEnabled;

    //
    /*@OneToMany(mappedBy = "user")
    private Set<Item> items;

    @OneToMany(mappedBy = "user")
    private Set<Input> inputs;

    @OneToMany(mappedBy = "user")
    private Set<Output> outputs;*/

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
