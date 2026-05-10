package MitPate.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import MitPate.pageobjects.CartPage;
import MitPate.pageobjects.OrderPage;

public class AbstractComponent {

	WebDriver driver;

	public AbstractComponent(WebDriver driver) { // constructor
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "[routerlink*='cart']")
	WebElement cartbtn;

	@FindBy(css = "[routerlink*='myorders']")
	WebElement orderbtn;

	public void WebElemetToAppear(By findBy) { // for By

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}

	public void WaitforWebElemetToAppear(WebElement findBy) { // for webelement

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}

	public void waitforElementtoDisappear(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOf(element));
	}

	public OrderPage goToOrdersPage() {

		orderbtn.click();
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;
	}

	public CartPage goToCartPage() {
		cartbtn.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}

}
