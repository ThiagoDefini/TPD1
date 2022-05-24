import java.rmi.Naming;
import java.util.Scanner;

//UnicastRemoteObject permite que a implementacao da classe possa ser estabelecida como um servico remoto
public class CaixaEletronico{

    public static Double solicitaSaque (Conta c, Double saque) throws RemoteException{
        return ag.solicitaSaque(c, saque);
    }

    
    public static Double solicitaDeposito (Conta c, Double deposito) throws RemoteException{
        return ag.solicitaDeposito(c, deposito);
    }

    
    public static Double solicitaSaldo(Conta c) throws RemoteException{
        return c.getSaldo();
    }

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        
        Administracao ad = (Administracao) Naming.lookup("rmi://localhost:1099/CalcService");

        int menu;

        do{
            System.out.println("1 - Saque");
            System.out.println("2 - Deposito");
            System.out.println("3 - Saldo");
            System.out.println("4 - Sair");
            menu = scan.nextInt();

            switch(menu){
                case 1:

                break;
                case 2:

                break;

                case 3:

                break;

                case 4:

                break;
                default:
                    System.out.println("Digite uma opção válida!");
            }



        }while(menu != 4);


    }

}
