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

    @RequestMapping(value = "/getTrafficPoliceEvent", method = {RequestMethod.POST})
    @ApiOperation(value = "待用", notes = "待用")
    public ResponseData getpoliceEvent(HttpServletRequest request, HttpServletResponse response,@RequestBody TrafficPoliceEventRequestDto policeEventRequestDto) {
        ResponseData responseData = null;
        try {
            if (policeEventRequestDto != null) {
                TrafficPoliceEventVo trafficPoliceEventVo = new TrafficPoliceEventVo();
                List policeEventRequestDtoList = policeService.getTrafficPoliceEventResponseDtoList(policeEventRequestDto);
                trafficPoliceEventVo.setTrafficPoliceEventList(policeEventRequestDtoList);
                responseData = new ResponseData(trafficPoliceEventVo, ResponseCodeEnum.SUCCESS.getCode(), "查询成功");
            } else {
                responseData = new ResponseData(ResponseCodeEnum.FAILURE.getCode(), "参数为空");
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseData = new ResponseData(ResponseCodeEnum.FAILURE.getCode(), e.getMessage());
        }
        return responseData;
    }

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

    //1.2.20	当日值班信息
    @RequestMapping(value = "/getPoliceDuty", method = {RequestMethod.POST})
    @ApiOperation(value="当日值班信息", notes="当日值班信息")
    public ResponseData getPoliceDuty( @RequestBody PoliceDutyRequestDto requestDto) {
        ResponseData responseData = null;
        try {
            if (requestDto != null) {
                PoliceDutyResponseDto responseDto= policeService.getPoliceDuty(requestDto);
                responseData = new ResponseData(responseDto, ResponseCodeEnum.SUCCESS.getCode(), ResponseCodeEnum.SUCCESS.getMsg());
            } else {
                responseData = new ResponseData(ResponseCodeEnum.FAILURE.getCode(), "参数为空");
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseData = new ResponseData(ResponseCodeEnum.FAILURE.getCode(), e.getMessage());
        }
        return responseData;
    }

    //1.2.19	警员、警车、对讲机在线统计
    @RequestMapping(value = "/getRTPoliceOnline", method = {RequestMethod.POST})
    @ApiOperation(value="警员、警车、对讲机在线统计", notes="警员、警车、对讲机在线统计")
    public ResponseData getRTPoliceOnline() {
        ResponseData response = null;
        try {
            RTPoliceOnlineResponseDto responseDto= policeService.getRTPoliceOnline();
            response = new ResponseData(responseDto, ResponseCodeEnum.SUCCESS.getCode(), ResponseCodeEnum.SUCCESS.getMsg());
        } catch (Exception e) {
            e.printStackTrace();
            response = new ResponseData(ResponseCodeEnum.FAILURE.getCode(), e.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "/getAdminJurisdiction", method = {RequestMethod.POST})
    @ApiOperation(value="实时辖区信息", notes="实时辖区信息")
    public ResponseData getAdminJurisdiction(HttpServletRequest request, HttpServletResponse response) {
        ResponseData responseData = null;
        try {
                List adminJurisdictionList = policeService.getAdminJurisdiction();
                responseData = new ResponseData(adminJurisdictionList, ResponseCodeEnum.SUCCESS.getCode(), "查询成功");
        } catch (Exception e) {
            e.printStackTrace();
            responseData = new ResponseData(ResponseCodeEnum.FAILURE.getCode(), e.getMessage());
        }
        return responseData;
    }

}
