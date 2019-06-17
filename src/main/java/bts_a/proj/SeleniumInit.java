package bts_a.proj;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumInit {
	
	private WebDriver driver;
	
	public SeleniumInit() {
		String os = System.getProperty("os.name").toLowerCase();
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver_mac");
		if(os.contains("linux")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver_linux");
		}
		driver = new ChromeDriver();
		driver.get("https://www.patagonia.com/shop/mens-jackets-vests");
	}
	
	public WebDriver getDriver() {
		return driver;
	}
}
