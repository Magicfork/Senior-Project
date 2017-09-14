import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ConvertCSV {
    public static void main(String[] args)
    {
        File fIn = new File("TestSheet.xlsx");
        File fOut = new File("TestCSV.csv");
        convert(fIn, fOut);
    }

    public static void convert(File in, File out){

        // These won't throw an error, so might as well declare them here
        StringBuffer data = new StringBuffer();
        XSSFSheet sheet = null;
        Row row = null;
        Cell cell = null;

        try{
            FileOutputStream fos = new FileOutputStream(out);

            XSSFWorkbook wBook = new XSSFWorkbook(new FileInputStream(in));

            sheet = wBook.getSheetAt(0);

            // Iterate through rows
            Iterator<Row> rowIterator = sheet.iterator();
            while(rowIterator.hasNext()){
                row = rowIterator.next();

                // Iterate through columns
                Iterator<Cell> cellIterator = row.cellIterator();
                while(cellIterator.hasNext()){
                    cell = cellIterator.next();

                    // Switch statement checks to see what type of data is contained within the cell
                    switch(cell.getCellTypeEnum()){
                        case BOOLEAN:
                            data.append(cell.getBooleanCellValue() + ",");
                            break;

                        case NUMERIC:
                            data.append(cell.getNumericCellValue() + ",");
                            break;

                        case STRING:
                            data.append(cell.getStringCellValue() + ",");
                            break;

                        case BLANK:
                            data.append("" + ",");
                            break;

                        default:
                            data.append(cell + ",");
                    }
                }

                // .csv files use carriage returns and don't have an ending comma, so this fixes that
                data.replace(data.lastIndexOf(","), data.lastIndexOf(",") + 1, "\r\n");
            }

            fos.write(data.toString().getBytes());
            fos.close();
        }
        // Currently catching all exceptions
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
