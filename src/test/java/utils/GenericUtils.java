package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GenericUtils {
	
	public WebDriver driver;
	public WebDriverWait wait;
	public GenericUtils() {
		this.driver = driver;
		
	}
	
	/*
	 * public void ExplicitWait() { wait = new WebDriverWait(driver,
	 * Duration.ofSeconds(10));
	 * 
	 * }
	 */
}
