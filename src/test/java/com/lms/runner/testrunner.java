package com.lms.runner;



import io.cucumber.testng.CucumberOptions;
	
//@RunWith(Cucumber.class) //Junit execution

	@CucumberOptions(
			plugin = {"pretty", "html:target/suvi.html"}, //reporting purpose
			monochrome=false,  //console output color
			tags = "@tag1", //tags from feature file
			features = {"src/test/java/com.lms.features"}, //location of feature files
			glue= "com.lms.steps") //location of step definition files


	public class testrunner {
		
		
	    }
	