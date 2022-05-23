import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

//UnicastRemoteObject permite que a implementacao da classe possa ser estabelecida como um servico remoto
public class AdministracaoImp extends UnicastRemoteObject implements Administracao{

    Banco b = (Banco) Naming.lookup("rmi://localhost:1099/BancService");

    public AdministracaoImp() throws RemoteException{
        super();
    }

    @Override
    public boolean abrirConta (String nome, int cpf) throws RemoteException{
        Conta c = new Conta(nome, cpf);
        b.adicionarConta(c);
        return true;
    }

    @Override
    public boolean fecharConta (Conta c) throws RemoteException{
        return true;
    }

    @Override
    public boolean autenticarConta (Conta c) throws RemoteException{
        return true;
    }

    @Override
    public Double saque (Conta c, Double saque) throws RemoteException{
        if(saque > c.getSaldo() || saque <= 0){
            return c.getSaldo();
        }
        return c.getSaldo() - saque;
    }

    @Override
    public Double deposito (Conta c, Double deposito) throws RemoteException{
        Double aux = c.getSaldo();

        c.setSaldo(aux + deposito);

        return c.getSaldo();
    }

}
