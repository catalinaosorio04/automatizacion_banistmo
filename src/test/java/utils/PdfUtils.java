
package utils;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.IOException;
import java.net.URI;

public final class PdfUtils {

    private PdfUtils() {
    }

    public static String obtenerTexto(String url) throws IOException {

        byte[] archivo;

        try (var conexion = URI.create(url).toURL().openStream()) {
            archivo = conexion.readAllBytes();
        } catch (IllegalArgumentException e) {
            throw new IOException("URL del PDF inválida", e);
        }

        try (PDDocument documento = Loader.loadPDF(archivo)) {
            return new PDFTextStripper().getText(documento);
        }
    }
}
