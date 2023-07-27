package com.ashu.chatapp.views;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.jar.JarOutputStream;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.ashu.chatapp.dao.UserDAO;
import com.ashu.chatapp.dto.UserDTO;
import com.ashu.chatapp.utils.UserInfo;

public class UserScreen extends JFrame{
	private JTextField useridtxt;
	private JPasswordField passwordField;

	
	public static void main(String[] args) {
		
					UserScreen window = new UserScreen();		
			}
	
	UserDAO userDAO = new UserDAO();
	private void doLogin() {
		String userid = useridtxt.getText();
		char[] password = passwordField.getPassword();
		
		UserDTO userDTO = new UserDTO(userid, password);
		try {
			String message ="";
			if(userDAO.isLogin(userDTO)) {
				message = "Welcome "+ userid;
				UserInfo.USER_NAME =userid;
				JOptionPane.showMessageDialog(this, message);
				setVisible(false);
				dispose();
				DashBoard dashBoard = new DashBoard(message);
				dashBoard.setVisible(true);
			}
			else {
				message = "Invalid userid or Password";
				JOptionPane.showMessageDialog(this, message);
			}
//			JOptionPane.showMessageDialog(this, message);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private void register() {
		
		String userid = useridtxt.getText();
		char[] password = passwordField.getPassword();
//		UserDAO userDAO = new UserDAO();
		UserDTO userDTO = new UserDTO(userid, password);
		try {
		int result = userDAO.add(userDTO);
		if(result>0) {
			JOptionPane.showMessageDialog(this, "Register Successfully");
		}else {
			JOptionPane.showMessageDialog(this, "Registe failed");
			//System.out.println("Not Added...");
		}
		} catch(ClassNotFoundException | SQLException ex){
			System.out.println("DB Issue.....");
			ex.printStackTrace();
		}
		catch (Exception ex) {
			System.out.println("Some Generic Exeption Raised.....");
			ex.printStackTrace(); // where is the exeption.
		}
		System.out.println("userid "+userid+" password "+password);  //Classname@hashcode(hexa)
		
	}

	public UserScreen() {
		setResizable(false);
		setTitle("Login\r\n");
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("LOGIN");
		lblNewLabel.setEnabled(false);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(190, 62, 122, 29);
		getContentPane().add(lblNewLabel);
		
		useridtxt = new JTextField();
		useridtxt.setBounds(237, 109, 201, 29);
		getContentPane().add(useridtxt);
		useridtxt.setColumns(10);
		
		JLabel UserId = new JLabel("Userid");
		UserId.setFont(new Font("Tahoma", Font.PLAIN, 18));
		UserId.setBounds(112, 109, 71, 22);
		getContentPane().add(UserId);
		
		JLabel PWDlbl = new JLabel("Password");
		PWDlbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		PWDlbl.setBounds(112, 167, 95, 22);
		getContentPane().add(PWDlbl);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(237, 167, 201, 29);
		getContentPane().add(passwordField);
		
		JButton Loginbt = new JButton("Login ");
		Loginbt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doLogin();
			}
		});
		Loginbt.setFont(new Font("Tahoma", Font.BOLD, 14));
		Loginbt.setBounds(137, 244, 95, 29);
		getContentPane().add(Loginbt);
		
		JButton Registerbt = new JButton("Register");
		Registerbt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				register();
			}
		});
		Registerbt.setFont(new Font("Tahoma", Font.BOLD, 15));
		Registerbt.setBounds(300, 244, 110, 28);
		getContentPane().add(Registerbt);
		setSize(537, 350);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
