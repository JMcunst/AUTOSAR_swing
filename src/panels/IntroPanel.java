package panels;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class IntroPanel extends JPanel {
	ImageIcon introIc = new ImageIcon("img/intro/intro_team4.png"); // 인트로 이미지
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g); // 화면을 비운다
		
		// 인트로 화면을 그린다
		g.drawImage(introIc.getImage(), 0, 0, /* this.getWidth(), this.getHeight(), */ null);

	}
}
