package view;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastroFrame extends JFrame{
    private JLabel loginLabel;
    private JTextField loginField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JLabel titulacaoLabel;
    private JTextField titulacaoField;
    private JLabel ingressoLabel;
    private JTextField ingressoField;
    private JRadioButton alunoOption;
    private JRadioButton professorOption;
    private JRadioButton tecnicoOption;
    private JButton loginButton;
    private ButtonGroup group;
    private String request;

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public CadastroFrame() {
        setTitle("Fazer Cadastro");
        setSize(300, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(13, 1, 10, 10));

        loginLabel = new JLabel("Escolha um login:");
        loginField = new JTextField();
        passwordLabel = new JLabel("Escolha uma senha:");
        passwordField = new JPasswordField();
        titulacaoLabel = new JLabel("Digite sua titulação (caso seja professor):");
        titulacaoField = new JTextField();
        ingressoLabel = new JLabel("Digite seu ano de ingresso (caso seja aluno):");
        ingressoField = new JTextField();
        alunoOption = new JRadioButton("Sou aluno");
        professorOption = new JRadioButton("Sou professor");
        tecnicoOption = new JRadioButton("Sou técnico");
        loginButton = new JButton("Fazer Cadastro");

        group = new ButtonGroup();
        group.add(alunoOption);
        group.add(professorOption);
        group.add(tecnicoOption);

        add(loginLabel);
        add(loginField);
        add(passwordLabel);
        add(passwordField);
        add(titulacaoLabel);
        add(titulacaoField);
        add(ingressoLabel);
        add(ingressoField);
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
                senha = senha.replace(" ", "");
                
                if (alunoOption.isSelected()){
                    
                    String anoDeIngresso = ingressoField.getText().replace(" ", "");
                    dispose();
                    setRequest("1:%s:%s:%s".formatted(login, senha, anoDeIngresso));
                    
                } else if (professorOption.isSelected()) {
                    
                    String titulacao = titulacaoField.getText().replace(" ", "");
                    dispose();
                    setRequest("2:%s:%s:%s".formatted(login, senha, titulacao));

                } else if (tecnicoOption.isSelected()) {

                    dispose();
                    setRequest("3:%s:%s".formatted(login, senha));
                    
                } else {
                    JOptionPane.showMessageDialog(null, "NENHUMA OPÇÃO SELECIONADA");
                }
            }
        });
}
}