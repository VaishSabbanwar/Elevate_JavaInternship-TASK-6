import javax.swing.* ;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ToDoApp extends JFrame implements ActionListener {

    // --- GUI Components ---
    private JTextField taskInputField;
    private JButton addButton;
    private JButton deleteButton;
    private JList<String> taskList;
    // DefaultListModel is used to manage the data displayed in the JList
    private DefaultListModel<String> listModel;

    public ToDoApp() {
        // Set up the main JFrame (the window)
        setTitle("Java To-Do List");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);
        setLayout(new BorderLayout(10, 10)); // Use BorderLayout for main structure
        
        // --- 1. Initialize Components ---
        listModel = new DefaultListModel<>();
        taskList = new JList<>(listModel);
        
        // Add scrollability to the task list
        JScrollPane scrollPane = new JScrollPane(taskList);
        
        taskInputField = new JTextField(20); // 20 columns wide
        addButton = new JButton("Add Task");
        deleteButton = new JButton("Delete Task");

        // --- 2. Set up Event Handling ---
        // Register the current class (this) as the ActionListener for both buttons
        addButton.addActionListener(this);
        deleteButton.addActionListener(this);
        
        // --- 3. Set up Layouts (Panels) ---

        // Input Panel: Holds the text field and the Add button at the top
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // FlowLayout for simple arrangement
        inputPanel.add(taskInputField);
        inputPanel.add(addButton);

        // Control Panel: Holds the Delete button at the bottom
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        controlPanel.add(deleteButton);

        // --- 4. Add Panels to JFrame ---
        add(inputPanel, BorderLayout.NORTH);  // Input at the top
        add(scrollPane, BorderLayout.CENTER); // Task list in the center, uses the most space
        add(controlPanel, BorderLayout.SOUTH); // Controls at the bottom

        // Make the window visible
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            addTask();
        } else if (e.getSource() == deleteButton) {
            deleteTask();
        }
    }

    private void addTask() {
        String task = taskInputField.getText().trim();

        if (!task.isEmpty()) {
            // Add the task text to the list model
            listModel.addElement(task);
            // Clear the input field after adding
            taskInputField.setText("");
        } else {
            // Show a simple error message if the input field is empty
            JOptionPane.showMessageDialog(this, 
                                          "Task cannot be empty!", 
                                          "Input Error", 
                                          JOptionPane.WARNING_MESSAGE);
        }
    }

    private void deleteTask() {
        int selectedIndex = taskList.getSelectedIndex();
        
        if (selectedIndex != -1) {
            // Remove the selected element from the list model
            listModel.remove(selectedIndex);
        } else {
            // Show a message if no task is selected
            JOptionPane.showMessageDialog(this, 
                                          "Please select a task to delete.", 
                                          "Deletion Error", 
                                          JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void main(String[] args) {
        // Run GUI updates on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            new ToDoApp();
        });
    }
}
