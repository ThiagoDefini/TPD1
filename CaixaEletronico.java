import java.rmi.Naming;
import java.util.Scanner;

//UnicastRemoteObject permite que a implementacao da classe possa ser estabelecida como um servico remoto
public class CaixaEletronico{

    private static String cod = "3";
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
                System.out.print("Digite o CPF da conta: ");
                cpf = scan.nextInt();
                System.out.print("Digite a senha: ");
                senha = scan.nextInt();
                if(!adm.autenticarConta(cpf, senha)){
                    System.out.println("Usuário ou senha incorretos.");
                }
            }while(!adm.autenticarConta(cpf,senha));
            int menu;
                do{
                    System.out.println("1 - Saque");
                    System.out.println("2 - Deposito");
                    System.out.println("3 - Saldo");
                    System.out.println("4 - Saque com erro");
                    System.out.println("5 - Deposito com erro");
                    System.out.println("6 - Sair");
                    System.out.print("Digite: ");
                    menu = scan.nextInt();

                    switch(menu){
                        case 1:
                            //Saque
                            String codSaque = gerarCodigo(); 
                            boolean aux1 = false;
                            int menuAux1;
                            Double saque;
                            System.out.print("Digite o valor do saque: ");
                            saque = scan.nextDouble();
                            do{ 
                                if(saque > adm.saldo(cpf) || saque <= 0){
                                    System.out.println("Valor inválido.");
                                    aux1 = true;
                                }else{
                                    if(adm.saque(cpf, saque, codSaque)){
                                        System.out.println("Saque realizado com sucesso.");
                                        aux1 = true;
                                    }else{
                                        System.out.println("Ocorreu um erro.");
                                        System.out.println("Deseja realizar a operação novamente? 1=Sim|2=Não");
                                        menuAux1 = scan.nextInt();
                                        if(menuAux1 == 2){
                                            aux1 = false;
                                        }else{
                                            aux1 =true;
                                        }   
                                    }
                                    System.out.println("Saldo: " + adm.saldo(cpf));
                                }
                            }while(aux1 != true);
                        break;
                        case 2:
                            //Deposito
                            String codDeposito = gerarCodigo(); 
                            boolean aux2 = false;
                            int menuAux2;
                            Double deposito;
                            System.out.print("Digite o valor do deposito: ");
                            deposito = scan.nextDouble();
                            do{ 
                                if(deposito <= 0){
                                    System.out.println("Valor inválido.");
                                    aux2 = true;
                                }else{
                                    if(adm.deposito(cpf, deposito, codDeposito)){
                                        System.out.println("Deposito realizado com sucesso.");
                                        aux2 = true;
                                    }else{
                                        System.out.println("Ocorreu um erro.");
                                        System.out.println("Deseja realizar a operação novamente? 1=Sim|2=Não");
                                        menuAux2 = scan.nextInt();
                                        if(menuAux2 == 2){
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
                            //Saldo
                            System.out.println("Saldo: "+adm.saldo(cpf));
                        break;
                        case 4:
                            //Saque com erro
                            String codSaqueF = gerarCodigo(); 
                            boolean aux4 = false;
                            int menuAux4;
                            Double saqueF;
                            System.out.print("Digite o valor do saque: ");
                            saqueF = scan.nextDouble();
                            do{ 
                                if(saqueF > adm.saldo(cpf) || saqueF <= 0){
                                    System.out.println("Valor inválido.");
                                    aux4 = true;
                                }else{
                                    if(adm.saqueFalso(cpf, saqueF, codSaqueF)){
                                        System.out.println("Saque realizado com sucesso.");
                                        aux4 = true;
                                    }else{
                                        System.out.println("Ocorreu um erro.");
                                        System.out.println("Deseja realizar a operação novamente? 1=Sim|2=Não");
                                        menuAux4 = scan.nextInt();
                                        if(menuAux4 == 2){
                                            aux4 = false;
                                        }else{
                                            aux4 =true;
                                        }   
                                    }
                                    System.out.println("Saldo: " + adm.saldo(cpf));
                                }
                            }while(aux4 != true);
                        break;
                        case 5:
                            //Deposito com erro
                            String codDepositoF = gerarCodigo(); 
                            boolean aux5 = false;
                            int menuAux5;
                            Double depositoF;
                            System.out.print("Digite o valor do deposito: ");
                            depositoF = scan.nextDouble();
                            do{ 
                                if(depositoF <= 0){
                                    System.out.println("Valor inválido.");
                                    aux5 = true;
                                }else{
                                    if(adm.depositoFalso(cpf, depositoF, codDepositoF)){
                                        System.out.println("Deposito realizado com sucesso.");
                                        aux5 = true;
                                    }else{
                                        System.out.println("Ocorreu um erro.");
                                        System.out.println("Deseja realizar a operação novamente? 1=Sim|2=Não");
                                        menuAux5 = scan.nextInt();
                                        if(menuAux5 == 2){
                                            aux5 = false;
                                        }else{
                                            aux5 =true;
                                        }   
                                    }
                                    System.out.println("Saldo: " + adm.saldo(cpf));
                                }
                            }while(aux5 != true);
                        break;
                        case 6:
                            System.out.println("Fechando o Programa.");
                        break;
                        default:
                            System.out.println("Digite uma opção válida!");
                    }

                }while(menu != 6);
            
        }catch (Exception e) {
            e.printStackTrace();
        }
        scan.close();
    }

}
