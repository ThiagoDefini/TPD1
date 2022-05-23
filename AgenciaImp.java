import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

//UnicastRemoteObject permite que a implementacao da classe possa ser estabelecida como um servico remoto
public class AgenciaImp extends UnicastRemoteObject implements Agencia{

    Administracao ad = (Administracao) Naming.lookup("rmi://localhost:1099/BancService");

    public AgenciaImp() throws RemoteException{
        super();
    }

    @Override
    public boolean solicitaAbrirConta (String nome, int cpf) throws RemoteException{
        ad.abrirConta(nome, cpf);
        return true;
    }

    @Override
    public boolean solicitaFecharConta (Conta c) throws RemoteException{
        return true;
    }

    @Override
    public boolean solicitaAutenticarConta (Conta c) throws RemoteException{
        return true;
    }

    @Override
    public Double solicitaSaque (Conta c, Double saque) throws RemoteException{
        return ad.saque(c, saque);
    }

    @Override
    public Double solicitaDeposito (Conta c, Double deposito) throws RemoteException{
        return ad.deposito(c, deposito);
    }

}
