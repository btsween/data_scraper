package bts_a.proj;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.yaml.snakeyaml.*;

public class Scraper {
	private String itemContainerPath;
	private WebElement itemContainer;
	private WebDriver driver;
	private DatabaseConnection sqlHandle;

	public Scraper() throws InterruptedException {
		driver = new SeleniumInit().getDriver();
		itemContainerPath = "//ul[@id='search-result-items']";
		itemContainer = driver.findElement(By.xpath(itemContainerPath));
		System.out.println(itemContainer.getAttribute("innerHTML"));
		//sqlHandle = new DatabaseConnection();
		execute();
	}

	public void grabData() {
		int nodeNum = Integer.parseInt(itemContainer.getAttribute("data-products-returned"));
		System.out.println(nodeNum);
		Map<String, Integer> data = new HashMap<String, Integer>();
		int i = 1;
		while (i <= nodeNum) {
			try {
				String nodePath = (itemContainerPath) + "/li[" + i + "]";
				WebElement currentNode = driver.findElement(By.xpath(nodePath));
				WebElement catalogNum = driver.findElement(By.xpath(nodePath + "/div[1]"));
				String cn = catalogNum.getAttribute("data-itemid");
				WebElement price = driver.findElement(By.xpath(nodePath + "/descendant::span[@class='price-sales']"));
				String p = price.getText();
				if (!p.isEmpty() && !cn.isEmpty()) {
					Integer priceInt = Integer.parseInt(p.substring(1, p.length() - 3));
					System.out.println("For catalog number: " + cn + " price is: " + p);
					//data.put(cn, priceInt);
					//sqlHandle.insertData(cn, priceInt);
				}
				((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", currentNode);
			} catch (Exception e) {
				System.out.println("Data grab failed . . .");
				System.out.println(e);
			}
			try {
				Thread.sleep(1500);
			} catch (Exception e) {
				System.out.println("Sleep failed . . .");
			}
			i++;
		}
	}

	public void writeToFile(Map<String,Integer> data) {
		Yaml yaml = new Yaml();
		FileWriter writer;
		try {
			writer = new FileWriter(System.getProperty("user.dir") + "/data.yml");
			yaml.dump(data, writer);
		} catch (IOException e) {
			System.out.println("File writing has failed . . .");
		}
	}

	public void execute() {
		grabData();
		driver.quit();
	}

	public static void main(String args[]) throws InterruptedException {
		new Scraper();
	}
}
