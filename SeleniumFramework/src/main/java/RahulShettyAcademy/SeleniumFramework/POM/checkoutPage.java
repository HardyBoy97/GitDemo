package RahulShettyAcademy.SeleniumFramework.POM;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import RahulShettyAcademy.AbstractComponents.AbstractComponent;

public class checkoutPage extends AbstractComponent {
	
	WebDriver driver;
	
	public checkoutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css=".form-group input")
	WebElement countryField;
	
	@FindBy(css=".list-group-item")
	List<WebElement> countries;
	
	@FindBy(css=".action__submit")
	WebElement placeOrder;
	
	
	By dropdown = By.cssSelector(".ta-results");
	
	public void selectCountry(String desiredCountryabbr, String desiredCountry) {
		countryField.sendKeys(desiredCountryabbr);
		explicitwait(dropdown);
		WebElement actualCountry = countries.stream().filter(s->s.getText().trim().equals(desiredCountry)).findFirst().orElse(null);
		actualCountry.click();
		
	}
	
	public confirmationPage placeOrder() {
		placeOrder.click();
		confirmationPage confirmationpage = new confirmationPage(driver);
		return confirmationpage;
	}
	
	
	
	

}
