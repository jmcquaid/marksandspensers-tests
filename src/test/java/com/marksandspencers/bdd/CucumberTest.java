package com.metapack.dmlabelregression.bdd;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(format = {"pretty", "html:target/api/output"}, features = ".", tags = {"@james"})
public class CucumberTest extends AbstractTestNGCucumberTests {
}

