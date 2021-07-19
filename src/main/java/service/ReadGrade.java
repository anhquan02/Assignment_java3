/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import model.Grade;
import pattern.ReadExcel;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Mypc
 */
public class ReadGrade implements ReadExcel<Grade> {

    final int COLUMN_INDEX_MASV = 0;
    final int COLUMN_INDEX_HOTEN = 1;
    final int COLUMN_INDEX_TIENGANH = 2;
    final int COLUMN_INDEX_TINHOC = 3;
    final int COLUMN_INDEX_GDTC = 4;

    public ReadGrade() {
    }


    @Override
    public List<Grade> readExcel(String excelFilePath) {
        try {
            List<Grade> listBooks = new ArrayList<>();
            // Get file
            InputStream inputStream = new FileInputStream(new File(excelFilePath));
            // Get workbook
            Workbook workbook = getWorkbook(inputStream, excelFilePath);
            // Get sheet
            Sheet sheet = workbook.getSheetAt(0);
            // Get all rows
            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row nextRow = iterator.next();
                if (nextRow.getRowNum() == 0) {
                    // Ignore header
                    continue;
                }
                // Get all cells
                Iterator<Cell> cellIterator = nextRow.cellIterator();
                // Read cells and set value for book object
                Grade gr = new Grade();
                while (cellIterator.hasNext()) {
                    //Read cell
                    Cell cell = cellIterator.next();
                    Object cellValue = getCellValue(cell);
                    if (cellValue == null || cellValue.toString().isEmpty()) {
                        continue;
                    }
                    // Set value for book object
                    int columnIndex = cell.getColumnIndex();
                    switch (columnIndex) {
                        case COLUMN_INDEX_MASV:
                            gr.setMasv((String) getCellValue(cell));
                            break;
                        case COLUMN_INDEX_HOTEN:
                            gr.setHoTen((String) getCellValue(cell));
                            break;
                        case COLUMN_INDEX_TIENGANH:
                            gr.setTiengAnh(new BigDecimal((double) cellValue).floatValue());
                            break;
                        case COLUMN_INDEX_TINHOC:
                            gr.setTinHoc(new BigDecimal((double) cellValue).floatValue());
                            break;
                        case COLUMN_INDEX_GDTC:
                            gr.setGDTC(new BigDecimal((double) cellValue).floatValue());
                            break;
                        default:
                            break;
                    }
                }
                listBooks.add(gr);
            }
            workbook.close();
            inputStream.close();
            return listBooks;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Workbook getWorkbook(InputStream inputStream, String excelFilePath) {
        Workbook workbook = null;
        try {
            if (excelFilePath.endsWith("xlsx")) {
                workbook = new XSSFWorkbook(inputStream);
            } else if (excelFilePath.endsWith("xls")) {
                workbook = new HSSFWorkbook(inputStream);
            } else {
                throw new IllegalArgumentException("The specified file is not Excel file");
            }
            return workbook;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Object getCellValue(Cell cell) {
        CellType cellType = cell.getCellTypeEnum();
        Object cellValue = null;
        switch (cellType) {
            case BOOLEAN:
                cellValue = cell.getBooleanCellValue();
                break;
            case FORMULA:
                Workbook workbook = cell.getSheet().getWorkbook();
                FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
                cellValue = evaluator.evaluate(cell).getNumberValue();
                break;
            case NUMERIC:
                cellValue = cell.getNumericCellValue();
                break;
            case STRING:
                cellValue = cell.getStringCellValue();
                break;
            case _NONE:
            case BLANK:
            case ERROR:
                break;
            default:
                break;
        }

        return cellValue;
    }

}
