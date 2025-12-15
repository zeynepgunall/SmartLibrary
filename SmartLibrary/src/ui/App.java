package ui;

import javax.swing.SwingUtilities;

import Classes.Book;
import Classes.Member;
import Main.LibraryManager;

public class App {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                LibraryManager manager = new LibraryManager();

                // Add one sample book
                manager.addItem(new Book("B1", "Harry Potter", "Zeynep"));

                // Add one sample member (will appear in the ComboBox)
                manager.addMember(new Member("M1", "Test Member"));

                MainFrame frame = new MainFrame(manager);
                frame.setVisible(true);

            }
        });
    }
}
