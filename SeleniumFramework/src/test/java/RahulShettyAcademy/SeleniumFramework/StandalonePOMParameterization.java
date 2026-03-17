package RahulShettyAcademy.SeleniumFramework;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Abstract.Base1;
import Abstract.Retry;
import RahulShettyAcademy.SeleniumFramework.POM.cartPage;
import RahulShettyAcademy.SeleniumFramework.POM.checkoutPage;
import RahulShettyAcademy.SeleniumFramework.POM.confirmationPage;
import RahulShettyAcademy.SeleniumFramework.POM.katalogPage;
import RahulShettyAcademy.SeleniumFramework.POM.landingPage;

public class StandalonePOMParameterization extends Base1 {

	String desiredCountryabbr = "ind";
	String desiredCountry = "India";
	//String alertMessage = " Thankyou for the order. ";

	@Test(dataProvider = "getData", retryAnalyzer = Retry.class)
	public void createOrder(HashMap<String, String> input) throws IOException, InterruptedException{
		driver = initializeDriver();

		landingPage obj = new landingPage(driver);
		obj.url();
		katalogPage obj2 = obj.loginPage(input.get("email"), input.get("pass"));

		List<WebElement> products = obj2.getCardlist();

	

		// click on the add to cart button:
		obj2.addToCart(input.get("product"));

		// click on Cart button:
		cartPage obj3 = obj2.cartButton();

		// check if the desired product is available on the cart and store the boolean value
		
		boolean match = obj3.verifyDesireProduct(input.get("product"));
		Assert.assertTrue(match);

		// click on the checkout button:
		checkoutPage obj4 = obj3.checkOutButton();

		// Select the country from table:
		obj4.selectCountry(desiredCountryabbr, desiredCountry);

		// click on the placeorder button:
		confirmationPage obj5 = obj4.placeOrder();

		// verify the acknowledgement message:
		String alertMessage = obj5.getSuccessMsg();
		Assert.assertTrue(alertMessage.trim().equalsIgnoreCase("THANKYOU FOR THE ORDER."));

		
		System.out.println("Success");
		
		

	}
	
	/*public String getScreenshot(String testCaseName) throws IOException
	{
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(System.getProperty("user.dir")+"//Reports"+testCaseName+".png"));
		return System.getProperty("user.dir")+"//Reports"+testCaseName+".png";
	}*/
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		List<HashMap<String, String>> data = getJsonData(System.getProperty("user.dir")+"//src//test//java//data//DataReader.json");
		return new Object [][] {{data.get(0)}, {data.get(1)}};
		
	}
	
	/*@DataProvider
	public Object[][] getData() 
	{
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("email", "hardyboy959595@gmail.com");
		map.put("pass", "Test1234");
		map.put("product", "ZARA COAT 3");
		
		HashMap<String, String> map2 = new HashMap<String, String>();
		map2.put("email", "hardiksonigra97@gmail.com");
		map2.put("pass", "Test1234");
		map2.put("product", "IPHONE 13 PRO");
		return new Object[][] {{map}, {map2}};
		
	}*/
	
//	@DataProvider
//	public Object[][] getData() {
//		return new Object[][] {{"hardyboy959595@gmail.com", "Test1234", "ZARA COAT 3" }, {"hardiksonigra97@gmail.com","Test1234", "IPHONE 13 PRO"}};
//	}

}
