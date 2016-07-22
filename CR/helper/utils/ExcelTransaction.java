package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import modal.Environment;


public class ExcelTransaction {

	
	private static int stIdx = 0;
	private static int endIdx = 0;
	
	public static String readXlsFile(String fpath, String sheet){
		String text = null;
		HSSFRow row = null;
		HSSFWorkbook wb = null;
		HSSFSheet sh = null;
        try (InputStream fis = new FileInputStream(fpath);) {
            wb = new HSSFWorkbook(fis);
            sh = wb.getSheet(sheet);
            for(int i=1; i< sh.getLastRowNum();i++){
            	row = sh.getRow(i);
            	if(row.getCell(0).getStringCellValue().equalsIgnoreCase(Environment.getQaEnv())){
            		text = row.getCell(1).getStringCellValue();
            		break;
            	}
            	System.out.println(row.getCell(0).getStringCellValue() + " - " + row.getCell(1).getStringCellValue());	
            }
            
        } catch (IOException e) {
            throw new RuntimeException("Unable to read file " + ":" + e.getMessage(), e);
        } finally {
            if (null != wb) {
                try {
                    wb.close();
                } catch (IOException e) {
                    // do nothing
                }
            }
        }
        return text;
	}
	
	public static List<String> readXlsFile(int stidx, int endidx, String sh, String fpath) throws IOException {
		List<String> lst = null;
		final int URL = 0; // URL Column in the spread sheet
		HSSFWorkbook wb = null;
		HSSFSheet sheet = null;

		try (InputStream fileINStream = new FileInputStream(fpath);) {
			wb = new HSSFWorkbook(fileINStream);
			wb.setMissingCellPolicy(Row.CREATE_NULL_AS_BLANK);

			sheet = wb.getSheet(sh);
			
			lst = new ArrayList<>();
			for (int r = stidx; r <= endidx; r++) {
				lst.add(sheet.getRow(r).getCell(URL).getStringCellValue());
			}

		} catch (IOException e) {
			throw e;
		} finally {
			if (null != wb) {
				try {
					wb.close();
				} catch (IOException e) {
					// do nothing
				}
			}
		}
		return lst;
	}
	
	public static List<String> readXlsFile(String range, String sh, String fpath) throws IOException {
		List<String> lst = null;
		final int URL = 0; // URL Column in the spread sheet
		HSSFWorkbook wb = null;
		HSSFSheet sheet = null;

		try (InputStream fileINStream = new FileInputStream(fpath);) {
			setStartEndIndex(range);
			wb = new HSSFWorkbook(fileINStream);
			wb.setMissingCellPolicy(Row.CREATE_NULL_AS_BLANK);

			sheet = wb.getSheet(sh);
			
			lst = new ArrayList<>();
			for (int r = stIdx; r <= endIdx; r++) {
				lst.add(sheet.getRow(r).getCell(URL).getStringCellValue());
			}

		} catch (IOException e) {
			throw e;
		} finally {
			if (null != wb) {
				try {
					wb.close();
				} catch (IOException e) {
					// do nothing
				}
			}
		}
		return lst;
	}
	
	private static void setStartEndIndex(String range){
		String[] rowRange;
		
		if (range.contains(",")) {
			rowRange = range.split(",");
			stIdx = Integer.parseInt(rowRange[0]);
			endIdx = Integer.parseInt(rowRange[1]);
		} else if (range.contains("-")) {
			rowRange = range.split("-");
			stIdx = Integer.parseInt(rowRange[0]);
			endIdx = Integer.parseInt(rowRange[1]);
		} else if (range.equalsIgnoreCase("all")) {

		} else if (range.equalsIgnoreCase("mini")) {
			//sheet = wb.getSheet(SheetName.Cars_MiniReg.name());
		} else if (range.matches("(\\d+)")) {
			stIdx = Integer.parseInt(range);
			endIdx = Integer.parseInt(range);
		}
	}
}
