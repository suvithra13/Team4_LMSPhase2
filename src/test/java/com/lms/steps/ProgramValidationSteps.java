package com.lms.steps;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import com.lms.PageObjects.Program_POM;
import com.lms.utilities.BaseClass;
import com.lms.utilities.ExcelReaderListMap;
import com.lms.utilities.PageUtils;
import com.lms.utilities.ProgramUtil;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ProgramValidationSteps extends BaseClass{
	WebDriver driver;
	public Program_POM programPage;//ProgramPage object
	public List<Map<String, String>> programTestData = null;
	private PageUtils pageUtils = null;
	public static final ProgramUtil programUtil = ProgramUtil.getInstance();
	
	public ProgramValidationSteps() {
		try {
			ExcelReaderListMap reader = new ExcelReaderListMap();
			programTestData = reader.getData(BaseClass.eXCEL, "Program");
			System.out.println("Created new instance for ProgramValidationSteps");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private PageUtils getPageUtils() {
		String rowsInfo = programPage.numberOfRecordstextValidate();
		pageUtils = new PageUtils(rowsInfo);
		
		return pageUtils;
	}
	
	private PageUtils getCurrentPageUtils() {
		if (pageUtils == null) {
			return getPageUtils();
		}
		
		return pageUtils;
	}
	
	@Given("admin enter the LMS site")
	public void admin_enter_the_lms_site() {
		
		if (programUtil.isLoggedIn()) {
			driver = programUtil.getDriver();
			programPage = programUtil.getProgramPage();
		} else {
			driver = new ChromeDriver();
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			driver.get("https://lms-frontend-api-hackathon-apr-326235f3973d.herokuapp.com");
			programPage = new Program_POM(driver);
			
			programUtil.setDriver(driver);
			programUtil.setProgramPage(programPage);
		}
	}
	
	@When("Admin enters Username {string} and Password {string} and logged in")
	public void admin_enters_username_and_password_and_logged_in(String Uname, String Pwd) {
		if (!programUtil.isLoggedIn()) {
			programPage.enterUserName(Uname);
			programPage.enterPasswordField(Pwd);
			programPage.clickOnLoginButton();
		}
	}

	@And("Admin is on dashboard page after Login")
	public void admin_is_on_dashboard_page_after_login() {
		if (!programUtil.isLoggedIn()) {
			String dashboardTitle = driver.getTitle();
			System.out.println("dashboardTitle=" + dashboardTitle);
			Assert.assertEquals("LMS", dashboardTitle);
		}
	}

	@Then("Admin clicks {string} on the navigation bar")
	public void admin_clicks_on_the_navigation_bar(String ProgramButton) throws InterruptedException {
		programPage.clickOnProgramButton();
	}
	
	@Then("Admin should see URL with {string}")
	public void admin_should_see_url_with(String manageProgram) {
		String actualUrl = driver.getCurrentUrl();
		System.out.println(actualUrl);
		String expectedUrl ="https://lms-frontend-api-hackathon-apr-326235f3973d.herokuapp.com/";
		Assert.assertEquals(actualUrl, expectedUrl);
		
		programPage.manageProgramIsDisplayed();
		Assert.assertEquals(programPage.manageProgramIsDisplayed(),"Manage Program");
	}
	
	//3
	@Then("Admin should see a heading with text {string} on the page")
	public void admin_should_see_a_heading_with_text_on_the_page(String string) {
		Assert.assertEquals(programPage.manageProgramIsDisplayed(),"Manage Program");
	 }
	
	@Then("Admin should see the footer as In total there are z programs")
	public void admin_should_see_the_footer_as_in_total_there_are_z_programs() {
		System.out.println("Footer Message ------>"+programPage.getfooterMessage());
		Assert.assertTrue(programPage.getfooterMessage().startsWith("In total there are ") && programPage.getfooterMessage().endsWith(" programs."));
	}
	
    //Delete
	@Then("Admin should see a Delete button on the top left hand side as Disabled")
	public void admin_should_see_a_delete_button_on_the_top_left_hand_side_as_disabled() {
		Assert.assertFalse(programPage.deleteButtonValidation());
	}
	
	//Search
	@Then("Admin should see Search bar with text as {string}")
	public void admin_should_see_search_bar_with_text_as(String string) {
		Assert.assertTrue(programPage.searchBoxValidation());  
	}
	
    //+A new Program
	@Then("Admin should see a {string} button on the program page above the data table")
	public void admin_should_see_a_button_on_the_program_page_above_the_data_table(String string) {
		Assert.assertEquals(programPage.addProgramButtonValidation(), "A New Program");
	}
	
	@Then("Admin should see data table on the Manage Program Page with column headers")
	public void dmin_should_see_data_table_on_the_manage_program_page_with_column_headers() {

		Assert.assertTrue("Program Name".equals(programPage.programNameHeaderValidation()));
		Assert.assertTrue("Program Description".equals(programPage.programDescriptionHeaderValidation()));
		Assert.assertTrue("Program Status".equals(programPage.programStatusHeaderValidation()));
		Assert.assertTrue("Edit / Delete".equals(programPage.editDeleteHeaderValidation()));
	}

	@Then("Admin should see the sort arrow icon beside to each column header except Edit and Delete")
	public void admin_should_see_the_sort_arrow_icon_beside_to_each_column_header_except_edit_and_delete() {
	  
	  Assert.assertTrue("programName".contains(programPage.programNameArrowIconValidation()));
	  Assert.assertTrue("Program Description".contains(programPage.programDescriptionArrowIconValidation()));
	  Assert.assertTrue("Program Status".contains(programPage.programStatusArrowIconValidation()));
	  Assert.assertTrue("Edit / Delete".equals(programPage.editDeleteHeaderValidation()));
	}
	
	// Pagenation
	@Then("Admin should see the text as Showing x to y of z entries along with Pagination icon below the table")
	public void admin_should_see_the_text_as_showing_x_to_y_of_z_entries_along_with_pagination_icon_below_the_table() {
		String rowsInfo = programPage.numberOfRecordstextValidate();
		String[] items = rowsInfo.split(" "); // Showing 1 to 5 of 7 entries
	    Assert.assertTrue(items.length == 7 && items[0].equals("Showing")&& items[2].equals("to")&& items[4].equals("of")&& items[6].equals("entries"));
	}

	@Then("Admin should see check box on the left side in all rows of the data table")
	public void admin_should_see_check_box_on_the_left_side_in_all_rows_of_the_data_table() {
		pageUtils = getCurrentPageUtils();
		for (int i = 1; i<=pageUtils.getRecordsPerPage(); i++) {
			WebElement rowCheckBox = driver.findElement(By.xpath("/html/body/app-root/app-program/div/mat-card/mat-card-content/p-table/div/div[1]/table/tbody/tr["+ i +"]/td[1]/p-tablecheckbox/div/div[2]"));
			Assert.assertTrue(rowCheckBox.isDisplayed());	
		}
	    
	}
	
    //-->here is bug
	@Then("Admin should see the Edit and Delete buttons on each row of the data table")
	public void admin_should_see_the_edit_and_delete_buttons_on_each_row_of_the_data_table() {
		pageUtils = getCurrentPageUtils();
		
		for (int i = 1; i<=pageUtils.getRecordsPerPage(); i++) {
			WebElement rowEditIcon = driver.findElement(By.xpath("/html/body/app-root/app-program/div/mat-card/mat-card-content/p-table/div/div[1]/table/tbody/tr["+ i +"]/td[5]/div/span/button[1]/span[1]"));
			Assert.assertTrue(rowEditIcon.isDisplayed());	
		}

     	for (int i = 1; i<=pageUtils.getRecordsPerPage(); i++) {
			WebElement rowdeleteicon = driver.findElement(By.xpath("/html/body/app-root/app-program/div/mat-card/mat-card-content/p-table/div/div[1]/table/tbody/tr["+ i +"]/td[5]/div/span/button[2]/span[1]"));
			Assert.assertTrue(rowdeleteicon.isDisplayed());
     	}
	}

	@Then("Admin should see the number of records displayed on the page are {int}")
	public void admin_should_see_the_number_of_records_displayed_on_the_page_are(Integer int1) {
		String[] items = programPage.numberOfRecordstextValidate().split(" "); // Showing 1 to 5 of 7 entries
		Assert.assertTrue(items.length == 7 && items[1].equals("1") && items[3].equals("5"));
	}
	
	//Add A Program
	@Given("Admin is on Manage Program Page")
	public void admin_is_on_manage_program_page() {
		programPage.manageProgramIsDisplayed();
		Assert.assertEquals(programPage.manageProgramIsDisplayed(),"Manage Program");
	}
	
	@When("Admin clicks <A New Program> button")
	public void admin_clicks_a_new_program_button() {
		programPage.clickOnaddProgram();
	}

	@Then("Admin should see a popup open for Program details with empty form along with <SAVE> and <CANCEL> button and Close\\(X) Icon on the top right corner of the window")
	public void admin_should_see_a_popup_open_for_program_details_with_empty_form_along_with_save_and_cancel_button_and_close_x_icon_on_the_top_right_corner_of_the_window() {
		// Check if all elements exists
		programPage.closeIsDisplayed();
		Assert.assertTrue(programPage.closeIsDisplayed());
		
		programPage.programNameValidate();
		Assert.assertTrue(programPage.programNameValidate().startsWith("Name"));
		
		//Assert.assertTrue(programPage.programTextFieldIsDisplayed());
		Assert.assertEquals(programPage.programTextFieldValidate(), "");

		//Assert.assertTrue(programPage.descriptionValidate().startsWith("Description"));

		Assert.assertTrue(programPage.descriptionTextFieldIsDisplayed());
		
		Assert.assertTrue(programPage.inActiveStatusIsDisplayed());
		
		Assert.assertTrue(programPage.cancelIsDisplayed());
		Assert.assertTrue(programPage.saveIsDisplayed());
	}
	
	@Then("Admin should see two input fields and their respective text boxes in the program details window")
	public void admin_should_see_two_input_fields_and_their_respective_text_boxes_in_the_program_details_window() {
		Assert.assertTrue(programPage.programNameValidate().startsWith("Name"));
		Assert.assertTrue(programPage.programTextFieldIsDisplayed());
		Assert.assertEquals(programPage.programTextFieldValidate(), "");
		
		Assert.assertTrue(programPage.descriptionValidate().startsWith("Description"));//-->false
		Assert.assertTrue(programPage.descriptionTextFieldIsDisplayed());
		Assert.assertEquals(programPage.descriptionTextFieldValidate(), "");
	}

	@Then("Admin should see two radio button for Program Status")
	public void admin_should_see_two_radio_button_for_program_status() {
		Assert.assertTrue(programPage.activeStatusIsDisplayed());
		Assert.assertTrue(programPage.inActiveStatusIsDisplayed());
	}
	
	@Given("Admin is on {string} Popup window")
	public void admin_is_on_popup_window(String string) {
		programPage.clickOnaddProgram();
	}

	@When("Admin clicks <Save>button without entering any data")
	public void admin_clicks_save_button_without_entering_any_data() {
		programPage.clickSave();
	
	}

	@Then("Admin gets a Error message alert")
	public void admin_gets_a_error_message_alert() {
		Assert.assertTrue(programPage.programNameValidate().startsWith("Name"));
		
		Assert.assertEquals(programPage.getProgramRequiredMsg(), "Program name is required.");
		
		Assert.assertEquals(programPage.descriptionTextFieldValidate(), "");
		
		Assert.assertEquals(programPage.getprogramDescRequiredMsg(), "Description is required.");
		
		Assert.assertEquals(programPage.getStatusMsg(),"Status is required.");
	    
	}
	
	//Proram Name From Excel
	@When("Admin enter value only in ProgramName using {string} and {int}")
	public void admin_enter_value_only_in_program_name_using_and(String SheetName, Integer rowNumber) throws InvalidFormatException, IOException {
		ExcelReaderListMap reader = new ExcelReaderListMap();
		List<Map<String, String>> testData = reader.getData(BaseClass.eXCEL, "Program");
		String ProgramName = testData.get(rowNumber).get("ProgramName"); 
		programPage.enterNameTextField(ProgramName);
	}

	@When("Admin clicks Save Button")
	public void admin_clicks_save_button() {
		WebElement save = driver.findElement(By.xpath("//*[@id=\"saveProgram\"]"));
		save.click();
		programPage.clickSave();
	}
	
    //Excel Reader Program Description
	@When("Admin enter value only in Program description using {string} and {int}")
	public void admin_enter_value_only_in_program_description_using_and(String SheetName, Integer rowNumber)throws InvalidFormatException, IOException
	{
		ExcelReaderListMap reader = new ExcelReaderListMap();
		List<Map<String, String>> testData = reader.getData(BaseClass.eXCEL, "Program");
		String ProgramDescription = testData.get(rowNumber).get("ProgramDescription"); 
		programPage.enterProgramDescriptionTextField(ProgramDescription);
	}
	
	@Then("Admin gets a message alert {string}ProgramName")//program des
	public void admin_gets_a_message_alert_ProgramName(String string) {
         Assert.assertEquals(programPage.getProgramRequiredMsg(), "Program name is required.");
	}
	
	@Then("admin gets a message alert for description")
	public void admin_gets_a_message_alert_for_description() {
		WebElement programDescRequiredMsg = driver.findElement(By.xpath("/html/body/app-root/app-program/p-dialog/div/div/div[2]/div[2]/small"));
		System.out.println("Program Description Required Message: " + programDescRequiredMsg.getText());
		Assert.assertEquals(programDescRequiredMsg.getText(), "Description is required.");
	}

	@When("Admin selects only Status")
	public void admin_selects_only_status() {
		programPage.clickActivel();
	}
	
	@Then("Admin gets a message alert {string} and {string}")
	public void admin_gets_a_message_alert_and(String string, String string2) {
	     Assert.assertEquals(programPage.getProgramRequiredMsg(), "Program name is required.");
	     Assert.assertEquals(programPage.getprogramDescRequiredMsg(), "Description is required.");
	}
	
	@When("Admin enters only numbers or special char in name and desc column {string} and {int}")
	public void admin_enters_only_numbers_or_special_char_in_name_and_desc_column_and(String SheetName, Integer rowNumber) throws InvalidFormatException, IOException {
		ExcelReaderListMap reader = new ExcelReaderListMap();
		List<Map<String, String>> testData = reader.getData(BaseClass.eXCEL, "Program");
		String ProgramName = testData.get(rowNumber).get("ProgramName"); 
		programPage.enterNameTextField(ProgramName);
		String ProgramDescription = testData.get(rowNumber).get("ProgramDescription"); 
		programPage.enterProgramDescriptionTextField(ProgramDescription);
	}
	
	@Then("Admin gets a Error message alert SpecialCharecters")
	public void admin_gets_a_error_message_alert_SpecialCharecters() {
		Assert.assertEquals(programPage.getprogramRequiredMsgSpecialCharacter(), "This field should start with an alphabet, no special char and min 2 char.");
		
		Assert.assertEquals(programPage.getprogramDescRequiredMsgSpecialCharacter(), "This field should start with an alphabet and min 2 char.");
	}
	
	@When("Admin clicks Close\\(X) Icon on Program Details form")
	public void admin_clicks_cancelor_close_x_icon_on_program_details_form() {
		programPage.clickOnClose();
	}
	
	@Then("Program Details popup window should be closed without savings")
	public void program_details_popup_window_should_be_closed_without_savings() {
		Assert.assertEquals(programPage.manageProgramIsDisplayed(),"Manage Program");
	}

	@When("Enter all the required fields with valid values {string} and {int}")
	public void enter_all_the_required_fields_with_valid_values_and(String sheetName, Integer rowNumber) throws Exception {
		ExcelReaderListMap reader = new ExcelReaderListMap();
		List<Map<String, String>> testData = reader.getData(BaseClass.eXCEL, sheetName);
		String programName = testData.get(rowNumber).get("ProgramName"); // Column heading
		programPage.enterNameTextField(programName);
		String programDescription = testData.get(rowNumber).get("ProgramDescription"); // Column heading
		programPage.enterProgramDescriptionTextField(programDescription);
		String status = testData.get(rowNumber).get("Status");
		if ("Active".equalsIgnoreCase(status)) {
			programPage.clickActivel();
		} else if ("Inactive".equalsIgnoreCase(status)) {
			programPage.clickInactive();
		} else if (status == null || "".equals(status.trim())) {
			programPage.clickActivel();
		} else {
			throw new Exception("Invalid Status:" + status);
		}
		
		programPage.clickSave();
	}

	@Then("Admin gets a message {string} alert and able to see the new program added in the data table")
	public void admin_gets_a_message_alert_and_able_to_see_the_new_program_added_in_the_data_table(String string) throws Exception {
		programPage.validateAddProgramSuccess();
	}

	@When("Admin clicks <Cancel>button")
	public void admin_clicks_cancel_button() {
		programPage.clickCancel();
	}

	@Then("Admin can see the Program details popup disappears without creating any program")
	public void admin_can_see_the_program_details_popup_disappears_without_creating_any_program() {
		Assert.assertEquals(programPage.manageProgramIsDisplayed(),"Manage Program");
	}
	
	@And("Admin clicks <Edit> button on the data table for any row")
	public void admin_clicks_edit_button_on_the_data_table_for_any_row() {
		pageUtils = getCurrentPageUtils();
		programPage.clickOnEditIcon(pageUtils.getRecordsPerPage());
	}

	@Then("Admin should see a popup open for Program details to edit")
	public void admin_should_see_a_popup_open_for_program_details_to_edit() {
		
		Assert.assertTrue(programPage.closeIsDisplayed());
		
		Assert.assertTrue(programPage.programNameValidate().startsWith("Name"));
		Assert.assertTrue(programPage.programTextFieldIsDisplayed());
		//Assert.assertTrue(!"".equals(programPage.programTextFieldValidate())); // TODO
		
		Assert.assertTrue(programPage.descriptionValidate().startsWith("Description"));
		Assert.assertTrue(programPage.descriptionTextFieldIsDisplayed());
		Assert.assertTrue(!"".equals(programPage.descriptionValidate()));
		
		Assert.assertTrue(programPage.activeStatusIsDisplayed());
		Assert.assertTrue(programPage.inActiveStatusIsDisplayed());
		
		Assert.assertTrue(programPage.cancelIsDisplayed());
		Assert.assertTrue(programPage.saveIsDisplayed());
	}

	@When("Admin edits the Name column using {string} and {int}")
	public void admin_edits_the_name_column_using_and(String sheetName, Integer rowNumber) throws InvalidFormatException, IOException {
		ExcelReaderListMap reader = new ExcelReaderListMap();
		List<Map<String, String>> testData = reader.getData(BaseClass.eXCEL, "Program");
		String programName = testData.get(rowNumber).get("ProgramName"); 
		programPage.clearNameTextField();
		programPage.enterNameTextField(programName);
	}

	@Then("Admin gets a message {string} alert and able to see the updated name in the table for the particular program")
	public void admin_gets_a_message_alert_and_able_to_see_the_updated_name_in_the_table_for_the_particular_program(String string) throws Exception {
		programPage.validateEditProgramSuccess();
	}

	@When("Admin edits the Description column in using {string} and {int}")
	public void admin_edits_the_description_column_in_using_and(String sheetName, Integer rowNumber) throws InvalidFormatException, IOException {
		ExcelReaderListMap reader = new ExcelReaderListMap();
		List<Map<String, String>> testData = reader.getData(BaseClass.eXCEL, "Program");
		String programDesc = testData.get(rowNumber).get("ProgramDescription"); 
		programPage.clearProgramDescriptionTextField();
		programPage.enterProgramDescriptionTextField(programDesc);
	}

	@Then("Admin gets a message {string} alert and able to see the updated description in the table for the particular program")
	public void admin_gets_a_message_alert_and_able_to_see_the_updated_description_in_the_table_for_the_particular_program(String string) throws Exception {
		programPage.validateEditProgramSuccess();
	}

	@When("Admin changes the Status")
	public void admin_changes_the_status() {
//		System.out.println("activeStatusIsSelected" + programPage.activeStatusIsSelected());
//		System.out.println("InactiveStatusIsSelected" + programPage.inActiveStatusIsSelected());
//	    if (programPage.activeStatusIsSelected()) {
//	    	programPage.clearActivel();
//	    	programPage.clickInactive();
//	    } else if (programPage.inActiveStatusIsSelected()) {
//	    	programPage.clearInactive();
//	    	programPage.clickActivel();
//	    } else {
//	    	programPage.clickActivel();
//	    }
//	    
//	    System.out.println("After activeStatusIsSelected" + programPage.activeStatusIsSelected());
//		System.out.println("After InactiveStatusIsSelected" + programPage.inActiveStatusIsSelected());
		
		programPage.clickInactive();
	}

	@Then("Admin gets a message {string} alert and able to see the updated status in the table for the particular program")
	public void admin_gets_a_message_alert_and_able_to_see_the_updated_status_in_the_table_for_the_particular_program(String string) throws Exception {
		programPage.validateEditProgramSuccess();
	}

	@When("Admin edit special char in name and desc column {string} and {int}")
	public void admin_edit_special_char_in_name_and_desc_column_and(String sheetName, Integer rowNumber) throws InvalidFormatException, IOException {
		ExcelReaderListMap reader = new ExcelReaderListMap();
		List<Map<String, String>> testData = reader.getData(BaseClass.eXCEL, "Program");
		
		String programName = testData.get(rowNumber).get("ProgramName"); 
		String programDesc = testData.get(rowNumber).get("ProgramDescription"); 
		
		programPage.clearNameTextField();
		programPage.clearProgramDescriptionTextField();
		
		programPage.enterNameTextField(programName);
		programPage.enterProgramDescriptionTextField(programDesc);
	}

	@Then("Admin gets a Error message alert Edit program")
	public void admin_gets_a_error_message_alert_edit_program() {
		programPage.getprogramRequiredMsgSpecialCharacter();
		programPage.getprogramDescRequiredMsgSpecialCharacter();
		Assert.assertEquals(programPage.getprogramRequiredMsgSpecialCharacter(), "This field should start with an alphabet, no special char and min 2 char.");
		Assert.assertEquals(programPage.getprogramDescRequiredMsgSpecialCharacter(), "This field should start with an alphabet and min 2 char.");
	}

	@When("Admin clicks <Cancel>button on edit popup")
	public void admin_clicks_cancel_button_on_edit_popup() {
		programPage.clickCancel();
	}

	@Then("Admin can see the Program details popup disappears and can see nothing changed for particular program")
	public void admin_can_see_the_program_details_popup_disappears_and_can_see_nothing_changed_for_particular_program() {
		Assert.assertEquals(programPage.manageProgramIsDisplayed(),"Manage Program");
	}

	@When("Admin clicks <Save>button on edit popup")
	public void admin_clicks_save_button_on_edit_popup() {
		programPage.clickSave();
	}
	
	@Then("Admin gets a message {string} alert and able to see the updated details in the table for the particular program")
	public void admin_gets_a_message_alert_and_able_to_see_the_updated_details_in_the_table_for_the_particular_program(String string) throws Exception {
		programPage.validateEditProgramSuccess();
	}

	// Delete Program step definitions
	@When("Admin clicks <Delete> button on the data table for any row")
	public void admin_clicks_delete_button_on_the_data_table_for_any_row() {
		pageUtils = getCurrentPageUtils();
		programPage.clickOnDeleteIcon(pageUtils.getRecordsPerPage());
	}
	
	@Then("Admin should see a alert open with heading {string} along with  <YES> and <NO> button for deletion")
	public void admin_should_see_a_alert_open_with_heading_along_with_yes_and_no_button_for_deletion(String string) {
		// Confirm header
		Assert.assertEquals(programPage.getConfrimText(), "Confirm");
		
		// No button
		Assert.assertTrue(programPage.isNoButtonDisplayed());
		Assert.assertEquals(programPage.getNoButtonText(), "No");
		
		// Yes button
		Assert.assertTrue(programPage.isYesButtonDisplayed());
		Assert.assertEquals(programPage.getYesButtonText(), "Yes");
	    
		// Close icon
		Assert.assertTrue(programPage.isDeleteCloseIconDisplayed());
	}
	
	@Then("Admin should see a message {string}")
	public void admin_should_see_a_message(String string) {
		// Confrim message
		Assert.assertTrue(programPage.getConfrimMsgText().contains("Are you sure you want to delete "));
	}
	
	@Given("Admin is on Confirm Deletion alert")
	public void admin_is_on_confirm_deletion_alert() {
		pageUtils = getCurrentPageUtils();
		programPage.clickOnDeleteIcon(pageUtils.getRecordsPerPage());
	}
	
	@When("Admin clicks <YES> button on the alert")
	public void admin_clicks_yes_button_on_the_alert() {
		programPage.clickOnYesButton();
	}
	
	@Then("Admin gets a message {string} alert and able to see that program deleted in the data table")
	public void admin_gets_a_message_alert_and_able_to_see_that_program_deleted_in_the_data_table(String string) throws Exception {
		programPage.validateDeleteProgramSuccess();
	}
	
	@When("Admin clicks <NO> button on the alert")
	public void admin_clicks_no_button_on_the_alert() {
		programPage.clickOnNoButton();
	}
	
	@Then("Admin can see the deletion alert disappears without deleting")
	public void admin_can_see_the_deletion_alert_disappears_without_deleting() {
		Assert.assertEquals(programPage.manageProgramIsDisplayed(),"Manage Program");
	}
	
	@When("Admin clicks Close\\(X) Icon on Deletion alert")
	public void admin_clicks_close_x_icon_on_deletion_alert() {
		programPage.clickOnDeleteCloseIcon();
	}
	
	@Then("Admin can see the deletion alert disappears without any changes")
	public void admin_can_see_the_deletion_alert_disappears_without_any_changes() {
		Assert.assertEquals(programPage.manageProgramIsDisplayed(),"Manage Program");
	}
	// End of Delete Program step definitions
}
