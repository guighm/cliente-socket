import java.util.ArrayList;
import java.util.Arrays;

public class Cliente {
    public static String pegarUser(String request) {
        String[] partes = request.split(":");
        String user = partes[1];
        return user;
    }
    public static String pegarConversas(String response) {
        String[] partes = response.split(":");
        ArrayList<String> novaLista = new ArrayList<>(Arrays.asList(partes));
        String chat = "";
        for (String linha : novaLista) {
            chat += "%s \n".formatted(linha);
        }
        return chat;
    }
}
