import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import view.StartFrame;

public class ClienteSocket {
    public static void main(String[] args) throws IOException {
        
        String host = JOptionPane.showInputDialog("Digite o IP do host:");
        host.replace(" ", "");
        Socket connection = new Socket(host, 12345);

        ObjectOutputStream saida = new ObjectOutputStream(connection.getOutputStream());

        String mensagem = "";

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new StartFrame().setVisible(true);
            }
        });
}
}