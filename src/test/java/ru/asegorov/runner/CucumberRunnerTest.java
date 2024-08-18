package ru.asegorov.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = {"org/asegorov/framework/hooks", "org/asegorov/framework/steps"},
        features = {"src/test/resources/scenario"},
        tags = {"@firstTest"}
)
public class CucumberRunnerTest {
}
