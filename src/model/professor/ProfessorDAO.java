package uea.trabalho.servidor.model.professor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import javax.swing.JOptionPane;

import uea.trabalho.servidor.db.Conexao;

public class ProfessorDAO {
    public void cadastrarProfessor(Professor p) {
        String sql = "INSERT INTO PROFESSOR (LOGIN, SENHA, TITULACAO) VALUES (?, ?, ?)";
        PreparedStatement ps = null;
        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, p.getLogin());
            ps.setString(2, p.getSenha());
            ps.setString(3, p.getTitulacao());

            ps.execute();
            ps.close();
            
        } catch (SQLIntegrityConstraintViolationException e) {
            JOptionPane.showMessageDialog(null, "Professor já cadastrado!", "ERRO", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void autenticarProfessor(String login, String senha) {
    String sql = "SELECT * FROM PROFESSOR WHERE LOGIN = ?";

    PreparedStatement ps = null;
    ResultSet rs = null;
    String loginBuscado = null;
    String senhaBuscada = null;

    try {
        ps = Conexao.getConexao().prepareStatement(sql);
        ps.setString(1, login);
        rs = ps.executeQuery();

        while (rs.next()) {
            loginBuscado = rs.getString("login");
            senhaBuscada = rs.getString("senha");
        }

        if (loginBuscado == null) {
            JOptionPane.showMessageDialog(null, "Não há registro!");
        } else if (loginBuscado.equals(login) && senhaBuscada.equals(senha)) {
                JOptionPane.showMessageDialog(null, "Login realizado com sucesso");
        } else {
            JOptionPane.showMessageDialog(null, "Senha Incorreta");
        }

        ps.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    public void limparTabela() {
        String sql = "DELETE FROM PROFESSOR";
        PreparedStatement ps = null;
        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
