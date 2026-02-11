import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ToDoApp extends JFrame {
    private DefaultListModel<String> taskListModel;
    private JList<String> taskList;
    private JTextField taskField;
    private JButton addButton, deleteButton;
    private JLabel heading;

    public ToDoApp() {
        setTitle("To-Do App");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        taskField = new JTextField(20);
        addButton = new JButton("Add Task");
        deleteButton = new JButton("Delete Task");
        heading = new JLabel("ToDoList Application");

        JPanel headJPanel = new JPanel();
        headJPanel.add(heading);
        heading.setForeground(new Color(225, 25, 152));
        add(headJPanel,BorderLayout.NORTH);

        JPanel inputPanel = new JPanel();
        inputPanel.add(taskField);
        addButton.setBackground(new Color(60, 179, 113)); 
        addButton.setForeground(Color.WHITE);             
        addButton.setFocusPainted(false);               
        inputPanel.add(addButton);
        deleteButton.setBackground(new Color(220, 20, 60)); 
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFocusPainted(false);
        inputPanel.add(deleteButton);
        inputPanel.setSize(new Dimension(600, 50));
        inputPanel.setBackground(new Color(240, 248, 255));
        add(inputPanel, BorderLayout.CENTER);

        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        JScrollPane scrollPane = new JScrollPane(taskList);
        taskList.setBackground(new Color(245, 255, 250));
        taskList.setForeground(Color.BLACK);              
        add(scrollPane, BorderLayout.SOUTH);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String task = taskField.getText().trim();
                if (!task.isEmpty()) {
                    taskListModel.addElement(task);
                    taskField.setText("");
                } else {
                    JOptionPane.showMessageDialog(ToDoApp.this, "Task cannot be empty.");
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = taskList.getSelectedIndex();
                if (selectedIndex != -1) {
                    taskListModel.remove(selectedIndex);
                } else {
                    JOptionPane.showMessageDialog(ToDoApp.this, "Select a task to delete.");
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ToDoApp().setVisible(true);
        });
    }
}