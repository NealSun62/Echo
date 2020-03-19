package cn.sits.rjb.candidate.model.vo;

import cn.sits.rjb.candidate.model.dto.GetCandidateInfoByKeywordResponseDto;
import cn.sits.rjb.common.data.PageResponseDto;

import java.util.List;

/**
 * @author Neal.Sun
 * @version V1.0
 * @Package cn.sits.rjb.candidate.model.vo
 * @date 2020/03/19 16:01
 */
public class GetCandidateInfoByKeywordVo extends PageResponseDto {
    private List<GetCandidateInfoByKeywordResponseDto> candidateInfoList;

    public List<GetCandidateInfoByKeywordResponseDto> getCandidateInfoList() {
        return candidateInfoList;
    }

    public void setCandidateInfoList(List<GetCandidateInfoByKeywordResponseDto> candidateInfoList) {
        this.candidateInfoList = candidateInfoList;
    }
}
