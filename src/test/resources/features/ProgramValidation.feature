Feature: Program Validation
Background: Admin successfully logged in to LMS Portal 
Given  admin enter the LMS site
 When   Admin enters Username "<username>" and Password "<password>" and logged in
 And Admin is on dashboard page after Login
 Then Admin clicks "Program" on the navigation bar

 #@LandingPage
 #Scenario: Validate landing in Program page
 #Then Admin should see URL with "Manage Program"
 #
 #@Heading
 #Scenario: Validate the heading
 #Then Admin should see a heading with text "Manage Program" on the page
 #
 #@Pagination
 #Scenario: Validate the text and pagination icon below the data table
 #Then Admin should see the text as Showing x to y of z entries along with Pagination icon below the table
 #
 #bug Footer Message ------>In total there are 0 programs.
 #@footer
 #Scenario: Validate the footer
 #Then Admin should see the footer as In total there are z programs
 #
 #@DeleteButton
 #Scenario: Validating the default state of Delete button
 #Then Admin should see a Delete button on the top left hand side as Disabled
 #
 #@SearchBar
 #Scenario: Verify Search bar on the Program page
 #Then Admin should see Search bar with text as "Search..."
 #
 #@AddNewProgram
 #Scenario: Validate Add New Program 
 #Then Admin should see a "+A New Program" button on the program page above the data table
 
 #@Verifydatatable 
 #Scenario: Verify data table on the Program page
 #Then Admin should see data table on the Manage Program Page with column headers
 #
 #@SortArrow
 #Scenario: Verify Sort arrow icon on the data table
 #Then Admin should see the sort arrow icon beside to each column header except Edit and Delete 
 #
 @CheckBox
 Scenario: Verify Check box on the data table
 When Admin should see the text as Showing x to y of z entries along with Pagination icon below the table
 Then Admin should see check box on the left side in all rows of the data table 
 
 @EditDeleteButton
 Scenario: Verify Edit and Delete buttons
 When Admin should see the text as Showing x to y of z entries along with Pagination icon below the table
 Then Admin should see the Edit and Delete buttons on each row of the data table
 
 #@CheckNumberOfRecords
 #Scenario: Validate that number of records (rows of data in the table) displayed
 #When Admin should see the text as Showing x to y of z entries along with Pagination icon below the table
 #Then Admin should see the number of records displayed on the page are 5
 
 #Add A Program -->this is bug
 @ValidateProgramDetailsPopupWindow
 Scenario: Validate Program Details Popup window
 Given Admin is on Manage Program Page
 When Admin clicks <A New Program> button
 Then Admin should see a popup open for Program details with empty form along with <SAVE> and <CANCEL> button and Close(X) Icon on the top right corner of the window
 
 @ValidateInputFields
 Scenario: Validate input fields and their text boxes in Program details form 
 Given Admin is on Manage Program Page
 When Admin clicks <A New Program> button
 Then Admin should see two input fields and their respective text boxes in the program details window
 
 #@ValidateRadioButton
 #Scenario: Validate radio button for Program Status 
 #Given Admin is on Manage Program Page
 #When Admin clicks <A New Program> button
 #Then Admin should see two radio button for Program Status
 
 #need todo
 @Emptyformsubmission
 Scenario: Empty form submission 
 Given Admin is on "Program Details" Popup window
 When Admin clicks <Save>button without entering any data
 Then Admin gets a Error message alert 
 

 
@EnteronlyProgramName
Scenario Outline: Enter only Program Name
Given Admin is on "Program Details" Popup window
When Admin enter value only in ProgramName using "<SheetName>" and <rowNumber> 
And Admin clicks Save Button
Then admin gets a message alert for description

 Examples: 
      | SheetName  | rowNumber |
      | Program |   0 |


@EnteronlyProgramDescription
Scenario Outline: Enter only Program Description
Given Admin is on "Program Details" Popup window
When Admin enter value only in Program description using "<SheetName>" and <rowNumber> 
And Admin clicks Save Button
Then Admin gets a message alert 'Name is required'ProgramName

