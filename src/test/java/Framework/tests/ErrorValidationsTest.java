package Framework.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import Framework.Testcomponents.BaseTest;
import Framework.Testcomponents.Retry;
import Framework.pageobject.CartPage;
import Framework.pageobject.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups= {"ErrorHandling"}, retryAnalyzer=Retry.class)
	public void loginErrorValidation() throws IOException {

		landingPage.loginApplication("Selenium32@gmail.com", "Selenium@33");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());

	}
	
	@Test
	public void productErrorValidation() throws IOException, InterruptedException {
		
		String productName = "IPHONE 13 PRO";
		ProductCatalogue productCatalogue = landingPage.loginApplication("Selenium32@gmail.com", "Selenium@32");
		List<WebElement> products = productCatalogue.getProductsList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.verifyProductTitles("IPHONE 14 PRO");
		Assert.assertFalse(match);
	}

}
