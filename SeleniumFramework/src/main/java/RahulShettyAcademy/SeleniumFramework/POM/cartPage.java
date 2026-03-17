package RahulShettyAcademy.SeleniumFramework.POM;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import RahulShettyAcademy.AbstractComponents.AbstractComponent;

public class cartPage extends AbstractComponent {
	WebDriver driver;
	
	public cartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartProducts;
	
	@FindBy(css="li.totalRow button")
	WebElement checkout;
	
	public List<WebElement> getcardsName() {
		return cartProducts;
	}
	
	public boolean verifyDesireProduct(String desiredProduct) {
		boolean match = cartProducts.stream().anyMatch(s->s.getText().equalsIgnoreCase(desiredProduct));
		return match;
		
	}
	
	public checkoutPage checkOutButton() {
		checkout.click();
		checkoutPage checkoutpage = new checkoutPage(driver);
		return checkoutpage;
	}

}
