#include <stdio.h>;
#include <string.h>;
int main() {
printf("__Bem Vindo a Fatorial_\n");
int rodando;
rodando=1;
while(rodando){
int start;
int fat;
fat=1;
printf("\nEscolha um numero: ");
int n;
scanf("%d",&n);
for(start=1;start<=n;start++){
fat=fat*start;

}
printf("o resultado do Fatorial e: ");
printf("%d",fat);
printf("\nDeseja parar? Digite 0 para sair e 1 para continuar");
int dec;
scanf("%d",&dec);
if(dec==0){
rodando=0;

}

}

}
