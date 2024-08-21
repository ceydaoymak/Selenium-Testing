import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.By;
import java.util.ArrayList;
import java.util.List;
public class SlidersTest {
    static WebDriver driver;

    public static void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get("https://www.hype-merch.com");
    }
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    @Test
    public void brandSlidesTest() {
        setup();
        try {

            WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));

            WebElement slide1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='Slide 1']")));
            slide1.click();
            System.out.println("Slide 1 clicked");

            WebElement slide2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='Slide 2']")));
            slide2.click();
            System.out.println("Slide 2 clicked");

            WebElement slide3 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='Slide 3']")));
            slide3.click();
            System.out.println("Slide 3 clicked");

            WebElement slide4 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='Slide 4']")));
            slide4.click();
            System.out.println("Slide 4 clicked");

            WebElement slide5 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='Slide 5']")));
            slide5.click();
            System.out.println("Slide 5 clicked");

            WebElement slide6 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='Slide 6']")));
            slide6.click();
            System.out.println("Slide 6 clicked");

            WebElement slide7 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='Slide 7']")));
            slide7.click();
            System.out.println("Slide 7 clicked");

            WebElement slide8 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='Slide 8']")));
            slide8.click();
            System.out.println("Slide 8 clicked");

        } catch (NoSuchElementException e) {
            e.printStackTrace();
            Assert.fail("One or more slide buttons were not found on the page.");
        } catch (ElementClickInterceptedException e) {
            e.printStackTrace();
            Assert.fail("One or more slide buttons could not be clicked.");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("An unexpected error occurred.");
        }
        tearDown();
    }

    @Test
    public void brandSlideClickTest(){
        setup();
        WebDriverWait wait = new WebDriverWait(driver,java.time.Duration.ofSeconds(5));

        WebElement slide4 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='Slide 4']")));
        slide4.click();

        try {

            WebElement collactionScreen = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.vueperslide[href='https://hype-merch.com/urun-grubu/koleksiyonluk-formalar']")));
            collactionScreen.click();
            System.out.println("New screen is displayed.");
        } catch (TimeoutException e) {
            System.out.println("New screen is not displayed.");
        }
