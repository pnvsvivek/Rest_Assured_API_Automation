package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	WebDriver driver;
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "lst-ib")
	public WebElement searchBox;
	
	@FindBy(className="lsb")
	public WebElement searchButton;
	
	@FindBy(xpath = "//div[@class='rc']/descendant::a")
	public List<WebElement> resultLinks;
	
}
