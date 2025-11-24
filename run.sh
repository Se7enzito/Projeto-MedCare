#!/bin/bash

echo "====================================="
echo "      Iniciando MedCare (JavaFX)"
echo "====================================="

# Local onde o Maven guarda o JavaFX
JFX_DIR="$HOME/.m2/repository/org/openjfx"

# Captura TODOS os jars necessários automaticamente
MODULE_PATH=$(find "$JFX_DIR" -name "*.jar" | tr '\n' ':')

if [ -z "$MODULE_PATH" ]; then
    echo "Erro: Nenhum JAR do JavaFX encontrado no Maven!"
    echo "Execute: mvn clean install"
    exit 1
fi

echo "→ Jars detectados:"
echo "$MODULE_PATH"
echo

VM_OPTS="--module-path $MODULE_PATH --add-modules javafx.controls,javafx.fxml"

echo "Compilando o projeto..."
mvn -q clean package

if [ $? -ne 0 ]; then
  echo "Erro na compilação!"
  exit 1
fi

echo
echo "Executando aplicação..."
echo

# Usando o plugin JavaFX
mvn javafx:run -q -Dexec.args="$VM_OPTS"

echo
echo "====================================="
echo "          MedCare encerrado"
echo "====================================="