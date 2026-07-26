@echo off
setlocal enabledelayedexpansion

REM ============================================
REM EXPORTAR TODO EL CODIGO JAVA A UN TXT
REM ============================================

REM Nombre del archivo de salida
set OUTPUT=todo_el_codigo.txt

REM Borra el archivo anterior si existe
if exist %OUTPUT% del %OUTPUT%

echo =============================== >> %OUTPUT%
echo   CODIGO COMPLETO DEL PROYECTO >> %OUTPUT%
echo =============================== >> %OUTPUT%
echo. >> %OUTPUT%

REM Busca todos los .java de forma recursiva
for /r %%f in (*.java) do (
    
    echo ====================================== >> %OUTPUT%
    echo ARCHIVO: %%f >> %OUTPUT%
    echo ====================================== >> %OUTPUT%
    
    type "%%f" >> %OUTPUT%
    
    echo. >> %OUTPUT%
    echo. >> %OUTPUT%
)

echo.
echo Listo. Se genero el archivo: %OUTPUT%
pause