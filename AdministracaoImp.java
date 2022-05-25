import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

//UnicastRemoteObject permite que a implementacao da classe possa ser estabelecida como um servico remoto
public class AdministracaoImp extends UnicastRemoteObject implements Administracao{

    //private Banco banco;
    private Banco banco = new Banco("Itau", 1);
        Conta c1 = new Conta("Thiago", 1);
        Conta c2 = new Conta("Felipe", 2);
        Conta c3 = new Conta("Eduardo", 3);
        

    public AdministracaoImp() throws RemoteException{
        super();
        banco.adicionarConta(c1);
        banco.adicionarConta(c2);
        banco.adicionarConta(c3);
    }

    @Override
    public boolean abrirConta (String nome, int cpf) throws RemoteException{
        Conta c = new Conta(nome, cpf);
        banco.adicionarConta(c);
        return true;
    }

    @Override
    public boolean fecharConta (int cpf) throws RemoteException{
        Conta c = banco.getConta(cpf);
        c.setStatus(false);
        return true;
    }

    @Override
    public boolean autenticarConta (int cpf) throws RemoteException{
        return banco.contains(cpf);
    }

    @Override
    public Double saque (int cpf, Double saque) throws RemoteException{
        Conta c = banco.getConta(cpf);
        if(saque > c.getSaldo() || saque <= 0){
            return c.getSaldo();
        }
        Double i = c.getSaldo() - saque;
        c.setSaldo(i);
        return c.getSaldo();
    }

    @Override
    public Double deposito (int cpf, Double deposito) throws RemoteException{
        Conta c = banco.getConta(cpf);
        if(deposito <= 0){
            return c.getSaldo();
        }
        Double i = c.getSaldo() + deposito;
        c.setSaldo(i);
        return c.getSaldo();
    }

    @Override
    public Double saldo (int cpf) throws RemoteException{
        return banco.getConta(cpf).getSaldo();
    }


}
