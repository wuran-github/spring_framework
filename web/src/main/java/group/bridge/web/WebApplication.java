package group.bridge.web;


import group.bridge.web.component.SpringUtil;
import group.bridge.web.component.WriteWarnRecord;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebApplication  {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
        WriteWarnRecord writeWarnRecord = SpringUtil.getBean(WriteWarnRecord.class);
//        Thread thread = new Thread(writeWarnRecord);
//        thread.start();

    }

}

