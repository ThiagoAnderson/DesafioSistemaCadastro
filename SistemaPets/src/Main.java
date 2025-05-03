import Domain.Menu;
import Domain.MenuHandler;
import Repositories.FileReaderService;
import Repositories.InputUser;
import Services.BuscarPetService;
import Validators.PetValidator;

public class Main {
    public static void main(String[] args) {
      Menu.opcoes();
      MenuHandler.menuRedirect();

    }

}
