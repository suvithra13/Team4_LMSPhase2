package com.lms.steps;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import com.lms.utilities.BaseClass;
import com.lms.utilities.*;
import com.lms.utilities.*;
import com.lms.factory.*;
import com.lms.PageObjects.Program_POM;
import com.lms.utilities.PageUtils;
import com.lms.PageObjects.*;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ProgramValidationSteps extends BaseClass{
	WebDriver driver;
	public Program_POM programPage;//ProgramPage object
	public List<Map<String, String>> programTestData = null;
	private PageUtils pageUtils = null;
	
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
		
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://lms-frontend-api-hackathon-apr-326235f3973d.herokuapp.com");
		programPage = new Program_POM(driver);
		//programPage = new Program_POM(DriverClass.getDriver());
	}
	
	@When("Admin enters Username {string} and Password {string} and logged in")
	public void admin_enters_username_and_password_and_logged_in(String Uname, String Pwd) {
		programPage.enterUserName(Uname);
		programPage.enterPasswordField(Pwd);
		programPage.clickOnLoginButton();
	}

//	@Then("Admin suceessfully logged in")
//	public void admin_suceessfully_logged_in() {
//	
//		String currentUrl =driver.getCurrentUrl();
//		Assert.assertEquals("https://lms-frontend-api-hackathon-apr-326235f3973d.herokuapp.com/login",currentUrl);
//	}

	@And("Admin is on dashboard page after Login")
	public void admin_is_on_dashboard_page_after_login() {
		//Assert.assertTrue(programPage.headerIsDisplayed());
		String dashboardTitle = driver.getTitle();
		System.out.println("dashboardTitle=" + dashboardTitle);
		Assert.assertEquals("LMS", dashboardTitle);
	}

	@Then("Admin clicks {string} on the navigation bar")
	public void admin_clicks_on_the_navigation_bar(String ProgramButton) {
		programPage.clickOnProgramButton();
	}

	@Then("Admin should see URL with {string}")
	public void admin_should_see_url_with(String manageProgram) {
		String currentUrl =driver.getCurrentUrl();
		System.out.println(currentUrl);
		String ExpectedUrl ="https://lms-frontend-api-hackathon-apr-326235f3973d.herokuapp.com/login";
		Assert.assertEquals(ExpectedUrl,currentUrl);
		
		programPage.manageProgramIsDisplayed();
		Assert.assertEquals(programPage.manageProgramIsDisplayed(),"Manage Program");
	}
	
	//3
	@Then("Admin should see a heading with text {string} on the page")
	public void admin_should_see_a_heading_with_text_on_the_page(String string) {
		//programPage.headerIsDisplayed()
		Assert.assertEquals(programPage.manageProgramIsDisplayed(),"Manage Program");
	 }
	
	@Then("Admin should see the footer as In total there are z programs")
	public void admin_should_see_the_footer_as_in_total_there_are_z_programs() {
		System.out.println("Footer Message ------>"+programPage.getfooterMessage());
		Assert.assertTrue(programPage.getfooterMessage().startsWith("In total there are ") && programPage.getfooterMessage().endsWith(" programs."));
	}
	
    //Delete--->need to check
	@Then("Admin should see a Delete button on the top left hand side as Disabled")
	public void admin_should_see_a_delete_button_on_the_top_left_hand_side_as_disabled() {
		//WebElement deletebutton=driver.findElement(By.xpath("/html/body/app-root/app-program/div/mat-card/mat-card-title/div[2]/div[1]/button"));
	    Assert.assertFalse(programPage.deleteButtonValidation());
	}
	
	//Search
	@Then("Admin should see Search bar with text as {string}")
	public void admin_should_see_search_bar_with_text_as(String string) {
		//WebElement searchField = driver.findElement(By.id("filterGlobal"));
		Assert.assertTrue(programPage.searchBoxValidation());  
	}
	
    //+A new Program
	@Then("Admin should see a {string} button on the program page above the data table")
	public void admin_should_see_a_button_on_the_program_page_above_the_data_table(String string) {
		//String addPrpogramButton = driver.findElement(By.className("p-button-label")).getText();
		
		//Here is a bug
		//Assert.assertTrue("".equals(addPrpogramButton));
		Assert.assertEquals(programPage.addProgramButtonValidation(), "");
	}
	
	@Then("Admin should see data table on the Manage Program Page with column headers")
	public void dmin_should_see_data_table_on_the_manage_program_page_with_column_headers() {

		Assert.assertTrue("Program Name".equals(programPage.programNameHeaderValidation()));
		Assert.assertTrue("Program Description".equals(programPage.programDescriptionHeaderValidation()));
		
		//WebElement programStatusHeader = driver.findElement(By.xpath("/html[1]/body[1]/app-root[1]/app-program[1]/div[1]/mat-card[1]/mat-card-content[1]/p-table[1]/div[1]/div[1]/table[1]/thead[1]/tr[1]/th[4]"));
		Assert.assertTrue("Program Status".equals(programPage.programStatusHeaderValidation()));
		 
		//WebElement editDeleteHeader = driver.findElement(By.xpath("/html[1]/body[1]/app-root[1]/app-program[1]/div[1]/mat-card[1]/mat-card-content[1]/p-table[1]/div[1]/div[1]/table[1]/thead[1]/tr[1]/th[5]"));
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
			WebElement rowEditIcon = driver.findElement(By.xpath("/html/body/app-root/app-program/div/mat-card/mat-card-content/p-table/div/div[1]/table/tbody/tr["+ i +"]/td[5]/div/span/button[1]/span[1]"));
			Assert.assertTrue(rowEditIcon.isDisplayed());	
		}
	
     	//Assert.assertTrue(programPage.editiconValidation());
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

		Assert.assertTrue(programPage.descriptionValidate().startsWith("Description"));
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
		
