#!/bin/bash

# Script de deploy para Render
# Uso: ./scripts/deploy-render.sh

set -e

echo "🚀 Iniciando deploy para Render..."

# Verificar se o Render CLI está instalado
if ! command -v render &> /dev/null; then
    echo "❌ Render CLI não está instalado."
    echo "📦 Instale com: npm install -g @render/cli"
    echo "🔑 Faça login com: render login"
    exit 1
fi

# Verificar se está logado no Render
if ! render whoami &> /dev/null; then
    echo "❌ Não está logado no Render."
    echo "🔑 Faça login com: render login"
    exit 1
fi

echo "✅ Render CLI configurado corretamente!"

# Build local para verificar se está tudo funcionando
echo "🔨 Fazendo build local para verificação..."
mvn clean package -DskipTests

if [ $? -eq 0 ]; then
    echo "✅ Build local bem-sucedido!"
else
    echo "❌ Build local falhou. Corrija os erros antes de fazer deploy."
    exit 1
fi

# Deploy para Render
echo "📦 Fazendo deploy para Render..."
render deploy

if [ $? -eq 0 ]; then
    echo "✅ Deploy para Render concluído com sucesso!"
    echo "🌐 Sua API está disponível em: https://analise-senadores-api.onrender.com"
else
    echo "❌ Deploy falhou. Verifique os logs no Render Dashboard."
    exit 1
fi

echo "🎉 Deploy concluído com sucesso!"
