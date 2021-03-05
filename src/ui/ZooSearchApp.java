package ui;

import core.Osetrovatele;
import dao.ZooDAO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ZooSearchApp extends JFrame {

    private JPanel contentPane;
    private JTextField jmenoTextField;
    private JButton btnSearch;
    private JScrollPane scrollPane;
    private JTable table;

    private ZooDAO zooDAO;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ZooSearchApp frame = new ZooSearchApp();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ZooSearchApp() {

        try {
            zooDAO = new ZooDAO();
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);
        }

        setTitle("Zoo Search App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel panel = new JPanel();
        FlowLayout flowLayout = (FlowLayout) panel.getLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        contentPane.add(panel, BorderLayout.NORTH);

        JLabel lblEnterLastName = new JLabel("Enter jmeno");
        panel.add(lblEnterLastName);

        jmenoTextField = new JTextField();
        panel.add(jmenoTextField);
        jmenoTextField.setColumns(10);

        btnSearch = new JButton("Hledat");
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    String jmeno = jmenoTextField.getText();

                    List<Osetrovatele> osetrovateles = null;

                    if (jmeno != null && jmeno.trim().length() > 0) {
                        osetrovateles = zooDAO.searchOsetrovatele(jmeno);
                    } else {
                        osetrovateles = zooDAO.getAllOsetrovatele();
                    }

                    ZooTableModel model = new ZooTableModel(osetrovateles);

                    table.setModel(model);

                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(ZooSearchApp.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        panel.add(btnSearch);

        scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);

        table = new JTable();
        scrollPane.setViewportView(table);
    }

}
