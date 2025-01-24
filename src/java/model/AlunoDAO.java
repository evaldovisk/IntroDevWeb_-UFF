package model;

import entidade.Aluno;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AlunoDAO {

    public ArrayList<Aluno> getAlunoAll() throws SQLException {

        ArrayList<Aluno> resultAluno = new ArrayList<>();
        Conexao conexao = new Conexao();

        PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM alunos");

        ResultSet resultado = sql.executeQuery();

        if (resultado != null) {

            while (resultado.next()) {
                Aluno aluno = new Aluno();

                aluno.setId(Integer.parseInt(resultado.getString("id")));
                aluno.setNome(resultado.getString("nome"));
                aluno.setEmail(resultado.getString("email"));
                aluno.setCelular(resultado.getString("celular"));
                aluno.setCpf(resultado.getString("cpf"));
                aluno.setSenha(resultado.getString("senha"));
                aluno.setEndereco(resultado.getString("endereco"));
                aluno.setCidade(resultado.getString("cidade"));
                aluno.setBairro(resultado.getString("bairro"));
                aluno.setCep(resultado.getString("cep"));

                resultAluno.add(aluno);
            }
        }

        return resultAluno;
    }

    public Aluno getAlunoById(int id) throws SQLException {
        Aluno aluno = null;
        Conexao conexao = new Conexao();
        String sql = "SELECT * FROM alunos WHERE id = ?";
        PreparedStatement stmt = conexao.getConexao().prepareStatement(sql);
        stmt.setInt(1, id);

        ResultSet resultado = stmt.executeQuery();

        if (resultado != null && resultado.next()) {
            aluno = new Aluno();
            aluno.setId(resultado.getInt("id"));
            aluno.setNome(resultado.getString("nome"));
            aluno.setEmail(resultado.getString("email"));
            aluno.setCelular(resultado.getString("celular"));
            aluno.setCpf(resultado.getString("cpf"));
            aluno.setSenha(resultado.getString("senha"));
            aluno.setEndereco(resultado.getString("endereco"));
            aluno.setCidade(resultado.getString("cidade"));
            aluno.setBairro(resultado.getString("bairro"));
            aluno.setCep(resultado.getString("cep"));
        }

        return aluno;
    }

    public boolean updateAluno(Aluno aluno) throws SQLException {
        Conexao conexao = new Conexao();
        String sql = "UPDATE alunos SET nome = ?, email = ?, celular = ?, cpf = ?, senha = ?, endereco = ?, cidade = ?, bairro = ?, cep = ? WHERE id = ?";
        PreparedStatement stmt = conexao.getConexao().prepareStatement(sql);

        stmt.setString(1, aluno.getNome());
        stmt.setString(2, aluno.getEmail());
        stmt.setString(3, aluno.getCelular());
        stmt.setString(4, aluno.getCpf());
        stmt.setString(5, aluno.getSenha());
        stmt.setString(6, aluno.getEndereco());
        stmt.setString(7, aluno.getCidade());
        stmt.setString(8, aluno.getBairro());
        stmt.setString(9, aluno.getCep());
        stmt.setInt(10, aluno.getId());

        int rowsAffected = stmt.executeUpdate();
        return rowsAffected > 0;
    }

    public boolean deleteAluno(int id) throws SQLException {
        Conexao conexao = new Conexao();
        String sql = "DELETE FROM alunos WHERE id = ?";
        PreparedStatement stmt = conexao.getConexao().prepareStatement(sql);
        stmt.setInt(1, id);

        int rowsAffected = stmt.executeUpdate();
        return rowsAffected > 0;
    }

    public boolean addAluno(Aluno aluno) throws SQLException {
        Conexao conexao = new Conexao();
        String sql = "INSERT INTO alunos (nome, email, celular, cpf, senha, endereco, cidade, bairro, cep) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = conexao.getConexao().prepareStatement(sql);

        stmt.setString(1, aluno.getNome());
        stmt.setString(2, aluno.getEmail());
        stmt.setString(3, aluno.getCelular());
        stmt.setString(4, aluno.getCpf());
        stmt.setString(5, aluno.getSenha());
        stmt.setString(6, aluno.getEndereco());
        stmt.setString(7, aluno.getCidade());
        stmt.setString(8, aluno.getBairro());
        stmt.setString(9, aluno.getCep());

        int rowsAffected = stmt.executeUpdate();
        return rowsAffected > 0;
    }

    public Aluno getAlunoByCpfSenha(Aluno aluno) throws SQLException {
        Aluno alunoObtido = null;
        Conexao conexao = new Conexao();
        String sql = "SELECT * FROM alunos WHERE cpf = ? AND senha = ? LIMIT 1";
        PreparedStatement stmt = conexao.getConexao().prepareStatement(sql);
        stmt.setString(1, aluno.getCpf());
        stmt.setString(2, aluno.getSenha());

        ResultSet resultado = stmt.executeQuery();

        if (resultado != null && resultado.next()) {
            alunoObtido = new Aluno();
            alunoObtido.setId(resultado.getInt("id"));
            alunoObtido.setNome(resultado.getString("nome"));
            alunoObtido.setEmail(resultado.getString("email"));
            alunoObtido.setCelular(resultado.getString("celular"));
            alunoObtido.setCpf(resultado.getString("cpf"));
            alunoObtido.setSenha(resultado.getString("senha"));
            alunoObtido.setEndereco(resultado.getString("endereco"));
            alunoObtido.setCidade(resultado.getString("cidade"));
            alunoObtido.setBairro(resultado.getString("bairro"));
            alunoObtido.setCep(resultado.getString("cep"));
        }

        return alunoObtido;
    }

}
