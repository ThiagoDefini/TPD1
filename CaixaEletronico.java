import java.rmi.*;

public interface CaixaEletronico extends Remote {
    public Double solicitaSaque (Conta c, Double saque) throws RemoteException;
    public Double solicitaDeposito (Conta c, Double deposito) throws RemoteException;
    public Double solicitaSaldo(Conta c) throws RemoteException;
}