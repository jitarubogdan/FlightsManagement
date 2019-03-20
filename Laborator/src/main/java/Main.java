import controller.LoginController;
import domain.Angajat;
import domain.Client;
import domain.Zbor;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import repository.*;
import service.AngajatService;
import service.BiletService;
import service.ClientService;
import service.ZborService;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Main extends Application {
    public static void main(String[] args) {
        /*Properties prop = new Properties();
        try{
            prop.load(new FileInputStream("database.config"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        //Repository r = new ClientRepository(prop);

        //Client c = new Client(7,"Bogdan","Berchisesti");


        //ApplicationContext context = new ClassPathXmlApplicationContext("SpringApp.xml");
        //ClientService service = context.getBean(ClientService.class);
        //service.save(c);

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try{
            Properties prop = new Properties();
            try{
                prop.load(new FileInputStream("database.config"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            AngajatInterface<String,String> repoA = new AngajatRepository(prop);
            AngajatService srvA = new AngajatService(repoA);

            ZborInterface repoZ = new ZborRepository(prop);
            ZborService srvZ = new ZborService(repoZ);

            ClientInterface repoC = new ClientRepository(prop);
            ClientService srvC = new ClientService(repoC);

            BiletInterface repoB = new BiletRepository(prop);
            BiletService srvB = new BiletService(repoB,repoC,repoZ);


            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/loginWindow.fxml"));
            Parent rootLayout = loader.load();
            LoginController controller = loader.getController();

            controller.setService(srvA,srvZ,srvC,srvB);

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
