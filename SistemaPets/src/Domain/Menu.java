package Domain;

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
