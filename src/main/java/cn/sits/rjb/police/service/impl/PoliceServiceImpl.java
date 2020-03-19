package cn.sits.rjb.police.service.impl;

import cn.sits.rjb.common.utils.JsonUtil;
import cn.sits.rjb.common.utils.PageUtil;
import cn.sits.rjb.constants.Constants;
import cn.sits.rjb.police.mapper.PoliceMapper;
import cn.sits.rjb.police.model.dto.*;
import cn.sits.rjb.police.model.po.PoliceDuty;
import cn.sits.rjb.police.model.po.ReportType;
import cn.sits.rjb.police.model.po.TrafficPoliceReporting;
import cn.sits.rjb.police.service.IPoliceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Neal.Sun
 * @version V1.0
 * @Package cn.sits.rjb.police.service.impl
 * @date 2020/02/18 15:12
 */
@Service
@Primary
public class PoliceServiceImpl implements IPoliceService {
    private static final Logger logger = LoggerFactory.getLogger(PoliceServiceImpl.class);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Resource
    PoliceMapper policeMapper;

    @Override
    public List getTrafficPoliceEventResponseDtoList(TrafficPoliceEventRequestDto trafficPoliceEventRequestDto) throws Exception {
        logger.info("..enter to function {},param data:{}", Thread.currentThread().getStackTrace()[1].getMethodName(), JsonUtil.convertObj2String(trafficPoliceEventRequestDto));
        List policePoliceEventResponseDtoList = new ArrayList();
        try {
            policePoliceEventResponseDtoList = policeMapper.getTrafficPoliceEventList(trafficPoliceEventRequestDto);
        } catch (Exception e) {
            logger.error("异常详情:{}", e.getMessage());
            e.printStackTrace();
            throw new Exception(e);
        }
        logger.info("..leave function {},result data:{}", Thread.currentThread().getStackTrace()[1].getMethodName(), JsonUtil.convertObj2String(policePoliceEventResponseDtoList));
        return policePoliceEventResponseDtoList;
    }

    @Override
    public List getAdminJurisdiction() throws Exception {
        List adminJurisdictionList = new ArrayList();
        try {
            adminJurisdictionList = policeMapper.getAdminJurisdiction();
        } catch (Exception e) {
            logger.error("异常详情:{}", e.getMessage());
            e.printStackTrace();
            throw new Exception(e);
        }
        logger.info("..leave function {},result data:{}", Thread.currentThread().getStackTrace()[1].getMethodName(), JsonUtil.convertObj2String(adminJurisdictionList));
        return adminJurisdictionList;
    }

