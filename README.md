# Automatización Banistmo – Validación de documento FATCA y CRS

## Descripción

Proyecto de automatización de pruebas desarrollado para validar la navegación y consulta del documento **PJ-Autocertificación unificado - FATCA y CRS** en el sitio web de Banistmo.

La automatización utiliza el patrón de diseño Screenplay junto con Serenity BDD, Selenium WebDriver y Cucumber.

## Tecnologías utilizadas

- Java 21
- Gradle 8.14.3
- Selenium WebDriver 4.33.0
- Serenity BDD 4.2.34
- Cucumber y Gherkin
- Apache PDFBox 3.0.5
- JUnit 4

## Escenario automatizado

1. Ingresar al sitio web de Banistmo.
2. Seleccionar la opción **Aprender es fácil**.
3. Acceder al apartado **Legales**.
4. Seleccionar **Autocertificación fiscal FATCA & CRS**.
5. Abrir el documento **PJ-Autocertificación unificado - Fatca y CRS.pdf**.
6. Validar el documento mediante la URL y el contenido extraído con Apache PDFBox.

## Arquitectura del proyecto

El proyecto implementa el patrón Screenplay con la siguiente organización:

- `pages`: localizadores de los elementos web.
- `tasks`: acciones de navegación.
- `interactions`: interacciones específicas con el navegador.
- `questions`: validaciones del documento.
- `stepdefinitions`: implementación de los pasos Gherkin.
- `hooks`: configuración de los actores.
- `runners`: ejecución de Cucumber con Serenity.
- `utils`: utilidades para lectura del PDF.
- `features`: definición del escenario de prueba.

## Requisitos

- Java JDK 21
- Google Chrome
- Conexión a internet

El proyecto incluye Gradle Wrapper, por lo que no es necesario instalar Gradle manualmente.

## Ejecución

En Windows, abrir una terminal en la raíz del proyecto y ejecutar:

```powershell
.\gradlew.bat clean test aggregate
```

Este comando ejecuta la automatización y genera el reporte de Serenity.

## Reporte de resultados

Después de ejecutar las pruebas, abrir:

`target/site/serenity/index.html`

El reporte permite consultar los escenarios ejecutados, sus resultados y las evidencias disponibles.



