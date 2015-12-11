//<editor-fold defaultstate="collapsed" desc="Region IMPORTS">

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
//</editor-fold>

public class MenuPrincipalController {

    //<editor-fold defaultstate="collapsed" desc="Region VARIABLES AND CONSTRUCTORS">
    @FXML
    private Button btnPersonajes;
    @FXML
    private Button btnSalir;
    
    private static PartidaController controladorPartida;
    private static Parent rootPartida;
    private static Scene popUpScene;
    
    public MenuPrincipalController(){
        Platform.runLater(() -> {////CARGO TODA LA VISTA DEL POPUP DE LA PARTIDA
            cargarVistaPopUp();
        });
        Platform.runLater(() -> {//CARGO TODA LA VISTA LA PARTIDA
            cargarVistaPartida();
        });
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Region HANDLERS AND EVENTS">   
    public void btnNuevaPartidaActionHandler(ActionEvent event) {
        final Stage popUpStage = new Stage();
        Platform.runLater(() -> {
            configPopUpWindow(popUpStage);//Armo la ventana del popup en paralelo
        });
        Platform.runLater(() -> {
            configPartidaWindow(popUpStage);//Cargo la partida y luego los datos de la ventana
        });
    }

    public void btnPersonajesActionHandler(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Views/Personajes.fxml"));
        Juego.getSceneMaster().setRoot(root);
    }

    public void showHelpScreen() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Views/AcercaDe.fxml"));
        Juego.getSceneMaster().setRoot(root);
    }

    public void btnSalirActionHandler(ActionEvent event) {
        Juego.closeStage();
    }

    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Region CONFIGS AND THREADS">   
    private void showPopUpOpciones(Stage popUpStage){
        Juego.setChild(popUpStage);                                             //Seteo como padre a la ventana principal
        popUpStage.setScene(popUpScene);                                        //Cargo la visata a la vetana
        popUpStage.initModality(Modality.APPLICATION_MODAL);                    //Seteo el stage padre como MODAL, para que permanezca bloqueado hasta salir del popup
        popUpStage.showAndWait();
    }

    private void configPopUpWindow(Stage popUpStage) {
        popUpStage.setTitle("CONFIGURACIÓN FINAL");
        Image icono = new Image(getClass().getResourceAsStream("/Resources/Images/Icons/settingIcon.png"));
        popUpStage.getIcons().add(icono);
        popUpStage.centerOnScreen();
        popUpStage.setResizable(false);
    }

    private void configPartidaWindow(Stage popUpStage) {
        Juego.getSceneMaster().setRoot(rootPartida);
        showPopUpOpciones(popUpStage);
    }
    
    private void cargarVistaPartida() {//Este es el Hilo para cargar la escena de la partida
        FXMLLoader fxmlPartida = new FXMLLoader(getClass().getResource("Views/Partida.fxml"));
            try {
                rootPartida = fxmlPartida.load();
            } catch (IOException ex) {
                Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }
            controladorPartida= fxmlPartida.<PartidaController>getController();
    }
    
    private void cargarVistaPopUp() {//Este es el Hilo para cargar la escena del PopUp
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Views/PopUpNuevaPartida.fxml"));
            try {
                Parent root = fxmlLoader.load();
                popUpScene = new Scene(root);
            } catch (IOException ex) {
                Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Platform.runLater(() -> {//CARGO LOS DATOS DEL CONTROLADOR
                PopUpNuevaPartidaController controller = fxmlLoader.<PopUpNuevaPartidaController>getController();
                controller.setPadreController(controladorPartida);
 //               controller.initializePopUp();
            });
    }
    
       //</editor-fold>
}
