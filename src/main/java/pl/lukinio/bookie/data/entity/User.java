package pl.lukinio.bookie.data.entity;

import pl.lukinio.bookie.data.AbstractEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User extends AbstractEntity implements Serializable {
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    public static final class Builder {
        private String username;
        private String password;
        private String email;
        private Role role;

        public Builder username(String username){
            this.username = username;
            return this;
        }
        public Builder password(String password){
            this.password = password;
            return this;
        }
        public Builder email(String email){
            this.email = email;
            return this;
        }
        public Builder role(Role role){
            this.role = role;
            return this;
        }
        public User build(){
            User user = new User();
            user.username = this.username;
            user.password = this.password;
            user.email = this.email;
            user.role = this.role;
            return user;
        }
    }
    public User(){
        /* default constructor for Spring Data JPA*/
    }

    public boolean checkPassword(String password){
        return this.password.equals(password);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), username, password, role);
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return String.format("User[username='%s', email='%s']", username, email);
    }
}
