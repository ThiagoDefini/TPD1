public class Banco {
    private String nome;
    private int codigo;
    private Arraylist<Conta> contas;
    public Banco(String nome, int codigo) {
        this.nome = nome;
        this.codigo = codigo;
        this.contas = new ArrayList<Conta>();
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Conta getConta(int cpf){
        for(Conta c : contas){
            if(c.getCpf == cpf){
                return c; 
            }
        }
        return null;
    }

    public void adicionarConta(Conta c){
        contas.add(c);
    }
    
}
