import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import javafx.util.Pair;

public class MainSceneController {

    @FXML private AnchorPane fondo;
    @FXML private MenuButton menuLineas_INICIO;
    @FXML private MenuButton menuEstaciones_INICIO;
    @FXML private MenuButton menuLineas_FIN;
    @FXML private MenuButton menuEstaciones_FIN;
    @FXML private Button button;
    @FXML private Text textoETA;


    @FXML private Line Obs_Tac,Tac_Jua,Jua_Cha,Cha_Sev,Sev_Ins,Ins_Cua,Cua_Bal ,
                        Uni_Cop,Cop_Quev,Quev_Viv,Viv_Coy,Coy_Zap,Zap_Div,Div_Eug,Eug_Eti,Eti_CenMed,CenMed_HGen,HGen_NH,NH_Bal,Bal_Jua,
                        Bar_Mix,Mix_SAntonio,SAntonio_SPedro,SPedro_Tac,Tac_Cons,Con_Aud,Aud_Pol,
                        Tac_Pat,Pal_Chil,Chil_CenMed,CenMed_LazCar,
                        Mix_Ins,Ins_H20Nov,H20Nov_Zap,Zap_PVenad,PVenad_Eje;

    @FXML private Circle Observatorio, Tacubaya, Juanacatlan, Chapultepec, Sevilla, Insurgentes, Cuauhtemoc, Balderas, 
                        Universidad, Copilco, Quevedo, Viveros, Coyoacan, Zapata, Division, Eugenia, Etiopia, Medico, Hospital, Niños, Juarez, 
                        Barranca, Mixcoac, SanAntonio, Constituyentes, SanPedrodelosPinos, Auditorio, Polanco,
                        Patriotismo, Chilpancingo, LazaroCardenas, 
                        InsurgentesSur, H20Nov, ParqueVenados, EjeCentral;  

    String id_to_estacion[] = new String[35];
    HashMap<String,Circle> hashStr_Circle = new HashMap<>();
    HashMap<Pair<Circle,Circle>,Line> hashCirclePair_Line = new HashMap<>();

    List<Line> tramos;
    Circle ultimaSelec_INICIO; String estacion_SALIDA;
    Circle ultimaSelec_FIN; String estacion_LLEGADA;

    void ini_hash(){
        // Línea 1
    hashStr_Circle.put("Observatorio", Observatorio);
    hashStr_Circle.put("Tacubaya", Tacubaya);
    hashStr_Circle.put("Juanacatlan", Juanacatlan);
    hashStr_Circle.put("Chapultepec", Chapultepec);
    hashStr_Circle.put("Sevilla", Sevilla);
    hashStr_Circle.put("Insurgentes", Insurgentes);
    hashStr_Circle.put("Cuauhtemoc", Cuauhtemoc);
    hashStr_Circle.put("Balderas", Balderas);

    // Línea 3
    hashStr_Circle.put("Universidad", Universidad);
    hashStr_Circle.put("Copilco", Copilco);
    hashStr_Circle.put("M. A. De Quevedo", Quevedo);
    hashStr_Circle.put("Viveros", Viveros);
    hashStr_Circle.put("Coyoacan", Coyoacan);
    hashStr_Circle.put("Zapata", Zapata);
    hashStr_Circle.put("Division del Norte", Division);
    hashStr_Circle.put("Eugenia", Eugenia);
    hashStr_Circle.put("Etiopia", Etiopia);
    hashStr_Circle.put("Centro Medico", Medico);
    hashStr_Circle.put("Hospital General", Hospital);
    hashStr_Circle.put("Niños Heroes", Niños);
    hashStr_Circle.put("Juarez", Juarez);

    // Línea 7
    hashStr_Circle.put("Barranca del Muerto", Barranca);
    hashStr_Circle.put("Mixcoac", Mixcoac);
    hashStr_Circle.put("San Antonio", SanAntonio);
    hashStr_Circle.put("San Pedro de los Pinos", SanPedrodelosPinos);
    hashStr_Circle.put("Constituyentes", Constituyentes);
    hashStr_Circle.put("Auditorio", Auditorio);
    hashStr_Circle.put("Polanco", Polanco);

    // Línea 9
    hashStr_Circle.put("Patriotismo", Patriotismo);
    hashStr_Circle.put("Chilpancingo", Chilpancingo);
    hashStr_Circle.put("Lazaro Cardenas", LazaroCardenas);

    // Línea 12
    hashStr_Circle.put("Insurgentes Sur", InsurgentesSur);
    hashStr_Circle.put("Hospital 20 de Noviembre", H20Nov);
    hashStr_Circle.put("Parque de los Venados", ParqueVenados);
    hashStr_Circle.put("Eje Central", EjeCentral);
    }

