import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

//UnicastRemoteObject permite que a implementacao da classe possa ser estabelecida como um servico remoto
public class CaixaEletronicoImp extends UnicastRemoteObject implements CaixaEletronico{

    Administracao ag = (Administracao) Naming.lookup("rmi://localhost:1099/BancService");

    public CaixaEletronicoImp() throws RemoteException{
        super();
    }

    @Override
    public Double solicitaSaque (Conta c, Double saque) throws RemoteException{
        return ag.solicitaSaque(c, saque);
    }

    @Override
    public Double solicitaDeposito (Conta c, Double deposito) throws RemoteException{
        return ag.solicitaDeposito(c, deposito);
    }

    @Override
    public Double solicitaSaldo(Conta c) throws RemoteException{
        return c.getSaldo();
    }

}
