import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import view.ChatFrame;
import view.Janela;

public class ClienteSocket {
    public static void main(String[] args) throws InterruptedException, IOException {

        String host = JOptionPane.showInputDialog("Digite o IP do host:");
        host = host.replace(" ", "");
        Socket connection = new Socket(host, 12345);
        BufferedReader entrada = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        ObjectOutputStream saida = new ObjectOutputStream(connection.getOutputStream());

        String user = "DESCONHECIDO";
        String request;    
        String response;

        while (true) {
            while (true) {  // tela inicial 
                Janela.request = null;
                request = Janela.startFrame();
                System.out.println(request);
                if (request == null) {
                    JOptionPane.showMessageDialog(null, "DIGITE UMA OPÇÃO VÁLIDA!", "Erro", JOptionPane.ERROR_MESSAGE);
                } else if (request.equals("SAIR")) {
                    String aviso = user + " saiu do SERVIDOR";
                    saida.writeObject("12:" + aviso + ":0");
                    System.exit(0);
                } else {
                    break;
                }
            }
            while (true) { // tela com a response
                saida.writeObject(request);
                saida.flush();
                response = entrada.readLine();
                JOptionPane.showMessageDialog(null, response, "RESPONSE", JOptionPane.INFORMATION_MESSAGE);
                break;        
            }

            if (response.contains("logado")) {
                user = Cliente.pegarUser(request);
                String aviso = user + " entrou no CHAT";
                saida.writeObject("12:" + aviso + ":0");
                break;
            }
        }

        if (response.equals("200 - Tecnico logado")) { // janela do técnico
            while (true) {
                Janela.request = null;
                saida.flush();
                request = Janela.tecnicoFrame();
                System.out.println(
                    request
                );
                if (request == null) {
                    JOptionPane.showMessageDialog(null, "DIGITE UMA OPÇÃO VÁLIDA!", "Erro", JOptionPane.ERROR_MESSAGE);
                } else if (request.contains("SAIR")){
                    String aviso = user + " saiu do CHAT";
                    saida.writeObject("12:" + aviso + ":0");
                    System.exit(0);
                } else if (request.contains("CHAT")) {
                    break;
                } else if (request.equals("13:0:0")) {
                    for (int i = 0; i < 2; i++) {
                        saida.writeObject(request);
                        saida.flush();
                        response = entrada.readLine();
                    }
                    request = Janela.killFrame(response);
                    saida.writeObject("14:" + request + ":0");
                    saida.flush();
                } else {
                    for (int i = 0; i < 2; i++) {
                        saida.writeObject(request);
                        saida.flush();
                        response = entrada.readLine();
                    }
                    Janela.listagemFrame(response);
                    saida.flush();
                }
            }
        }
        for (int i = 0; i < 2; i++) {
            saida.flush();
            saida.writeObject("10:0:0");
            saida.flush();
            response = entrada.readLine();
        }
        String conversas = Cliente.pegarConversas(response);
        System.out.println(conversas);
        while (true) {
            request = JOptionPane.showInputDialog("Digite sua mensagem, ou digite SAIR para sair:");
            String pedaco = "%s -> %s".formatted(user, request);
            System.out.println(pedaco);
            saida.flush();
            saida.writeObject("11:%s:0".formatted(pedaco));
            saida.flush();
            response = entrada.readLine();
            if (request.equals("SAIR")) {
                String aviso = user + " saiu do CHAT";
                    saida.writeObject("12:" + aviso + ":0");
                    System.exit(0);
            }
            }
        }
}