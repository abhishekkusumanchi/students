package students;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class LoginPage extends JPanel implements ActionListener {

	JLabel title;
	JLabel loginIdLabel, passwordLabel;
	JTextField loginIdField;
	JPasswordField passwordField;
	JButton login, backButton;
	JPanel loginPanel, displayPanel;
	CardLayout cl;
	JTable table;

	LoginPage() {

		loginPanel = new JPanel();
		displayPanel = new JPanel();

		title = new JLabel("login page");
		title.setFont(new Font(null, Font.BOLD, 30));
		title.setBounds(280, 0, 500, 100);

		loginIdLabel = new JLabel("Login Id:");
		loginIdLabel.setBounds(220, 180, 120, 20);

		passwordLabel = new JLabel("Password:");
		passwordLabel.setBounds(220, 220, 120, 20);

		loginIdField = new JTextField();
		loginIdField.setBounds(350, 180, 100, 20);

		passwordField = new JPasswordField();
		passwordField.setBounds(350, 220, 100, 20);

		login = new JButton("login");
		login.setBounds(320, 370, 100, 20);
		login.addActionListener(this);

		loginPanel.add(login);
		loginPanel.add(title);
		loginPanel.add(loginIdLabel);
		loginPanel.add(passwordLabel);
		loginPanel.add(loginIdField);
		loginPanel.add(passwordField);
		loginPanel.setLayout(null);
		loginPanel.setVisible(true);
		loginPanel.setSize(800, 600);
		loginPanel.setBounds(0, 0, 800, 600);

		displayPanel.setLayout(null);
		displayPanel.setVisible(true);
		displayPanel.setSize(800, 600);
		displayPanel.setBounds(0, 0, 800, 600);

		cl = new CardLayout();

		this.add(loginPanel);
		this.add(displayPanel);
		this.setLayout(cl);

	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == login) {
			boolean validUser = false;
			String studentId = loginIdField.getText();
			String password = new String(passwordField.getPassword());
			String data = null;
			try {
				validUser = FileOrganization.validateStudent(studentId, password);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, e);
			}
			if (validUser) {
				System.out.println("next page");
				JOptionPane.showMessageDialog(this, "login successful");
				try {
					data = FileOrganization.getStudentDetails(studentId);
				} catch (IOException e) {
					e.printStackTrace();
				}
				display(data);
				cl.next(this);

			}
		}else if(ae.getSource()==backButton) {
			loginIdField.setText(null);
			passwordField.setText(null);
			cl.previous(this);
			
		}

	}

	public void display(String data) {
		System.out.println(data);
		Scanner sc = new Scanner(data);
		String[][] details = new String[7][2];
		for (int i = 0; i < 7; i++) {
			String[] line = sc.nextLine().split(":");
			details[i][0] = line[0];
			details[i][1] = line[1];
		}
		sc.close();
		String[] col = { "property", "value" };
		table = new JTable(details, col);
		table.setRowHeight(32);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(480, 248));
		scrollPane.setBounds(150, 100, 480, 248);
		backButton = new JButton("go back to login page");
		backButton.setBounds(300, 380, 170, 20);
		backButton.addActionListener(this);
		// table.setBounds(50, 50, 700, 150);
		displayPanel.add(scrollPane);
		displayPanel.add(backButton);

	}

}
