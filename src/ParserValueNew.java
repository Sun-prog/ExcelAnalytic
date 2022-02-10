import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.DataFormatter;
//чтение и запись строк - источник https://betacode.net/11259/read-write-excel-file-in-java-using-apache-poi
public class ParserValueNew {

    private static CellType NUMERIC;

    public static List<Research> parse(String name, int year) {
        //OPCPackage fs;

        InputStream in = null;
        HSSFWorkbook wb = null;

        //List<String> tempValueCountry = new ArrayList<String>();
        List<Research> listResearch= new ArrayList<Research>();
        List<Country> listCountry= new ArrayList<>();

        try {
             wb = new HSSFWorkbook(new FileInputStream(name));

//читаем названия стран, записываем в лист
        Sheet sheet = wb.getSheetAt(0);
        Row row = sheet.getRow(0);

        // Get iterator to all cells of current row
        Iterator<Cell> cellIterator = row.cellIterator();
        System.out.println("изначально стран"+listCountry.size());

        while (cellIterator.hasNext()) {

            Cell cell = cellIterator.next();
            if (cell.getStringCellValue()!=null&& !cell.getStringCellValue().trim().isEmpty()) {
                listCountry.add(new Country(cell.getStringCellValue(), year));
            }



        }
        System.out.println("добавлено стран"+listCountry.size());
//читаем все значения.получится таблица где в каждой строчке содержатся значения одной дисциплины для разных стран
        // Get iterator to all the rows in current sheet
        Iterator<Row> rowIterator = sheet.iterator();

        while (rowIterator.hasNext()) {
            DataFormatter formatter = new DataFormatter();
            //if (row.getRowNum()==0)continue;
           // if (row.getRowNum()==1)continue;

            row = rowIterator.next();
            List<String> rowValue = new ArrayList<String>();
            if(row == null) {
                break;
            }

            for(int i=1;i<row.getLastCellNum();i+=2) {
                CellType cellType = row.getCell(i).getCellType();
                String val = formatter.formatCellValue(row.getCell(i));
                rowValue.add(val.replaceAll(",",""));
            }
            if(!rowValue.isEmpty()){
                for(String value : rowValue) {
                    System.out.print(value + ", ");
                }
                listResearch.add(new Research( rowValue));
                System.out.println("длина строки с значениями = "+rowValue.size());

            }

        }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(Research s : listResearch) {
            System.out.println("Количество наук "+s.dataCountryString.size());
        }
        for(int i = 0; i< listCountry.size(); i++) {
            List<String> tempValueCountry = new ArrayList<String>();
            for(Research s : listResearch) {
                tempValueCountry.add(s.dataCountryString.get(i));
            }

            listCountry.get(i).setListValueString(tempValueCountry);

        }
        for(Country s : listCountry) {
            System.out.println(s.name+s.year);
            for(Year year1 : YearDAO.listYear()) {
                String nameYear = new String(year1.getCountry().toCharArray()).replaceAll("\\s+","").trim().substring(1);
                String nameCountry = new String(s.getName().toCharArray()).replaceAll("\\s+","").trim();
                if (year1.getYearNumber()==year){
                    //System.out.println("название страны у года="+nameYear+"название страны="+nameCountry+nameYear.length()+nameCountry.length());
                    if(nameCountry.equals(nameYear)) {
                        System.out.println("нашли название страны у года="+nameYear+"название страны="+nameCountry+nameYear.length()+nameCountry.length());
                        year1.setListValueString(s.getListValueString());
                        year1.setListString(true);
                        s.setAdd(true);
                        break;
                    }

                }
            }
        }
        return listResearch;
        }

}
