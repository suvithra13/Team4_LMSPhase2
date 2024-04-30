Feature: Delete Program
Background: Admin successfully logged in to LMS Portal 
Given admin enter the LMS site
 When Admin enters Username "<username>" and Password "<password>" and logged in
 And Admin is on dashboard page after Login
 Then Admin clicks "Program" on the navigation bar
 
 @ValidateDeleteFeature
 Scenario: Validate Delete Feature
 Given Admin is on Manage Program Page 
 When Admin clicks <Delete> button on the data table for any row
 Then Admin should see a alert open with heading "Confirm" along with  <YES> and <NO> button for deletion
 
 @ValidatedetailsforConfirmDeletionform
 Scenario: Validate details for Confirm Deletion form
 Given Admin is on Manage Program Page 
 When Admin clicks <Delete> button on the data table for any row
 Then Admin should see a message "Are you sure you want to delete <Program name>?"
 
 @ClickYesondeletionwindow
 Scenario: Click Yes on deletion window
 Given Admin is on Confirm Deletion alert
 When Admin clicks <YES> button on the alert
 Then Admin gets a message "Successful Program Deleted" alert and able to see that program deleted in the data table
 
 @ClickNoOnDeletionWindow
 Scenario: Click No on deletion window
 Given Admin is on Confirm Deletion alert
 When Admin clicks <NO> button on the alert
 Then Admin can see the deletion alert disappears without deleting
 
 @Cancel/Close
 Scenario: Validate Cancel/Close(X) icon on Confirm Deletion alert
 Given Admin is on Confirm Deletion alert
 When Admin clicks Close(X) Icon on Deletion alert
 Then Admin can see the deletion alert disappears without any changes
 
 
