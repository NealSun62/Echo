package cn.sits.rjb.police.controller;

import cn.sits.rjb.common.data.ResponseData;
import cn.sits.rjb.common.enums.ResponseCodeEnum;
import cn.sits.rjb.police.model.dto.*;
import cn.sits.rjb.police.model.vo.TrafficPoliceEventVo;
import cn.sits.rjb.police.service.IPoliceService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Neal.Sun
 * @version V1.0
 * @Package cn.sits.rjb.Police.controller
 * @date 2020/02/18 13:41
 */
@RestController
@RequestMapping("/police")
public class PoliceController {
    private static final Logger logger = LoggerFactory.getLogger(PoliceController.class);

    @Resource
    IPoliceService policeService;

    @RequestMapping(value = "/getPoliceReporting", method = {RequestMethod.POST})
    @ApiOperation(value="当月事件事故统计", notes="1.2.10和1.2.11当月事件事故统计")
    public ResponseData getPoliceReporting(HttpServletRequest request, HttpServletResponse response, @RequestBody TrafficPoliceReportingRequestDto requestDto) {
        ResponseData responseData = null;
        try {
            if (requestDto != null) {
                TrafficPoliceReportingResponseDto responseDto= policeService.getPoliceReporting(requestDto);
                responseData = new ResponseData(responseDto, ResponseCodeEnum.SUCCESS.getCode(), "查询成功");
            } else {
                responseData = new ResponseData(ResponseCodeEnum.FAILURE.getCode(), "参数为空");
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseData = new ResponseData(ResponseCodeEnum.FAILURE.getCode(), e.getMessage());
        }
        return responseData;
    }


}
