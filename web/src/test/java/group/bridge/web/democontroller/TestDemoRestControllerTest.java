package group.bridge.web.democontroller;

import group.bridge.web.WebApplication;
import group.bridge.web.entity.Permission;
import group.bridge.web.entity.Role;
import group.bridge.web.service.PermissionService;
import group.bridge.web.service.RoleService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestDemoRestControllerTest {
    private URL base;
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private RoleService roleService;

    @Before
    public void setUp() throws MalformedURLException {
        String url = "http://localhost:"+port+"/test/api/";
        this.base = new URL(url);
//        System.out.println(ShiroUtil.parseHash("admin","admin"));
    }
    @Test
    public void addPermission() {
        Permission permission = new Permission();
        permission.setName("p1");
        permission.setValue("v1");

        ResponseEntity<String> result = restTemplate.postForEntity(
                base.toString()+"/permission/add",
                permission,
                String.class);
        System.out.println(result.getBody());
    }

    @Test
    public void updatePermission() {

        ResponseEntity<String> result = restTemplate.postForEntity(
                base.toString()+"/permission/update",null,String.class);
        System.out.println(result.getBody());

    }
    @Test
    public void updateRole(){
        ResponseEntity<String> result = restTemplate.postForEntity(
                base.toString()+"/role/update",null,String.class);
        System.out.println(result.getBody());
    }

    @Test
    public void getPermission(){
        ResponseEntity<String> result = restTemplate.getForEntity(
                base.toString()+"/permission/get",String.class);
        System.out.println(result.getBody());
    }
    @Test
    public void getRole(){
        ResponseEntity<String> result = restTemplate.getForEntity(
                base.toString()+"/role/get",String.class);
        System.out.println(result.getBody());
    }

    @Test
    public void addUser(){
        ResponseEntity<String> result = restTemplate.getForEntity(
                base.toString()+"/user/add",String.class);
        System.out.println(result.getBody());
    }
    @Test
    public void updateUser(){
        ResponseEntity<String> result = restTemplate.getForEntity(
                base.toString()+"/user/update",String.class);
        System.out.println(result.getBody());
    }
    @Test
    public void getUser(){
        ResponseEntity<String> result = restTemplate.getForEntity(
                base.toString()+"/user/get",String.class);
        System.out.println(result.getBody());
    }
}