@echo off

REM Muda para o diretório onde o script está
cd /d "%~dp0"

REM Lê o arquivo .env e define as variáveis de ambiente
FOR /F "tokens=1,* delims==" %%A IN (.env) DO (
    echo %%A=%%B
    set %%A=%%B
)

REM Executa a aplicação Spring Boot usando Maven
echo Iniciando a aplicação Spring Boot...
call mvnw.cmd spring-boot:run

REM Alternativa: se preferir usar java diretamente (requer JAR compilado)
REM if exist "target\demo-0.0.1-SNAPSHOT.jar" (
REM     java -jar target\demo-0.0.1-SNAPSHOT.jar
REM ) else (
REM     echo JAR não encontrado. Execute 'mvnw.cmd clean package' primeiro.
REM     pause
REM )