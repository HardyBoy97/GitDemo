package RahulShettyAcademy.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import RahulShettyAcademy.SeleniumFramework.POM.cartPage;
import RahulShettyAcademy.SeleniumFramework.POM.katalogPage;

public class AbstractComponent {
	
	WebDriver driver;

	
	
	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	

	@FindBy(css="[routerlink*='cart']")
	WebElement cartlink;

	public void explicitwait(By findby) {
		WebDriverWait wait  = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findby));
	}
	
	public void invisibleexplicitwait(WebElement ele) {
		WebDriverWait wait  = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	
	public cartPage cartButton() {
		cartlink.click();
		cartPage cartpage = new cartPage(driver);
		return cartpage;
	}

}