    //**Función de apoyo para ini_PairHash pq me da hueva escribir bidireccional*/
    void ins_Par(Circle a, Circle b, Line l){
        hashCirclePair_Line.put(new Pair<>(a,b), l);
        hashCirclePair_Line.put(new Pair<>(b,a), l);
    }
    void ini_PairHash(){
        //Linea 1
        ins_Par(Observatorio, Tacubaya, Obs_Tac);
        ins_Par(Tacubaya, Juanacatlan, Tac_Jua);
        ins_Par(Juanacatlan, Chapultepec, Jua_Cha);
        ins_Par(Chapultepec, Sevilla, Cha_Sev);
        ins_Par(Sevilla, Insurgentes, Sev_Ins);
        ins_Par(Insurgentes, Cuauhtemoc, Ins_Cua);
        ins_Par(Cuauhtemoc, Balderas, Cua_Bal);
        
        //Linea 3
        ins_Par(Universidad, Copilco, Uni_Cop);
        ins_Par(Copilco,Quevedo,Cop_Quev);
        ins_Par(Quevedo, Viveros, Quev_Viv);
        ins_Par(Viveros, Coyoacan, Viv_Coy);
        ins_Par(Coyoacan, Zapata, Coy_Zap);
        ins_Par(Zapata, Division, Zap_Div);
        ins_Par(Division, Eugenia, Div_Eug);
        ins_Par(Eugenia, Etiopia, Eug_Eti);
        ins_Par(Etiopia, Medico, Eti_CenMed);
        ins_Par(Medico, Hospital, CenMed_HGen);
        ins_Par(Hospital, Niños, HGen_NH);
        ins_Par(Niños, Balderas, NH_Bal);
        ins_Par(Balderas, Juarez, Bal_Jua);

        //Linea 7
        ins_Par(Barranca, Mixcoac, Bar_Mix);
        ins_Par(Mixcoac, SanAntonio, Mix_SAntonio);
        ins_Par(SanAntonio, SanPedrodelosPinos, SAntonio_SPedro);
        ins_Par(SanPedrodelosPinos, Tacubaya, SPedro_Tac);
        ins_Par(Tacubaya, Constituyentes, Tac_Cons);
        ins_Par(Constituyentes, Auditorio, Con_Aud);
        ins_Par(Auditorio, Polanco, Aud_Pol);
        
        //Linea 9
        ins_Par(Tacubaya, Patriotismo, Tac_Pat);
        ins_Par(Patriotismo, Chilpancingo, Pal_Chil);
        ins_Par(Chilpancingo, Medico, Chil_CenMed);
        ins_Par(Medico, LazaroCardenas, CenMed_LazCar);

        //Linea 12
        ins_Par(Mixcoac, InsurgentesSur, Mix_Ins);
        ins_Par(InsurgentesSur, H20Nov, Ins_H20Nov);
        ins_Par(H20Nov, Zapata, H20Nov_Zap);
        ins_Par(Zapata, ParqueVenados, Zap_PVenad);
        ins_Par(ParqueVenados, EjeCentral, PVenad_Eje);


    }

