package cn.sits.rjb.excel.mapper;


import cn.sits.rjb.excel.model.dto.DownloadCandidateListExcelRequestDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Neal.Sun
 * @version V1.0
 * @Package cn.sits.rjb.traffic.mapper
 * @date 2020/02/18 15:17
 */
@Mapper
public interface ExcelMapper {
    List downloadCandidateListExcel(DownloadCandidateListExcelRequestDto requestDto);
    int addCandidateListExcel(List candidateLists);
}
