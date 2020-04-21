package cn.sits.rjb.candidate.controller;

import cn.sits.rjb.candidate.model.dto.GetCandidateInfoByKeywordRequestDto;
import cn.sits.rjb.candidate.model.vo.GetCandidateInfoByKeywordVo;
import cn.sits.rjb.candidate.service.ICandidateInfoService;
import cn.sits.rjb.common.data.ResponseData;
import cn.sits.rjb.common.enums.ResponseCodeEnum;
import cn.sits.rjb.common.utils.TokenUtil;
import cn.sits.rjb.system.service.IAuthDataService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yuhongjie
 * @version 1.0
 * @date 2019-11-12 9:14
 */
@RestController
@RequestMapping("/Candidate")
public class CandidateController {
    private static final Logger logger = LoggerFactory.getLogger(CandidateController.class);

    @Autowired
    ICandidateInfoService iCandidateInfoService;

    @Autowired
    IAuthDataService iAuthDataService;

    //    获取候选人信息
//    @AccessLimit(limit = 1, sec = 10)//10秒内4次
    @RequestMapping(value = "/getCandidateInfoByKeyword", method = {RequestMethod.POST})
    @ApiOperation(value = "关键字查候选人信息", notes = "关键字查候选人信息")
    public ResponseData getCandidateInfoByKeyword(HttpServletRequest request, @RequestBody GetCandidateInfoByKeywordRequestDto requestDto) {
        ResponseData responseData = null;
        // 设置当前登录人ID
        try {
            Long userId = TokenUtil.getUserId(request);
            requestDto.setUserId(userId);
            // 校验操作权限
            String[] sourceArr = {"CAND_SHW"};
            responseData = iAuthDataService.checkOperation(request, requestDto.getOpt(), sourceArr);
            if (!responseData.getResult().equals(ResponseCodeEnum.SUCCESS.getCode())) {
                return responseData;
            }
            GetCandidateInfoByKeywordVo getCandidateInfoByKeywordVo = iCandidateInfoService.getCandidateInfoByKeyword(requestDto);
            return new ResponseData(getCandidateInfoByKeywordVo, ResponseCodeEnum.SUCCESS.getCode(), ResponseCodeEnum.SUCCESS.getMsg());
        } catch (Exception e) {
            e.printStackTrace();
             responseData = new ResponseData(ResponseCodeEnum.FAILURE.getCode(), e.getMessage());
        }
        return responseData;
    }
}



