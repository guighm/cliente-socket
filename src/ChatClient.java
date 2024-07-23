import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JOptionPane;
import view.Janela;

public class ChatClient {
    public static void main(String[] args) throws InterruptedException, IOException {

        String host = JOptionPane.showInputDialog("Digite o IP do host:");
        host = host.replace(" ", "");
        Socket connection = new Socket(host, 12345);
        BufferedReader entrada = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        PrintWriter saida = new PrintWriter(connection.getOutputStream(), true);

        String user = "DESCONHECIDO";
        String request;    
        String response;

        while (true) {
            while (true) {  // tela inicial 
                Janela.request = null;
                request = Janela.startFrame();
                if (request == null) {
                    JOptionPane.showMessageDialog(null, "DIGITE UMA OPÇÃO VÁLIDA!", "Erro", JOptionPane.ERROR_MESSAGE);
                } else if (request.equals("SAIR")) {
                    System.exit(0);
                } else {
                    break;
                }
            }
            while (true) { // tela com a response
                saida.println(request);
                saida.flush();
                response = entrada.readLine();
                JOptionPane.showMessageDialog(null, response, "RESPONSE", JOptionPane.INFORMATION_MESSAGE);
                break;        
            }

            if (response.contains("logado")) {
                user = Cliente.pegarUser(request);
                break;
            }
        }

        if (response.equals("200 - Tecnico logado")) { // janela do técnico
            while (true) {
                Janela.request = null;
                saida.flush();
                request = Janela.tecnicoFrame();
                if (request == null) {
                    JOptionPane.showMessageDialog(null, "DIGITE UMA OPÇÃO VÁLIDA!", "Erro", JOptionPane.ERROR_MESSAGE);
                } else if (request.contains("SAIR")){
                    System.exit(0);
                } else if (request.contains("CHAT")) {
                    break;
                } else if (request.equals("10:0:0")) {
                    saida.println(request);
                    saida.flush();
                    response = entrada.readLine();
                    request = Janela.killFrame(response);
                    if (request.contains("SAIR")) {
                        continue;
                    }
                    saida.println("11:" + request + ":0");
                    saida.flush();
                } else {
                    saida.println(request);
                    saida.flush();
                    response = entrada.readLine();
                    System.out.println(response);
                    Janela.listagemFrame(response);
                    saida.flush();
                    }
                }
            }

        saida.println("13:" + user + ":0");
        saida.flush();
        request = "12:0:0";
        saida.println(request);
        saida.flush();
        response = entrada.readLine();
        String conversas = Cliente.pegarConversas(response);
        System.out.println(conversas);
        String texto = """
        A seguir, digite sua mensagem!!
        Para sair, digite 1
        Para ver a lista de usuários, digite 2
        """;
        while (true) { // chat
            String mensagem = JOptionPane.showInputDialog(null, texto, user, JOptionPane.INFORMATION_MESSAGE);
            if (mensagem.equals("1")) {
                saida.println("14:" + user + ":0");
                saida.flush();
                System.exit(0);
            } else if (mensagem.equals("2")) {
                saida.println("15:0:0");
                saida.flush();
                response = entrada.readLine();
                Janela.listagemFrame(response);
            } else {
                String mensagemChat = "%s -> %s".formatted(user, mensagem);
                saida.println("16:%s:0".formatted(mensagemChat));
                saida.flush();
                response = entrada.readLine();
                System.out.println(response);
            }
        }
    }
}