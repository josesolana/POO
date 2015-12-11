
import Model.Personajes.Personaje;
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
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class PersonajesController implements Initializable {

    private ArrayList<Personaje> mainList;

    @FXML
    private GridPane gridPaneCards;
    @FXML
    private Button btnReturn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainList = ModelLoader.getInstance().getListaIndividuos();
        renderGrid();
    }

    // Refinar el código, no está bueno
    private void renderGrid() {
        int i = 0;
        int j = 0;
        for (Personaje p : mainList) {
            if (j > 6) {
                i++;
                j = 0;
            }
            ImageView img = new ImageView(p.getImage());
            img.setOnMouseClicked((MouseEvent event) -> {
                mostrar();
            });
            setCommonCardStyles(img);
            GridPane.setConstraints(img, j, i);
            gridPaneCards.getChildren().add(img);
            j++;
        }
    }

    public void mostrar() {
        System.out.println("Holaaaaaaa");
    }

    public void setCommonCardStyles(ImageView image) {
        image.setFitHeight(120);
        image.setFitWidth(90);
        image.setSmooth(true);
        image.setPreserveRatio(true);
        image.setEffect(new DropShadow());
    }

    public void btnAddPersonajeActionHandler(ActionEvent event) throws IOException {
        System.out.println("Qué acelga?");
    }

    public void btnReturnActionHandler(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Views/MenuPrincipal.fxml"));
        Juego.getSceneMaster().setRoot(root);
    }

}
