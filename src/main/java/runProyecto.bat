@echo off
echo Compilando archivos Java...
javac com\AutosQA\*.java com\AutosQA\dao\*.java com\AutosQA\model\*.java com\AutosQA\db\*.java

if %errorlevel% neq 0 (
    echo ❌ Error de compilación.
    pause
    exit /b %errorlevel%
)

echo ✅ Compilación exitosa.
echo Ejecutando programa...

java com.AutosQA.Main

echo 🏁 Programa finalizado.
pause
