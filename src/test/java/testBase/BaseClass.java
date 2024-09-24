package testBase;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	public static WebDriver driver;
	public Logger logger;
	public Properties prop;

	@SuppressWarnings("deprecation")
	@BeforeClass(alwaysRun = true)
	@Parameters({ "os", "browser" })
	public void setUp(String os, String br) throws FileNotFoundException, IOException {
		logger = LogManager.getLogger(this.getClass());

		FileReader file = new FileReader("./src//test//resources//config.properties");
		prop = new Properties();
		prop.load(file);
		//selenium grid setUp
		if (prop.getProperty("execution_env").equalsIgnoreCase("remote")) {
			
			DesiredCapabilities cap = new DesiredCapabilities();

			if (os.equalsIgnoreCase("windows")) {
				cap.setPlatform(Platform.WIN11);

			} else if (os.equalsIgnoreCase("edge")) {
				cap.setPlatform(Platform.MAC);
			} else {
				System.out.println("No Match Opertaing provided in xml file");
			}
			switch (br.toLowerCase()) {
			case "chrome":
				cap.setBrowserName("chrome");
				break;
			case "edge":
				cap.setBrowserName("MicrosoftEdge");
				break;
			default:
				System.out.println("No browser is provided in xml file");
			}
			
			driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
	
}
		//Local setUp
		if (prop.getProperty("execution_env").equalsIgnoreCase("local")) {
			
			switch (br.toLowerCase()) {
			case "chrome":
				driver = new ChromeDriver();
				break;
			case "edge":
				driver = new EdgeDriver();
				break;
			default:
				System.out.println("Invalid browser Name");
				return;
			}
		}

		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get(prop.getProperty("appURL"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}

	public String randomString() {
		return RandomStringUtils.randomAlphabetic(5);
		
	}

	public String randomNumber() {
		return RandomStringUtils.randomNumeric(10);
	}

	public String randomEmail() {
		return randomString() + randomNumber() + "@gmail.com";
	}

	public String randomPassword() {
		return randomString() + RandomStringUtils.randomAlphanumeric(3);

	}

	public String caputureScreenShot(String testName) {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		TakesScreenshot takeScreenShot = (TakesScreenshot) driver;
		File sourceFile = takeScreenShot.getScreenshotAs(OutputType.FILE);
		String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + testName + "-" + timeStamp
				+ ".png";
		File targetFile = new File(targetFilePath);
		sourceFile.renameTo(targetFile);
		return targetFilePath;

	}

}
