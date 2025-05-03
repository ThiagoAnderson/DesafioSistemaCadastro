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
                        EditarPetService.renomearAposHifen(path,nome.toUpperCase());
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
                    } else {
                        System.out.println("Número inválido.");
                    }
                    break;
                case 3:
                    System.out.println("Digite o novo peso:");
                    String peso = InputUser.inputUser();
                    peso = peso.replace(",", ".");
                    if (PetValidator.validarApenasNumeros(peso)) {
                        alterarLinha(path, 5, peso);
                    } else {
                        System.out.println("Peso inválido");
                    }
                    break;
                case 4:
                    System.out.println("Digite a nova raça:");
                    String raca = InputUser.inputUser();
                    if (PetValidator.validarCaracteres(raca)) {
                        alterarLinha(path, 6, raca);
                    } else {
                        System.out.println("Raça digitada inválida");
                    }
                    break;
                case 5:
                    System.out.println("Digite o novo endereço(Nº,Cidade,Rua)");
                    String endereco = InputUser.inputUser();
                    alterarLinha(path, 3, endereco);
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
            String novoNomeCompleto = nomeAntigo.substring(0, posHifen + 1) + novoNome.replace(" ","");
            File novoArquivo = new File(arquivo.getParent(), novoNomeCompleto);
            arquivo.renameTo(novoArquivo);
        }
    }
}

