package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class LoginSteps {

    WebDriver wd;

    @Given("Navigate to Home Page")
    public void navigateToLoginPage() {
        wd = new ChromeDriver();
        wd.manage().window().maximize();
        wd.get("https://contacts-app-tobbymarshall815.vercel.app");
        wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @When("Click on Login tab")
    public void clickOnLoginTab() {
        click(By.xpath("//*[.='LOGIN']"));
    }

    @Then("Appear LoginRegistration form")
    public void isLoginRegistrationFormPresent() {
        Assert.assertTrue(isElementPresent(By.cssSelector(".login_login__3EHKB")));
    }

    @And("Enter valid data")
    public void enterValidData() {
        type(By.cssSelector("[placeholder = 'Email']"), "sascha@gmail.com");
        type(By.cssSelector("[placeholder = 'Password']"), "K7100596c_");
    }

    @And("Click on Login Button")
    public void ClickOnLoginButton() {
        click(By.xpath("//*[.='Login']"));
    }

    @Then("SignOut button displayed")
    public void isSignOutButtonPresent() {
        Assert.assertTrue(isElementPresent(By.xpath("//*[.='Sign Out']")));
    }

    @And("Enter valid email and an invalid password")
    public void enterInvalidPassword(DataTable table) {

        List<Map<String, String>> dataTable = table.asMaps();
        String email = dataTable.get(0).get("email");
        String password = dataTable.get(0).get("password");

        type(By.cssSelector("[placeholder = 'Email']"), email);
        type(By.cssSelector("[placeholder = 'Password']"), password);
    }

    @Then("Alert appeared")
    public void isAlertAppeared() {
        Assert.assertTrue(isAlertPresent());
    }

    private boolean isAlertPresent() {
        Alert alert = new WebDriverWait(wd, 10)
                .until(ExpectedConditions.alertIsPresent());
        if (alert == null) {
            return false;
        } else {
            wd.switchTo().alert();
            alert.accept();
            return true;
        }
    }

    private void type(By locator, String text) {
        if (text != null) {
            click(locator);
            wd.findElement(locator).clear();
            wd.findElement(locator).sendKeys(text);
        }
    }

    private boolean isElementPresent(By locator) {
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
