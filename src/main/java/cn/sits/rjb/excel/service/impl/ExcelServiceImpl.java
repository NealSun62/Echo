package cn.sits.rjb.excel.service.impl;

import cn.sits.rjb.excel.mapper.ExcelMapper;
import cn.sits.rjb.excel.service.IExcelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;

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

}
