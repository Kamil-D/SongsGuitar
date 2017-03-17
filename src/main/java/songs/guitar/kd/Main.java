package songs.guitar.kd;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import songs.guitar.kd.util.HibernateUtil;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("../resources/sample.fxml"));
        // Aby zainicjalizować statyczne składowe klasy HibernateUtil należy wczytać klasę do pamięci - np. poprzez stworzenie jej nowego obiektu

        HibernateUtil hibernateUtil = new HibernateUtil();

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("javafxviews/sample.fxml"));
        primaryStage.setTitle("Songs to learn");
        primaryStage.setScene(new Scene(root, 1200, 800));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
