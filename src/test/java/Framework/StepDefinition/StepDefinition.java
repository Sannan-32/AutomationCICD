package Framework.StepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Framework.Testcomponents.BaseTest;
import Framework.pageobject.CartPage;
import Framework.pageobject.CheckOutPage;
import Framework.pageobject.ConfirmationPage;
import Framework.pageobject.LandingPage;
import Framework.pageobject.ProductCatalogue;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinition extends BaseTest {
	
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmationPage;
	
	@Given("I landed on Ecommerce page")
	public void I_landed_on_Ecommerce_page() throws IOException {
		landingPage=launchApplication();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void Logged_in_with_username_and_password(String username,String password) {
		productCatalogue = landingPage.loginApplication(username,password);

	}
	
	@When("^I add product (.+) to Cart$")
	public void I_add_product_to_Cart(String productName) throws InterruptedException {
		List<WebElement> products = productCatalogue.getProductsList();
		productCatalogue.addProductToCart(productName);
	}
	
	@When("^Checkout (.+) and submit the order$")
	public void checkout_submit_order(String productName) throws InterruptedException {
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.verifyProductTitles(productName);
		Assert.assertTrue(match);
		CheckOutPage checkOutPage = cartPage.goToCheckOut();
		checkOutPage.selectCountry("india");
		confirmationPage = checkOutPage.submitOrder();
	}
	
	@Then("{string} message is displayed on the ConfirmationPage")
	public void confirmation_message(String string) {
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
	}
	
	@Then("{string} message is displayed")
	public void error_confirmation_message(String string) {
		Assert.assertEquals(string, landingPage.getErrorMessage());
	}
	

}
