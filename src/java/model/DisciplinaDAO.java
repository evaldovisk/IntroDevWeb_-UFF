package model;

import entidade.Disciplina;
import java.sql.*;
import java.util.ArrayList;

public class DisciplinaDAO {

    public void Inserir(Disciplina disciplina) throws Exception {
        Conexao conexao = new Conexao();
        try {
            String sqlQuery = "INSERT INTO disciplina (nome, requisito, ementa, carga_horaria) VALUES (?, ?, ?, ?)";
            PreparedStatement sql = conexao.getConexao().prepareStatement(sqlQuery);
            sql.setString(1, disciplina.getNome());
            sql.setString(2, disciplina.getRequisito());
            sql.setString(3, disciplina.getEmenta());
            sql.setInt(4, disciplina.getCargaHoraria());
            sql.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir disciplina: " + e.getMessage());
        } finally {
            conexao.closeConexao();
        }
    }

    public ArrayList<Disciplina> ListaDeDisciplinas() throws Exception {
        ArrayList<Disciplina> disciplinas = new ArrayList<>();
        Conexao conexao = new Conexao();
        try {
            String sqlQuery = "SELECT * FROM disciplina ORDER BY nome";
            PreparedStatement sql = conexao.getConexao().prepareStatement(sqlQuery);
            ResultSet resultado = sql.executeQuery();
            while (resultado.next()) {
                Disciplina disciplina = new Disciplina();
                disciplina.setId(resultado.getInt("id"));
                disciplina.setNome(resultado.getString("nome"));
                disciplina.setRequisito(resultado.getString("requisito"));
                disciplina.setEmenta(resultado.getString("ementa"));
                disciplina.setCargaHoraria(resultado.getInt("carga_horaria"));
                disciplinas.add(disciplina);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar disciplinas: " + e.getMessage());
        } finally {
            conexao.closeConexao();
        }
        return disciplinas;
    }

    public Disciplina getDisciplina(int id) throws Exception {
        Disciplina disciplina = null;
        Conexao conexao = new Conexao();
        try {
            String sqlQuery = "SELECT * FROM disciplina WHERE id = ?";
            PreparedStatement sql = conexao.getConexao().prepareStatement(sqlQuery);
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();
            if (resultado.next()) {
                disciplina = new Disciplina();
                disciplina.setId(resultado.getInt("id"));
                disciplina.setNome(resultado.getString("nome"));
                disciplina.setRequisito(resultado.getString("requisito"));
                disciplina.setEmenta(resultado.getString("ementa"));
                disciplina.setCargaHoraria(resultado.getInt("carga_horaria"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao obter disciplina: " + e.getMessage());
        } finally {
            conexao.closeConexao();
        }
        return disciplina;
    }

    public void Alterar(Disciplina disciplina) throws Exception {
        Conexao conexao = new Conexao();
        try {
            String sqlQuery = "UPDATE disciplina SET nome = ?, requisito = ?, ementa = ?, carga_horaria = ? WHERE id = ?";
            PreparedStatement sql = conexao.getConexao().prepareStatement(sqlQuery);
            sql.setString(1, disciplina.getNome());
            sql.setString(2, disciplina.getRequisito());
            sql.setString(3, disciplina.getEmenta());
            sql.setInt(4, disciplina.getCargaHoraria());
            sql.setInt(5, disciplina.getId());
            sql.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao alterar disciplina: " + e.getMessage());
        } finally {
            conexao.closeConexao();
        }
    }

    public void Excluir(Disciplina disciplina) throws Exception {
        Conexao conexao = new Conexao();
        try {
            String sqlQuery = "DELETE FROM disciplina WHERE id = ?";
            PreparedStatement sql = conexao.getConexao().prepareStatement(sqlQuery);
            sql.setInt(1, disciplina.getId());
            sql.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir disciplina: " + e.getMessage());
        } finally {
            conexao.closeConexao();
        }
    }
}
