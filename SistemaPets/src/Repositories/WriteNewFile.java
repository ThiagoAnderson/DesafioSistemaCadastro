package Repositories;

import Entities.Pet;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class WriteNewFile {
    public static void cadastrarArquivoPet(Pet pet) {
        LocalDateTime localDateTime = LocalDateTime.now();
        String ano = String.valueOf(localDateTime.getYear());
        String mes = String.format("%02d", localDateTime.getMonthValue());
        String dia = String.valueOf(localDateTime.getDayOfMonth());
        String hora = String.valueOf(localDateTime.getHour());
        String minuto = String.valueOf(localDateTime.getMinute());
        String[] splitName = pet.getNome().split(" ");
        String petNome = splitName[0];
        String petSobrenome = splitName[1];
        String nomeArquivo = ano + mes + dia + "T" + hora + minuto + "-" + petNome.toUpperCase()+ petSobrenome.toUpperCase();

        File pasta = new File("C:\\Dev\\DesafioSistemaCadastro\\SistemaPets\\src\\Repositories\\petsCadastrados");
        if (!pasta.exists()) {
            pasta.mkdirs();
        }
        File file = new File(pasta, nomeArquivo);

        try (FileWriter fw = new FileWriter(file);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(" - " + pet.getNome());
            bw.newLine();
            bw.write(" - " + pet.getTipoPet().toString());
            bw.newLine();
            bw.write(" - " + pet.getSexoPet().toString());
            bw.newLine();
            bw.write(" - " +pet.getEnderecoPet().getNumero()  + "," + pet.getEnderecoPet().getCidade() + "," +pet.getEnderecoPet().getRua() );
            bw.newLine();
            bw.write(" - " + pet.getIdade() + " anos");
            bw.newLine();
            bw.write(" - " + pet.getPeso() + "kg");
            bw.newLine();
            bw.write(" - " + pet.getRaca());
            bw.newLine();
            bw.flush();

        } catch (IOException e) {
            throw new RuntimeException(e);

        }
    }
}

