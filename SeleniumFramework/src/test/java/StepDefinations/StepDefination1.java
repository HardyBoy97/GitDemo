package StepDefinations;

import java.io.IOException;

import org.testng.Assert;

import Abstract.Base1;
import RahulShettyAcademy.SeleniumFramework.POM.cartPage;
import RahulShettyAcademy.SeleniumFramework.POM.checkoutPage;
import RahulShettyAcademy.SeleniumFramework.POM.confirmationPage;
import RahulShettyAcademy.SeleniumFramework.POM.katalogPage;
import RahulShettyAcademy.SeleniumFramework.POM.landingPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
	
public class StepDefination1 extends Base1  {
	
	public landingPage landingpage;
	public katalogPage katalogpage;
	public cartPage cartpage;
	public checkoutPage checkoutpage;
	public confirmationPage confirmationpage;
	
	@Given("I landed on the Ecommerce page")
	public void I_landed_on_the_Ecommerce_page() throws IOException
	{
		landingpage = lunchApplication();
		
	}
	
	@Given("^Logged in with the username (.+) and password (.+)$")
	public void Logged_in_with_the_username_and_password(String username, String password)
	{
		katalogpage= landingpage.loginPage(username, password);
	}
	
	@When("^I add the product (.+) to the cart$")
	public void I_add_the_product_to_the_cart(String productname) throws InterruptedException
	{
		katalogpage.addToCart(productname);
	}
	
	@When("^Checkout the (.+) and select the (.+) and (.+) and Submit the order$")
	public void checkout_Submit_Order(String desiredProduct, String desiredCountryabbr, String desiredCountry)
	{
		cartpage = katalogpage.cartButton();
		boolean match = cartpage.verifyDesireProduct(desiredProduct);
		Assert.assertTrue(match);
		checkoutpage = cartpage.checkOutButton();
		checkoutpage.selectCountry(desiredCountryabbr, desiredCountry);
		confirmationpage =checkoutpage.placeOrder();
	}
	
	@Then("{string} message is displayed on the confirmation page")
	public void message_displayed_on_confirmation(String string)
	{
		String alertMessage = confirmationpage.getSuccessMsg();
		System.out.println("The alertMessage is "+alertMessage);
		Assert.assertTrue(alertMessage.equalsIgnoreCase(string));
		System.out.println("The String is "+string);
		driver.close();
	}
	
	
	

}