    @FXML
    public void initialize(){
        textoETA.setVisible(false);
        MenuItem ninguna = new MenuItem("Ninguna (?)");
        MenuItem l1 = new MenuItem("L1: Rosa Mexicano");
        MenuItem l3 = new MenuItem("L3: Verde Olivo");
        MenuItem l7 = new MenuItem("L7: Naranja");
        MenuItem l9 = new MenuItem("L9: Café");
        MenuItem l12 = new MenuItem("L12: Dorado");
        menuLineas_INICIO.getItems().addAll(ninguna,l1,l3,l7,l9,l12);
        
        MenuItem ningunaf = new MenuItem("Ninguna (?)");
        MenuItem l1f = new MenuItem("L1: Rosa Mexicano");
        MenuItem l3f = new MenuItem("L3: Verde Olivo");
        MenuItem l7f = new MenuItem("L7: Naranja");
        MenuItem l9f = new MenuItem("L9: Café");
        MenuItem l12f = new MenuItem("L12: Dorado");
        menuLineas_FIN.getItems().addAll(ningunaf,l1f,l3f,l7f,l9f,l12f);

        ninguna.setOnAction(e -> estaciones_linea(0));
        l1.setOnAction(e -> estaciones_linea(1));
        l3.setOnAction(e -> estaciones_linea(3));
        l7.setOnAction(e -> estaciones_linea(7));
        l9.setOnAction(e -> estaciones_linea(9));
        l12.setOnAction(e -> estaciones_linea(12));

        ningunaf.setOnAction(e -> estaciones_lineaf(0));
        l1f.setOnAction(e -> estaciones_lineaf(1));
        l3f.setOnAction(e -> estaciones_lineaf(3));
        l7f.setOnAction(e -> estaciones_lineaf(7));
        l9f.setOnAction(e -> estaciones_lineaf(9));
        l12f.setOnAction(e -> estaciones_lineaf(12));

        ultimaSelec_INICIO = null;
        ultimaSelec_FIN = null;
        ini_hash();
        ini_PairHash();
        ini_arr();

        tramos = List.of(Obs_Tac,Tac_Jua,Jua_Cha,Cha_Sev,Sev_Ins,Ins_Cua,Cua_Bal ,
                        Uni_Cop,Cop_Quev,Quev_Viv,Viv_Coy,Coy_Zap,Zap_Div,Div_Eug,Eug_Eti,Eti_CenMed,CenMed_HGen,HGen_NH,NH_Bal,Bal_Jua,
                        Bar_Mix,Mix_SAntonio,SAntonio_SPedro,SPedro_Tac,Tac_Cons,Con_Aud,Aud_Pol,
                        Tac_Pat,Pal_Chil,Chil_CenMed,CenMed_LazCar,
                        Mix_Ins,Ins_H20Nov,H20Nov_Zap,Zap_PVenad,PVenad_Eje);

        for(Line tramo : tramos){
            tramo.setVisible(false);
        }



    }

    public void estaciones_linea(int linea){
        menuEstaciones_INICIO.getItems().clear();
        menuEstaciones_INICIO.setText("Estación");
        menuEstaciones_INICIO.setDisable(false);
        List<String> estaciones;
        switch(linea){
            case 1:
                menuLineas_INICIO.setText("L1: Rosa Mexicano");
                estaciones = List.of("Observatorio","Tacubaya","Juanacatlan","Chapultepec","Sevilla","Insurgentes","Cuauhtemoc","Balderas");
                break;
            case 3:
                menuLineas_INICIO.setText("L3: Verde Olivo");
                estaciones = List.of("Universidad","Copilco","M. A. De Quevedo","Viveros","Coyoacan","Zapata","Division del Norte","Eugenia","Etiopia","Centro Medico","Hospital General","Niños Heroes","Balderas","Juarez");
                break;
            case 7:
                menuLineas_INICIO.setText("L7: Naranja");
                estaciones = List.of("Barranca del Muerto","Mixcoac","San Antonio","San Pedro de los Pinos","Tacubaya","Constituyentes","Auditorio","Polanco");
                break;
            case 9:
                menuLineas_INICIO.setText("L9: Café");
                estaciones =  List.of("Tacubaya","Patriotismo","Chilpancingo","Centro Medico","Lazaro Cardenas");
                break;
            case 12:
                menuLineas_INICIO.setText("L12: Dorado");
                estaciones = List.of("Mixcoac","Insurgentes Sur","Hospital 20 de Noviembre","Zapata","Parque de los Venados","Eje Central");
                break;
            default:
                menuEstaciones_INICIO.setDisable(true);
                menuLineas_INICIO.setText("Línea");
                ultimaSelec_INICIO.setVisible(false);
                estaciones = null;
                return; 
        }

        for(String estacion : estaciones){
            MenuItem est_item = new MenuItem(estacion);
            est_item.setOnAction(e -> onEstacionSeleccionada_INICIO(estacion));
            menuEstaciones_INICIO.getItems().add(est_item);
        }

    }
    
