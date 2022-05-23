import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class BancoServer {
    public BancoServer(){
        try {
            //Definicao do ip onde o servico ira funcionar
            System.setProperty("java.rmi.server.hostname", "localhost");
            //Registro do servico em uma porta
            LocateRegistry.createRegistry(1099);
            //Cria o objeto que implementa os metodos que serao servidos
            Banco b = new Banco("Itau",1);
            Administracao a = new AdministracaoImp();
            Conta c1 = new Conta("Felipe",12345);
            Conta c2 = new Conta("Eduardo",54321);
            b.adicionarConta(c1);
            b.adicionarConta(c2);
            //Coloca na porta registrada o servico da calculadora
            Naming.bind("BancService", (Remote) b);
            System.out.println("Conexao estabelecida.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new BancoServer();
    }
}