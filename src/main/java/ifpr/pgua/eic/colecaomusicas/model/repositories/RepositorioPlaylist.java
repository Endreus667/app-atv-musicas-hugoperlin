package ifpr.pgua.eic.colecaomusicas.model.repositories;
import java.util.List;

import com.github.hugoperlin.results.Resultado;
import ifpr.pgua.eic.colecaomusicas.model.daos.PlaylistDAO;
import ifpr.pgua.eic.colecaomusicas.model.entities.Musica;
import ifpr.pgua.eic.colecaomusicas.model.entities.Playlist;

public class RepositorioPlaylist {
    private PlaylistDAO dao;

    public RepositorioPlaylist(PlaylistDAO dao) {
        this.dao = dao;
    }

    public Resultado cadastrarPlaylist(String nome, List<Musica> musica) {
   
        if (nome == null || nome.isEmpty()) {
            return Resultado.erro("Nome inválido!");
        }

       
        Playlist playlist = new Playlist(nome, musica);

      
        return dao.criar(playlist);
    }

    public static Resultado listarPlaylist() {
        return null;
    }
}
