package Domain;

import Entities.Pet;

import java.io.*;
import java.time.LocalDateTime;

public class Arquivo{

    String path = "C:\\Dev\\DesafioSistemaCadastro\\SistemaPets\\file.txt";

    public Arquivo() {
    }

    public Arquivo(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void lerArquivo() {
        File file = new File(this.path);
        try(FileReader fileReader = new FileReader(file)){

            int i;
            while ((i=fileReader.read())!= -1){
                System.out.print((char)i);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void criarArquivoPet(Pet pet){

        LocalDateTime localDateTime = LocalDateTime.now();
        String ano = String.valueOf(localDateTime.getYear());
        String mes = String.format("%02d", localDateTime.getMonthValue());
        String dia = String.valueOf(localDateTime.getDayOfMonth());
        String hora = String.valueOf(localDateTime.getHour());
        String minuto = String.valueOf(localDateTime.getMinute());
        String nomeArquivo = ano+mes+dia+"T"+hora+minuto+"-"+pet.getNome().toUpperCase()+pet.getSobrenome().toUpperCase();

        File pasta = new File("C:\\Dev\\DesafioSistemaCadastro\\SistemaPets\\src\\petsCadastrados");
        if (!pasta.exists()) {
            pasta.mkdirs();
        }
        File file = new File(pasta,nomeArquivo);

        try (FileWriter fw = new FileWriter(file);
             BufferedWriter bw = new BufferedWriter(fw)){
            bw.write("1 - "+pet.getNome());
            bw.newLine();
            bw.write("2 - "+pet.getTipoPet().toString());
            bw.newLine();
            bw.write("3 - "+pet.getSexoPet().toString());
            bw.newLine();
            bw.write("4 - "+pet.getRua()+","+pet.getNumCasa()+","+pet.getCidade());
            bw.newLine();
            bw.write("5 - "+pet.getIdade()+" anos");
            bw.newLine();
            bw.write("6 - "+pet.getPeso()+"kg");
            bw.newLine();
            bw.write("7 - "+pet.getRaca());
            bw.newLine();
            bw.flush();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
