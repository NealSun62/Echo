package cn.sits.rjb.excel.controller;

import cn.sits.rjb.common.data.ResponseData;
import cn.sits.rjb.common.enums.ResponseCodeEnum;
import cn.sits.rjb.excel.service.IExcelService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Neal.Sun
 * @version V1.0
 * @Package cn.sits.rjb.Police.controller
 * @date 2020/02/18 13:41
 */
@RestController
@RequestMapping("/police")
public class ExcelController {
    private static final Logger logger = LoggerFactory.getLogger(ExcelController.class);

    @Resource
    IExcelService policeService;

    @RequestMapping(value = "/getPoliceReporting", method = {RequestMethod.POST})
    @ApiOperation(value="当月事件事故统计", notes="1.2.10和1.2.11当月事件事故统计")
    public ResponseData getPoliceReporting(HttpServletRequest request, HttpServletResponse response) {
        ResponseData responseData = null;
        try {
//            if (requestDto != null) {
//                TrafficPoliceReportingResponseDto responseDto= policeService.getPoliceReporting(requestDto);
//                responseData = new ResponseData(responseDto, ResponseCodeEnum.SUCCESS.getCode(), "查询成功");
//            } else {
//                responseData = new ResponseData(ResponseCodeEnum.FAILURE.getCode(), "参数为空");
//            }
        } catch (Exception e) {
            e.printStackTrace();
            responseData = new ResponseData(ResponseCodeEnum.FAILURE.getCode(), e.getMessage());
        }
        return responseData;
    }

    /*从Excel取数据存库*/
//    @Scheduled(cron = "0/20 * * * * ?") // 间隔1分钟执行
//    public ResponseData getExcel() {
//        ResponseData responseData = null;
//        File file = new File("D:\\EXCEL\\1.xls");
//        Map<String, String> convertMap = new HashMap<String, String>();
//        String filePath = "D:\\EXCEL\\1.xls";
//        try {
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            List<HashMap<String, String>> excelList = new ArrayList<HashMap<String, String>>();
//            Map<String, String> headMap = new LinkedHashMap<String, String>();
//            List<ExcelDto> listExcelDto = new ArrayList<ExcelDto>();
//            headMap.put("号牌号码", "hphm");
//            headMap.put("号牌颜色", "hpys");
////            headMap.put("号牌种类", "hpzl");
//            excelList = ExcelUtils.convertExcelDataToMapData(headMap, filePath);
//            for(HashMap excelMap : excelList){
//                ExcelDto excelDto = new ExcelDto();
//                String hpys = transferColor((String)excelMap.get("hpys"));
//                String hphm = (String)excelMap.get("hphm");
//                String hpzl = WeightUtil.hpzl(hpys,hphm);
//                excelDto.setTruck_no_type(hpzl);
//                excelDto.setTruck_no(hphm);
//                listExcelDto.add(excelDto);
//            }
//            int num = excelMapper.insertExcel(listExcelDto);
//            System.out.println(excelList);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return responseData;
//    }

}
