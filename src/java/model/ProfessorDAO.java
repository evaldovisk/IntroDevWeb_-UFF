package model;

import entidade.Professor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProfessorDAO {
    
    public ArrayList<Professor> getProfessorAll() throws SQLException {
        ArrayList<Professor> resultProfessor = new ArrayList<>();
        Conexao conexao = new Conexao();

        PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM professores");

        ResultSet resultado = sql.executeQuery();

        if (resultado != null) {
            while (resultado.next()) {
                Professor professor = new Professor();

                professor.setId(resultado.getInt("id"));
                professor.setNome(resultado.getString("nome"));
                professor.setEmail(resultado.getString("email"));
                professor.setCpf(resultado.getString("cpf"));
                professor.setSenha(resultado.getString("senha"));

                resultProfessor.add(professor);
            }
        }

        return resultProfessor;
    }

    public Professor getProfessorById(int id) throws SQLException {
        Professor professor = null;
        Conexao conexao = new Conexao();
        String sql = "SELECT * FROM professores WHERE id = ?";
        PreparedStatement stmt = conexao.getConexao().prepareStatement(sql);
        stmt.setInt(1, id);

        ResultSet resultado = stmt.executeQuery();

        if (resultado != null && resultado.next()) {
            professor = new Professor();

            professor.setId(resultado.getInt("id"));
            professor.setNome(resultado.getString("nome"));
            professor.setEmail(resultado.getString("email"));
            professor.setCpf(resultado.getString("cpf"));
            professor.setSenha(resultado.getString("senha"));
        }

        return professor;
    }

    public boolean updateProfessor(Professor professor) throws SQLException {
        Conexao conexao = new Conexao();
        String sql = "UPDATE professores SET nome = ?, email = ?, cpf = ?, senha = ? WHERE id = ?";
        PreparedStatement stmt = conexao.getConexao().prepareStatement(sql);

        stmt.setString(1, professor.getNome());
        stmt.setString(2, professor.getEmail());
        stmt.setString(3, professor.getCpf());
        stmt.setString(4, professor.getSenha());
        stmt.setInt(5, professor.getId());

        int rowsAffected = stmt.executeUpdate();
        return rowsAffected > 0;
    }

    public boolean deleteProfessor(int id) throws SQLException {
        Conexao conexao = new Conexao();
        String sql = "DELETE FROM professores WHERE id = ?";
        PreparedStatement stmt = conexao.getConexao().prepareStatement(sql);
        stmt.setInt(1, id);

        int rowsAffected = stmt.executeUpdate();
        return rowsAffected > 0;
    }

    public boolean addProfessor(Professor professor) throws SQLException {
        Conexao conexao = new Conexao();
        String sql = "INSERT INTO professores (nome, email, cpf, senha) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = conexao.getConexao().prepareStatement(sql);

        stmt.setString(1, professor.getNome());
        stmt.setString(2, professor.getEmail());
        stmt.setString(3, professor.getCpf());
        stmt.setString(4, professor.getSenha());


        int rowsAffected = stmt.executeUpdate();
        return rowsAffected > 0;
    }
    
}