    public void estaciones_lineaf(int linea){
        menuEstaciones_FIN.getItems().clear();
        menuEstaciones_FIN.setText("Estación");
        menuEstaciones_FIN.setDisable(false);
        List<String> estaciones;
        switch(linea){
            case 1:
                menuLineas_FIN.setText("L1: Rosa Mexicano");
                estaciones = List.of("Observatorio","Tacubaya","Juanacatlan","Chapultepec","Sevilla","Insurgentes","Cuauhtemoc","Balderas");
                break;
            case 3:
                menuLineas_FIN.setText("L3: Verde Olivo");
                estaciones = List.of("Universidad","Copilco","M. A. De Quevedo","Viveros","Coyoacan","Zapata","Division del Norte","Eugenia","Etiopia","Centro Medico","Hospital General","Niños Heroes","Balderas","Juarez");
                break;
            case 7:
                menuLineas_FIN.setText("L7: Naranja");
                estaciones = List.of("Barranca del Muerto","Mixcoac","San Antonio","San Pedro de los Pinos","Tacubaya","Constituyentes","Auditorio","Polanco");
                break;
            case 9:
                menuLineas_FIN.setText("L9: Café");
                estaciones =  List.of("Tacubaya","Patriotismo","Chilpancingo","Centro Medico","Lazaro Cardenas");
                break;
            case 12:
                menuLineas_FIN.setText("L12: Dorado");
                estaciones = List.of("Mixcoac","Insurgentes Sur","Hospital 20 de Noviembre","Zapata","Parque de los Venados","Eje Central");
                break;
            default:
                menuEstaciones_FIN.setDisable(true);
                menuLineas_FIN.setText("Línea");
                ultimaSelec_FIN.setVisible(false);
                estaciones = null;
                return; 
        }

        for(String estacion : estaciones){
            MenuItem est_item = new MenuItem(estacion);
            est_item.setOnAction(e -> onEstacionSeleccionada_FIN(estacion));
            menuEstaciones_FIN.getItems().add(est_item);
        }

    }

    public void onEstacionSeleccionada_INICIO(String estacion){
        if(ultimaSelec_INICIO!=null){
            ultimaSelec_INICIO.setVisible(false);
            estacion_SALIDA = null;
        }
        menuEstaciones_INICIO.setText(estacion);
        ultimaSelec_INICIO = hashStr_Circle.get(estacion);
        ultimaSelec_INICIO.setVisible(true);
        estacion_SALIDA = estacion;
    }

    public void onEstacionSeleccionada_FIN(String estacion){
        if(ultimaSelec_FIN!=null){
            ultimaSelec_FIN.setVisible(false);
            estacion_LLEGADA = null;
        }
        menuEstaciones_FIN.setText(estacion);
        ultimaSelec_FIN = hashStr_Circle.get(estacion);
        ultimaSelec_FIN.setVisible(true);
        estacion_LLEGADA = estacion;
    }
    
    @FXML
    private AnchorPane tfTitle;

    /**Para cada int se devuelve un String que describe una estación
     * (Se requiere para la comunicación con el backend)
     */
    public void ini_arr(){
        List<String> estaciones = List.of("Observatorio","Tacubaya","Juanacatlan","Chapultepec","Sevilla","Insurgentes","Cuauhtemoc","Balderas");
        estaciones = estaciones.reversed();
        int i = 0;
        for(String e : estaciones){
            id_to_estacion[i] = e;
            i++;
        }
        estaciones = List.of("Universidad","Copilco","M. A. De Quevedo","Viveros","Coyoacan","Zapata","Division del Norte","Eugenia","Etiopia","Centro Medico","Hospital General","Niños Heroes","Juarez");
        estaciones = estaciones.reversed();
        for(String e : estaciones){
            id_to_estacion[i] = e;
            i++;
        }
        estaciones = List.of("Barranca del Muerto","Mixcoac","San Antonio","San Pedro de los Pinos","Constituyentes","Auditorio","Polanco");
        estaciones = estaciones.reversed();
        for(String e : estaciones){
            id_to_estacion[i] = e;
            i++;
        }
        estaciones = List.of("Patriotismo","Chilpancingo","Lazaro Cardenas");
        estaciones = estaciones.reversed();
        for(String e : estaciones){
            id_to_estacion[i] = e;
            i++;
        }
        estaciones = List.of("Insurgentes Sur","Hospital 20 de Noviembre","Parque de los Venados","Eje Central");
        estaciones = estaciones.reversed();
        for(String e : estaciones){
            id_to_estacion[i] = e;
            i++;
        }
    }
    

