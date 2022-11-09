package steps;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class AddContactSteps {

    WebDriver wd;

    @Before
    public void navigateToLoginPage() {
        wd = new ChromeDriver();
        wd.manage().window().maximize();
        wd.get("https://contacts-app-tobbymarshall815.vercel.app");
        wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @Given("Navigate to User Account")
    public void navigateToUserAccount() {
        click(By.xpath("//*[.='LOGIN']"));
        type(By.cssSelector("[placeholder = 'Email']"), "sascha@gmail.com");
        type(By.cssSelector("[placeholder = 'Password']"), "K7100596c_");
        click(By.xpath("//*[.='Login']"));
    }

    @When("Click on Add tab")
    public void clickOnAddTab() {
        click(By.xpath("//a[.='ADD']"));
    }

    @And("Enter valid contact data")
    public void enterValidContactData() {
        type(By.cssSelector("[placeholder = 'Name']"), "Black");
        type(By.cssSelector("[placeholder = 'Last Name']"), "Sascha");
        type(By.cssSelector("[placeholder = 'Phone']"), "12354689");
        type(By.cssSelector("[placeholder = 'email']"), "sascha@gmail.com");
        type(By.cssSelector("[placeholder = 'Address']"), "Jena");
        type(By.cssSelector("[placeholder = 'description']"), "School boy");
    }

    @And("Click on Save Button")
    public void clickOnSaveButton() {
        click(By.xpath("//button[.='Save']"));
    }

    @Then("Contact is displayed")
    public void isContactPresent() {
        click(By.cssSelector(".contact-item_card__2SOIM"));
        Assert.assertTrue(isElementPresent(By.xpath("//button[contains(., 'Remove')]")));
    }

    public void type(By locator, String text) {
        if (text != null) {
            click(locator);
            wd.findElement(locator).clear();
            wd.findElement(locator).sendKeys(text);
        }
    }

    public boolean isElementPresent(By locator) {
        return wd.findElements(locator).size() > 0;
    }

    public void click(By locator) {
        wd.findElement(locator).click();
    }

    public void pause(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

