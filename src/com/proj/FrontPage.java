/**	This class displays the Home Page of Wedding Planner. It consists of control flow for all the major functionalities of this application
 * like log in, sign up, plan a wedding and send invitation.
 * @author Vipul Patil */

package com.proj;

import java.awt.Button;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class FrontPage{
	final static Button loginButton = new Button("Log In");
	final static Button signupButton = new Button("Sign Up");
	final static Button logoutButton = new Button("Log Out");
	final static JFrame window = new JFrame("Wedding Planner - Welcome");
	final static JPanel panel = new JPanel();
	final static JButton planButton = new JButton("Plan a Wedding");
	final static JButton invitationsButton = new JButton("Wedding Invitations");
	
	/** This class contains the main function which indicates that the control flow of the whole application begins from this page.
	 * @param args 
	 * @throws ClassNotFoundException */

	public static void main(String[] args) throws ClassNotFoundException{
		window.setSize(630, 600);	// (width, height)	/** Front Page Frame: window */
		window.setResizable(false);
		
		/** As this is the main window, if the user clicks to close this window then they should be asked once again whether he or she has to exit for sure. */
		window.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				int selectedOption = JOptionPane.showConfirmDialog(window, "Exit Wedding Planner?", "Confirm Exit", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
			 	if(selectedOption == JOptionPane.YES_OPTION)
			 		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				else if(selectedOption == JOptionPane.NO_OPTION)
					window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				else
					window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			}
		});
		
		/** panel is contained on top of the window, on which various components can be added */
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(560,540));	// (width, height)
		panel.setBackground(new Color(255,229,219));	// (r,g,b)
	    window.add(panel);
		
	    /** The title of the project: Wedding Planner is displayed using a simple label */
		JLabel titleLabel = new JLabel("Wedding Planner");
		titleLabel.setFont(new Font("Curlz MT", Font.BOLD, 45));
    	titleLabel.setForeground(new Color(225,0,0));	
    	titleLabel.setBounds(30, 20, 350, 55);			// (x, y, width, height)
    	panel.add(titleLabel);
    	
    	/** This button is for a returning user who wants to log into the application */
    	loginButton.setFont(new Font("Arial", Font.BOLD, 15));
    	loginButton.setBounds(410, 37, 80, 30);
    	loginButton.setForeground(new Color(225,0,0));
    	loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    	panel.add(loginButton);
    	loginButton.addActionListener(new ActionListener(){		// When this button is clicked log in window should be opened.
			public void actionPerformed(ActionEvent arg0){
				window.setVisible(false);
				new LoginClass();
			}
		});
    	
    	/** This button is for a new user who wants to sign up for the application */
    	signupButton.setFont(new Font("Arial", Font.BOLD, 15));
    	signupButton.setBounds(500, 37, 80, 30);
    	signupButton.setForeground(new Color(225,0,0));
    	signupButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    	panel.add(signupButton);
    	signupButton.addActionListener(new ActionListener(){		// When this button is clicked sign up window should be opened.
			public void actionPerformed(ActionEvent e){
				window.setVisible(false);
				new SignUpClass();
			}
		});
    	    	
    	/** A cover image is loaded and displayed on the front page of the application */
    	ImageIcon frontPageImageIcon = new ImageIcon(FrontPage.class.getResource("cover-frontpage.jpg"));
    	
    	JLabel frontPageImageLabel = new JLabel(frontPageImageIcon);
    	frontPageImageLabel.setBounds(25, 100, 550, 325);
    	frontPageImageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 15));
    	panel.add(frontPageImageLabel);
	    
    	/**	This button is for planning the wedding which is disabled initially until user logs in */ 
    	planButton.setEnabled(false);
    	planButton.setBounds(125, 475, 150, 30);
    	planButton.setToolTipText("First log into the application.");
    	panel.add(planButton);
    	planButton.addActionListener(new ActionListener(){			// On click of this button planner page should be displayed.
			public void actionPerformed(ActionEvent e){
				window.setVisible(false);
				new PlannerPage();
			}
		});
    	
    	/** This button is for sending invitations which is also disabled initially until user logs in */
    	invitationsButton.setEnabled(false);
	    invitationsButton.setBounds(335, 475, 150, 30);
	    invitationsButton.setToolTipText("First log into the application.");
	    panel.add(invitationsButton);
        invitationsButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){				// This button should open the invitation page.
				window.setVisible(false);
				new InvitationsPage();
			}
		});
        
        JLabel copyrightLabel = new JLabel("© Wedding Planner - Created by Pooja Warang");
        copyrightLabel.setBounds(185,550,250,20);
        copyrightLabel.setForeground(new Color(60,60,60));
        panel.add(copyrightLabel);
        
	    window.setVisible(true);
	    
	    Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection;
		try{
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/event\",\"root\",\"root");
			java.sql.DatabaseMetaData dbmd = connection.getMetaData();
			ResultSet rs=dbmd.getTables(null, null, "usertable", null);
			
			
			connection.close();
		} 
		catch (SQLException e1){
			System.out.println("Tables already exists");
			System.exit(0);
		}
	}
}