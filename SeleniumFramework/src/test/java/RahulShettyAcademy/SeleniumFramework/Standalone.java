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

public class Standalone {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/client/#/auth/login");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		driver.findElement(By.id("userEmail")).sendKeys("hardyboy959595@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Test1234");
		driver.findElement(By.id("login")).click();
		
		WebDriverWait wait  = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		
		List<WebElement> cards = driver.findElements(By.cssSelector(".mb-3"));
		String desiredProduct = "ZARA COAT 3";
		String desiredCountryabbr = "ind";
		String desiredCountry = "India";
		
		//apply the filter for the desired product and store it
		WebElement actualProduct = cards.stream().filter(s->s.findElement(By.tagName("b")).getText().equals(desiredProduct)).findFirst().orElse(null);
		
		//click on the add to cart button:
		actualProduct.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		//wait until loader disappears:
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		//wait until success alert appears:
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		
		//click on Cart button:
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		//Store all the cart products name:
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		
		//check if the desired product is available on the cart and store the boolean value:
		boolean match = cartProducts.stream().anyMatch(s->s.getText().equalsIgnoreCase(desiredProduct));
		
		Assert.assertTrue(match);
		
		//click on the checkout button:
		driver.findElement(By.cssSelector("li.totalRow button")).click();
		
		//open the country drop-down
		driver.findElement(By.cssSelector(".form-group input")).sendKeys(desiredCountryabbr);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		
		//collect all the countries from table:
		List<WebElement> countries = driver.findElements(By.cssSelector(".list-group-item"));
		
		WebElement actualCountry = countries.stream().filter(s->s.getText().trim().equals(desiredCountry)).findFirst().orElse(null);
		actualCountry.click();
		
		//click on the checkout button:
		
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		//verify the acknowledgement message:
		String alertMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		
		Assert.assertTrue(alertMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		
		driver.close();
		System.out.println("Success");
		
		
		
	}

}
