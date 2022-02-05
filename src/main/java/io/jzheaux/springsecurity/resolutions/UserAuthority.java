package io.jzheaux.springsecurity.resolutions;

import javax.persistence.*;
import java.util.UUID;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="authorities")
public class UserAuthority {
    @Id
    private
    UUID id;

    @Column
    private
    String authority;

    @JoinColumn(name="username", referencedColumnName="username")
    @ManyToOne
    private
    User user;

    UserAuthority() {}

    public UserAuthority(User user, String authority) {
        this.setId(UUID.randomUUID());
        this.setUser(user);
        this.setAuthority(authority);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
