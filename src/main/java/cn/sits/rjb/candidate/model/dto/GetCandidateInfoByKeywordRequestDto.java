package cn.sits.rjb.candidate.model.dto;

import cn.sits.rjb.system.model.dto.UserDto;

/**
 * @author Neal.Sun
 * @version V1.0
 * @Package cn.sits.rjb.candidate.model.dto
 * @date 2020/03/19 15:54
 */
public class GetCandidateInfoByKeywordRequestDto extends UserDto {
    private String keyword;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
