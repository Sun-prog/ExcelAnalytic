import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

public class Parser {

    public static void parse(String name) {
        //OPCPackage fs;

        InputStream in = null;
        HSSFWorkbook wb = null;
        try {
           // in = new FileInputStream(name);
           // wb = new HSSFWorkbook(in);
             wb = new HSSFWorkbook(new FileInputStream(name));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Sheet sheet = wb.getSheetAt(0);
        Iterator<Row> it = sheet.iterator();
        while (it.hasNext()) {

                Row row = it.next();
                //CountryDAO.list.add(new Country(row.getCell(0).getStringCellValue(),new Year(2012)));
                YearDAO.list.add(new Year(row.getCell(0).getStringCellValue(),2012, row.getCell(29).getNumericCellValue(),row.getCell(28).getNumericCellValue()));
            //YearDAO.list.add(new Year(row.getCell(0).getStringCellValue(),2012, row.getCell(30).getNumericCellValue(),row.getCell(30).getNumericCellValue()));
            YearDAO.list.add(new Year(row.getCell(0).getStringCellValue(),2013, row.getCell(26).getNumericCellValue(),row.getCell(25).getNumericCellValue()));
            YearDAO.list.add(new Year(row.getCell(0).getStringCellValue(),2014, row.getCell(2).getNumericCellValue(),row.getCell(1).getNumericCellValue()));
            YearDAO.list.add(new Year(row.getCell(0).getStringCellValue(),2015, row.getCell(5).getNumericCellValue(),row.getCell(4).getNumericCellValue()));
            YearDAO.list.add(new Year(row.getCell(0).getStringCellValue(),2016, row.getCell(8).getNumericCellValue(),row.getCell(7).getNumericCellValue()));
            YearDAO.list.add(new Year(row.getCell(0).getStringCellValue(),2017, row.getCell(11).getNumericCellValue(),row.getCell(10).getNumericCellValue()));
            YearDAO.list.add(new Year(row.getCell(0).getStringCellValue(),2018, row.getCell(14).getNumericCellValue(),row.getCell(13).getNumericCellValue()));
            YearDAO.list.add(new Year(row.getCell(0).getStringCellValue(),2019, row.getCell(17).getNumericCellValue(),row.getCell(16).getNumericCellValue()));
            YearDAO.list.add(new Year(row.getCell(0).getStringCellValue(),2020, row.getCell(20).getNumericCellValue(),row.getCell(19).getNumericCellValue()));
            YearDAO.list.add(new Year(row.getCell(0).getStringCellValue(),2021, row.getCell(23).getNumericCellValue(),row.getCell(22).getNumericCellValue()));

        }

    }

}
