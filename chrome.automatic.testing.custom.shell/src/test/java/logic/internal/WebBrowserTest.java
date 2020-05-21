package logic.internal;
import logic.internal.controller.CustomWebServiceTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import javax.jws.WebService;

class WebBrowserTest {

    void testControllerManually(CustomWebServiceTest controller)
    {
        controller.getTitle();

        controller.runURL("https://www.ukr.net/");
        controller.runURL("https://www.foxtrot.com.ua/uk");
        controller.runURL("https://moodle.fi.npu.edu.ua/");

        controller.closeHandle(); // close the handle
    }

    @Test
    void main() throws InterruptedException {
        CustomWebServiceTest mainController = new CustomWebServiceTest();
        mainController.Initialize("http://automationpractice.com/", "My Store", true, true);

        WebElement loginElement =  mainController.getWebDriverElementByClass("header_user_info");
        loginElement.click(); // Sign In button click.

        if (mainController.getTitle().contentEquals("Login - My Store"))
            System.out.println("Checking title... passed!");
        else
            System.out.println("Checking title... failed! [ " + mainController.getTitle() + " ]");

        Assertions.assertEquals("Login - My Store", mainController.getTitle());

        Thread.sleep(1000);

        WebElement emailElement =  mainController.getWebDriverElement("email");
        WebElement passwdElement = mainController.getWebDriverElement("passwd");

        emailElement.sendKeys ("bodia.semesko28@gmail.com");
        passwdElement.sendKeys("11111");

        WebElement submitElement = mainController.getWebDriverElement("SubmitLogin");
        submitElement.click(); // Log In

        Thread.sleep(1500);

       // loginElement =  mainController.getWebDriverElementByText("An email address required.");

       // if (loginElement.isDisplayed())
            //System.out.println("Checking text... failed! Email Address Required!");

        //Thread.sleep(1500);

        //loginElement =  mainController.getWebDriverElementByText("Password is required.");

        //if (loginElement.isDisplayed())
           // System.out.println("Checking text... failed! Password is required. ");


        //Assertions.assertEquals("My account - My Store", mainController.getTitle());
        // loginElement =  mainController.getWebDriverElementByClass("account");

        // Assertions.assertEquals("Bohdan Semesko", loginElement.getText());

        //WebElement tShirts =  mainController.getWebDriverElementByClass("sfHoverForce"); //
        //tShirts.click(); // Sign In button click.

        mainController.runURL("http://automationpractice.com/index.php?id_category=5&controller=category");
        mainController.runURL("http://automationpractice.com/index.php?id_product=1&controller=product");

        WebElement cartButton = mainController.getWebDriverElement("Submit");
        cartButton.click();

        Thread.sleep(1500);

        mainController.runURL("http://automationpractice.com/index.php?controller=order");

        WebElement inputSelector = mainController.getWebDriverElement("quantity_1_1_0_324261");
        inputSelector.click();
        inputSelector.sendKeys("2");

        Thread.sleep(1500);

        WebElement totalCostAmount = mainController.getWebDriverElementById("total_price");
        System.out.println(totalCostAmount.getText());

        if (totalCostAmount.getText().contentEquals("$35.02"))
            System.out.println("Testing math... passed!");

        Assertions.assertEquals("$35.02", totalCostAmount.getText());

        mainController.closeHandle(); // close the handle if isManualTesting enabled
    }
}