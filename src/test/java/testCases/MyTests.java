package testCases;

import java.io.IOException;
import java.util.HashMap;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import components.ReusableMethods;
import pageObjects.HomePage;

public class MyTests{
	
	WebDriver driver;
	ReusableMethods reuse = new ReusableMethods();
	HashMap<String, String> hm = new HashMap<String, String>();
	@BeforeMethod
	public void launchBrowser() throws IOException
	{
		hm = reuse.readExcel(0);
		driver = reuse.selectBrowser(hm.get("browser"));
	}
	@Test
	public void checkResults() throws InterruptedException, IOException
	{
		HomePage homePage = new HomePage(driver);
		driver.get(reuse.getProperties("url"));
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOf(homePage.searchBox));
		homePage.searchBox.sendKeys(hm.get("SearchText"));
		wait.until(ExpectedConditions.elementToBeClickable(homePage.searchButton));
		homePage.searchButton.click();
		if(homePage.resultLinks.size()!=0)
		{
			System.out.println("Results are displayed");
		}
		else
		{
			System.out.println("No Results");
		}
	}	
	
	@AfterMethod
	public void tearDown()
	{
		driver.close();
	}
}
