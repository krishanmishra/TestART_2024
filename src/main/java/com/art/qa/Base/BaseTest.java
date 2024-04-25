package com.art.qa.Base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.art.qa.pageHelper.AccountViewerPage;
import com.art.qa.pageHelper.CreateFluxPage;
import com.art.qa.pageHelper.HomePage;
import com.art.qa.pageHelper.LoginPage;

public class BaseTest {

	private WebDriver driver;
	String browserName;
	String enviromentName;

	/* pages object initialization */
	protected LoginPage loginPage;
	protected HomePage homePage;
	protected CreateFluxPage createFluxPage;
	protected AccountViewerPage accountViewerPage;

	/*******************************************
	 * Before Suite Select the browser
	 ***********************/
	@BeforeMethod
	public void beforeSuite() throws Exception {

		// here we are checking if the value is coming from the maven command (mvn test)
		// if so then read that otherwise read that from data properties
		browserName = System.getProperty("browser") != null ? System.getProperty("browser")
				: Utilities.readPropertiesFile("browser");
		
		//DriverType.Chrome.toString().toLowerCase()
		System.out.println(browserName);

		// headless browser
		boolean headlessBrowser = System.getProperty("headless") != null
				? Boolean.parseBoolean(System.getProperty("headless"))
				: false;

		if (DriverType.Firefox.toString().toLowerCase().equals(browserName.toLowerCase())) {
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			if (headlessBrowser) {
				firefoxOptions.addArguments("--headless");
			}
			firefoxOptions.setAcceptInsecureCerts(true);
			driver = new FirefoxDriver(firefoxOptions);

		} else if (DriverType.Chrome.toString().toLowerCase().equals(browserName.toLowerCase())) {

			ChromeOptions options = new ChromeOptions();
			// options.setHeadless(true);
			if (headlessBrowser) {
				options.addArguments("--headless");
			}
			options.addArguments("--remote-allow-origins=*");
			options.setAcceptInsecureCerts(true);
			driver = new ChromeDriver(options);

		} else {
			throw new Exception("Please pass valid browser type value");
		}
		/* Delete cookies */
		getDriver().manage().deleteAllCookies();

		/* maximize the browser */
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));

		loginPage = new LoginPage(getDriver());
		homePage = new HomePage(getDriver());
		accountViewerPage = new AccountViewerPage(getDriver());
		createFluxPage =new CreateFluxPage(getDriver());

	}

	@BeforeMethod
	public void setUp() throws Exception {

		enviromentName = System.getProperty("environment") != null ? System.getProperty("environment")
				: Utilities.readPropertiesFile("environment");

		// enviromentName = Utilities.readPropertiesFile("Environment");
		System.out.println(Environment.QA.toString().toLowerCase());

		if (Environment.INTG.toString().toLowerCase().equals(enviromentName.toLowerCase())) {
			getDriver().get("https://54.184.15.236/ARTWeb");

		} else if (Environment.QA.toString().toLowerCase().equals(enviromentName.toLowerCase())) {

			getDriver().get("https://172.31.34.187/ART");

		} else if (Environment.Demo.toString().toLowerCase().equals(enviromentName.toLowerCase())) {
			getDriver().get("https://demo.skystem.com");

		}else if (Environment.SSOUser.toString().toLowerCase().equals(enviromentName.toLowerCase())) {
			getDriver().get("https://172.31.34.187/ART/app/login/customer/TestSSO1");
			
		}else if (Environment.APIUser.toString().toLowerCase().equals(enviromentName.toLowerCase())) {
			getDriver().get("http://172.31.34.187/ARTAPITest/");
			
		}else {
			throw new Exception("Please pass valid environment");
		}

	}

//	@AfterMethod
//	public void afterTest() {
//		
//		getDriver().close();
//	}
	
	/*******************************************
	 * After Suite Quit the browser
	 ***********************/
	@AfterTest
	public void afterSuite() {		
			
			getDriver().close();	

	}

	/*****************************************
	 * getter the driver
	 *********************/
	public WebDriver getDriver() {
		return driver;
	}

	/*******************************************
	 * Driver type as Enum
	 ***********************/
	enum DriverType {
		Firefox, IE, Chrome
	}

	/*******************************************
	 * Environment as Enum
	 ***********************/
	enum Environment {
		QA, INTG, Demo,SSOUser,APIUser
	}

}