    @Override
    public TrafficPoliceReportingResponseDto getPoliceReporting(TrafficPoliceReportingRequestDto requestDto) throws Exception {
        logger.info("..enter to function {},param data:{}", Thread.currentThread().getStackTrace()[1].getMethodName(), JsonUtil.convertObj2String(requestDto));
        List<TrafficPoliceReportingDto> policeReportingList = new ArrayList<TrafficPoliceReportingDto>();
        TrafficPoliceReportingResponseDto responseDto = new TrafficPoliceReportingResponseDto();
        //分页
        Integer pageNum = Constants.DEFAULT_PAGE_INDEX + 1;
        Integer pageSize = Constants.DEFAULT_PAGE_SIZE;
        if (requestDto.getPageNum() != null && requestDto.getPageNum() > 0) {
            pageNum = requestDto.getPageNum();
        }

        if (requestDto.getPageSize() != null && requestDto.getPageSize() > 0) {
            pageSize = requestDto.getPageSize();
        }
        Integer offset = (pageNum - 1) * pageSize;
        requestDto.setPageNum(pageNum);
        requestDto.setPageSize(pageSize);
        requestDto.setOffset(offset);

        String startTime = requestDto.getStartTime().substring(0, 19);
        String endTime = requestDto.getEndTime().substring(0, 19);
        requestDto.setStartTime(startTime);
        requestDto.setEndTime(endTime);
        try {
            policeReportingList = policeMapper.getPoliceReporting(requestDto);
            responseDto.setTrafficPoliceReportingList(policeReportingList);
            int count = policeReportingList.size();
            int totalPage = PageUtil.getTotalPage(count, pageSize);
            responseDto.setTotalSize(count);
            responseDto.setTotalPage(totalPage);

            //已处置事件数、高速段总数
            int expresswayCount = 0;
            HashSet<String> expresswaySet = new HashSet<>();
            int resolvedReportNumber = 0;
            List<String> reportTypeList = new ArrayList<String>();
            List<String> expresswayList = new ArrayList<String>();
            for (TrafficPoliceReportingDto trafficPoliceReporting : policeReportingList) {
                if (4 == trafficPoliceReporting.getState()) {
                    resolvedReportNumber++;
                }
                expresswaySet.add(String.valueOf(trafficPoliceReporting.getEventExpresswayId()));
                reportTypeList.add(trafficPoliceReporting.getEventContent());
                expresswayList.add(trafficPoliceReporting.getName());
            }
            responseDto.setResolvedReportNumber(resolvedReportNumber);
            //高速类型个数
            Map<String,Integer> expresswayMap = new HashMap<>();
            for(String a : expresswayList){
                //定义一个计数器，用来记录重复数据的个数
                int i = 1;
                //若有该值，计数+1；若无该值，总数为1
                if(expresswayMap.get(a) != null){
                    i = expresswayMap.get(a) + 1;
                }
                expresswayMap.put(a,i);
            }
            responseDto.setExpresswayCount(expresswayMap.size());

            //高速类型分类后的详情列表
            List<ExpresswayDto> expressWayList = new ArrayList<ExpresswayDto>();
            for (Map.Entry<String,Integer> entry : expresswayMap.entrySet()){
                ExpresswayDto reportType = new ExpresswayDto();
                reportType.setCount(entry.getValue());
                reportType.setName(entry.getKey());
                expressWayList.add(reportType);
            }
            responseDto.setExpresswayList(expressWayList);

            //事件类型个数
            Map<String,Integer> map = new HashMap<>();
            for(String a : reportTypeList){
                //定义一个计数器，用来记录重复数据的个数
                int i = 1;
                //若有该值，计数+1；若无该值，总数为1
                if(map.get(a) != null){
                    i = map.get(a) + 1;
                }
                map.put(a,i);
            }
            responseDto.setReportTypeCount(map.size());

            //事件类型分类后的详情列表
            List<ReportType> reportingTypeList = new ArrayList<ReportType>();
            for (Map.Entry<String,Integer> entry : map.entrySet()){
                ReportType reportType = new ReportType();
                reportType.setTypeCount(entry.getValue());
                reportType.setTypeName(entry.getKey());
                reportingTypeList.add(reportType);
            }
            responseDto.setReportTypeList(reportingTypeList);

            //昨日事件总数
            int yesterdayReportNumber = policeMapper.getYesterdayPoliceReportingNumber(requestDto);
            responseDto.setYesterdayReportNumber(yesterdayReportNumber);

        } catch (Exception e) {
            logger.error("异常详情:{}", e.getMessage());
            e.printStackTrace();
            throw new Exception(e);
        }
        logger.info("..leave function {},result data:{}", Thread.currentThread().getStackTrace()[1].getMethodName(), JsonUtil.convertObj2String(responseDto));
        return responseDto;
    }
    /*
        1.2.20	当日值班信息
        */
    @Override
    public PoliceDutyResponseDto getPoliceDuty(PoliceDutyRequestDto requestDto) throws Exception{
        logger.info("..enter to function {},param data:{}", Thread.currentThread().getStackTrace()[1].getMethodName(),JsonUtil.convertObj2String(requestDto));
        PoliceDutyResponseDto responseDto = new PoliceDutyResponseDto();
        List<PoliceDuty> policeDutyList = new ArrayList<PoliceDuty>();
        //分页
        Integer pageNum = Constants.DEFAULT_PAGE_INDEX + 1;
        Integer pageSize = Constants.DEFAULT_PAGE_SIZE;
        if(requestDto.getPageNum() != null && requestDto.getPageNum() > 0) {
            pageNum = requestDto.getPageNum();
        }

        if(requestDto.getPageSize() != null && requestDto.getPageSize() > 0) {
            pageSize = requestDto.getPageSize();
        }
        Integer offset = (pageNum-1)*pageSize;
        requestDto.setPageNum(pageNum);
        requestDto.setPageSize(pageSize);
        requestDto.setOffset(offset);
        try{
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            int dayShiftCount=0;
            int nightShiftCount=0;
            //当天
            if(requestDto.getStartTime()==""||requestDto.getEndTime()==""){
                Date date = new Date();
                requestDto.setStartTime(df.format(date));
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);//设置起时间
                cal.add(Calendar.DATE, 1);
                requestDto.setEndTime(df.format(cal.getTime()));
            }
            policeDutyList = policeMapper.getPoliceDutyList(requestDto);
            responseDto.setPoliceDutyList(policeDutyList);
            int count = policeDutyList.size();
            int totalPage = PageUtil.getTotalPage(count,pageSize);
            responseDto.setTotalSize(count);
            responseDto.setTotalPage(totalPage);
            for(PoliceDuty pd:policeDutyList){
                if (pd.getDutyType()==1){
                    dayShiftCount++;
                }else{
                    nightShiftCount++;
                }
            }
            responseDto.setDayShiftCount(dayShiftCount);
            responseDto.setNightShiftCount(nightShiftCount);
        }catch (Exception e){
            logger.error("异常详情:{}", e.getMessage());
            e.printStackTrace();
            throw new Exception(e);
        }
        logger.info("..leave function {},result data:{}", Thread.currentThread().getStackTrace()[1].getMethodName());
        return responseDto;
    }

    //1.2.19	警员、警车、对讲机在线统计
    @Override
    public RTPoliceOnlineResponseDto getRTPoliceOnline() throws Exception {
        logger.info("..enter to function {},param data:{}", Thread.currentThread().getStackTrace()[1].getMethodName(), "");
        RTPoliceOnlineResponseDto responseDto = new RTPoliceOnlineResponseDto();
        try {
            responseDto = policeMapper.getRTPoliceOnline();
        } catch (Exception e) {
            logger.error("异常详情:{}", e.getMessage());
            e.printStackTrace();
            throw new Exception(e);
        }
        logger.info("..leave function {},result data:{}", Thread.currentThread().getStackTrace()[1].getMethodName(), JsonUtil.convertObj2String(responseDto));
        return responseDto;
    }
}
