import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class thuchanh02 {

    public static void main(String[] args) {
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

    private static void login(WebDriver driver, String email, String password) {
        WebElement emailInput = driver.findElement(By.id("email"));
        emailInput.sendKeys(email);
        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys(password);

        WebElement loginButton = driver.findElement(By.id("loginButton"));
        loginButton.click();
    }

    private static void clickTaskMenu(WebDriver driver) {
        WebElement taskMenu = driver.findElement(By.xpath("//a[contains(text(),'Task')]"));
        taskMenu.click();
    }

    private static void addTask(WebDriver driver, String title, String description) {
        WebElement addTaskButton = driver.findElement(By.xpath("//button[contains(text(),'Add a task')]"));
        addTaskButton.click();
        WebElement titleInput = driver.findElement(By.id("taskTitle"));
        titleInput.sendKeys(title);

        WebElement descriptionInput = driver.findElement(By.id("taskDescription"));
        descriptionInput.sendKeys(description);

        WebElement saveButton = driver.findElement(By.xpath("//button[contains(text(),'Save')]"));
        saveButton.click();
    }

    private static void searchTask(WebDriver driver, String taskTitle) {
        WebElement searchInput = driver.findElement(By.id("searchTaskInput"));
        searchInput.sendKeys(taskTitle);

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='task-result']")));

        WebElement firstResult = driver.findElement(By.xpath("//div[@class='task-result'][1]"));
        firstResult.click();
    }

    private static void addCommentsAndChangeStatus(WebDriver driver, String comment1, String comment2, String newStatus) {
        WebElement commentInput = driver.findElement(By.id("commentInput"));
        commentInput.sendKeys(comment1);
        WebElement addCommentButton = driver.findElement(By.id("addCommentButton"));
        addCommentButton.click();

        commentInput.sendKeys(comment2);
        addCommentButton.click();

        WebElement statusDropdown = driver.findElement(By.id("statusDropdown"));
        statusDropdown.click();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        List<WebElement> statusOptions = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@class='status-option']")));

        for (WebElement option : statusOptions) {
            if (option.getText().equalsIgnoreCase(newStatus)) {
                option.click();
                break;
            }
        }
    }
}
