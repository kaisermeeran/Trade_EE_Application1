package com.utilitypackage;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class excelutils {
	
	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static XSSFWorkbook wb;
	public static XSSFSheet ws;
	public static XSSFRow row;
	public static XSSFCell cell;
	public static CellStyle style;
	
	public static int getRowCount(String xlFile, String sheetName) throws Exception {
		fi = new FileInputStream(xlFile);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(sheetName);
		int rowCount = ws.getLastRowNum();
		wb.close();
		fi.close();
		return rowCount;
	}
	public static int getCellCount(String xlFile, String sheetName, int rowNum) throws Exception {
		fi = new FileInputStream(xlFile);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(sheetName);
		row = ws.getRow(rowNum);
		int cellCount = row.getLastCellNum();
		wb.close();
		fi.close();
		return cellCount;
	}
	public static String getCellData(String xlFile, String sheetName, int rowNum, int colNum) throws Exception {
	    fi = new FileInputStream(xlFile);
	    wb = new XSSFWorkbook(fi);
	    ws = wb.getSheet(sheetName);
	    
	    row = ws.getRow(rowNum);
	    if (row == null) {
	        wb.close();
	        fi.close();
	        return "";  // return empty if row is missing
	    }

	    cell = row.getCell(colNum);
	    if (cell == null) {
	        wb.close();
	        fi.close();
	        return "";  // return empty if cell is missing
	    }

	    String data;
	    try {
	        if (cell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(cell)) {
	            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	            data = sdf.format(cell.getDateCellValue());
	        } else {
	            DataFormatter formatter = new DataFormatter();
	            data = formatter.formatCellValue(cell);
	        }
	    } catch (Exception e) {
	        data = "";
	    }

	    wb.close();
	    fi.close();
	    return data;
	}


	public static void setCellData(String xlFile, String sheetName, int rowNum, int colNum, String data) throws Exception {
		fi = new FileInputStream(xlFile);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(sheetName);
		row = ws.getRow(rowNum);
		if (row == null) {
			row = ws.createRow(rowNum);
		}
		cell = row.getCell(colNum);
		if (cell == null) {
			cell = row.createCell(colNum);
		}
		cell.setCellValue(data);
		fo = new FileOutputStream(xlFile);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
	}
	
	public static void fillGreenColor(String xlFile, String sheetName, int rowNum, int colNum) throws Exception {
		fi = new FileInputStream(xlFile);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(sheetName);
		row = ws.getRow(rowNum);
		if (row == null) {
			row = ws.createRow(rowNum);
		}
		cell = row.getCell(colNum);
		if (cell == null) {
			cell = row.createCell(colNum);
		}
		style = wb.createCellStyle();
		style.setFillForegroundColor((short) 0x00FF00); // Green color
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cell.setCellStyle(style);
		fo = new FileOutputStream(xlFile);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
	}
	
	public static void fillRedColor(String xlFile, String sheetName, int rowNum, int colNum) throws Exception {
		fi = new FileInputStream(xlFile);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(sheetName);
		row = ws.getRow(rowNum);
		if (row == null) {
			row = ws.createRow(rowNum);
		}
		cell = row.getCell(colNum);
		if (cell == null) {
			cell = row.createCell(colNum);
		}
		style = wb.createCellStyle();
		style.setFillForegroundColor((short) 0xFF0000); // Red color
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cell.setCellStyle(style);
		fo = new FileOutputStream(xlFile);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
	}
	
	public static void close() throws Exception {
		if (wb != null) {
			wb.close();
		}
		if (fi != null) {
			fi.close();
		}
		if (fo != null) {
			fo.close();
		}
	}
	
	public static int getColumnIndexByName(String xlFile, String sheetName, String columnName) throws Exception {
	    fi = new FileInputStream(xlFile);
	    wb = new XSSFWorkbook(fi);
	    ws = wb.getSheet(sheetName);
	    row = ws.getRow(0); // Assuming header is in the first row
	    int colCount = row.getLastCellNum();
	    int colIndex = -1;

	    for (int i = 0; i < colCount; i++) {
	        XSSFCell cell = row.getCell(i);
	        if (cell != null && cell.getStringCellValue().trim().equalsIgnoreCase(columnName.trim())) {
	            colIndex = i;
	            break;
	        }
	    }

	    wb.close();
	    fi.close();
	    return colIndex;
	}

	

}
