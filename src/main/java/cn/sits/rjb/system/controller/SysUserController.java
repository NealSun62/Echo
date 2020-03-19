package cn.sits.rjb.system.controller;

import cn.sits.rjb.common.data.ResponseData;
import cn.sits.rjb.common.enums.ResponseCodeEnum;
import cn.sits.rjb.system.model.po.SysUser;
import cn.sits.rjb.system.service.ISysUserService;
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
@RequestMapping("/SysUser")
public class SysUserController {
    private static final Logger logger = LoggerFactory.getLogger(SysUserController.class);

    @Autowired
    ISysUserService iSysUserService;


    /**
     * 登录
     * @return BaseResponse
     */
    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    public ResponseData login(@RequestBody SysUser sysUser){
        ResponseData response = null;
        try {
            if (sysUser != null) {
                response = iSysUserService.login(sysUser.getUserCode(), sysUser.getPassWord());
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
