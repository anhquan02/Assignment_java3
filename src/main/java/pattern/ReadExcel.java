/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pattern;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author Mypc
 */
public interface ReadExcel<T> {      
    public List<T> readExcel(String excelFilePath);
    public Workbook getWorkbook(InputStream inputStream, String excelFilePath);
    public Object getCellValue(Cell cell);
    
}
