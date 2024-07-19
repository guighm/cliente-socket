package uea.trabalho.cliente.view;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import uea.trabalho.servidor.model.aluno.Aluno;
import uea.trabalho.servidor.model.aluno.AlunoDAO;
import uea.trabalho.servidor.model.professor.Professor;
import uea.trabalho.servidor.model.professor.ProfessorDAO;
import uea.trabalho.servidor.model.tecnico.Tecnico;
import uea.trabalho.servidor.model.tecnico.TecnicoDAO;

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
                senha.replace(" ", "");
                String titulacao = titulacaoField.getText().replace(" ", "");
                int anoDeIngresso = Integer.parseInt(ingressoField.getText());

                if (alunoOption.isSelected()){

                    dispose();
                    Aluno aluno1 = new Aluno(login, senha, anoDeIngresso);
                    new AlunoDAO().cadastrarAluno(aluno1);

                } else if (professorOption.isSelected()) {

                    dispose();
                    Professor professor1 = new Professor(login, senha, titulacao);
                    new ProfessorDAO().cadastrarProfessor(professor1);

                } else if (tecnicoOption.isSelected()) {

                    dispose();
                    Tecnico tecnico1 = new Tecnico(login, senha);
                    new TecnicoDAO().cadastrarTecnico(tecnico1);
                    
                } else {
                    JOptionPane.showMessageDialog(null, "NENHUMA OPÇÃO SELECIONADA");
                }
            }
        });
}
}