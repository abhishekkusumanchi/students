package students;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class Tabbs extends JFrame{
	
	Tabbs(){
		JTabbedPane tp=new JTabbedPane(); 
		JPanel registrationPanel = new RegistrationFrame();
		JPanel loginPage = new LoginPage();
		JPanel searchPage = new SearchPage();

		tp.add("registration form",registrationPanel);
		tp.add("login page",loginPage);
		tp.add("Search Page",searchPage);
		tp.setBounds(0,0,800,600);
		add(tp);
		
		
		
	}
	
}
