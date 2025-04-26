package Entities;

import Entities.Enums.SexoPet;
import Entities.Enums.TipoPet;

public class Pet {
    private String nome ;
    private TipoPet tipoPet;
    private SexoPet sexoPet;
    private EnderecoPet enderecoPet;
    private Double idade;
    private Double peso;
    private String raca;

    public Pet(String nome, TipoPet tipoPet, SexoPet sexoPet, EnderecoPet enderecoPet, Double idade, Double peso, String raca) {
        this.nome = nome;
        this.tipoPet = tipoPet;
        this.sexoPet = sexoPet;
        this.enderecoPet = enderecoPet;
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

    public EnderecoPet getEnderecoPet() {
        return enderecoPet;
    }

    public void setEnderecoPet(EnderecoPet enderecoPet) {
        this.enderecoPet = enderecoPet;
    }

    public Double getIdade() {
        return idade;
    }

    public void setIdade(Double idade) {
        this.idade = idade;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }
}
