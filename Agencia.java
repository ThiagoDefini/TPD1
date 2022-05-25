import java.rmi.Naming;
import java.util.Scanner;

//UnicastRemoteObject permite que a implementacao da classe possa ser estabelecida como um servico remoto
public class Agencia{

    private static String cod = "2";
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

            int menu;
            do{
                System.out.println("1 - Abertura de Conta");
                System.out.println("2 - Autenticação de conta");
                System.out.println("3 - Fechamento de Conta");
                System.out.println("4 - Saque");
                System.out.println("5 - Deposito");
                System.out.println("6 - Saldo");
                System.out.println("7 - Saque com erro");
                System.out.println("8 - Deposito com erro");
                System.out.println("9 - Sair");
                menu = scan.nextInt();
                int cpf = 0;
                int senha = 0;
                switch(menu){
                    case 1:
                    //Abertura de Conta
                        String nome;
                        System.out.println("Digite o nome: ");
                        nome = scan.next();
                        System.out.println("Digite o CPF da conta: ");
                        cpf = scan.nextInt();
                        System.out.print("Digite a senha da conta: ");
                        senha = scan.nextInt();
                        if(adm.abrirConta(nome, cpf, senha)){
                            System.out.println("A conta foi criada.");
                        }else{
                            System.out.println("Erro ao criar a conta.");
                        }
                    break;
                    case 2:
                    //Autenticação de Conta
                        System.out.print("Digite o CPF da conta: ");
                        cpf = scan.nextInt();
                        System.out.print("Digite a senha: ");
                        senha = scan.nextInt();
                        if(adm.autenticarConta(cpf, senha)){
                            System.out.println("Usuário e senha corretos.");
                        }else{
                            System.out.println("Usuário ou senha incorretos.");
                        }
                    break;
                    case 3:
                    //Fechamento de Conta
                        System.out.print("Digite o CPF da conta: ");
                        cpf = scan.nextInt();

                        if(adm.fecharConta(cpf)){
                            System.out.println("A conta foi fechada com sucesso.");
                        }else{
                            System.out.println("Ouve um erro ao fechar a conta.");
                        }
                    break;
                    case 4:
                    //Saque
                        String codSaque = gerarCodigo(); 
                        boolean aux4 = false;
                        int menuAux4;
                        Double saque;
                        System.out.print("Digite o CPF da conta: ");
                        cpf = scan.nextInt();
                        System.out.print("Digite o valor do saque: ");
                        saque = scan.nextDouble();
                        do{ 
                            if(saque > adm.saldo(cpf) || saque <= 0){
                                System.out.println("Valor inválido.");
                                aux4 = true;
                            }else{
                                if(adm.saque(cpf, saque, codSaque)){
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
                    //Deposito
                        String codDeposito = gerarCodigo(); 
                        boolean aux5 = false;
                        int menuAux5;
                        Double deposito;
                        System.out.print("Digite o CPF da conta: ");
                        cpf = scan.nextInt();
                        System.out.print("Digite o valor do deposito: ");
                        deposito = scan.nextDouble();
                        do{ 
                            if(deposito <= 0){
                                System.out.println("Valor inválido.");
                                aux5 = true;
                            }else{
                                if(adm.deposito(cpf, deposito, codDeposito)){
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
                    //Saldo
                        System.out.println("Digite o CPF da conta: ");
                        cpf = scan.nextInt();
                        System.out.println("Saldo: "+adm.saldo(cpf));
                    break;
                    case 7:
                    //Saque com erro
                        String codSaqueF = gerarCodigo(); 
                        boolean aux7 = false;
                        int menuAux7;
                        Double saqueF;
                        System.out.print("Digite o CPF da conta: ");
                        cpf = scan.nextInt();
                        System.out.print("Digite o valor do saque: ");
                        saqueF = scan.nextDouble();
                        do{ 
                            if(saqueF > adm.saldo(cpf) || saqueF <= 0){
                                System.out.println("Valor inválido.");
                                aux7 = true;
                            }else{
                                if(adm.saqueFalso(cpf, saqueF, codSaqueF)){
                                    System.out.println("Saque realizado com sucesso.");
                                    aux7 = true;
                                }else{
                                    System.out.println("Ocorreu um erro.");
                                    System.out.println("Deseja realizar a operação novamente? 1=Sim|2=Não");
                                    menuAux7 = scan.nextInt();
                                    if(menuAux7 == 2){
                                        aux7 = false;
                                    }else{
                                        aux7 =true;
                                    }   
                                }
                                System.out.println("Saldo: " + adm.saldo(cpf));
                            }
                        }while(aux7 != true);
                    break;
                    case 8:
                    //Deposito com erro
                        String codDepositoF = gerarCodigo(); 
                        boolean aux8 = false;
                        int menuAux8;
                        Double depositoF;
                        System.out.print("Digite o CPF da conta: ");
                        cpf = scan.nextInt();
                        System.out.print("Digite o valor do deposito: ");
                        depositoF = scan.nextDouble();
                        do{ 
                            if(depositoF <= 0){
                                System.out.println("Valor inválido.");
                                aux8 = true;
                            }else{
                                if(adm.depositoFalso(cpf, depositoF, codDepositoF)){
                                    System.out.println("Deposito realizado com sucesso.");
                                    aux8 = true;
                                }else{
                                    System.out.println("Ocorreu um erro.");
                                    System.out.println("Deseja realizar a operação novamente? 1=Sim|2=Não");
                                    menuAux8 = scan.nextInt();
                                    if(menuAux8 == 2){
                                        aux8 = false;
                                    }else{
                                        aux8 =true;
                                    }   
                                }
                                System.out.println("Saldo: " + adm.saldo(cpf));
                            }
                        }while(aux8 != true);
                    break;
                    case 9:
                    //Fechar o programa
                        System.out.println("Fechando o Programa.");
                    break;
                    default:
                        System.out.println("Digite uma opção válida!");
                }

            }while(menu != 9);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
