package Entities;

public class EnderecoPet {
    public String rua;
    public String numero;
    public String cidade;


    public EnderecoPet() {
    }

    public EnderecoPet(String numero,String rua,String cidade) {
        this.rua = rua;
        this.numero = numero;
        this.cidade = cidade;
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
