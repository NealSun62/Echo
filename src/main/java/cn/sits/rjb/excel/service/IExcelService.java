package cn.sits.rjb.excel.service;

import java.util.List;

/**
 * @author Neal.Sun
 * @version V1.0
 * @Package cn.sits.rjb.police.service.impl
 * @date 2020/02/25 16:01
 */
public interface IExcelService {
   int importCandidateListExcel(List<List<Object>> candidateListExcel) throws Exception;
}
