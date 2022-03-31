package br.ce.wcaquino.appium;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;


public class Desafio {
	
	@SuppressWarnings("deprecation")
	@Test
	public void desafioTesteDosCampos() throws MalformedURLException {
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability("platformName", "Android");
		desiredCapabilities.setCapability("deviceName", "emulator-5554");
		desiredCapabilities.setCapability("automationName", "uiautomator2");
		desiredCapabilities.setCapability(MobileCapabilityType.APP,
				"/Users/paulo/eclipse-workspace/CursoAppium/src/main/resources/CTAppium_1_0.apk");

		AndroidDriver<MobileElement> driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"),
				desiredCapabilities);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		List<MobileElement> elementosEncontrados = driver.findElements(By.className("android.widget.TextView"));
		elementosEncontrados.get(1).click();
		
		MobileElement preencherNome = driver.findElement(MobileBy.AccessibilityId("nome"));
		preencherNome.sendKeys("Paulo Henrique");		
		String text = preencherNome.getText();
		Assert.assertEquals("Paulo Henrique", text);	
		
		driver.findElement(MobileBy.AccessibilityId("console")).click();
		driver.findElement(By.xpath("//android.widget.CheckedTextView[@text='PS4']")).click();
		
		driver.findElement(MobileBy.AccessibilityId("save")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@text='LIMPAR']")).click();
		
		//driver.quit();
	}
}
