package Services;

import Domain.Menu;
import Domain.MenuHandler;
import Repositories.InputUser;
import Validators.PetValidator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class EditarPetService {
    private static File[] listarTodosPet() {
        File pasta = new File("C:\\Dev\\DesafioSistemaCadastro\\SistemaPets\\src\\Repositories\\petsCadastrados");
        File[] arquivos = pasta.listFiles();
        if (arquivos != null) {
            return arquivos;
        }
        return null;
    }

    public static void editarEspecifico(int escolha) throws IOException {
        File[] list = listarTodosPet();
        String path = list[escolha - 1].getAbsolutePath();

        Menu.opcoesEdicaoCriterios();
        int escolhaMenu = Integer.parseInt(InputUser.inputUser());
        if (escolhaMenu <= 0 || escolhaMenu > 6) {
            System.out.println("Escolha invalida");
            EditarPetService.listarTodosPet();
            System.out.print("Escolha o número do pet que deseja editar: ");
            int edicaoEscolha = Integer.parseInt(InputUser.inputUser());
            EditarPetService.editarEspecifico(edicaoEscolha);
        } else {
            switch (escolhaMenu) {
                case 1:
                    System.out.println("Digite o novo nome:");
                    String nome = InputUser.inputUser();
                    if (PetValidator.validarCaracteres(nome)) {
                        alterarLinha(path, 0, nome);
                        EditarPetService.renomearAposHifen(path, nome.toUpperCase());
                        System.out.println("Nome alterado com sucesso!");
                    } else {
                        System.out.println("Nome inválido");
                        Menu.opcoesEdicao();
                    }
                    break;
                case 2:
                    System.out.println("Digite a nova idade:");
                    String idade = InputUser.inputUser();
                    idade = idade.replace(",", ".");
                    if (PetValidator.validarApenasNumeros(idade)) {
                        alterarLinha(path, 4, idade);
                        System.out.println("Idade alterado com sucesso!");
                    } else {
                        System.out.println("Número inválido.");
                        Menu.opcoesEdicao();
                    }
                    break;
                case 3:
                    System.out.println("Digite o novo peso:");
                    String peso = InputUser.inputUser();
                    peso = peso.replace(",", ".");
                    if (PetValidator.validarApenasNumeros(peso)) {
                        alterarLinha(path, 5, peso);
                        System.out.println("Peso alterado com sucesso!");
                    } else {
                        System.out.println("Peso inválido.");
                        Menu.opcoesEdicao();
                    }
                    break;
                case 4:
                    System.out.println("Digite a nova raça:");
                    String raca = InputUser.inputUser();
                    if (PetValidator.validarCaracteres(raca)) {
                        alterarLinha(path, 6, raca);
                        System.out.println("Raça alterada com sucesso!");
                    } else {
                        System.out.println("Raça inválida.");
                        Menu.opcoesEdicao();
                    }
                    break;
                case 5:
                    System.out.println("Digite o novo endereço(Nº,Cidade,Rua)");
                    String endereco = InputUser.inputUser();
                    alterarLinha(path, 3, endereco);
                    System.out.println("Endereço alterado com sucesso");
                    break;
                case 6:
                    System.out.println("Selecione dois critérios para alterar (ex: 1,2)");
                    String[] criterios = InputUser.inputUser().split(",");

                    if (criterios.length < 2) {
                        System.out.println("Critérios inválidos! Você deve informar dois atributos separados por vírgula!");
                        Menu.opcoesEdicao();
                    }

                    int opc1 = Integer.parseInt(criterios[0].trim());
                    int opc2 = Integer.parseInt(criterios[1].trim());

                    if (opc1 < 1 || opc1 > 5 || opc2 < 1 || opc2 > 5) {
                        System.out.println("Opção Inválida! Escolha um número de 1 a 5 para cada atributo.");
                        Menu.opcoesEdicao();
                    }

                    int[] linhas = {0, 0, 4, 5, 6, 3};
                    int linhaAtributoUm = linhas[opc1];
                    int linhaAtributoDois = linhas[opc2];

                    System.out.println("Digite o valor para o primeiro atributo:");
                    String valorUm = InputUser.inputUser().trim();
                    if ((opc1 == 2 || opc1 == 3) && valorUm.contains(",")) {
                        valorUm = valorUm.replace(",", ".");
                    }

                    System.out.println("Digite o valor para o segundo atributo:");
                    String valorDois = InputUser.inputUser().trim();
                    if ((opc2 == 2 || opc2 == 3) && valorDois.contains(",")) {
                        valorDois = valorDois.replace(",", ".");
                    }
                    if(opc1 == 1){
                        if(PetValidator.validarCaracteres(valorUm)){
                            renomearAposHifen(path,valorUm);
                        }
                        System.out.println("Sem caracteres especiais no nome");
                        return;
                    } else if(opc2 == 1){
                        if(PetValidator.validarCaracteres(valorDois)){
                            renomearAposHifen(path,valorDois);
                        }
                        System.out.println("Sem caracteres especiais no nome");
                        return;
                    }
                    alterarLinha(path, linhaAtributoUm, valorUm);
                    alterarLinha(path, linhaAtributoDois, valorDois);
                    System.out.println("Dados Alterados com sucesso!");
                    break;
            }
        }
    }

    private static void alterarLinha(String pathArquivo, int indiceLinha, String novoValor) throws IOException {
        Path caminho = Paths.get(pathArquivo);
        String[] linhas = Files.readAllLines(caminho).toArray(new String[0]);
        if (indiceLinha >= 0 && indiceLinha < linhas.length) {
            linhas[indiceLinha] = "- " + novoValor;
            Files.write(Path.of(pathArquivo), Arrays.asList(linhas));
        }
    }

    public static void renomearAposHifen(String caminhoCompleto, String novoNome) {
        File arquivo = new File(caminhoCompleto);
        String nomeAntigo = arquivo.getName();
        int posHifen = nomeAntigo.indexOf('-');

        if (posHifen != -1) {
            String novoNomeCompleto = nomeAntigo.substring(0, posHifen + 1) + novoNome.replace(" ", "");
            File novoArquivo = new File(arquivo.getParent(), novoNomeCompleto);
            arquivo.renameTo(novoArquivo);
        }
    }
}

