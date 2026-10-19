
# language: es

Característica: Validación de documento FATCA y CRS en Banistmo
  Como usuario de Banistmo
  Quiero consultar el documento de autocertificación FATCA y CRS
  Para verificar que el PDF corresponde al documento solicitado

  @mitest
  Escenario: Validar documento de autocertificación FATCA y CRS
    Dado que el usuario ingresa a la página de Banistmo
    Cuando selecciona la opción Aprender es fácil
    Y accede al apartado Legales
    Y selecciona la opción FATCA y CRS
    Y abre el documento PJ-Autocertificación unificado- Fatca y CRS
    Entonces debe validar que el documento PDF sea el correcto
