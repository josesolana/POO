//<editor-fold defaultstate="collapsed" desc="Region IMPORTS">

import Model.Elementos.*;
import Model.Personajes.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
//</editor-fold>

public class ModelLoader {

    //<editor-fold defaultstate="collapsed" desc="Region VARIABLES AND CONSTRUCTORS">
    public final static StringBuffer IMGFOLDER = new StringBuffer( "/Resources/Images/Cards/");
    private final static StringBuffer MAZOS = new StringBuffer("/Resources/ExcelSheets/Mazos.xls");
    private final static StringBuffer LIGAS = new StringBuffer("/Resources/ExcelSheets/Ligas.xls");
    private final static ModelLoader INSTANCE = new ModelLoader(); 
    
    private ArrayList<Mazo> listaMazos;

    private ModelLoader() {
        listaMazos = new ArrayList<>();
        loadPersonajes();
        loadLiga();
    }

    public static ModelLoader getInstance() {
        return INSTANCE;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Region EXCEL AND ELEMENTS LOADING">
    private ArrayList<Sheet> loadExcelSheet(StringBuffer plantilla) {
        final InputStream excelIS = getClass().getResourceAsStream(plantilla.toString());
        Sheet[] sheet=null;
        try {
            sheet = Workbook.getWorkbook(excelIS).getSheets();
        } catch (IOException | BiffException ex) {
            Logger.getLogger(ModelLoader.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return (new ArrayList<>(Arrays.asList(sheet)));
    }

    private void loadPersonajes(){
        // Cargo la hoja de excel con la información de los individuos
        loadExcelSheet(MAZOS).forEach(mazo -> {
            // La fila 0 de la hoja contiene la cabecera de los datos (nombre, fuerza, etc).
            HashSet<String> atributos=new HashSet<>();
            ArrayList<Personaje> listaIndividuos= new ArrayList<>();
            Cell[] headerRow = mazo.getRow(0);
            // Cargo la lista con los individuos que se encuentran en la base de datos (hoja de excel).
            for (int i = 1; i < mazo.getRows() ; i++) {
                Individuo x = createIndividuo(mazo.getRow(i), headerRow,atributos);
                listaIndividuos.add(x);
            }
            listaMazos.add(new Mazo(mazo.getName(), listaIndividuos, atributos));
        });
    }
    
    private void loadLiga(){
        loadExcelSheet(LIGAS).forEach((final Sheet liga) -> { // Ahora cargo los Grupos, que están en el mismo archivo de excel, pero en la hoja 1.
            // La fila 0 de la hoja contiene la cabecera de los datos (nombre, fuerza, etc).
            Mazo mazo=null;
            for (Mazo mazoTemp : listaMazos){
                if(mazoTemp.getNombre().toString().equals(liga.getName())){
                    mazo=mazoTemp;
                    break;
                }
            }
            if(mazo!=null){
                Cell[] headerRow = liga.getRow(0);
                for (int i = 1; i < liga.getRows(); i++) {
                    Grupo x = createGrupo(liga.getRow(i), headerRow, mazo);
                    mazo.addCartas(x);
                }
            }
        });
    }
    
    private Individuo createIndividuo(Cell[] persRow, Cell[] headerRow,HashSet<String> attrib) {
        // Creo el nuevo individuo con: 
        Individuo temp = new Individuo(persRow[0].getContents(),  // Nombre
                                       persRow[2].getContents(),  // Companía de Origen
                                       persRow[1].getContents()); // Bando (héroe o villano).
        
        String attribName;
        for (int j = 3; j < headerRow.length; j++) {
            attribName = headerRow[j].getContents();
            temp.addAtributo(attribName, new APropio(Double.parseDouble(persRow[j].getContents())));
            attrib.add(attribName);
        }
        return temp;
    }

    private Grupo createGrupo(Cell[] ligaRow, Cell[] headerRow, Mazo mazo) {
        // Creo el nuevo Grupo con: Nombre y Bando (héroe o villano). 
        Grupo temp = new Grupo(ligaRow[0].getContents(), ligaRow[1].getContents());
        addPersonajesToGrupo(temp, ligaRow, mazo);        // Posteriormente obtengo los personajes que lo componen  
        addEstrategiasToGrupo(temp, headerRow, ligaRow);   // Lo sgrego a su lista.
        return temp;
    }

    private void addPersonajesToGrupo(Grupo grupo, Cell[] grupoRow, Mazo mazo) {
        ArrayList<String> miembros = new ArrayList<>(Arrays.asList(grupoRow[grupoRow.length - 2].getContents().split(",")));
        miembros.parallelStream().forEach(nombre ->{
            Personaje aux=mazo.getCarta(nombre);
            if (aux!=null)
                grupo.addPersonaje(aux);//Finalmente lo agrego
        });
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
/*    public ArrayList<String> getAllAttrib() {
        return (new ArrayList<>(allAttrib));
    }
*/
    
    public ArrayList<Mazo> getMazos(){
        return listaMazos;
    }
    
 /*   public ArrayList<Personaje> getListaIndividuos(){
        return listaIndividuos;
    }
*/
    //</editor-fold>
}
