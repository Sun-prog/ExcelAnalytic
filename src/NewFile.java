import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NewFile {
    public static void createFile() {
        Document doc;
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Employees sheet");
        List<Year> list = YearDAO.listYear();
        List<Country> listCountry = CountryDAO.listCountry();
        List<ArrayList<Double>> listtwoArray = ValueDAO.listtwoArray();
        List<String> listDisc = ValueDAO.listdisc();
        int numberCell = 10;
        int rownum = 0;
        Cell cell;
        Row row;
        //
        HSSFCellStyle style = createStyleForTitle(workbook);

        row = sheet.createRow(rownum);

        // Excel Country
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("Country");
        cell.setCellStyle(style);
        //Excel Year
        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("Year");
        cell.setCellStyle(style);
        //Excel Proposals
        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("Number of Awards");
        cell.setCellStyle(style);
        //Excel Proposals
        cell = row.createCell(3, CellType.STRING);
        cell.setCellValue("Number of Proposals");
        cell.setCellStyle(style);
        List<Research> listResearch = ParserValue.parse("C:/doc.xls",2012);

        for(Research s : listResearch) {
            cell = row.createCell(numberCell, CellType.STRING);
            cell.setCellValue(s.getName());
            cell.setCellStyle(style);
            numberCell++;
        }
        numberCell = 10;

        try {


// заполняем новую таблицу excel
            for (Year year : list) {
                //for (int i=2012;i<2022;i++) {
                rownum++;
                row = sheet.createRow(rownum);

                // названия стран
                cell = row.createCell(0, CellType.STRING);
                cell.setCellValue(year.getCountry());
                // год
                cell = row.createCell(1, CellType.NUMERIC);
                cell.setCellValue(year.getYearNumber());

                //numberProposals
                cell = row.createCell(2, CellType.NUMERIC);
                if (!(year.getNumberProposals()%1==0)) {
                    year.setNumberProposals(year.getNumberProposals()*1000);
                    cell.setCellValue(year.getNumberProposals());
                    cell = row.createCell(5, CellType.STRING);
                    cell.setCellValue("умножили Proposals на 1000");
                } else {

                    cell.setCellValue(year.getNumberProposals());
                }



                //numberAwards
                cell = row.createCell(3, CellType.NUMERIC);
                if (!(year.getNumberAwards()%1==0)) {
                    year.setNumberAwards(year.getNumberAwards()*1000);

                    cell.setCellValue(year.getNumberAwards());
                    cell = row.createCell(6, CellType.STRING);
                    cell.setCellValue("умножили Awards на 1000");
                } else {
                    cell.setCellValue(year.getNumberAwards());
                }

                if(year.getNumberProposals()<year.getNumberAwards()){
                    year.setComment("Proposals<Awards");
                    cell = row.createCell(4, CellType.STRING);
                    cell.setCellValue(year.getNumberAwards());
                    cell.setCellValue("Proposals<Awards");

                }

                if(year.isListString){
                    for (String value : year.getListValueString()) {
                        cell = row.createCell(numberCell-2, CellType.STRING);
                        cell.setCellValue(value);
                        numberCell++;
                    }
                } else {

                    for (Double value : year.getListValue()) {
                        cell = row.createCell(numberCell, CellType.NUMERIC);
                        cell.setCellValue(value);
                        numberCell++;

                    }
                }
                numberCell = 10;

            }

            File file = new File("C:/docNew.xlsx");
            file.getParentFile().mkdirs();

            FileOutputStream outFile = new FileOutputStream(file);
            workbook.write(outFile);
            System.out.println("Created file: " + file.getAbsolutePath());
            workbook.close();
            outFile.close();

        } catch (IOException e) {
            System.out.println("неа");
            e.printStackTrace();
        }

    }

    private static HSSFCellStyle createStyleForTitle(HSSFWorkbook workbook) {
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        return style;
    }
}
