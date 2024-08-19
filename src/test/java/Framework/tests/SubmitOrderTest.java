package Framework.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Framework.Testcomponents.BaseTest;
import Framework.pageobject.CartPage;
import Framework.pageobject.CheckOutPage;
import Framework.pageobject.ConfirmationPage;
import Framework.pageobject.OrdersPage;
import Framework.pageobject.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {

	String productName;

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {

		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		// ProductCatalogue productCatalogue = new ProductCatalogue(driver);

		List<WebElement> products = productCatalogue.getProductsList();
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.verifyProductTitles(input.get("product"));
		Assert.assertTrue(match);

		CheckOutPage checkOutPage = cartPage.goToCheckOut();
		checkOutPage.selectCountry("india");
		ConfirmationPage confirmationPage = checkOutPage.submitOrder();

		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));

	}
	/*
	@Test(dependsOnMethods = { "submitOrder" })
	public void orderHistoryTest() {
		ProductCatalogue productCatalogue = landingPage.loginApplication("Selenium32@gmail.com", "Selenium@32");
		OrdersPage ordersPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(ordersPage.verifyOrderDisplay(productName));

	}
	*/
	
	@DataProvider
	public Object[][] getData() throws IOException {

		/*
		 * HashMap<String, String> map = new HashMap<String, String>(); map.put("email",
		 * "Selenium32@gmail.com"); map.put("password", "Selenium@32");
		 * map.put("product", "IPHONE 13 PRO");
		 * 
		 * HashMap<String, String> map1 = new HashMap<String, String>();
		 * map1.put("email", "Selenium51@gmail.com"); map1.put("password",
		 * "Selenium@51"); map1.put("product", "ZARA COAT 3");
		 */

		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "\\src\\test\\java\\Frameworrk\\Data\\Purchaseorder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };

	}

	/*
	 * @DataProvider public Object[][] getData() { return new Object[][] { {
	 * "Selenium32@gmail.com", "Selenium@32", "IPHONE 13 PRO" }, {
	 * "anshika@gmail.com", "Iamking@000", "ZARA COAT 3" } };
	 */

}
