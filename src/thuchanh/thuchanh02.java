package thuchanh;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.List;


public class thuchanh02 {
    public thuchanh02() {
    }

    @Test
    public void Test() {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("https://rise.fairsketch.com");
        login(driver, "admin@demo.com", "riseDemo");
        clickTaskMenu(driver);
        addTask(driver, "Task Title", "Task Description");
        String taskTitle = "Task Title";
        searchTask(driver, taskTitle);
        addCommentsAndChangeStatus(driver, "Comment 1", "Comment 2", "In Progress");
        driver.quit();
    }

    private void login(WebDriver driver, String email, String password) {
        WebElement emailInput = driver.findElement(By.id("email"));
        emailInput.sendKeys(new CharSequence[]{email});
        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys(new CharSequence[]{password});
        WebElement loginButton = driver.findElement(By.id("loginButton"));
        loginButton.click();
    }

    private void clickTaskMenu(WebDriver driver) {
        WebElement taskMenu = driver.findElement(By.xpath("//a[contains(text(),'Task')]"));
        taskMenu.click();
    }

    private void addTask(WebDriver driver, String title, String description) {
        WebElement addTaskButton = driver.findElement(By.xpath("//button[contains(text(),'Add a task')]"));
        addTaskButton.click();
        WebElement titleInput = driver.findElement(By.id("taskTitle"));
        titleInput.sendKeys(new CharSequence[]{title});
        WebElement descriptionInput = driver.findElement(By.id("taskDescription"));
        descriptionInput.sendKeys(new CharSequence[]{description});
        WebElement saveButton = driver.findElement(By.xpath("//button[contains(text(),'Save')]"));
        saveButton.click();
    }

    private void searchTask(WebDriver driver, String taskTitle) {
        WebElement searchInput = driver.findElement(By.id("searchTaskInput"));
        searchInput.sendKeys(new CharSequence[]{taskTitle});
        WebDriverWait wait = new WebDriverWait(driver, 10L);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='task-result']")));
        WebElement firstResult = driver.findElement(By.xpath("//div[@class='task-result'][1]"));
        firstResult.click();
    }

    private void addCommentsAndChangeStatus(WebDriver driver, String comment1, String comment2, String newStatus) {
        WebElement commentInput = driver.findElement(By.id("commentInput"));
        commentInput.sendKeys(new CharSequence[]{comment1});
        WebElement addCommentButton = driver.findElement(By.id("addCommentButton"));
        addCommentButton.click();
        commentInput.sendKeys(new CharSequence[]{comment2});
        addCommentButton.click();
        WebElement statusDropdown = driver.findElement(By.id("statusDropdown"));
        statusDropdown.click();
        WebDriverWait wait = new WebDriverWait(driver, 10L);
        List<WebElement> statusOptions = (List)wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@class='status-option']")));
        Iterator var9 = statusOptions.iterator();

        while(var9.hasNext()) {
            WebElement option = (WebElement)var9.next();
            if (option.getText().equalsIgnoreCase(newStatus)) {
                option.click();
                break;
            }
        }

    }
}
