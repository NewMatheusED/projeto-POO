@echo off
echo 🚀 Iniciando projeto em modo desenvolvimento...
echo 📁 Diretório: %CD%
echo 🔄 Hot reload ativado com DevTools
echo.

echo ⚡ Compilando projeto...
call mvnw.cmd clean compile

echo.
echo 🌐 Iniciando aplicação...
echo 📍 URL: http://localhost:8080
echo 🔥 Hot reload: Ativo (salve os arquivos para recarregar)
echo.

call mvnw.cmd spring-boot:run

pause
