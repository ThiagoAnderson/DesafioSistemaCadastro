package Services;

import Entities.EnderecoPet;
import Entities.Enums.SexoPet;
import Entities.Enums.TipoPet;
import Entities.Pet;
import Validators.PetValidator;

public class CadastroPetService {
    private static final String NAO_INFORMADO = "NÃO INFORMADO";

    private String nome = NAO_INFORMADO;
    private String tipo;
    private String sexo;
    private String endereco;
    private String idade = NAO_INFORMADO;
    private String peso = NAO_INFORMADO;
    private String raca = NAO_INFORMADO;

    public CadastroPetService(String nome, String tipo, String sexo, String endereco, String idade, String peso, String raca) {
        this.nome = nome;
        this.tipo = tipo;
        this.sexo = sexo;
        this.endereco = endereco;
        this.idade = idade;
        this.peso = peso;
        this.raca = raca;
    }

    public static Pet cadastrarPet(String nome, String tipo, String sexo, String endereco, String idade, String peso, String raca) {
        try {
            String nomeValido = nome;
            if (nome == null || nome.trim().isEmpty()) {
                nomeValido = NAO_INFORMADO;
            }

            if (!PetValidator.nomeValidator(nome)) {
                throw new IllegalArgumentException("Nome inválido");
            }

            TipoPet tipoPet;
            try {
                tipoPet = TipoPet.valueOf(tipo.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Tipo de pet inválido. As opções válidas são GATO e CACHORRO.");
            }

            if (tipoPet != TipoPet.GATO && tipoPet != TipoPet.CACHORRO) {
                throw new IllegalArgumentException("Tipo de pet inválido, apenas GATO e CACHORRO permitidos");
            }

            SexoPet sexoPet;
            try {
                sexoPet = SexoPet.valueOf(sexo.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Sexo do pet inválido. As opções válidas são MACHO e FEMEA.");
            }

            if (sexoPet != SexoPet.FEMEA && sexoPet != SexoPet.MACHO) {
                throw new IllegalArgumentException("Sexo do pet inválido, apenas MACHO e FEMEA");
            }

            EnderecoPet enderecoPet = new EnderecoPet();
            try {
                String[] partesEndereco = endereco.split(",");

                String numero = NAO_INFORMADO;
                String cidade = NAO_INFORMADO;
                String rua = NAO_INFORMADO;

                if (partesEndereco.length == 2) {
                    cidade = partesEndereco[0].trim();
                    rua = partesEndereco[1].trim();
                } else {

                    if (partesEndereco.length > 0 && !partesEndereco[0].trim().isEmpty()) {
                        numero = partesEndereco[0].trim();
                    }
                    if (partesEndereco.length > 1 && !partesEndereco[1].trim().isEmpty()) {
                        cidade = partesEndereco[1].trim();
                    }
                    if (partesEndereco.length > 2 && !partesEndereco[2].trim().isEmpty()) {
                        rua = partesEndereco[2].trim();
                    }

                }
                enderecoPet.setNumero(numero);
                enderecoPet.setCidade(cidade);
                enderecoPet.setRua(rua);

            } catch (Exception e) {
                throw new IllegalArgumentException("Endereço inválido.");
            }

            String petI = idade;
            if (petI == null || petI.isEmpty()) {
                petI = NAO_INFORMADO;
            } else {
                String idadeParaConverter = petI.replace(",", ".");
                Float idadeNumerica;
                try {
                    idadeNumerica = Float.parseFloat(idadeParaConverter);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Idade inválida. Digite um número (use vírgula ou ponto como separador decimal).");
                }

                if (idadeNumerica > 20) {
                    throw new IllegalArgumentException("Idade maior que 20 anos não permitida.");
                }

                ;
                if (idadeNumerica < 1) {
                    petI = String.valueOf(idadeNumerica);
                    petI = "0." + petI;
                } else {
                    petI = String.valueOf(idadeNumerica.floatValue());
                }
            }

            String pesoPet = peso;
            if (peso == null || peso.isEmpty()) {
                pesoPet = NAO_INFORMADO;
            } else {
                String pesoParaConverter = peso.replace(",", ".");
                Float p;
                try {
                    p = Float.parseFloat(pesoParaConverter);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Peso invalido");
                }

                if (p > 60) {
                    throw new IllegalArgumentException("Peso máximo 60kg");
                } else if (p < 0.5) {
                    throw new IllegalArgumentException("Peso mínimo 0.5kg");
                }
                pesoPet = String.valueOf(p);
            }

            String racaPet = raca;
            if (raca == null || raca.isEmpty()) {
                racaPet = NAO_INFORMADO;
            }
            if (!PetValidator.validarCaracteres(raca)) {
                throw new IllegalArgumentException("Characteres estranho não saõ permitidos");
            }

            Pet petCadastrado = new Pet(nomeValido, tipoPet, sexoPet, enderecoPet, petI, pesoPet, racaPet);
            return petCadastrado;

        } catch (IllegalArgumentException e) {
            System.err.println("Erro ao cadastrar o pet: " + e.getMessage());
            return null;
        }
    }
}
