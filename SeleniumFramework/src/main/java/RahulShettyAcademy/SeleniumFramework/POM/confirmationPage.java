package RahulShettyAcademy.SeleniumFramework.POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


import RahulShettyAcademy.AbstractComponents.AbstractComponent;

public class confirmationPage extends AbstractComponent {
	
	WebDriver driver;
	
	public confirmationPage( WebDriver driver) {
		super(driver);
		this.driver = driver;
		
	}
	
	@FindBy(css=".hero-primary")
	WebElement success;
	
	public String getSuccessMsg() {
		String alertMessage = success.getText().trim();
		return alertMessage;
		
		
	}

}
