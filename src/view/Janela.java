package view;

import java.util.ArrayList;
import java.util.Arrays;
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
                return "CHAT:0:0";
            case 2:
                request = tecnicoTarefasFrame(); 
                return request;
            case 3:
                return "SAIR:0:0";
            default:
                return null;
    }

    }

    public static String tecnicoTarefasFrame() {
        String texto = """
            Selecione uma opção:
            1 - Listar Alunos
            2 - Listar Professores
            3 - Listar Técnicos
            4 - Ver conexões
            5 - Sair
            """;
            int opcao = Integer.parseInt(JOptionPane.showInputDialog(texto));
            switch (opcao) {
                case 1: 
                    return "7:0:0";
                case 2: 
                    return "8:0:0";
                case 3: 
                    return "9:0:0";
                case 4: 
                    return "13:0:0";
                case 5: 
                    return "SAIR:0:0";
                default:
                    return null;
        
    }
}

    public static void listagemFrame(String response) {
        String texto = "";
        String[] linhas = response.split(":");
        ArrayList<String> novaLista = new ArrayList<>(Arrays.asList(linhas));
        for (String linha : novaLista) {
            texto += (linha + "\n");
        }
        JOptionPane.showMessageDialog(null, texto);
    }

    public static String killFrame(String response) {
        String texto = "";
        String[] linhas = response.split(":");
        ArrayList<String> novaLista = new ArrayList<>(Arrays.asList(linhas));
        for (String linha : novaLista) {
            texto += (linha + "\n");
        }
        texto += "QUAL CONEXÃO VOCÊ QUER FECHAR?";
        String conexao = JOptionPane.showInputDialog(null, texto);
        conexao = conexao.replace(" ", "");
        return conexao;
    }
}