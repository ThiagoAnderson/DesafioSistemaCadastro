import Domain.Menu;
import Domain.MenuHandler;
import Repositories.FileReaderService;
import Validators.PetValidator;

public class Main {
    public static void main(String[] args) {
//        Menu.opcoes();
//        MenuHandler.menuRedirect();
        PetValidator petValidator = new PetValidator();
        System.out.println(petValidator.nomeValidator("Klebson"));

    }

}
