/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import model.Student;
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
import model.Grade;

/**
 *
 * @author Mypc
 */
public class ReadStudent implements ReadExcel<Student> {

    final int COLUMN_INDEX_MASV = 0;
    final int COLUMN_INDEX_HOTEN = 1;
    final int COLUMN_INDEX_EMAIL = 2;
    final int COLUMN_INDEX_SDT = 3;
    final int COLUMN_INDEX_GIOITINH = 4;
    final int COLUMN_INDEX_DIACHI = 5;
    final int COLUMN_INDEX_HINH = 6;

    public ReadStudent() {
    }

    @Override
    public List<Student> readExcel(String excelFilePath) {
        try {
            List<Student> listBooks = new ArrayList<>();
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
                Student st = new Student();
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
                            st.setMasv((String) getCellValue(cell));
                            break;
                        case COLUMN_INDEX_HOTEN:
                            st.setHoTen((String) getCellValue(cell));
                            break;
                        case COLUMN_INDEX_EMAIL:
                            st.setEmail((String) getCellValue(cell));
                            break;
                        case COLUMN_INDEX_SDT:
                            st.setSdt((String) getCellValue(cell));
                            break;
                        case COLUMN_INDEX_GIOITINH:
                            st.setGioiTinh((String) getCellValue(cell));
                            break;
                        case COLUMN_INDEX_DIACHI:
                            st.setDiaChi((String) getCellValue(cell));
                            break;
                        case COLUMN_INDEX_HINH:
                            st.setHinh((String) getCellValue(cell));
                            break;
                        default:
                            break;
                    }
                }
                listBooks.add(st);
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
