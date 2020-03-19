package cn.sits.rjb.candidate.service.impl;

import cn.sits.rjb.candidate.mapper.CandidateInfoMapper;
import cn.sits.rjb.candidate.model.dto.GetCandidateInfoByKeywordRequestDto;
import cn.sits.rjb.candidate.model.dto.GetCandidateInfoByKeywordResponseDto;
import cn.sits.rjb.candidate.model.vo.GetCandidateInfoByKeywordVo;
import cn.sits.rjb.candidate.service.ICandidateInfoService;
import cn.sits.rjb.common.utils.PageUtil;
import cn.sits.rjb.constants.Constants;
import jodd.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Neal.Sun
 * @version V1.0
 * @Package cn.sits.rjb.police.service.impl
 * @date 2020/02/18 15:12
 */
@Service
@Primary
public class CandidateInfoServiceImpl implements ICandidateInfoService {
    private static final Logger logger = LoggerFactory.getLogger(CandidateInfoServiceImpl.class);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Resource
    CandidateInfoMapper candidateInfoMapper;

    @Override
    public GetCandidateInfoByKeywordVo getCandidateInfoByKeyword(GetCandidateInfoByKeywordRequestDto requestDto) throws Exception{
        GetCandidateInfoByKeywordVo getCandidateInfoByKeywordVo = new GetCandidateInfoByKeywordVo();
        logger.info("getCandidateInfoByKeyword is Begin");
        GetCandidateInfoByKeywordResponseDto getCandidateInfoByKeywordResponseDto = new GetCandidateInfoByKeywordResponseDto();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(StringUtil.isEmpty(requestDto.getKeyword())) {
            requestDto.setKeyword("");
        }else{
            requestDto.setKeyword(requestDto.getKeyword().trim());
        }
        //默认没传参数的话，只查七天前的数据
//        if(requestDto.getEndTime() == null &&
//                requestDto.getStartTime() == null &&
//                StringUtil.isEmpty(requestDto.getKeyword()) &&
//                requestDto.getOverRate() == null){
//            Calendar can = Calendar.getInstance();
//            can.add(Calendar.DATE, -7);
//            Calendar can2 = Calendar.getInstance();
//            can2.add(Calendar.DATE, +1);
//            Date date = can.getTime();
//            Date date2 = can2.getTime();
//            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//            Timestamp ts = new Timestamp(date.getTime());
//            Timestamp ts2 = new Timestamp(date2.getTime());
//            requestDto.setStartTime(ts);
//            requestDto.setEndTime(ts2);
//        }
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
        //传递String类型时间
//        String startTimeString = sdf.format(requestDto.getStartTime());
//        String endTimeString = sdf.format(requestDto.getEndTime());
//        requestDto.setStartTimeString(startTimeString);
//        requestDto.setEndTimeString(endTimeString);
//
        Integer count = candidateInfoMapper.queryCandidateInfoListCount(requestDto);
        int totalPage = PageUtil.getTotalPage(count,pageSize);
        getCandidateInfoByKeywordVo.setTotalPage(totalPage);
        getCandidateInfoByKeywordVo.setTotalSize(count);

        try {
            List<GetCandidateInfoByKeywordResponseDto> candidateInfoList = new ArrayList<GetCandidateInfoByKeywordResponseDto>();
            candidateInfoList = candidateInfoMapper.getCandidateInfoByKeyword(requestDto);
            getCandidateInfoByKeywordVo.setCandidateInfoList(candidateInfoList);
        } catch(Exception e) {
            logger.info(e.getMessage());
            e.printStackTrace();
            throw new Exception(e);
        }
        logger.info("getCandidateInfoByKeyword is Finish");
        return getCandidateInfoByKeywordVo;
    }
}
