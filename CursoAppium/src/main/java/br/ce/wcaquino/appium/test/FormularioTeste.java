package br.ce.wcaquino.appium.test;

import static org.junit.Assert.assertEquals;

import java.net.MalformedURLException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import br.ce.wcaquino.appium.core.DSL;
import br.ce.wcaquino.appium.core.DriverFactory;
import br.ce.wcaquino.appium.page.FormularioPage;
import br.ce.wcaquino.appium.page.MenuPage;
import io.appium.java_client.MobileBy;

public class FormularioTeste {
	
	private MenuPage menu = new MenuPage();
	private FormularioPage page = new FormularioPage();
	
	@Before
	public void inicializarAppium() throws MalformedURLException {	
		menu.acessarFormulario();		
	}
	
	@After
	public void tearDown() {
		DriverFactory.killDriver();
	}	

	@Test
	public void devePreencherCampoTexto() throws MalformedURLException {	
		page.escreverNome("Manuella");	
		assertEquals("Manuella", page.obterNome());		
	}

	
	@Test
	public void deveInteragirCombo() throws MalformedURLException {			
		page.selecionarCombo("Nintendo Switch");			
		Assert.assertEquals("Nintendo Switch", page.obterValorCombo());
	}
	
	@Test
	public void deveInteragirSwitchCheckBox() throws MalformedURLException {
		// Selecionar formulário => trabalhando com xpath
		//driver.findElement(By.xpath("//*[@text='Formulário']")).click();  // * para selecionar qualquer elemento que possua o texto "Formulário"
		
		
		Assert.assertFalse(page.isCheckMarcado());
		Assert.assertTrue(page.isSwitchMarcado());			
	
		
		page.clicarCheck();
		page.clicarSwitch();			
		
		Assert.assertTrue(page.isCheckMarcado());
		Assert.assertFalse(page.isSwitchMarcado());				
	}
	
	@Test
	public void deveRealizarCadastro() throws MalformedURLException {	
		page.escreverNome("MANUELLA");
		page.clicarCheck();
		page.clicarSwitch();
		page.selecionarCombo("Nintendo Switch");		
		page.salvar();		
		
		Assert.assertEquals("Nome: MANUELLA", page.obterNomeCadastrado());
		Assert.assertEquals("Console: switch", page.obterConsoleCadastrado());
		Assert.assertTrue(page.obterCheckCadastrado().endsWith("OFF"));
		Assert.assertTrue(page.obterSwitchCadastrado().endsWith("Marcado"));
	}	
}
