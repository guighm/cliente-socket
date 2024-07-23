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
                } else if (request.equals("13:0:0")) {
                    for (int i = 0; i < 2; i++) {
                        saida.println(request);
                        saida.flush();
                        response = entrada.readLine();
                    }
                    request = Janela.killFrame(response);
                    if (request.contains("SAIR")) {
                        System.exit(0);
                    }
                    saida.println("14:" + request + ":0");
                    saida.flush();
                } else {
                    saida.println(request);
                    saida.flush();
                    response = entrada.readLine();
                    }
                    Janela.listagemFrame(response);
                    saida.flush();
                }
            }

        user = Cliente.pegarUser(request);
        String aviso = user + " entrou no CHAT";
        saida.println("12:" + aviso + ":0");
        
        while (true) { // chat
            JOptionPane.showMessageDialog(null, "NÃO É TÉCNICO", "RESPONSE", JOptionPane.INFORMATION_MESSAGE);
        }
}
}



            //         }
            //     }
                // while (true) {
                //     saida.flush();
                //     saida.writeObject("10:0:0");
                //     response = entrada.readLine();
                //     if (response != null) {
                //         System.out.println("CONVERSAS: " + response);
                //         String conversas = Cliente.pegarConversas(response);
                //         System.out.println(conversas);
                //         String texto = "Digite sua mensagem, ou digite 1 para sair";
                //         String mensagem = JOptionPane.showInputDialog(null, texto, "CHAT", JOptionPane.INFORMATION_MESSAGE);
                //         saida.writeObject("11:%s:0".formatted(mensagem));
                //         saida.flush();
                //         String chat = "";
                //         String[] linhas = response.split(":");
                //         ArrayList<String> novaLista = new ArrayList<>(Arrays.asList(linhas));
                //         for (String linha : novaLista) {
                //             chat += (linha + "\n");
                //         }
                //         System.out.println(chat);
                //     }
                // }

