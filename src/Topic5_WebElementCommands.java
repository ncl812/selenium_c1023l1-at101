    import org.openqa.jetty.html.Select;
    import org.openqa.selenium.By;
    import org.openqa.selenium.WebDriver;
    import org.openqa.selenium.WebElement;
    import org.openqa.selenium.chrome.ChromeDriver;
    import org.testng.Assert;
    import org.testng.annotations.AfterClass;
    import org.testng.annotations.BeforeClass;
    import org.testng.annotations.Test;
    import java.util.List;
    import java.util.concurrent.TimeUnit;

    public class Topic5_WebElementCommands {
        WebDriver driver;

        @BeforeClass
        public void beforeClass() {
            System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            driver.manage().window().maximize();
            driver.get("https://demoqa.com/automation-practice-form");
        }

        @Test
        public void TC01_CheckEnvironment() {
            String getTitle = driver.getTitle();
            Assert.assertEquals("DEMOQA", getTitle);
        }


        @Test
        public void TC02_dropDownList() {
            driver.get("https://demo.guru99.com/insurance/v1/register.php");
            String slbTitle = "//select[@id='user_title']";
            String optTitle = "//select[@id='user_title'//option[text()='%s']";
            String slbTilteOptions = "//select[@id='user_title']//option";

            org.openqa.selenium.support.ui.Select title = new Select(driver.findElement(By.xpath(slbTitle)));
            title.selectByValue("Duches");
            title.selectByIndex(13);
            System.out.print("Step 1 - Select value captain");
            title.selectByVisibleText("Captain");
            System.out.print("Step 2 - Verify Captain is selected");
            WebElement eOptionTitle = driver.findElement(By.xpath(String.format(optTitle, "Captain")));
            String expResult = eOptionTitle.getAttribute("selected");
            System.out.print(expResult);

            List<WebElement> numberOption = driver.findElements(By.xpath(slbTilteOptions));
            int number = numberOption.size();
            System.out.print(number);
        }

        @Test
        public void TC04_Iframe() {
            String iframe = "//iframe[@id='frame1']";
            String h1 = "//h1[@id='sampleHeading']";
            WebElement frame = driver.findElement(By.xpath(iframe));
            driver.switchTo().frame(frame);
            WebElement txtH1 = driver.findElement(By.xpath(h1));
            System.out.print(txtH1.getText());
        }

        @AfterClass
        public void afterClass() {
            driver.quit();
        }
    }



