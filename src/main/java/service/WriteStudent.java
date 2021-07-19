/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import model.Student;
import pattern.WriteExcel;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Mypc
 */
public class WriteStudent implements WriteExcel<Student> {

    final int COLUMN_INDEX_MASV = 0;
    final int COLUMN_INDEX_HOTEN = 1;
    final int COLUMN_INDEX_EMAIL = 2;
    final int COLUMN_INDEX_SDT = 3;
    final int COLUMN_INDEX_GIOITINH = 4;
    final int COLUMN_INDEX_DIACHI = 5;
    final int COLUMN_INDEX_HINH = 6;

    public WriteStudent(List<Student> lst, String path) {
        writeExcel(lst, path);
    }

    @Override
    public void writeExcel(List<Student> lst, String path) {
        // Create Workbook
        Workbook workbook = getWorkbook(path);
        // Create sheet
        Sheet sheet = workbook.createSheet("Student"); // Create sheet with sheet name
        int rowIndex = 0;
        // Write header
        writeHeader(sheet, rowIndex);
        // Write data
        rowIndex++;
        for (Student st : lst) {
            // Create row
            Row row = sheet.createRow(rowIndex);
            // Write data on row
            writeList(st, row);
            rowIndex++;
        }
        int numberOfColumn = sheet.getRow(0).getPhysicalNumberOfCells();
        autosizeColumn(sheet, numberOfColumn);

        // Create file excel
        createOutputFile(workbook, path);
        System.out.println("Done!!!");
    }

    @Override
    public Workbook getWorkbook(String path) {
        Workbook workbook = null;

        if (path.endsWith("xlsx")) {
            workbook = new XSSFWorkbook();
        } else if (path.endsWith("xls")) {
            workbook = new HSSFWorkbook();
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }

        return workbook;
    }

    @Override
    public void writeHeader(Sheet sheet, int rowIndex) {
        // create CellStyle
        CellStyle cellStyle = createStyleForHeader(sheet);

        // Create row
        Row row = sheet.createRow(rowIndex);

        // Create cells
        Cell cell = row.createCell(COLUMN_INDEX_MASV);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Mã SV");

        cell = row.createCell(COLUMN_INDEX_HOTEN);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Họ tên");

        cell = row.createCell(COLUMN_INDEX_EMAIL);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Email");

        cell = row.createCell(COLUMN_INDEX_SDT);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("SDT");

        cell = row.createCell(COLUMN_INDEX_GIOITINH);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Giới tính");
        
        cell = row.createCell(COLUMN_INDEX_DIACHI);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Địa chỉ");
        
        cell = row.createCell(COLUMN_INDEX_HINH);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Hình");
    }

    @Override
    public void writeList(Student t, Row row) {
        Cell cell = row.createCell(COLUMN_INDEX_MASV);
        cell.setCellValue(t.getMasv());

        cell = row.createCell(COLUMN_INDEX_HOTEN);
        cell.setCellValue(t.getHoTen());

        cell = row.createCell(COLUMN_INDEX_EMAIL);
        cell.setCellValue(t.getEmail());

        cell = row.createCell(COLUMN_INDEX_SDT);
        cell.setCellValue(t.getSdt());

        cell = row.createCell(COLUMN_INDEX_GIOITINH);
        cell.setCellValue(t.getGioiTinh());
        
        cell = row.createCell(COLUMN_INDEX_DIACHI);
        cell.setCellValue(t.getDiaChi());

        cell = row.createCell(COLUMN_INDEX_HINH);
        cell.setCellValue(t.getHinh());
    }

    @Override
    public CellStyle createStyleForHeader(Sheet sheet) {
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman");
        font.setBold(true);
        font.setFontHeightInPoints((short) 14);
        font.setColor(IndexedColors.WHITE.getIndex());
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        return cellStyle;
    }

    @Override
    public void autosizeColumn(Sheet sheet, int lastColumn) {
        for (int columnIndex = 0; columnIndex < lastColumn; columnIndex++) {
            sheet.autoSizeColumn(columnIndex);
        }
    }

    @Override
    public void createOutputFile(Workbook workbook, String excelFilePath) {
        try (OutputStream os = new FileOutputStream(excelFilePath)) {
            workbook.write(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