tearDown();
    }

    @Test
    public void catagorizeSlideTest(){
        setup();
        WebDriverWait wait = new WebDriverWait(driver,java.time.Duration.ofSeconds(10));
        try {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //Best Seller
            clickElement(wait, By.xpath("//*[@id=\"__nuxt\"]/div/div/div[1]/div[3]/div[1]/div/div/div[2]/div/section/button[1]"));
            clickElement(wait, By.xpath("//*[@id=\"__nuxt\"]/div/div/div[1]/div[3]/div[1]/div/div/div[2]/div/section/button[2]"));
            //Brand New
            clickElement(wait, By.xpath("//*[@id=\"__nuxt\"]/div/div/div[1]/div[3]/div[2]/div/div/div[2]/div/section/button[1]"));
            clickElement(wait, By.xpath("//*[@id=\"__nuxt\"]/div/div/div[1]/div[3]/div[2]/div/div/div[2]/div/section/button[2]"));
            //Soon To Be Annonced
            clickElement(wait, By.xpath("//*[@id=\"__nuxt\"]/div/div/div[1]/div[3]/div[3]/div/div/div[2]/div/section/button[1]"));
            clickElement(wait, By.xpath("//*[@id=\"__nuxt\"]/div/div/div[1]/div[3]/div[3]/div/div/div[2]/div/section/button[2]"));
            //Nike x Stüssy
            clickElement(wait, By.xpath("//*[@id=\"__nuxt\"]/div/div/div[1]/div[3]/div[4]/div/div/div[2]/div/section/button[1]"));
            clickElement(wait, By.xpath("//*[@id=\"__nuxt\"]/div/div/div[1]/div[3]/div[4]/div/div/div[2]/div/section/button[2]"));
            //Adidas Gazelle
            clickElement(wait, By.xpath("//*[@id=\"__nuxt\"]/div/div/div[1]/div[3]/div[5]/div/div/div[2]/div/section/button[1]"));
            clickElement(wait, By.xpath("//*[@id=\"__nuxt\"]/div/div/div[1]/div[3]/div[5]/div/div/div[2]/div/section/button[2]"));

        }catch (NoSuchElementException e){
            e.printStackTrace();
            Assert.fail("One or more slide buttons were not found on the page.");
        } catch (ElementClickInterceptedException e) {
            e.printStackTrace();
            Assert.fail("One or more slide buttons could not be clicked.");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("An unexpected error occurred.");
        }
tearDown();
    }
    @Test
    public void catogarizeLeftSliderProductTest() {
        setup();
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(5));
        try {
            Thread.sleep(3000);

            WebElement bestSellersSection = driver.findElement(By.xpath("//p[contains(text(),'En Çok Satanlar')]/ancestor::div[@class='mb-4']"));
            List<WebElement> listItems = bestSellersSection.findElements(By.cssSelector("li.carousel__slide[style*='width: 16.6667%']"));
            List<WebElement> hiddenItems = new ArrayList<>();
            for (WebElement item : listItems) {
                if ("true".equals(item.getAttribute("aria-hidden"))) {
                    hiddenItems.add(item);
                }
            }

            clickElement(wait, By.xpath("//*[@id=\"__nuxt\"]/div/div/div[1]/div[3]/div[1]/div/div/div[2]/div/section/button[1]"));
            for (WebElement item : hiddenItems) {
                if ("false".equals(item.getAttribute("aria-hidden"))) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", item);
                    break;
                }
            }

            System.out.println("Hidden items:");
            for (WebElement hiddenItem : hiddenItems) {
                System.out.println(hiddenItem.getText());
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ElementClickInterceptedException e) {
            System.err.println("Element click intercepted: " + e.getMessage());
        } catch (NoSuchElementException e) {
            System.err.println("Element not found: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
        tearDown();
    }

    @Test
    public void catogarizeRightSliderProductTest() {
        setup();
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(5));
        try {
            Thread.sleep(3000);

            WebElement bestSellersSection = driver.findElement(By.xpath("//p[contains(text(),'En Yeniler')]/ancestor::div[@class='mb-4']"));
            List<WebElement> listItems = bestSellersSection.findElements(By.cssSelector("li.carousel__slide[style*='width: 16.6667%']"));
            List<WebElement> hiddenItems = new ArrayList<>();
            for (WebElement item : listItems) {
                if ("true".equals(item.getAttribute("aria-hidden"))) {
                    hiddenItems.add(item);
                }
            }

            clickElement(wait, By.xpath("//*[@id=\"__nuxt\"]/div/div/div[1]/div[3]/div[2]/div/div/div[2]/div/section/button[2]"));
            for (WebElement item : hiddenItems) {
                if ("false".equals(item.getAttribute("aria-hidden"))) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", item);
                    break;
                }
            }

            System.out.println("Hidden items:");
            for (WebElement hiddenItem : hiddenItems) {
                System.out.println(hiddenItem.getText());
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ElementClickInterceptedException e) {
            System.err.println("Element click intercepted: " + e.getMessage());
        } catch (NoSuchElementException e) {
            System.err.println("Element not found: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
        tearDown();
    }

    @Test
    public void productSliderTest() throws InterruptedException {
        setup();
        WebElement searchBox = driver.findElement(By.xpath("//input[@class='form-control ps-2 border-color border w-100']"));
        searchBox.sendKeys("Kobe 8 Protro Venice Beach 2024");
        searchBox.sendKeys(Keys.ENTER);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        WebElement product = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"scrollTo\"]/div[2]/div[2]/div/div/div/div/div/a/div/div[1]/h1")));
        product.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"__nuxt\"]/div/div[2]/div[3]/div[1]/div/div[1]/h1")));
        Actions actions = new Actions(driver);
        WebElement s1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[name()='svg']//*[name()='path' and @d='m1 1 l7 8 -7 8']")));

        for (int i = 0; i < 5; i++) {
            actions.moveToElement(s1).click().perform();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
tearDown();
    }
    private void clickElement(WebDriverWait wait, By locator) {

        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            element.click();
        } catch (ElementClickInterceptedException e) {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

}
