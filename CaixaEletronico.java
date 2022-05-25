import java.rmi.Naming;
import java.util.Scanner;

//UnicastRemoteObject permite que a implementacao da classe possa ser estabelecida como um servico remoto
public class CaixaEletronico{

    private static String cod = "100";
    private static int count;
    
    public static String gerarCodigo(){
        String codCount = cod + count;
        count++;
        return codCount;
    }

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        try{
            Administracao adm = (Administracao) Naming.lookup("rmi://localhost:1099/AdmService");

            int cpf = 0;
            int senha = 0;
            do{
                System.out.println("Digite o CPF da conta: ");
                cpf = scan.nextInt();
                System.out.println("Digite a senha: ");
                senha = scan.nextInt();
                if(!adm.autenticarConta(cpf, senha)){
                    System.out.println("Usuário ou senha incorretos.");
                }
            }while(!adm.autenticarConta(cpf,senha));
            int menu;
                do{
                    System.out.println("1 - Saque");
                    System.out.println("2 - Saque com erro");
                    System.out.println("2 - Deposito");
                    System.out.println("3 - Saldo");
                    System.out.println("4 - Sair");
                    System.out.print("Digite: ");
                    menu = scan.nextInt();

                    switch(menu){
                        case 1:
                            String codSaque = gerarCodigo(); 
                            boolean aux1 = false;
                            do{ 
                                int menuAux;
                                Double saque;
                                System.out.print("Digite o valor do saque: ");
                                saque = scan.nextDouble();
                                if(saque > adm.saldo(cpf) || saque <= 0){
                                    System.out.println("Digite um valor válido.");
                                    aux1 = false;
                                }else{
                                    if(adm.saque(cpf, saque, codSaque)){
                                        System.out.println("Saque realizado com sucesso.");
                                        aux1 = true;
                                    }else{
                                        System.out.println("Ocorreu algum erro.");
                                        System.out.println("Deseja realizar a operação novamente? 1=Sim|2=Não");
                                        menuAux = scan.nextInt();
                                        if(menuAux == 2){
                                            aux1 = false;
                                        }else{
                                            aux1 =true;
                                        }   
                                    }
                                    System.out.println("Saldo depois do saque: " + adm.saldo(cpf));
                                }
                            }while(aux1 != true);
                        break;
                        case 2:
                            String codDeposito = gerarCodigo(); 
                            boolean aux2 = false;
                            do{ 
                                int menuAux;
                                Double deposito;
                                System.out.print("Digite o valor do deposito: ");
                                deposito = scan.nextDouble();
                                if(deposito <= 0){
                                    System.out.println("Digite um valor válido.");
                                    aux2 = false;
                                }else{
                                    if(adm.deposito(cpf, deposito, codDeposito)){
                                        System.out.println("Deposito realizado com sucesso.");
                                        aux2 = true;
                                    }else{
                                        System.out.println("Ocorreu algum erro.");
                                        System.out.println("Deseja realizar a operação novamente? 1=Sim|2=Não");
                                        menuAux = scan.nextInt();
                                        if(menuAux == 2){
                                            aux2 = false;
                                        }else{
                                            aux2 =true;
                                        }   
                                    }
                                    System.out.println("Saldo: " + adm.saldo(cpf));
                                }
                            }while(aux2 != true);

                        break;
                        case 3:
                            System.out.println("Saldo: "+adm.saldo(cpf));
                        break;
                        case 4:
                            System.out.println("Fechando o Programa.");
                        break;
                        default:
                            System.out.println("Digite uma opção válida!");
                    }

                }while(menu != 4);

            
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
