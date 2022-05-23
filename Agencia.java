import java.rmi.*;

public interface Agencia extends Remote {
    public boolean solicitaAbrirConta (String nome, int cpf) throws RemoteException;
    public boolean solicitaFecharConta (Conta c) throws RemoteException;
    public boolean solicitaAutenticarConta (Conta c) throws RemoteException;
    public Double solicitaSaque (Conta c, Double saque) throws RemoteException;
    public Double solicitaDeposito (Conta c, Double deposito) throws RemoteException;
}