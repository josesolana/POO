
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Juego extends Application {

    private static Scene sceneMaster;
    private static Stage stageMaster; //Lo saqué del ModelLoader, porque no tiene nada que ver con el Modelo
    public final static int MAXROUND=30;//TOPE QUE PONEMOS NOSOTROS PARA QUE NO SEA INFINITO
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage stage) throws Exception {
        stageMaster=stage;
        Parent root = FXMLLoader.load(getClass().getResource("Views/MenuPrincipal.fxml"));
        sceneMaster= new Scene(root);
        stageMaster.setScene(sceneMaster);
        stageMaster.centerOnScreen();
        stageMaster.setResizable(false);
        stageMaster.setTitle("SuperHeroes");
        final Image icono = new Image(getClass().getResourceAsStream("/Resources/Images/Icons/windowIcon.png"));
        stageMaster.getIcons().add(icono);
        stageMaster.show();
    }

    public static Scene getSceneMaster() {
        return sceneMaster;
    }

    public static void closeStage() {
        stageMaster.close();
    }
    
     public static void setChild(Stage child) {
        child.initOwner(stageMaster);
    }
    
}
