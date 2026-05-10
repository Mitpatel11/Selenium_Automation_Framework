package MitPatel.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import MitPate.pageobjects.CartPage;
import MitPate.pageobjects.ProductCatelogue;
import MitPatel.TestComponents.BaseTest;
import MitPatel.TestComponents.Retry;

public class ErrorValidation extends BaseTest {

	@Test(groups = { "ErrorHandling" }, retryAnalyzer = Retry.class)
	public void LoginInErrorValidation() throws IOException {
		String productName = "ZARA COAT 3";
		ProductCatelogue productCatelogue = landingPage.loginApplication("mitpatel@yahoo.com", "hNKurJ#!3CV.3G9");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());

		// Code will be in the LandingPage as we are handling that page
	}

	@Test // example that we have to use the strategy Eg: combine all the error message
			// validations of the LoginPage in one Java Class then so on. Rather than
			// creating multiple java class as 100 java class making is not an optimal
			// solution
	public void ProductErrorValidation() throws IOException {
		String productName = "ZARA COAT 3";

		ProductCatelogue productCatelogue = landingPage.loginApplication("mitpatel@yahoo.com", "b#5kDgMmJ3Ftrm6");
		List<WebElement> products = productCatelogue.getProductList();
		productCatelogue.AddProductToCart(productName);

		CartPage cartPage = productCatelogue.goToCartPage();

		Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 3123123");
		Assert.assertFalse(match);

	}
}
