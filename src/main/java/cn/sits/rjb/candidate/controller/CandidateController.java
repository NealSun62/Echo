package cn.sits.rjb.candidate.controller;

import cn.sits.rjb.candidate.model.dto.GetCandidateInfoByKeywordRequestDto;
import cn.sits.rjb.candidate.model.vo.GetCandidateInfoByKeywordVo;
import cn.sits.rjb.candidate.service.ICandidateInfoService;
import cn.sits.rjb.common.data.ResponseData;
import cn.sits.rjb.common.enums.ResponseCodeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

//    获取候选人信息
    @RequestMapping(value = "/getCandidateInfoByKeyword", method = {RequestMethod.POST})
    public ResponseData getCandidateInfoByKeyword(@RequestBody GetCandidateInfoByKeywordRequestDto requestDto){
        ResponseData response = null;
        try {
            if (requestDto != null) {
                GetCandidateInfoByKeywordVo getCandidateInfoByKeywordVo = iCandidateInfoService.getCandidateInfoByKeyword(requestDto);
                return new ResponseData(getCandidateInfoByKeywordVo,ResponseCodeEnum.SUCCESS.getCode(),ResponseCodeEnum.SUCCESS.getMsg());
            } else {
                logger.error("操作失败！传值为空");
                response = new ResponseData(ResponseCodeEnum.FAILURE.getCode(),"传值为空");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response = new ResponseData(ResponseCodeEnum.FAILURE.getCode(),e.getMessage());
        }
        return response;
    }


}
