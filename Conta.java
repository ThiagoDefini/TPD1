public class Conta {
    private String nome;
    private int cpf;
    private Double saldo;
    private boolean status;

    public Conta(String nome, int cpf){
        this.nome = nome;
        this.cpf = cpf;
        this.saldo = 0.0;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }
    
}
