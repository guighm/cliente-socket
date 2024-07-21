package view;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class Janela {

    public static String request;

    public static String startFrame() {

        String texto = """
        Selecione uma opção:
        1 - Fazer Login
        2 - Fazer Cadastro
        3 - Sair
        """;
        int opcao = Integer.parseInt(JOptionPane.showInputDialog(texto));
        switch (opcao) {

            case 1:
            LoginFrame login = new LoginFrame();
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    login.setVisible(true);
                }
            });
            while (request == null) {
                request = login.getRequest();
            }
            return request;

            case 2: 
                CadastroFrame cadastro = new CadastroFrame();
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        cadastro.setVisible(true);
                    }
                });
                while (request == null) {
                    request = cadastro.getRequest();
                }
                return request;
            case 3:
                return "SAIR";
            default:
                JOptionPane.showMessageDialog(null, "DIGITE UMA OPÇÃO VÁLIDA!", "Erro", JOptionPane.ERROR_MESSAGE);
                opcao = Integer.parseInt(JOptionPane.showInputDialog(texto));
                return null;
        }
    }

    public static String tecnicoFrame() {
        String texto = """
        Selecione uma opção:
        1 - Entrar no chat
        2 - Logar no Servidor
        3 - Sair
        """;
        int opcao = Integer.parseInt(JOptionPane.showInputDialog(texto));
        switch (opcao) {
            case 1:

                return null;
            case 2:
                return null;
            case 3:
                return "SAIR";
            default:
                JOptionPane.showMessageDialog(null, "DIGITE UMA OPÇÃO VÁLIDA!", "Erro", JOptionPane.ERROR_MESSAGE);
                opcao = Integer.parseInt(JOptionPane.showInputDialog(texto));
                return null;

    }

    }

    public static String tecnicoTarefasFrame() {
        String texto = """
            Selecione uma opção:
            1 - Listar Alunos
            2 - Listar Professores
            3 - Listar Técnicos
            4 - Visualizar Status dos Usuários
            5 - Apagar conexão
            6 - Sair
            """;
            int opcao = Integer.parseInt(JOptionPane.showInputDialog(texto));
            switch (opcao) {
                case 1: 
                    return null;
                case 2: 
                    return null;
                case 3: 
                    return null;
                case 4: 
                    return null;
                case 5: 
                    return null;
                case 6: 
                    return null;
                default:
                    JOptionPane.showMessageDialog(null, "DIGITE UMA OPÇÃO VÁLIDA!", "Erro", JOptionPane.ERROR_MESSAGE);
                    opcao = Integer.parseInt(JOptionPane.showInputDialog(texto));
                    return null;
        
    }
}
}