package com.datadriven.utilities;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.datadriven.base.Constants;

public class ExcelUtility {

	private File file = null;
	private Workbook wb = null;
	private Sheet sheet = null;
	private Row row = null;
	private DataFormatter formatter = new DataFormatter();

	public ExcelUtility(String Path) {
		try {
			file = new File(Path);

			wb = WorkbookFactory.create(file);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public Workbook getCurrentWB() {
		return wb;
	}

	public void closeWorkBook() {

		try {
			wb.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getRowCount(String sheetName) {
		int rowCount = 0;
		try {
			sheet = getCurrentWB().getSheet(sheetName);
			rowCount = sheet.getLastRowNum() + 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowCount;

	}

	public int getRowCount(int sheetIndex) {
		int rowCount = 0;
		try {
			sheet = getCurrentWB().getSheetAt(sheetIndex);
			rowCount = sheet.getLastRowNum() + 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowCount;
	}

	public String getCellData(String sheetName, int rowNum, int colNum) {
		String cellData = null;
		try {
			sheet = getCurrentWB().getSheet(sheetName);
			cellData = formatter.formatCellValue(sheet.getRow(rowNum - 1).getCell(colNum - 1));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return cellData;
	}

	public String getCellData(int sheetIndex, int rowNum, int colNum) {
		String cellData = new String();
		try {
			sheet = getCurrentWB().getSheetAt(sheetIndex);
			cellData = formatter.formatCellValue(sheet.getRow(rowNum - 1).getCell(colNum - 1));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return cellData;
	}

	public String getCellData(int sheetIndex, int rowNum, String colName) {
		String cellData = new String();
		try {
			sheet = getCurrentWB().getSheetAt(sheetIndex);
			int colIndex = getColNumber(sheetIndex, colName);
			if (colIndex != -1) {
				cellData = getCellData(sheetIndex, rowNum, colIndex - 1);
				return cellData;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";

	}

	public String getCellData(String sheetName, int rowNum, String colName) {
		String cellData = new String();
		try {
			sheet = getCurrentWB().getSheet(sheetName);
			int colIndex = getColNumber(sheetName, colName);
			if (colIndex != -1) {
				cellData = getCellData(sheetName, rowNum, colIndex);
				return cellData;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";

	}

	public boolean isRowEmpty(String sheetName, int rowNum) {

		try {
			sheet = getCurrentWB().getSheet(sheetName);
			row = sheet.getRow(rowNum - 1);
			for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
				Cell cell = row.getCell(c);
				if (cell != null && cell.getCellType() != Cell.CELL_TYPE_BLANK)
					return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;

	}

	public boolean isRowEmpty(int sheetIndex, int rowNum) {

		try {
			sheet = getCurrentWB().getSheetAt(sheetIndex);
			row = sheet.getRow(rowNum - 1);
			for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
				Cell cell = row.getCell(c);
				if (cell != null && cell.getCellType() != Cell.CELL_TYPE_BLANK)
					return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;

	}

	public int getColNumber(int sheetIndex, String cellData)

	{
		try {
			sheet = getCurrentWB().getSheetAt(sheetIndex);
			for (int rowNum = 0; rowNum < sheet.getLastRowNum(); rowNum++) {
				row = sheet.getRow(rowNum);
				if (row != null)
					for (int colNum = 0; colNum < row.getLastCellNum(); colNum++) {
						System.out.println(formatter.formatCellValue(row.getCell(colNum)));
						if (formatter.formatCellValue(row.getCell(colNum)).equalsIgnoreCase(cellData))

							return colNum + 1;

					}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	public int getColNumber(String sheetName, String cellData)

	{
		try {
			sheet = getCurrentWB().getSheet(sheetName);
			for (int rowNum = 0; rowNum < sheet.getLastRowNum(); rowNum++) {
				row = sheet.getRow(rowNum);
				if (row != null)
					for (int colNum = 0; colNum < row.getLastCellNum(); colNum++) {
						if (formatter.formatCellValue(row.getCell(colNum)).equalsIgnoreCase(cellData))

							return colNum + 1;

					}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	public int getRowNumber(String sheetName, String cellData)

	{
		try {
			sheet = getCurrentWB().getSheet(sheetName);
			for (int rowNum = 0; rowNum < sheet.getLastRowNum(); rowNum++) {
				row = sheet.getRow(rowNum);
				if (row != null)
					for (int colNum = 0; colNum < row.getLastCellNum(); colNum++) {

						if (formatter.formatCellValue(row.getCell(colNum)).equalsIgnoreCase(cellData))

							return rowNum + 1;

					}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	public int getRowNumber(int sheetIndex, String cellData)

	{
		try {
			sheet = getCurrentWB().getSheetAt(sheetIndex);
			for (int rowNum = 0; rowNum < sheet.getLastRowNum(); rowNum++) {
				row = sheet.getRow(rowNum);
				if (row != null)
					for (int colNum = 0; colNum < row.getLastCellNum(); colNum++) {
						System.out.println(formatter.formatCellValue(row.getCell(colNum)));
						if (formatter.formatCellValue(row.getCell(colNum)).equalsIgnoreCase(cellData))

							return rowNum + 1;

					}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	public Object[][] extractTestData(String TCID, String testDataSheet) {
		Object[][] dataArray = null;
		try {
			int testCaseRowNum = getRowNumber(testDataSheet, TCID);
			int dataKeysRow = testCaseRowNum + 1;
			int testDataRow = testCaseRowNum + 2;
			int rowCounter = 0;
			int dataKeysColCount = getCurrentWB().getSheet(testDataSheet).getRow(dataKeysRow).getLastCellNum();
			int count = 0;
			int testDataRowCounter = testDataRow;

			while (!isRowEmpty(testDataSheet, testDataRowCounter)) {
				testDataRowCounter++;
				rowCounter++;
			}
			dataArray = new Object[rowCounter][1];
			for (int i = testDataRow; i < testDataRow + rowCounter; i++) {
				Hashtable<String, String> dataTable = new Hashtable<String, String>();
				for (int c = 1; c <= dataKeysColCount; c++) {
					if (!isRowEmpty(testDataSheet, i))
						dataTable.put(getCellData(testDataSheet, dataKeysRow, c), getCellData(testDataSheet, i, c));

				}

				dataArray[count][0] = dataTable;
				count++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataArray;

	}

	public boolean checkRunMode(String testCaseName)

	{
		int rowNum = getRowNumber(Constants.RUN_MODE_SHEET, testCaseName);
		String runMode = getCellData(Constants.RUN_MODE_SHEET, rowNum, Constants.RUN_MODE_COL);
		if (runMode.equalsIgnoreCase("N")) {
			return true;
		} else if (runMode.equalsIgnoreCase("Y")) {
			return false;
		}
		return true;

	}

}