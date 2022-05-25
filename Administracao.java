import java.rmi.*;

public interface Administracao extends Remote {
    public boolean abrirConta (String nome, int cpf, int senha) throws RemoteException;
    public boolean fecharConta (int cpf) throws RemoteException;
    public boolean autenticarConta (int cpf, int senha) throws RemoteException;
    //Pronto
    public boolean saque (int cpf, Double saque, String codCount) throws RemoteException;
    public boolean saqueFalso(int cpf, Double saque, String codCount) throws RemoteException;
    public boolean deposito (int cpf, Double deposito, String codCount) throws RemoteException;
    public boolean depositoFalso(int cpf, Double deposito, String codCount) throws RemoteException;
    public Double saldo (int cpf) throws RemoteException;
}