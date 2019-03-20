package group.bridge.web.configurer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 日志配置类
 * @author wuran
 * @Created on 2019/3/20
 */
@Configuration
public class LogConfig {

    private static Logger LOG = LoggerFactory.getLogger(LogConfig.class);

    @Bean
    public String logTest(){
        LOG.info("=====test log=====");
        return "abc";
    }
}
