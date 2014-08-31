package com.mvmlabs.springboot.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "user_authority")
public class UserAuthority implements GrantedAuthority {

    private static final long serialVersionUID = 6184045308025810944L;

    @Id
    @Column(nullable = false)
    private String            authority;

    public UserAuthority() {
    }

    public UserAuthority(final String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(final String authority) {
        this.authority = authority;
    }

    @Override
    public boolean equals(final Object obj) {
        return (obj != null) && (authority != null) && (obj instanceof UserAuthority) && getAuthority().equals(((UserAuthority) obj).getAuthority());
    }
}
