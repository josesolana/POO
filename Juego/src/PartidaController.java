//<editor-fold defaultstate="collapsed" desc="Region IMPORTS">

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;

//</editor-fold>
public class PartidaController implements Initializable {

    //<editor-fold defaultstate="collapsed" desc="Region VARIABLES AND CONSTRUCTORS">
    private final static StringBuilder EMPATE = new StringBuilder("La ronda fué un empate");
    private final static StringBuilder SUSPENSO = new StringBuilder("EL GANADOR FUE...");
    private final static StringBuilder GANADOR = new StringBuilder("GANADOR: ");

    private Parent root;
    private int rounds;
    private int actualRound;
    private Jugador player;
    private Jugador cpu;
    
    @FXML
    private Label lblRounds;
    @FXML
    private Label lblActualRound;
    @FXML
    private Label lblCaractCPU;
    @FXML
    private Label lblCaractPlayer;
    @FXML
    private Label lblNomPlayer;
    @FXML
    private Label lblNomCOM;
    @FXML
    private Label lblPuntajePlayer;
    @FXML
    private Label lblPuntajePlayer1;
    @FXML
    private Label lblPuntajeCOM;
    @FXML
    private Label lblMessage;
    @FXML
    private ComboBox<String> cmbAttributes;
    @FXML
    private HBox hBoxCOM;
    @FXML
    private HBox hboxPlayer;
    @FXML
    private HBox hBoxTable;
    @FXML
    private Button btnPlay;
    @FXML
    private Button btnNextRound;
    @FXML
    private ImageView imgCardCPU;

    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Region INITIALIZE AND CONSTRUCTOR">
    @Override
    public void initialize(URL location, ResourceBundle resources) { //CARGA TODA LA ESCENA
        btnPlay.setVisible(false);
        btnNextRound.setVisible(false);
        imgCardCPU.setVisible(false);
        hBoxTable.setDisable(true);
        refreshActualRound();
    }

    public void start(int rounds,Jugador jugador, ObservableList<String> atributo, Mazo mazo){// Configuración desde el PopUp
        this.setRounds(rounds);
        player=jugador;
        if (atributo == null)
            cmbAttributes.setVisible(false); //TODO Agregar los label de el atributo y sus valores
        else {
            cmbAttributes.setItems(atributo);
            cmbAttributes.getSelectionModel().selectFirst();
        }
        inicializarJugadores(mazo);
    }

