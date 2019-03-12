package group.bridge.web.entity;

import javax.persistence.*;
import java.util.*;

/**
 * @author wuran
 * @Created on 2019/3/5
 */
@Entity(name = "frame_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;
    @Column(name = "user_name")
    String username;
    @Column(name = "password")
    String password;
    @Column(name = "salt")
    String salt;
    @Column(name = "admin")
    Boolean admin;

    @ManyToMany
    @JoinTable(name = "frame_role_user_mapping",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    Set<Role> roles = new HashSet<>(0);


    public User(String username, String password, boolean admin) {
        setUsername(username);
        setPassword(password);
        setAdmin(admin);
    }

    public User(String username, String password) {
        this(username, password, false);
    }

    public User() {

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

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    public void addRole(Role role){
        this.roles.add(role);
    }
    public void addRoles(Collection<Role> roles){
        this.roles.addAll(roles);
    }
}
