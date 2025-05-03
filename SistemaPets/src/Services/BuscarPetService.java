package Services;

import Domain.Menu;
import Domain.MenuHandler;
import Repositories.InputUser;
import Validators.PetValidator;

import java.io.*;

public class BuscarPetService {
    public static void buscarTodos() {
        File pasta = new File("C:\\Dev\\DesafioSistemaCadastro\\SistemaPets\\src\\Repositories\\petsCadastrados");
        File[] arquivos = pasta.listFiles();
        int cont = 1;
        for (File arquivo : arquivos) {
            if (arquivo.isFile()) {
                String absolutePath = arquivo.getAbsolutePath();
                File file = new File(absolutePath);
                try (BufferedReader leitor = new BufferedReader(new FileReader(file))) {
                    String linha;
                    System.out.print(cont);
                    while ((linha = leitor.readLine()) != null) {
                        System.out.print(linha);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            cont++;
            System.out.println();
        }
    }
    public static void BuscarPorCriterio() {
        String escolha = InputUser.inputUser();
        if (PetValidator.validarNumero(escolha)) {
            switch (escolha) {
                case "1":
                    System.out.println("Nome ou sobrenome?");
                    String campo = InputUser.inputUser().toLowerCase();

                    if (campo.equals("nome") || campo.equals("sobrenome")) {
                        System.out.println("Digite o " + campo + " a ser buscado:");
                        String criterio = InputUser.inputUser().toLowerCase();
                        File pasta = new File("C:\\Dev\\DesafioSistemaCadastro\\SistemaPets\\src\\Repositories\\petsCadastrados");
                        File[] arquivos = pasta.listFiles();
                        boolean encontrado = false;

                        if (arquivos != null) {
                            for (File arquivo : arquivos) {
                                if (arquivo.isFile()) {
                                    try (BufferedReader leitor = new BufferedReader(new FileReader(arquivo))) {
                                        String primeiraLinha = leitor.readLine();

                                        if (primeiraLinha != null && primeiraLinha.trim().startsWith("-")) {
                                            String nomeCompleto = primeiraLinha.replace("-", "").trim();
                                            String[] partes = nomeCompleto.split(" ");

                                            String valorComparado = "";

                                            if (campo.equals("nome") && partes.length >= 1) {
                                                valorComparado = partes[0].toLowerCase();
                                            }

                                            if (campo.equals("sobrenome") && partes.length >= 2) {
                                                valorComparado = partes[1].toLowerCase();
                                            }

                                            if (valorComparado.equals(criterio)) {
                                                encontrado = true;
                                                System.out.println("Arquivo: " + arquivo.getName());
                                                try (BufferedReader leitor2 = new BufferedReader(new FileReader(arquivo))) {
                                                    String linha;
                                                    while ((linha = leitor2.readLine()) != null) {
                                                        System.out.println(linha);
                                                    }
                                                    System.out.println();
                                                }
                                            }
                                        }
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }

                        if (!encontrado) {
                            System.out.println("Nenhum pet cadastrado com esse critério");
                            Menu.opcoes();
                            MenuHandler.menuRedirect();
                        }
                    } else {
                        System.out.println("Escolha inválida, digite 'nome' ou 'sobrenome'");
                        Menu.opcoesBusca();
                        BuscarPetService.BuscarPorCriterio();
                    }
                    break;
                case "2":
                    System.out.println("Digite o sexo do pet (macho ou fêmea):");
                    String sexo = InputUser.inputUser().toLowerCase();
                    buscarPorAtributo(3, sexo);
                    break;
                case "3":
                    System.out.println("Digite a idade do pet:");
                    String idade = InputUser.inputUser().toLowerCase();
                    idade = idade.replace(",", ".");
                    buscarPorAtributo(5, idade);
                    break;
                case "4":
                    System.out.println("Digite o peso do pet:");
                    String peso = InputUser.inputUser().toLowerCase();
                    peso = peso.replace(",", ".");
                    buscarPorAtributo(6, peso);
                    break;
                case "5":
                    System.out.println("Digite a raça do pet:");
                    String raca = InputUser.inputUser().toLowerCase();
                    buscarPorAtributo(7, raca);
                    break;
                case "6":
                    System.out.println("Digite o endereço do pet(Nº,Cidade,Rua):");
                    String endereco = InputUser.inputUser().toLowerCase();
                    buscarPorAtributo(4, endereco);
                    break;
                case "7":
                    System.out.println("Digite os atributos desejados na pesquisa (ex: 1,2)");
                    String[] atributos = InputUser.inputUser().split(",");
                    if (atributos.length < 2) {
                        System.out.println("Você deve informar dois atributos separados por vírgula!");
                        return;
                    }
                    int opc1 = Integer.parseInt(atributos[0].trim());
                    int opc2 = Integer.parseInt(atributos[1].trim());
                    int[] linhas = {0, 1, 3, 5, 6, 7, 4};
                    if (opc1 < 1 || opc1 > 6 || opc2 < 1 || opc2 > 6) {
                        System.out.println("Opção inválida! Escolha um número de 1 a 6 para cada atributo.");
                        return;
                    }
                    int linhaAtributoUm   = linhas[opc1];
                    int linhaAtributoDois = linhas[opc2];


                    System.out.println("Digite o valor para o primeiro atributo:");
                    String valorUm = InputUser.inputUser().trim();
                    if(valorUm.contains(",") && opc1 != 6){
                        valorUm = valorUm.replace(",", ".");
                    }
                    System.out.println("Digite o valor para o segundo atributo:");
                    String valorDois = InputUser.inputUser().trim();
                    if(valorDois.contains(",") && opc2 != 6){
                        valorDois = valorDois.replace(",", ".");
                    }


                    buscarDoisAtributos(valorUm, valorDois, linhaAtributoUm, linhaAtributoDois);
                    break;
                default:
                    System.out.println("Opção inválida.");
                    Menu.opcoesBusca();
                    BuscarPetService.BuscarPorCriterio();
                    break;
            }
        }
    }
    private static void buscarPorAtributo(int linhaAtributo, String valor) {
        File pasta = new File("C:\\Dev\\DesafioSistemaCadastro\\SistemaPets\\src\\Repositories\\petsCadastrados");
        File[] arquivos = pasta.listFiles();
        boolean encontrado = false;

        if (arquivos != null) {
            for (File arquivo : arquivos) {
                if (arquivo.isFile()) {
                    try (BufferedReader leitor = new BufferedReader(new FileReader(arquivo))) {
                        String linha;
                        int linhaAtual = 1;

                        while ((linha = leitor.readLine()) != null) {
                            if (linhaAtual == linhaAtributo) {
                                if (linha.toLowerCase().contains(valor)) {
                                    encontrado = true;
                                    System.out.println("Arquivo: " + arquivo.getName());
                                    try (BufferedReader leitor2 = new BufferedReader(new FileReader(arquivo))) {
                                        String linha2;
                                        while ((linha2 = leitor2.readLine()) != null) {
                                            System.out.println(linha2);
                                        }
                                        System.out.println();
                                    }
                                }
                            }
                            linhaAtual++;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        if (!encontrado) {
            System.out.println("Nenhum pet cadastrado com esse critério");
            Menu.opcoes();
            MenuHandler.menuRedirect();
        }
    }
    private static void buscarDoisAtributos(String atributoUm, String atributoDois, int linhaAtributoUm, int linhaAtributoDois) {
        File pasta = new File("C:\\Dev\\DesafioSistemaCadastro\\SistemaPets\\src\\Repositories\\petsCadastrados");
        File[] arquivos = pasta.listFiles();
        boolean encontrado = false;

        if (arquivos != null) {
            for (File arquivo : arquivos) {
                if (arquivo.isFile()) {
                    boolean atr1 = false;
                    boolean atr2 = false;
                    try (BufferedReader leitor = new BufferedReader(new FileReader(arquivo))) {
                        String linha;
                        int linhaAtual = 1;
                        while ((linha = leitor.readLine()) != null) {
                            String texto = linha.toLowerCase();
                            if (linhaAtual == linhaAtributoUm && texto.contains(atributoUm.toLowerCase())) {
                                atr1 = true;
                            }
                            if (linhaAtual == linhaAtributoDois && texto.contains(atributoDois.toLowerCase())) {
                                atr2 = true;
                            }
                            linhaAtual++;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (atr1 && atr2) {
                        encontrado = true;
                        System.out.println("Arquivo: " + arquivo.getName());
                        try (BufferedReader leitor2 = new BufferedReader(new FileReader(arquivo))) {
                            String linha2;
                            while ((linha2 = leitor2.readLine()) != null) {
                                System.out.println(linha2);
                            }
                            System.out.println();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        if (!encontrado) {
            System.out.println("Pet não encontrado!");
            Menu.opcoesBusca();
            BuscarPetService.BuscarPorCriterio();
        }
    }
}
