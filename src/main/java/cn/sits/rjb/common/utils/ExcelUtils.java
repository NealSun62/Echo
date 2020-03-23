package cn.sits.rjb.common.utils;

import jodd.util.StringUtil;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

public class ExcelUtils {
    private static String EXCEL_XLS = ".xls";
    private static String XLS = ".xls";
    private static String EXCEL_XLSX = ".xlsx";
    private static String XLSX = ".xlsx";
    /**
     * 导出excel
     *
     * @param response
     * @param header
     * @param dataList
     * @throws Exception
     */
    public static void exportExcel(HttpServletResponse response, List<String> header, List<List<String>> dataList) throws Exception {
        exportExcel(response, "主标题", "副标题", header, dataList);
    }

    /**
     * 导出excel
     *
     * @param response
     * @param title
     * @param subheading
     * @param header
     * @param dataList
     * @throws Exception
     */
    public static void exportExcel(HttpServletResponse response, String title, String subheading, List<String> header, List<List<String>> dataList) throws Exception {
        //获取一个HSSFWorkbook对象
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFCellStyle style = getHSSFCellStyle(workbook);
        //创建一个sheet
        HSSFSheet sheet = workbook.createSheet("Sheet1");
        //创建一个标题行
        CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, 0, header.size());
        //创建一个副标题行
        CellRangeAddress cellRangeAddress2 = new CellRangeAddress(1, 1, 0, header.size());
        sheet.addMergedRegion(cellRangeAddress);
        sheet.addMergedRegion(cellRangeAddress2);

        //标题，居中
        HSSFRow row0 = sheet.createRow(0);
        HSSFCell cell0 = row0.createCell(0);
        cell0.setCellValue(title);
        cell0.setCellStyle(style);
        // 第一行
        HSSFRow row1 = sheet.createRow(1);
        HSSFCell cell1 = row1.createCell(0);
        //副标题
        cell1.setCellValue(subheading);
        cell1.setCellStyle(style);

        //表头
        HSSFRow row = sheet.createRow(2);

        HSSFCell cell = null;
        for (int i = 0; i < header.size(); i++) {
            cell = row.createCell(i);
            cell.setCellValue(header.get(i));
            cell.setCellStyle(style);
        }

        //数据
        for (int i = 0; i < dataList.size(); i++) {
            row = sheet.createRow(i + 3);
            for (int j = 0; j < dataList.get(i).size(); j++) {
                row.createCell(j).setCellValue(dataList.get(i).get(j));
            }
        }

