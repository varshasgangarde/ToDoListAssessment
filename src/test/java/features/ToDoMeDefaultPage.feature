Feature: To Do List Me Application Verification
  As a user, I want to verify the default page after launching the Todo List application and 
  cover some user interactions with the application 
  so that I can ensure the page loads correctly with the expected elements and supports user actions.
  
  @PriorityOne
  Scenario Outline: Verify Default Page Elements
    Given Launch the to do List application
    Then I should see the application title <Title>
    Then I should see the application slogan <Slogan>
    Then I should see the <StatusLink>, <SyncLink> and <RemoteLink> links on top menu
    And I should see the default task <TasksTitle>
    And I should see the default list name <ListName>
    And Validate the default <TasksTitle> matches with <ListName>
    And I should see a task input field enabled
    And Validate list to do tasks

    Examples:
      | Title      | Slogan            | TasksTitle    | ListName      | StatusLink |SyncLink|RemoteLink|
      | TodoListMe | Powerfully Simple | Today's Tasks | Today's Tasks | Status     |Sync    |Remote    |
      
  @PriorityTwo 
 	Scenario: Add a Task, mark as completed and remove from the list
		Given Launch the to do List application
		When The user enters a new <Task> in the input field and presses enter
		Then The <Task> should be added to the list of tasks
		When The user clicks on the checkbox next to a <Task>
		Then The <Task> should be marked as completed
		When The user scrolls the page, the <Title> changes
		When The user clicks on the delete icon next to a <Task>
		Then The <Task> should be removed from the list
		
		Examples:
			|Task											 |Title        |
			|Breakfast on the mountains|Today's Tasks|

			     