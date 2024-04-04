package pageObjects;

import org.openqa.selenium.WebDriver;

public class PageObjectManager {

	public ToDoMeDefaultPage defaultPage;
	public WebDriver driver;

	public PageObjectManager(WebDriver driver) {
		// this.defaultPage = defaultPage;
		this.driver = driver;
	}

	public ToDoMeDefaultPage ToDoMeDefaultPage() {
		defaultPage = new ToDoMeDefaultPage(driver);
		return defaultPage;
	}
	
}