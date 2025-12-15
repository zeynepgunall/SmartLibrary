package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Book;
import model.Magazine;
import model.DVD;
import model.LibraryItem;
import service.LibraryManager;

public class AddItemFrame extends JFrame {

    private LibraryManager libraryManager;

    private JComboBox<String> cmbType;
    private JTextField txtId;
    private JTextField txtTitle;
    private JTextField txtAuthor;
    private JTextField txtIssueNumber;
    private JTextField txtDuration;

    private JLabel lblAuthor;
    private JLabel lblIssueNumber;
    private JLabel lblDuration;

    public AddItemFrame(LibraryManager libraryManager) {
        this.libraryManager = libraryManager;

        setTitle("Add Item (Book / Magazine / DVD)");
        setSize(400, 260);
        setLocationRelativeTo(null);

        initUI();
    }

    private void initUI() {
        // Item type selection
        JLabel lblType = new JLabel("Type:");
        cmbType = new JComboBox<>();
        cmbType.addItem("Book");
        cmbType.addItem("Magazine");
        cmbType.addItem("DVD");

        JLabel lblId = new JLabel("ID:");
        JLabel lblTitle = new JLabel("Title:");
        lblAuthor = new JLabel("Author:");
        lblIssueNumber = new JLabel("Issue Number (Magazine):");
        lblDuration = new JLabel("Duration (minutes - DVD):");

        txtId = new JTextField();
        txtTitle = new JTextField();
        txtAuthor = new JTextField();
        txtIssueNumber = new JTextField();
        txtDuration = new JTextField();

        JButton btnSave = new JButton("Save");

        // Update visible fields when item type changes
        cmbType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateFieldVisibility();
            }
        });

        // Initial visibility setup
        updateFieldVisibility();

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveItem();
            }
        });

        JPanel formPanel = new JPanel(new GridLayout(6, 2, 5, 5));
        formPanel.add(lblType);        formPanel.add(cmbType);
        formPanel.add(lblId);          formPanel.add(txtId);
        formPanel.add(lblTitle);       formPanel.add(txtTitle);
        formPanel.add(lblAuthor);      formPanel.add(txtAuthor);
        formPanel.add(lblIssueNumber); formPanel.add(txtIssueNumber);
        formPanel.add(lblDuration);    formPanel.add(txtDuration);

        setLayout(new BorderLayout());
        add(formPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(btnSave);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    // Show/hide fields based on selected item type
    private void updateFieldVisibility() {
        String type = (String) cmbType.getSelectedItem();

        if ("Book".equals(type)) {
            lblAuthor.setVisible(true);
            txtAuthor.setVisible(true);

            lblIssueNumber.setVisible(false);
            txtIssueNumber.setVisible(false);

            lblDuration.setVisible(false);
            txtDuration.setVisible(false);
        } else if ("Magazine".equals(type)) {
            lblAuthor.setVisible(false);
            txtAuthor.setVisible(false);

            lblIssueNumber.setVisible(true);
            txtIssueNumber.setVisible(true);

            lblDuration.setVisible(false);
            txtDuration.setVisible(false);
        } else if ("DVD".equals(type)) {
            lblAuthor.setVisible(false);
            txtAuthor.setVisible(false);

            lblIssueNumber.setVisible(false);
            txtIssueNumber.setVisible(false);

            lblDuration.setVisible(true);
            txtDuration.setVisible(true);
        }
    }

    private void saveItem() {
        String type = (String) cmbType.getSelectedItem();
        String id = txtId.getText().trim();
        String title = txtTitle.getText().trim();

        if (id.isEmpty() || title.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "ID and Title fields are required.");
            return;
        }

        LibraryItem item = null;

        try {
            if ("Book".equals(type)) {
                String author = txtAuthor.getText().trim();
                if (author.isEmpty()) {
                    JOptionPane.showMessageDialog(this,
                            "Author field cannot be empty.");
                    return;
                }
                item = new Book(id, title, author);

            } else if ("Magazine".equals(type)) {
                String issueText = txtIssueNumber.getText().trim();
                if (issueText.isEmpty()) {
                    JOptionPane.showMessageDialog(this,
                            "Issue Number field cannot be empty.");
                    return;
                }
                int issue = Integer.parseInt(issueText);
                item = new Magazine(id, title, issue);

            } else if ("DVD".equals(type)) {
                String durationText = txtDuration.getText().trim();
                if (durationText.isEmpty()) {
                    JOptionPane.showMessageDialog(this,
                            "Duration field cannot be empty.");
                    return;
                }
                int duration = Integer.parseInt(durationText);
                item = new DVD(id, title, duration);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Issue Number and Duration must be numeric values.");
            return;
        }

        if (item != null) {
            libraryManager.addItem(item);
            JOptionPane.showMessageDialog(this,
                    "Item added successfully: " + item.getDetails());
            dispose(); // close the window
        }
    }
}
