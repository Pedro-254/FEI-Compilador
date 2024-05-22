#include <stdio.h>;
#include <string.h>;
int main() {
printf("___Bem Vindo a Calculadora____\n");
float valor;
valor=0;
int contador;
contador=0;
int rodando;
rodando=1;
char msgOperacaoInvalida[100];
strcpy(msgOperacaoInvalida,"Operacao Invalida!\n");
while(rodando){
/* ____Valor Atual____ */
printf("Valor Atual: ");
printf("%f",valor);
/* ____Escolhendo um numero____ */
printf("\nEscolha um numero: ");
int entrada;
scanf("%d",&entrada);
/* ____Escolhendo uma Operacao____ */
printf("\nEscolha uma operacao: \n");
printf("Operacoes Disponiveis: \n(0 -> Cancelar) \n(1 -> +)  \n(2 -> -) \n(3 -> *)  \n(4 -> /) \n");
int operacao;
scanf("%d",&operacao);
/* ____Realizando Operação_____ */
switch(operacao){
case 0:
rodando=0;
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
printf("%s",msgOperacaoInvalida);
contador=contador-1;
break;

}
if(operacao!=0){
contador=contador+1;

}

}
printf("__Voce fez ");
printf("%d",contador);
printf(" operacoes__\n");
if(contador==0){
printf("\nPra que tu abriu a calculadora? kk\n");

}
if(contador<=3){
printf("Dava pra ter feito de cabeca\n");

}
if(contador==4){
printf("Ta bao\n");

}
if(contador>5){
printf("Coroi quanta conta\n");

}
else{
printf("Isso aqui e so um teste de else msm :)");

}

}
