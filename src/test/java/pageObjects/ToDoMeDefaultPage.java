package pageObjects;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Wait;

public class ToDoMeDefaultPage {
	public WebDriver driver;

	public ToDoMeDefaultPage(WebDriver driver) {
		this.driver = driver;
	}

	private By applicationHeaderTitle = By.xpath("//div[@id='title']/h1");
	private By applicationHeaderSlogan = By.id("slogan");
	private By myDefaultTasksTitle = By.id("mytitle");
	private By myDefaultListInGreen = By.id("mylist_0");
	private By myDefaultListTitle = By.className("listname");
	private By addItemPanel = By.className("newtodonormal");
	private By addItemPanelPlaceholder = By.className("newtodonormal");
	private By myToDoDefaultTaskList = By.xpath("//div//ul[@id ='mytodos']//li");
	private By hyperLinks = By.tagName("a");
	private By noToDos = By.xpath("//p[@class = 'notodos' and text() = 'No items.  Why not add one below.']");
	private By topMenuLinks = By.xpath("//div[@id = 'topmenu']/a");
	private By topMenuStatusLinkText = By.xpath("//div[@id='topmenu']/a[contains(text(), ' Status')]");
	private By topMenuSyncLinkText = By.xpath("//div[@id='topmenu']/a[contains(text(), ' Sync')]");
	private By topMenuRemoteLinkText = By.xpath("//div[@id='topmenu']/a[contains(text(), ' Remote')]");
	private By myDoneToDos = By.xpath("//div//ul[@id ='mydonetodos']//li");
	private By noToDosInDone = By.xpath("//p[@class = 'notodos' and text() = 'No done items.']");
	private By applicationTitleAfterScroll = By.xpath("//h2[@id='mytitle']");

	public String getApplicationHeaderTitle() {
		// Get the application header title text
		return driver.findElement(applicationHeaderTitle).getText();
	}

	public String getApplicationSloganTitle() {
		// Get the application slogan title text
		return driver.findElement(applicationHeaderSlogan).getText();
	}

	public String getApplicationDefaultTasksTitle() {
		// Get the application default tasks title text
		return driver.findElement(myDefaultTasksTitle).getText();
	}

	public boolean checkDefaultListColorIsGreen() {
		// Check the default list highlighted in green colour
		return driver.findElement(myDefaultListInGreen).isDisplayed();
	}

	public String getApplicationListTitle() {
		// Get the application list title text
		return driver.findElement(myDefaultListTitle).getText();
	}

	public boolean checkAddItemPanelIsEnabled() {
		// Check the add iten panel is enabled
		return driver.findElement(addItemPanel).isDisplayed();
	}

	public String getAddItemPanelPlaceholderText() {
		// Get the add item panel placeholder text
		return driver.findElement(addItemPanelPlaceholder).getAttribute("placeholder");
	}

	public int getCountOfDefaultTask() {
		// Find the list of default to do tasks
		List<WebElement> myToDoTasksList = driver.findElements(myToDoDefaultTaskList);
		System.out.println(myToDoTasksList.size());
		return myToDoTasksList.size();

	}

	public String getEmptyListPlaceholder() {
		// Get text of empty placeholder text
		WebElement noToDoWebElement = driver.findElement(noToDos);
		return noToDoWebElement.getText();
	}

	public boolean emptyListPlaceholderNotPresent() {
		return driver.findElements(noToDos).size() == 0;
	}

	public String getApplicationStatusLinkTitle() {
		// Get the status link text
		return driver.findElement(topMenuStatusLinkText).getText();
	}

	public String getApplicationSyncLinkTitle() {
		// Get the Sync link text
		return driver.findElement(topMenuSyncLinkText).getText();
	}

	public String getApplicationRemoteLinkTitle() {
		// Get the Remote link text
		return driver.findElement(topMenuRemoteLinkText).getText();
	}

	public int getTopMenuLinkCount() {
		// Check the count of top menu links
		int count = 0;
		List<WebElement> links = driver.findElements(topMenuLinks);
		ArrayList<String> arrOfLinks = new ArrayList<String>();

		// Retrieve tasks and add them to the ArrayList
		for (WebElement element : links) {

			arrOfLinks.add(element.getText());
		}
		return count;
	}

	public void enterTaskInAddItemPanel(String Task) {
		// Add task in add item panel and press enter
		driver.findElement(addItemPanel).sendKeys(Task);
		driver.findElement(addItemPanel).sendKeys(Keys.ENTER);
	}

	public boolean isTaskPresentInToDoList(String Task) {
		// Check the newly added task
		List<WebElement> myToDoTasksList = driver.findElements(myToDoDefaultTaskList);
		for (WebElement element : myToDoTasksList) {
			if (element.getText().equals(Task)) {
				return true;
			}
		}
		return false;
	}

	public boolean clickOnTaskToDoCheckbox(String Task) {
		// Click on checkbox of newly added task
		List<WebElement> myToDoTasksList = driver.findElements(myToDoDefaultTaskList);
		for (WebElement element : myToDoTasksList) {
			if (element.getText().equals(Task)) {
				element.findElement(By.xpath("./child::*")).click();
				return true;
			}
		}
		return false;
	}

	public boolean isCompletedTaskPresentInDone(String Task) throws InterruptedException {
		// Check task added in done section
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)", "");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		List<WebElement> myDoneToDosList = driver.findElements(myDoneToDos);
		for (WebElement element : myDoneToDosList) {
			if (element.getText().equals(Task)) {
				return true;
			}
		}
		return false;
	}

	public boolean isTaskDeleted(String Task) {
		// Click on delete icon added next to completed task in done section
		List<WebElement> myToDoTasksList = driver.findElements(myDoneToDos);
		for (WebElement element : myToDoTasksList) {
			if (element.getText().equals(Task)) {
				WebElement childElement = element.findElement(By.xpath(".//img"));
				Actions actions = new Actions(driver);
				actions.moveToElement(childElement).build().perform();
				childElement.click();
				return true;
			}
		}
		return false;
	}
	
	public String isApplicationHeaderUpdated() {
		// Get text of application title after scroll the page
		return driver.findElement(applicationTitleAfterScroll).getText();	
	}
	
	public int getCountOfDoneTask() throws InterruptedException {
		// Find the list of done tasks
		List<WebElement> myDoneTasksList = driver.findElements(myDoneToDos);
		Thread.sleep(10);
		//System.out.println(myDoneTasksList.size());
		return myDoneTasksList.size();
	}
	public String getEmptyDoneListPlaceholder() {
		// Get text of done placeholder text
		WebElement noToDoWebElement = driver.findElement(noToDosInDone);
		return noToDoWebElement.getText();
	}

	public boolean emptyDoneListPlaceholderNotPresent() {
		return driver.findElements(noToDos).size() == 0;
	}

}
