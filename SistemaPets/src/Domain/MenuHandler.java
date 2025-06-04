package Domain;

import Entities.Pet;
import Repositories.FileReaderService;
import Repositories.InputUser;
import Repositories.WriteNewFile;
import Services.*;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuHandler {
    private static int menuChoice() {
        try {
            Scanner input = new Scanner(System.in);
            int escolha = input.nextInt();
            return escolha;
        } catch (InputMismatchException e) {
            System.out.print("Apenas números permitidos, tente novamente: ");
            MenuHandler.menuChoice();
        }
        return 0;
    }

    public static void menuRedirect() {
        int escolha = menuChoice();
        switch (escolha) {
            case 1:
                 salvarPetArquivo(MenuHandler.coletarDadosPet());
                break;
            case 2:
                Menu.opcoesEdicao();
                String opc = InputUser.inputUser();
                if(opc.equals("1")){
                    BuscarPetService.buscarTodos();
                    System.out.print("Escolha o número do pet que deseja editar: ");
                    int edicaoEscolha = Integer.parseInt(InputUser.inputUser());
                    try {
                        EditarPetService.editarEspecifico(edicaoEscolha);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                break;
            case 3:
                DeletarPetService.deletarPet();
                break;
            case 4:
                System.out.println("Lista De Todos os Pets:");
                BuscarPetService.buscarTodos();
                break;
            case 5:
                Menu.opcoesBusca();
                BuscarPetService.BuscarPorCriterio();
                break;
            case 6:
                System.out.println("Saindo da aplicação...");
                break;
            default:
                System.out.println("Erro na escolha! Selecione novamente");
                menuRedirect();
        }
    }

    private static Pet coletarDadosPet() {
        FileReaderService.readFile("C:\\Dev\\DesafioSistemaCadastro\\SistemaPets\\src\\Repositories\\Questionario.txt");
        String nome = InputUser.inputUser();
        String tipo = InputUser.inputUser();
        String sexo = InputUser.inputUser();
        String endereco = InputUser.inputUser();
        String idade = InputUser.inputUser();
        String peso = InputUser.inputUser();
        String raca = InputUser.inputUser();
       // return CadastroPetService.cadastrarPet(nome,tipo,sexo,endereco,idade,peso,raca);
        return null;
    }
    private static void salvarPetArquivo(Pet pet){
        WriteNewFile.cadastrarArquivoPet(pet);
    }
}

