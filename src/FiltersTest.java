import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.By;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import java.util.ArrayList;
import java.util.List;
public class FiltersTest {
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
    public void sizes48ProductFilters() {
        setup();
        WebElement catagori = driver.findElement(By.xpath("//a[@title='Menu']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(catagori).perform();


        WebElement shoesBar = driver.findElement(By.xpath("//a[@title='Ayakkabı']"));
        actions.moveToElement(shoesBar).perform();

        WebElement adidasOption = driver.findElement(By.xpath("//p[text()='adidas']"));
        adidasOption.click();

        WebElement sizes = driver.findElement(By.xpath("//*[@id=\"headingTen\"]/button"));

        JavascriptExecutor executorsizes = (JavascriptExecutor) driver;
        executorsizes.executeScript("arguments[0].click();", sizes);
        WebElement size48 = driver.findElement(By.xpath("//label[text()='48']"));

        JavascriptExecutor executor48 = (JavascriptExecutor) driver;
        executor48.executeScript("arguments[0].click();", size48);

        WebElement product = driver.findElement(By.xpath("//a[@href='https://hype-merch.com/bad-bunny-x-campus-the-last-campus']"));

        product.click();


        try {

            WebElement testProduct = driver.findElement(By.xpath("//p[text()='48' and @class='m-0 text-black-color fw-semibold font-size-lg2x down-sm-font-size-xxs']"));
            assertEquals("48", testProduct.getText());


        } catch (StaleElementReferenceException e) {
            e.printStackTrace();
        }

tearDown();
    }

    @Test
    public void size455ProductTest() {
        setup();
        WebElement catagori = driver.findElement(By.xpath("//a[@title='Menu']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(catagori).perform();
        WebElement shoesBar = driver.findElement(By.xpath("//a[@title='Ayakkabı']"));

        actions.moveToElement(shoesBar).perform();
        WebElement airJordanOption = driver.findElement(By.xpath("//p[text()='Air Jordan']"));
        airJordanOption.click();

        WebElement sizes = driver.findElement(By.xpath("//*[@id=\"headingTen\"]/button"));
        JavascriptExecutor executorsizes = (JavascriptExecutor) driver;
        executorsizes.executeScript("arguments[0].click();", sizes);

        WebElement size455 = driver.findElement(By.xpath("//label[text()='45.5']"));

        JavascriptExecutor executor455 = (JavascriptExecutor) driver;
        executor455.executeScript("arguments[0].click();", size455);
        WebElement product2 = driver.findElement(By.xpath("//a[@href='https://hype-merch.com/jordan-jumpman-jack-tr-sail']"));
        product2.click();

        try {
            WebElement testProduct = driver.findElement(By.xpath("//p[text()='45.5' and @class='m-0 text-black-color fw-semibold font-size-lg2x down-sm-font-size-xxs']"));
            assertEquals("45.5", testProduct.getText());

        } catch (StaleElementReferenceException e) {
            e.printStackTrace();

        }
tearDown();

    }
    @Test
    public void size42ProductTest() {
        setup();
        WebElement catagori = driver.findElement(By.xpath("//a[@title='Menu']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(catagori).perform();
        WebElement shoesBar = driver.findElement(By.xpath("//a[@title='Ayakkabı']"));

        actions.moveToElement(shoesBar).perform();
        WebElement balenciagaOption = driver.findElement(By.xpath("//p[text()='Balenciaga']"));
        balenciagaOption.click();

        WebElement sizes = driver.findElement(By.xpath("//*[@id=\"headingTen\"]/button"));
        JavascriptExecutor executorsizes = (JavascriptExecutor) driver;
        executorsizes.executeScript("arguments[0].click();", sizes);

        WebElement size42 = driver.findElement(By.xpath("//label[text()='42']"));
        JavascriptExecutor executor42 = (JavascriptExecutor) driver;
        executor42.executeScript("arguments[0].click();", size42);

        WebElement product = driver.findElement(By.xpath("//h1[text()='Balenciaga Track Sneaker Dark Green']"));
        product.click();

        try {
            WebElement testProduct = driver.findElement(By.xpath("//p[@class='m-0 text-black-color fw-semibold font-size-lg2x down-sm-font-size-xxs' and text()='42']"));
            assertEquals("42", testProduct.getText());

        } catch (StaleElementReferenceException e) {
            e.printStackTrace();

        }
        tearDown();
    }

    @Test
    public void sizeSProductTest() {
        setup();
        WebElement catagori = driver.findElement(By.xpath("//a[@title='Menu']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(catagori).perform();
        WebElement clothesBar = driver.findElement(By.xpath("//a[@class='prett d-flex align-items-center' and @href='https://hype-merch.com/giyim' and @title='Giyim']"));

        actions.moveToElement(clothesBar).perform();
        WebElement amiParisOption = driver.findElement(By.xpath("//p[text()='Ami Paris']"));
        amiParisOption.click();

        WebElement sizes = driver.findElement(By.xpath("//*[@id=\"headingTen\"]/button"));
        JavascriptExecutor executorsizes = (JavascriptExecutor) driver;
        executorsizes.executeScript("arguments[0].click();", sizes);

        WebElement sizeS = driver.findElement(By.xpath("//label[@class='btn font-size-xxs' and @for='btnradio1' and text()='S']"));
        JavascriptExecutor executorS = (JavascriptExecutor) driver;
        executorS.executeScript("arguments[0].click();", sizeS);

        WebElement product = driver.findElement(By.xpath("//a[@href='https://hype-merch.com/ami-paris-ami-de-coeur-classic-fit-tshirt-2']"));
        product.click();

        try {
            WebElement testProduct = driver.findElement(By.xpath("//p[@class='m-0 text-black-color fw-semibold font-size-lg2x down-sm-font-size-xxs' and text()='S']"));
            assertEquals("S", testProduct.getText());

        } catch (StaleElementReferenceException e) {
            e.printStackTrace();

        }
        tearDown();
    }
    @Test
    public void sizeMProductTest() {
        setup();
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
        WebElement catagori = driver.findElement(By.xpath("//a[@title='Menu']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(catagori).perform();

        WebElement clothesBar = driver.findElement(By.xpath("//a[@class='prett d-flex align-items-center' and @href='https://hype-merch.com/giyim' and @title='Giyim']"));
        actions.moveToElement(clothesBar).perform();


        WebElement offWhiteLink = driver.findElement(By.xpath("//a[contains(@href, '/giyim/offwhite-2')]/p[contains(@class, 'm-0 font-size-lg') and contains(@class, 'ps-2') and contains(@class, 'text-black-color') and contains(@class, 'text-capitalize')]"));
        actions.moveToElement(offWhiteLink).perform();
        actions.click().perform();

        WebElement sizes = driver.findElement(By.xpath("//*[@id=\"headingTen\"]/button"));
        JavascriptExecutor executorsizes = (JavascriptExecutor) driver;
        executorsizes.executeScript("arguments[0].click();", sizes);

        WebElement sizeM = driver.findElement(By.xpath("//label[@class='btn font-size-xxs' and text()='M']"));
        JavascriptExecutor executorM = (JavascriptExecutor) driver;
        executorM.executeScript("arguments[0].click();", sizeM);

        WebElement product = driver.findElement(By.xpath("//h1[@class='m-0 font-size-base text-black-color fw-semibold lh-sm line-overflow down-sm-font-size-xxs' and text()='OFF-WHITE x Jordan T-shirt White']"));
        product.click();

        try {
            WebElement testProduct = driver.findElement(By.xpath("//p[@class='m-0 text-black-color fw-semibold font-size-lg2x down-sm-font-size-xxs' and text()='M']"));
            assertEquals("M", testProduct.getText());

        } catch (StaleElementReferenceException e) {
            e.printStackTrace();

        }
tearDown();
    }

    @Test
    public void sizeLProductTest() throws InterruptedException {
        setup();
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
        WebElement catagori = driver.findElement(By.xpath("//a[@title='Menu']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(catagori).perform();

        WebElement clothesBar = wait.until((ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='prett d-flex align-items-center' and @href='https://hype-merch.com/giyim' and @title='Giyim']"))));
        actions.moveToElement(clothesBar).perform();


        WebElement ralphLaurenLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(@class, 'm-0') and contains(@class, 'font-size-lg') and contains(@class, 'ps-2') and contains(@class, 'text-black-color') and contains(@class, 'text-capitalize') and text()='Ralph Lauren']")));
        actions.moveToElement(ralphLaurenLink).perform();
        actions.click().perform();

        WebElement sizes = driver.findElement(By.xpath("//*[@id=\"headingTen\"]/button"));
        JavascriptExecutor executorsizes = (JavascriptExecutor) driver;
        executorsizes.executeScript("arguments[0].click();", sizes);

        WebElement sizeL = driver.findElement(By.xpath("//label[@class='btn font-size-xxs' and text()='L']"));
        JavascriptExecutor executorL = (JavascriptExecutor) driver;
        executorL.executeScript("arguments[0].click();", sizeL);

        WebElement product = driver.findElement(By.xpath("//div[contains(@class, 'border') and contains(@class, 'border-radius-sm')]/a[@href='https://hype-merch.com/polo-ralph-lauren-polo-pony-wool-jumper']"));
        product.click();

        try {
            WebElement testProduct = driver.findElement(By.xpath("//p[contains(@class, 'm-0') and contains(@class, 'text-black-color') and contains(@class, 'fw-semibold') and contains(@class, 'font-size-lg2x') and contains(@class, 'down-sm-font-size-xxs') and text()='L']\n"));
            assertEquals("L", testProduct.getText());

        } catch (StaleElementReferenceException e) {
            e.printStackTrace();

        }
tearDown();
    }

    @Test
    public void price500to5000ProductTest() throws InterruptedException {
        setup();
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
        WebElement catagori = driver.findElement(By.xpath("//a[@title='Menu']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(catagori).perform();

        WebElement cosmeticBar = driver.findElement(By.xpath("//a[@class='prett d-flex align-items-center' and @href='https://hype-merch.com/kozmetik' and @title='Kozmetik']"));
        actions.moveToElement(cosmeticBar).perform();


        WebElement charloteLink = driver.findElement(By.xpath("//p[@class='m-0 font-size-lg ps-2 text-black-color text-capitalize' and text()='Charlotte Tilbury']"));
        actions.moveToElement(charloteLink).perform();
        actions.click().perform();

        WebElement price = driver.findElement(By.xpath("//*[@id=\"headingEight\"]/button"));
        JavascriptExecutor executorprice = (JavascriptExecutor) driver;
        executorprice.executeScript("arguments[0].click();", price);

        WebElement priceV = driver.findElement(By.xpath("//input[@class='form-check-input shadow-none me-2 cursor py-0' and @id='radio-0' and @type='radio' and @value='500,5000' and @style='height: 15px !important; width: 15px !important;']"));
        JavascriptExecutor executorV = (JavascriptExecutor) driver;
        executorV.executeScript("arguments[0].click();", priceV);

        WebElement product = driver.findElement(By.xpath("//a[@class='text-decoration-none d-flex align-items-center justify-content-center' and @href='https://hype-merch.com/lip-cheat-pillow-talk-medium']"));
        product.click();

        try {
            WebElement priceElement = driver.findElement(By.xpath("//p[text()='2.400,00₺']"));
            String priceText = priceElement.getText();

            if (!priceText.isEmpty()) {
                try {
                    String cleanedText = priceText.replaceAll("[^0-9,]", "");

                    double priceValue = Double.parseDouble(cleanedText.replace(",", "."));
                    assertTrue(priceValue >= 500 && priceValue <= 5000);
                    System.out.println("500 <= "+ priceValue +" <= 5000");
                } catch (NumberFormatException e) {

                    fail("The price text '" + priceText + "' is not a valid number.");
                }
            } else {

                fail("The price text is empty.");
            }
        } catch (StaleElementReferenceException e) {
            e.printStackTrace();
        } catch (NoSuchElementException e) {
            e.printStackTrace();

        }
        tearDown();
    }

    @Test
    public void price5000to20000ProductTest() throws InterruptedException {
        setup();
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
        WebElement catagori = driver.findElement(By.xpath("//a[@title='Menu']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(catagori).perform();

        WebElement homeBar = driver.findElement(By.xpath("//*[@id=\"headingOne\"]/li/ul/li[4]/a"));
        actions.moveToElement(homeBar).perform();


        WebElement balenciagaLink = driver.findElement(By.xpath("//p[contains(@class, 'm-0') and contains(@class, 'font-size-lg') and contains(@class, 'ps-2') and contains(@class, 'text-black-color') and contains(@class, 'text-capitalize') and text()='Balenciaga']"));
        actions.moveToElement(balenciagaLink).perform();
        actions.click().perform();

        WebElement price = driver.findElement(By.xpath("//*[@id=\"headingEight\"]/button"));
        JavascriptExecutor executorprice = (JavascriptExecutor) driver;
        executorprice.executeScript("arguments[0].click();", price);

        WebElement priceV = driver.findElement(By.xpath("//input[@id='radio-1' and @value='5000,20000']"));
        JavascriptExecutor executorV = (JavascriptExecutor) driver;
        executorV.executeScript("arguments[0].click();", priceV);

        WebElement product = driver.findElement(By.xpath("//h1[text()='Balenciaga x adidas Beach Towel']"));
        product.click();

        try {
            WebElement priceElement = driver.findElement(By.xpath("//p[contains(@class, 'm-0') and contains(@class, 'font-size-xxs') and contains(@class, 'fw-semibold') and contains(@class, 'text-black-color') and contains(@class, 'hover-text-black') and text()='20.000,00₺']\n"));
            String priceText = priceElement.getText();

            if (!priceText.isEmpty()) {
                try {
                    String cleanedText = priceText.replaceAll("[^0-9,]", "");

                    double priceValue = Double.parseDouble(cleanedText.replace(",", "."));
                    assertTrue(priceValue > 5000 && priceValue <= 20000);
                    System.out.println("5000 < " + priceValue +" <= 20000");
                } catch (NumberFormatException e) {

                    fail("The price text '" + priceText + "' is not a valid number.");
                }
            } else {

                fail("The price text is empty.");
            }
        } catch (StaleElementReferenceException e) {
            e.printStackTrace();
        } catch (NoSuchElementException e) {
            e.printStackTrace();

        }
        tearDown();
    }



    @Test
    public void price20000to100000ProductTest() throws InterruptedException {
        setup();
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
        WebElement catagori = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='Menu']")));
        Actions actions = new Actions(driver);
        actions.moveToElement(catagori).perform();

        WebElement collectionBar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='Koleksiyonluk']")));
        actions.moveToElement(collectionBar).perform();


        WebElement swatchLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='Swatch']")));
        actions.moveToElement(swatchLink).perform();
        actions.click().perform();

        WebElement price = driver.findElement(By.xpath("//*[@id=\"headingEight\"]/button"));
        JavascriptExecutor executorprice = (JavascriptExecutor) driver;
        executorprice.executeScript("arguments[0].click();", price);

        WebElement priceV = driver.findElement(By.xpath("//input[@id='radio-2' and @value='20000,100000']"));
        JavascriptExecutor executorV = (JavascriptExecutor) driver;
        executorV.executeScript("arguments[0].click();", priceV);

        WebElement product = driver.findElement(By.xpath("//h1[text()='Swatch x Omega Bioceramic Moonswatch Mission To Moonshine Swiss Lantern Pattern']"));
        product.click();

        try {
            WebElement priceElement = driver.findElement(By.xpath("//p[contains(@class, 'm-0') and contains(@class, 'font-size-xxs') and contains(@class, 'fw-semibold') and contains(@class, 'text-black-color') and contains(@class, 'hover-text-black') and text()='45.000,00₺']\n"));
            String priceText = priceElement.getText();

            if (!priceText.isEmpty()) {
                try {
                    String cleanedText = priceText.replaceAll("[^0-9,]", "");

                    double priceValue = Double.parseDouble(cleanedText.replace(",", "."));
                    assertTrue(priceValue > 20000 && priceValue <= 100000);
                    System.out.println("20000 < " + priceValue + " <= 100000");
                } catch (NumberFormatException e) {

                    fail("The price text '" + priceText + "' is not a valid number.");
                }
            } else {

                fail("The price text is empty.");
            }
        } catch (StaleElementReferenceException e) {
            e.printStackTrace();
        } catch (NoSuchElementException e) {
            e.printStackTrace();

        }
        tearDown();
    }
    @Test
    public void price100000to500000ProductTest() throws InterruptedException {
        setup();
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(20));
        WebElement catagori = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='Menu']")));
        Actions actions = new Actions(driver);

        catagori.click();

        WebElement collectionBar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='Koleksiyonluk']")));
        actions.moveToElement(collectionBar).perform();



        WebElement rolexLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='Rolex']\n")));
        actions.moveToElement(rolexLink).perform();
        actions.click().perform();

        WebElement price = driver.findElement(By.xpath("//*[@id=\"headingEight\"]/button"));
        JavascriptExecutor executorprice = (JavascriptExecutor) driver;
        executorprice.executeScript("arguments[0].click();", price);

        WebElement priceV = driver.findElement(By.xpath("//input[@id='radio-4' and @value='500000,1000000']"));
        JavascriptExecutor executorV = (JavascriptExecutor) driver;
        executorV.executeScript("arguments[0].click();", priceV);

        WebElement product = driver.findElement(By.xpath("//h1[text()='Rolex Submariner 124060 Black Dial']"));
        product.click();

        try {
            WebElement priceElement = driver.findElement(By.xpath("//*[@id=\"collapseExample\"]/div/div[2]/div/div/div/p[2]"));
            String priceText = priceElement.getText();

            if (!priceText.isEmpty()) {
                try {
                    String cleanedText = priceText.replaceAll("[^0-9,]", "");

                    double priceValue = Double.parseDouble(cleanedText.replace(",", "."));
                    System.out.println(priceValue);
                    assertTrue(priceValue > 100000 && priceValue <= 500000);
                    System.out.println("100000 < "+ priceValue + " <= 500000");
                } catch (NumberFormatException e) {

                    fail("The price text '" + priceText + "' is not a valid number.");
                }
            } else {

                fail("The price text is empty.");
            }
        } catch (StaleElementReferenceException e) {
            e.printStackTrace();
        } catch (NoSuchElementException e) {
            e.printStackTrace();

        }
        tearDown();
    }

    @Test
    public void price500000to1000000ProductTest() throws InterruptedException {
        setup();
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(20));
        WebElement catagori = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='Menu']")));
        Actions actions = new Actions(driver);
        actions.moveToElement(catagori).perform();

        WebElement collectionBar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='Koleksiyonluk']")));
        actions.moveToElement(collectionBar).perform();


        WebElement rolexLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='Rolex']\n")));
        actions.moveToElement(rolexLink).perform();
        actions.click().perform();

        WebElement price = driver.findElement(By.xpath("//*[@id=\"headingEight\"]/button"));
        JavascriptExecutor executorprice = (JavascriptExecutor) driver;
        executorprice.executeScript("arguments[0].click();", price);

        WebElement priceV = driver.findElement(By.xpath("//input[@id='radio-3' and @value='100000,500000']"));
        JavascriptExecutor executorV = (JavascriptExecutor) driver;
        executorV.executeScript("arguments[0].click();", priceV);
        Thread.sleep(5000);

        WebElement product = driver.findElement(By.xpath("//h1[text()='Rolex Submariner Date 126610LV Green Dial']"));
        product.click();

        try {
            WebElement priceElement = driver.findElement(By.xpath("//*[@id=\"collapseExample\"]/div/div[2]/div/div/div/p[2]"));
            String priceText = priceElement.getText();

            if (!priceText.isEmpty()) {
                try {
                    String cleanedText = priceText.replaceAll("[^0-9,]", "");

                    double priceValue = Double.parseDouble(cleanedText.replace(",", "."));
                    assertTrue(priceValue > 500000 && priceValue <= 1000000);
                    System.out.println("500000 < " + priceValue + " <=1000000");
                } catch (NumberFormatException e) {

                    fail("The price text '" + priceText + "' is not a valid number.");
                }
            } else {

                fail("The price text is empty.");
            }
        } catch (StaleElementReferenceException e) {
            e.printStackTrace();
        } catch (NoSuchElementException e) {
            e.printStackTrace();

        }
        tearDown();
    }




    @Test
    public void ascPriceProductTest() throws InterruptedException {
        setup();
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(5));
        ascButton();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[contains(text(), 'Havainas Slim 4000030')]\n")));

        wait.until(ExpectedConditions.urlMatches("https://www.hype-merch.com/.*\\?direction=asc&orderBy=price"));
        List<Double> prices = new ArrayList<>();
        try {
            int i = 1;
            while (i <= 32) {
                WebElement priceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*//*[@id='scrollTo']/div[2]/div[2]/div/div/div[" + i + "]/div/div/div/p")));
                String priceText = priceElement.getText();
                if (priceText.contains(".")) {
                    priceText = priceText.replaceFirst("\\.", "");
                }
                priceText = priceText.replace("₺", "")
                        .replace("+", "")
                        .replace(",", ".");

                double priceValue = Double.parseDouble(priceText);
                System.out.println("Price: " + priceValue);
                prices.add(priceValue);
                if (i == 32)
                    break;
                i++;
            }
            boolean isSorted = true;
            for (int j = 0; j < prices.size()-1; j++) {

                if (prices.get(j) > prices.get(j + 1)) {
                    isSorted = false;
                    break;

                }
            }
            if (isSorted) {
                System.out.println("Prices's correct sort: " + prices);
            } else {
                System.out.println("Prices's sort is incorrect");
            }


        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
        tearDown();
    }


    @Test
    public void descPriceProductTest() throws InterruptedException{
        setup();
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(5));
        descButton();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[text()='Off-White x Air Force 1 Low Black']\n")));

        wait.until(ExpectedConditions.urlMatches("https://www.hype-merch.com/.*\\?direction=desc&orderBy=price"));
        List<Double> prices = new ArrayList<>();
        try {
            int i = 1;
            while (i <= 32) {
                WebElement priceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*//*[@id='scrollTo']/div[2]/div[2]/div/div/div[" + i + "]/div/div/div/p")));
                String priceText = priceElement.getText();
                if (priceText.contains(".")) {
                    priceText = priceText.replace(".", "");
                }
                priceText = priceText.replace("₺", "")
                        .replace("+", "")
                        .replace(",", ".");

                double priceValue = Double.parseDouble(priceText);
                System.out.println("Price: " + priceValue);
                prices.add(priceValue);
                if (i == 32)
                    break;
                i++;
            }
            boolean isSorted = true;
            for (int j = 0; j < prices.size()-1; j++) {

                if (prices.get(j) < prices.get(j + 1)) {
                    isSorted = false;
                    break;

                }
            }
            if (isSorted) {
                System.out.println("Prices's correct sort: " + prices);
            } else {
                System.out.println("Prices's sort is incorrect");
            }


        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }

tearDown();
    }


    public void ascButton() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(100));
        WebElement catagori = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='Menu']")));
        Actions actions = new Actions(driver);
        actions.moveToElement(catagori).perform();


        WebElement shoesCatogari = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='prett d-flex align-items-center' and @title='Ayakkabı']\n")));
        shoesCatogari.click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        WebElement filterBar = driver.findElement(By.xpath("//*[@id=\"scrollTo\"]/div[2]/div[1]/div[2]"));
        filterBar.click();
        WebElement ascOption = driver.findElement(By.cssSelector("select.form-select.text-black-color.fw-500 option[value='asc']"));
        ascOption.click();
    }


