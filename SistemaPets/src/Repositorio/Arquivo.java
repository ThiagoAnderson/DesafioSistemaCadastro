package Repositorio;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Arquivo implements LerArquivo{

    String path;

    public Arquivo(String path) {
        this.path = path;
    }

    @Override
    public void lerArquivo() {
        File file = new File(path);
        try(FileReader fileReader = new FileReader(file)){

            int i;
            while ((i=fileReader.read())!= -1){
                System.out.print((char)i);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
