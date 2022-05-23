import java.rmi.*;

public interface Agencia extends Remote {
    public boolean abrirConta (String nome, int cpf) throws RemoteException;
    public boolean fecharConta (Conta c) throws RemoteException;
    public boolean autenticarConta (Conta c) throws RemoteException;
    public Double saque (Conta c, Double saque) throws RemoteException;
    public Double deposito (Conta c, Double deposito) throws RemoteException;
}