package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Member;
import service.LibraryManager;

public class AddMemberFrame extends JFrame {

    private LibraryManager libraryManager;

    private JTextField txtMemberId;
    private JTextField txtName;

    public AddMemberFrame(LibraryManager libraryManager) {
        this.libraryManager = libraryManager;

        setTitle("Add Member");
        setSize(300, 180);
        setLocationRelativeTo(null);

        initUI();
    }

    private void initUI() {
        JLabel lblMemberId = new JLabel("Member ID:");
        JLabel lblName = new JLabel("Name:");

        txtMemberId = new JTextField();
        txtName = new JTextField();

        JButton btnSave = new JButton("Save");

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveMember();
            }
        });

        setLayout(new GridLayout(3, 2, 5, 5));
        add(lblMemberId); add(txtMemberId);
        add(lblName);     add(txtName);
        add(new JLabel()); // spacer
        add(btnSave);
    }

    private void saveMember() {
        String id = txtMemberId.getText().trim();
        String name = txtName.getText().trim();

        if (id.isEmpty() || name.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Member ID and Name fields cannot be empty.");
            return;
        }

        Member member = new Member(id, name);
        libraryManager.addMember(member);

        JOptionPane.showMessageDialog(this,
                "Member added successfully: " + member.toString());

        dispose(); // close the window
    }
}