        OutputStream outputStream = response.getOutputStream();
        //设置页面不缓存
        response.reset();
        String filename = title;
        //设置返回文件名的编码格式
        response.setCharacterEncoding("utf-8");
        filename = URLEncoder.encode(filename, "utf-8");
        response.setHeader("Content-disposition", "attachment;filename=" + filename + ".xls");
        response.setContentType("application/msexcel");
        workbook.write(outputStream);
        outputStream.close();
    }

    /**
     * 导入数据（单页）
     *
     * @param file        文件
     * @param sheetIndex  页名的索引(从0开始，-1代表全部页)
     * @param headerIndex 表头的索引（用于获取共多少列以及第几行开始读数据）
     * @return
     * @throws IOException
     */
    public static List<List<Object>> importExcel(MultipartFile file, int sheetIndex, int headerIndex) throws Exception {
        Workbook workbook = null;
        //返回的data
        List<List<Object>> data = new ArrayList<>();
        workbook = getWorkbook(file);
        //导入某一页
        if (sheetIndex != -1 && sheetIndex > -1) {
            Sheet sheet = workbook.getSheetAt(sheetIndex);
            List<List<Object>> lists = importOneSheet(sheet, headerIndex);
            data.addAll(lists);
        } else {
            //导入全部
            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                Sheet sheet = workbook.getSheetAt(i);
                if (sheet == null) {
                    continue;
                }
                List<List<Object>> lists = importOneSheet(sheet, headerIndex);
                data.addAll(lists);
            }
        }
        return data;
    }

    /**
     * 导入数据（所有页）
     *
     * @param file        文件
     * @param headerIndex 表头的索引（用于获取共多少列以及第几行开始读数据）
     * @return
     * @throws IOException
     */
    public static List<List<Object>> importExcel(MultipartFile file, int headerIndex) throws Exception {
        return importExcel(file, -1, headerIndex);
    }

    /**
     * 创建一个style
     *
     * @param workbook
     * @return
     */
    private static HSSFCellStyle getHSSFCellStyle(HSSFWorkbook workbook) {
        HSSFCellStyle style = workbook.createCellStyle();
        //居中
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        return style;
    }


    /**
     * 获取一个sheet里的数据
     *
     * @param sheet
     * @param headerIndex
     * @return
     * @throws Exception
     */
    private static List<List<Object>> importOneSheet(Sheet sheet, int headerIndex) throws Exception {
        List<List<Object>> data = new ArrayList<>();
        int row = sheet.getLastRowNum();
        //row = -1 表格中没有数据
        //row = headerIndex 表格中表头以下没有数据（指没有有用数据）
        if (row == -1 || row == headerIndex) {
            throw new Exception("表格中没有有用数据!");
        }
        //通过表头获取共多少列
        int coloumNum = sheet.getRow(headerIndex).getPhysicalNumberOfCells();
        //从表头下一行开始取数据
        for (int i = headerIndex + 1; i <= row; i++) {
            Row row1 = sheet.getRow(i);
            List<Object> list = new ArrayList<>();
            if (row1 != null) {
                for (int j = 0; j < coloumNum; j++) {
                    list.add(getCellValue(row1.getCell(j)));
                }
            }
            data.add(list);
        }
        return data;
    }

    /**
     * 获取workbook
     *
     * @return
     */
    private static Workbook getWorkbook(MultipartFile file) throws Exception {
        Workbook workbook = null;
        //获取文件名
        String fileName = file.getOriginalFilename();
        //判断文件格式
        if (fileName.endsWith(XLS)) {
            workbook = new HSSFWorkbook(file.getInputStream());
        } else if (fileName.endsWith(XLSX)) {
            workbook = new XSSFWorkbook(file.getInputStream());
        } else {
            throw new Exception("文件格式有误!");
        }
        return workbook;
    }


    /**
     * 获取单元格的值
     *
     * @param cell
     * @return
     */
    private static String getCellValue(Cell cell) {
        String cellValue = "";
        DecimalFormat df = new DecimalFormat("#");
        switch (cell.getCellType()) {
            case HSSFCell.CELL_TYPE_STRING:
                cellValue = cell.getRichStringCellValue().getString().trim();
                break;
            case HSSFCell.CELL_TYPE_NUMERIC:
                cellValue = df.format(cell.getNumericCellValue()).toString();
                break;
            case HSSFCell.CELL_TYPE_BOOLEAN:
                cellValue = String.valueOf(cell.getBooleanCellValue()).trim();
                break;
            case HSSFCell.CELL_TYPE_FORMULA:
                cellValue = cell.getCellFormula();
                break;
            default:
                cellValue = "";
        }
        return cellValue.trim();
    }
    /**
     *
     * @Title: createExcelFile
     * @Description: 在填充sheet数据的时候,会需要一个空的Excel文件,用于设置Sheet信息的时候用到
     * @return 一个不带有头信息,数据信息的空的excel文件
     * @return: HSSFWorkbook
     */
    public static HSSFWorkbook createExcelFile() {
        HSSFWorkbook wb = new HSSFWorkbook();
        return wb;
    }

    /**
     *
     * @Title: createExcelFile
     * @Description: 创建一个空的带有头信息的excel
     * @param fileName
     * @param heads
     * @return
     * @return: HSSFWorkbook
     */
    public static HSSFWorkbook createExcelFile(String fileName,
                                               List<String> heads) {
        HSSFWorkbook wb = new HSSFWorkbook();
        if (StringUtil.isEmpty(fileName) || null == heads) {
            return null;
        } else {
            HSSFSheet sheet = wb.createSheet(fileName);
            HSSFRow row = sheet.createRow(0);
            // 封装头信息
            for (int index = 0; index < heads.size(); index++) {
                row.createCell(index).setCellValue(heads.get(index));
            }
        }
        return wb;
    }

    /**
     * @Title: createExcelFile
     * @Description: 创建excel,带有头信息和数据
     * @param fileName
     *            excel表格文件名称
     * @param heads
     *            excel表格的头信息
     * @param dataList
     *            excel表格要填充的数据
     * @return
     * @throws IOException
     * @return: HSSFWorkbook
     */
    public static Workbook createExcelFile(String fileName, List<String> heads, List<List<String>> dataList) {
        Workbook wb = new XSSFWorkbook();
        if (StringUtil.isEmpty(fileName) || null == heads || null == dataList) {
            return null;
        } else {
            Sheet sheet = wb.createSheet(fileName);
            Row row = sheet.createRow(0);
            // 封装头信息
            for (int index = 0; index < heads.size(); index++) {
                row.createCell(index).setCellValue(heads.get(index));
            }
            // 填充数据信息
            for (int i = 0; i < dataList.size(); i++) {
                Row row_data = sheet.createRow(i + 1);
                for (int j = 0; j < dataList.get(i).size(); j++) {
                    row_data.createCell(j).setCellValue(dataList.get(i).get(j));
                }
            }
        }
        return wb;
    }

    /**
     *
     * @Title: produceCellType
     * @Description: 获取excel单元格里面内容的格式,来获取数据
     * @param cell
     *            单元格
     * @return
     * @return: String 单元格的内容
     */
    private static String produceCellType(Cell cell) {
        String cellStrData = null;
        if (null == cell) {
            return null;
        } else {
            switch (cell.getCellType()) {
                case HSSFCell.CELL_TYPE_NUMERIC: // 日期或者数字
                    // 处理日期格式、时间格式
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        SimpleDateFormat sdf = null;
                        // 时间格式的处理
                        if (cell.getCellStyle().getDataFormat() == HSSFDataFormat
                                .getBuiltinFormat("h:mm")) {
                            sdf = new SimpleDateFormat("HH:mm");
                        } else {// 日期格式的处理
                            sdf = new SimpleDateFormat("yyyy-MM-dd");
                        }
                        Date date = cell.getDateCellValue();
                        cellStrData = sdf.format(date);
                    } else {
                        // 数字的处理
                        double cellData = cell.getNumericCellValue();
                        cellStrData = String.valueOf(cellData);
                    }
                    break;
                case HSSFCell.CELL_TYPE_STRING: // 字符串
                    cellStrData = cell.getStringCellValue();
                    break;
                case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
                    cellStrData = String.valueOf(cell.getBooleanCellValue());
                    break;
                case HSSFCell.CELL_TYPE_FORMULA: // 公式
                    cellStrData = String.valueOf(cell.getCellFormula());
                    break;
                case HSSFCell.CELL_TYPE_BLANK: // 空值
                    break;
                case HSSFCell.CELL_TYPE_ERROR: // 故障
                    break;
                default:
                    break;
            }
        }
        return cellStrData;
    }

    /**
     *
     * @Title: convertExcelDataToMapDataWithPrimaryKey
     * @Description: 将excel文件的每一行数据,转换为HashMap的形式.只转换第一个sheet的数据内容
     * @param convertMap
     *            转换的准则,例如 Map<String, String> headMap = new
     *            LinkedHashMap<String, String>(); headMap.put("指标ID",
     *            "indicatorId"); headMap.put("指标名称", "indicatorName");
     * @param filePath
     *            excel文件
     * @return
     * @throws IOException
     * @return: List<HashMap<String, String>>
     */
    public static List<HashMap<String, String>> convertExcelDataToMapData(
            Map<String, String> convertMap, String filePath) throws IOException {
        List<HashMap<String, String>> dataList = new ArrayList<HashMap<String, String>>();
        Map<String, Integer> headMap = new HashMap<String, Integer>();
        if (null == convertMap || convertMap.size() == 0
                || StringUtil.isEmpty(filePath)) {
            return dataList;
        } else {
            InputStream input = new FileInputStream(filePath); // 建立输入流
            Workbook wb = new HSSFWorkbook(input);
            Sheet sheet = wb.getSheetAt(0);
            Row rowIndexs = sheet.getRow(0);
            int cellSize = rowIndexs.getLastCellNum();
            Set<String> keys = convertMap.keySet();
            // 将对应的字段和excel的head的下标对应起来
            for (String key : keys) {
                for (int i = 0; i < cellSize; i++) {
                    Cell cell = rowIndexs.getCell(i);
                    if (cell != null
                            && cell.getCellType() != Cell.CELL_TYPE_BLANK) {
                        if (key.equals(cell.getStringCellValue())) {
                            headMap.put(key, rowIndexs.getCell(i)
                                    .getColumnIndex());
                        }
                    }
                }
            }
            // 处理数据
            int rowSize = sheet.getLastRowNum();
            for (int i = 1; i < rowSize; i++) { // 第一行默认是表头数据,不算入计算结果
                HashMap<String, String> resultMap = new HashMap<String, String>(); // 用于保存每一行的转换结果
                Row row = sheet.getRow(i);
                for (Entry<String, Integer> entry : headMap.entrySet()) {
                    Cell cell = row.getCell(entry.getValue());
                    String data = produceCellType(cell);
                    resultMap.put(convertMap.get(entry.getKey()), data);
                }
                dataList.add(resultMap);
            }
        }

        return dataList;
    }

    /**
     *
     * @Title: convertExcelDataToMapDataWithPrimaryKey
     * @Description: excel的转换,带有主键的原则。如果excel的那一行数据的表示的主键为null或者没填写。那么这一行不转换。
     *               例如,下面的 指标ID可以理解为主键.//默认第一行的第一列为主键
     *               将excel文件的每一行数据,转换为HashMap的形式.只转换第一个sheet的数据内容.
     * @param convertMap
     *            转换的准则,例如 Map<String, String> headMap = new
     *            LinkedHashMap<String, String>(); headMap.put("指标ID",
     *            "indicatorId"); headMap.put("指标名称", "indicatorName");
     * @param filePath
     *            excel文件
     * @return
     * @throws IOException
     * @return: List<HashMap<String, String>>
     */
    public static List<HashMap<String, String>> convertExcelDataToMapDataWithPrimaryKey(
            Map<String, String> convertMap, String filePath) throws IOException {
        List<HashMap<String, String>> dataList = new ArrayList<HashMap<String, String>>();
        Map<String, Integer> headMap = new HashMap<String, Integer>();
        if (null == convertMap || convertMap.size() == 0
                || StringUtil.isEmpty(filePath)) {
            return dataList;
        } else {
            InputStream input = new FileInputStream(filePath); // 建立输入流
            Workbook wb = new HSSFWorkbook(input);
            Sheet sheet = wb.getSheetAt(0);
            Row rowIndexs = sheet.getRow(0);
            int cellSize = rowIndexs.getLastCellNum();
            Set<String> keys = convertMap.keySet();
            // 将对应的字段和excel的head的下标对应起来
            for (String key : keys) {
                for (int i = 0; i < cellSize; i++) {
                    Cell cell = rowIndexs.getCell(i);
                    if (cell != null
                            && cell.getCellType() != Cell.CELL_TYPE_BLANK) {
                        if (key.equals(cell.getStringCellValue())) {
                            headMap.put(key, rowIndexs.getCell(i)
                                    .getColumnIndex());
                        }
                    }
                }
            }
            // 处理数据
            int rowSize = sheet.getLastRowNum();
            for (int i = 1; i < rowSize; i++) { // 第一行默认是表头数据,不算入计算结果
                HashMap<String, String> resultMap = new HashMap<String, String>(); // 用于保存每一行的转换结果
                Row row = sheet.getRow(i);
                Cell flagCell = row.getCell(0); // 默认第0列是每一行的主键
                if (null != row && null != flagCell
                        && HSSFCell.CELL_TYPE_BLANK != flagCell.getCellType()) {
                    for (Entry<String, Integer> entry : headMap.entrySet()) {
                        Cell cell = row.getCell(entry.getValue());
                        if (null != cell) {
                            String data = produceCellType(cell);
                            resultMap.put(convertMap.get(entry.getKey()), data);
                        }
                    }
                }
                // 将数据加入到,返回数值里面
                dataList.add(resultMap);
            }
        }
        return dataList;
    }

    /**
     * @param <T>
     * @Title: convertExcelDataToClassData
     * @Description: 解析excel已有的数据,以Class的形式返回.
     * @param
     *
     * @param fileName
     *            excel文件
     * @param class1
     *            要转换的Class的类型
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws IOException
     * @throws NoSuchFieldException
     * @throws SecurityException
     * @return: List<T>
     */
    public static <T> List<T> convertExcelDataToClassData(
            Map<String, String> convertMap, String fileName, Class<T> class1)
            throws InstantiationException, IllegalAccessException, IOException,
            NoSuchFieldException, SecurityException {

        List<T> objects = new ArrayList<T>(); // 返回结果集
        Map<String, Integer> indexHashMap = new HashMap<String, Integer>(); // 定位excel头文件cell位置
        if (null == convertMap || convertMap.size() == 0
                || StringUtil.isEmpty(fileName)) {
            return objects;
        } else {
            InputStream input = new FileInputStream(fileName); // 建立输入流
            Workbook wb = new HSSFWorkbook(input);
            Sheet sheet = wb.getSheetAt(0);
            Row rowIndexs = sheet.getRow(0);
            int cellSize = rowIndexs.getLastCellNum();
            // 将对应的字段和excel的head的下标对应起来
            Set<String> keys = convertMap.keySet();
            for (String key : keys) {
                for (int i = 0; i < cellSize; i++) {
                    Cell cell = rowIndexs.getCell(i);
                    if (cell != null
                            && cell.getCellType() != Cell.CELL_TYPE_BLANK) {
                        if (key.equals(produceCellType(cell))) {
                            indexHashMap.put(key, rowIndexs.getCell(i)
                                    .getColumnIndex()); // 头文件push 下标位置
                        }
                    }
                }
            }
            // 数据的封装
            for (int i = 1; i <= sheet.getLastRowNum(); i++) { // 第一行默认是下标，不算入计算结果
                Row row = sheet.getRow(i);
                T object = class1.newInstance();
                for (Entry<String, Integer> entry : indexHashMap.entrySet()) {
                    Cell cell = row.getCell(entry.getValue());
                    String data = produceCellType(cell);
                    String fieldName = convertMap.get(entry.getKey());
                    Field field = object.getClass().getDeclaredField(fieldName);
                    field.setAccessible(true);
                    // 根据Field的类型,来设置Field的内容
                    // 以便于适应除了String外的int,long,double,float等类型的属性
                    setFieldValue(object, data, field);
                }
                objects.add(object);
            }

        }
        return objects;
    }

    /**
     * @param <T>
     * @Title: convertExcelDataToClassData
     * @Description: 解析excel已有的数据,以Class的形式返回.
     * @param
     *
     * @param class1
     *            要转换的Class的类型
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws IOException
     * @throws NoSuchFieldException
     * @throws SecurityException
     * @return: List<T>
     */
    public static <T> List<T> convertExcelDataToClassData(
            Map<String, String> convertMap, InputStream input, Class<T> class1)
            throws InstantiationException, IllegalAccessException, IOException,
            NoSuchFieldException, SecurityException {

        List<T> objects = new ArrayList<T>(); // 返回结果集
        Map<String, Integer> indexHashMap = new HashMap<String, Integer>(); // 定位excel头文件cell位置
        if (null == convertMap || convertMap.size() == 0 || null == input) {
            return objects;
        } else {
            Workbook wb = new HSSFWorkbook(input);
            Sheet sheet = wb.getSheetAt(0);
            Row rowIndexs = sheet.getRow(0);
            int cellSize = rowIndexs.getLastCellNum();
            // 将对应的字段和excel的head的下标对应起来
            Set<String> keys = convertMap.keySet();
            for (String key : keys) {
                for (int i = 0; i < cellSize; i++) {
                    Cell cell = rowIndexs.getCell(i);
                    if (cell != null
                            && cell.getCellType() != Cell.CELL_TYPE_BLANK) {
                        if (key.equals(produceCellType(cell))) {
                            indexHashMap.put(key, rowIndexs.getCell(i)
                                    .getColumnIndex()); // 头文件push 下标位置
                        }
                    }
                }
            }
            // 数据的封装
            for (int i = 1; i <= sheet.getLastRowNum(); i++) { // 第一行默认是下标，不算入计算结果
                Row row = sheet.getRow(i);
                T object = class1.newInstance();
                for (Entry<String, Integer> entry : indexHashMap.entrySet()) {
                    Cell cell = row.getCell(entry.getValue());
                    String data = produceCellType(cell);
                    String fieldName = convertMap.get(entry.getKey());
                    Field field = object.getClass().getDeclaredField(fieldName);
                    field.setAccessible(true);
                    // 根据Field的类型,来设置Field的内容
                    // 以便于适应除了String外的int,long,double,float等类型的属性
                    setFieldValue(object, data, field);
                }
                objects.add(object);
            }

        }
        return objects;
    }

    /**
     * @Title: matcheExcelIndexToDataForm
     * @Description: 解析excel已数组的形式返回
     * @param headNameMap
     *            headNameMap.put("CID", "customerId")
     * @param
     * @param class1
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws IOException
     * @throws NoSuchFieldException
     * @throws SecurityException
     * @return: List<Object>
     */
    public static <T> List<T> convertExcelDataToClassDataWithPrimaryKey(
            Map<String, String> headNameMap, InputStream ips, Class<T> class1)
            throws InstantiationException, IllegalAccessException, IOException,
            NoSuchFieldException, SecurityException {

        List<T> objects = new ArrayList<T>(); // 返回结果集
        Map<String, Integer> indexHashMap = new HashMap<String, Integer>(); // 定位excel头文件cell位置
        if (null == headNameMap || headNameMap.size() == 0 || null == ips) {
            return objects;
        } else {
            Workbook wb = null;
            wb = new HSSFWorkbook(ips);
            Sheet sheet = wb.getSheetAt(0);
            Row rowIndexs = sheet.getRow(0);
            int cellSize = rowIndexs.getLastCellNum();
            Set<String> keys = headNameMap.keySet();
            for (String key : keys) {
                for (int i = 0; i < cellSize; i++) {
                    Cell cell = rowIndexs.getCell(i);
                    if (cell != null
                            && cell.getCellType() != Cell.CELL_TYPE_BLANK) {
                        if (key.equals(cell.getStringCellValue())) {
                            indexHashMap.put(key, rowIndexs.getCell(i)
                                    .getColumnIndex()); // 头文件push 下标位置
                        }

                    }

                }
            }

            for (int i = 1; i <= sheet.getLastRowNum(); i++) { // 第一行默认是下标，不算入计算结果
                Row row = sheet.getRow(i);
                T object = class1.newInstance();
                Cell flagCell = row.getCell(0);
                if (null != row && null != flagCell
                        && HSSFCell.CELL_TYPE_BLANK != flagCell.getCellType()) {
                    for (Entry<String, Integer> entry : indexHashMap.entrySet()) {
                        Cell cell = row.getCell(entry.getValue());
                        if (null != cell) {
                            String data = produceCellType(cell);
                            String fieldName = headNameMap.get(entry.getKey());
                            Field field = object.getClass().getDeclaredField(
                                    fieldName);
                            field.setAccessible(true);
                            // 根据Field的类型,来设置Field的内容
                            // 以便于适应除了String外的int,long,double,float等类型的属性
                            setFieldValue(object, data, field);
                        } else {
                            continue;
                        }
                    }
                    objects.add(object);
                } else {
                    break;
                }
            }

        }
        return objects;
    }

    /**
     *
     * @Title: setFieldValue 设置JavaBean属性的数据,以便于支持除了String类型外的其他数据类型
     * @Description: setFieldValue
     *               设置JavaBean属性的数据,以便于支持除了String类型外的其他数据类型,例如int,
     *               long,double,date
     * @param object
     *            JavaBean
     * @param data
     *            要设置的数据
     * @param field
     *            JavaBean的Field字段
     * @throws IllegalAccessException
     * @return: void
     */
    private static <T> void setFieldValue(T object, String data, Field field)
            throws IllegalAccessException {
        // 对field的类型进行判断,以便于支持String外的其它类型
        String fieldType = field.getType().getName();
        if (fieldType.equals("java.lang.Double") || fieldType.equals("double")) {
            // Double类型的处理
            double doubleValue = Double.parseDouble(data);
            field.set(object, doubleValue);
        } else if (fieldType.equals("java.lang.Float")
                || fieldType.equals("float")) {
            // Float类型的处理
            float folatValue = Float.parseFloat(data);
            field.set(object, folatValue);
        } else if (fieldType.equals("java.lang.Integer")
                || fieldType.equals("int")) {
            // Integer类型的处理
            int intValue = Integer.parseInt(data);
            field.set(object, intValue);
        } else if (fieldType.equals("java.lang.Long")
                || fieldType.equals("long")) {
            // Long类型的处理
            long longValue = Long.parseLong(data);
            field.set(object, longValue);
        } else if (field.getType().getName().equals("java.util.Date")) {
            // Date类型的处理
            // "yyyy-MM-dd HH:mm:ss",根据具体的格式来处理
            Date dateValue = strToDate(data);
            field.set(object, dateValue);
        } else {
            // String的处理
            field.set(object, data);
        }
    }

    /**
     * @Title: matcheExcelIndexToDataForm
     * @Description: 解析excel已数组的形式返回
     * @param headNameMap
     *            headNameMap.put("CID", "customerId")
     * @param fileName
     * @param class1
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws IOException
     * @throws NoSuchFieldException
     * @throws SecurityException
     * @return: List<Object>
     */
    public static <T> List<T> convertExcelDataToClassDataWithPrimaryKey(
            Map<String, String> headNameMap, String fileName, Class<T> class1)
            throws InstantiationException, IllegalAccessException, IOException,
            NoSuchFieldException, SecurityException {

        List<T> objects = new ArrayList<T>(); // 返回结果集
        Map<String, Integer> indexHashMap = new HashMap<String, Integer>(); // 定位excel头文件cell位置
        if (null == headNameMap || headNameMap.size() == 0 || null == fileName) {
            return objects;
        } else {
            Workbook wb = null;
            InputStream ips = new FileInputStream(fileName);
            wb = new HSSFWorkbook(ips);
            Sheet sheet = wb.getSheetAt(0);
            Row rowIndexs = sheet.getRow(0);
            int cellSize = rowIndexs.getLastCellNum();
            Set<String> keys = headNameMap.keySet();
            for (String key : keys) {
                for (int i = 0; i < cellSize; i++) {
                    Cell cell = rowIndexs.getCell(i);
                    if (cell != null
                            && cell.getCellType() != Cell.CELL_TYPE_BLANK) {
                        if (key.equals(cell.getStringCellValue())) {
                            indexHashMap.put(key, rowIndexs.getCell(i)
                                    .getColumnIndex()); // 头文件push 下标位置
                        }

                    }

                }
            }

            for (int i = 1; i <= sheet.getLastRowNum(); i++) { // 第一行默认是下标，不算入计算结果
                Row row = sheet.getRow(i);
                T object = class1.newInstance();
                Cell flagCell = row.getCell(0);
                if (null != row && null != flagCell
                        && HSSFCell.CELL_TYPE_BLANK != flagCell.getCellType()) {
                    for (Entry<String, Integer> entry : indexHashMap.entrySet()) {
                        Cell cell = row.getCell(entry.getValue());
                        if (null != cell) {
                            String data = produceCellType(cell);
                            String fieldName = headNameMap.get(entry.getKey());
                            Field field = object.getClass().getDeclaredField(
                                    fieldName);
                            field.setAccessible(true);
                            // 根据Field的类型,来设置Field的内容
                            // 以便于适应除了String外的int,long,double,float等类型的属性
                            setFieldValue(object, data, field);
                        } else {
                            continue;
                        }
                    }
                    objects.add(object);
                } else {
                    break;
                }
            }

        }
        return objects;
    }

    /**
     *
     * @Title: exportExcel
     * @Description: 需要先创建好excel文件,调用一次添加一次sheet信息
     * @param workbook
     *            要添加sheet信息的excel
     * @param sheetNum
     *            sheet的编号位置,从0开始
     * @param sheetTitle
     *            要添加sheet信息
     * @param heads
     *            头信息
     * @param dataList
     *            要填充的数据
     * @return
     * @throws Exception
     * @return: HSSFWorkbook 返回天填充数据后的excel
     */
    public static HSSFWorkbook fillExcelWithSheetInfo(HSSFWorkbook workbook,
                                                      int sheetNum, String sheetTitle, List<String> heads,
                                                      List<List<String>> dataList) throws IOException {
        if (StringUtil.isEmpty(sheetTitle) || null == heads
                || null == dataList || workbook == null) {
            return null;
        } else {
            // create sheet
            HSSFSheet sheet = workbook.createSheet();
            workbook.setSheetName(sheetNum, sheetTitle);
            // 头信息
            HSSFRow row = sheet.createRow(0);
            for (int index = 0; index < heads.size(); index++) {
                row.createCell(index).setCellValue(heads.get(index));
            }
            // 填充信息
            for (int i = 0; i < dataList.size(); i++) {
                HSSFRow row_data = sheet.createRow(i + 1);
                for (int j = 0; j < dataList.get(i).size(); j++) {
                    row_data.createCell(j).setCellValue(dataList.get(i).get(j));
                }
            }
        }
        return workbook;
    }

    /**
     * 使用一个List数组,来填充要显示的excel数据
     *
     * @param
     *
     * @return
     * @throws NoSuchFieldException
     * @throws SecurityException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public static Map<String, Object> fillExcelData(
            Map<String, String> headMap, List<?> objects)
            throws NoSuchFieldException, SecurityException,
            IllegalArgumentException, IllegalAccessException {
        Map<String, Object> resMap = new HashMap<String, Object>();
        List<String> heads = new ArrayList<String>();
        Set<String> keySet = headMap.keySet();
        List<List<String>> dataList = new ArrayList<List<String>>();
        boolean flag = true;
        if (null == objects || objects.size() < 1) {
            for (String key : keySet) {
                heads.add(key);
            }
        }
        for (Object object : objects) {
            List<String> data = new ArrayList<String>();
            for (String key : keySet) {
                if (flag == true) {
                    heads.add(key);
                }
                Field userField = object.getClass().getDeclaredField(
                        headMap.get(key));
                userField.setAccessible(true);
                String userData = String.valueOf(userField.get(object));
                // 一些特殊的判断
                if ("状态".equals(key)) {
                    if ("1".equals(userData)) {
                        userData = "有效";
                    } else {
                        userData = "无效";
                    }
                }
                data.add(userData);
            }
            flag = false;
            dataList.add(data);
        }
        resMap.put("heads", heads);
        resMap.put("dataList", dataList);
        return resMap;
    }

    /**
     * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
     *
     * @param strDate
     * @return
     */
    private static Date strToDate(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }
    public static String importExcel(String filePath) throws Exception {
        //判断文件
        if (filePath != null && !"".equals(filePath)) {
            File file = new File(filePath);
            //判断格式
            if (file.getName().endsWith(EXCEL_XLS) || file.getName().endsWith(EXCEL_XLSX)) {
                //创建输入流对象
                InputStream is = new FileInputStream(file);
                Workbook workbook = null;
                //判断excel版本号
                if (file.getName().endsWith(EXCEL_XLS)) {
                    workbook = new HSSFWorkbook(is);
                } else if (file.getName().endsWith(EXCEL_XLSX)) {
                    workbook = new XSSFWorkbook(is);
                }
                Map<String, Object> objectMap=new HashMap<>();
                //循环表格（sheet）
                for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                    Sheet sheet = workbook.getSheetAt(i);
                    //判断sheet是否有数据
                    if (sheet.getPhysicalNumberOfRows() <= 0) {
                        continue;
                    }
                    //存放集合
                    List<Map<String, Object>> list = new ArrayList<>();
                    //存放表头名字（第一行的数据）
                    List<String> header = new ArrayList<>();
                    for (int x = 0; x < sheet.getRow(0).getLastCellNum(); x++) {
                        Cell cell = sheet.getRow(0).getCell(x);
                        String value = cell.getStringCellValue();
                        header.add(value);
                    }

                    //获取行并进行循环
                    for (int j = 1; j <= sheet.getLastRowNum(); j++) {
                        Row row = sheet.getRow(j);
                        //判断row是否有数据
                        if (row.getPhysicalNumberOfCells() <= 0) {
                            continue;
                        }
                        //存放数据
                        Map<String, Object> map = new HashMap<>();
                        //获取单元格并进行循环
                        for (int k = 0; k < row.getLastCellNum(); k++) {
                            Cell cell = row.getCell(k);
                            if (cell == null || cell.toString().trim().equals("")) {
                                continue;
                            }
                            CellType cellType = cell.getCellTypeEnum();
                            //存放值
                            String cellValue = "";
                            //字符串
                            if (cellType == CellType.STRING) {
                                cellValue = cell.getStringCellValue().trim();
                                cellValue = io.netty.util.internal.StringUtil.isNullOrEmpty(cellValue) ? "" : cellValue;
                            }
                            //数据格式
                            if (cellType == CellType.NUMERIC) {
                                //判断日期类型
                                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                                    SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                    String date1 = dff.format(cell.getDateCellValue());
                                    cellValue = date1;
                                } else {
                                    //设置数据格式（"#.######"是几位小数）
                                    cellValue = new DecimalFormat("#.######").format(cell.getNumericCellValue());
                                }
                            }
                            if (cellType == CellType.BOOLEAN) {
                                cellValue = String.valueOf(cell.getBooleanCellValue());
                            }
                            //添加数据到map
                            map.put(header.get(k), cellValue);
                        }
                        //把map数据添加到list
                        list.add(map);
                    }
                    objectMap.put(sheet.getSheetName(),list);
                }
                return objectMap.toString();
            } else {
                return "文件不是excel";
            }
        } else {
            return "文件不存在";
        }
    }
}
