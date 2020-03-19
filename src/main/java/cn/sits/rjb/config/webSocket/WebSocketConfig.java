package cn.sits.rjb.config.webSocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author wangjunwei
 * @version 1.0
 * @date 2019-12-13 9:05
 */
@Configuration
public class WebSocketConfig {

    /**
     * 使用@SpringBootApplication启动类进行启动时需要下面这段代码
     * 使用 外部tomcat启动时需要注释以下代码
     * @return
     */
//    @Bean
//    public ServerEndpointExporter serverEndpointExporter() {
//        return new ServerEndpointExporter();
//    }
}
