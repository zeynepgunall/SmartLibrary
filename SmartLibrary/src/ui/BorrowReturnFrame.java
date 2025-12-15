package ui;

import model.Member;
import service.LibraryManager;

import javax.swing.*;
import java.awt.*;

public class BorrowReturnFrame extends JFrame {

    private final LibraryManager libraryManager;

    private JComboBox<Member> cmbMembers; 
    private JTextField txtItemId;
    private JLabel lblResult;

    public BorrowReturnFrame(LibraryManager libraryManager) {
        if (libraryManager == null) {
            throw new IllegalArgumentException("libraryManager cannot be null");
        }
        this.libraryManager = libraryManager;

        setTitle("Borrow / Return Item");
        setSize(420, 240);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initUI();
        loadMembers();
    }

    private void initUI() {
        JPanel root = new JPanel(new BorderLayout(10, 10));
        root.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        setContentPane(root);

        JPanel form = new JPanel(new GridLayout(2, 2, 8, 8));

        form.add(new JLabel("Member (optional):"));
        cmbMembers = new JComboBox<>();
        form.add(cmbMembers);

        form.add(new JLabel("Item ID:"));
        txtItemId = new JTextField();
        form.add(txtItemId);

        root.add(form, BorderLayout.CENTER);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        JButton btnBorrow = new JButton("Borrow");
        JButton btnReturn = new JButton("Return");

        btnBorrow.addActionListener(e -> handleBorrow());
        btnReturn.addActionListener(e -> handleReturn());

        buttons.add(btnBorrow);
        buttons.add(btnReturn);

        lblResult = new JLabel(" ");
        lblResult.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel bottom = new JPanel(new BorderLayout(0, 8));
        bottom.add(buttons, BorderLayout.NORTH);
        bottom.add(lblResult, BorderLayout.SOUTH);

        root.add(bottom, BorderLayout.SOUTH);
    }

    private void loadMembers() {
        DefaultComboBoxModel<Member> model = new DefaultComboBoxModel<>();
        for (Member m : libraryManager.getAllMembers()) {
            model.addElement(m);
        }
        cmbMembers.setModel(model);
        cmbMembers.setSelectedItem(null);
    }

    private void handleBorrow() {
        String itemId = txtItemId.getText().trim();

        if (itemId.isEmpty()) {
            setResult("Please enter an item ID.", false);
            return;
        }

        boolean ok = libraryManager.borrowItem(itemId);
        setResult(ok
                ? "Borrow successful."
                : "Borrow failed (not found / not borrowable / already borrowed).",
                ok);
    }

    private void handleReturn() {
        String itemId = txtItemId.getText().trim();

        if (itemId.isEmpty()) {
            setResult("Please enter an item ID.", false);
            return;
        }

        boolean ok = libraryManager.returnItem(itemId);
        setResult(ok
                ? "Return successful."
                : "Return failed (not found / not borrowable / already available).",
                ok);
    }

    private void setResult(String msg, boolean success) {
        lblResult.setText(msg);
        lblResult.setForeground(success ? new Color(0, 128, 0) : Color.RED);
    }
}
