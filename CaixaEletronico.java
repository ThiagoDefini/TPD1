import java.rmi.Naming;
import java.util.Scanner;

//UnicastRemoteObject permite que a implementacao da classe possa ser estabelecida como um servico remoto
public class CaixaEletronico{

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        try{
            Administracao adm = (Administracao) Naming.lookup("rmi://localhost:1099/AdmService");

            int cpf = 0;

            do{
                cpf = 0;
                System.out.println("Digite o CPF da conta: ");
                cpf = scan.nextInt();

                int menu;
                do{
                    System.out.println("1 - Saque");
                    System.out.println("2 - Deposito");
                    System.out.println("3 - Saldo");
                    System.out.println("4 - Sair");
                    menu = scan.nextInt();

                    switch(menu){
                        case 1:
                            Double saque;
                            System.out.print("Digite o valor do saque");
                            saque = scan.nextDouble();
                            System.out.println("Saldo depois do saque: " + adm.saque(cpf, saque));
                        break;
                        case 2:
                            Double deposito;
                            System.out.print("Digite o valor do deposito: ");
                            deposito = scan.nextDouble();
                            System.out.println("Saldo depois do deposito: " + adm.deposito(cpf, deposito));
                        break;
                        case 3:
                            System.out.println(adm.saldo(cpf));
                        break;
                        case 4:
                            System.out.println("Fechando o Programa.");
                        break;
                        default:
                            System.out.println("Digite uma opção válida!");
                    }

                }while(menu != 4);

            }while(adm.autenticarConta(cpf));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
