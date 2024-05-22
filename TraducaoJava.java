import java.util.Scanner;
public class TraducaoJava {
public static void main(String[] args) {
int x;
System.out.println("Escolha uma Opção");
Scanner scanner = new Scanner(System.in);
x = Integer.parseInt(scanner.nextLine());;
switch(x){
case 1:
System.out.println("Opção 1\n");
break;
case 2:
System.out.println("Opção 2\n");
break;
case 3:
System.out.println("Opção 3\n");
break;
case 4:
System.out.println("Opção 4\n");
break;
default:
System.out.println("Opção 0\n");
x=0;
break;

}
int i;
for(i=0;i<x;i++){
System.out.println("Valor de i: ");
System.out.println(i);
System.out.println("\n");
if(i==2){
System.out.println("3");
System.out.println("\n");

}
if(i>2){
System.out.println("maior que 2\n");

}
if(i<2){
System.out.println("menor que 2\n");

}

}
}
}