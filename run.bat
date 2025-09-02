@echo off

REM Muda para o diretório onde o script está
cd /d "%~dp0"

REM Lê o arquivo .env e define as variáveis de ambiente
FOR /F "tokens=1,* delims==" %%A IN (.env) DO (
    echo %%A=%%B
    set %%A=%%B
)

REM Define o profile de desenvolvimento
set SPRING_PROFILES_ACTIVE=dev

REM Executa a aplicação Spring Boot usando Maven
echo Iniciando a aplicação Spring Boot...
call mvnw.cmd spring-boot:run