
package pages;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class BanistmoPage {

    public static final Target APRENDER_ES_FACIL =
            Target.the("Opción Aprender es fácil del footer")
                    .located(By.cssSelector(
                            "a[href='/personas/aprender-es-facil']"
                    ));
}
