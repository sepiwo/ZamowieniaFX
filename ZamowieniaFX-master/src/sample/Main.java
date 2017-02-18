package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;


public class Main extends Application {
    //Controller c =new Controller();

    public void Main(){

    }



    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("sample.fxml"));
      //  URL location = getClass().getResource("sample.fxml");
        //FXMLLoader fxmlLoader = new FXMLLoader();
        //Parent root = (Parent) fxmlLoader.load();
        //c = fxmlLoader.getController();
        Parent root = (Parent) loader.load();

        primaryStage.setTitle("ZamowieniaFX");
        primaryStage.setScene(new Scene(root, 700, 600));
        primaryStage.show();
    }



    public static void  main(String[] args) {
        launch(args);
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        //c.baza.zamknijPolaczenie();
    }
}
