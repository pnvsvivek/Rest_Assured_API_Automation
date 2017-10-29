package components;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class ReusableMethods {
	
	public static void main(String args[]) throws IOException
	{
		ReusableMethods reuse = new ReusableMethods();
		HashMap<String,String> hm = new HashMap<String, String>();
		hm = reuse.readExcel(0);
		System.out.println("text is "+hm.get("SearchText"));
	}

	public WebDriver selectBrowser(String browser)
	{
		WebDriver driver = null;
		if(browser.equalsIgnoreCase("ie"))
		{
			System.setProperty("webdriver.ie.driver", "drivers//IEDriverServer.exe");
			DesiredCapabilities cap = DesiredCapabilities.internetExplorer();
			cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			driver = new InternetExplorerDriver(cap);
			driver.manage().window().maximize();
		}
		else if(browser.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "drivers//chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		}
		else if(browser.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "drivers//geckodriver.exe");
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
		}
		return driver;
	}
	
	public String getProperties(String key) throws IOException
	{
		File file = new File("config.properties");
		FileInputStream fis = new FileInputStream(file);
		Properties prop = new Properties();
		prop.load(fis);
		return prop.getProperty(key);
	}
	
	@SuppressWarnings("resource")
	public HashMap<String,String> readExcel(int index) throws IOException
	{
		String filePath = "C:\\Users\\p.nvs.vivek\\workspace\\com.test.RestAssured\\files\\TD.xlsx";
		String sheetName = "data";
		List<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
		FileInputStream fis = new FileInputStream(filePath);
		Workbook wb = new XSSFWorkbook(fis);
		Sheet sh = wb.getSheet(sheetName);
		int rd = sh.getLastRowNum()-sh.getFirstRowNum();
		for(int i=1;i<=rd;i++)
		{
			HashMap<String, String> hm = new HashMap<String, String>();
			Row row = sh.getRow(i);
			if(row!=null)
			{
				for(int j=0;j<row.getLastCellNum();j++)
				{
				hm.put(sh.getRow(0).getCell(j).toString(), row.getCell(j).toString());
				}
			}
			list.add(hm);
		}
		return list.get(index);
	}
	
	@SuppressWarnings("resource")
	public List<HashMap<String,String>> readExcel() throws IOException
	{
		String filePath = "C:\\Users\\p.nvs.vivek\\workspace\\com.test.RestAssured\\files\\TD.xlsx";
		String sheetName = "data";
		List<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
		FileInputStream fis = new FileInputStream(filePath);
		Workbook wb = new XSSFWorkbook(fis);
		Sheet sh = wb.getSheet(sheetName);
		int rd = sh.getLastRowNum()-sh.getFirstRowNum();
		for(int i=1;i<=rd;i++)
		{
			HashMap<String, String> hm = new HashMap<String, String>();
			Row row = sh.getRow(i);
			if(row!=null)
			{
				for(int j=0;j<row.getLastCellNum();j++)
				{
				hm.put(sh.getRow(0).getCell(j).toString(), row.getCell(j).toString());
				}
			}
			list.add(hm);
		}
		return list;
	}
}