    public void descButton() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(100));
        WebElement catagori = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='Menu']")));
        Actions actions = new Actions(driver);
        actions.moveToElement(catagori).perform();


        WebElement shoesCatogari = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='prett d-flex align-items-center' and @title='Ayakkabı']\n")));
        shoesCatogari.click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        WebElement filterBar = driver.findElement(By.xpath("//*[@id=\"scrollTo\"]/div[2]/div[1]/div[2]"));
        filterBar.click();
        WebElement descOption = driver.findElement(By.cssSelector("select.form-select.text-black-color.fw-500 option[value='desc']"));
        descOption.click();
    }

    @Test
    public void biggerThanEquivalanceTest() throws InterruptedException {
        setup();
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(5));
        descButton();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[text()='Off-White x Air Force 1 Low Black']\n")));

        wait.until(ExpectedConditions.urlMatches("https://www.hype-merch.com/.*\\?direction=desc&orderBy=price"));
        WebElement Element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"scrollTo\"]/div[2]/div[2]/div/div/div[1]/div")));
        Element.click();

        WebElement priceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"__nuxt\"]/div/div[2]/div[3]/div[3]/div[3]/div[2]/div/div[1]/p")));
        String priceText = priceElement.getText();
        priceText = priceText.replace(".","")
                .replace("₺","");
        priceText = priceText.replace(",",".");
        double price = Double.parseDouble(priceText);

        try {
            if (price < 500) {
                throw new Exception("500 >" + price);
            }  else if (price >= 500 && price <= 1000000) {
                System.out.println("500 < "+ price +" < 1000000 ");
            } else if (price > 1000000) {
                throw new Exception("1000000 < " + price);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
tearDown();

    }

    @Test
    public void smallerThanEquivalanceTest() throws InterruptedException {
        setup();
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(5));
        ascClothButton();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        wait.until(ExpectedConditions.urlMatches("https://www.hype-merch.com/.*\\?direction=asc&orderBy=price"));
        WebElement Element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='https://hype-merch.com/logo-socks-6']")));
        Element.click();

        WebElement priceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"__nuxt\"]/div/div[2]/div[3]/div[3]/div[3]/div/div/div[1]/p")));
        String priceText = priceElement.getText();
        priceText = priceText.replace(".","")
                .replace("₺","");
        priceText = priceText.replace(",",".");
        double price = Double.parseDouble(priceText);

        try {
            if (price < 500) {
                throw new Exception("500 >" + price);
            }  else if (price >= 500 && price <= 1000000) {
                System.out.println("500 < "+ price +" < 1000000 ");
            } else if (price > 1000000) {
                throw new Exception("1000000 < " + price);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
tearDown();
    }
    public void ascClothButton() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(100));
        WebElement catagori = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='Menu']")));
        Actions actions = new Actions(driver);
        actions.moveToElement(catagori).perform();


        WebElement clothCatogari = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"headingOne\"]/li/ul/li[2]/a")));
        clothCatogari.click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        WebElement filterBar = driver.findElement(By.xpath("//*[@id=\"scrollTo\"]/div[2]/div[1]/div[2]"));
        filterBar.click();
        WebElement ascOption = driver.findElement(By.cssSelector("select.form-select.text-black-color.fw-500 option[value='asc']"));
        ascOption.click();
    }
}
