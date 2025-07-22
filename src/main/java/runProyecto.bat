@echo off
echo Compilando archivos Java...

cd src\main\java

REM Crea la carpeta de clases si no existe
mkdir ..\..\..\bin 2>nul

REM Compila todos los archivos y los deja en bin
javac -d ..\..\..\bin com\AutosQA\*.java

if %errorlevel% neq 0 (
    echo ❌ Error de compilación.
) else (
    echo ✅ Compilación exitosa.
    echo Ejecutando clase principal...
    cd ..\..\..\bin
    java com.AutosQA.MainApp
)

pause
