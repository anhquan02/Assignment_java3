/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlydiem;

import model.Grade;
import pattern.WriteExcel;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.BuiltinFormats;
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
public class WriteGrade implements WriteExcel<Grade> {

    final int COLUMN_INDEX_MASV = 0;
    final int COLUMN_INDEX_HOTEN = 1;
    final int COLUMN_INDEX_TIENGANH = 2;
    final int COLUMN_INDEX_TINHOC = 3;
    final int COLUMN_INDEX_GDTC = 4;
    private static CellStyle cellStyleFormatNumber = null;

    public WriteGrade(List<Grade> lst, String path) {
        writeExcel(lst, path);
    }

    @Override
    public void writeExcel(List<Grade> lst, String path) {
        // Create Workbook
        Workbook workbook = getWorkbook(path);
        // Create sheet
        Sheet sheet = workbook.createSheet("Grade"); // Create sheet with sheet name
        int rowIndex = 0;
        // Write header
        writeHeader(sheet, rowIndex);
        // Write data
        rowIndex++;
        for (Grade book : lst) {
            // Create row
            Row row = sheet.createRow(rowIndex);
            // Write data on row
            writeList(book, row);
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

        cell = row.createCell(COLUMN_INDEX_TIENGANH);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Tiếng anh");

        cell = row.createCell(COLUMN_INDEX_TINHOC);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Tin học");

        cell = row.createCell(COLUMN_INDEX_GDTC);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("GDTC");
    }

    @Override
    public void writeList(Grade t, Row row) {
        if (cellStyleFormatNumber == null) {
            // Format number
            short format = (short) BuiltinFormats.getBuiltinFormat("#,##0");
            // DataFormat df = workbook.createDataFormat();
            // short format = df.getFormat("#,##0");

            //Create CellStyle
            Workbook workbook = row.getSheet().getWorkbook();
            cellStyleFormatNumber = workbook.createCellStyle();
            cellStyleFormatNumber.setDataFormat(format);
        }
        Cell cell = row.createCell(COLUMN_INDEX_MASV);
        cell.setCellValue(t.getMasv());

        cell = row.createCell(COLUMN_INDEX_HOTEN);
        cell.setCellValue(t.getHoTen());

        cell = row.createCell(COLUMN_INDEX_TIENGANH);
        cell.setCellValue(t.getTiengAnh());
        cell.setCellStyle(cellStyleFormatNumber);

        cell = row.createCell(COLUMN_INDEX_TINHOC);
        cell.setCellValue(t.getTinHoc());
        cell.setCellStyle(cellStyleFormatNumber);

        cell = row.createCell(COLUMN_INDEX_GDTC);
        cell.setCellValue(t.getGDTC());
        cell.setCellStyle(cellStyleFormatNumber);

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
