import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

//UnicastRemoteObject permite que a implementacao da classe possa ser estabelecida como um servico remoto
public class AdministracaoImp extends UnicastRemoteObject implements Administracao{

    private class Transacao {
        private String codTransacao;
        private boolean status;

        public Transacao(String codTransacao, boolean status){
            this.codTransacao = codTransacao;
            this.status = status;
        }

        public String getCodTransacao() {
            return codTransacao;
        }

        public void setCodTransacao(String codTransacao) {
            this.codTransacao = codTransacao;
        }

        public boolean getStatus() {
            return status;
        }

        public void setStatus(boolean status) {
            this.status = status;
        }  

    }

    //private Banco banco;
    private Banco banco = new Banco("Itau", 1);
    Conta c1 = new Conta("Thiago", 1, 1);
    Conta c2 = new Conta("Felipe", 2, 2);
    Conta c3 = new Conta("Eduardo", 3, 3);
    private ArrayList<Transacao> transacoes;

    public AdministracaoImp() throws RemoteException{
        super();
        banco.adicionarConta(c1);
        banco.adicionarConta(c2);
        banco.adicionarConta(c3);
        transacoes = new ArrayList<Transacao>();
    }

    public boolean containsTransacao(String codCount){
        for(Transacao t : transacoes){
            if(t.getCodTransacao() == codCount){
                return true;
            }
        }
        return false;
    }

    public boolean getStatusTransacao(String codCount){
        for(Transacao t : transacoes){
            if(t.getCodTransacao() == codCount){
                return t.getStatus();
            }
        }
        return false;
    }

    @Override
    public boolean abrirConta (String nome, int cpf, int senha) throws RemoteException{
        Conta c = new Conta(nome, cpf, senha);
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
    public boolean autenticarConta (int cpf, int senha) throws RemoteException{
        if(banco.contains(cpf)){
            if(banco.getConta(cpf).getSenha() == senha){
                return true;
            }
        }            
        return false;
    }

    @Override
    public boolean saque (int cpf, Double saque, String codTransacao) throws RemoteException{
        if(containsTransacao(codTransacao) && getStatusTransacao(codTransacao) == true){
            return true;
        }
        Conta c = banco.getConta(cpf);
        if(saque > c.getSaldo() || saque <= 0){
            Transacao f = new Transacao(codTransacao, false);
            transacoes.add(f);
            return false;
        }
        Double i = c.getSaldo() - saque;
        c.setSaldo(i);
        Transacao t = new Transacao(codTransacao, true);
        transacoes.add(t);
        return true;
    }

    @Override
    public boolean saqueFalso(int cpf, Double saque, String codTransacao) throws RemoteException{
        if(containsTransacao(codTransacao) && getStatusTransacao(codTransacao) == true){
            //Verifica se a transação o correu mesmo que tenha retornado false
            return true;
        }
        Conta c = banco.getConta(cpf);
        if(saque > c.getSaldo() || saque <= 0){
            Transacao f = new Transacao(codTransacao, false);
            transacoes.add(f);
            return false;
        }
        Double i = c.getSaldo() - saque;
        c.setSaldo(i);
        //Transação ocorreu
        Transacao t = new Transacao(codTransacao, true);
        transacoes.add(t);
        //Mas retornou false
        return false;
    }

    @Override
    public boolean deposito (int cpf, Double deposito, String codTransacao) throws RemoteException{
        if(containsTransacao(codTransacao) && getStatusTransacao(codTransacao) == true){
            return true;
        }
        if(deposito <= 0){
            Transacao f = new Transacao(codTransacao, false);
            transacoes.add(f);
            return false;
        }
        Conta c = banco.getConta(cpf);
        Double i = c.getSaldo() + deposito;
        c.setSaldo(i);
        Transacao t = new Transacao(codTransacao, true);
        transacoes.add(t);
        return true;
    }

    @Override
    public boolean depositoFalso(int cpf, Double deposito, String codTransacao) throws RemoteException{
        if(containsTransacao(codTransacao) && getStatusTransacao(codTransacao) == true){
            //Verifica se a transação o correu mesmo que tenha retornado false
            return true;
        }
        if(deposito <= 0){
            Transacao f = new Transacao(codTransacao, false);
            transacoes.add(f);
            return false;
        }
        Conta c = banco.getConta(cpf);
        Double i = c.getSaldo() + deposito;
        c.setSaldo(i);
        //Transação ocorreu
        Transacao t = new Transacao(codTransacao, true);
        transacoes.add(t);
        //Mas retornou false
        return false;
    }

    @Override
    public Double saldo (int cpf) throws RemoteException{
        return banco.getConta(cpf).getSaldo();
    }


}
