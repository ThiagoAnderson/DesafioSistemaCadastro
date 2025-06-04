import Domain.Menu;
import Domain.MenuHandler;
import Repositories.FileReaderService;
import Repositories.InputUser;
import Services.BuscarPetService;
import Services.CadastroPetService;
import Validators.PetValidator;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
      //  System.out.println(CadastroPetService.novoPet());
       CadastroPetService.novoPet();
    }

}
