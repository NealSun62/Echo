package cn.sits.rjb.excel.controller;

import cn.sits.rjb.common.aop.AccessLimit;
import cn.sits.rjb.common.data.ResponseData;
import cn.sits.rjb.common.enums.ResponseCodeEnum;
import cn.sits.rjb.common.utils.ExcelUtils;
import cn.sits.rjb.excel.mapper.ExcelMapper;
import cn.sits.rjb.excel.model.dto.DownloadCandidateInfoByKeywordResponseDto;
import cn.sits.rjb.excel.model.dto.DownloadCandidateListExcelRequestDto;
import cn.sits.rjb.excel.service.IExcelService;
import com.alibaba.druid.util.StringUtils;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class DownloadController {
    private static final Logger logger = LoggerFactory.getLogger(DownloadController.class);

    @Autowired
    IExcelService iExcelService;

    @Resource
    ExcelMapper excelMapper;


    /**
     * 导出候选人列表Excel
     */
    @SuppressWarnings("rawtypes")
    @AccessLimit(limit = 4,sec = 10)//10秒内4次
    @RequestMapping(value = "/downloadCandidateListExcel", method = RequestMethod.GET)
    @ApiOperation(value = "候选人信息的导出", notes = "根据条件进行候选人信息的导出")
    public ResponseData downloadCarsListExcel(HttpServletRequest request,
                                              HttpServletResponse response, String keyword
    ) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<List<String>> dataList = new ArrayList<List<String>>();

        try {
            String sheetName = "候选人信息";
            List<String> heads = new ArrayList<String>();
            heads.add("候选人姓名");
            heads.add("联系方式");
            heads.add("情况");
            heads.add("更新时间");
            heads.add("先公司");
            heads.add("前公司");
            DownloadCandidateListExcelRequestDto requestDto = new DownloadCandidateListExcelRequestDto();
            if (StringUtils.isEmpty(keyword)) {
                requestDto.setKeyword("");
            } else {
                requestDto.setKeyword(keyword.trim());
            }
            // excel要填充的数据
            List<DownloadCandidateInfoByKeywordResponseDto> candidateList = new ArrayList<DownloadCandidateInfoByKeywordResponseDto>();
            DownloadCandidateInfoByKeywordResponseDto responseDto = new DownloadCandidateInfoByKeywordResponseDto();
            candidateList = excelMapper.downloadCandidateListExcel(requestDto);
            for (DownloadCandidateInfoByKeywordResponseDto candidate : candidateList) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                List<String> rowData = new ArrayList<String>();
                rowData.add(candidate.getCandidateName());
                rowData.add(candidate.getTelephone());
                rowData.add(candidate.getDescription());
                rowData.add(sdf.format(candidate.getUpdateTime()));
                rowData.add(candidate.getCompanyNow());
                rowData.add(candidate.getCompanyBefore());
                dataList.add(rowData);
            }
            Workbook createExcelFile = ExcelUtils.createExcelFile(sheetName, heads, dataList);

            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String datestamp = sdf.format(date);

            String fileName = "候选人信息导出" + datestamp + ".xlsx";
            OutputStream os = null;

            response.reset();
            response.setHeader("Content-Disposition", "attachment; filename=" + new String(fileName.getBytes("gb2312"), "ISO8859-1"));
            response.setHeader("filename", new String(fileName.getBytes("gb2312"), "ISO8859-1"));
            response.setContentType("application/ms-excel; charset=utf-8");

            os = response.getOutputStream();
            createExcelFile.write(os);
            os.flush();
            os.close();
            createExcelFile = null;
            logger.info("文件下载成功");
        } catch (Exception e) {
            logger.error("downloadCarsListExcel ", e);
            return new ResponseData(ResponseCodeEnum.COMMON_ERROR_100.getCode(), "候选人信息导出失败");
        }

        return new ResponseData(ResponseCodeEnum.SUCCESS.getCode(), ResponseCodeEnum.SUCCESS.getMsg());
    }


}