    /**recorrido de muestra */
    List<Integer> recorrido = List.of(7,6,24,25,26,34,33,15,16,17);
    List<Integer> lastRecorrido=null;
    @FXML
    void calcularRecorrido(ActionEvent event) {
        limpiarRecorrido();
        int e_sald=-1,e_lleg=-1;
        if(estacion_LLEGADA==null || estacion_SALIDA == null){
            System.out.println("Request inválida\n");
            return;
        }
        for(int i=0;i<35;i++){
            if(estacion_SALIDA.compareTo(id_to_estacion[i])==0){
                e_sald = i;
            }
            if(estacion_LLEGADA.compareTo(id_to_estacion[i])==0){
                e_lleg = i;
            }
        }
        //TODO: REQUEST con 
        //(e_sald,e_lleg)
        System.out.println("REQUEST:\n");
        Float timeTaken=null;
        Pair<List<Integer>,Float> res;
        res = hacer_request(e_sald,e_lleg);
        recorrido=res.getKey();
        timeTaken=res.getValue();
        //TODO: después de request
        
        lastRecorrido = recorrido;
        Circle a,b;
        for(int i=0;i<recorrido.size()-1;i++){
            a=hashStr_Circle.get(id_to_estacion[recorrido.get(i)]);
            b=hashStr_Circle.get(id_to_estacion[recorrido.get(i+1)]);
            a.setVisible(true);b.setVisible(true);
            Line l = hashCirclePair_Line.get(new Pair<>(a,b));
            if(l==null){
                System.out.println("Conexión entre estaciones"+a+" y "+b+"no encontrada");
            }
            if(l!=null)
            l.setVisible(true);
        }
        textoETA.setText(timeTaken.toString()+" min");
        textoETA.setVisible(true);
    }

    void limpiarRecorrido(){
        if(lastRecorrido==null){
            return;
        }
        Circle a,b;
        for(int i=0;i<lastRecorrido.size()-1;i++){
            a=hashStr_Circle.get(id_to_estacion[lastRecorrido.get(i)]);
            b=hashStr_Circle.get(id_to_estacion[lastRecorrido.get(i+1)]);
            a.setVisible(false);b.setVisible(false);
            Line l = hashCirclePair_Line.get(new Pair<>(a,b));
            if(l==null){
                System.out.println("Conexión entre estaciones"+a+" y "+b+"no encontrada");
            }
            l.setVisible(false);
        }
    }


    Pair<List<Integer>,Float> hacer_request(int ini, int fin){
        try {
            ProcessBuilder processBuilder = new ProcessBuilder();
            System.out.println(ini);System.out.println(fin);
            processBuilder.command("cmd.exe","/c","py src/script.py "+ini+" "+fin);
            Process process = processBuilder.start();
            //BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream())); 
            String line;
            Float time;
            List<Integer> path = new ArrayList<Integer>();
            if((line = reader.readLine()) != null) {
                System.out.println(line);
                String[] parts;
                //line = parts[1];
                
                    parts = line.split(",");
                for (String string : parts) {
                    System.out.println(string);
                    path.add(Integer.parseInt(string));
                }
                
                System.out.println(path);
                if((line = reader.readLine())!=null){
                    time = Float.parseFloat(line);
                    
                    System.out.println(time);
                    Pair<List<Integer>,Float> res = new Pair<List<Integer>,Float>(path, time);
                    return res;
                }
            }   
        } catch (Exception e) {
            System.err.println(e);
        }

        return null;
    }

}