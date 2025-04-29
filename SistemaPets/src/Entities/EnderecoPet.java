package Entities;

public class EnderecoPet {

    public String numero;
    public String cidade;
    public String rua;

    public EnderecoPet() {
    }

    public EnderecoPet(String numero, String cidade, String rua) {
        this.numero = numero;
        this.cidade = cidade;
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
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


}
