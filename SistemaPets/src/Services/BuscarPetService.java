package Services;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BuscarPetService {
    private static final String dir = "C:\\Dev\\DesafioSistemaCadastro\\SistemaPets\\src\\Repositories\\petsCadastrados";

    public static String buscarTodos() {
        StringBuilder resultado = new StringBuilder();

        try (Stream<Path> stream = Files.list(Paths.get(dir))) {
            List<Path> arquivos = stream.collect(Collectors.toList());
            int contador = 1;

            for (Path arquivo : arquivos) {
                if (Files.isRegularFile(arquivo)) {
                    String conteudo = Files.lines(arquivo)
                            .map(linha -> linha.replaceFirst("^\\d+\\s*-\\s*", ""))
                            .collect(Collectors.joining(" - "));

                    resultado.append(contador)
                            .append(" - ")
                            .append(conteudo)
                            .append("\n");
                    contador++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return resultado.toString();
    }
    public static String buscarCriterios(String alvo){
        StringBuilder resultado = new StringBuilder();
        int contador = 1;

        try (Stream<Path> stream = Files.list(Paths.get(dir))) {
            for (Path arquivo : stream.collect(Collectors.toList())) {
                if (Files.isRegularFile(arquivo)) {
                    String conteudo = Files.lines(arquivo)
                            .map(linha -> linha.replaceFirst("^\\d+\\s*-\\s*", ""))
                            .collect(Collectors.joining(" - "));

                    if (conteudo.contains(alvo)) {
                        resultado.append(contador++)
                                .append(" - ")
                                .append(conteudo)
                                .append("\n");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return resultado.toString();
    }
    public static String buscarCriteriosMultiplos(String alvo,String alvo2){
        StringBuilder resultado = new StringBuilder();
        int contador = 1;

        try (Stream<Path> stream = Files.list(Paths.get(dir))) {
            for (Path arquivo : stream.collect(Collectors.toList())) {
                if (Files.isRegularFile(arquivo)) {
                    String conteudo = Files.lines(arquivo)
                            .map(linha -> linha.replaceFirst("^\\d+\\s*-\\s*", ""))
                            .collect(Collectors.joining(" - "));

                    if (conteudo.contains(alvo) && conteudo.contains(alvo2)) {
                        resultado.append(contador++)
                                .append(" - ")
                                .append(conteudo)
                                .append("\n");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return resultado.toString();
    }

}
