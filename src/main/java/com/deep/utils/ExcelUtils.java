package com.deep.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtils {

    private Workbook workbook;
    private Sheet sheet;

    // ── Constructor — opens excel file ──
    public ExcelUtils(String filePath, String sheetName) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheet(sheetName);
    }

    // ── Get total rows ──
    public int getRowCount() {
        return sheet.getLastRowNum();
    }

    // ── Get total columns ──
    public int getColumnCount() {
        return sheet.getRow(0).getLastCellNum();
    }

    // ── Get cell data ──
    public String getCellData(int rowNum, int colNum) {
        Row row = sheet.getRow(rowNum);
        if (row == null) return "";

        Cell cell = row.getCell(colNum);
        if (cell == null) return "";

        // Handle different cell types
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf((int) cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            default:
                return "";
        }
    }

    // ── Get all data as 2D array ──
    public String[][] getAllData() {
        int rows = getRowCount();
        int cols = getColumnCount();

        // rows-1 because row 0 is header
        String[][] data = new String[rows][cols];

        for (int i = 1; i <= rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i - 1][j] = getCellData(i, j);
            }
        }
        return data;
    }

    // ── Close workbook ──
    public void closeWorkbook() throws IOException {
        if (workbook != null) {
            workbook.close();
        }
    }
}