package Framework.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Framework.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {

	WebDriver driver;

	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// List<WebElement> products = driver.findElements(By.className("mb-3"));

	@FindBy(className = "mb-3")
	List<WebElement> products;

	By productsBy = By.className("mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastContainer = By.id("toast-container");

	@FindBy(css = ".ng-animating")
	WebElement spinner;

	public List<WebElement> getProductsList() throws InterruptedException {
		waitForElementToAppear(productsBy);
		return products;
	}

	public WebElement getProductByName(String productname) throws InterruptedException {
		WebElement prod = getProductsList().stream()
				.filter(product -> product.findElement(By.cssSelector("h5 b")).getText().equals(productname))
				.findFirst().orElse(null);
		return prod;
	}

	public void addProductToCart(String productname) throws InterruptedException {
		WebElement prod = getProductByName(productname);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastContainer);
		waitForElementToDisappear(spinner);

	}

}
