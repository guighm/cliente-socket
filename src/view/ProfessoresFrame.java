package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ProfessoresFrame extends JFrame {
    private JTextArea area;
    private JScrollPane scrollPane;
    private JButton sairButton;

    public ProfessoresFrame(String lista) {
        setTitle("Lista de Professores");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        area = new JTextArea();
        area.setEditable(false);
        scrollPane = new JScrollPane(area);
        add(scrollPane, BorderLayout.CENTER);

        sairButton = new JButton("Sair");
        add(sairButton, BorderLayout.SOUTH);

        area.append(lista);
        
        sairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
