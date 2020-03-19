package cn.sits.rjb.excel.model.dto;

import cn.sits.rjb.common.data.PageRequestDto;

/**
 * @author Neal.Sun
 * @version V1.0
 * @Package cn.sits.rjb.candidate.model.dto
 * @date 2020/03/19 15:54
 */
public class DownloadCandidateListExcelRequestDto extends PageRequestDto {
    private String keyword;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
