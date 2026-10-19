
package questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import utils.PdfUtils;

import java.io.IOException;
import java.text.Normalizer;
import java.util.Locale;

public class DocumentoCorrecto implements Question<Boolean> {

    @Override
    public Boolean answeredBy(Actor actor) {

        String url = BrowseTheWeb.as(actor).getDriver().getCurrentUrl();

        if (!url.toLowerCase(Locale.ROOT)
                .contains("pj-autocertificacion-unificado-fatca-y-crs.pdf")) {
            return false;
        }

        try {
            String contenido = Normalizer.normalize(
                            PdfUtils.obtenerTexto(url), Normalizer.Form.NFD)
                    .replaceAll("\\p{M}", "")
                    .toUpperCase(Locale.ROOT);

            return contenido.contains("AUTO-CERTIFICACION FISCAL")
                    && contenido.contains("PERSONA JURIDICA");

        } catch (IOException e) {
            throw new IllegalStateException(
                    "No se pudo leer el documento PDF: " + url, e);
        }
    }

    public static DocumentoCorrecto delPdf() {
        return new DocumentoCorrecto();
    }
}