Examples: 
      | SheetName  | rowNumber |
      | Program |  1  |
      
 


	@SelectStatusonly
	Scenario: Select Status only
	Given Admin is on "Program Details" Popup window
	When Admin selects only Status 
	And Admin clicks Save Button
	Then Admin gets a message alert 'Name' and 'Description required'
	
	@ValidateInvalidValuesOntheTextcolumn
	Scenario Outline: Validate invalid values on the text column
	Given Admin is on "Program Details" Popup window
	When Admin enters only numbers or special char in name and desc column "<SheetName>" and <rowNumber> 
	And Admin clicks Save Button
	Then Admin gets a Error message alert SpecialCharecters 
	
	Examples: 
      | SheetName  | rowNumber |
      | Program |  2 |
	

	@ValidateClose(X)icononProgramDetailsform
	Scenario: Validate Close(X) icon on Program Details form
	Given Admin is on "Program Details" Popup window
	When Admin clicks Close(X) Icon on Program Details form
	Then Program Details popup window should be closed without savings

@ValidateSaveButton 
Scenario Outline: Validate Save button on Program Details form
Given Admin is on "Program Details" Popup window
When Enter all the required fields with valid values "<SheetName>" and <rowNumber>
And Admin clicks Save Button
Then Admin gets a message "Successful Program Created" alert and able to see the new program added in the data table

	Examples: 
      | SheetName  | rowNumber |
      | Program |  3 |

#@ValidateCancelbuttononProgramDetailsform
#Scenario: Validate Cancel button on Program Details form
#Given Admin is on "Program Details" Popup window
#When Admin clicks <Cancel>button 
#Then Admin can see the Program details popup disappears without creating any program

  #Edit Program
@ValidateEditFeature
Scenario: Validate Edit Feature
Given Admin is on Manage Program Page 
When Admin should see the text as Showing x to y of z entries along with Pagination icon below the table
Then Admin should see the number of records displayed on the page are 5
And Admin clicks <Edit> button on the data table for any row
Then Admin should see a popup open for Program details to edit

#@EditProgramName
#Scenario Outline: Edit Program Name
#Given Admin is on Program Details Popup window to Edit
#When Admin edits the Name column using "<SheetName>" and <rowNumber> 
#And Admin clicks Save Button
#Then Admin gets a message "Successful Program Updated" alert and able to see the updated name in the table for the particular program
#
#	Examples: 
      #| SheetName  | rowNumber |
      #| Program |  4 |
      
#@EditProgramdescription
#Scenario Outline: Edit Program description
#Given Admin is on Program Details Popup window to Edit
#When Admin edits the Description column in using "<SheetName>" and <rowNumber> 
#And Admin clicks Save Button
#Then Admin gets a message "Successful Program Updated" alert and able to see the updated description in the table for the particular program  
#
#	Examples: 
      #| SheetName  | rowNumber |
      #| Program |  5 |
      #
      #
#@ChangeProgramStatus 
#Scenario: Change Program Status
#Given Admin is on Program Details Popup window to Edit
#When Admin changes the Status 
#And Admin clicks Save Button
#Then Admin gets a message "Successful Program Updated" alert and able to see the updated status in the table for the particular program
#
#@ValidateInvalidValuesOntheTextColumn
#Scenario Outline: Validate invalid values on the text column
#Given Admin is on Program Details Popup window to Edit
#When Admin edit special char in name and desc column "<SheetName>" and <rowNumber> 
#And Admin clicks Save Button
#Then Admin gets a Error message alert Edit program
#
#Examples: 
      #| SheetName  | rowNumber |
      #| Program |  6 |
#
#
#@ValidateCancelButtonOnEditPopup
#Scenario: Validate Cancel button on Edit popup
#Given Admin is on Program Details Popup window to Edit
#When Admin clicks <Cancel>button on edit popup 
#Then Admin can see the Program details popup disappears and can see nothing changed for particular program
#
#@ValidateSavebuttononEditpopup
#Scenario: Validate Save button on Edit popup
#Given Admin is on Program Details Popup window to Edit
#When Admin clicks <Save>button on edit popup 
#










 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
  