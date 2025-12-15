package ui;

import javax.swing.*;

import Main.LibraryManager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    private LibraryManager libraryManager;

    public MainFrame(LibraryManager libraryManager) {
        this.libraryManager = libraryManager;

        setTitle("Smart Library");
        setSize(400, 320);
        setLocationRelativeTo(null); // center on screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initUI();
    }

    private void initUI() {
        // Title at the top
        JLabel label = new JLabel("Smart Library Application", SwingConstants.CENTER);
        add(label, BorderLayout.NORTH);

        // Buttons
        JButton btnAddItem = new JButton("Add Item");
        JButton btnViewItems = new JButton("List Items");
        JButton btnBorrowReturn = new JButton("Borrow / Return");
        JButton btnAddMember = new JButton("Add Member");

        // Add item
        btnAddItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddItemFrame(libraryManager).setVisible(true);
            }
        });

        // List items
        btnViewItems.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewItemsFrame(libraryManager).setVisible(true);
            }
        });

        // Borrow / return
        btnBorrowReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BorrowReturnFrame(libraryManager).setVisible(true);
            }
        });

        // Add member
        btnAddMember.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddMemberFrame(libraryManager).setVisible(true);
            }
        });

        // Panel layout
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 10, 10)); // 4 buttons, 1 column
        panel.add(btnAddItem);
        panel.add(btnViewItems);
        panel.add(btnBorrowReturn);
        panel.add(btnAddMember);

        add(panel, BorderLayout.CENTER);
    }
}