    private void inicializarJugadores(Mazo mazo){//INICIAlIZA EL OBJETO
        ArrayList<Mazo> repartida=mazo.repartir(2); //2 Participantes
        player.setCartas(repartida.get(0));
        cpu=new Jugador(repartida.get(1));
        cpu.setNombre("CPU");
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Region METHODS">

    private void setRounds(int rounds) {//Configura el nro de partidas elegidas en el popup
        this.rounds = rounds;
        lblRounds.setText(String.valueOf(rounds));
    }

    // CÓDIGO PARA REFINAR
    public void refreshActualRound() {//Muestra en el Tablero los el nro de ronda
        lblActualRound.setText(String.valueOf(++actualRound));
        lblMessage.setText(null);
    }

    // CÓDIGO PARA REFINAR
    public void refreshPointsPoints() {//
        lblPuntajePlayer1.setText(String.valueOf(player.getPuntaje()));//Muestra en el Tablero los puntajes
        lblPuntajeCOM.setText(String.valueOf(cpu.getPuntaje()));
    }

    public void play() {//Cuando apretamos el boton Tirar
        prepararArena();
        enfrentar();
    }
    
    public void nextRound() {//Cuando apretamos el boton Siguiente
        cmbAttributes.setDisable(false);
        btnNextRound.setVisible(false);
        hBoxTable.getChildren().clear();
        player.next();
        cpu.next();
        refreshActualRound();
    }

    public void enfrentar() {//Enfrentamos a los jugadores
        hBoxTable.setDisable(true);
        switch (player.compareTo(cpu)) {//Comparamos las cartas
            case 1: {
                player.winner(cpu.perdio());//Se agregan y quitan las cartas, respectivamente.
                winnerEffect(0,player.getNombre());
                break;
            }
            case -1: {
                cpu.winner(player.perdio());
                winnerEffect(1, cpu.getNombre());
                break;
            }
            default: {
                tie();
                showMessage(EMPATE.toString());
                break;
            }
        }
        refreshPointsPoints();
    }

    public void showPlayerCard() {//Al hacer click sobre el mazo, se muestra la primer carta
        if ((hboxPlayer.getChildren().size() < 2) && (hBoxTable.getChildren().isEmpty())) {//Solo se muestra si no hay cartas en la arena, ni ya se encuentra visualizando una carta
            ImageView img = new ImageView(player.getCarta().getImage());//Cargo la imagen que lo caracteriza.
            setCommonCardStyles(img);//Lo pongo el formto de Carta
            hboxPlayer.getChildren().add(img);//Lo agrego al costado del mazo, antes de lanzarlo.
            imgCardCPU.setVisible(true);//Simulo como que el otro jugador tira una carta.
            btnPlay.setVisible(true);//Ya puedo lanzar, asi que hago visible el boton Tirar.
        }
    }

    public void showMessage(String s) {
        lblMessage.setText(s);
    }

    public void setCommonCardStyles(ImageView image) {
        image.setFitHeight(150);
        image.setFitWidth(110);
        image.setSmooth(true);
        image.setPreserveRatio(true);
        image.setEffect(new DropShadow());
    }

    public void btnReturnActionHandler(ActionEvent event) {try {
        //EXIT
        root = FXMLLoader.load(getClass().getResource("Views/MenuPrincipal.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(PartidaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Juego.getSceneMaster().setRoot(root);
    }

    private void winnerEffect(int cardToRemove, String winner) {//Se hace la animación de cuando uno de los jugadores Gana, verifica fin partida.
        Node node = hBoxTable.getChildren().get(cardToRemove);
        ScaleTransition st = new ScaleTransition(Duration.millis(250), (ImageView) node);
        st.setOnFinished((ActionEvent event) -> {
            hBoxTable.getChildren().remove(node);
            ImageView img = (ImageView) hBoxTable.getChildren().get(0);
            img.setEffect(new DropShadow(20, 0.5, 0.5, Color.LIME));
            img.setFitHeight(200);
            img.setFitWidth(150);
            if (isTheEnd()) {//Si se alcanzo en nro de rondas, o alguno se quedó son cartas: Fin
                theEnd();// Se terminó el juego
            } else {//Sino
                btnNextRound.setVisible(true);//Mostramos el botón, paa pasar a la siguiente ronda
            }
        });
        st.setToX(0.1f);
        st.setToY(0.1f);
        st.setDelay(Duration.millis(750));
        st.play();
        showMessage("El jugador " + winner + " ganó la ronda");
    }

    private void tie() {//Acá cuando Empatan
        hBoxTable.getChildren().parallelStream().forEach(carta -> {
            ((ImageView)carta).setEffect(new DropShadow(20, 0.5, 0.5, Color.GRAY));
            ((ImageView)carta).setFitHeight(180);
            ((ImageView)carta).setFitWidth(120);
        });
        if (isTheEnd()) {
            theEnd();
        } else {
            btnNextRound.setVisible(true);
        }
    }

    private boolean isTheEnd() {
        if (player.sinCartas() || cpu.sinCartas() || rounds == actualRound) {//Alguno sin cartas o se llego al limite de rondas.
            hboxPlayer.setVisible(false);
            lblNomPlayer.setVisible(false);
            hBoxCOM.setVisible(false);
            lblNomCOM.setVisible(false);
            return true;
        }
        return false;
    }

    private void theEnd() {//Efecto final, para mostrar si se perdió o ganó la partida.
        final ImageView cartaFinal;//Esto es para mostrar si ganó o perdió.
        StringBuilder winner;
        if (player.getPuntaje() > cpu.getPuntaje()) {//Quién Ganó?
            winner = new StringBuilder(lblNomPlayer.getText());//Nombre del ganador
            cartaFinal = new ImageView(getClass().getResource(ModelLoader.IMGFOLDER + "Winner.png").toString());//cargo la carta ganadora.
        } else {
            winner = new StringBuilder(lblNomCOM.getText());//nombre del ganador
            cartaFinal = new ImageView(getClass().getResource(ModelLoader.IMGFOLDER + "Loser.png").toString());//Cargo la carta perdedora
        }
        showMessage(SUSPENSO.toString());//Le pongo suspenso
        FadeTransition ft = new FadeTransition(Duration.millis(250), lblMessage);
        ft.setFromValue(1f);
        ft.setToValue(0.1f);
        ft.setCycleCount(6);
        ft.setOnFinished((ActionEvent event) -> {
            showMessage(GANADOR.toString() + winner);//Muestro el ganador
            ft.setFromValue(0f);
            ft.setToValue(1f);
            ft.setCycleCount(2);
            ft.setDuration(Duration.millis(750));
            ft.play();
            setCommonCardStyles(cartaFinal);
            hBoxTable.getChildren().clear();
            hBoxTable.getChildren().add(cartaFinal);//Mueestro si gano o perdió.
            ScaleTransition st = new ScaleTransition(Duration.millis(1500), cartaFinal);
            st.setFromX(.5f);
            st.setFromY(.5f);
            st.setToX(1.5f);
            st.setToY(1.5f);
            st.play();
        });
        ft.play();
    }

    private void prepararArena(){//Borrar y mostrar las cosas, para el enfrentamiento.
        cmbAttributes.setDisable(true);
        btnPlay.setVisible(false);
        ImageView playerImage = new ImageView(player.getCarta().getImage());
        setCommonCardStyles(playerImage);
        ImageView COMImage = new ImageView(cpu.getCarta().getImage());
        setCommonCardStyles(COMImage);
        hBoxTable.setDisable(false);
        hBoxTable.getChildren().add(COMImage);
        hBoxTable.getChildren().add(playerImage);
        hboxPlayer.getChildren().remove(1);
        imgCardCPU.setVisible(false);
    }
   //</editor-fold>
}
