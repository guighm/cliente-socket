package uea.trabalho.servidor.model.tecnico;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import javax.swing.JOptionPane;

import uea.trabalho.servidor.db.Conexao;

public class TecnicoDAO {
    public void cadastrarTecnico(Tecnico t){
        String sql = "INSERT INTO TECNICO (LOGIN, SENHA) VALUES (?, ?)";

        PreparedStatement ps = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, t.getLogin());
            ps.setString(2, t.getSenha());

            ps.execute();
            ps.close();
        } catch (SQLIntegrityConstraintViolationException e) {
            JOptionPane.showMessageDialog(null, "Técnico já cadastrado!", "ERRO", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void autenticarTecnico(String login, String senha) {
        String sql = "SELECT * FROM TECNICO WHERE LOGIN = ?";

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
        String sql = "DELETE FROM TECNICO";
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
