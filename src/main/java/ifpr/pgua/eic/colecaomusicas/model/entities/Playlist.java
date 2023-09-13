package ifpr.pgua.eic.colecaomusicas.model.entities;

import java.util.ArrayList;

public class Playlist {
    private int id;
    private String nome;
    ArrayList<Musica> musica = new ArrayList<>();
    
    

    public Playlist(String nome, ArrayList<Musica> musica) {
        this.nome = nome;
        this.musica = musica;
    }


    public Playlist(int id, String nome, ArrayList<Musica> musica) {
        this.id = id;
        this.nome = nome;
        this.musica = musica;
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


    public ArrayList<Musica> getMusica() {
        return musica;
    }


    public void setMusica(ArrayList<Musica> musica) {
        this.musica = musica;
    }

}
