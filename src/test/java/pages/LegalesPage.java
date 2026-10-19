
package pages;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class LegalesPage {

    public static final Target DESCUBRE_MAS_LEGALES =
            Target.the("Botón Descubre más de Legales")
                    .located(By.xpath(
                            "//div[@data-testid='ml-card']" +
                                    "[.//*[normalize-space(text())='Legales']]" +
                                    "//button[@data-testid='ml-card-actions-button']"
                    ));
}
