package Repositories;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileReaderService {

    public static void readFile(String path){

        try{
            String lines = Files.readString(Path.of(path));
            System.out.print(lines);

        }catch (IOException e){
            e.getMessage();
        }
    }
    public static void readLine(String path, int target){
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            System.out.println(lines.get(target-1));
        } catch (IOException e) {
            System.out.println("Falha ao ler o arquivo de perguntas.");
            e.getMessage();
        }
    }

}
