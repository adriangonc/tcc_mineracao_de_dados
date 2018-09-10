package br.com.mwork.test;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {
	@Test
	public void testarLogin() {
		System.setProperty("webdriver.chrome.driver", "C:\\AMBIENTE JAVA\\chromedriver_win32\\chromedriver.exe");
		String email = "adriangonc@hotmail.com";
		String senha = "asdf";

		for (int i = 0; i < 5; i++) {
			
			WebDriver navegador = new ChromeDriver();
			navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			
			navegador.get("http://localhost:8081/mwork-0.1/login.xhtml");

			navegador.findElement(By.id("j_username")).sendKeys(email);
			navegador.findElement(By.id("j_password")).sendKeys(senha);

			navegador.findElement(By.name("j_idt12")).click();
			String usuarioLogado = navegador.findElement(By.id("emailUsuario")).getText();

			assertEquals(email, usuarioLogado);

			if (usuarioLogado.equals(email)) {
				System.out.println("Teste bem sucedido");
			} else {
				System.out.println("Teste mal sucedido");
			}

			navegador.close();

		}

	}
}
