package group.bridge.web.component;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;

@Component

public class WriteWarnRecord implements Runnable {
    boolean stop = false;
    boolean stoped = false;
    public WriteWarnRecord(){

    }
    public void buildWarnRecord(){

    }

    @Override
    public void run() {
        while(!stop) {
            try {

                Thread.sleep(1000);
                buildWarnRecord();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        stoped=true;
    }
    @PreDestroy
    public void destory(){
        stop = true;
        System.out.println("warn_record is closing...");
        while (stoped){

        }
        System.out.println("warn_record is closed");

    }
}
