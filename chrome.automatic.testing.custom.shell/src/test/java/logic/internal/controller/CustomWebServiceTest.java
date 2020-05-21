package logic.internal.controller;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;

// It's a try to write own Custom WebService :D
public class CustomWebServiceTest {

    private WebDriver mainDriver;

    private String baseUrl;
    private String expectedTitle;
    private String actualTitle;

    private boolean isAutoClosing = true;
    private boolean isManualTestingRunned = false;

    public WebElement getWebDriverElement(String elementName)
    {
        WebElement element = mainDriver.findElement(By.name(elementName));
        try{
            if(element.isEnabled()){
                return element;
            }
        }

        catch(NoSuchElementException elementException){
            System.out.println(elementException.toString());
        }

        return null;
    }

    public WebElement getWebDriverElementByText(String Text)
    {
        WebElement element = mainDriver.findElement(By.xpath(".//*[text()='" + Text + "']"));
        try{
            if(element.isEnabled()){
                return element;
            }
        }

        catch(NoSuchElementException elementException){
            System.out.println(elementException.toString());
        }

        return null;
    }

    public WebElement getWebDriverElementByClass(String elementClassName)
    {
        WebElement element = mainDriver.findElement(By.className(elementClassName));
        try{
            if(element.isEnabled()){
                return element;
            }
        }

        catch(NoSuchElementException elementException){
            System.out.println(elementException.toString());
        }

        return null;
    }

    public WebElement getWebDriverElementById(String elementClassName)
    {
        WebElement element = mainDriver.findElement(By.id(elementClassName));
        try{
            if(element.isEnabled()){
                return element;
            }
        }

        catch(NoSuchElementException elementException){
            System.out.println(elementException.toString());
        }

        return null;
    }

    public WebDriver getWebDriver()
    {
        return mainDriver;
    }

    public void runManualTesting()
    {
        if (!isManualTestingRunned)
            isManualTestingRunned = true;

        isAutoClosing = false;
    }

    public void switchManualTesting()
    {
        isManualTestingRunned = !isManualTestingRunned;
    }

    public void runURL(String URL)
    {
        if (isManualTestingRunned)
             mainDriver.get(URL);
    }

    public String getCurrentUrl()
    {
        if (isManualTestingRunned)
            return mainDriver.getCurrentUrl();

        return "";
    }

    public String getTitle()
    {
        if (isManualTestingRunned)
            return mainDriver.getTitle();

        return "";
    }

    public void closeHandle()
    {
        mainDriver.close();
    }

     /**
     *
     */
    private WebDriver createNewWebDriver(boolean isChromeDriver, String drive)
    {
        if (drive.length() < 1)
        {
            drive = "C";
            System.out.println("Forcing the C:\\ path");
        }

        if (isChromeDriver) {
            System.setProperty("webdriver.chrome.driver", drive + ":\\chromedriver.exe");
            return new ChromeDriver();
        }
        else {
            System.setProperty("webdriver.gecko.driver",  drive + ":\\geckodriver.exe");
            return new FirefoxDriver();
        }
    }

     /**
      * @URL String value of the domain you wish to check
      * @ExpectedTitle String value of the Title you wish to compare
      * @isChromeBrowser Boolean value of the Browser
      * @isManualTestingRunned Boolean value which tell's if testing drive handle would be auto closed.
     */
    public void Initialize(String URL, String ExpectedTitle, boolean isChromeBrowser, boolean isManualTestingRunned) {
        // declaration and instantiation of objects/variables

        WebDriver driver = createNewWebDriver(isChromeBrowser, "D"); // to do... detection current user browser and dir
        mainDriver = driver;

        baseUrl = URL;
        expectedTitle = ExpectedTitle;

        // launch and direct it to the Base URL
        driver.get(baseUrl);
        actualTitle = driver.getTitle();

        if (actualTitle.contentEquals(expectedTitle)){
            System.out.println("Title equality Test [[Passed]]! Output: Actual[ " + actualTitle + " ], Expected[ " + expectedTitle + " ]");
        } else {
            System.out.println("Title equality Test [[Failed]]! Output: Actual[ " + actualTitle + " ], Expected[ " + expectedTitle + " ]");
        }

        if (isManualTestingRunned)
            runManualTesting();

        System.out.println("DONE!");

        // close

        if (isAutoClosing && !isManualTestingRunned)
             driver.close();
    }
}