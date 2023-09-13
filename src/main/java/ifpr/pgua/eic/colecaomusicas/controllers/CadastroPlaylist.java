package ifpr.pgua.eic.colecaomusicas.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.text.PlainDocument;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.App;
import ifpr.pgua.eic.colecaomusicas.model.daos.FabricaConexoes;
import ifpr.pgua.eic.colecaomusicas.model.entities.Genero;
import ifpr.pgua.eic.colecaomusicas.model.entities.Playlist;
import ifpr.pgua.eic.colecaomusicas.model.repositories.RepositorioGeneros;
import ifpr.pgua.eic.colecaomusicas.model.repositories.RepositorioPlaylist;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Alert.AlertType;

public class CadastroPlaylist implements Initializable{

    @FXML
    private ListView<Playlist> lstplaylist;

    private RepositorioPlaylist repositorioPlaylist;

   
    public CadastroPlaylist(RepositorioPlaylist repositorioPlaylist) {
        this.repositorioPlaylist = repositorioPlaylist;
    }

    @FXML
    void voltar(ActionEvent event) {
        App.popScreen();
    }

    @FXML
    void mostrarSelecionados(){
        List<Playlist> selecionados = lstplaylist.getSelectionModel().getSelectedItems();
        
        String str = "";

        for(Playlist playlioPlaylist:selecionados){
            str += playlioPlaylist.getNome()+";";
        }

        Alert alert = new Alert(AlertType.INFORMATION, str);
        alert.showAndWait();
    
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        lstplaylist.getItems().clear();
        
        lstplaylist.getSelectionModel()
              .setSelectionMode(SelectionMode.MULTIPLE);
        
        
        Resultado r = RepositorioPlaylist.listarPlaylist();

        if(r.foiSucesso()){
            List<Playlist> lista = (List)r.comoSucesso().getObj();
            lstplaylist.getItems().addAll(lista);
        }else{
            Alert alert = new Alert(AlertType.ERROR, r.getMsg());
            alert.showAndWait();
        }
    
    }

}