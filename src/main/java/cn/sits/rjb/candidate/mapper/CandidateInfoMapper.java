package cn.sits.rjb.candidate.mapper;


import cn.sits.rjb.candidate.model.dto.GetCandidateInfoByKeywordRequestDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Neal.Sun
 * @version V1.0
 * @Package cn.sits.rjb.traffic.mapper
 * @date 2020/02/18 15:17
 */
@Mapper
public interface CandidateInfoMapper {
    int queryCandidateInfoListCount(GetCandidateInfoByKeywordRequestDto requestDto);
    List getCandidateInfoByKeyword(GetCandidateInfoByKeywordRequestDto requestDto);
}
