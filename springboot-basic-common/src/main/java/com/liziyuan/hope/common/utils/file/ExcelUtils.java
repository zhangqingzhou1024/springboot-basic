
package com.liziyuan.hope.common.utils.file;

import com.liziyuan.hope.common.utils.DateHelper;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.util.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ExcelUtils {
    public ExcelUtils() {
    }

    public static <T> void exportFile(String sheetName, String[] headerNames, List<T> dataList, String[] fieldNames, int[] cellTypes, OutputStream outputStream) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException, IOException {
        Assert.notNull(sheetName, "sheetName must be not null");
        Assert.notNull(outputStream, "outputStream must be not null");
        Assert.notNull(headerNames, "headerNames must be not null");
        Assert.notNull(fieldNames, "fieldNames must be not null");
        Assert.isTrue(headerNames.length == fieldNames.length, "headerNames.length must be equal to fieldNames.length  ");
        if (ArrayUtils.isNotEmpty(cellTypes)) {
            Assert.isTrue(cellTypes.length == fieldNames.length, "cellTypes.length must be equal to fieldNames.length  ");
        } else {
            cellTypes = new int[fieldNames.length];

            for (int i = 0; i < cellTypes.length; ++i) {
                cellTypes[i] = 1;
            }
        }

        Assert.notNull(dataList, "dataList must be not null");
        Assert.notNull(outputStream, "outputStream must be not null");
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
        XSSFCellStyle cellStyle = xssfWorkbook.createCellStyle();
        XSSFCellStyle numericCellStyle = xssfWorkbook.createCellStyle();
        XSSFCellStyle numCellStyle = xssfWorkbook.createCellStyle();
        XSSFCellStyle decimalCellStyle_1 = xssfWorkbook.createCellStyle();
        XSSFCellStyle decimalCellStyle_2 = xssfWorkbook.createCellStyle();
        XSSFCellStyle decimalCellStyle_3 = xssfWorkbook.createCellStyle();
        numericCellStyle.setDataFormat(xssfWorkbook.createDataFormat().getFormat("#,##0.00"));
        numCellStyle.setDataFormat(xssfWorkbook.createDataFormat().getFormat("#,#0"));
        decimalCellStyle_1.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.0"));
        decimalCellStyle_2.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));
        decimalCellStyle_3.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.000"));
        XSSFSheet sheet = xssfWorkbook.createSheet(sheetName);
        XSSFCellStyle headCellStyle = xssfWorkbook.createCellStyle();
        headCellStyle.setAlignment((short) 2);
        XSSFFont font = xssfWorkbook.createFont();
        font.setColor((short) 8);
        font.setFontHeightInPoints((short) 13);
        font.setBoldweight((short) 700);
        headCellStyle.setFont(font);
        headCellStyle.setFillPattern((short) 1);
        headCellStyle.setFillForegroundColor((short) 22);
        headCellStyle.setBorderBottom((short) 1);
        headCellStyle.setBorderLeft((short) 1);
        headCellStyle.setBorderTop((short) 1);
        headCellStyle.setBorderRight((short) 1);
        XSSFRow headRow = sheet.createRow(0);

        for (int i = 0; i < headerNames.length; ++i) {
            sheet.setColumnWidth(i, 6400);
            XSSFCell cell = headRow.createCell(i);
            cell.setCellValue(headerNames[i]);
            cell.setCellStyle(headCellStyle);
        }

        Iterator var28 = dataList.iterator();

        while (var28.hasNext()) {
            T data = (T) var28.next();
            XSSFRow dataRow = sheet.createRow(sheet.getLastRowNum() + 1);

            for (int i = 0; i < fieldNames.length; ++i) {
                String fieldName = fieldNames[i];
                String cellValue = GetFiledValue(data, fieldName);
                XSSFCell cell = dataRow.createCell(i);
                if (cellTypes[i] == 0) {
                    cell.setCellType(cellTypes[i]);
                    if (StringUtils.isNotEmpty(cellValue)) {
                        cell.setCellValue(Double.parseDouble(cellValue));
                    }

                    cell.setCellStyle(numericCellStyle);
                } else if (cellTypes[i] == 6) {
                    cell.setCellType(0);
                    if (StringUtils.isNotEmpty(cellValue)) {
                        cell.setCellValue((double) Integer.parseInt(cellValue));
                    }

                    cell.setCellStyle(numCellStyle);
                } else if (cellTypes[i] == 7) {
                    cell.setCellType(0);
                    if (StringUtils.isNotEmpty(cellValue)) {
                        cell.setCellValue(Double.parseDouble(cellValue));
                    }

                    cell.setCellStyle(decimalCellStyle_1);
                } else if (cellTypes[i] == 8) {
                    cell.setCellType(0);
                    if (StringUtils.isNotEmpty(cellValue)) {
                        cell.setCellValue(Double.parseDouble(cellValue));
                    }

                    cell.setCellStyle(decimalCellStyle_2);
                } else if (cellTypes[i] == 9) {
                    cell.setCellType(0);
                    if (StringUtils.isNotEmpty(cellValue)) {
                        cell.setCellValue(Double.parseDouble(cellValue));
                    }

                    cell.setCellStyle(decimalCellStyle_3);
                } else {
                    cell.setCellType(cellTypes[i]);
                    cell.setCellValue(cellValue);
                }
            }
        }

        try {
            xssfWorkbook.write(outputStream);
        } finally {
            if (null != outputStream) {
                outputStream.close();
            }

        }

    }

    private static <T> String GetFiledValue(T data, String fieldName) {
        String cellValue = "";

        try {
            Field field = getField(data, fieldName);
            if (field == null) {
                return cellValue;
            }

            field.setAccessible(true);
            Object value = field.get(data);
            if (null != value) {
                if (value instanceof Date) {
                    value = DateHelper.formatDateTime((Date) value);
                }

                cellValue = ObjectUtils.toString(value);
            }
        } catch (Exception var5) {
        }

        return cellValue;
    }

    private static <T> Field getField(T data, String fieldName) {
        Field field = null;
        Class clazz = data.getClass();

        while (clazz != null) {
            try {
                field = clazz.getDeclaredField(fieldName);
                if (field != null) {
                    return field;
                }
            } catch (Exception var5) {
                clazz = clazz.getSuperclass();
            }
        }

        return field;
    }

    public static <T> List<T> readExcel(InputStream in, String fileName, Class<T> entityClass) throws IOException, IllegalAccessException, InstantiationException {
        return readExcel(in, fileName, entityClass, 0, 1);
    }

    public static <T> List<T> readExcel(InputStream in, String fileName, Class<T> entityClass, int sheetIndex, int startRow) throws IOException, IllegalAccessException, InstantiationException {
        List<T> list = new ArrayList();
        String suffix = fileName.substring(fileName.lastIndexOf("."));

        if (!".xlsx".equals(suffix)) {
            System.out.println("不支持的文件类型！");
            throw new IllegalArgumentException("不支持的文件类型！");
        } else {
            XSSFWorkbook wb = new XSSFWorkbook(in);
            XSSFSheet sheet = wb.getSheetAt(sheetIndex);
            Field[] fields = entityClass.getDeclaredFields();
            ArrayList fieldList = new ArrayList();
            Field[] superFields = fields;
            int rowNum = fields.length;

            int i;
            for (i = 0; i < rowNum; ++i) {
                Field field = superFields[i];
                fieldList.add(field);
            }

            superFields = entityClass.getSuperclass().getDeclaredFields();
            if (ArrayUtils.isNotEmpty(superFields)) {
                Field[] var24 = superFields;
                i = superFields.length;

                for (int var25 = 0; var25 < i; ++var25) {
                    Field field = var24[var25];
                    fieldList.add(field);
                }
            }

            rowNum = sheet.getLastRowNum();

            for (i = startRow; i <= rowNum; ++i) {
                T entity = entityClass.newInstance();
                XSSFRow row = sheet.getRow(i);
                if (!isRowEmpty(row)) {
                    int cellNum = row.getLastCellNum();

                    for (int j = 0; j < cellNum; ++j) {
                        Field field = (Field) fieldList.get(j);
                        Class<?> fieldType = field.getType();
                        field.setAccessible(true);
                        if (getValue(row.getCell(j)) != null) {
                            if (String.class == fieldType) {
                                field.set(entity, getValue(row.getCell(j)));
                            } else if (Integer.TYPE != fieldType && Integer.class != fieldType) {
                                if (Long.TYPE != fieldType && Long.class != fieldType) {
                                    if (Float.TYPE != fieldType && Float.class != fieldType) {
                                        if (Short.TYPE != fieldType && Short.class != fieldType) {
                                            if (Double.TYPE != fieldType && Double.class != fieldType) {
                                                if (Date.class == fieldType) {
                                                    if (row.getCell(j).toString().length() > 0) {
                                                        Date date = row.getCell(j).getDateCellValue();
                                                        field.set(entity, date);
                                                    } else {
                                                        field.set(entity, (Object) null);
                                                    }
                                                } else {
                                                    field.set(entity, true);
                                                }
                                            } else {
                                                field.set(entity, Double.valueOf(row.getCell(j).toString()));
                                            }
                                        } else {
                                            field.set(entity, Short.valueOf(row.getCell(j).toString()));
                                        }
                                    } else {
                                        field.set(entity, Float.valueOf(row.getCell(j).toString()));
                                    }
                                } else {
                                    field.set(entity, Long.valueOf(row.getCell(j).toString()));
                                }
                            } else {
                                field.set(entity, Integer.valueOf(getValue(row.getCell(j))));
                            }
                        }
                    }

                    list.add(entity);
                }
            }

            return list;
        }
    }

    public static boolean isRowEmpty(XSSFRow row) {
        if (row == null) {
            return true;
        } else {
            for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); ++c) {
                Cell cell = row.getCell(c);
                if (cell != null && cell.getCellType() != 3) {
                    return false;
                }
            }

            return true;
        }
    }

    private static String getValue(XSSFCell xssfCell) {
        if (null == xssfCell) {
            return null;
        } else {
            String value = null;
            if (xssfCell.getCellType() == 4) {
                value = String.valueOf(xssfCell.getBooleanCellValue());
            } else if (xssfCell.getCellType() == 0) {
                Object inputValue = null;
                Long longVal = Math.round(xssfCell.getNumericCellValue());
                Double doubleVal = xssfCell.getNumericCellValue();
                if (Double.parseDouble(longVal + ".0") == doubleVal) {
                    inputValue = longVal;
                } else {
                    inputValue = doubleVal;
                }

                DecimalFormat df = new DecimalFormat("#.####");
                value = String.valueOf(df.format(inputValue));
            } else {
                String cellValue = xssfCell.getStringCellValue();
                value = cellValue == null ? null : String.valueOf(cellValue);
            }

            if (value != null) {
                value = value.trim();
            }

            return value;
        }
    }

    public static XSSFSheet setXSSFValidation(XSSFSheet sheet, List<String> dataList, int firstRow, int endRow, int firstCol, int endCol) {
        XSSFDataValidationHelper dvHelper = new XSSFDataValidationHelper(sheet);
        XSSFDataValidationConstraint dvConstraint = (XSSFDataValidationConstraint) dvHelper.createExplicitListConstraint((String[]) dataList.toArray(new String[0]));
        CellRangeAddressList regions = new CellRangeAddressList(firstRow, endRow, firstCol, endCol);
        XSSFDataValidation validation = (XSSFDataValidation) dvHelper.createValidation(dvConstraint, regions);
        sheet.addValidationData(validation);
        return sheet;
    }
}
