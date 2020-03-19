package cn.sits.rjb.candidate.service;

import cn.sits.rjb.candidate.model.dto.GetCandidateInfoByKeywordRequestDto;
import cn.sits.rjb.candidate.model.vo.GetCandidateInfoByKeywordVo;

/**
 * @author Neal.Sun
 * @version V1.0
 * @Package cn.sits.rjb.police.service.impl
 * @date 2020/02/25 16:01
 */
public interface ICandidateInfoService {
    GetCandidateInfoByKeywordVo getCandidateInfoByKeyword(GetCandidateInfoByKeywordRequestDto requestDto) throws Exception;
}
