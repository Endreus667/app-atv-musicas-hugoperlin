package ifpr.pgua.eic.colecaomusicas.model.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.model.entities.Musica;
import ifpr.pgua.eic.colecaomusicas.model.entities.Playlist;

public class JDBCPlaylistDAO implements PlaylistDAO{

    private static final String INSERTSQLPlaylist = "INSERT INTO playlist(nome) VALUES (?)";
    private static final String INSERTSQLplaylistmusicas = "INSERT INTO playlistmusicas(id_playlist , id_musica ) VALUES (?,?)";
    private static final String SELECTSQL = "SELECT * FROM playlistmusicas";

    private FabricaConexoes fabrica;

    public JDBCPlaylistDAO(FabricaConexoes fabrica) {
        this.fabrica = fabrica;
    }

    @Override
    public Resultado criar(Playlist playlist) {
        try (Connection con = fabrica.getConnection()) {

            PreparedStatement pstm = con.prepareStatement(INSERTSQLPlaylist, Statement.RETURN_GENERATED_KEYS);
            
            pstm.setString(1, playlist.getNome());
            

            int ret = pstm.executeUpdate();

            if(ret == 1){
                ResultSet rs = pstm.getGeneratedKeys();
                rs.next();
                int id = rs.getInt(1);

                playlist.setId(id);
                
                PreparedStatement pstplaylistmusicas = con.prepareStatement(INSERTSQLplaylistmusicas, Statement.RETURN_GENERATED_KEYS);
                for(Musica m: playlist.getMusica()){
                    pstplaylistmusicas.setInt(1, playlist.getId());
                    pstplaylistmusicas.setInt(2, m.getId());

                    pstplaylistmusicas.executeUpdate();
                }
                return Resultado.sucesso("Playlist cadastrada", playlist);
            }


            return Resultado.erro("Erro desconhecido!");


        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }

    }

    @Override
    public Resultado listar() {
       try(Connection con = fabrica.getConnection()){
        PreparedStatement pstm = con.prepareStatement(SELECTSQL);

        ResultSet rs = pstm.executeQuery();

        ArrayList<Playlist> lista = new ArrayList<>();
        while(rs.next()){
            String nome = rs.getString("nome");
            ArrayList<Musica> musicas = new ArrayList<>();

            //iremos buscar artista e genero através do repositório
            Playlist playlist = new Playlist(nome, musicas);

            lista.add(playlist);
        }
        return Resultado.sucesso("Playlist listadas", lista);
       }
       catch(SQLException e){
        return Resultado.erro(e.getMessage());
       }
    }

    @Override
    public Resultado atualizar(int id, Playlist nova) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'atualizar'");
    }

    @Override
    public Resultado deletar(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deletar'");
    }
    
}
