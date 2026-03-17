package RahulShettyAcademy.SeleniumFramework;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Abstract.Base1;
import RahulShettyAcademy.SeleniumFramework.POM.cartPage;
import RahulShettyAcademy.SeleniumFramework.POM.checkoutPage;
import RahulShettyAcademy.SeleniumFramework.POM.confirmationPage;
import RahulShettyAcademy.SeleniumFramework.POM.katalogPage;
import RahulShettyAcademy.SeleniumFramework.POM.landingPage;

public class StandalonePOMTestNG  {
	public WebDriver driver;

	String desiredProduct = "ZARA COAT 3";
	String desiredCountryabbr = "ind";
	String desiredCountry = "India";
	String alertMessage = " Thankyou for the order. ";

	landingPage obj;
	katalogPage obj2;
	cartPage obj3;
	checkoutPage obj4;
	confirmationPage obj5;

	@Test()
	public void login() {

		 driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		

		obj = new landingPage(driver);
		obj.url();
		obj2 = obj.loginPage("hardyboy959595@gmail.com", "Test1234");
	}

	@Test(dependsOnMethods = {"login"})
	public void cardPage() throws InterruptedException {

		List<WebElement> products = obj2.getCardlist();

		// click on the add to cart button:
		obj2.addToCart(desiredProduct);

		// click on Cart button:
		obj3 = obj2.cartButton();
	}

	@Test(dependsOnMethods = {"cardPage"})
	public void cartPage() {

		// Store all the cart products name:
		// List<WebElement> cartProducts = obj3.getcardsName();

		// check if the desired product is available on the cart and store the boolean
		// value:

		boolean match = obj3.verifyDesireProduct(desiredProduct);
		Assert.assertTrue(match);

		// click on the checkout button:
		obj4 = obj3.checkOutButton();
	}

	@Test(dependsOnMethods = {"cartPage"})
	public void placeOrderPage() {

		// Select the country from table:

		obj4.selectCountry(desiredCountryabbr, desiredCountry);

		// click on the placeorder button:
		obj5 = obj4.placeOrder();
	}

	@Test(dependsOnMethods = {"placeOrderPage"})
	public void SuccessMsgPage() {

		// verify the acknowledgement message:
		String actualMessage = obj5.getSuccessMsg();
		Assert.assertTrue(actualMessage.trim().equalsIgnoreCase("THANKYOU FOR THE ORDER."));

		driver.quit();;		
		System.out.println("Success");
	}
	
	
}
