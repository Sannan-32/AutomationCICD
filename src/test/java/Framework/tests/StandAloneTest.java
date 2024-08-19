package Framework.tests;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Framework.pageobject.LandingPage;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {

		String productName = "IPHONE 13 PRO";

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		
		LandingPage landingPage = new LandingPage(driver);

		driver.findElement(By.id("userEmail")).sendKeys("Selenium32@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Selenium@32");
		driver.findElement(By.id("login")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		// driver.findElement(By.cssSelector("button[class='btn w-10
		// rounded']")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("mb-3")));
		List<WebElement> products = driver.findElements(By.className("mb-3"));

		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("h5 b")).getText().equals("IPHONE 13 PRO"))
				.findFirst().orElse(null);

		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		// prod.findElement(By.cssSelector("button[class='btn w-10 rounded']")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		/*WebElement element = driver.findElement(By.cssSelector("[routerlink*=cart]"));
		Actions a=new Actions(driver);
		a.moveToElement(element).click().build().perform();*/
		
		driver.findElement(By.cssSelector("[routerlink*=cart]")).click();

		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));

		boolean match = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		Actions a=new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		
		driver.findElement(By.cssSelector(".action__submit")).click();
		String confirmPurchase= driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmPurchase.equalsIgnoreCase("Thankyou for the order."));
		
		
		
		
	}

}
