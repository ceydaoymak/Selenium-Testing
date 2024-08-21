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
import java.util.ArrayList;
import java.util.List;
public class FunctionsTest {
    static WebDriver driver;
    public String mail = "atakanyildiz27@gmail.com";
    public String password = "abcdefg22";


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

    void login(String username, String password) {

        setup();
        WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(), 'Giriş Yap')]"));
        loginButton.click();

        WebElement loginLink = driver.findElement(By.xpath("//a[contains(text(), 'Giriş Yap')]"));
        loginLink.click();

        WebElement email = driver.findElement(By.xpath("//*[@id=\"username\"]"));
        email.sendKeys(username);

        WebElement passwordElement = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        passwordElement.sendKeys(password);

        WebElement login = driver.findElement(By.xpath("//button[@class='btn w-100 down-sm-font-size-xxs text-black-color']"));
        login.click();

    }

    @Test
    void sellingPage() {

        login(mail, password);

        WebElement sellButton = driver.findElement(By.linkText("Ürün Sat"));
        sellButton.click();

        WebElement loggedInMessage = driver.findElement(By.xpath("//p[@class='m-0 font-size-xxl text-black-color fw-semibold']"));
        String actualMessage = loggedInMessage.getText();
        assertEquals("Sistem Nasıl Çalışır?", actualMessage);
        tearDown();
    }


        @Test
        void CheckPasswordsCompatibility() throws InterruptedException {
            setup();
            Duration timeout = Duration.ofSeconds(10);
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            login(mail, password);
            WebElement element = driver.findElement(By.xpath("//span[contains(text(), 'Profilim')]"));
            element.click();
            WebElement profileLink = driver.findElement(By.linkText("Profilim"));
            profileLink.click();
            WebElement password = driver.findElement(By.id("nav-password-tab"));
            password.click();

            WebElement currentPassword = driver.findElement(By.id("currentPassword"));
            currentPassword.sendKeys("abcdefg22");

            WebElement newPassword = driver.findElement(By.id("newPassword"));
            newPassword.sendKeys("abcdefg12");

            WebElement newPasswordAgain = driver.findElement(By.id("newPasswordRepeat"));
            newPasswordAgain.sendKeys("abcdefg123");
            WebElement submit = driver.findElement(By.xpath("//*[@id=\"nav-password\"]/form[2]/div[4]/div/button"));
            submit.click();
            Thread.sleep(3000);

            //PASSWORDS ARE DIFFERENT THAN EACH OTHER
            WebElement notSameWarning = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'Vue-Toastification__toast-body') and contains(text(), 'Yeni girmiş olduğunuz şifreler birbiriyle uyuşmuyor')]")));

            String warningText = notSameWarning.getText();
            System.out.println(warningText);

            assertEquals("Yeni girmiş olduğunuz şifreler birbiriyle uyuşmuyor", warningText);
            tearDown();
        }

        @Test
        public void changePasswordsValidTest() {

            login(mail, password);
            WebElement element = driver.findElement(By.xpath("//span[contains(text(), 'Profilim')]"));
            element.click();
            WebElement profileLink = driver.findElement(By.linkText("Profilim"));
            profileLink.click();
            WebElement password = driver.findElement(By.id("nav-password-tab"));
            password.click();

            WebElement currentPassword = driver.findElement(By.id("currentPassword"));
            currentPassword.sendKeys("abcdefg22");
            WebElement newPassword = driver.findElement(By.id("newPassword"));
            newPassword.sendKeys("abcdefg33");

            WebElement newPasswordAgain = driver.findElement(By.id("newPasswordRepeat"));
            newPasswordAgain.sendKeys("abcdefg333");
            WebElement submit = driver.findElement(By.xpath("//*[@id=\"nav-password\"]/form[2]/div[4]/div/button"));
            submit.click();

            login(mail, "abcdefg22");

            try {
                WebElement myProfile = driver.findElement(By.xpath("//span[contains(text(), 'Profilim')]"));

                assertTrue(myProfile.isDisplayed());

            } catch (NoSuchElementException e) {
                System.out.println("Login with new password is unsuccessfull.");
            }
        }

    @Test
    public void searchBoxCapacityTest() {
        setup();
        WebElement searchbox = driver.findElement(By.xpath("//input[@class='form-control ps-2 border-color border w-100']"));
        searchbox.click();

        int chunkSize = 1 * 1024 * 1024;
        String bigString = new String(new char[chunkSize]).replace('\0', 'a');

        int maxCapacity = 5 * chunkSize;

        try {
            int i;
            for (i = 0; i < maxCapacity; i += chunkSize) {
                searchbox.sendKeys(bigString);
            }

            String inputText = searchbox.getAttribute("value");
            searchbox.sendKeys(inputText);
            searchbox.sendKeys(Keys.ENTER);
        } catch (Exception e) {
            System.out.println("Max kapasiteyi aştınız.");
        }
        tearDown();
    }

    @Test
    public void wrongUserNameTest() {

        login("atakanyildiz27@gmail.com", "12345");
        Duration timeout = Duration.ofSeconds(10);
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        WebElement wrongUsernameWarning = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Kullanıcı adınız ya da şifreniz hatalı.']")));

        String text = wrongUsernameWarning.getText();

        assertTrue(wrongUsernameWarning.isDisplayed());
        assertEquals("Kullanıcı adınız ya da şifreniz hatalı.", text);
        tearDown();
    }

    @Test
    public void userNotExistWarningTest() {
        login("ceydaoymak09@gmail.com", "12345");
        Duration timeout = Duration.ofSeconds(10);
        WebDriverWait wait = new WebDriverWait(driver, timeout);

        try {
            WebElement userNotExistWarning = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Bu değer doğru bir e-mail adresi değildir.']")));
            assertTrue("Uyarı mesajı görünmeliydi", userNotExistWarning.isDisplayed());
            assertEquals("Bu değer doğru bir e-mail adresi değildir.", userNotExistWarning.getText());
        } catch (TimeoutException e) {
            System.out.println("The user not exist warning is not shown on the screen");
        }
        tearDown();
    }

    @Test
    public void newUser() throws InterruptedException {
        setup();
        WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(), 'Giriş Yap')]"));
        loginButton.click();
        WebElement signUpLink = driver.findElement(By.xpath("//a[text()='Kayıt Ol']"));
        signUpLink.click();
        WebElement name = driver.findElement(By.id("name"));

        Actions actions = new Actions(driver);
        actions.click(name).
                sendKeys("atakan").
                sendKeys(Keys.TAB).
                sendKeys("yıldız").sendKeys(Keys.TAB).
                sendKeys("atakanyildiz27@gmail.com").
                sendKeys(Keys.TAB).perform();

        WebElement phoneNumber = driver.findElement(By.id("phone"));
        phoneNumber.sendKeys("05330949127");


        WebElement passwordBox = driver.findElement(By.id("password"));
        String password = "abcdefg12345";
        passwordBox.sendKeys(password);

        WebElement passwordBox2 = driver.findElement(By.id("passwordRepeat"));
        passwordBox2.sendKeys(password);

        WebElement campaignCheckBox = driver.findElement(By.xpath("//input[@class='me-2 checkbox down-sm-width-height']"));

        campaignCheckBox.click();
        WebElement agreement = driver.findElement(By.id("agreement"));
        agreement.click();

        WebElement kvkk = driver.findElement(By.id("kvkk"));
        kvkk.click();

        WebElement registerButton = driver.findElement(By.xpath("//button[@class='btn w-100 down-sm-font-size-xxs text-black-color']"));
        registerButton.click();
        try {
            WebElement activationCode = driver.findElement(By.xpath("//label[text()='Aktivasyon Kodu']"));

            String elementText = activationCode.getText();
            if (elementText.contains("Aktivasyon Kodu")) {
                System.out.println("User registered successfully.");
            } else {
                System.out.println("Registration is unsuccessfull.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        tearDown();
    }

    @Test
    public void changeNameTest() {

        try {
            login(mail, password);
            WebElement element = driver.findElement(By.xpath("//span[contains(text(), 'Profilim')]"));
            element.click();
            WebElement profileLink = driver.findElement(By.linkText("Profilim"));
            profileLink.click();

            WebElement nameBox = driver.findElement(By.id("name"));
            nameBox.clear();
            nameBox.sendKeys("bora");
            WebElement updateButton = driver.findElement(By.xpath("//button[@class='btn w-100 down-sm-font-size-xxs text-black-color']"));
            updateButton.click();
        } catch (NoSuchElementException e) {
            System.out.println("Changing username is unsuccessfull!");
            throw e;
        }
        tearDown();
    }

    @Test
    public void addAddress() {

        login(mail, password);

        WebElement element = driver.findElement(By.xpath("//span[contains(text(), 'Profilim')]"));
        element.click();
        WebElement profileLink = driver.findElement(By.linkText("Profilim"));
        profileLink.click();
        WebElement addressButton = driver.findElement(By.id("nav-address-tab"));
        addressButton.click();
        WebElement addAddressButton = driver.findElement(By.xpath("//*[@id=\"nav-address\"]/div[1]/div/a"));
        addAddressButton.click();
        WebElement title = driver.findElement(By.id("title"));
        title.sendKeys("Ev");

        WebElement name = driver.findElement(By.id("fullName"));
        name.sendKeys("ati 242");

        WebElement selectCity = driver.findElement(By.id("city"));
        selectCity.click();

        WebElement izmirOption = selectCity.findElement(By.xpath("//option[not(@disabled) and text()='İzmir']"));
        izmirOption.click();

        WebElement selectDistrict = driver.findElement(By.name("district"));
        selectDistrict.click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement bornovaOption = selectDistrict.findElement(By.xpath("//option[not(@disabled) and text()='Bornova']"));
        bornovaOption.click();

        WebElement phoneNo = driver.findElement(By.cssSelector("input[placeholder='Telefon Numaranız']"));
        phoneNo.sendKeys("05071121902");

        WebElement postNo = driver.findElement(By.id("postCode"));
        postNo.sendKeys("35000");

        WebElement addres = driver.findElement(By.id("address"));
        addres.sendKeys("Kazımdirik mahallesi,Sanayi caddesi, no:17,Yaşar Üniversitesi");

        WebElement saveButton = driver.findElement(By.xpath("//*[@id=\"collapseExample\"]/div/form[2]/div[3]/div/button"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveButton);

        tearDown();

    }

    @Test
    public void addAdressTest() {

        login(mail, password);
        WebElement element = driver.findElement(By.xpath("//span[contains(text(), 'Profilim')]"));
        element.click();
        WebElement profileLink = driver.findElement(By.linkText("Profilim"));
        profileLink.click();
        WebElement addressButton = driver.findElement(By.id("nav-address-tab"));
        addressButton.click();

        WebElement address = driver.findElement(By.xpath("//p[text()='Kazımdirik mahallesi,Sanayi caddesi, no:17,Yaşar Üniversitesi']"));
        try {
            assertTrue(address.isDisplayed());
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            System.out.println("Address could not saved");
        }
        tearDown();
    }

    @Test
    public void profilePageInvalidIDNumberTest() throws InterruptedException {

        login(mail, password);
        Duration timeout = Duration.ofSeconds(10);
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        WebElement element = driver.findElement(By.xpath("//span[contains(text(), 'Profilim')]"));
        element.click();
        WebElement profileLink = driver.findElement(By.linkText("Profilim"));
        profileLink.click();

        WebElement idBox = driver.findElement(By.id("tcNo"));

        idBox.sendKeys("11111111111");

        WebElement updateButton = driver.findElement(By.xpath("//button[@class='btn w-100 down-sm-font-size-xxs text-black-color']"));
        updateButton.click();

        try {
            wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.id("tcNo"))));
            System.out.println("System accepts the invalid id number.");
        } catch (TimeoutException e) {
            System.out.println("System rejects the invalid id number.");
        }
        tearDown();
    }

    @Test
    public void profilPageInvalidPhoneNumberTest() {
        login(mail, password);
        Duration timeout = Duration.ofSeconds(10);
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        WebElement element = driver.findElement(By.xpath("//span[contains(text(), 'Profilim')]"));
        element.click();
        WebElement profileLink = driver.findElement(By.linkText("Profilim"));
        profileLink.click();
        WebElement phoneBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("phone")));
        wait.until(ExpectedConditions.elementToBeClickable(phoneBox));
        phoneBox.click();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].removeAttribute('data-maska-value');", phoneBox);
        phoneBox.clear();
        phoneBox.sendKeys("06748396756");


        WebElement updateButton = driver.findElement(By.xpath("//button[@class='btn w-100 down-sm-font-size-xxs text-black-color']"));
        updateButton.click();

        try {
            wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.id("phone"))));
            System.out.println("System accepts the invalid phone number without sending any codes to authentication.");
        } catch (TimeoutException e) {
            System.out.println("System rejects the invalid phone number.");
        }

    }

    @Test
    public void AddToCart() {

        login(mail, password);
        Duration timeout = Duration.ofSeconds(10);
        WebDriverWait wait = new WebDriverWait(driver, timeout);

        WebElement searchBox = driver.findElement(By.xpath("//input[@class='form-control ps-2 border-color border w-100']"));
        searchBox.sendKeys("Sambae White Black Gum");
        searchBox.sendKeys(Keys.ENTER);

        WebElement product = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h1[contains(@class, 'm-0') and contains(@class, 'font-size-base') and contains(@class, 'text-black-color') and contains(@class, 'fw-semibold') and contains(@class, 'lh-sm') and contains(@class, 'line-overflow') and contains(@class, 'down-sm-font-size-xxs') and text()='Sambae White Black Gum']")));
        product.click();

        WebElement shoeNumber = driver.findElement(By.xpath("//*[@id=\"collapseExample\"]/div/div[2]/div/div[3]/div/p[1]"));
        shoeNumber.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement addToCart = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='btn' and text()='SEPETE EKLE']")));
        addToCart.click();
    }

    @Test
    public void AddToCartNotificationTest() {
        try {
            AddToCart();
            Duration timeout = Duration.ofSeconds(10);
            WebDriverWait wait = new WebDriverWait(driver, timeout);

            WebElement notification = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='alert' and contains(@class, 'Vue-Toastification__toast-body') and text()='Ürün sepete eklendi!']")));

            String expectedOutput = "Ürün sepete eklendi!";
            String actualOutput = notification.getText();

            assertNotNull(notification);

            try {
                assertEquals(expectedOutput, actualOutput);
            } catch (AssertionError e) {
                System.out.println("Assertion failed: Actual toast text: \"" + actualOutput + "\"");
                throw e;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement productAtCart = driver.findElement(By.xpath("//p[@class='m-0 font-size-base black-color fw-500 text-start']"));
        String productText = productAtCart.getAttribute("innerText");

        if (productText.equals("Sambae White Black Gum")) {
            System.out.println("The product added to the cart correctly");
        }
        tearDown();
    }

    @Test
    public void addToCartCorrectSizeTest() {

        AddToCart();

        WebElement shoeNumber = driver.findElement(By.xpath("//*[@id=\"collapseExample\"]/div/div[2]/div/div[3]/div/p[1]"));

        WebElement shoeNumberAtCart = driver.findElement(By.xpath("//*[@id=\"__nuxt\"]/div/div[1]/div/div/div[2]/div/div[1]/div[2]/div[2]/p"));
        String text = shoeNumberAtCart.getText();
        String[] parts = text.split(":");
        String size = parts[1].trim();
        System.out.println(size);

        try {
            assertEquals(shoeNumber.getText(), size);
            System.out.println("The correct size added to the cart.");
        } catch (AssertionError e) {
            System.out.println("The sizes are different.");
        }
        tearDown();
    }

    @Test
    public void addToCartPriceTest() {

        AddToCart();
        WebElement actualPrice = driver.findElement(By.xpath("//*[@id=\"collapseExample\"]/div/div[2]/div/div[3]/div/p[2]"));

        WebElement addedPrice = driver.findElement(By.xpath("//*[@id=\"__nuxt\"]/div/div[1]/div/div/div[3]/div[2]/p"));
        try {
            assertEquals(actualPrice.getText(), addedPrice.getText());
            System.out.println("The correct price added to the cart.");

        } catch (AssertionError e) {
            System.out.println("The price is wrong.");

        }
        tearDown();
    }

    @Test
    public void getDescribeforProduct() {
        setup();
        String shoeName = "Kobe 8 Protro Venice Beach 2024";
        WebElement searchBox = driver.findElement(By.xpath("//input[@class='form-control ps-2 border-color border w-100']"));
        searchBox.sendKeys(shoeName);
        searchBox.sendKeys(Keys.ENTER);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        WebElement product = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"scrollTo\"]/div[2]/div[2]/div/div/div/div/div/a/div/div[1]/h1")));
        product.click();

        try {
            WebElement productInfo = driver.findElement(By.xpath("//p[@class='m-0 font-size-base fw-500 text-black-color down-sm-font-size-xxs']"));
            if (productInfo.isDisplayed() && productInfo.getText().contains("Kobe 8 Protro")) {
                System.out.println("Product has an explanation.");
            } else {
                System.out.println("Product's explanation does not entered.");
            }
        } catch (Exception e) {
            System.out.println("Product page could not found.");
        }
        tearDown();
    }

    @Test
    public void catogariesBarAtProductPageTest() {
        setup();
        try {
            Duration timeout = Duration.ofSeconds(10);
            WebDriverWait wait = new WebDriverWait(driver, timeout);

            WebElement searchBox = driver.findElement(By.xpath("//input[@class='form-control ps-2 border-color border w-100']"));
            searchBox.sendKeys("samba white");
            searchBox.sendKeys(Keys.ENTER);

            WebElement product = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h1[contains(@class, 'm-0') and contains(@class, 'font-size-base') and contains(@class, 'text-black-color') and contains(@class, 'fw-semibold') and contains(@class, 'lh-sm') and contains(@class, 'line-overflow') and contains(@class, 'down-sm-font-size-xxs') and text()='Sambae White Black Gum']")));
            product.click();

            Thread.sleep(5000);
            WebElement kategorilerMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='Menu']")));
            Actions actions = new Actions(driver);
            actions.moveToElement(kategorilerMenu).perform();


            WebElement ayakkabiMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='Ayakkabı']")));
            actions.moveToElement(ayakkabiMenu).perform();


            WebElement christianLouboutinOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='Christian Louboutin']")));
            christianLouboutinOption.click();

            wait.until(ExpectedConditions.titleContains("Christian Louboutin"));

        } catch (ElementClickInterceptedException e) {
            System.out.println("Catogaries options are not clickable.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        tearDown();
    }

    @Test
    public void searchBarBrandTest() {
        setup();
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(5));

        WebElement searchInput = driver.findElement(By.xpath("//input[@class='form-control ps-2 border-color border w-100' and @type='search' and @placeholder='Ayakkabı, Haber vb… Ara' and @aria-label='Search']"));
        searchInput.click();
        searchInput.sendKeys("adidas");
        List<String> adidasProducts = new ArrayList<>();
        try {
            boolean isCorrect = true;

            for (int i = 1; i <= 10; i++) {
                WebElement products = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"navbarSupportedContent\"]/div/div[2]/div/div[" + i + "]/a/div[2]/div/div[2]/p")));
                String productsText = products.getText();
                adidasProducts.add(productsText);
                System.out.println(productsText);
                if (!productsText.equals("adidas")) {
                    isCorrect = false;
                    break;
                }

            }

            if (isCorrect) {
                System.out.println("All products brand are adidas.");
            } else
                System.out.println("Incorrect listing.");

        } catch (StaleElementReferenceException e) {
            System.out.println("Stale element error.");
            e.printStackTrace();
        } catch (NoSuchElementException e) {
            System.out.println("Element dosen't find.");
            e.printStackTrace();
        }
        tearDown();
    }

    @Test
    public void footerTestAirjordan4() {
        setup();
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(20));


        WebElement targetElement = driver.findElement(By.xpath("//*[@id='__nuxt']/div/div/div[2]"));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        boolean atBottom = false;

        while (!isElementInViewport(targetElement, driver) && !atBottom) {
            js.executeScript("window.scrollBy(0, 250)");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Long scrollPosition = (Long) js.executeScript("return window.pageYOffset + window.innerHeight");
            Long pageHeight = (Long) js.executeScript("return document.body.scrollHeight");
            if (scrollPosition >= pageHeight) {
                atBottom = true;
            }
        }

        if (isElementInViewport(targetElement, driver)) {
            js.executeScript("arguments[0].scrollIntoView(true);", targetElement);
        }

        WebElement airjordan4 = driver.findElement(By.xpath("//a[@href='https://hype-merch.com/urunler?query=Air Jordan 4']"));
        airjordan4.click();

        List<String> text = new ArrayList<>();
        int i = 1;
        while (i <= 32) {
            WebElement priceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"scrollTo\"]/div[2]/div[2]/div/div/div[" + i + "]/div/div/a/div/div[2]/h3")));
            String pText = priceElement.getText();
            text.add(pText);
            if (i == 32)
                break;
            i++;
        }
        try {
            boolean isTrue = true;
            List<String> incorrect = new ArrayList<>();
            for (int j = 0; j < text.size() - 1; j++) {

                if (!text.get(j).contains("Air Jordan 4")) {
                    incorrect.add(text.get(j));
                }
                if (!incorrect.isEmpty()) {
                    isTrue = false;
                }
            }
            if (isTrue) {
                System.out.println("Prices's correct sort: ");
            } else {
                System.out.println("Prices's sort is incorrect" + incorrect);
            }
        } catch (AssertionError e) {
            System.out.println("Test failed: The text contains 'Air Jordan 4'");
        }
        tearDown();
    }

    public static boolean isElementInViewport(WebElement element, WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (boolean) js.executeScript(
                "var rect = arguments[0].getBoundingClientRect();" +
                        "return (rect.top >= 0 && rect.bottom <= (window.innerHeight || document.documentElement.clientHeight));",
                element);
    }

    @Test
    public void infiniteInvalidLoginTryTest() {
        try {
            login("atakanyildiz27@gmail.com", "1234567890");
            WebElement login = driver.findElement(By.xpath("//button[@class='btn w-100 down-sm-font-size-xxs text-black-color']"));
            for (int i = 1; i < 100; i++) {
                login.click();
            }
            System.out.println("User can try to login with wrong password for infinite times.");
        } catch (AssertionError e) {

        }
    }
}