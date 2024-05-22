import java.util.Scanner;
public class TraducaoJava {
public static void main(String[] args) {
 Scanner scanner = new Scanner(System.in);
System.out.print("___Bem Vindo a Calculadora____\n");
float valor;
valor=0;
int contador;
contador=0;
boolean rodando;
rodando=true;
String msgOperacaoInvalida;
msgOperacaoInvalida = "Operacao Invalida!\n";
while(rodando){
/* ____Valor Atual____ */
System.out.print("Valor Atual: ");
System.out.print(valor);
/* ____Escolhendo um numero____ */
System.out.print("\nEscolha um numero: ");
int entrada;
entrada = Integer.parseInt(scanner.nextLine());;
/* ____Escolhendo uma Operacao____ */
System.out.print("\nEscolha uma operacao: \n");
System.out.print("Operacoes Disponiveis: \n(0 -> Cancelar) \n(1 -> +)  \n(2 -> -) \n(3 -> *)  \n(4 -> /) \n");
int operacao;
operacao = Integer.parseInt(scanner.nextLine());;
/* ____Realizando Operação_____ */
switch(operacao){
case 0:
rodando=false;
break;
case 1:
valor=valor+entrada;
break;
case 2:
valor=valor-entrada;
break;
case 3:
valor=valor*entrada;
break;
case 4:
valor=valor/entrada;
break;
default:
System.out.print(msgOperacaoInvalida);
contador=contador-1;
break;

}
if(operacao!=0){
contador=contador+1;

}

}
System.out.print("__Voce fez ");
System.out.print(contador);
System.out.print(" operacoes__\n");
if(contador==0){
System.out.print("\nPra que tu abriu a calculadora? kk\n");

}
if(contador<=3){
System.out.print("Dava pra ter feito de cabeca\n");

}
if(contador==4){
System.out.print("Ta bao\n");

}
if(contador>5){
System.out.print("Coroi quanta conta\n");

}
else{
System.out.print("Isso aqui e so um teste de else msm :)");

}
}
}