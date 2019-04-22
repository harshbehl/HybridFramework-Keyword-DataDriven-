package com.datadriven.utilities;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.NPOIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	private OPCPackage pkg = null;
	private NPOIFSFileSystem fs = null;
	private File file = null;
	private Workbook wb = null;
	private Sheet sheet = null;
	private Row row = null;
	private Cell cell = null;
	private DataFormatter formatter = new DataFormatter();

	public ExcelUtility(String Path) {
		try {
			file = new File(Path);
			if (Path.endsWith("xlsx")) {
				pkg = OPCPackage.open(file);
				wb = new XSSFWorkbook(pkg);
			} else if (Path.endsWith("xls")) {
				fs = new NPOIFSFileSystem(file);
				wb = new HSSFWorkbook(fs.getRoot(), false);
			} else
				System.out.println("This is not an Excel file");
		} catch (Throwable t) {
			System.out.println(t.getMessage());
		}

	}

	public Workbook getCurrentWB() {
		return wb;
	}

	public void closeWorkBook() {
		if (fs != null)
			try {
				fs.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		else if (pkg != null)
			try {
				pkg.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public Boolean isSheetEmpty(String sheetName) {
		if (getCurrentWB().getSheet(sheetName).getLastRowNum() == -1)
			return true;
		else
			return false;
	}

	public Boolean isSheetEmpty(int sheetIndex) {
		if (getCurrentWB().getSheetAt(sheetIndex).getLastRowNum() == -1)
			return true;
		else
			return false;
	}

	public int getRowCount(String sheetName) {
		sheet = getCurrentWB().getSheet(sheetName);
		return sheet.getLastRowNum() + 1;

	}

	public int getRowCount(int sheetIndex) {
		try {
			sheet = getCurrentWB().getSheetAt(sheetIndex);
		} catch (Throwable t) {
			System.out.println(t.getMessage());
		}
		return sheet.getLastRowNum() + 1;
	}

	public String getCellData(String sheetName, int rowNum, int colNum) {
		String cellData = null;
		try {
			sheet = getCurrentWB().getSheet(sheetName);
			cellData = formatter.formatCellValue(sheet.getRow(rowNum + 1).getCell(colNum + 1));

		} catch (Throwable t) {
			System.out.println(t.getMessage());
		}
		return cellData;
	}

	public String getCellData(int sheetIndex, int rowNum, int colNum) {
		String cellData = new String();
		try {
			sheet = getCurrentWB().getSheetAt(sheetIndex);
			cellData = formatter.formatCellValue(sheet.getRow(rowNum + 1).getCell(colNum + 1));

		} catch (Throwable t) {
			t.printStackTrace();
		}
		return cellData;
	}

	public boolean isRowEmpty(String sheetName, int rowNum) {
		Boolean result = false;
		try {
			sheet = getCurrentWB().getSheet(sheetName);
			if (sheet.getRow(rowNum + 1) == null)
				result = true;

		} catch (Throwable t) {
			System.out.println(t.getMessage());
		}

		return result;

	}

	public boolean isRowEmpty(int sheetIndex, int rowNum) {
		Boolean result = false;
		try {
			sheet = getCurrentWB().getSheetAt(sheetIndex);
			if (sheet.getRow(rowNum + 1) == null)
				result = true;

		} catch (Throwable t) {
			System.out.println(t.getMessage());
		}

		return result;

	}

	public HashMap<Integer, List<String>> getSheetData(int sheetIndex) {
		HashMap<Integer, List<String>> data = new HashMap<Integer, List<String>>();
		if (!isSheetEmpty(sheetIndex))
			try {
				sheet = getCurrentWB().getSheetAt(sheetIndex);
				if (getRowCount(sheetIndex) != 0) {
					List<String> rowData = null;
					for (int i = 0; i < getRowCount(sheetIndex); i++) {
						row = sheet.getRow(i);
						rowData = new ArrayList<String>();

						for (int j = 0; j < row.getLastCellNum(); j++) {
							cell = row.getCell(j);
							rowData.add(formatter.formatCellValue(cell));

						}
						data.put(i, rowData);
					}
				}

			} catch (Throwable t) {
				t.printStackTrace();
			}
		return data;

	}

	public HashMap<Integer, List<String>> getSheetData(String sheetName) {
		HashMap<Integer, List<String>> data = new HashMap<Integer, List<String>>();
		if (!isSheetEmpty(sheetName))
			try {
				sheet = getCurrentWB().getSheet(sheetName);
				if (getRowCount(sheetName) != 0) {

					for (int i = 0; i < getRowCount(sheetName); i++) {
						row = sheet.getRow(i);

						List<String> rowData = new ArrayList<String>();

						for (int j = 0; j < row.getLastCellNum(); j++) {
							cell = row.getCell(j);
							rowData.add(formatter.formatCellValue(cell));

						}
						data.put(i, rowData);
					}
				}

			} catch (Throwable t) {
				System.out.println(t.getMessage());
			}
		return data;

	}

}