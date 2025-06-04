package Services;

import Entities.EnderecoPet;
import Entities.Enums.SexoPet;
import Entities.Enums.TipoPet;
import Entities.Pet;
import Repositories.FileReaderService;
import Repositories.InputUser;
import Repositories.WriteNewFile;
import Validators.PetValidator;

import static Validators.PetValidator.validarApenasNumeros;

public class CadastroPetService {
    private static final String NAO_INFORMADO = "NÃO INFORMADO";
    private static final String path = "C:\\Dev\\DesafioSistemaCadastro\\SistemaPets\\src\\Repositories\\Questionario.txt";

    private static String nome() {
        FileReaderService.readLine(path, 1);
        String resposta = InputUser.inputUser();
        if (resposta == null || resposta.trim().isEmpty()) {
            return NAO_INFORMADO;
        }
        if (!PetValidator.nomeValidator(resposta)) {
            System.out.println("Entrada inválida, necessário nome e sobrenome além de não ser permitido caracteres.");
            return nome();
        }
        return resposta;
    }
    private static String tipo() {
        FileReaderService.readLine(path, 2);
        String resposta = InputUser.inputUser();
        TipoPet tipoPet;
        try {
            tipoPet = TipoPet.valueOf(resposta.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Tipo de pet inválido. As opções válidas são GATO e CACHORRO.");
            return tipo();
        }
        if (tipoPet != TipoPet.GATO && tipoPet != TipoPet.CACHORRO) {
            throw new IllegalArgumentException("Tipo de pet inválido, apenas GATO e CACHORRO permitidos");
        }
        return resposta.toUpperCase();
    }
    private static String sexo(){
        FileReaderService.readLine(path, 3);
        String resposta = InputUser.inputUser();
        if(resposta.equals("Fêmea") || resposta.equals("FÊMEA")){
            resposta = "FEMEA";
        }
        SexoPet sexoPet;
        try {
            sexoPet = SexoPet.valueOf(resposta.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Sexo do pet inválido. As opções válidas são MACHO e FEMEA.");
            return sexo();
        }
        if (sexoPet != SexoPet.FEMEA && sexoPet != SexoPet.MACHO) {
            throw new IllegalArgumentException("Sexo do pet inválido, apenas MACHO e FEMEA");
        }
        return resposta.toUpperCase();
    }
    private static String[] endereco() {
        FileReaderService.readLine(path, 4);
        String resposta = InputUser.inputUser();
        String[] partes = resposta.split(",");

        String[] respostaCompleta = new String[3];

        if (partes.length == 2) {
            String rua = partes[0].trim();
            String cidade = partes[1].trim();

            if (rua.isEmpty() || cidade.isEmpty()) {
                System.out.println("Rua e cidade são obrigatórias se o número for omitido.");
                return endereco();
            }

            respostaCompleta[0] = NAO_INFORMADO;
            respostaCompleta[1] = rua;
            respostaCompleta[2] = cidade;
            return respostaCompleta;
        }

        if (partes.length == 3) {
            String numero = partes[0].trim();
            String rua = partes[1].trim();
            String cidade = partes[2].trim();

            if (numero.isEmpty() || rua.isEmpty() || cidade.isEmpty()) {
                System.out.println("Nenhum dos campos pode ser vazio.");
                return endereco();
            }

            respostaCompleta[0] = numero;
            respostaCompleta[1] = rua;
            respostaCompleta[2] = cidade;
            return respostaCompleta;
        }

        System.out.println("Formato inválido. Informe: [número],[rua],[cidade] ou apenas [rua],[cidade].");
        return endereco();
    }
    private static String idade() {
        System.out.println("O pet tem menos de um ano? (SIM/NAO)");
        String respostaIdade = InputUser.inputUser();

        if (respostaIdade == null || respostaIdade.isEmpty()) {
            System.out.println("Resposta inválida.");
            return idade();
        }

        if (respostaIdade.equalsIgnoreCase("NAO") || respostaIdade.equalsIgnoreCase("NÃO")) {
            FileReaderService.readLine(path, 5);
            System.out.print("Digite a idade em anos (até 20): ");
            String resposta = InputUser.inputUser();

            if (resposta == null || resposta.isEmpty()) {
                return NAO_INFORMADO;
            }

            Float idadeFloat;
            try {
                idadeFloat = Float.parseFloat(resposta.replace(",", "."));
            } catch (NumberFormatException e) {
                System.out.println("Formato inválido. Tente novamente.");
                return idade();
            }

            if (idadeFloat > 20) {
                System.out.println("Idade inválida.");
                return idade();
            }

            return resposta.replace(",", ".");
        } else if (respostaIdade.equalsIgnoreCase("SIM")) {
            FileReaderService.readLine(path, 5);
            System.out.println("Digite a idade da seguinte forma: 0.meses");
            String resposta = InputUser.inputUser();

            if (resposta == null || resposta.isEmpty()) {
                return NAO_INFORMADO;
            }

            Float idadeFloat;
            try {
                idadeFloat = Float.parseFloat(resposta.replace(",", "."));
            } catch (NumberFormatException e) {
                System.out.println("Formato inválido. Tente novamente.");
                return idade();
            }

            if (idadeFloat > 1) {
                System.out.println("Idade maior que um ano.");
                return idade();
            }

            return resposta.replace(",", ".");
        } else {
            System.out.println("Resposta inválida, responda com SIM ou NAO.");
            return idade();
        }
    }
    private static String peso() {
        FileReaderService.readLine(path, 6);
        String resposta = InputUser.inputUser();

        if (resposta == null || resposta.isEmpty()) {
            return NAO_INFORMADO;
        }

        resposta = resposta.replace(",", ".");

        if (!validarApenasNumeros(resposta)) {
            System.out.println("Formato inválido, tente novamente.");
            return peso();
        }

        Float pesoF = Float.parseFloat(resposta);

        if (pesoF > 60.0 || pesoF < 0.5) {
            System.out.println("Peso fora dos valores (0.5kg - 60kg), digite novamente.");
            return peso();
        }

        return resposta;
    }
    private static String raca(){
        FileReaderService.readLine(path, 7);
        String resposta = InputUser.inputUser();
        if (resposta == null || resposta.isEmpty()) {
            return NAO_INFORMADO;
        }
        if (!PetValidator.validarCaracteres(resposta)) {
            System.out.println("Apenas letras são permitidas no nome da raça.");
            return  raca();
        }
        return resposta;
    }
    public static void novoPet(){
        String nome = nome();
        String tipo = tipo();
        String sexo = sexo();
        String[] endereco = endereco();
        String idade = idade();
        String peso = peso();
        String raca = raca();

        Pet pet = new Pet(nome,TipoPet.valueOf(tipo),SexoPet.valueOf(sexo),new EnderecoPet(endereco[0],endereco[1],endereco[2]),idade,peso,raca);
        WriteNewFile.cadastrarArquivoPet(pet);
    }
}


