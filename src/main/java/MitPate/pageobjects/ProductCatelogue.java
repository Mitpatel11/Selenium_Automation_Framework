package MitPate.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import MitPate.AbstractComponents.AbstractComponent;

public class ProductCatelogue extends AbstractComponent {

	WebDriver driver;

	public ProductCatelogue(WebDriver driver) {
		super(driver);  // with this and creating a in the AbstractComponents class use constructor for driver.
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

	@FindBy(css = ".mb-3")
	List<WebElement> products;

	@FindBy(css = ".ng-animating")
	WebElement spinner;

	By productsBy = By.cssSelector(".mb-3");

	By addtocart = By.cssSelector(".card-body button:last-of-type");

	By toastMessage = By.cssSelector("#toast-container");

	public List<WebElement> getProductList() {
		WebElemetToAppear(productsBy);
		return products;
	}


	public WebElement getProductByName(String productName) {
		WebElement reqprod = getProductList().stream()        // here use the class instead of the variable products as we have to wait for the products.
				.filter(product -> product.findElement(By.tagName("b")).getText().equals(productName)).findFirst()
				.orElse(null);
		return reqprod;
	}

	public void AddProductToCart(String productName) {
		WebElement reqprod = getProductByName(productName);
		reqprod.findElement(addtocart).click();
		WebElemetToAppear(toastMessage);// here we cant use the PageFactory as we have scope set and not the driver.findElemetns etc.....
		//here it is WebElement.findElement so we cant use PageFactory
		waitforElementtoDisappear(spinner);
	}
}
