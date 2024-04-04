package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestBase {

	private WebDriver driver;

	public WebDriver WebDriverManager() throws IOException {
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "/src/test/resources/global.properties");
		Properties prop = new Properties();
		prop.load(fis);
		String url = prop.getProperty("URL");
		
		// Launch application URL
		if (driver == null) {
			driver = new ChromeDriver(); // driver gets the life
			//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			driver.get(url); // Launch Application URL
			driver.manage().window().maximize(); // Maximize window
		}

		return driver;
	}

}
