echo "======================================"
echo "  Iniciando Sammy's Seashore Supplies"
echo "======================================"
echo

javac *.java
if [ $? -ne 0 ]; then
  echo "Erro na compilacao!"
  exit 1
fi

echo
echo Executando codigo de aluguel para equipamentos!!

java SammyAppGUI

echo
echo "Limpando arquivos .class..."
rm -f *.class

echo
echo "Execucao finalizada."
