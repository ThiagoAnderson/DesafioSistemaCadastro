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
    public static void opcoesBusca(){
        System.out.println("Selecione uma opção para busca");
        System.out.println("--------------------------------");
        System.out.println("1- Nome ou sobrenome\n" +
                "2- Sexo\n" +
                "3- Idade\n" +
                "4- Peso\n" +
                "5- Raça\n" +
                "6- Endereço\n"+
                "7- Criterios múltiplos"
        );
    }
    public static void opcoesEdicao(){
        System.out.println("Selecione uma opção para buscar");
        System.out.println("--------------------------------");
        System.out.println("1 - Buscar todos os pets \n" +
                "2- Buscar pet por criterio especifico");
    }
    public static void opcoesEdicaoCriterios(){
        System.out.println("Selecione uma opção para edicao");
        System.out.println("--------------------------------");
        System.out.println("1- Nome ou sobrenome\n" +
                "2- Idade\n" +
                "3- Peso\n" +
                "4- Raça\n" +
                "5- Endereço\n"+
                "6- Criterios múltiplos"
        );
    }
}
