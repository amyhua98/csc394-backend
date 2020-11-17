package com.csc394.capStoneProject.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.csc394.capStoneProject.dto.UserDTO;

public class UserPrincipal implements UserDetails {

    private static final long serialVersionUID = 1L;

   
    private Long id;
    private String userName;
    private String password;
    

    private Collection<? extends GrantedAuthority> authorities;

    /**
     * @param nom
     * @param prenom
     * @param cuId
     * @param role
     * @param authorities
     */
    private UserPrincipal(String password,String userName, Long id, Collection<? extends GrantedAuthority> authorities) {
        super();
        this.id = id;
        this.userName = userName;
        this.authorities = authorities;
        this.password = password;
    }

    public static UserPrincipal create(UserDTO user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
//        if (user != null && user.getPermissions() != null) {
//            for (String permission : user.getPermissions()) {
//                authorities.add(new SimpleGrantedAuthority(permission));
//            }
//        }

        return new UserPrincipal(user.getPassword(), user.getUserName(), user.getId(), authorities);
    }


    /**
     * @param cuId the cuId to set
     */
    public void setId(Long cuId) {
        this.id = cuId;
    }
    
    public Long getId(){
    	return this.id;
    }

    

    /**
     * @param authorities the authorities to set
     */
    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        // the password is mocked (authentication only by role, user had already
        // authenticated by GASSI,
        // Gassi redirect to nevada by role in header)
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    @Override
    public String getUsername() {
        return userName;
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
        return true;
    }
}
