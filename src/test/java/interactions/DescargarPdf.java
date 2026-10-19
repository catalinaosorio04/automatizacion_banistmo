
package interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.FatcaPage;

import java.time.Duration;
import java.util.Set;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class DescargarPdf implements Interaction {

    @Override
    public <T extends Actor> void performAs(T actor) {

        WebDriver driver = BrowseTheWeb.as(actor).getDriver();
        WebDriverWait espera = new WebDriverWait(
                driver, Duration.ofSeconds(15));

        Set<String> ventanasAnteriores = driver.getWindowHandles();

        WebElement enlace = espera.until(d ->
                FatcaPage.DOCUMENTO_PJ_FATCA.resolveAllFor(actor)
                        .stream()
                        .filter(WebElement::isDisplayed)
                        .filter(WebElement::isEnabled)
                        .findFirst()
                        .orElse(null));

        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                enlace);

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

        espera.until(d ->
                d.getWindowHandles().size() > ventanasAnteriores.size());

        for (String ventana : driver.getWindowHandles()) {
            if (!ventanasAnteriores.contains(ventana)) {
                driver.switchTo().window(ventana);
                break;
            }
        }
    }

    public static DescargarPdf documentoFatca() {

        return instrumented(DescargarPdf.class);
    }
}
