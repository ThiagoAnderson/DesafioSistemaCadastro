package Services;

import Domain.Menu;
import Domain.MenuHandler;
import Repositories.InputUser;

import java.io.*;

public class DeletarPetService {
    public static void deletarPet() {
        File pasta = new File("C:\\Dev\\DesafioSistemaCadastro\\SistemaPets\\src\\Repositories\\petsCadastrados");
        File[] arquivos = pasta.listFiles();

        if (arquivos == null || arquivos.length == 0) {
            System.out.println("Nenhum pet cadastrado para exclusão.");
            return;
        }

        int cont = 1;
        for (File arquivo : arquivos) {
            if (arquivo.isFile()) {
                System.out.println("[" + cont + "] " + arquivo.getName());
                try (BufferedReader leitor = new BufferedReader(new FileReader(arquivo))) {
                    String linha;
                    while ((linha = leitor.readLine()) != null) {
                        System.out.println(linha);
                    }
                    System.out.println();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                cont++;
            }
        }

        System.out.println("Digite o número do pet que deseja deletar:");
        String entrada = InputUser.inputUser();
        if (!entrada.matches("\\d+")) {
            System.out.println("Entrada inválida.");
            return;
        }

        int indice = Integer.parseInt(entrada) - 1;
        if (indice < 0 || indice >= arquivos.length) {
            System.out.println("Número inválido.");
            return;
        }

        File arquivoSelecionado = arquivos[indice];
        System.out.println("Tem certeza que deseja deletar este pet? (SIM ou NAO)");
        String confirmacao = InputUser.inputUser().toUpperCase();

        if (confirmacao.equals("SIM")) {
            if (arquivoSelecionado.delete()) {
                System.out.println("Pet deletado com sucesso.");
            } else {
                System.out.println("Erro ao deletar o pet.");
            }
        } else {
            System.out.println("Operação cancelada.");
        }

        Menu.opcoes();
        MenuHandler.menuRedirect();
    }
}
