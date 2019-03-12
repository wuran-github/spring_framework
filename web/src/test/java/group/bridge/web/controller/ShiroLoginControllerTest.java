package group.bridge.web.controller;

import group.bridge.web.WebApplication;
import group.bridge.web.util.ShiroUtil;
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
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ShiroLoginControllerTest {

    private URL base;
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;
    @Before
    public void setUp() throws MalformedURLException {
        String url = "http://localhost:"+port+"/shiro/";
        this.base = new URL(url);
//        System.out.println(ShiroUtil.parseHash("admin","admin"));
    }
    @Test
    public void userLogin() {
        Map<String,String> variables = new HashMap<>();
        variables.put("name","user");
        variables.put("password","user");
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(
                base.toString()+"/login?name={name}&password={password}&admin=false",null,String.class,variables);
//        System.out.println(responseEntity.getBody());
        responseEntity.getBody();

    }
    @Test
    public void adminLogin(){
        Map<String,String> variables = new HashMap<>();
        variables.put("name","admin");
        variables.put("password","admin");
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(
                base.toString()+"/login?name={name}&password={password}&admin=true",null,String.class,variables);
//        System.out.println(responseEntity.getBody());
    }
}