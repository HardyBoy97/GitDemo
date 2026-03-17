package Abstract;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import RahulShettyAcademy.SeleniumFramework.POM.landingPage;



public class Base1 {
	public WebDriver driver;
	public landingPage  landingpage;
	
	
	
	
	
	
	public WebDriver initializeDriver() {
		 driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		return driver;
		
	}
	
	public landingPage lunchApplication()
	{
		driver = initializeDriver();
		  landingpage = new landingPage(driver);
		  landingpage.url();
		  return landingpage;
	}
	
	public List<HashMap<String, String>> getJsonData(String filepath) throws IOException {
		
		//read the json to string:
		String jsonContent  = FileUtils.readFileToString(new File(filepath), StandardCharsets.UTF_8);
		
		//string to hashmap: with jackson Databind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
		});
		return data;
	}
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException
	{
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(System.getProperty("user.dir")+"//Reports//"+testCaseName+".png"));
		return System.getProperty("user.dir")+"//Reports"+testCaseName+".png";
	}
	
	@AfterMethod
	public void close() {
		if(driver!=null) {
		driver.quit();
		}
		
		
	}

}
