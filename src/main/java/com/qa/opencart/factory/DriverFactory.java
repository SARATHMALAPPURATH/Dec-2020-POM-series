package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * 
 * @author sarat
 *
 */

public class DriverFactory {

	WebDriver driver;
	Properties prop;
	public static String highlight;
	OptionsManager optionsManager;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

	/**
	 * This method is used to init the driver on the basis of given browser value
	 * 
	 * @param browserName
	 * @return WebDriver
	 */

	public WebDriver init_driver(Properties prop) {

		String browserName = prop.getProperty("browser").trim();
		highlight = prop.getProperty("highlight").trim();
		optionsManager = new OptionsManager(prop);

		System.out.println("browser name is: " + browserName);

		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
//			driver = new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
//			driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));

		} else {
			System.out.println("Browser is not found.. please pass the correct browser name " + browserName);
		}

//		driver.manage().window().fullscreen();
//		driver.manage().deleteAllCookies();
//		driver.get(prop.getProperty("url").trim());
		getDriver().manage().window().fullscreen();
		getDriver().manage().deleteAllCookies();
		getDriver().get(prop.getProperty("url").trim());

		return getDriver();
	}

	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}

	/**
	 * This method is initializing the properties from .properties file
	 * 
	 * @return properties(prop)
	 */

	public Properties init_prop() {
		prop = new Properties();
		FileInputStream ip = null;
		String env = System.getProperty("env");
		if (env == null) {
			try {
				ip = new FileInputStream(
						"C:\\Users\\sarat\\eclipse-workspace\\Dec2020POMSeries\\src\\test\\resources\\config\\config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			try {
				switch (env) {
				case "qa":
					ip = new FileInputStream(
							"C:\\Users\\sarat\\eclipse-workspace\\Dec2020POMSeries\\src\\test\\resources\\config\\qa.config.properties");
					break;
				case "stage":
					ip = new FileInputStream(
							"C:\\Users\\sarat\\eclipse-workspace\\Dec2020POMSeries\\src\\test\\resources\\config\\stage.config.properties");
					break;
				case "dev":
					ip = new FileInputStream(
							"C:\\Users\\sarat\\eclipse-workspace\\Dec2020POMSeries\\src\\test\\resources\\config\\dev.config.properties");
					break;
				default:
					System.out.println("Please pass the right environment");
					break;
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

	/**
	 * take screenshot
	 * 
	 * @return
	 */

	public String getScreenshot() {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
//		File srcFile = new File(src);

		String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);

		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}
}
