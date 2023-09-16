/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.exam;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.exam.model.Model;
import it.polito.tdp.exam.model.Successore;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

    private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnCreaGrafo"
    private Button btnCreaGrafo; // Value injected by FXMLLoader

    @FXML // fx:id="btnDettagli"
    private Button btnDettagli; // Value injected by FXMLLoader

    @FXML // fx:id="btnSimula"
    private Button btnSimula; // Value injected by FXMLLoader

    @FXML // fx:id="cmbAnno"
    private ComboBox<Integer> cmbAnno; // Value injected by FXMLLoader

    @FXML // fx:id="cmbSquadra"
    private ComboBox<String> cmbSquadra; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML // fx:id="txtTifosi"
    private TextField txtTifosi; // Value injected by FXMLLoader

    @FXML
    void handleCreaGrafo(ActionEvent event) {
    	this.txtResult.clear();
    	String input=this.cmbSquadra.getValue();
    	if(input==null) {
    		this.txtResult.setText("Selezionare una Squadra!");
    		return;
    	}
    	this.model.buildGraph(input);
    	this.txtResult.setText("Grafo creato!\n");
    	this.txtResult.appendText("V: "+this.model.getVsize()+"\n");
    	this.txtResult.appendText("A: "+this.model.getEsize()+"\n");
    	this.cmbAnno.getItems().setAll(this.model.getNodes());
    }
    
    @FXML
    void handleDettagli(ActionEvent event) {
    	this.txtResult.clear();
    	Integer input=this.cmbAnno.getValue();
    	if(input==null) {
    		this.txtResult.setText("Selezionare un anno!");
    		return;
    	}
    	for(Successore s:this.model.getSuccessorEdges(input)) {
    		this.txtResult.appendText(input+"<--->"+s.getE()+" peso= : "+s.getPeso()+"\n");
    	}
    }

    @FXML
    void handleSimula(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnDettagli != null : "fx:id=\"btnDettagli\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbAnno != null : "fx:id=\"cmbAnno\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbSquadra != null : "fx:id=\"cmbSquadra\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTifosi != null : "fx:id=\"txtTifosi\" was not injected: check your FXML file 'Scene.fxml'.";

    }

    public void setModel(Model model) {
        this.model = model;
        this.cmbSquadra.getItems().setAll(this.model.getTeams());
    }

}
