package Services;

import Domain.Arquivo;
import Entities.Enums.SexoPet;
import Entities.Enums.TipoPet;
import Entities.Pet;

import java.util.Scanner;

public class CadastrarPet {

    private static final String NAO_INFORMADO = "NÃO INFORMADO";

    public static void cadastrar() {
        Scanner input = new Scanner(System.in);
        String nome = NAO_INFORMADO;
        String sobrenome = NAO_INFORMADO;
        SexoPet sexo;
        TipoPet tipo;
        String numero = NAO_INFORMADO;
        String cidade = NAO_INFORMADO;
        String rua = NAO_INFORMADO;
        String idade = NAO_INFORMADO;
        String peso = NAO_INFORMADO;
        String raca = NAO_INFORMADO;

        String linhaNome = input.nextLine().trim();
        String[] partesNome = linhaNome.split(" ");
        if (partesNome.length < 2) {
            throw new IllegalArgumentException("Você deve digitar nome e sobrenome.");
        }

        if (partesNome[0].matches("[a-zA-Z]+") && partesNome[1].matches("[a-zA-Z]+")) {
            nome = partesNome[0];
            sobrenome = partesNome[1];
        } else {
            throw new IllegalArgumentException("O nome e sobrenome devem conter apenas letras.");
        }

        String tipoPet = input.nextLine().trim().toUpperCase();
        if (tipoPet.equals("GATO") || tipoPet.equals("CACHORRO")) {
            tipo = TipoPet.valueOf(tipoPet);
        } else {
            throw new IllegalArgumentException("Tipo de pet inválido.");
        }

        String sexoPet = input.nextLine().trim().toUpperCase();
        if (sexoPet.equals("MACHO") || sexoPet.equals("FEMEA")) {
            sexo = SexoPet.valueOf(sexoPet);
        } else {
            throw new IllegalArgumentException("Sexo inválido.");
        }

        String enderecoLinha = input.nextLine();
        String[] partesEndereco = enderecoLinha.split(",");
        if (partesEndereco.length != 3) {
            throw new IllegalArgumentException("Informe número, cidade e rua separados por vírgula.");
        }

        String entradaNumero = partesEndereco[0].trim();
        if (entradaNumero.length() == 0) {
            numero = NAO_INFORMADO;
        } else {
            numero = entradaNumero;
        }

        String entradaCidade = partesEndereco[1].trim();
        if (entradaCidade.length() == 0) {
            cidade = NAO_INFORMADO;
        } else {
            cidade = entradaCidade;
        }

        String entradaRua = partesEndereco[2].trim();
        if (entradaRua.length() == 0) {
            rua = NAO_INFORMADO;
        } else {
            rua = entradaRua;
        }

        String idadeEntrada = input.nextLine().replace(",", ".").trim();
        if (idadeEntrada.length() == 0) {
            idade = NAO_INFORMADO;
        } else {
            double idadeDouble = Double.parseDouble(idadeEntrada);
            if (idadeDouble > 20) {
                throw new IllegalArgumentException("Idade inválida.");
            } else if (idadeDouble < 1) {
                int meses = (int)(idadeDouble * 10);
                idade = "0." + meses;
            } else {
                idade = String.valueOf(idadeDouble);
            }
        }

        String pesoEntrada = input.nextLine().replace(",", ".").trim();
        if (pesoEntrada.length() == 0) {
            peso = NAO_INFORMADO;
        } else {
            double pesoDouble = Double.parseDouble(pesoEntrada);
            if (pesoDouble < 0.5 || pesoDouble > 60.0) {
                throw new IllegalArgumentException("Peso fora do intervalo permitido.");
            } else {
                peso = String.valueOf(pesoDouble);
            }
        }

        String entradaRaca = input.nextLine().trim();
        if (entradaRaca.length() == 0) {
            raca = NAO_INFORMADO;
        } else if (entradaRaca.matches("[a-zA-Z]+")) {
            raca = entradaRaca;
        } else {
            throw new IllegalArgumentException("Raça só pode conter letras.");
        }

        Pet pet = new Pet(nome, sobrenome, tipo, sexo, numero, cidade, rua, idade, peso, raca);
        Arquivo.criarArquivoPet(pet);
    }
}
