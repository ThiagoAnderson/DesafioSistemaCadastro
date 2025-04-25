package Entities;

import Entities.Enums.SexoPet;
import Entities.Enums.TipoPet;

public class Pet {
    private String nome ;
    private String sobrenome;
    private TipoPet tipoPet;
    private SexoPet sexoPet;
    private String numCasa;
    private String cidade;
    private String rua;
    private String idade;
    private String peso;
    private String raca;

    public Pet(String nome, String sobrenome, TipoPet tipoPet, SexoPet sexoPet,
               String numCasa, String cidade, String rua, String idade, String peso, String raca) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.tipoPet = tipoPet;
        this.sexoPet = sexoPet;
        this.numCasa = numCasa;
        this.cidade = cidade;
        this.rua = rua;
        this.idade = idade;
        this.peso = peso;
        this.raca = raca;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public TipoPet getTipoPet() {
        return tipoPet;
    }

    public void setTipoPet(TipoPet tipoPet) {
        this.tipoPet = tipoPet;
    }

    public SexoPet getSexoPet() {
        return sexoPet;
    }

    public void setSexoPet(SexoPet sexoPet) {
        this.sexoPet = sexoPet;
    }

    public String getNumCasa() {
        return numCasa;
    }

    public void setNumCasa(String numCasa) {
        this.numCasa = numCasa;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", tipoPet=" + tipoPet +
                ", sexoPet=" + sexoPet +
                ", numCasa='" + numCasa + '\'' +
                ", cidade='" + cidade + '\'' +
                ", rua='" + rua + '\'' +
                ", idade='" + idade + '\'' +
                ", peso='" + peso + '\'' +
                ", raca='" + raca + '\'' +
                '}';
    }
}
