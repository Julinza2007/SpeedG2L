@echo off
setlocal EnableExtensions DisableDelayedExpansion

REM ============================================================
REM   INFORME DEL PROYECTO SPEEDG2L
REM   El .bat debe estar en la carpeta raiz del proyecto.
REM ============================================================

REM Nos aseguramos de trabajar SIEMPRE desde donde esta este .bat
cd /d "%~dp0"

set "SALIDA=todo_el_proyecto.txt"

echo Generando informe de SpeedG2L...
echo Carpeta: %CD%
echo.

REM Si ya existia un informe anterior, lo eliminamos
if exist "%SALIDA%" del /q "%SALIDA%"

(
echo ============================================================
echo                 INFORME DEL PROYECTO SPEEDG2L
echo ============================================================
echo.
echo Fecha: %date%
echo Hora:  %time%
echo Ruta:  %CD%
echo.
echo ============================================================
echo 1. ESTRUCTURA DEL PROYECTO
echo ============================================================
echo.

tree /F /A

echo.
echo ============================================================
echo 2. CODIGO FUENTE JAVA
echo ============================================================
echo.
) > "%SALIDA%"

REM ------------------------------------------------------------
REM CORE - codigo fuente
REM ------------------------------------------------------------
for /r "core\src\main\java" %%F in (*.java) do (
    >>"%SALIDA%" echo.
    >>"%SALIDA%" echo ============================================================
    >>"%SALIDA%" echo ARCHIVO: %%F
    >>"%SALIDA%" echo ============================================================
    >>"%SALIDA%" echo.
    type "%%F" >> "%SALIDA%"
    >>"%SALIDA%" echo.
)

REM ------------------------------------------------------------
REM LWJGL3 - codigo fuente
REM ------------------------------------------------------------
for /r "lwjgl3\src\main\java" %%F in (*.java) do (
    >>"%SALIDA%" echo.
    >>"%SALIDA%" echo ============================================================
    >>"%SALIDA%" echo ARCHIVO: %%F
    >>"%SALIDA%" echo ============================================================
    >>"%SALIDA%" echo.
    type "%%F" >> "%SALIDA%"
    >>"%SALIDA%" echo.
)

REM ------------------------------------------------------------
REM CONFIGURACION DEL PROYECTO
REM ------------------------------------------------------------
(
echo.
echo ============================================================
echo 3. ARCHIVOS DE CONFIGURACION
echo ============================================================
echo.
) >> "%SALIDA%"

for %%F in (
    "build.gradle"
    "settings.gradle"
    "gradle.properties"
    "core\build.gradle"
    "lwjgl3\build.gradle"
    "README.md"
    "CHANGELOG.md"
) do (
    if exist "%%~F" (
        >>"%SALIDA%" echo.
        >>"%SALIDA%" echo ============================================================
        >>"%SALIDA%" echo ARCHIVO: %%~F
        >>"%SALIDA%" echo ============================================================
        >>"%SALIDA%" echo.
        type "%%~F" >> "%SALIDA%"
        >>"%SALIDA%" echo.
    )
)

REM ------------------------------------------------------------
REM ARCHIVOS DE ASSETS
REM ------------------------------------------------------------
(
echo.
echo ============================================================
echo 4. ASSETS
echo ============================================================
echo.
echo Lista de archivos dentro de assets:
echo.
) >> "%SALIDA%"

if exist "assets" (
    for /r "assets" %%F in (*) do (
        >>"%SALIDA%" echo %%F
    )
)

REM ------------------------------------------------------------
REM INFORMACION DE LAS CLASES JAVA
REM ------------------------------------------------------------
(
echo.
echo ============================================================
echo 5. CLASES JAVA ENCONTRADAS
echo ============================================================
echo.
) >> "%SALIDA%"

for /r "core\src\main\java" %%F in (*.java) do (
    >>"%SALIDA%" echo %%F
)

for /r "lwjgl3\src\main\java" %%F in (*.java) do (
    >>"%SALIDA%" echo %%F
)

REM ------------------------------------------------------------
REM IMPORTS
REM ------------------------------------------------------------
(
echo.
echo ============================================================
echo 6. IMPORTS UTILIZADOS
echo ============================================================
echo.
) >> "%SALIDA%"

for /r "core\src\main\java" %%F in (*.java) do (
    >>"%SALIDA%" echo.
    >>"%SALIDA%" echo [%%F]
    findstr /b /c:"import " "%%F" >> "%SALIDA%" 2>nul
)

for /r "lwjgl3\src\main\java" %%F in (*.java) do (
    >>"%SALIDA%" echo.
    >>"%SALIDA%" echo [%%F]
    findstr /b /c:"import " "%%F" >> "%SALIDA%" 2>nul
)

REM ------------------------------------------------------------
REM FIN
REM ------------------------------------------------------------
(
echo.
echo ============================================================
echo                    FIN DEL INFORME
echo ============================================================
) >> "%SALIDA%"

echo.
echo ============================================================
echo INFORME GENERADO CORRECTAMENTE
echo ============================================================
echo.
echo Archivo:
echo %CD%\%SALIDA%
echo.
echo El codigo fuente esta en la seccion:
echo "2. CODIGO FUENTE JAVA"
echo.
pause
