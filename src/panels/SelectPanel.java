package panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class SelectPanel extends JPanel{
	
	private ImageIcon login = new ImageIcon("img/select/login_btn.png");

	private JButton btn_login;
	
	public JButton getBtn() {
		return btn_login;
	}
	
	private int logined = 0;
	
	public void setBtnImgClicked() {
		btn_login.setIcon(new ImageIcon("img/select/login_btn_clicked.png"));
		btn_login.setIcon(new ImageIcon("img/select/login_btn.png"));
	}
	
	public int Logined() {
		return logined;
	}
	
	public SelectPanel(Object o) {
		
		btn_login = new JButton(login);
		btn_login.setName("StartBtn");	
		btn_login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				/*
				if(ID.getText().equals("team4")&& Arrays.equals(PWD.getPassword(), "1234".toCharArray())) {
					logined = 1;
				}
				*/
				
			}
		});
		btn_login.addMouseListener((MouseListener) o);
		btn_login.setBounds(805, 280, 105, 91);
		add(btn_login);
		btn_login.setBorderPainted(false);
		btn_login.setContentAreaFilled(false);
		btn_login.setFocusPainted(false);
		
		JTextField ID = new JTextField();
		ID.setFont(new Font("Arial", Font.BOLD, 30));
		ID.setBounds(623, 280, 170, 43);
		add(ID);
		ID.setColumns(10);
		
		JPasswordField PWD = new JPasswordField();
		PWD.setFont(new Font("Arial", Font.BOLD, 30));
		PWD.setBounds(623, 330, 170, 43);
		add(PWD);
		
		JLabel label_login = new JLabel("LOGIN");
		label_login.setForeground(Color.WHITE);
		label_login.setFont(new Font("Arial", Font.BOLD, 30));
		label_login.setBounds(623, 231, 111, 39);
		add(label_login);
		
		JLabel label_id = new JLabel("ID :");;
		label_id.setForeground(Color.WHITE);
		label_id.setFont(new Font("Arial", Font.BOLD, 30));
		label_id.setHorizontalAlignment(SwingConstants.RIGHT);
		label_id.setBounds(548, 280, 64, 43);
		add(label_id);
		
		JLabel label_pwd = new JLabel("PW :");
		label_pwd.setFont(new Font("Arial", Font.BOLD, 30));
		label_pwd.setForeground(Color.WHITE);
		label_pwd.setHorizontalAlignment(SwingConstants.RIGHT);
		label_pwd.setBounds(530, 330, 81, 43);
		add(label_pwd);
		
		JLabel selectBg = new JLabel("");
		selectBg.setForeground(Color.ORANGE);
		selectBg.setHorizontalAlignment(SwingConstants.CENTER);
		selectBg.setIcon(new ImageIcon("img/select/selectBg_team4.png"));
		selectBg.setBounds(0, 0, 1000, 700);
		add(selectBg);
	}
}
