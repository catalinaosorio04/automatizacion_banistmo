
package stepdefinitions;

import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;

import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.actors.OnStage;

import tasks.IngresarAAprender;
import tasks.IngresarALegales;
import tasks.IngresarAFatca;
import interactions.DescargarPdf;
import questions.DocumentoCorrecto;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.is;

public class BanistmoStepDefinitions {

    @Dado("que el usuario ingresa a la página de Banistmo")
    public void ingresarABanistmo() {
        OnStage.theActorCalled("Usuario").attemptsTo(
                Open.url("https://www.banistmo.com/personas")
        );
    }

    @Cuando("selecciona la opción Aprender es fácil")
    public void seleccionarAprenderEsFacil() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                IngresarAAprender.esFacil()
        );
    }

    @Cuando("accede al apartado Legales")
    public void accederALegales() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                IngresarALegales.desdeAprender()
        );
    }

    @Cuando("selecciona la opción FATCA y CRS")
    public void seleccionarFatcaYCRS() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                IngresarAFatca.desdeLegales()
        );
    }

    @Cuando("abre el documento PJ-Autocertificación unificado- Fatca y CRS")
    public void abrirDocumentoFatca() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                DescargarPdf.documentoFatca()
        );
    }

    @Entonces("debe validar que el documento PDF sea el correcto")
    public void validarDocumentoPdf() {
        OnStage.theActorInTheSpotlight().should(
                seeThat(DocumentoCorrecto.delPdf(), is(true))
        );
    }
}
