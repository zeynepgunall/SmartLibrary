package ui;

import javax.swing.*;

import Interfaces.LibraryItem;
import Main.LibraryManager;

import java.awt.*;

public class ViewItemsFrame extends JFrame {

    private LibraryManager libraryManager;

    public ViewItemsFrame(LibraryManager libraryManager) {
        this.libraryManager = libraryManager;

        setTitle("List Items");
        setSize(400, 300);
        setLocationRelativeTo(null);

        initUI();
    }

    private void initUI() {
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

        for (LibraryItem item : libraryManager.getAllItems()) {
            textArea.append(item.getDetails() + "\n");
        }

        JScrollPane scrollPane = new JScrollPane(textArea);

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
    }
}
