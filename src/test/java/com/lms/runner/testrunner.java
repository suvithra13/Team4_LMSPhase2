package com.lms.runner;



import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class) //Junit execution

	@CucumberOptions(
			plugin = {"pretty", 
					 "html:target/LMS.html",
					"json:target/LMS.json"}, //reporting purpose
			//monochrome=false,  //console output color
			//tags = "@tag1", //tags from feature file
			features = {//"src/test/resources/features",
					//"src/test/resources/features/Programlogin.feature",
					"src/test/resources/features/ProgramValidation.feature",
					"src/test/resources/features/Delete.feature"
					},
			//location of feature files
			glue= {"com.lms.steps","com.lms.hooks" })
	


	public class testrunner {
		{
			try {
				System.out.println("Runnin testrunner...");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
