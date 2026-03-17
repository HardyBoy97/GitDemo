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

import RahulShettyAcademy.SeleniumFramework.POM.cartPage;
import RahulShettyAcademy.SeleniumFramework.POM.checkoutPage;
import RahulShettyAcademy.SeleniumFramework.POM.confirmationPage;
import RahulShettyAcademy.SeleniumFramework.POM.katalogPage;
import RahulShettyAcademy.SeleniumFramework.POM.landingPage;

public class StandalonePOM {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		landingPage obj = new landingPage(driver);
		obj.url();
		katalogPage katalogpage = obj.loginPage("hardyboy959595@gmail.com", "Test1234");
		
		
		List<WebElement> products = katalogpage.getCardlist();
		
		
		String desiredProduct = "ZARA COAT 3";
		String desiredCountryabbr = "ind";
		String desiredCountry = "India";
		String alertMessage = " Thankyou for the order. ";
		
		//click on the add to cart button:
		katalogpage.addToCart(desiredProduct);
		
		//click on Cart button:
		cartPage cartpage =  katalogpage.cartButton();
		
		
		//Store all the cart products name:
		//List<WebElement> cartProducts = obj3.getcardsName();
		
		//check if the desired product is available on the cart and store the boolean value:
		boolean match = cartpage.verifyDesireProduct(desiredProduct);
		Assert.assertTrue(match);
		
		//click on the checkout button:
		checkoutPage checkoutpage = cartpage.checkOutButton();
		
		//Select the country from table:
		checkoutpage.selectCountry(desiredCountryabbr, desiredCountry);
		
		
		//click on the placeorder button:
		confirmationPage confirmationpage = checkoutpage.placeOrder();
		
		//verify the acknowledgement message:
		confirmationpage.getSuccessMsg();
		Assert.assertTrue(alertMessage.trim().equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		
		driver.close();
		System.out.println("Success");
		
		
		
	}

}
