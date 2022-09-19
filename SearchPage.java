package students;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class SearchPage extends JPanel implements ActionListener {
	JTextField studentNameTextField;
	JLabel studentNameLabel, title;
	JButton search, reset;
	JTable table;
	JScrollPane scrollPane;

	SearchPage() {
		title = new JLabel("search page");
		title.setFont(new Font(null, Font.BOLD, 30));
		title.setBounds(250, 0, 500, 100);

		studentNameTextField = new JTextField();
		studentNameTextField.setBounds(300, 140, 100, 20);

		studentNameLabel = new JLabel("Enter Name:");
		studentNameLabel.setBounds(200, 140, 100, 20);

		search = new JButton("search");
		search.setBounds(420, 140, 80, 20);
		search.addActionListener(this);

		reset = new JButton("reset");
		reset.setBounds(520, 140, 100, 20);
		reset.addActionListener(this);

		add(title);
		add(studentNameTextField);
		add(studentNameLabel);
		add(search);

		setLayout(null);
		setVisible(true);
		setSize(800, 600);

	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == search) {
			String name = studentNameTextField.getText();
			if (!name.equals("")) {
				try {

					List<String> list = FileOrganization.getSearchedData(name);
					int records = list.size();
					if (records == 0) {
						JOptionPane.showMessageDialog(this, "No data found with name" + name);
					} else if (records > 1) {
						int selectedOption = JOptionPane.showConfirmDialog(this, String
								.format("%d records found with name %s\nclick yes to show the data ", records, name));
						if (selectedOption == JOptionPane.YES_OPTION) {
							this.add(reset);
							showRecords(list);
							this.repaint();
							
						}
					}

				} catch (IOException e) {
					e.printStackTrace();
				}

			} else {
				JOptionPane.showMessageDialog(this, "invalid name", "alert", JOptionPane.WARNING_MESSAGE);
			}
		}
		else if(ae.getSource()==reset) {
			studentNameTextField.setText(null);
			this.remove(scrollPane);
			this.remove(reset);
			this.repaint();
		}
	}

	public void showRecords(List<String> list) {
		System.out.println(list);
		String[][] data = new String[list.size()][4];
		String[] cols = { "SNo", "Name", "Reg Id", "Gender" };
		int i = 0;
		for (String str : list) {
			Scanner sc = new Scanner(str);
			data[i][0] = i + 1 + "";
			for (int j = 1; j < 4; j++) {
				data[i][j] = sc.nextLine().split(":")[1];
			}
			sc.close();
			i++;
		}
		System.out.println(data);
		table = new JTable(data, cols);
		scrollPane = new JScrollPane(table);
		table.setRowHeight(30);
		int limit = 0;
		if(list.size()>5) 
			limit = 175; 
		else	
			limit= 30 * list.size() + 25;
		scrollPane.setPreferredSize(new Dimension(500,limit));
		scrollPane.setBounds(100, 300, 500, limit);
		add(scrollPane);
	}

}
