import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;
import view.Janela;

public class Teste {

    private static String request;
    private static String response;
    public static String user;

    public static void main(String[] args) {
        String host = JOptionPane.showInputDialog("Digite o IP do host:");
        if (host != null) {
            host = host.trim();
            try (Socket connection = new Socket(host, 12345);
                 BufferedReader entrada = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                 ObjectOutputStream saida = new ObjectOutputStream(connection.getOutputStream())) {

                user = "DESCONHECIDO";
                handleLogin(saida, entrada);
                handleChat(saida, entrada);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void handleLogin(ObjectOutputStream saida, BufferedReader entrada) throws IOException {
        while (true) {
            request = getRequestFromUser();
            if (request == null) {
                JOptionPane.showMessageDialog(null, "DIGITE UMA OPÇÃO VÁLIDA!", "Erro", JOptionPane.ERROR_MESSAGE);
            } else if (request.equals("SAIR")) {
                sendExitMessage(saida);
                System.exit(0);
            } else {
                sendRequest(saida, request);
                response = entrada.readLine();
                JOptionPane.showMessageDialog(null, response, "RESPONSE", JOptionPane.INFORMATION_MESSAGE);
                if (response.contains("logado")) {
                    user = Cliente.pegarUser(request);
                    sendUserEntryMessage(saida);
                    break;
                }
            }
        }
    }

    private static void handleChat(ObjectOutputStream saida, BufferedReader entrada) throws IOException {
        while (true) {
            request = getTecnicoRequest();
            if (request == null) {
                JOptionPane.showMessageDialog(null, "DIGITE UMA OPÇÃO VÁLIDA!", "Erro", JOptionPane.ERROR_MESSAGE);
            } else if (request.equals("SAIR:0:0")) {
                sendExitMessage(saida);
                System.exit(0);
            } else if (request.equals("CHAT:0:0")) {
                break;
            } else {
                handleRequest(saida, entrada);
            }
        }
        chatLoop(saida, entrada);
    }

    private static String getRequestFromUser() {
        Janela.request = null;
        return Janela.startFrame();
    }

    private static String getTecnicoRequest() {
        Janela.request = null;
        return Janela.tecnicoFrame();
    }

    private static void sendExitMessage(ObjectOutputStream saida) throws IOException {
        String aviso = user + " saiu do SERVIDOR";
        saida.writeObject("12:" + aviso + ":0");
    }

    private static void sendUserEntryMessage(ObjectOutputStream saida) throws IOException {
        String aviso = user + " entrou no CHAT";
        saida.writeObject("12:" + aviso + ":0");
    }

    private static void sendRequest(ObjectOutputStream saida, String request) throws IOException {
        saida.writeObject(request);
        saida.flush();
    }

    private static void handleRequest(ObjectOutputStream saida, BufferedReader entrada) throws IOException {
        sendRequest(saida, request);
        response = entrada.readLine();
        if (request.equals("11:0:0")) {
            request = Janela.killFrame(response);
            sendRequest(saida, request);
        } else {
            Janela.listagemFrame(response);
        }
    }

    private static void chatLoop(ObjectOutputStream saida, BufferedReader entrada) throws IOException {
        while (true) {
            sendRequest(saida, "10:a:a");
            response = entrada.readLine();
            System.out.println("CONVERSAS: " + response);
            String conversas = Cliente.pegarConversas(response);
            System.out.println(conversas);

            String mensagem = JOptionPane.showInputDialog(null, "Digite sua mensagem, ou digite 1 para sair", "CHAT", JOptionPane.INFORMATION_MESSAGE);
            sendRequest(saida, mensagem);

            String chat = String.join("\n", response.split(":"));
            System.out.println(chat);
        }
    }
}
