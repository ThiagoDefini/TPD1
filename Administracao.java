import java.rmi.*;

public interface Administracao extends Remote {
    public boolean abrirConta (String nome, int cpf) throws RemoteException;
    public boolean fecharConta (int cpf) throws RemoteException;
    public boolean autenticarConta (int cpf) throws RemoteException;
    public Double saque (int cpf, Double saque) throws RemoteException;
    public Double deposito (int cpf, Double deposito) throws RemoteException;
    public Double saldo (int cpf) throws RemoteException;
}