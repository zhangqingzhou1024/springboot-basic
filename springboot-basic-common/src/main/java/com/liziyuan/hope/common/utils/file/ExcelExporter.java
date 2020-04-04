//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.liziyuan.hope.common.utils.file;

import com.liziyuan.hope.common.utils.DateHelper;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.util.Assert;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

public class ExcelExporter {
    private final String DEFAULT_SHEET_NAME = "Sheet1";
    private String sheetName = "Sheet1";
    private String[] cellNames;
    private String[] fieldNames;
    private OutputStream outputStream;
    private final SXSSFWorkbook workbook;
    private final Sheet sheet;
    private final CellStyle cellStyle;
    private final DataFormat dataFormat;
    private static final Log LOG = LogFactory.getLog(ExcelExporter.class);

    public ExcelExporter(String sheetName, String[] cellNames, String[] fieldNames, OutputStream outputStream) throws IllegalArgumentException {
        this.setSheetName(sheetName);
        this.setCellNames(cellNames);
        this.setFieldNames(fieldNames);
        this.setOutputStream(outputStream);
        this.workbook = new SXSSFWorkbook();
        this.sheet = this.workbook.createSheet(this.sheetName);
        this.cellStyle = this.workbook.createCellStyle();
        this.dataFormat = this.workbook.createDataFormat();
        this.initSheetHead();
    }

    public <T> ExcelExporter append(Collection<T> datas) throws IOException {
        Iterator var2 = datas.iterator();

        while (var2.hasNext()) {
            T data = (T) var2.next();
            Row dataRow = this.sheet.createRow(this.sheet.getLastRowNum() + 1);

            for (int i = 0; i < this.fieldNames.length; ++i) {
                Cell cell = dataRow.createCell(i);
                Object value = this.getFiledValue(data, this.fieldNames[i]);
                this.setCellValue(cell, value);
            }
        }

        return this;
    }

    public void write() throws IOException {
        try {
            this.workbook.write(this.outputStream);
            this.workbook.dispose();
        } finally {
            if (null != this.outputStream) {
                this.outputStream.close();
            }

        }

    }

    private void initSheetHead() {
        Row headRow = this.sheet.createRow(0);

        for (int i = 0; i < this.cellNames.length; ++i) {
            headRow.createCell(i).setCellValue(this.cellNames[i]);
        }

    }

    private void setCellValue(Cell cell, Object value) {
        if (null != cell) {
            if (null == value) {
                cell.setCellValue("");
            } else {
                Double cellValue;
                if (!(value instanceof Double) && !(value instanceof Float)) {
                    if (value instanceof Long) {
                        if (value.toString().length() > 11) {
                            cell.setCellValue(ObjectUtils.toString(value));
                        } else {
                            cell.setCellType(0);
                            cellValue = Double.parseDouble(value.toString());
                            cell.setCellValue(cellValue);
                        }
                    } else if (!(value instanceof Integer) && !(value instanceof Short)) {
                        if (value instanceof Date) {
                            String cellValue01 = DateHelper.formatDateTime((Date) value);
                            cell.setCellValue(cellValue01);
                        } else if (value instanceof Boolean) {
                            Boolean booleanValue = (Boolean) value;
                            String cellValue02 = booleanValue ? "是" : "否";
                            cell.setCellValue(cellValue02);
                        } else {
                            cell.setCellValue(ObjectUtils.toString(value));
                        }
                    } else {
                        cell.setCellType(0);
                        cellValue = Double.parseDouble(value.toString());
                        cell.setCellValue(cellValue);
                    }
                } else if (value.toString().length() > 11) {
                    cell.setCellValue(ObjectUtils.toString(value));
                } else {
                    cell.setCellType(0);
                    this.cellStyle.setDataFormat(this.dataFormat.getFormat("0.00"));
                    cell.setCellStyle(this.cellStyle);
                    cellValue = Double.parseDouble(value.toString());
                    cell.setCellValue(cellValue);
                }
            }

        }
    }

    private <T> Object getFiledValue(T data, String fieldName) {
        Object value = null;

        try {
            Field field = data.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            value = field.get(data);
        } catch (Exception var5) {
            LOG.warn("getFiledValue fail,", var5);
        }

        return value;
    }

    private void setSheetName(String sheetName) {
        if (null != sheetName) {
            this.sheetName = sheetName;
        } else {
            LOG.debug("using default sheetName 'Sheet1'");
        }

    }

    private void setCellNames(String[] cellNames) {
        Assert.notNull(cellNames, "cellNames must be not null");
        this.cellNames = cellNames;
    }

    private void setFieldNames(String[] fieldNames) {
        Assert.notNull(fieldNames, "fieldNames must be not null");
        Assert.isTrue(this.cellNames.length == fieldNames.length, "fieldNames.length must be equal to cellNames.length");
        this.fieldNames = fieldNames;
    }

    private void setOutputStream(OutputStream outputStream) {
        Assert.notNull(outputStream, "outputStream must be not null");
        this.outputStream = outputStream;
    }

    public String getSheetName() {
        return this.sheetName;
    }

    public String[] getFieldNames() {
        return this.fieldNames;
    }

    public String[] getCellNames() {
        return this.cellNames;
    }
}
