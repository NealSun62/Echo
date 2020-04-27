package cn.sits.rjb.excel.service.impl;

import cn.sits.rjb.excel.mapper.ExcelMapper;
import cn.sits.rjb.excel.model.po.CandidateExcelInfo;
import cn.sits.rjb.excel.service.IExcelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
public class ExcelServiceImpl implements IExcelService {
    private static final Logger logger = LoggerFactory.getLogger(ExcelServiceImpl.class);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Resource
    ExcelMapper excelMapper;

    @Transactional(propagation= Propagation.REQUIRED,rollbackFor=Exception.class)
    @Override
    public int importCandidateListExcel(List<List<Object>> candidateListExcel) throws Exception {
        int result = 0;
        List candidateLists = new ArrayList();
        try {
            for (List<Object> candidateList : candidateListExcel) {
                CandidateExcelInfo candidateExcelInfo = new CandidateExcelInfo();
                candidateExcelInfo.setCandidateName(candidateList.get(0).toString());
                candidateExcelInfo.setTelephone(candidateList.get(1).toString());
                candidateExcelInfo.setDescription(candidateList.get(2).toString());
                candidateExcelInfo.setCompanyNow(candidateList.get(3).toString());
                candidateExcelInfo.setCompanyBefore(candidateList.get(4).toString());
                candidateExcelInfo.setPost(candidateList.get(5).toString());
                candidateLists.add(candidateExcelInfo);
            }
            if (candidateLists.size() > 0) {
                result = excelMapper.addCandidateListExcel(candidateLists);
            }
            if (true) {
//                throw new Exception();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception();
        }
        return result;
    }

}
