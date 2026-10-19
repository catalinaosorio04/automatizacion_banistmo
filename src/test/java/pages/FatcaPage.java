
package pages;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class FatcaPage {

    public static final Target DESCUBRE_MAS_FATCA =
            Target.the("Botón Descubre más de FATCA y CRS")
                    .located(By.xpath(
                            "//div[@data-testid='ml-card']" +
                                    "[.//*[normalize-space(text())='Autocertificación fiscal FATCA & CRS']]" +
                                    "//button[@data-testid='ml-card-actions-button']"
                    ));



    public static final Target DOCUMENTO_PJ_FATCA =
            Target.the("PDF de autocertificación unificado FATCA y CRS")
                    .located(By.cssSelector(
                            "a[aria-label*='PJ-Autocertificación unificado']"
                    ));

}
