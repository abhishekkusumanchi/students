package students;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class RegistrationFrame extends JPanel implements ActionListener {
	JLabel title;
	JLabel firstNameLabel,lastNameLabel,mailLabel,idLabel,genderLabel,addressLabel,skillsLabel,phoneNumberLabel;
	JTextArea addressTextArea;
	JScrollPane scrollpane;
	JTextField firstNameTextField,lastNameTextField,mailTextField;
	JTextField idTextField,phoneNumberTextField;
	JButton submit,reset;
	JRadioButton male,female;
	ButtonGroup buttongroup;
	JCheckBox cb1,cb2,cb3,cb4,cb5,cb6;
	Map<JCheckBox,String> checkBoxes; 
	List<JTextField> textFields;
	RegistrationFrame(){

		title = new JLabel("Student Registration Form");
		title.setFont(new Font(null,Font.BOLD,30));
		title.setBounds(190,0,500, 100);
		
		idLabel = new JLabel("Register Number:");
		idLabel.setBounds(200,100,100,20);
		
		firstNameLabel = new JLabel("First Name:");
		firstNameLabel.setBounds(200, 140, 100, 20);
		
		lastNameLabel = new JLabel("Last Name:");
		lastNameLabel.setBounds(200, 180, 100, 20);
		
		genderLabel = new JLabel("Gender:");
		genderLabel.setBounds(200,220, 100, 20);
		
		addressLabel = new JLabel("Address:");
		addressLabel.setBounds(200, 260, 100, 20);
		
		skillsLabel = new JLabel("Skills:");
		skillsLabel.setBounds(200, 310, 100, 20);
		
		mailLabel = new JLabel("Mail:");
		mailLabel.setBounds(200, 370, 100, 20);
		
		phoneNumberLabel = new JLabel("Phone Number:");
		phoneNumberLabel.setBounds(200,410,100,20);
		
		submit = new JButton("submit");
		submit.setBounds(200,480, 100, 20);
		submit.addActionListener(this);
		
		reset = new JButton("reset");
		reset.setBounds(400,480, 100, 20);
		reset.addActionListener(this);
		//male or female radio buttons
		male = new JRadioButton("Male");
		female = new JRadioButton("Female");
		buttongroup = new ButtonGroup();
		buttongroup.add(male);
		buttongroup.add(female);
		male.setBounds(320,220, 60, 20);
		female.setBounds(380,220, 80, 20);
		
		//text fields
		firstNameTextField = new JTextField();
		firstNameTextField.setBounds(320,140,100,20);
		
		idTextField = new JTextField();
		idTextField.setBounds(320,100,100,20);
		
		lastNameTextField = new JTextField();
		lastNameTextField.setBounds(320,180,100,20);
		
		mailTextField = new JTextField();
		mailTextField.setBounds(320,370,100,20);
		
		phoneNumberTextField = new JTextField();
		phoneNumberTextField.setBounds(320,410,100,20);
		
		
		//text area
		addressTextArea = new JTextArea(4,10);
		//addressTextArea.setBounds(320, 260, 200, 40);
		addressTextArea.setLineWrap(true);
		scrollpane = new JScrollPane(addressTextArea);
		scrollpane.setBounds(320, 260, 200, 40);
		//check boxes
		cb1 = new JCheckBox("C");
		cb2 = new JCheckBox("HTML/CSS");
		cb3 = new JCheckBox("Python");
		cb4 = new JCheckBox("Java");
		cb5 = new JCheckBox("JS");	
		cb6 = new JCheckBox("SQL");

		cb1.setBounds(320, 310, 70, 20);cb2.setBounds(390, 310, 120, 20);cb3.setBounds(510, 310, 70, 20);
		cb4.setBounds(320, 330, 70, 20);cb5.setBounds(390, 330, 70, 20);cb6.setBounds(460, 330, 70, 20);
		checkBoxes = new LinkedHashMap<>();
		checkBoxes.put(cb1,"C");
		checkBoxes.put(cb2,"HTML/CSS");
		checkBoxes.put(cb3,"Python");
		checkBoxes.put(cb4,"Java");
		checkBoxes.put(cb5,"JS");
		checkBoxes.put(cb6,"SQL");
		
		//list of text fields
		textFields = new ArrayList<JTextField>();
		textFields.add(firstNameTextField);
		textFields.add(lastNameTextField);
		textFields.add(mailTextField);
		textFields.add(phoneNumberTextField);
		textFields.add(idTextField);

		
		//default settings
		//this.setTitle("registration form");
		this.setVisible(true);
		//this.setDefaultCloseOperation(Jthis.EXIT_ON_CLOSE);
		this.setSize(800,600);
		this.setLayout(null);
		
		
		//adding components to the this
		this.add(title);
		this.add(firstNameLabel);
		this.add(lastNameLabel);
		this.add(idLabel);this.add(addressLabel);this.add(mailLabel);
		this.add(genderLabel);this.add(phoneNumberLabel);this.add(skillsLabel);
		this.add(firstNameTextField);this.add(idTextField);this.add(lastNameTextField);
		this.add(mailTextField);this.add(scrollpane);this.add(phoneNumberTextField);
		this.add(male);
		this.add(female);
		this.add(cb1);this.add(cb2);this.add(cb3);
		this.add(cb4);this.add(cb5);this.add(cb6);
		this.add(submit);this.add(reset);
		
	}

	@Override
	public void actionPerformed(ActionEvent ae){
		if(ae.getSource()==submit) {
			System.out.println("submit");
			boolean isDone = false;
			//fetching data from the form
			String firstName = firstNameTextField.getText();
			String lastName = lastNameTextField.getText();
			String id = idTextField.getText().toUpperCase();
			String address = addressTextArea.getText();
			String phoneNo = phoneNumberTextField.getText();
			String email = mailTextField.getText();
			String password = generateRandomPassword();
			//fetching the gender
			String gender = null;
			if(male.isSelected())
				gender = "male";
			if(female.isSelected())
				gender = "female";
			//fetching the skills of the student using map of check boxes and strings
			StringBuffer skills = new StringBuffer();
			Set<JCheckBox> set = checkBoxes.keySet();
			for(JCheckBox box:set) {
				if(box.isSelected())
					skills.append(checkBoxes.get(box)+" ");	
			}
			
			
			//validating the data using validation(user defined) class
		try {
			if(Validation.isValidId(id))
				throw new RegistrationFormException("Invalid Id");
			if(firstName.equals("") || lastName.equals(""))
				throw new RegistrationFormException("Invalid Name");
			if(Validation.isValidMail(email))
				throw new RegistrationFormException("Invalid Email");
			if(Validation.isValidPhoneNumber(phoneNo))
				throw new RegistrationFormException("Invalid Phone Number");
			if(address.equals(""))
				throw new RegistrationFormException("Invalid Address");
			if("".equals(skills.toString()))
				throw new RegistrationFormException("select any skills");
			if(gender == null) 
				throw new RegistrationFormException("choose your gender");
			StringBuffer details = new StringBuffer();
			details.append("Name:"+firstName+" "+lastName+"\n");
			details.append("Student Id:"+id+"\n");
			details.append("Gender:"+gender+"\n");
			details.append("Email:"+email+"\n");
			details.append("Phone number:"+phoneNo+"\n");
			details.append("Address:"+address+"\n");
			details.append("Skills:"+skills+"\n");
			details.append("Password:"+password);
			
			try {
				 isDone = FileOrganization.insertStudent(id, details.toString());
			}catch(IOException ie) {}
			if(!isDone) {
				throw new RegistrationFormException("student already exists");
			}
			JOptionPane.showMessageDialog(this, "Student have been registered successfully\nyour password is:"+password);
				
		}catch(Exception e) {
			 JOptionPane.showMessageDialog(this,e); 
		}
		
		
		
		}else if(ae.getSource()==reset) {
			for(JTextField field:textFields)
				field.setText(null);
			addressTextArea.setText(null);
			Set<JCheckBox> set = checkBoxes.keySet();
			for(JCheckBox box:set)
				box.setSelected(false);
			male.setSelected(false);
			female.setSelected(false);
			
		}
		
	}
	static String generateRandomPassword() {
		Random random = new Random();
		float f = random.nextFloat();
		int num = (int)(f * 100000);
		return "lendi@"+num;
	}

}
