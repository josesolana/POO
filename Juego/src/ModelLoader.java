//<editor-fold defaultstate="collapsed" desc="Region IMPORTS">

import Model.Elementos.*;
import Model.Personajes.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
//</editor-fold>

public class ModelLoader {

    //<editor-fold defaultstate="collapsed" desc="Region VARIABLES AND CONSTRUCTORS">
    public final static String IMGFOLDER = "/Resources/Images/Cards/";
    public final static String EXCEL = "/Resources/ExcelSheets/Personajes.xls";
    private final static ModelLoader INSTANCE = new ModelLoader();              //TODO: Hay que Hacer que juege y agrege las cartas
    
    private final HashSet<String> allAttrib;
    private ArrayList<Personaje> listaIndividuos;

    private ModelLoader() {
        allAttrib = new HashSet<>();
        listaIndividuos = new ArrayList<>();
        try {
            loadElements();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ModelLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ModelLoader getInstance() {
        return INSTANCE;
    }

    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Region EXCEL AND ELEMENTS LOADING">
    private Sheet loadExcelSheet(int hoja) {
        try {
            final InputStream excelIS = getClass().getResourceAsStream(EXCEL);
            Sheet sheet = Workbook.getWorkbook(excelIS).getSheet(hoja);
            return sheet;
        } catch (IOException | BiffException ex) {
            Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    //TODO: REVISAR ESTO, es 99% REUSABLE!
    private void loadElements() throws FileNotFoundException {
        //Platform.runLater(()->{
            Sheet sheet = loadExcelSheet(0);
            // Cargo la hoja de excel con la información de los individuos.
            int rows = sheet.getRows();
            int columns = sheet.getColumns();

            // La fila 0 de la hoja contiene la cabecera de los datos (nombre, fuerza, etc).
            Cell[] headerRow = sheet.getRow(0);

            // Cargo la lista con los individuos que se encuentran en la base de datos (hoja de excel).
            for (int i = 1; i < rows; i++) {
                Individuo x = createIndividuo(sheet, headerRow, i, columns);
                listaIndividuos.add(x);
            }
        //});
        //Platform.runLater(()->{
            /*Sheet*/ sheet = loadExcelSheet(1);
            // Ahora cargo los Grupos, que están en el mismo archivo de excel, pero en la hoja 1.
            /*int */rows = sheet.getRows();
            /*int */columns = sheet.getColumns();
            // La fila 0 de la hoja contiene la cabecera de los datos (nombre, fuerza, etc).
            /*Cell[] */headerRow = sheet.getRow(0);

            for (int i = 1; i < rows; i++) {
                Grupo x = createGrupo(sheet, headerRow, i, columns, listaIndividuos);
                listaIndividuos.add(x);
            }
        //});
    }

    private Individuo createIndividuo(Sheet sheet, Cell[] headerRow, int row, int columns) {
        Cell[] individuoRow = sheet.getRow(row);    // Cargo la fila correspondiente al personaje
                                                    // Creo el nuevo individuo con: 
        Individuo temp = new Individuo(individuoRow[0].getContents(),  // Nombre
                                       individuoRow[2].getContents(),  // Companía de Origen
                                       individuoRow[1].getContents()); // Bando (héroe o villano).
        
        String attribName;
        for (int j = 3; j < headerRow.length; j++) {
            attribName = headerRow[j].getContents();
            temp.addAtributo(attribName, new APropio(Double.parseDouble(individuoRow[j].getContents())));
            allAttrib.add(attribName);
        }
        return temp;
    }

    private Grupo createGrupo(Sheet sheet, Cell[] headerRow, int row, int columns, ArrayList<Personaje> lista) {
        Cell[] grupoRow = sheet.getRow(row); // cargo la fila correspondiente al personaje
        // Creo el nuevo Grupo con: Nombre y Bando (héroe o villano). 
        Grupo temp = new Grupo(grupoRow[0].getContents(), grupoRow[1].getContents());
        addPersonajesToGrupo(temp, grupoRow, lista);        // Posteriormente obtengo los personajes que lo componen  
        addEstrategiasToGrupo(temp, headerRow, grupoRow);   // Lo sgrego a su lista.
        return temp;
    }

    private void addPersonajesToGrupo(Grupo temp, Cell[] grupoRow, ArrayList<Personaje> lista) {
        ArrayList<String> miembros = new ArrayList<>(Arrays.asList(grupoRow[grupoRow.length - 2].getContents().split(",")));
        miembros.parallelStream().forEach(nombre -> //Recorro la lista de Miembros
            lista.parallelStream().filter(p -> // Filtro de la lista de personajes
                    (p.getNombre().equals(nombre))).parallel().forEach(personalTemp -> { //Aquellos que son igual a nombre
                        temp.addPersonaje(personalTemp); //Finalmente lo agrego
            })
        );
    }

    private void addEstrategiasToGrupo(Grupo temp, Cell[] headerRow, Cell[] grupoRow) {
        EMayorValor mayorValor = new EMayorValor();
        EMenorValor menorValor = new EMenorValor();
        ESuma sumaValores = new ESuma();
        EPromedio promedioValores = new EPromedio();
        String value;
        String header;

        for (int j = 2; j < headerRow.length - 1; j++) {
            value = grupoRow[j].getContents();
            header = headerRow[j].getContents();

            switch (value) {
                case "Mayor":
                    temp.addEstrategia(header, mayorValor);
                    break;
                case "Menor":
                    temp.addEstrategia(header, menorValor);
                    break;
                case "Promedio":
                    temp.addEstrategia(header, promedioValores);
                    break;
                case "Suma":
                    temp.addEstrategia(header, sumaValores);
                    break;
                default:
                    temp.addEstrategia(header, new EValorFijo(Double.parseDouble(value)));
                    break;
            }
        }
    }

    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Region GETTERS">
    public ArrayList<String> getAllAttrib() {
        return (new ArrayList<>(allAttrib));
    }

    public ArrayList<Personaje> getListaIndividuos(){
        return listaIndividuos;
    }

    //</editor-fold>
}
