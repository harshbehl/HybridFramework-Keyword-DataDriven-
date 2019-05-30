package com.datadriven.base;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.Properties;

import com.datadriven.utilities.ExcelUtility;

public class DriverScript {
public Properties envProp;


public Properties locProp;
	Page page = new Page();

	public void runDriver(ExcelUtility util, Hashtable<String, String> dataTable, String TCID)
			throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		page.setEnvProp(envProp);
		page.setLocProp(locProp);
		page.setDataTable(dataTable);
		for (int rowNum = 2; rowNum <= util.getRowCount(Constants.KEYWORDS_SHEET); rowNum++) {
			String TestName = util.getCellData(Constants.KEYWORDS_SHEET, rowNum, Constants.TCID_COL);
			if (TestName.equals(TCID)) {
				String DataKey = util.getCellData(Constants.KEYWORDS_SHEET, rowNum, Constants.KEYWORDS_DATA_COL);
				String ObjectKey = util.getCellData(Constants.KEYWORDS_SHEET, rowNum, Constants.KEYWORDS_OBJ_COL);
				String Keyword = util.getCellData(Constants.KEYWORDS_SHEET, rowNum, Constants.KEYWORDS_COL);
				page.setDataKey(DataKey);
				page.setObjectKey(ObjectKey);

				Method m = page.getClass().getMethod(Keyword);
				m.invoke(page);
			}
		}
	}
	
	
	public void setEnvProp(Properties envProp) {
		this.envProp = envProp;
		
	}

	public void setLocProp(Properties locProp) {
		this.locProp = locProp;
		
	}
	
	public void quit()
	{
		if(page!=null)
			page.tearUp();
	}
}
