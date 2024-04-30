package com.lms.PageObjects;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class Program_POM {
	//private static Logger log = LogManager.getLogger(Program_POM.class);
	WebDriver driver ;
	
	public Program_POM(WebDriver driver) {
		this.driver = driver; //-->.
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "username")
	@CacheLookup
	WebElement username;
	
	@FindBy(id = "password")
	@CacheLookup
	WebElement password;
	
	@FindBy(id = "login")
	@CacheLookup
	WebElement loginBtn;	
	
	@FindBy(xpath="//span[normalize-space()='LMS - Learning Management System']")
	@CacheLookup
	WebElement dashBoardheader;
	
	@FindBy(xpath="//span[@class='mat-button-wrapper']")
	@CacheLookup
	WebElement programButton;
	
	@FindBy(xpath="//div[normalize-space()='Manage Program']")
	@CacheLookup
	WebElement manageProgram;
	
	@FindBy(xpath="/html[1]/body[1]/app-root[1]/app-program[1]/div[1]/mat-card[1]/mat-card-content[1]/p-table[1]/div[1]/div[2]/div[1]")
	@CacheLookup
	WebElement footerMessage;
	
	@FindBy(xpath="/html[1]/body[1]/app-root[1]/app-program[1]/div[1]/mat-card[1]/mat-card-content[1]/p-table[1]/div[1]/p-paginator[1]/div[1]/span[1]")
	@CacheLookup
	WebElement numberOfRecordstext;
	
	@FindBy(xpath="/html/body/app-root/app-program/div/mat-card/mat-card-title/div[2]/div[1]/button")
	@CacheLookup
	WebElement deleteButton;
	
	@FindBy(id="filterGlobal")
	@CacheLookup
	WebElement searchBox;
	
	@FindBy(xpath="/html/body/app-root/app-program/div/mat-card/mat-card-title/div[2]/div[3]/button/span[2]")
	@CacheLookup
	WebElement addProgramButton;
	
	@FindBy(xpath="/html[1]/body[1]/app-root[1]/app-program[1]/div[1]/mat-card[1]/mat-card-content[1]/p-table[1]/div[1]/div[1]/table[1]/thead[1]/tr[1]/th[2]")
	@CacheLookup
	WebElement programNameHeader;
	
	@FindBy(xpath="/html[1]/body[1]/app-root[1]/app-program[1]/div[1]/mat-card[1]/mat-card-content[1]/p-table[1]/div[1]/div[1]/table[1]/thead[1]/tr[1]/th[3]")
	@CacheLookup
	WebElement programDescriptionHeader;
	
	@FindBy(xpath="/html[1]/body[1]/app-root[1]/app-program[1]/div[1]/mat-card[1]/mat-card-content[1]/p-table[1]/div[1]/div[1]/table[1]/thead[1]/tr[1]/th[4]")
	@CacheLookup
	WebElement programStatusHeader;
	
	@FindBy(xpath="/html[1]/body[1]/app-root[1]/app-program[1]/div[1]/mat-card[1]/mat-card-content[1]/p-table[1]/div[1]/div[1]/table[1]/thead[1]/tr[1]/th[5]")
	@CacheLookup
	WebElement editDeleteHeader;
	
	//Arrows
	@FindBy(xpath="/html/body/app-root/app-program/div/mat-card/mat-card-content/p-table/div/div[1]/table/thead/tr/th[2]/p-sorticon/i")
	@CacheLookup
	WebElement programNameArrowIcon;
	
	@FindBy(xpath="/html/body/app-root/app-program/div/mat-card/mat-card-content/p-table/div/div[1]/table/thead/tr/th[3]/p-sorticon/i")
	@CacheLookup
	WebElement programDescriptionArrowIcon;
	
	@FindBy(xpath="/html/body/app-root/app-program/div/mat-card/mat-card-content/p-table/div/div[1]/table/thead/tr/th[4]/p-sorticon")
	@CacheLookup
	WebElement programStatusArrowIcon;
	
	@FindBy(xpath="//div[@class='p-checkbox-box']")
	@CacheLookup
	WebElement checkBox;
	
	@FindBy(xpath="//*[@id=\"editProgram\"]/span[2]")
	@CacheLookup
	WebElement editicon;
	
	@FindBy(xpath="//*[@id=\"deleteProgram\"]/span[2]")
	@CacheLookup
	WebElement deleteicon;
	/*public String searchAndMaxErrorMessage() {
		return searchAndMaxErrorMessage.getText();
		
	}
	public void userGetErrormessage() {
		Assert.assertTrue("Error occurred during submission".equals(searchAndMaxErrorMessage()));
		
	}*/
	@FindBy(xpath="/html/body/app-root/app-program/p-dialog/div/div/div[1]/div/button")
	WebElement close;
	
	public String numberOfRecordstextValidate() {
		return numberOfRecordstext.getText();
	}
	public String getfooterMessage() {
		return footerMessage.getText();
	}

	public void enterUserName(String Uname) {
		username.sendKeys("sdetorganizers@gmail.com");
	}
	public void enterPasswordField(String Pwd) {
		password.sendKeys("UIHackathon@02");
	}
	public void clickOnLoginButton() {
		loginBtn.click();
	}
	public void headerIsDisplayed() {
		dashBoardheader.isDisplayed();
	}
	public void clickOnProgramButton() throws InterruptedException {
		Thread.sleep(1000);
		programButton.click();
	}
	public String manageProgramIsDisplayed() {
		return manageProgram.getText();
	}
	public boolean deleteButtonValidation() {
		return deleteButton.isEnabled();
	}
	public boolean searchBoxValidation() {
		return searchBox.isDisplayed();
	}
	public String addProgramButtonValidation() {
		return addProgramButton.getText();
	}
	public void clickOnaddProgram() {
		addProgramButton.click();
	}
	
	public String programNameHeaderValidation() {
		return programNameHeader.getText();
	}
	
	public String programDescriptionHeaderValidation() {
		return programDescriptionHeader.getText();
	}
	public String programStatusHeaderValidation() {
		return programStatusHeader.getText();
	}
	
	public String editDeleteHeaderValidation() {
		return editDeleteHeader.getText();
	}
	public String programNameArrowIconValidation() {
		return programNameArrowIcon.getText();
	}
	public String programDescriptionArrowIconValidation() {
		return programDescriptionArrowIcon.getText();
	}
	public String programStatusArrowIconValidation() {
		return programStatusArrowIcon.getText();
	}
	
	public boolean checkBoxValidation() {
		return checkBox.isDisplayed();
	}
	public boolean editiconValidation() {
		return editicon.isDisplayed();
	}
	public boolean deleteiconValidation() {
		return deleteicon.isDisplayed();
	}
	public boolean closeIsDisplayed() {
		return close.isDisplayed();
	}
	
	//Add Program Objects
	@FindBy(xpath="//*[@id='programName']")
	@CacheLookup
	WebElement nameTextField;
	
	@FindBy (xpath="//*[@id=\"programDescription\"]")
	@CacheLookup
	WebElement programDescriptionTextField;
	
	@FindBy(xpath="/html/body/app-root/app-program/p-dialog/div/div/div[2]/div[1]/label")
	@CacheLookup
	WebElement namevalidate;
	
	@FindBy(xpath="/html/body/app-root/app-program/p-dialog/div/div/div[2]/div[2]/label")
	@CacheLookup
	WebElement description;
	
	@FindBy(xpath="/html/body/app-root/app-program/p-dialog/div/div/div[2]/div[3]/div[2]/p-radiobutton/div/div[2]")
	@CacheLookup
	WebElement activeStatus;
	
	@FindBy(xpath="/html/body/app-root/app-program/p-dialog/div/div/div[2]/div[3]/div[3]/p-radiobutton/div/div[2]")
	@CacheLookup
	WebElement inActiveStatus;
	
	@FindBy(xpath="/html/body/app-root/app-program/p-dialog/div/div/div[3]/button[1]")
	@CacheLookup
	WebElement  cancel;
	
	@FindBy(xpath="//*[@id=\"saveProgram\"]")
	WebElement save;
	
	@FindBy(xpath="/html/body/app-root/app-program/p-dialog/div/div/div[2]/div[1]/small")
	@CacheLookup
	WebElement programRequiredMsg;
	
	@FindBy(xpath="/html/body/app-root/app-program/p-dialog/div/div/div[2]/div[2]/small")
	@CacheLookup
	WebElement programDescRequiredMsg;
	
	@FindBy(xpath="/html/body/app-root/app-program/p-dialog/div/div/div[2]/small")
	@CacheLookup
	WebElement statusMsg;
	
	@FindBy(xpath="/html/body/app-root/app-program/p-dialog/div/div/div[2]/div[1]/small")
	@CacheLookup
	WebElement programRequiredMsgSpecialCharacter;
	
	@FindBy(xpath="/html/body/app-root/app-program/p-dialog/div/div/div[2]/div[2]/small")
	@CacheLookup
	WebElement programDescRequiredMsgSpecialCharacter;
	
	public String getprogramDescRequiredMsgSpecialCharacter() {
		return programDescRequiredMsgSpecialCharacter.getText();
	}
	public String getprogramRequiredMsgSpecialCharacter() {
		return programRequiredMsgSpecialCharacter.getText();
	}
	
	public String getStatusMsg() {
		return statusMsg.getText();
	}
	public String getprogramDescRequiredMsg() {
		return programDescRequiredMsg.getText();
	}
	
	public void clearNameTextField() {
		nameTextField.clear();
	}
	public void enterNameTextField(String giveprogramName) {
		nameTextField.sendKeys(giveprogramName);
		//nameTextField.sendKeys(syJava123);
	}
	
	public void enterSpecialOrNumberNameTextField() {
		//nameTextField.sendKeys(giveprogramName);
		nameTextField.sendKeys("123");
	}
	public void enterSpecialCharecterOrNumberProgramDescriptionTextField() {
		programDescriptionTextField.sendKeys("@#$");
	}
	public void clearProgramDescriptionTextField() {
		programDescriptionTextField.clear();
	}
	public void enterProgramDescriptionTextField(String giveProgramDescription) {
		programDescriptionTextField.sendKeys(giveProgramDescription);
	}
	public String programNameValidate() {
		return namevalidate.getText();
	}
	public boolean programTextFieldIsDisplayed() {
		return nameTextField.isDisplayed();
	}
	public String programTextFieldValidate() {
		return nameTextField.getText();
	}
	public String descriptionValidate() {
		return description.getText();
	}
	public boolean descriptionTextFieldIsDisplayed() {
		return programDescriptionTextField.isDisplayed();
	}
	public String descriptionTextFieldValidate() {
		return programDescriptionTextField.getText();
	}
	public boolean activeStatusIsDisplayed() {
		return activeStatus.isDisplayed();
	}
	public boolean inActiveStatusIsDisplayed() {
		return inActiveStatus.isDisplayed();
	}
	public boolean activeStatusIsSelected() {
		return activeStatus.isSelected();
	}
	public boolean inActiveStatusIsSelected() {
		return inActiveStatus.isSelected();
	}
	public void clearActivel() {
		activeStatus.clear();
	}
	public void clearInactive() {
		inActiveStatus.clear();
	}
	public void clickActivel() {
		activeStatus.click();
	}
	public void clickInactive() {
		inActiveStatus.click();
	}
	public boolean cancelIsDisplayed() {
		return cancel.isDisplayed();
	}
	public boolean saveIsDisplayed() {
		return save.isDisplayed();
	}
	public void clickCancel() {
		cancel.click();
	}
	public void clickSave() {
		save.click();
	}
	public void clickOnClose() {
		close.click();
	}
	public void clickOnEditIcon() {
		editicon.click();
	}
	public void clickOnEditIcon(int recordsPerPage) {
		for (int i = 1; i <= recordsPerPage; i++) {
			if (i == 1) {
				WebElement rowEditIcon = driver.findElement(By.xpath("/html/body/app-root/app-program/div/mat-card/mat-card-content/p-table/div/div[1]/table/tbody/tr["+ i +"]/td[5]/div/span/button[1]/span[1]"));
				rowEditIcon.click();
				break;
			}
		}
	}
	public void clickOnDeleteIcon(int recordsPerPage) {
		for (int i = 1; i <= recordsPerPage; i++) {
			if (i == 1) {
				WebElement rowEditIcon = driver.findElement(By.xpath("/html/body/app-root/app-program/div/mat-card/mat-card-content/p-table/div/div[1]/table/tbody/tr["+ i +"]/td[5]/div/span/button[2]/span[1]"));
				rowEditIcon.click();
				break;
			}
		}
	}
	public String getProgramRequiredMsg() {
		return programRequiredMsg.getText();
	}
	
	public void validateAddProgramSuccess() throws Exception {
		try {
			WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Program Created Successfully')]")));
			Thread.sleep(1000);
			
			WebElement successSummary = driver.findElement(By.xpath("/html/body/app-root/app-program/p-toast/div/p-toastitem/div/div/div/div[1]"));
			System.out.println("successSummary:" + successSummary.getText());
			Assert.assertEquals(successSummary.getText(), "Successful");
			
			WebElement successDetail = driver.findElement(By.xpath("/html/body/app-root/app-program/p-toast/div/p-toastitem/div/div/div/div[2]"));
			System.out.println("successDetail:" + successDetail.getText());
			Assert.assertEquals(successDetail.getText(), "Program Created Successfully");
		} catch (Exception e) {
			throw new Exception("Error while waiting for the add program success notification to appear: " + e.getMessage());
		}
	}
	
	public void validateEditProgramSuccess() throws Exception {
		try {
			WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Program Updated')]")));
			Thread.sleep(1000);
			
			WebElement successSummary = driver.findElement(By.xpath("/html/body/app-root/app-program/p-toast/div/p-toastitem/div/div/div/div[1]"));
			System.out.println("successSummary:" + successSummary.getText());
			Assert.assertEquals(successSummary.getText(), "Successful");
			
			WebElement successDetail = driver.findElement(By.xpath("/html/body/app-root/app-program/p-toast/div/p-toastitem/div/div/div/div[2]"));
			System.out.println("successDetail:" + successDetail.getText());
			Assert.assertEquals(successDetail.getText(), "Program Updated");
		} catch (Exception e) {
			throw new Exception("Error while waiting for the edit program success notification to appear: " + e.getMessage());
		}
	}
	
	public void validateDeleteProgramSuccess() throws Exception {
		try {
			WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Program Deleted')]")));
			Thread.sleep(1000);
			
			WebElement successSummary = driver.findElement(By.xpath("/html/body/app-root/app-program/p-toast/div/p-toastitem/div/div/div/div[1]"));
			System.out.println("successSummary:" + successSummary.getText());
			Assert.assertEquals(successSummary.getText(), "Successful");
			
			WebElement successDetail = driver.findElement(By.xpath("/html/body/app-root/app-program/p-toast/div/p-toastitem/div/div/div/div[2]"));
			System.out.println("successDetail:" + successDetail.getText());
			Assert.assertEquals(successDetail.getText(), "Program Deleted");
		} catch (Exception e) {
			throw new Exception("Error while waiting for the delete program success notification to appear: " + e.getMessage());
		}
	}
	
	// Delete Program elements/objects
	@FindBy(xpath="/html/body/app-root/app-program/p-confirmdialog/div/div/div[1]/span")
	@CacheLookup
	WebElement confirm;
	
	@FindBy(xpath="/html/body/app-root/app-program/p-confirmdialog/div/div/div[3]/button[1]")
	@CacheLookup
	WebElement noButton;
	
	@FindBy(xpath="/html/body/app-root/app-program/p-confirmdialog/div/div/div[3]/button[1]/span[2]")
	@CacheLookup
	WebElement noButtonLabel;
	
	@FindBy(xpath="/html/body/app-root/app-program/p-confirmdialog/div/div/div[3]/button[2]")
	@CacheLookup
	WebElement yesButton;
	
	@FindBy(xpath="/html/body/app-root/app-program/p-confirmdialog/div/div/div[3]/button[2]/span[2]")
	@CacheLookup
	WebElement yesButtonLabel;
	
	@FindBy(xpath="/html/body/app-root/app-program/p-confirmdialog/div/div/div[2]/span")
	@CacheLookup
	WebElement deleteConfirmMsg;
	
	@FindBy(xpath="/html/body/app-root/app-program/p-confirmdialog/div/div/div[1]/div/button/span")
	@CacheLookup
	WebElement deleteCloseIcon;
	
	public String getConfrimText() {
		return confirm.getText();
	}
	
	public boolean isNoButtonDisplayed() {
		return noButton.isDisplayed();
	}
	
	public void clickOnNoButton() {
		noButton.click();
	}
	
	public String getNoButtonText() {
		return noButtonLabel.getText();
	}
	
	public boolean isYesButtonDisplayed() {
		return yesButton.isDisplayed();
	}
	
	public void clickOnYesButton() {
		yesButton.click();
	}
	
	public String getYesButtonText() {
		return yesButtonLabel.getText();
	}
	
	public String getConfrimMsgText() {
		return deleteConfirmMsg.getText();
	}
	
	public boolean isDeleteCloseIconDisplayed() {
		return deleteCloseIcon.isDisplayed();
	}
	
	public void clickOnDeleteCloseIcon() {
		deleteCloseIcon.click();
	}
	// End of Delete Program elements/objects
}
	