//		WebElement description = driver.findElement(By.xpath("/html/body/app-root/app-program/p-dialog/div/div/div[2]/div[2]/label"));
//		Assert.assertTrue(description.getText().startsWith("Description"));
		Assert.assertTrue(programPage.descriptionTextFieldValidate().startsWith("Description"));
		
		//WebElement descriptionTextField = driver.findElement(By.xpath("//*[@id=\"programDescription\"]"));
		Assert.assertTrue(programPage.descriptionTextFieldIsDisplayed());
		
		Assert.assertEquals(programPage.descriptionValidate(), "");
	}

	@Then("Admin should see two radio button for Program Status")
	public void admin_should_see_two_radio_button_for_program_status() {
//		WebElement active = driver.findElement(By.xpath("/html/body/app-root/app-program/p-dialog/div/div/div[2]/div[3]/div[2]/p-radiobutton/div/div[2]"));
//		Assert.assertTrue(active.isDisplayed());
		Assert.assertTrue(programPage.activeStatusIsDisplayed());

//		WebElement inactive = driver.findElement(By.xpath("/html/body/app-root/app-program/p-dialog/div/div/div[2]/div[3]/div[3]/p-radiobutton/div/div[2]"));
//		Assert.assertTrue(inactive.isDisplayed());
		Assert.assertTrue(programPage.inActiveStatusIsDisplayed());
	}
	
	@Given("Admin is on {string} Popup window")
	public void admin_is_on_popup_window(String string) {
		programPage.clickOnaddProgram();
	}

	@When("Admin clicks <Save>button without entering any data")
	public void admin_clicks_save_button_without_entering_any_data() {
		programPage.clickSave();
//		WebElement save = driver.findElement(By.xpath("//*[@id=\"saveProgram\"]"));
//		save.click();
	}

	@Then("Admin gets a Error message alert")
	public void admin_gets_a_error_message_alert() {
//		WebElement programName = driver.findElement(By.xpath("//*[@id=\"programName\"]"));
//
//		System.out.println("Program Name: " + programName.getText());
//		Assert.assertEquals(programName.getText(), "");
		Assert.assertEquals(programPage.programNameValidate(), "");
		
		
//		WebElement programRequiredMsg = driver.findElement(By.xpath("/html/body/app-root/app-program/p-dialog/div/div/div[2]/div[1]/small"));
//		System.out.println("Program Name Required Message: " + programRequiredMsg.getText());
//		Assert.assertEquals(programRequiredMsg.getText(), "Program name is required.");
		Assert.assertEquals(programPage.getProgramRequiredMsg(), "Program name is required.");
		
//		WebElement programDesc = driver.findElement(By.xpath("//*[@id=\"programDescription\"]"));
//		System.out.println("Program Desc: " + programDesc.getText());
//		Assert.assertEquals(programDesc.getText(), "");
		Assert.assertEquals(programPage.descriptionTextFieldValidate(), "");
		
//		WebElement programDescRequiredMsg = driver.findElement(By.xpath("/html/body/app-root/app-program/p-dialog/div/div/div[2]/div[2]/small"));
//		System.out.println("Program Description Required Message: " + programDescRequiredMsg.getText());
//		Assert.assertEquals(programDescRequiredMsg.getText(), "Description is required.");
		Assert.assertEquals(programPage.getprogramDescRequiredMsg(), "Description is required.");
		
		//WebElement statusMsg = driver.findElement(By.xpath("/html/body/app-root/app-program/p-dialog/div/div/div[2]/small"));
		//Assert.assertEquals(  statusMsg.getText(),"Status Required Message: ");
		Assert.assertEquals(programPage.getStatusMsg(),"Status Required Message: ");
	    
	}
	
	//Proram Name From Excel
	@When("Admin enter value only in ProgramName using {string} and {int}")
	public void admin_enter_value_only_in_program_name_using_and(String SheetName, Integer rowNumber) throws InvalidFormatException, IOException {
		ExcelReaderListMap reader = new ExcelReaderListMap();
		List<Map<String, String>> testData = reader.getData(BaseClass.eXCEL, "Program");
		String ProgramName = testData.get(rowNumber).get("ProgramName"); // Column heading
		//WebElement nameTextField = driver.findElement(By.xpath("//*[@id=\"programName\"]"));
		programPage.enterNameTextField(ProgramName);
	}

	@When("Admin clicks Save Button")
	public void admin_clicks_save_button() {
		WebElement save = driver.findElement(By.xpath("//*[@id=\"saveProgram\"]"));
		save.click();
	   
	}
	
    //Excel Reader Program Description
	@When("Admin enter value only in Program description using {string} and {int}")
	public void admin_enter_value_only_in_program_description_using_and(String SheetName, Integer rowNumber)throws InvalidFormatException, IOException
	{
		ExcelReaderListMap reader = new ExcelReaderListMap();
		List<Map<String, String>> testData = reader.getData(BaseClass.eXCEL, "Program");
		//String ProgramName = testData.get(rowNumber).get("ProgramName"); // Column heading
		String ProgramDescription = testData.get(rowNumber).get("ProgramDescription"); // Column heading
		programPage.enterProgramDescriptionTextField(ProgramDescription);
	    
	}
	
	/*@When("Admin enter value only in username using {string} and {int}")
	public void admin_enter_value_only_in_username_using_and(String SheetName, Integer rowNumber)
			throws InvalidFormatException, IOException {
		ExcelReaderListMap reader = new ExcelReaderListMap();
		LoggerLoad.info("User forgot to enter password");
		List<Map<String, String>> testData = reader.getData(BaseClass.eXCEL, "Login");
		String User_name = testData.get(rowNumber).get("user"); // Column heading
		String Pass_word = testData.get(rowNumber).get("password"); // Column heading
		loginpage.enterUsernamePasswrd(User_name, Pass_word);
	}*/
	

	@Then("Admin gets a message alert {string}ProgramName")//program des
	public void admin_gets_a_message_alert_ProgramName(String string) {
//		WebElement programRequiredMsg = driver.findElement(By.xpath("/html/body/app-root/app-program/p-dialog/div/div/div[2]/div[1]/small"));
//		System.out.println("Program Name Required Message: " + programRequiredMsg.getText());
//		Assert.assertEquals(programRequiredMsg.getText(), "Program name is required.");
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
//		WebElement active = driver.findElement(By.xpath("/html/body/app-root/app-program/p-dialog/div/div/div[2]/div[3]/div[2]/p-radiobutton/div/div[2]"));
//		Assert.assertTrue( active.isDisplayed());
//		active.click();
	}
	
	@Then("Admin gets a message alert {string} and {string}")
	public void admin_gets_a_message_alert_and(String string, String string2) {
//		WebElement programRequiredMsg = driver.findElement(By.xpath("/html/body/app-root/app-program/p-dialog/div/div/div[2]/div[1]/small"));
//		System.out.println("Program Name Required Message: " + programRequiredMsg.getText());
//		Assert.assertEquals(programRequiredMsg.getText(), "Program name is required.");
		Assert.assertEquals(programPage.getProgramRequiredMsg(), "Program name is required.");
//    WebElement programDescRequiredMsg = driver.findElement(By.xpath("/html/body/app-root/app-program/p-dialog/div/div/div[2]/div[2]/small"));
//		
//		System.out.println("Program Description Required Message: " + programDescRequiredMsg.getText());
		//Assert.assertEquals(programDescRequiredMsg.getText(), "Description is required.");
		Assert.assertEquals(programPage.getprogramDescRequiredMsg(), "Description is required.");
	}

