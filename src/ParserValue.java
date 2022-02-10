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
//чтение и запись строк - источник https://betacode.net/11259/read-write-excel-file-in-java-using-apache-poi
public class ParserValue {

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
        System.out.println("изначально стран"+CountryDAO.list.size());

        while (cellIterator.hasNext()) {

            Cell cell = cellIterator.next();
            //System.out.println("ищем страну "+cell.getStringCellValue());
            if (cell.getStringCellValue()!=null&& !cell.getStringCellValue().trim().isEmpty()) {
               // System.out.println("нашли страну " + cell.getStringCellValue());
                //работает для 12 года
               // CountryDAO.list.add(new Country(cell.getStringCellValue(), year));
                //удалить?
               // tempValueCountry.add(cell.getStringCellValue());
                listCountry.add(new Country(cell.getStringCellValue(), year));
            }



        }
        System.out.println("добавлено стран"+CountryDAO.list.size());
//читаем все значения.получится таблица где в каждой строчке содержатся значения одной дисциплины для разных стран
        // Get iterator to all the rows in current sheet
        Iterator<Row> rowIterator = sheet.iterator();

        while (rowIterator.hasNext()) {

            row = rowIterator.next();
            List<Double> rowValue = new ArrayList<Double>();
            if(row == null) {
                break;
            }

            for(int i=2;i<row.getLastCellNum();i+=2) {
                CellType cellType = row.getCell(i).getCellType();
                if (row.getCell(i) == null || row.getCell(i).getCellType() == CellType.BLANK) {
                    break;// This cell is empty
                }


                switch (cellType) {
                    case NUMERIC:
                        rowValue.add(row.getCell(i).getNumericCellValue());

                      //  System.out.println("записано в row,i ="+i+" значение= "+row.getCell(i).getNumericCellValue());
                       // System.out.print("\t");
                        //break;
                }

            }
            if(!rowValue.isEmpty()){
                for(Double value : rowValue) {
                    System.out.print(value + ", ");
                }
                //ValueDAO.twoArrayList.add((ArrayList<Double>) rowValue);
                listResearch.add(new Research(row.getCell(0).getStringCellValue(), rowValue));

                //System.out.println("добавляем дисциплину "+row.getCell(0).getStringCellValue());
                System.out.println("длина строки с значениями = "+rowValue.size());
                //ValueDAO.disc.add(row.getCell(0).getStringCellValue());
                //rowValue.clear();
            }

        }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(Research s : listResearch) {
            System.out.println(s.name+"Количество наук "+s.dataCountry.size());
        }
        for(int i = 0; i< listCountry.size(); i++) {
            List<Double> tempValueCountry = new ArrayList<Double>();
            for(Research s : listResearch) {
                tempValueCountry.add(s.dataCountry.get(i));
            }
            listCountry.get(i).setListValue(tempValueCountry);

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
                        year1.setListValue(s.getListValue());
                        s.setAdd(true);
                        break;
                    }

                }
            }
        }
        return listResearch;
        }

}
