package stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.PageObjectManager;
import pageObjects.ToDoMeDefaultPage;
import utils.TestContextSetup;

public class ToDoMeDefaultPageSteps {
	TestContextSetup testContextSetup;
	public String myDefaultTasksTitle;
	public String defaultListTitle;
	public PageObjectManager pageObjectManager;
	public ToDoMeDefaultPage defaultPage;

	public ToDoMeDefaultPageSteps(TestContextSetup testContextSetup) {

		this.testContextSetup = testContextSetup;
		defaultPage = testContextSetup.pageObjectManager.ToDoMeDefaultPage();
	}

	@Given("Launch the to do List application")
	public void Launch_the_to_do_list_application() throws InterruptedException, IOException {
		
		/* Launch to do list Me application */
		testContextSetup.testBase.WebDriverManager();
	}

	@Then("^I should see the application title (.+)$")
	public void i_should_see_the_application_title(String Title) {
		
		// Application title header
		Assert.assertEquals(defaultPage.getApplicationHeaderTitle(), Title);
		
	}

	@Then("^I should see the application slogan (.+)$")
	public void i_should_see_the_application_slogan(String Slogan) {
		
		// Application slogan
		Assert.assertEquals(defaultPage.getApplicationSloganTitle(), Slogan);
	}

	@Then("^I should see the (.+), (.+) and (.+) links on top menu$")
	public void i_should_see_the_status_sync_and_remote_links_on_top_menu(String StatusLink, String SyncLink, String RemoteLink) {
	    
		// Application top menu links
		Assert.assertEquals(defaultPage.getApplicationStatusLinkTitle(), StatusLink);
		Assert.assertEquals(defaultPage.getApplicationSyncLinkTitle(), SyncLink);
		Assert.assertEquals(defaultPage.getApplicationRemoteLinkTitle(), RemoteLink);
	    defaultPage.getTopMenuLinkCount();
	}
	
	@Then("^I should see the default task (.+)$")
	public void i_should_see_the_default_task(String TasksTitle) {
		
		// Default task title - Today's Tasks
		Assert.assertEquals(defaultPage.getApplicationDefaultTasksTitle(),TasksTitle);
	}

	@Then("^I should see the default list name (.+)$")
	public void i_should_see_the_default_list_name(String ListName) {
		
		// Default list name highlighted in green colour
		Assert.assertTrue(defaultPage.checkDefaultListColorIsGreen());
		Assert.assertEquals(defaultPage.getApplicationListTitle(), ListName);
	}

	@Then("^Validate the default (.+) matches with (.+)$")
	public void validate_the_default_matches_with(String TasksTitle, String ListName) {

		// Assert default task title header with default list name
		Assert.assertEquals(defaultPage.getApplicationDefaultTasksTitle(), defaultPage.getApplicationListTitle());
	}

	@Then("I should see a task input field enabled")
	public void i_should_see_a_task_input_field_enabled() {
		
		// Input field is enabled
		Assert.assertTrue(defaultPage.checkAddItemPanelIsEnabled());
		defaultPage.getAddItemPanelPlaceholderText();
	}

	@Then("Validate list to do tasks")
	public void validate_list_to_do_tasks() {
		
		//Check to do list section
		String str = "No items.  Why not add one below.";
		if(defaultPage.getCountOfDefaultTask() == 0)
		{
			Assert.assertEquals(defaultPage.getEmptyListPlaceholder(), str);
		}
		else
		{
			Assert.assertTrue(defaultPage.emptyListPlaceholderNotPresent());
		}
	}

	@Then("I should see a footer with copyright information")
	public void i_should_see_a_footer_with_copyright_information() {
		 
		//To perform Scroll on application using Selenium
		JavascriptExecutor js = (JavascriptExecutor) testContextSetup.driver;
		js.executeScript("window.scrollBy(0,500)", "");
		
		// Find all hyperlinks on the page
		List<WebElement> hyperLinks = testContextSetup.wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.tagName("a")));
		
		// Print the count of hyperlinks
		System.out.println("Total number of hyperlinks: "+ hyperLinks.size());
		
		 // Print the text and href attribute of each hyperlink
		for(WebElement links : hyperLinks) {
			
			System.out.println("Text: "+ links.getText());
			System.out.println("URL: "+ links.getAttribute("href"));
			System.out.println("*********************");
		}
	}
	
	@When("^The user enters a new (.+) in the input field and presses enter$")
	public void the_user_enters_a_new_task_in_the_input_field_and_presses_enter(String Task) {
	    // Add new task in to do list
		defaultPage.enterTaskInAddItemPanel(Task);
	}
	
	@Then("^The (.+) should be added to the list of tasks$")
	public void the_breakfast_on_the_mountains_should_be_added_to_the_list_of_tasks(String Task) {
	    // Check the task added to the list
		Assert.assertTrue(defaultPage.isTaskPresentInToDoList(Task));
	}
	
	@When("^The user clicks on the checkbox next to a (.+)$")
	public void the_user_clicks_on_the_checkbox_next_to_a_task(String Task) {
	    // Click on the checkbox to complete the task
		Assert.assertTrue(defaultPage.clickOnTaskToDoCheckbox(Task));
	}

	@Then("^The (.+) should be marked as completed$")
	public void the_task_should_be_marked_as_completed(String Task) throws InterruptedException {
	    // Check completed task added in done section
		Assert.assertTrue(defaultPage.isCompletedTaskPresentInDone(Task));
		
	}
	
	@When("^The user scrolls the page, the (.*) changes$")
	public void the_user_scrolls_the_page_the_today_s_tasks_changes(String Header ) {
	    // Check the application header when user scrolls the page
		Assert.assertEquals(defaultPage.isApplicationHeaderUpdated(), Header);
	}

	@When("^The user clicks on the delete icon next to a (.+)$")
	public void the_user_clicks_on_the_delete_icon_next_to_a(String Task) {
	    // Click on delete icon to delete
		Assert.assertTrue(defaultPage.isTaskDeleted(Task));
	}

	@Then("^The (.+) should be removed from the list$")
	public void the_breakfast_on_the_mountains_should_be_removed_from_the_list(String Task) throws InterruptedException {
	    // 
		String str = "No done items.";
		if(defaultPage.getCountOfDoneTask() == 0)
		{
			Assert.assertEquals(defaultPage.getEmptyDoneListPlaceholder(), str);
		}
		else
		{
			Assert.assertTrue(defaultPage.emptyDoneListPlaceholderNotPresent());
		}
	}
}