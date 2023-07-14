package ec.telconet.mscomppruebamilenaorellana;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ec.telconet.mscomppruebamilenaorellana.services.UserService;

@SpringBootApplication
public class MsCompPruebaMilenaOrellanaApplication implements CommandLineRunner {
    @Autowired
    private UserService usuarioService;

    public static void main(String[] args) {
        SpringApplication.run(MsCompPruebaMilenaOrellanaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // usuarioService.crearUsuarioAdmin(); 
		//Descomentar la linea anterior para el ingreso del primer administrador
    }
}