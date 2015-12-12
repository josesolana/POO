//<editor-fold defaultstate="collapsed" desc="Region IMPORTS">

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

//</editor-fold>

public class PopUpNuevaPartidaController implements Initializable {

    //<editor-fold defaultstate="collapsed" desc="Region VARIABLES AND CONSTRUCTORS">
    
    private final static String[] MODOAUTO=new String[] {"Random","Atributo","MAYOR","MENOR"};
    private final static String MANUAL="Manual";
    private final static String NOMBRE="HUMANO";
    private PartidaController padreController;
    private final HashMap<String,ModoJuego> modoJuegos;
    private Mazo mazo = ModelLoader.getInstance().getMazos().get(0);
    
    @FXML
    private Slider sldNumRondas;
    @FXML
    private Label lblRondas;
    @FXML
    private TextField txtBoxNomPlayer;
    @FXML
    private ChoiceBox<String> choiceBoxModalidad;
    @FXML
    private ChoiceBox<String> cmbAttributes;
    @FXML
    private CheckBox chkBoxAuto;
    @FXML
    private AnchorPane anchorPane;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        anchorPane.addEventFilter(KeyEvent.KEY_PRESSED,(KeyEvent e) -> {//Con el Enter pasa al próximo scenario.
            if (e.getCode()==KeyCode.ENTER)
                btnReadyActionHandler(new ActionEvent());
        });
        cmbAttributes.setItems(FXCollections.observableArrayList(mazo.getAtributo()));
        cmbAttributes.getSelectionModel().selectFirst();
        modoJuegos.put(MANUAL, new Manual(cmbAttributes));
        modoJuegos.put(MODOAUTO[0], new Random(cmbAttributes));
        modoJuegos.put(MODOAUTO[1],  modoJuegos.get(MANUAL));
        
        choiceBoxModalidad.setItems(FXCollections.observableArrayList(MODOAUTO));
        choiceBoxModalidad.getSelectionModel().select(MODOAUTO[0]);
        choiceBoxModalidad.valueProperty().addListener((a,b,nuevo) -> {
            cmbAttributes.setVisible(nuevo.equals(MODOAUTO[1]));
        });
        initTabOpcDeJuego();
    }
    
    //
    public PopUpNuevaPartidaController(){
        modoJuegos=new HashMap<>();
        padreController=null;
    }

    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Region METHODS">
    
    // Antes de mostrar la ventanita, inicializo el Slider con las cantidades de rondas posibles.
    private void initTabOpcDeJuego() {//Consotructor de la parte visual
        sldNumRondas.setValue((Juego.MAXROUND/3));
        sldNumRondas.setMin(1);
        sldNumRondas.setMax(Juego.MAXROUND);
        sldNumRondas.setShowTickLabels(true);
        sldNumRondas.setShowTickMarks(true);
        sldNumRondas.setMajorTickUnit(Juego.MAXROUND/2);
        sldNumRondas.setMinorTickCount(5);
        lblRondas.setText(String.format("%.0f",sldNumRondas.getValue()));
        
        //Los listeners
        sldNumRondas.valueProperty().addListener((a,b,nuevo) -> {
            lblRondas.setText(String.format("%.0f", nuevo));//Hace que el Numerito observe el Slider y se actualice.
        });
    }
    
    //Nombre a Mostrar
    private String getPlayerName() {
        String tmpName=txtBoxNomPlayer.getText().toUpperCase(Locale.getDefault());
        return tmpName.isEmpty() ? NOMBRE : tmpName;
    }
    
    // Configura el padre
   public void setPadreController(PartidaController aThis) {
        padreController = aThis;
    }

    //accion si se selecciona automatico
    public void automatic(){
        boolean estaAuto=chkBoxAuto.isSelected();
        choiceBoxModalidad.setVisible(estaAuto);
        if(estaAuto){
            boolean porAtributo= choiceBoxModalidad.getValue().equals(MODOAUTO[1]);
            cmbAttributes.setVisible(porAtributo);
        }
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Region HANDLERS AND EVENTS"> 
    
    public void btnReadyActionHandler(ActionEvent event) {
        boolean esAuto = chkBoxAuto.isSelected();
        Jugador jugador= new Jugador(getPlayerName());//Creo el jugador, con los ajustes seteados
        String selectedMJ =(esAuto) ? choiceBoxModalidad.getValue() : MANUAL ;
        jugador.setModoJuego(modoJuegos.get(selectedMJ));//Seteo el tipo de juego elegido
        ObservableList<String> atributo=(esAuto) ? null : cmbAttributes.getItems() ; //O MEJOR AGREGO UN NEEDEXTRA A MJ??????
        padreController.start((int)sldNumRondas.getValue(), jugador, atributo, mazo);//TODO: Hay que mandar el mazo seleccionado
        ((Stage) sldNumRondas.getScene().getWindow()).close();
    }

    public void btnReturnActionHandler(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Views/MenuPrincipal.fxml"));
        Juego.getSceneMaster().setRoot(root);
        ((Stage) sldNumRondas.getScene().getWindow()).close();
    }
    
    //</editor-fold>
}