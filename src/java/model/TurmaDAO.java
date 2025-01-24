package model;

import controller.admin.DTO.TurmaRequest;
import entidade.Aluno;
import entidade.Turma;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TurmaDAO {

    public Map<String, List<Turma>> getTurmasGroupByCodigo() throws SQLException {
        Map<String, List<Turma>> turmasMap = new HashMap<>();
        Conexao conexao = new Conexao();

        String sql = "SELECT * FROM turmas ORDER BY codigo_turma";
        PreparedStatement stmt = conexao.getConexao().prepareStatement(sql);

        ResultSet resultado = stmt.executeQuery();

        if (resultado != null) {
            while (resultado.next()) {
                Turma turma = new Turma();
                turma.setId(resultado.getInt("id"));
                turma.setProfessorId(resultado.getInt("professor_id"));
                turma.setDisciplinaId(resultado.getInt("disciplina_id"));
                turma.setAlunoId(resultado.getInt("aluno_id"));
                turma.setCodigoTurma(resultado.getString("codigo_turma"));
                turma.setNota(resultado.getInt("nota"));

                String codigoTurma = turma.getCodigoTurma();
                turmasMap.computeIfAbsent(codigoTurma, k -> new ArrayList<>()).add(turma);
            }
        }

        return turmasMap;
    }

    public Turma getTurmaByCodigo(String codigoTurma) throws SQLException {
        Conexao conexao = new Conexao();
        Turma turma = null;
        String sql = "SELECT * FROM turmas WHERE codigo_turma = ?";

        PreparedStatement stmt = conexao.getConexao().prepareStatement(sql);

        stmt.setString(1, codigoTurma);

        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            turma = new Turma();
            turma.setCodigoTurma(rs.getString("codigo_turma"));
            turma.setProfessorId(rs.getInt("professor_id"));
            turma.setDisciplinaId(rs.getInt("disciplina_id"));
            turma.setAlunoId(rs.getInt("aluno_id"));
            turma.setNota((int) rs.getInt("nota"));
        }

        return turma;
    }

    public List<Turma> getTurmasByCodigo(String codigoTurma) throws SQLException {
        List<Turma> resultTurma = new ArrayList<>();
        Conexao conexao = new Conexao();

        String sql = "SELECT * FROM turmas WHERE codigo_turma = ?";
        PreparedStatement stmt = conexao.getConexao().prepareStatement(sql);
        stmt.setString(1, codigoTurma);

        ResultSet resultado = stmt.executeQuery();

        if (resultado != null) {
            while (resultado.next()) {
                Turma turma = new Turma();
                turma.setId(resultado.getInt("id"));
                turma.setProfessorId(resultado.getInt("professor_id"));
                turma.setDisciplinaId(resultado.getInt("disciplina_id"));
                turma.setAlunoId(resultado.getInt("aluno_id"));
                turma.setCodigoTurma(resultado.getString("codigo_turma"));
                turma.setNota(resultado.getInt("nota"));

                resultTurma.add(turma);
            }
        }

        return resultTurma;
    }

    public ArrayList<Turma> getTurmasAll() throws SQLException {
        ArrayList<Turma> resultTurma = new ArrayList<>();
        Conexao conexao = new Conexao();

        PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM turmas");

        ResultSet resultado = sql.executeQuery();

        if (resultado != null) {
            while (resultado.next()) {
                Turma turma = new Turma();

                turma.setId(resultado.getInt("id"));
                turma.setProfessorId(resultado.getInt("professor_id"));
                turma.setDisciplinaId(resultado.getInt("disciplina_id"));
                turma.setAlunoId(resultado.getInt("aluno_id"));
                turma.setCodigoTurma(resultado.getString("codigo_turma"));
                turma.setNota(resultado.getInt("nota"));

                resultTurma.add(turma);
            }
        }

        return resultTurma;
    }

    public Turma getTurmaById(int id) throws SQLException {
        Turma turma = null;
        Conexao conexao = new Conexao();
        String sql = "SELECT * FROM turmas WHERE id = ?";
        PreparedStatement stmt = conexao.getConexao().prepareStatement(sql);
        stmt.setInt(1, id);

        ResultSet resultado = stmt.executeQuery();

        if (resultado != null && resultado.next()) {
            turma = new Turma();
            turma.setId(resultado.getInt("id"));
            turma.setProfessorId(resultado.getInt("professor_id"));
            turma.setDisciplinaId(resultado.getInt("disciplina_id"));
            turma.setAlunoId(resultado.getInt("aluno_id"));
            turma.setCodigoTurma(resultado.getString("codigo_turma"));
            turma.setNota(resultado.getInt("nota"));
        }

        return turma;
    }

    public boolean updateTurma(Turma turma) throws SQLException {
        Conexao conexao = new Conexao();
        String sql = "UPDATE turmas SET professor_id = ?, disciplina_id = ?, aluno_id = ?, nota = ? WHERE id = ?";
        PreparedStatement stmt = conexao.getConexao().prepareStatement(sql);
        stmt.setInt(1, turma.getProfessorId());
        stmt.setInt(2, turma.getDisciplinaId());
        stmt.setInt(3, turma.getAlunoId());
        stmt.setDouble(4, turma.getNota());
        stmt.setInt(5, turma.getId() );

        int rowsAffected = stmt.executeUpdate();
        return rowsAffected > 0;

    }

    public boolean deleteTurma(int id) throws SQLException {
        Conexao conexao = new Conexao();
        String sql = "DELETE FROM turmas WHERE id = ?";
        PreparedStatement stmt = conexao.getConexao().prepareStatement(sql);
        stmt.setInt(1, id);

        int rowsAffected = stmt.executeUpdate();
        return rowsAffected > 0;
    }

    public boolean addTurma(Turma turma) throws SQLException {
        Conexao conexao = new Conexao();
        String sql = "INSERT INTO turmas (professor_id, disciplina_id, aluno_id, codigo_turma, nota) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt = conexao.getConexao().prepareStatement(sql);

        stmt.setInt(1, turma.getProfessorId());
        stmt.setInt(2, turma.getDisciplinaId());
        stmt.setInt(3, turma.getAlunoId());
        stmt.setString(4, turma.getCodigoTurma());
        stmt.setInt(5, turma.getNota());

        int rowsAffected = stmt.executeUpdate();
        return rowsAffected > 0;
    }

    public boolean existeTurmaComCodigo(String codigoTurma) throws SQLException {
        Conexao conexao = new Conexao();
        String sql = "SELECT COUNT(*) FROM turmas WHERE codigo_turma = ?";
        PreparedStatement stmt = conexao.getConexao().prepareStatement(sql);
        stmt.setString(1, codigoTurma);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getInt(1) > 0;
        }

        return false;
    }

    public boolean excluirTurmaPorCodigo(String codigoTurma) throws SQLException {
        Conexao conexao = new Conexao();
        String sql = "DELETE FROM turmas WHERE codigo_turma = ?";
        PreparedStatement stmt = conexao.getConexao().prepareStatement(sql);
        stmt.setString(1, codigoTurma);
        int rowsAffected = stmt.executeUpdate();
        return rowsAffected > 0;

    }

}
