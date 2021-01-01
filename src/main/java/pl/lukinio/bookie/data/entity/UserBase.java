package pl.lukinio.bookie.data.entity;

import com.vaadin.flow.component.Component;
import org.javatuples.Pair;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="role", discriminatorType = DiscriminatorType.STRING)
@Table(name = "users")
public abstract class UserBase implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    @Column protected String username;
    @Column protected String password;
    @Column protected String email;
    @Column(name = "role", insertable = false, updatable = false)
    protected String role;

    UserBase(String username, String password, String email, String role){
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }
    UserBase(){
        /* default constructor for JPA */
    }

    public abstract void setAvailableRoutes();
    public abstract List<Pair<String,  Class<? extends Component>>> getRoutes();
    public abstract boolean isNull();

    public boolean checkPassword(String password){
        return this.password.equals(password);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        UserBase user = (UserBase) o;
        return Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), username, password, role);
    }

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
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

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return String.format("User[username='%s', email='%s']", username, email);
    }
}
