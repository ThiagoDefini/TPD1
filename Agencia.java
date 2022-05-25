import java.rmi.Naming;
import java.util.Scanner;

//UnicastRemoteObject permite que a implementacao da classe possa ser estabelecida como um servico remoto
public class Agencia{

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        try{
            Administracao adm = (Administracao) Naming.lookup("rmi://localhost:1099/AdmService");

            int menu;
            do{
                System.out.println("1 - Abertura de Conta");
                System.out.println("2 - Autenticação de conta");
                System.out.println("3 - Fechamento de Conta");
                System.out.println("4 - Saque");
                System.out.println("5 - Deposito");
                System.out.println("6 - Saldo");
                System.out.println("7 - Sair");
                menu = scan.nextInt();
                int cpf = 0;
                switch(menu){
                    case 1:
                    //Abertura de Conta
                        String nome;
                        System.out.println("Digite o nome: ");
                        nome = scan.next();
                        System.out.println("Digite o CPF da conta: ");
                        cpf = scan.nextInt();
                        
                        //adm.abrirConta(nome, cpf);
                    break;
                    case 2:
                    //Autenticação de Conta
                        System.out.println("Digite o CPF da conta: ");
                        cpf = scan.nextInt();


                    break;
                    case 3:
                    //Fechamento de Conta
                        System.out.println("Digite o CPF da conta: ");
                        cpf = scan.nextInt();

                        if(adm.fecharConta(cpf)){
                            System.out.println("A conta foi fechada com sucesso.");
                        }else{
                            System.out.println("Ouve algum erro ao fechar a conta.");
                        }
                    break;
                    case 4:
                    //Saque
                        Double saque;
                        System.out.println("Digite o CPF: ");
                        cpf = scan.nextInt();
                        System.out.print("Digite o valor do saque");
                        saque = scan.nextDouble();
                        //System.out.println("Saldo depois do saque: " + adm.saque(cpf, saque));
                    break;
                    case 5:
                    //Deposito
                        Double deposito;
                        System.out.println("Digite o CPF da conta: ");
                        cpf = scan.nextInt();
                        System.out.print("Digite o valor do deposito: ");
                        deposito = scan.nextDouble();
                        //System.out.println("Saldo depois do deposito: " + adm.deposito(cpf, deposito));
                    break;
                    case 6:
                    //Saldo
                        System.out.println("Digite o CPF da conta: ");
                        cpf = scan.nextInt();
                        System.out.println(adm.saldo(cpf));
                    break;
                    case 7:
                    //Fechar o programa
                        System.out.println("Fechando o Programa.");
                    break;
                    default:
                        System.out.println("Digite uma opção válida!");
                }

            }while(menu != 7);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
