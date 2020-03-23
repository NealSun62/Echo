package cn.sits.rjb.excel.controller;

import cn.sits.rjb.common.data.ResponseData;
import cn.sits.rjb.common.enums.ResponseCodeEnum;
import cn.sits.rjb.common.utils.ExcelUtils;
import cn.sits.rjb.excel.mapper.ExcelMapper;
import cn.sits.rjb.excel.model.po.CandidateExcelInfo;
import cn.sits.rjb.excel.service.IExcelService;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
public class UploadController {
    private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

    @Autowired
    IExcelService iExcelService;

    @Resource
    ExcelMapper excelMapper;

    @RequestMapping(value = "/importCandidateListExcel", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData importCandidateListExcel(MultipartFile file) {
        List<List<Object>> candidateListExcel = null;
        ResponseData responseData = null;
        try {
            candidateListExcel = ExcelUtils.importExcel(file, 0,0);
            if(candidateListExcel.size()>0){
                int candidateListNumber = iExcelService.importCandidateListExcel(candidateListExcel);
                responseData =  new ResponseData("导入"+candidateListNumber+"条数据",ResponseCodeEnum.SUCCESS.getCode(), "候选人信息导入成功");
            }else {
                return new ResponseData(ResponseCodeEnum.COMMON_ERROR_100.getCode(), "候选人信息导入失败。数据可能为空或格式异常");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseData;
    }

    /**
     * 导出候选人列表Excel
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/uploadCandidateListExcel", method = RequestMethod.GET)
    @ApiOperation(value = "候选人信息的导入", notes = "根据条件进行候选人信息的导入")
    public ResponseData downloadCarsListExcel(HttpServletRequest request,
                                              HttpServletResponse response, String excelPath) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            String target = ExcelUtils.importExcel(excelPath);
            JSONArray json = JSONArray.fromObject(target);//userStr是json字符串
            List<CandidateExcelInfo> users = (List<CandidateExcelInfo>) JSONArray.toCollection(json, CandidateExcelInfo.class);
            logger.info("文件下载成功");
        } catch (Exception e) {
            logger.error("downloadCarsListExcel ", e);
            return new ResponseData(ResponseCodeEnum.COMMON_ERROR_100.getCode(), "候选人信息导出失败");
        }

        return new ResponseData(ResponseCodeEnum.SUCCESS.getCode(), ResponseCodeEnum.SUCCESS.getMsg());
    }


}
