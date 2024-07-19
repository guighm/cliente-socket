package uea.trabalho.cliente.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import uea.trabalho.servidor.model.aluno.AlunoDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class LoginFrame extends JFrame{
    private JLabel loginLabel;
    private JTextField loginField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JRadioButton alunoOption;
    private JRadioButton professorOption;
    private JRadioButton tecnicoOption;
    private JButton loginButton;
    private ButtonGroup group;

    public LoginFrame() {
        setTitle("Fazer Login");
        setSize(250, 330);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(9, 1, 10, 10));

        loginLabel = new JLabel("Login:");
        loginField = new JTextField();
        passwordLabel = new JLabel("Senha:");
        passwordField = new JPasswordField();
        alunoOption = new JRadioButton("Sou aluno");
        professorOption = new JRadioButton("Sou professor");
        tecnicoOption = new JRadioButton("Sou técnico");
        loginButton = new JButton("Fazer Login");

        group = new ButtonGroup();
        group.add(alunoOption);
        group.add(professorOption);
        group.add(tecnicoOption);

        add(loginLabel);
        add(loginField);
        add(passwordLabel);
        add(passwordField);
        add(alunoOption);
        add(professorOption);
        add(tecnicoOption);
        add(loginButton);
        add(new JLabel());

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = loginField.getText().replace(" ", "").toLowerCase();
                String senha = new String(passwordField.getPassword());
                senha.replace(" ", "");
                if (alunoOption.isSelected()){
                    dispose();
                    new AlunoDAO().autenticarAluno(login, senha);
                } else if (professorOption.isSelected()) {
                    JOptionPane.showMessageDialog(null, "PROFESSOR");
                } else if (tecnicoOption.isSelected()) {
                    JOptionPane.showMessageDialog(null, "TÉCNICO");
                } else {
                    JOptionPane.showMessageDialog(null, "NENHUMA OPÇÃO SELECIONADA");
                }
            }
        });
    }
}