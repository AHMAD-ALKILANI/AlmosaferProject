package myPackage;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class myTestCases extends Parameters {

	@BeforeTest
	public void mySetup() {
		ConfigurationToAccsess();
	}

	@Test(priority = 1)
	public void CheckTheDefualtLanguageIsEnglish() {
		String ActualLanguage = driver.findElement(By.tagName("html")).getDomAttribute("lang");

		Assert.assertEquals(ActualLanguage, ExpectedEnglishLanguage);
	}

	@Test(priority = 2)
	public void CheckTheDefualtCurrency() {
		String ActualCurrency = driver.findElement(By.cssSelector(".sc-dRFtgE.fPnvOO")).getText();

		Assert.assertEquals(ActualCurrency, ExpectedCurrency);
	}

	@Test(priority = 3)
	public void CheckMobileNumber() {
		String ActualMobileNumber = driver.findElement(By.tagName("Strong")).getText();
		Assert.assertEquals(ActualMobileNumber, ExpectedMobileNumber);
	}

	@Test(priority = 4)
	public void CheckQitafLogo() {

		WebElement TheFooter = driver.findElement(By.tagName("footer"));
		WebElement TheContainerDiv = TheFooter.findElement(By.cssSelector(".sc-ghsgMZ.hIElfs"));
		WebElement QitafLogo = TheContainerDiv.findElement(By.tagName("svg"));
		boolean ActualQitafLogoDisplayed = QitafLogo.isDisplayed();

		Assert.assertEquals(ActualQitafLogoDisplayed, ExpectedQitafLogoDisplayed);
	}

	@Test(priority = 5)
	public void CheckHotelTabIsNotSelected() {
		WebElement HotelTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		String ActualValueForHotelTab = HotelTab.getDomAttribute("aria-selected");
		Assert.assertEquals(ActualValueForHotelTab, ExpectedValueForHotelTab);
	}

	@Test(priority = 6)
	public void CheckDepatureDate() {
		List<WebElement> dates = driver.findElements(By.cssSelector(".sc-fvLVrH.hNjEjT"));
		String ActualDepartureDate = dates.get(0).getText();
		Assert.assertEquals(ActualDepartureDate, Tomorrow);
	}

	@Test(priority = 7)
	public void CheckRuternDate() {
		List<WebElement> dates = driver.findElements(By.cssSelector(".sc-fvLVrH.hNjEjT"));
		String ActualRuterndate = dates.get(1).getText();
		Assert.assertEquals(ActualRuterndate, DayAfterTomorrow);

		System.out.println(ActualRuterndate);
		System.out.println(DayAfterTomorrow);
	}

	@Test(priority = 8)
	public void RandomlyChangeWebsiteLanguage() throws InterruptedException {
		// first soluiton
		driver.get(Websites[RandomIndexForTheWibesite]);

		// if(driver.getCurrentUrl().contains("en")) {
		// String ActualLanguage =
		// driver.findElement(By.tagName("html")).getDomAttribute("lang");

		// Assert.assertEquals(ActualLanguage,ExpectedEnglishLanguage);
		// }
		// else {
		// String ActualLanguage =
		// driver.findElement(By.tagName("html")).getDomAttribute("lang");

		// Assert.assertEquals(ActualLanguage,ExpectedArabicLanguage);
		// }
		// }
		// Second solution
		driver.get(Websites[RandomIndexForTheWibesite]);
		WebElement HeaderForTheLanguage = driver.findElement(By.xpath("//a[@data-testid='Header__LanguageSwitch']"));

		WebElement HotelTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		HotelTab.click();

		WebElement SearchCityInput = driver.findElement(By.cssSelector(".sc-phbroq-2.uQFRS.AutoComplete__Input"));

		if (HeaderForTheLanguage.getText().equals("العربية")) {
			String ActualLanguage = driver.findElement(By.tagName("html")).getDomAttribute("lang");

			Assert.assertEquals(ActualLanguage, ExpectedEnglishLanguage);
			SearchCityInput.sendKeys(EnglishCities[RandomEnglishCity]);

			WebElement ListOfCities = driver.findElement(By.cssSelector(".sc-phbroq-4.gGwzVo.AutoComplete__List"));
			ListOfCities.findElements(By.tagName("li")).get(1).click();
		} else {
			String ActualLanguage = driver.findElement(By.tagName("html")).getDomAttribute("lang");

			Assert.assertEquals(ActualLanguage, ExpectedArabicLanguage);
			SearchCityInput.sendKeys(ArabicCities[RandomArabicCity]);
			WebElement ListOfCities = driver.findElement(By.cssSelector(".sc-phbroq-4.gGwzVo.AutoComplete__List"));
			ListOfCities.findElements(By.tagName("li")).get(1).click();
		}

		WebElement NumberOfVistor = driver.findElement(By.cssSelector(".sc-tln3e3-1.gvrkTi"));
		Select mySelector = new Select(NumberOfVistor);
		int RandomIndex = rand.nextInt(2);
		mySelector.selectByIndex(RandomIndex);

		WebElement SearchButton = driver.findElement(By.cssSelector(".sc-1vkdpp9-5.btwWVk"));
		SearchButton.click();

		Thread.sleep(10000);
		WebElement Results = driver.findElement(By.xpath("//span[@data-testid='srp_properties_found']"));
		System.out.println(Results.getText() + "@@@@@@@@");

		boolean ActualResult = Results.getText().contains("stays") || Results.getText().contains("مكان");
		Assert.assertEquals(ActualResult, ExpectedRuselts);

	}

}
