package Domain;

import Entities.Pet;
import Repositories.FileReaderService;
import Repositories.InputUser;
import Repositories.WriteNewFile;
import Services.CadastroPetService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuHandler {
    public static int menuChoice() {
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
                System.out.println("Alterando pet existente");
                break;
            case 3:
                System.out.println("Deletando pet cadastrado");
                break;
            case 4:
                System.out.println("Listando todos os pets Cadastrados");
                break;
            case 5:
                System.out.println("Listar pets por critério(idade,nome,raça)");
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
        return CadastroPetService.cadastrarPet(nome,tipo,sexo,endereco,idade,peso,raca);
    }
    private static void salvarPetArquivo(Pet pet){
        WriteNewFile.cadastrarArquivoPet(pet);
    }
}

