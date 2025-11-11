@echo off
title Sammy's Seashore Supplies
echo ======================================
echo   Iniciando Sammy's Seashore Supplies
echo ======================================
echo.

javac *.java
if %errorlevel% neq 0 (
    echo Erro na compilação!
    pause
    exit /b
)

echo.
echo Executando codigo de aluguel para equipamentos!!
java SammyAppGUI

echo.
echo Limpando os arquivos...
del /Q *.class

echo.
echo Execucao finalizada!
pause
