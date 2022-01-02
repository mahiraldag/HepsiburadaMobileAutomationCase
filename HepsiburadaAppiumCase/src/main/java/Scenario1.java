import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class Scenario1 {
    public AndroidDriver<MobileElement> driver;
    public WebDriverWait wait;

    By locationButton = By.id("com.pozitron.hepsiburada:id/textViewLocation");
    By cityDropdown = By.xpath("//android.widget.ImageView[@content-desc=\"İl seçin\"]");
    By randomCity = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.widget.FrameLayout/androidx.cardview.widget.CardView/android.view.ViewGroup/android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[3]/android.widget.TextView");
    By districtDropdown = By.xpath("//android.widget.ImageView[@content-desc=\"İlçe seçin\"]");
    By randomDistrict = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.widget.FrameLayout/androidx.cardview.widget.CardView/android.view.ViewGroup/android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[3]/android.widget.TextView");
    By areaDropdown = By.xpath("//android.widget.ImageView[@content-desc=\"Mahalle seçin\"]");
    By randomArea = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.widget.FrameLayout/androidx.cardview.widget.CardView/android.view.ViewGroup/android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[3]/android.widget.TextView");
    By locationSaveButton = By.id("com.pozitron.hepsiburada:id/button");
    By locationSavedPopUpClose = By.id("com.pozitron.hepsiburada:id/ivClose"); 
    By categoryTabButton = By.id("com.pozitron.hepsiburada:id/nav_graph_category") ;
    By sizeOzel = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[2]/androidx.recyclerview.widget.RecyclerView/androidx.cardview.widget.CardView[1]/android.view.ViewGroup/android.widget.TextView");
    By randomCategory = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.ScrollView/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]/androidx.cardview.widget.CardView/android.widget.ImageView");
    By yarinKapindaCityName = By.id("com.pozitron.hepsiburada:id/textViewLocation");



    @BeforeClass
    public void setup() throws MalformedURLException {
        driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), TestUtil.getDefaultDesiredCapabilities());
        wait = new WebDriverWait(driver, 10);
    }

    @Test (priority = 0)
    public void openLocationSection() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locationButton)).click();
    }

    @Test(dependsOnMethods = "openLocationSection")
    public void enterLocationDetails() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(cityDropdown)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(randomCity)).click(); 
        wait.until(ExpectedConditions.visibilityOfElementLocated(districtDropdown)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(randomDistrict)).click();  
        wait.until(ExpectedConditions.visibilityOfElementLocated(areaDropdown)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(randomArea)).click();
    }

    @Test(dependsOnMethods = "enterLocationDetails")
    public void saveLocationDetails() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locationSaveButton)).click();
    }

   
    @Test(dependsOnMethods = "saveLocationDetails")
    public void checkLocationSavedPopup() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locationSavedPopUpClose));
    }

    @Test(dependsOnMethods = "checkLocationSavedPopup")
    public void openCategories() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(categoryTabButton)).click();
    }

    @Test(dependsOnMethods = "openCategories")
    public void openRandomCategory() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(sizeOzel)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(randomCategory)).click();
    }

    @Test(dependsOnMethods = "openRandomCategory")
    public void checkCityNameFromLocation() {
        String yarinKapimdaCityName = wait.until(ExpectedConditions.visibilityOfElementLocated(yarinKapindaCityName)).getText();
        Assert.assertTrue(yarinKapimdaCityName.toLowerCase().contains("Afyon"));
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }
}
