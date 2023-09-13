package ifpr.pgua.eic.colecaomusicas.model.entities;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private int id;
    private String nome;
    List<Musica> musicas = new ArrayList<>();
    

    public Playlist(String nome, List<Musica> musicas) {
        this.nome = nome;
        this.musicas = musicas;
     
    }

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getNome() {
        return nome;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }


    public List<Musica> getMusica() {
        return musicas;
    }


    public void setMusica(ArrayList<Musica> musica) {
        this.musicas = musica;
    }

}
