package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;

public class ChatFrame extends JFrame{
    private JTextArea chatArea;
    private JTextField messageField;
    private JButton sendButton;
    private JScrollPane scrollPane;
    private String user;

    public ChatFrame(String lista, String user) {
        this.user = user;
        setTitle("WhatsApp 2.0");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        scrollPane = new JScrollPane(chatArea);
        add(scrollPane, BorderLayout.CENTER);

        JPanel messagePanel = new JPanel();
        messagePanel.setLayout(new BorderLayout());

        messageField = new JTextField();
        messagePanel.add(messageField, BorderLayout.CENTER);

        sendButton = new JButton("Enviar");
        messagePanel.add(sendButton, BorderLayout.EAST);
        add(messagePanel, BorderLayout.SOUTH);

        chatArea.append(lista);

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        messageField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        }
        );
    }

    public String sendMessage() {
        String mensagem = messageField.getText().trim();
        if (!mensagem.isEmpty()) {
            chatArea.append("%s: ".formatted(this.user) + mensagem + "\n");
            messageField.setText("");
            return mensagem;
        }
        return null;
    }
    
}