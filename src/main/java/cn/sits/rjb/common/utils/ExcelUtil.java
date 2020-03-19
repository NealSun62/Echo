package cn.sits.rjb.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

@Configuration
public class ExcelUtil {
    @Value("${excel.url}")
    public String filePath;
    @Value("${excelUrlPrefix}")
    public String excelUrlPrefix;
    /**
     * 导出Excel
     * @param sheetName sheet名称
     * @param title 标题
     * @param values 内容
     * @param wb HSSFWorkbook对象
     * @return
     */
    public static HSSFWorkbook getHSSFWorkbook(String sheetName,String []title,String [][]values, HSSFWorkbook wb){

        // 第一步，创建一个HSSFWorkbook，对应一个Excel文件
        if(wb == null){
            wb = new HSSFWorkbook();
        }

        // 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet(sheetName);

        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
        HSSFRow row = sheet.createRow(0);

        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

        //声明列对象
        HSSFCell cell = null;

        //创建标题
        for(int i=0;i<title.length;i++){
            sheet.setColumnWidth(i,20*256);
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(style);
        }
        HSSFCellStyle cellStyle = wb.createCellStyle();//新建单元格样式
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
        cellStyle.setWrapText(true);//设置自动换行
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
        //创建内容
        for(int i=0;i<values.length;i++){
            row = sheet.createRow(i + 1);
            for(int j=0;j<values[i].length;j++){
                //将内容按顺序赋给对应的列对象
                cell = row.createCell(j);
                cell.setCellValue(values[i][j]);
                cell.setCellStyle(cellStyle);
            }
        }
        return wb;
    }

    public String getExcelPath(String sheetName, String[] title, String[][] content, String fileName) throws Exception {
        HSSFWorkbook wb = getHSSFWorkbook(sheetName, title, content, null);
        File folder = new File(filePath);
        if (!folder.exists() && !folder.isDirectory()) {
            System.out.println("文件夹路径不存在，创建路径:" + filePath);
            folder.mkdirs();
        } else {
            System.out.println("文件夹路径存在:" + filePath);
        }
        File tempFile = new File(filePath + fileName +DateUtil.getStringToday().replace(" ","")+ ".xls");
        if(tempFile.exists()){
            tempFile=File.createTempFile(fileName,".xls",folder);
        }
        OutputStream tempOut = new FileOutputStream(tempFile);
        wb.write(tempOut);
        tempOut.close();
        return  excelUrlPrefix + tempFile.getName();
    }

    public static boolean isExcel2003(String fileName){
        if(fileName.matches("^.+\\.(?i)(xlsx)$")){
            return false;
        } else {
            return true;
        }
    }
    public static String getValue(Cell cell) {
        String cellValue = "";
        if (cell!= null) {
            cell.setCellType(Cell.CELL_TYPE_STRING);
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC: // 数字
                    cellValue = cell.getStringCellValue();
                    break;
                case Cell.CELL_TYPE_STRING: // 字符串
                    cellValue = cell.getStringCellValue();
                    if (StringUtils.isEmpty(cellValue)){
                        cellValue = "0";
                    }
                    break;
                case Cell.CELL_TYPE_BOOLEAN: // Boolean
                    cellValue = cell.getBooleanCellValue() + "";
                    break;
                case Cell.CELL_TYPE_FORMULA: // 公式
                    cellValue = cell.getCellFormula() + "";
                    break;
                case Cell.CELL_TYPE_BLANK: // 空值
                    cellValue = "";
                    break;
                case Cell.CELL_TYPE_ERROR: // 故障
                    cellValue = "非法字符";
                    break;
                default:
                    cellValue = "未知类型";
                    break;
            }
        }
        return cellValue;
    }

}
