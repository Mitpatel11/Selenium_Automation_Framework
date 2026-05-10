package MitPatel.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import MitPate.pageobjects.CartPage;
import MitPate.pageobjects.CheckoutPage;
import MitPate.pageobjects.ConfirmationPage;
import MitPate.pageobjects.OrderPage;
import MitPate.pageobjects.ProductCatelogue;
import MitPatel.TestComponents.BaseTest;

public class PageFactoryActionsTest extends BaseTest {
	String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData" , groups = {"Purchase"})
	public void submitOrderTest(HashMap<String,String> input) throws IOException {

		// the object is coming from the
		// BaseTest and object is created as
		// public LandingPage landingPage also we have set it as Before method so it
		// handles everything here

		ProductCatelogue productCatelogue = landingPage.loginApplication(input.get("email"), input.get("password"));

		// we set the new creation in the java class to do lesser code and smarter way
		// to handle and keep the code clean.
		// ProductCatelogue productCatelogue = new ProductCatelogue(driver);
		List<WebElement> products = productCatelogue.getProductList();
		productCatelogue.AddProductToCart(input.get("product"));

		// driver.findElement(By.cssSelector("[routerlink*='cart']")).click(); // this
		// is re-usable as cart is on header of the application and shows on all the
		// page.
		// so we will define it in the abstractcomponents
		CartPage cartPage = productCatelogue.goToCartPage(); // here we can access from any of the page objects both are
																// child and the abstract is parent. both extends the
																// abstract

		Boolean match = cartPage.VerifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckoutPage();

		checkoutPage.selectCountry("India");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String success = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(success.equalsIgnoreCase("Thankyou for the order."));

		// driver.close(); we have set this as AfterMethod so code looks clean
	}

	// to verify that the product is displaying in the orders page

	@Test(dependsOnMethods = ("submitOrderTest"))
	public void OrderHistoryTest() {
		// here we have to link the above test case as we have to add first then check
		// in the history page we use depends
		ProductCatelogue productCatelogue = landingPage.loginApplication("mitpatel807@yahoo.com", "hNKurJ#!3CV.3G9");

		OrderPage orderPage = productCatelogue.goToOrdersPage();

		Assert.assertTrue(orderPage.VerifyOrderDisplay(productName));

	}

	@DataProvider
	public Object[][] getData() throws IOException {

//		HashMap<String,String> map = new HashMap <String,String>();
//		map.put("email", "mitpatel807@yahoo.com");
//		map.put("password", "hNKurJ#!3CV.3G9");
//		map.put("product", "ZARA COAT 3");


//		HashMap<String,String> map1 = new HashMap <String,String>();
//		map1.put("email", "mitpatel807@yahoo.com");
//		map1.put("password", "hNKurJ#!3CV.3G9");
//		map1.put("product", "ADIDAS ORIGINAL");

		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir") + "//src//test//java/MitPatel//data//PurchaseOrder.json");
		return new Object [][] {{data.get(0)} , {data.get(1)} };
	}

}
