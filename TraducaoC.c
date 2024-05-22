#include <stdio.h>;
int main() {
int x;
printf("Escolha uma Opção");
scanf("%d",&x);
switch(x){
case 1:
printf("Opção 1\n");
break;
case 2:
printf("Opção 2\n");
break;
case 3:
printf("Opção 3\n");
break;
case 4:
printf("Opção 4\n");
break;
default:
printf("Opção 0\n");
x=0;
break;

}
int i;
for(i=0;i<x;i++){
printf("Valor de i: ");
printf("%d",i);
printf("\n");
if(i==2){
printf("%d",3);
printf("\n");

}
if(i>2){
printf("maior que 2\n");

}
if(i<2){
printf("menor que 2\n");

}

}

}
