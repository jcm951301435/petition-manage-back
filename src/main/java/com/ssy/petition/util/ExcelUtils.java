package com.ssy.petition.util;

import com.ssy.petition.annotation.EntityExcelWorkBook;
import com.ssy.petition.annotation.ExcelBook;
import com.ssy.petition.annotation.ExcelColumn;
import com.ssy.petition.excel.BaseExcelBook;
import com.ssy.petition.excel.BaseExcelColumn;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class ExcelUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelUtils.class);

    private static ResourceLoader resourceLoader;

    private static final String TEMPLATE_FILE_BASE_PATH = "classpath:template/";

    private static final Map<Class<?>, BaseExcelBook> EXCEL_CLASS_MAP = new HashMap<>();

    private static final String INDEX_FIELD_NAME = "excelIndexField";

    private static final String INDEX_FIELD_TITLE = "序号";

    private static final String DEFAULT_TITLE = "Excel导出";

    private static final String TITLE_FONT_NAME = "华文中宋";

    private static final String COLUMN_TITLE_FONT_NAME = "黑体";

    private static final String COLUMN_VALUE_FONT_NAME = "宋体";

    private static final short TITLE_ROW_HEIGHT = 34 * 20;

    private static final short COLUMN_TITLE_ROW_HEIGHT = 34 * 20;

    private static final String XLS_FILE_SUFFIX_2003 = "xls";

    private static final String XLS_FILE_SUFFIX_2007 = "xlsx";

    private static final int TITLE_COUNT = 2;

    public static ResourceLoader getResourceLoader() {
        return resourceLoader;
    }

    @Autowired
    public void setResourceLoader(ResourceLoader resourceLoader) {
        ExcelUtils.resourceLoader = resourceLoader;
    }

    public static void downLoadTemplate(String name, String type, String title, HttpServletResponse response) {
        String path = TEMPLATE_FILE_BASE_PATH + name + "." + XLS_FILE_SUFFIX_2007;
        Resource resource = resourceLoader.getResource(path);
        InputStream inputStream;
        try {
            inputStream = resource.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("模板文件不存在，请联系管理员上传！");
        }
        if (inputStream != null) {
            try {
                HttpUtils.downLoadResponse(title + "." + XLS_FILE_SUFFIX_2007, response);
                OutputStream outputStream = response.getOutputStream();
                IOUtils.copy(inputStream, outputStream);
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("模板文件下载出错，请联系管理员！");
            }
        }
    }

    public static <T> void downLoad(List<T> list, Class<T> cls, HttpServletResponse response) {
        BaseExcelBook baseExcelBook = getExcelBook(cls);
        String title = baseExcelBook.getTitle();
        XSSFWorkbook wb = generateExcelBook(list, cls);
        HttpUtils.downLoadResponse(title + ".xlsx", response);
        try {
            wb.write(response.getOutputStream());
            wb.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <T> XSSFWorkbook generateExcelBook(List<T> list, Class<T> cls) {
        XSSFWorkbook wb = new XSSFWorkbook();
        EntityExcelWorkBook excelWorkBook = getEntityExcelWorkBook(wb, cls);
        initTitle(excelWorkBook);
        initColumnTitle(excelWorkBook);
        initColumnValue(excelWorkBook, list);
        return wb;
    }

    private static <T> EntityExcelWorkBook getEntityExcelWorkBook(XSSFWorkbook wb, Class<T> cls) {
        BaseExcelBook baseExcelBook = getExcelBook(cls);
        String title = baseExcelBook.getTitle();
        int importRowBeginCount = baseExcelBook.getImportRowBeginCount();
        EntityExcelWorkBook excelWorkBook = new EntityExcelWorkBook(cls, wb, title, importRowBeginCount);
        return excelWorkBook;
    }

    public static <T> List<T> generateListFromFile(MultipartFile file, Class<T> cls) throws InstantiationException, IllegalAccessException {
        Workbook workbook = getWorkBookFromFile(file);
        return generateListFromExcel(workbook, cls);
    }

    private static Workbook getWorkBookFromFile(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        Workbook workbook = null;
        if (StringUtils.isNotEmpty(fileName)) {
            try {
                InputStream is = file.getInputStream();
                if (fileName.endsWith(XLS_FILE_SUFFIX_2003)) {
                    workbook = new HSSFWorkbook(is);
                } else if (fileName.endsWith(XLS_FILE_SUFFIX_2007)) {
                    workbook = new XSSFWorkbook(is);
                }
            } catch (IOException e) {
                LOGGER.error("文件解析错误 {}", e.getMessage());
                e.printStackTrace();
            }
        }
        return workbook;
    }

    public static <T> List<T> generateListFromExcel(Workbook workbook, Class<T> cls) throws IllegalAccessException, InstantiationException {
        List<T> resultList = new ArrayList<>();
        if (workbook == null) {
            throw new RuntimeException("文本不能为空");
        }
        Sheet sheet = workbook.getSheetAt(0);
        if (sheet == null) {
            throw new RuntimeException("找不到sheet");
        }
        BaseExcelBook baseExcelBook = getExcelBook(cls);
        List<BaseExcelColumn> columnList = baseExcelBook.getColumnList();
        T object;
        for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
            if (i < baseExcelBook.getImportRowBeginCount() - 1) {
                continue;
            }
            Row row = sheet.getRow(i);
            object = cls.newInstance();
            for (BaseExcelColumn column : columnList) {
                int sort = column.getSort();
                String text = column.getText();
                Field field = column.getField();
                String fieldName = column.getFieldName();
                if (INDEX_FIELD_NAME.equalsIgnoreCase(fieldName)) {
                    continue;
                }
                if (row.getLastCellNum() < sort) {
                    throw new RuntimeException(" 列：" + text + " 应在第" + (sort + 1) + "列，请核对Excel文件与模板！");
                }
                Cell cell = row.getCell(sort);
                if (cell == null) {
                    continue;
                }
                Class fieldClass = field.getType();
                Object value = null;
                if (fieldClass.equals(String.class)) {
                    switch (cell.getCellType()) {
                        case NUMERIC:
                            Double doubleValue =cell.getNumericCellValue();
                            value = String.valueOf(doubleValue.intValue());
                            break;
                        case STRING:
                            value = cell.getStringCellValue();
                            break;
                        default:
                            value = cell.getStringCellValue();
                            break;
                    }
                } else if (fieldClass.equals(Integer.class) || fieldClass.equals(Short.class)) {
                    if (cell.getCellType() == CellType.NUMERIC) {
                        Double doubleValue =cell.getNumericCellValue();
                        value = doubleValue.intValue();
                    } else {
                        value = Integer.valueOf(cell.getStringCellValue());
                    }
                } else if (fieldClass.equals(Date.class)) {
                    value = cell.getDateCellValue();
                } else if (fieldClass.equals(Boolean.class)) {
                    value = cell.getBooleanCellValue();
                }
                field.setAccessible(true);
                field.set(object, value);
            }
            resultList.add(object);
        }
        return resultList;
    }

    /**
     * 生成标题
     *
     * @param excelWorkBook
     */
    private static void initTitle(EntityExcelWorkBook excelWorkBook) {
        Class<?> cls = excelWorkBook.getCls();
        BaseExcelBook baseExcelBook = getExcelBook(cls);
        List<BaseExcelColumn> columnList = baseExcelBook.getColumnList();
        XSSFSheet sheet = excelWorkBook.getSheet();
        Row titleRow = sheet.createRow(0);
        titleRow.setHeight(TITLE_ROW_HEIGHT);
        Cell titleCell = titleRow.createCell(0);
        titleCell.setCellStyle(getTitleStyle(excelWorkBook));
        titleCell.setCellValue(excelWorkBook.getTitle());
        if (columnList.size() > 0) {
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, columnList.size() - 1));
        }
    }

    /**
     * 生成列字段名
     *
     * @param excelWorkBook
     */
    private static void initColumnTitle(EntityExcelWorkBook excelWorkBook) {
        Class<?> cls = excelWorkBook.getCls();
        BaseExcelBook baseExcelBook = getExcelBook(cls);
        List<BaseExcelColumn> columnList = baseExcelBook.getColumnList();
        XSSFSheet sheet = excelWorkBook.getSheet();
        Row colTextRow = sheet.createRow(1);
        colTextRow.setHeight(COLUMN_TITLE_ROW_HEIGHT);
        for (int i = 0; i < columnList.size(); i++) {
            BaseExcelColumn baseExcelColumn = columnList.get(i);
            sheet.setColumnWidth(i, baseExcelColumn.getColWidth());
            Cell colTextCell = colTextRow.createCell(i);
            colTextCell.setCellStyle(getColumnTitleStyle(excelWorkBook));
            colTextCell.setCellValue(baseExcelColumn.getText());
        }
    }

    /**
     * 生成列值
     *
     * @param excelWorkBook
     */
    private static void initColumnValue(EntityExcelWorkBook excelWorkBook, List list) {
        Class<?> cls = excelWorkBook.getCls();
        BaseExcelBook baseExcelBook = getExcelBook(cls);
        List<BaseExcelColumn> columnList = baseExcelBook.getColumnList();
        XSSFSheet sheet = excelWorkBook.getSheet();
        for (int i = 0; i < list.size(); i++) {
            Object obj = list.get(i);
            Row rowTemp = sheet.createRow(i + TITLE_COUNT);
            for (int j = 0; j < columnList.size(); j++) {
                BaseExcelColumn baseExcelColumn = columnList.get(j);
                String fieldName = baseExcelColumn.getFieldName();
                Cell cellTemp = rowTemp.createCell(j);
                cellTemp.setCellStyle(getColumnStyle(excelWorkBook));
                String value;
                if (fieldName.equalsIgnoreCase(INDEX_FIELD_NAME)) {
                    value = j + 1 + "";
                } else {
                    String methodName = baseExcelColumn.getMethodName();
                    value = getFormatValue(obj, methodName);
                }
                cellTemp.setCellValue(value);
            }
        }
    }

    private static XSSFCellStyle getDefaultStyle(EntityExcelWorkBook excelWorkBook) {
        XSSFWorkbook wb = excelWorkBook.getWorkbook();
        XSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        return style;
    }

    private static XSSFCellStyle getTitleStyle(EntityExcelWorkBook excelWorkBook) {
        XSSFWorkbook wb = excelWorkBook.getWorkbook();
        XSSFCellStyle style = getDefaultStyle(excelWorkBook);
        XSSFFont font = wb.createFont();
        font.setFontName(TITLE_FONT_NAME);
        font.setFontHeightInPoints((short) 16);
        font.setBold(true);
        style.setFont(font);
        return style;
    }

    private static XSSFCellStyle getColumnTitleStyle(EntityExcelWorkBook excelWorkBook) {
        XSSFWorkbook wb = excelWorkBook.getWorkbook();
        XSSFCellStyle style = getDefaultStyle(excelWorkBook);
        XSSFFont font = wb.createFont();
        font.setFontName(COLUMN_TITLE_FONT_NAME);
        font.setFontHeightInPoints((short) 12);
        style.setFont(font);
        return style;
    }

    private static XSSFCellStyle getColumnStyle(EntityExcelWorkBook excelWorkBook) {
        XSSFWorkbook wb = excelWorkBook.getWorkbook();
        XSSFCellStyle style = getDefaultStyle(excelWorkBook);
        XSSFFont font = wb.createFont();
        font.setFontName(COLUMN_VALUE_FONT_NAME);
        font.setFontHeightInPoints((short) 10);
        style.setFont(font);
        style.setWrapText(true);
        return style;
    }

    private static BaseExcelBook getExcelBook(Class<?> cls) {
        if (EXCEL_CLASS_MAP.containsKey(cls)) {
            return EXCEL_CLASS_MAP.get(cls);
        }
        return initClass(cls);
    }

    private static BaseExcelBook initClass(Class<?> cls) {
        BaseExcelBook baseExcelBook = getBaseExcelBook(cls);
        EXCEL_CLASS_MAP.put(cls, baseExcelBook);
        return baseExcelBook;
    }

    private static BaseExcelBook getBaseExcelBook(Class<?> cls) {
        String title = DEFAULT_TITLE;
        int importRowBeginCount = 3;
        Annotation[] annotations = cls.getAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation instanceof ExcelBook) {
                title = ((ExcelBook) annotation).title();
                importRowBeginCount = ((ExcelBook) annotation).importRowBeginCount();
                break;
            }
        }
        List<BaseExcelColumn> columnList = getExcelColumns(cls);
        return new BaseExcelBook(title, importRowBeginCount, columnList);
    }

    private static boolean getShowIndex(Class<?> cls) {
        boolean showIndex = false;
        Annotation[] annotations = cls.getAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation instanceof ExcelBook) {
                showIndex = ((ExcelBook) annotation).showIndex();
                break;
            }
        }
        return showIndex;
    }

    /**
     * 获取类所有 ExcelColumn 列信息
     *
     * @param cls
     * @return
     */
    private static List<BaseExcelColumn> getExcelColumns(Class<?> cls) {
        List<BaseExcelColumn> columnList = new ArrayList<>();
        if (getShowIndex(cls)) {
            BaseExcelColumn column = new BaseExcelColumn(INDEX_FIELD_NAME, null, INDEX_FIELD_TITLE,
                    5 * 256, (short) 1, 0, null);
            columnList.add(column);
        }
        List<Class<?>> superClasses = getSupperClasses(cls);
        for (Class<?> clsTmp : superClasses) {
            for (Field field : clsTmp.getDeclaredFields()) {
                Annotation[] annotations = field.getAnnotations();
                for (Annotation annotation : annotations) {
                    String fieldName = field.getName();
                    String methodName = getGetMethodName(fieldName);
                    if (StringUtils.isNotEmpty(fieldName)) {
                        if (annotation instanceof ExcelColumn) {
                            ExcelColumn excelColumn = (ExcelColumn) annotation;
                            String text = excelColumn.text();
                            int colWidth = excelColumn.colWidth();
                            short color = excelColumn.color();
                            int sort = excelColumn.sort();
                            BaseExcelColumn column = new BaseExcelColumn(fieldName, methodName, text, colWidth, color, sort, field);
                            columnList.add(column);
                        }
                    }
                }
            }
        }
        return columnList.stream().sorted(Comparator.comparing(BaseExcelColumn::getSort)).collect(Collectors.toList());
    }

    /**
     * 根据属性拼接get方法
     *
     * @param fieldName
     * @return
     */
    private static String getGetMethodName(String fieldName) {
        char[] chars = fieldName.toCharArray();
        chars[0] = (char) (chars[0] - 32);
        return "get" + new String(chars);
    }

    /**
     * 获取格式化后的属性值
     *
     * @param object
     * @param methodName
     * @return
     */
    private static String getFormatValue(Object object, String methodName) {
        Object value = getValue(object, methodName);
        if (value == null) {
            return "";
        }
        return getValue(object, methodName).toString();
    }

    /**
     * 通过方法名获取对象属性的值
     *
     * @param object
     * @param methodName
     * @return
     */
    private static Object getValue(Object object, String methodName) {
        Object value = null;
        if (StringUtils.isNotEmpty(methodName)) {
            try {
                Method method = object.getClass().getMethod(methodName);
                value = method.invoke(object);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return value;
    }

    /**
     * 获取类 所有父类 包括自己
     *
     * @param cls
     * @return
     */
    private static List<Class<?>> getSupperClasses(Class<?> cls) {
        List<Class<?>> classList = new ArrayList<>();
        classList.add(cls);
        Class<?> superClass = cls.getSuperclass();
        while (superClass != null) {
            if ("java.lang.Object".equals(superClass.getSimpleName())) {
                break;
            }
            classList.add(superClass);
            superClass = superClass.getSuperclass();
        }
        return classList;
    }

}
