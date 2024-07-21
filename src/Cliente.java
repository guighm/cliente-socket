public class Cliente {
    public static String pegarUser(String request) {
        String[] partes = request.split(":");
        String user = partes[1];
        return user;
    }
}
