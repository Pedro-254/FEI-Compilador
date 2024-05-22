import java.util.Scanner;
public class TraducaoJava {
public static void main(String[] args) {
 Scanner scanner = new Scanner(System.in);
System.out.print("__Bem Vindo a Fatorial_\n");
boolean rodando;
rodando=true;
while(rodando){
int start;
int fat;
fat=1;
System.out.print("\nEscolha um numero: ");
int n;
n = Integer.parseInt(scanner.nextLine());;
for(start=1;start<=n;start++){
fat=fat*start;

}
System.out.print("o resultado do Fatorial e: ");
System.out.print(fat);
System.out.print("\nDeseja parar? Digite 0 para sair e 1 para continuar");
int dec;
dec = Integer.parseInt(scanner.nextLine());;
if(dec==0){
rodando=false;

}

}
}
}