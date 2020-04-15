package cn.sits.rjb.tool;

import cn.sits.rjb.common.data.ResponseData;
import cn.sits.rjb.common.enums.ResponseCodeEnum;
import cn.sits.rjb.config.rabbitmq.RabbitConfig;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class RabbitController {
    private static final Logger logger = LoggerFactory.getLogger(RabbitController.class);
    @Autowired
    RabbitTemplate rabbitTemplate;

    @RequestMapping(value = "/sendRabbitmq", method = RequestMethod.GET)
    @ApiOperation(value = "候选人信息的导出", notes = "根据条件进行候选人信息的导出")
    public ResponseData sendRabbitmq(HttpServletRequest request, HttpServletResponse response) {
        try {
            User user = new User();
            user.setPassword("1111");
            user.setUsername("ly");
            rabbitTemplate.convertAndSend(RabbitConfig.EXCHANG, RabbitConfig.KEY, user);
//            sender.send();
        } catch (Exception e) {
            logger.error("downloadCarsListExcel ", e);
            return new ResponseData(ResponseCodeEnum.COMMON_ERROR_100.getCode(), "候选人信息导出失败");
        }

        return new ResponseData(ResponseCodeEnum.SUCCESS.getCode(), ResponseCodeEnum.SUCCESS.getMsg());
    }


}
