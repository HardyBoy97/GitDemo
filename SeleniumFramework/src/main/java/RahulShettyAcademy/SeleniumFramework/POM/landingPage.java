package RahulShettyAcademy.SeleniumFramework.POM;			



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import RahulShettyAcademy.AbstractComponents.AbstractComponent;

public class landingPage extends AbstractComponent {
	WebDriver driver;
	
	public landingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
 
	
	@FindBy(id = "userEmail")
	WebElement uname;
	
	@FindBy(id="userPassword")
	WebElement pass;
	
	@FindBy(id="login")
	WebElement login;
	
	@FindBy(css="div.toast-error")
	WebElement errorMessage;
	
	public void url() {
		driver.get("https://rahulshettyacademy.com/client/");
	}
	
	public String getErrorMessage(String wrongusername, String wrongpassword)
	{
		uname.sendKeys(wrongusername);
		pass.sendKeys(wrongpassword);
		login.click();
		String errormsg = errorMessage.getText();
		return errormsg;
	}
	
	public katalogPage loginPage(String username, String password) {
		uname.sendKeys(username);
		pass.sendKeys(password);
		login.click();
		katalogPage katalogpage = new katalogPage(driver);
		return katalogpage;
		
	}
	
	

	
	
		
	}

