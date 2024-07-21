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
    public static String user;

    public static void main(String[] args) throws InterruptedException {
        try {
            String host = JOptionPane.showInputDialog("Digite o IP do host:");
            host = host.replace(" ", "");
            Socket connection = new Socket(host, 12345);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            ObjectOutputStream saida = new ObjectOutputStream(connection.getOutputStream());
            user = "DESCONHECIDO";
            
            while (true) {
                while (true) {
                    while (true) {
                        Janela.request = null;
                        saida.flush();
                        request = Janela.startFrame();
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
                    while (true) {
                        saida.writeObject(request);
                        saida.flush();
                        response = entrada.readLine();
                        JOptionPane.showMessageDialog(null, response, "RESPONSE", JOptionPane.INFORMATION_MESSAGE);
                        if (response.contains("logado") || response.contains("400")) {
                            break;
                        }
                    }
                    if (response.contains("logado")) {
                        user = Cliente.pegarUser(request);
                        String aviso = user + " entrou no CHAT";
                        saida.writeObject("12:" + aviso + ":0");
                        break;
                    }
                }
                if (response.equals("200 - Técnico logado")) {
                    while (true) {
                        Janela.request = null;
                        saida.flush();
                        request = Janela.tecnicoFrame();
                        if (request == null) {
                            JOptionPane.showMessageDialog(null, "DIGITE UMA OPÇÃO VÁLIDA!", "Erro", JOptionPane.ERROR_MESSAGE);
                        } else if (request.equals("SAIR:0:0")) {
                            String aviso = user + " saiu do CHAT";
                            saida.writeObject("12:" + aviso + ":0");
                            System.exit(0);
                        } else if (request.equals("CHAT:0:0")){
                            break;
                        } else if (request.equals("11:0:0")){ 
                            saida.writeObject(request);
                            saida.flush();
                            response = entrada.readLine();
                            request = Janela.killFrame(response);
                            saida.writeObject(request);
                            saida.flush();

                        } else {
                            saida.writeObject(request);
                            saida.flush();
                            response = entrada.readLine();
                            Janela.listagemFrame(response);
                        }
                    }
                }
                while (true) {
                    
                }

            }

            //connection.close();


            //         } while(!request.equals("SAIR"));
            //     }

            // } while(!request.equals("SAIR"));

        } catch (IOException e) {
            e.printStackTrace();
        }
}
}