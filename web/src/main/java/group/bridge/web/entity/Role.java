package group.bridge.web.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.*;

/**
 * @author wuran
 * @Created on 2019/3/11
 */
@Entity(name = "frame_role")
public class Role {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(name = "name")
    String name;
    @Column(name = "value")
    String value;
    @ManyToMany
    @JoinTable(name = "frame_permission_role_mapping",
            inverseJoinColumns = {@JoinColumn(name = "permission_id", referencedColumnName = "id")},
            joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    @JsonManagedReference
    Set<Permission> permissions = new HashSet<>(0);
    @ManyToMany
    @JoinTable(name = "frame_role_user_mapping",
            joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")},
    inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")})
    @JsonBackReference
    Set<User> users = new HashSet<>(0);

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }
    public void addPermission(Permission permission){
        this.permissions.add(permission);
    }
    public void addPermissions(Collection<Permission> permissions){
        this.permissions.addAll(permissions);
    }
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
    public void addUser(User user){
        this.users.add(user);
    }
    public void addUsers(Collection<User> users){
        this.users.addAll(users);
    }
}
