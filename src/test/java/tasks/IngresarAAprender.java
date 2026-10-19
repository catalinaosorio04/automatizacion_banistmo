
package tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.targets.Target;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.BanistmoPage;

import java.time.Duration;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class IngresarAAprender implements Task {

    private static final Target ENTENDIDO =
            Target.the("Aviso de privacidad")
                    .located(By.xpath("//button[normalize-space()='Entendido']"));

    private static final Target CERRAR_PROMOCION =
            Target.the("Cerrar promoción")
                    .located(By.cssSelector(
                            "[data-testid='or-promotional-modal__close']"));

    @Override
    public <T extends Actor> void performAs(T actor) {

        WebDriver driver = BrowseTheWeb.as(actor).getDriver();
        WebDriverWait espera = new WebDriverWait(
                driver, Duration.ofSeconds(15));

        cerrarSiAparece(actor, driver, ENTENDIDO, 5);
        cerrarSiAparece(actor, driver, CERRAR_PROMOCION, 10);

        espera.until(ExpectedConditions.invisibilityOfElementLocated(
                By.cssSelector(".cbc-promotional-modal__content")));

        WebElement enlace = espera.until(d ->
                BanistmoPage.APRENDER_ES_FACIL.resolveAllFor(actor)
                        .stream()
                        .filter(WebElement::isDisplayed)
                        .filter(WebElement::isEnabled)
                        .findFirst()
                        .orElse(null));

        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript(
                "arguments[0].scrollIntoView({block:'center'});", enlace);

        espera.until(d -> Boolean.TRUE.equals(js.executeScript("""
                const rect = arguments[0].getBoundingClientRect();
                const x = rect.left + rect.width / 2;
                const y = rect.top + rect.height / 2;

                if (x < 0 || x >= innerWidth ||
                    y < 0 || y >= innerHeight) return false;

                const elemento = document.elementFromPoint(x, y);

                return elemento === arguments[0] ||
                       arguments[0].contains(elemento);
                """, enlace)));

        enlace.click();

        espera.until(ExpectedConditions.urlContains(
                "/personas/aprender-es-facil"));
    }

    private void cerrarSiAparece(
            Actor actor, WebDriver driver, Target boton, int segundos) {

        WebDriverWait espera = new WebDriverWait(
                driver, Duration.ofSeconds(segundos));

        try {
            espera.until(d -> boton.resolveAllFor(actor).stream()
                    .anyMatch(e -> e.isDisplayed() && e.isEnabled()));

            actor.attemptsTo(Click.on(boton));

        } catch (TimeoutException e) {
            // La ventana emergente es opcional.
        }
    }

    public static IngresarAAprender esFacil() {
        return instrumented(IngresarAAprender.class);
    }
}
