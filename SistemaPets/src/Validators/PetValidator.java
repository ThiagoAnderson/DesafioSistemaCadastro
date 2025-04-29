package Validators;

public class PetValidator {
    public static boolean nomeValidator(String nomeComposto){
        if(nomeComposto == null || nomeComposto.trim().isEmpty()){
            return true;
        }
        String[] nomeSobrenome = nomeComposto.split(" ");
        if(nomeSobrenome.length < 2){
            return false;
        }
        if(!validarCaracteres(nomeComposto)){
            return false;
        }
        return true;
    }
    public static boolean validarCaracteres(String nome){
        if(nome == null || nome.isEmpty()){
            return true;
        }
        for(int i=0; i < nome.length(); i++){
            char c = nome.charAt(i);
            if(!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == ' ')){
                return false;
            }
        }
        return true;
    }
    public static boolean validarNumero(String entrada){
        if(entrada == null || entrada.isEmpty()){
            return true;
        }
        for(int i=0; i < entrada.length(); i++){
            char c = entrada.charAt(i);
            if(!Character.isDigit(c)){
                return false;
            }
        }
        return true;
    }
}
