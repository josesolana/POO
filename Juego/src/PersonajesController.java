import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class PersonajesController implements Initializable {

    private static final StringBuilder  DEFAULT_NAME = new StringBuilder("NINGUNO");

    @FXML
    private HBox hBoxMazos;
    @FXML
    private Button btnReturn;
    @FXML
    private Button btnEditMazo; 
    @FXML
    private Label lblNombreMazo;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mostrarMazos(ModelLoader.getInstance().getMazos());
        seleccionarMazo(DEFAULT_NAME, false);
    }

    private void setCommonCardStyles(ImageView image){
        image.setFitHeight(200);
        image.setFitWidth(150);
        image.setSmooth(true);
        image.setPreserveRatio(true);
        image.setEffect(new DropShadow());
        image.setBlendMode(BlendMode.SRC_ATOP);
    }

    private void mostrarMazos(ArrayList<Mazo> mazos){
        mazos.parallelStream().forEach((Mazo m) -> {
            ImageView img = new ImageView(m.getEspalda());
            img.setOnMouseClicked((MouseEvent a) -> {   
                seleccionarMazo(m.getNombre(), true);
                }); 
            setCommonCardStyles(img);     
            hBoxMazos.getChildren().add(img);
        });
    }
    
    private void seleccionarMazo(StringBuilder nombre, boolean value){
        lblNombreMazo.setText(nombre.toString());
        btnEditMazo.setVisible(value);
    }
    
    public void btnAddMazoActionHandler(ActionEvent event) throws IOException {
        System.out.println("AGREGAR MAZO");
    }
    
    public void btnEditMazoActionHandler(ActionEvent event) throws IOException {
        System.out.println("EDITAR MAZO " + lblNombreMazo.getText());
    }
    
    public void btnResetActionHandler(ActionEvent event) throws IOException {
        seleccionarMazo(DEFAULT_NAME, false);
    }
    
    public void btnReturnActionHandler(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Views/MenuPrincipal.fxml"));
        Juego.getSceneMaster().setRoot(root);
    }
}
