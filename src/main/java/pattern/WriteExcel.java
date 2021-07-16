/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pattern;

//import java.awt.List;
import java.util.List;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author Mypc
 */
public interface WriteExcel<T> {
    public void writeExcel(List<T> lst ,String path);
    public Workbook getWorkbook(String path);
    public void writeHeader(Sheet sheet, int rowIndex);
    public void writeList(T t ,Row row);
    public CellStyle createStyleForHeader(Sheet sheet);
    public void autosizeColumn(Sheet sheet, int lastColumn);
    public void createOutputFile(Workbook workbook, String excelFilePath);
}