//	@When("Admin enters only numbers or special char in name and desc column")
//	public void admin_enters_only_numbers_or_special_char_in_name_and_desc_column() {
//		programPage.enterSpecialOrNumberNameTextField();
//   
//	}
	
	@When("Admin enters only numbers or special char in name and desc column {string} and {int}")
	public void admin_enters_only_numbers_or_special_char_in_name_and_desc_column_and(String SheetName, Integer rowNumber) throws InvalidFormatException, IOException {
		ExcelReaderListMap reader = new ExcelReaderListMap();
		List<Map<String, String>> testData = reader.getData(BaseClass.eXCEL, "Program");
		String ProgramName = testData.get(rowNumber).get("ProgramName"); // Column heading
		//WebElement nameTextField = driver.findElement(By.xpath("//*[@id=\"programName\"]"));
		programPage.enterNameTextField(ProgramName);
		String ProgramDescription = testData.get(rowNumber).get("ProgramDescription"); // Column heading
		programPage.enterProgramDescriptionTextField(ProgramDescription);
	}
	
	@Then("Admin gets a Error message alert SpecialCharecters")
	public void admin_gets_a_error_message_alert_SpecialCharecters() {
//		WebElement programRequiredMsg = driver.findElement(By.xpath("/html/body/app-root/app-program/p-dialog/div/div/div[2]/div[1]/small"));
//		System.out.println("Program Name Required Message: " + programRequiredMsg.getText());
		Assert.assertEquals(programPage.getprogramRequiredMsgSpecialCharacter(), "This field should start with an alphabet, no special char and min 2 char.");
		
//		WebElement programDescRequiredMsgSpecialCharacter = driver.findElement(By.xpath("/html/body/app-root/app-program/p-dialog/div/div/div[2]/div[2]/small"));
//		System.out.println("Program Description Required Message: " + programDescRequiredMsg.getText());
		//AssertEquals(programDescRequiredMsg.getText(), "This field should start with an alphabet and min 2 char.");
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
	public void admin_gets_a_message_alert_and_able_to_see_the_new_program_added_in_the_data_table(String string) {
		driver.switchTo().alert().accept();
		String alertMessage= driver.switchTo().alert().getText();
		System.out.println(alertMessage);
	}

	@When("Admin clicks <Cancel>button")
	public void admin_clicks_cancel_button() {
		programPage.clickCancel();
	}

	@Then("Admin can see the Program details popup disappears without creating any program")
	public void admin_can_see_the_program_details_popup_disappears_without_creating_any_program() {
	    
	}
	
	@And("Admin clicks <Edit> button on the data table for any row")
	public void admin_clicks_edit_button_on_the_data_table_for_any_row() {
		pageUtils = getCurrentPageUtils();
		for (int i = 1; i<=pageUtils.getRecordsPerPage(); i++) {	
			if (i == 1) {
				programPage.clickOnEditIcon();
				break;
			}
		}
	}

	@Then("Admin should see a popup open for Program details to edit")
	public void admin_should_see_a_popup_open_for_program_details_to_edit() {
		
		Assert.assertTrue(programPage.closeIsDisplayed());
		Assert.assertTrue(programPage.programNameValidate().startsWith("Name"));
		Assert.assertTrue(programPage.programTextFieldIsDisplayed());
		Assert.assertEquals(programPage.programTextFieldValidate(), "");
		
		Assert.assertTrue(programPage.descriptionValidate().startsWith("Description"));
		Assert.assertTrue(programPage.descriptionTextFieldIsDisplayed());
		Assert.assertEquals(programPage.descriptionValidate(), "");
		Assert.assertTrue(programPage.activeStatusIsDisplayed());

		Assert.assertTrue(programPage.inActiveStatusIsDisplayed());
		
		Assert.assertTrue(programPage.cancelIsDisplayed());
		Assert.assertTrue(programPage.saveIsDisplayed());
	}

	@Given("Admin is on Program Details Popup window to Edit")
	public void admin_is_on_program_details_popup_window_to_edit() {
		
	   
	}

	@When("Admin edits the Name column using {string} and {int}")
	public void admin_edits_the_name_column_using_and(String string, Integer int1) {
	   
	}

	@Then("Admin gets a message {string} alert and able to see the updated name in the table for the particular program")
	public void admin_gets_a_message_alert_and_able_to_see_the_updated_name_in_the_table_for_the_particular_program(String string) {
	    
	}

	@When("Admin edits the Description column in using {string} and {int}")
	public void admin_edits_the_description_column_in_using_and(String string, Integer int1) {
	    
	}

	@Then("Admin gets a message {string} alert and able to see the updated description in the table for the particular program")
	public void admin_gets_a_message_alert_and_able_to_see_the_updated_description_in_the_table_for_the_particular_program(String string) {
	    
	}

	@When("Admin changes the Status")
	public void admin_changes_the_status() {
	    
	}

	@Then("Admin gets a message {string} alert and able to see the updated status in the table for the particular program")
	public void admin_gets_a_message_alert_and_able_to_see_the_updated_status_in_the_table_for_the_particular_program(String string) {
	    
	}

	@When("Admin edit special char in name and desc column {string} and {int}")
	public void admin_edit_special_char_in_name_and_desc_column_and(String string, Integer int1) {
	   
	}

	@Then("Admin gets a Error message alert Edit program")
	public void admin_gets_a_error_message_alert_edit_program() {
	    
	}

	@When("Admin clicks <Cancel>button on edit popup")
	public void admin_clicks_cancel_button_on_edit_popup() {
	    
	}

	@Then("Admin can see the Program details popup disappears and can see nothing changed for particular program")
	public void admin_can_see_the_program_details_popup_disappears_and_can_see_nothing_changed_for_particular_program() {
	    
	}

	@When("Admin clicks <Save>button on edit popup")
	public void admin_clicks_save_button_on_edit_popup() {
	    
	}

}
