import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.JOptionPane;
import view.Janela;


public class ClienteSocket {
    private static String request;
    private static String response;

    public static void main(String[] args) throws InterruptedException {
        try {
            String host = JOptionPane.showInputDialog("Digite o IP do host:");
            host = host.replace(" ", "");
            Socket connection = new Socket(host, 12345);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            ObjectOutputStream saida = new ObjectOutputStream(connection.getOutputStream());
            
            do{
                do {
                    Janela.request = null;
                    saida.flush();
                    request = Janela.startFrame();
                    
                    if (request == null) {
                        response = "400";
                        continue;
                    }

                    saida.writeObject(request);
                    response = entrada.readLine();
                    JOptionPane.showMessageDialog(null, response, "ATENÇÃO", JOptionPane.INFORMATION_MESSAGE);
                } while (request == null && response.contains("400") || response.contains("cadatrado com sucesso"));

                if (response.equals("200 - Técnico logado")) {
                    do {

                    } while(!request.equals("SAIR"));
                }

            } while(!request.equals("SAIR"));

        } catch (IOException e) {
            e.printStackTrace();
        }
}
}