@echo off
setlocal enabledelayedexpansion
TITLE MedCare (JavaFX)

echo =====================================
echo       Iniciando MedCare (JavaFX)
echo =====================================

REM Diretório onde o Maven armazena o JavaFX
set "JFX_DIR=%USERPROFILE%\.m2\repository\org\openjfx"

REM Limpa variável
set "MODULE_PATH="

REM Captura apenas JARs do JavaFX (ignora plugins)
for /f "delims=" %%i in ('dir /s /b "%JFX_DIR%\javafx-*.jar"') do (
    set "MODULE_PATH=!MODULE_PATH!;%%i"
)

if "%MODULE_PATH%"=="" (
    echo Erro: Nenhum JAR do JavaFX encontrado no Maven!
    echo Execute: mvn clean install
    exit /b 1
)

REM Remove primeiro ponto e vírgula
set "MODULE_PATH=%MODULE_PATH:~1%"

echo Jars detectados:
echo %MODULE_PATH%
echo.

echo Compilando projeto...
call mvn clean install -q

if %ERRORLEVEL% neq 0 (
    echo.
    echo Erro na compilacao!
    exit /b 1
)

echo.
echo Executando aplicacao...
echo.

call mvn javafx:run -q

echo.
echo =====================================
echo           MedCare encerrado
echo =====================================

pause