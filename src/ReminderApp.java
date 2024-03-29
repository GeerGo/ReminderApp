import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

public class ReminderApp extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField txtEvent;
    private JTextField txtTime;
    private JTable table_1;

    private JLabel lblTimer;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        // Run the program
        EventQueue.invokeLater(() -> {
            try {
                ReminderApp frame = new ReminderApp();

                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the frame.
     */
    private ReminderApp() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 787, 407);
        setLocationRelativeTo(null);
        setTitle("Reminder");

        // Create menu bar at the top with "Exit" and "Help"
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(Color.WHITE);
        setJMenuBar(menuBar);

        final JMenuItem itmExit = new JMenuItem("Exit");
        itmExit.addActionListener(arg0 -> {
            if (arg0.getSource() == itmExit) {
                int x = JOptionPane.showConfirmDialog(
                        rootPane, "Are you sure?", "Confirmation", JOptionPane.OK_CANCEL_OPTION);
                if (x == 0) {
                    System.out.println(x);
                    System.exit(0);
                }
            }
        });

        JMenu mnHelp = new JMenu("Help");
        menuBar.add(mnHelp);

        JMenuItem itmAbout = new JMenuItem("About");
        itmAbout.addActionListener(e -> {
            JFrame f = new JFrame("About");
            f.setVisible(true);
            f.setSize(300, 300);
            f.setLocationRelativeTo(null);
            f.getContentPane().setLayout(new FlowLayout());
            JLabel lblabout1 = new JLabel();
            JLabel lblabout3 = new JLabel();

            lblabout1.setText("Ashley Barkworth");
            lblabout3.setText("University of British Columbia");
            f.getContentPane().add(lblabout1);
            f.getContentPane().add(lblabout3);


        });

        mnHelp.add(itmAbout);
        itmAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));

        JPanel minPanel = new JPanel();
        minPanel.setForeground(Color.LIGHT_GRAY);
        minPanel.setBackground(Color.DARK_GRAY);
        minPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(minPanel);
        minPanel.setLayout(null);

        JPanel creatEventPanel = new JPanel();
        creatEventPanel.setBackground(Color.DARK_GRAY);
        creatEventPanel.setBorder(new TitledBorder(null, "Create New Event", TitledBorder.CENTER, TitledBorder.TOP, null, Color.ORANGE));
        creatEventPanel.setName("");
        creatEventPanel.setBounds(10, 24, 279, 239);
        minPanel.add(creatEventPanel);
        creatEventPanel.setLayout(null);

        JLabel lblEventName = new JLabel("Event Name");
        lblEventName.setForeground(Color.WHITE);
        lblEventName.setFont(new Font("Arial Black", Font.PLAIN, 12));
        lblEventName.setBounds(10, 23, 80, 23);
        creatEventPanel.add(lblEventName);

        txtEvent = new JTextField();
        txtEvent.setBackground(Color.LIGHT_GRAY);
        txtEvent.setBounds(108, 21, 161, 29);
        creatEventPanel.add(txtEvent);
        txtEvent.setColumns(10);

        JLabel lblDate = new JLabel("Date");
        lblDate.setForeground(Color.WHITE);
        lblDate.setFont(new Font("Arial Black", Font.PLAIN, 12));
        lblDate.setBounds(10, 74, 46, 29);
        creatEventPanel.add(lblDate);

        final JDateChooser dateCh = new JDateChooser();
        dateCh.setForeground(Color.LIGHT_GRAY);
        dateCh.setBorder(null);
        dateCh.setBackground(Color.LIGHT_GRAY);
        dateCh.setBounds(108, 74, 161, 29);
        creatEventPanel.add(dateCh);

        JLabel lblTime = new JLabel("Time");
        lblTime.setForeground(Color.WHITE);
        lblTime.setFont(new Font("Arial Black", Font.PLAIN, 12));
        lblTime.setBounds(10, 137, 46, 29);
        creatEventPanel.add(lblTime);

        JButton btnNewButton = new JButton("Save");
        btnNewButton.addActionListener(arg0 -> {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String date = dateFormat.format(dateCh.getDate());
            String event = txtEvent.getText();
            String time = txtTime.getText();

            if (event.isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "Please Enter An Event.");
                txtEvent.requestFocus();
            } else {
                DefaultTableModel model = (DefaultTableModel) table_1.getModel();
                model.addRow(new Object[]{event, date, time});
            }
        });

        btnNewButton.setBackground(Color.LIGHT_GRAY);
        btnNewButton.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnNewButton.setBounds(180, 194, 89, 23);
        creatEventPanel.add(btnNewButton);

        txtTime = new JTextField();
        txtTime.setFont(new Font("Tahoma", Font.PLAIN, 18));
        txtTime.setHorizontalAlignment(SwingConstants.CENTER);
        txtTime.setText("00:00:00");
        txtTime.setBounds(108, 139, 161, 27);
        creatEventPanel.add(txtTime);
        txtTime.setColumns(10);

        JButton btnNewButton_1 = new JButton("Edit");
        btnNewButton_1.setBackground(Color.LIGHT_GRAY);
        btnNewButton_1.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
        btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnNewButton_1.setBounds(81, 194, 89, 23);
        creatEventPanel.add(btnNewButton_1);
        btnNewButton_1.addActionListener(arg0 -> {
            DefaultTableModel model = (DefaultTableModel) table_1.getModel();
            model.setValueAt(txtEvent.getText(), table_1.getSelectedRow(), 0);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            model.setValueAt(dateFormat.format(dateCh.getDate()), table_1.getSelectedRow(), 1);
            model.setValueAt(txtTime.getText(), table_1.getSelectedRow(), 2);
        });


        JPanel recentEventPanel = new JPanel();
        recentEventPanel.setBackground(Color.DARK_GRAY);
        recentEventPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Recent Events", TitledBorder.CENTER, TitledBorder.TOP, null, Color.ORANGE));
        recentEventPanel.setBounds(299, 24, 462, 295);
        minPanel.add(recentEventPanel);

        JScrollPane scrollPane = new JScrollPane();

        JPanel panel = new JPanel();
        panel.setBackground(Color.DARK_GRAY);
        GroupLayout gl_recentEventPanel = new GroupLayout(recentEventPanel);
        gl_recentEventPanel.setHorizontalGroup(
                gl_recentEventPanel.createParallelGroup(Alignment.LEADING)
                        .addGroup(Alignment.TRAILING, gl_recentEventPanel.createSequentialGroup()
                                .addGroup(gl_recentEventPanel.createParallelGroup(Alignment.TRAILING)
                                        .addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
                                        .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE))
                                .addContainerGap())
        );
        gl_recentEventPanel.setVerticalGroup(
                gl_recentEventPanel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_recentEventPanel.createSequentialGroup()
                                .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(panel, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(21, Short.MAX_VALUE))
        );

        table_1 = new JTable();
        table_1.setFont(new Font("Tahoma", Font.BOLD, 12));
        table_1.setModel(new DefaultTableModel(
                new Object[][]{
                },
                new String[]{
                        "EVENT", "DATE", "TIME"
                }
        ));
        table_1.getColumnModel().getColumn(0).setPreferredWidth(119);
        table_1.getColumnModel().getColumn(1).setPreferredWidth(76);
        scrollPane.setViewportView(table_1);
        panel.setLayout(null);

        JButton btnNewButton_2 = new JButton("Clear");
        btnNewButton_2.addActionListener(arg0 -> clear());
        btnNewButton_2.setBounds(33, 21, 89, 23);
        panel.add(btnNewButton_2);

        JButton btnNewButton_3 = new JButton("Delete");
        btnNewButton_3.addActionListener(arg0 -> {
            DefaultTableModel model = (DefaultTableModel) table_1.getModel();
            if (table_1.getSelectedRow() == -1) {
                if (table_1.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(rootPane, "No data to be deleted.!");
                } else {
                    JOptionPane.showMessageDialog(rootPane, "You must select an item.!");
                }
            } else {
                model.removeRow(table_1.getSelectedRow());
            }
        });
        btnNewButton_3.setBounds(311, 21, 89, 23);
        panel.add(btnNewButton_3);
        recentEventPanel.setLayout(gl_recentEventPanel);

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(Color.DARK_GRAY);
        panel_1.setBounds(10, 263, 279, 56);
        minPanel.add(panel_1);
        panel_1.setLayout(null);

        lblTimer = new JLabel("00:00:00");
        lblTimer.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        lblTimer.setFont(new Font("Tw Cen MT", Font.PLAIN, 18));
        lblTimer.setHorizontalAlignment(SwingConstants.CENTER);
        lblTimer.setForeground(Color.GREEN);
        lblTimer.setBounds(0, 0, 279, 56);
        panel_1.add(lblTimer);

        // Clock
        javax.swing.Timer t = new javax.swing.Timer(1000, new Listener());
        t.start();
    }

    class Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Calendar rightnow = Calendar.getInstance();
            int hour = rightnow.get(Calendar.HOUR_OF_DAY);
            if (hour > 12) {
                hour = hour % 12;
            }
            if (hour == 0) {
                hour = 12;
            }
            int min = rightnow.get(Calendar.MINUTE);
            int sec = rightnow.get(Calendar.SECOND);
            lblTimer.setText("" + hour + ":" + min + ":" + sec);

        }
    }

    private void clear() {
        DefaultTableModel model = (DefaultTableModel) table_1.getModel();
        if (table_1.getRowCount() > 0) {
            for (int i = table_1.getRowCount() - 1; i > -1; i--) {
                model.removeRow(i);
            }
        }
    }
}