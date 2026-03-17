package RahulShettyAcademy.SeleniumFramework.POM;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;


import RahulShettyAcademy.AbstractComponents.AbstractComponent;

public class katalogPage extends AbstractComponent {
	WebDriver driver;
	
	public katalogPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	By toastMessage = By.cssSelector("#toast-container");

	
	By productlist = By.cssSelector(".mb-3");
	By addtocart = By.cssSelector(".card-body button:last-of-type");
	
	
	public List<WebElement> getCardlist() {
		explicitwait(productlist);
		return products;
	}
	
	public WebElement getDesireProduct(String desiredproduct) {
		WebElement actualProduct = getCardlist().stream().filter(s->s.findElement(By.tagName("b")).getText().equalsIgnoreCase(desiredproduct)).findFirst().orElse(null);
		return actualProduct;
	}
	
	public void addToCart(String desiredproduct) throws InterruptedException {
		WebElement product = getDesireProduct(desiredproduct);
		product.findElement(addtocart).click();
		Thread.sleep(2000);
		//explicitwait(toastMessage);
		//invisibleexplicitwait(spinner);
		
		
		
		
		
	}
	

	
	

	
	
	
}
