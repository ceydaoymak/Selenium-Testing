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
import static org.junit.Assert.*;
public class mainView {
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
    public void titleTest() {
       setup();

        String actualTitle = driver.getTitle();
        String expectedTitle = "Hype Merch | Orijinal ve Limitli Üretilen Sneaker Platformu";
        Assert.assertEquals(actualTitle, expectedTitle);

        tearDown();
    }


    @Test
    public void menuBar() {
        setup();
        WebElement catagoriesBar = driver.findElement(By.linkText("Kategoriler"));
        WebElement newsBar = driver.findElement(By.linkText("Haberler"));
        WebElement sellBar = driver.findElement(By.linkText("Ürün Sat"));
        WebElement cartBar = driver.findElement(By.xpath("//span[contains(text(), 'Sepetim')]"));

        try {
            assertTrue("Categories menu is not displayed", catagoriesBar.isDisplayed());
            assertTrue("News menu is not displayed", newsBar.isDisplayed());
            assertTrue("Sell Product menu is not displayed", sellBar.isDisplayed());
            assertTrue("Cart menu is not displayed", cartBar.isDisplayed());
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        tearDown();
    }

    @Test
    public void catagoriesBar() {
        setup();
        WebElement catagoriesBar = driver.findElement(By.linkText("Kategoriler"));

        Actions actions = new Actions(driver);
        actions.moveToElement(catagoriesBar).perform();

        WebElement shoesBar = driver.findElement(By.linkText("Ayakkabı"));
        WebElement clothingBar = driver.findElement(By.linkText("Giyim"));
        WebElement homeAndLifeBar = driver.findElement(By.linkText("Kozmetik"));
        WebElement collectionBar = driver.findElement(By.linkText("Ev & Yaşam"));
        WebElement bagAndWallet = driver.findElement(By.linkText("Çanta & Cüzdan"));
        try {
            assertTrue(shoesBar.isDisplayed());
            assertTrue(clothingBar.isDisplayed());
            assertTrue(homeAndLifeBar.isDisplayed());
            assertTrue(collectionBar.isDisplayed());
            assertTrue(bagAndWallet.isDisplayed());
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        tearDown();
    }

    @Test
    public void newsPage() {
        setup();
        WebElement newsBar = driver.findElement(By.linkText("Haberler"));
        newsBar.click();
        WebElement newsText = driver.findElement(By.xpath("//*[@id=\"__nuxt\"]/div/div[1]/div[5]/div[2]/a/div[1]/p"));
        assertTrue(newsText.isDisplayed());

        for (int i = 0; i < 3; i++) {
            WebElement firstItem = driver.findElement(By.xpath("(//p[@class='font-size-base text-black-color fw-bold'])[" + (i + 1) + "]"));
            firstItem.click();

            WebElement newsPageTitle = driver.findElement(By.xpath("//p[@class='font-size-base text-black-color fw-bold']")); // Bu xpath'i doğru sayfanın başlığına göre güncellemeniz gerekebilir
            assertTrue(newsPageTitle.isDisplayed());

            driver.navigate().back();
        }
        tearDown();
    }

    @Test
    public void PageSizeTest() {
        setup();
        int width = driver.manage().window().getSize().getWidth();
        int height = driver.manage().window().getSize().getHeight();
        System.out.println("Page size: " + width + "x" + height);

        Dimension newSize = new Dimension(1024, 768);
        driver.manage().window().setSize(newSize);

        Dimension mobilSize = new Dimension(360, 640);
        driver.manage().window().setSize(mobilSize);
        tearDown();
    }

    @Test
    public void PageLoadTime() {
        setup();
        try {
            long startTime = System.currentTimeMillis();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));
            long endTime = System.currentTimeMillis();
            long loadTime = endTime - startTime;
            if (loadTime > 30) {
                System.out.println("Website is too slow:" + loadTime);
            } else {
                System.out.println("Load time of page: " + loadTime + " milliseconds");
            }
        } catch (Exception e) {
            System.out.println("An error occurred while loading the page: " + e.getMessage());
        }
        tearDown();
    }


}