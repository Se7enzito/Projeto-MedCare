@echo off
TITLE MedCare (JavaFX)

echo =====================================
echo       Iniciando MedCare (JavaFX)
echo =====================================

REM Diretório onde o Maven armazena o JavaFX
set "JFX_DIR=%USERPROFILE%\.m2\repository\org\openjfx"

REM Captura todos os jars necessários
for /f "delims=" %%i in ('dir /s /b "%JFX_DIR%\*.jar"') do (
    set "MODULE_PATH=!MODULE_PATH!;%%i"
)

REM Habilita delayed expansion
setlocal enabledelayedexpansion

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

call mvn javafx:run -q -Dexec.args="%VM_OPTS%"

echo.
echo =====================================
echo           MedCare encerrado
echo =====================================

pause