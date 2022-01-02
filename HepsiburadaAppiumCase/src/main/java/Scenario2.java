import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class Scenario2 {

    public AndroidDriver<MobileElement> driver;
    public WebDriverWait wait;



    By superFiyatSuperTeklifButton = By.id("com.pozitron.hepsiburada:id/dod_all");
    By checkProductWithMultiplePics = By.id("com.pozitron.hepsiburada:id/pi_product_list_item_image"); // Index = 1 olmalı
    By openProductWithMultiplePics = By.id("com.pozitron.hepsiburada:id/tv_product_list_item_name");
    By openProductPics = By.id("com.pozitron.hepsiburada:id/productImage");
    By firstPicForScrolling = By.id("com.pozitron.hepsiburada:id/zoomableImage");
    By favoriteButton = By.id("com.pozitron.hepsiburada:id/product_detail_favourites");
    By clickOnUsernameInput = By.id("txtUserName");
    By clickOnGirişYapInUsernamePage = By.id("btnLogin");
    By clickOnPasswordInput = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[1]/android.view.View[4]/android.view.View/android.widget.EditText");
    By clickOnGirisYapInPasswordPage = By.id("btnEmailSelect");
    By clickAccount = By.id("com.pozitron.hepsiburada:id/account_icon");
    By myFavorites = By.xpath("//android.view.ViewGroup[@content-desc=\"account_menu_5\"]");
    By favoritedProduct = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[1]/android.webkit.WebView/android.webkit.WebView/android.view.View/android.widget.ListView/android.view.View/android.view.View[5]");
    By numberOfFavorites = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[1]/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[5]");
    String favoritedProductName;

    @BeforeClass
    public void setup() throws MalformedURLException {
        driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), TestUtil.getDefaultDesiredCapabilities());
        wait = new WebDriverWait(driver, 10);
    }

    @Test (priority = 0)
    public void expandSuperFiyatSuperTeklifProducts() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(superFiyatSuperTeklifButton)).click();
    }

    @Test(dependsOnMethods = "expandSuperFiyatSuperTeklifProducts")
    public void findProductWithMultiplePics() {
        String multiplePicsProductIndex = wait.until(ExpectedConditions.visibilityOfElementLocated(checkProductWithMultiplePics)).getAttribute("index");
        favoritedProductName = wait.until(ExpectedConditions.visibilityOfElementLocated(checkProductWithMultiplePics)).getText();
        Assert.assertTrue(multiplePicsProductIndex.toLowerCase().contains("1"));
    }

    @Test(dependsOnMethods = "findProductWithMultiplePics")
    public void openProductWithMultiplePics() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(openProductWithMultiplePics)).click();

    }

    @Test(dependsOnMethods = "openProductWithMultiplePics")
    public void openProductPicsInProductDetail() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(openProductPics)).click();
    }

    @Test(dependsOnMethods = "openProductPicsInProductDetail")
    public void scrollHorizontally() {
        TouchActions action = new TouchActions(driver);
        action.scroll(wait.until(ExpectedConditions.visibilityOfElementLocated(firstPicForScrolling)), 10, 100);
        action.perform();
        // Did action end scrolling?
    }

    @Test(dependsOnMethods = "scrollHorizontally")
    public void closeProductPics() {
        // driver go back
        driver.navigate().back();
    }

    @Test(dependsOnMethods = "closeProductPics")
    public void addProductToFavorites() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(favoriteButton)).click();
    }

    @Test(dependsOnMethods = "addProductToFavorites")
    public void enterUsernameAndClickOnLogin() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(clickOnUsernameInput)).sendKeys("luxuriantjackal@maildrop.cc");
        //MobileElement element1 = (MobileElement) driver.findElementById(String.valueOf(clickOnUsernameInput));
        //element.sendKeys("Hello world!");
        wait.until(ExpectedConditions.visibilityOfElementLocated(clickOnGirişYapInUsernamePage));
    }

    @Test(dependsOnMethods = "enterUsername")
    public void enterPasswordAndLogin() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(clickOnPasswordInput)).sendKeys("Test1234");
        wait.until(ExpectedConditions.visibilityOfElementLocated(clickOnGirisYapInPasswordPage)).click();
    }

    @Test(dependsOnMethods = "enterPasswordAndLogin")
    public void goToHomepage() {
        // driver go back
        driver.navigate().back();
    }

    @Test(dependsOnMethods = "enterPasswordAndLogin")
    public void clickOnProfile() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(clickAccount)).click();
    }

    @Test(dependsOnMethods = "enterPasswordAndLogin")
    public void clickOnMyFavorites() {
        // Check "Beğendiklerim" first
        String favoritesText = wait.until(ExpectedConditions.visibilityOfElementLocated(myFavorites)).getText();
        Assert.assertTrue(favoritesText.toLowerCase().contains("beğendiklerim"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(myFavorites)).click();
    }

    @Test(dependsOnMethods = "enterPasswordAndLogin")
    public void findFavoritedItem() {
        // Verify that there is "1" item on favorites list
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(numberOfFavorites)).getText().contains("1"));
    }


    @AfterClass
    public void teardown() {
        driver.quit();
    }

}
