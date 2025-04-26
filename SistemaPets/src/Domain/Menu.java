package Domain;

import Services.CadastrarPet;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    public static void opcoes() {
        System.out.println("SELECIONE UMA OPÇÃO ABAIXO: ");
        System.out.println("--------------------------------");
        System.out.println("1-Cadastrar um novo pet\n" +
                "2-Alterar os dados do pet cadastrado\n" +
                "3-Deletar um pet cadastrado\n" +
                "4-Listar todos os pets cadastrados\n" +
                "5-Listar pets por algum critério (idade, nome, raça)\n" +
                "6-Sair");
    }
}

//    private static void escolha(int choice){
//        switch (choice){
//            case 1:
//                Arquivo arquivo = new Arquivo();
//                arquivo.lerArquivo();
//                CadastrarPet.cadastrar();
//                break;
//            case 2:
//                System.out.println("Alterando dados de um pet...");
//                break;
//            case 3:
//                System.out.println("Deletando pet cadastrado...");
//                break;
//            case 4:
//                System.out.println("Listando pets cadastrados...");
//                break;
//            case 5:
//                System.out.println("Listando pets por criterio(idade,nome,raça)...");
//                break;
//            case 6:
//                System.out.println("Saindo do menu...");
//                break;
//            default:
//                System.out.println("Opção invalida, digite novamente.");
//                Menu.opcoes();
//        }
//    }
//}
