package org.xpdojo.bank;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/behaviour/resources/features",
    glue = "org.xpdojo.bank.stepdefintions",
    plugin = {"pretty", "html:target/cucumber-reports.html"}
)
public class BankAccountOperationsTest {
}
