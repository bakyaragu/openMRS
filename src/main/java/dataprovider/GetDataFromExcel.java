package dataprovider;

import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class GetDataFromExcel {
public static String[][] getExcelData(String filePath) {
		
		XSSFWorkbook workbook = null;
		try {
			workbook = new XSSFWorkbook(filePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		XSSFSheet sheet = workbook.getSheetAt(0);
		int rowCount = sheet.getLastRowNum();
		int cellcount = sheet.getRow(0).getLastCellNum();
		String[][] data = new String[rowCount][cellcount];
		for(int i=1;i<=rowCount;i++) {
			XSSFRow row = sheet.getRow(i);					
			for(int j=0;j<cellcount;j++) {
				XSSFCell cell = row.getCell(j);
				if(cell.getCellType()==CellType.NUMERIC) {
					data[i-1][j] = cell.getRawValue();
				}
				else
				//String cellData = cell.getRawValue();
//				DataFormatter formatteddata= new DataFormatter();
//				data[i-1][j] = formatteddata.formatCellValue(cell);
				//System.out.println(cell);
				data[i-1][j] = cell.getStringCellValue();
			}
		}
		try {
			workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;

	}

}
