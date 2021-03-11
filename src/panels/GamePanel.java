package panels;

import java.awt.AlphaComposite;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ingame.Back;
import ingame.MyButton;
import ingame.Cookie;
import ingame.CookieImg;
import ingame.MapObjectImg;
import main.Main;
import util.Util;
import main.Connector;

public class GamePanel extends JPanel{
	static Connector conn;
	
	private ImageIcon cookieIc; // 기본모션
	private ImageIcon cookieLeft; // 점프모션
	private ImageIcon cookieRight; 
	
	private ImageIcon gameRoad = new ImageIcon("img/Objectimg/road_need_200.png");
	private ImageIcon dashBoard = new ImageIcon("img/Objectimg/dash_0.png");
	
	private ImageIcon lightLeft = new ImageIcon("img/Objectimg/left_light.png"); 
	private ImageIcon lightRight = new ImageIcon("img/Objectimg/right_light.png");
	private ImageIcon lightLeftOff = new ImageIcon("img/Objectimg/left_light_off.png"); 
	private ImageIcon lightRightOff = new ImageIcon("img/Objectimg/right_light_off.png");
	
	private ImageIcon wiper_0 = new ImageIcon("img/cookieimg/cookie1/wiper_0.png");
	private ImageIcon wiper_1 = new ImageIcon("img/cookieimg/cookie1/wiper_1.png");
	private ImageIcon wiper_2 = new ImageIcon("img/cookieimg/cookie1/wiper_2.png");
	private ImageIcon wiper_3 = new ImageIcon("img/cookieimg/cookie1/wiper_3.png");
	
	private ImageIcon chair_0 = new ImageIcon("img/cookieimg/cookie1/chair_0.png");
	private ImageIcon chair_1 = new ImageIcon("img/cookieimg/cookie1/chair_1.png");
	private ImageIcon chair_2 = new ImageIcon("img/cookieimg/cookie1/chair_2.png");
	
	private ImageIcon LDWS_0 = new ImageIcon("img/cookieimg/cookie1/LDWS_btn_0.png");
	private ImageIcon LDWS_1 = new ImageIcon("img/cookieimg/cookie1/LDWS_btn_1.png");
	
	private ImageIcon soundOff_0 = new ImageIcon("img/cookieimg/cookie1/sound_off.png");
	private ImageIcon soundOff_1 = new ImageIcon("img/cookieimg/cookie1/sound_off_1.png");
	private ImageIcon seatOff_0 = new ImageIcon("img/cookieimg/cookie1/seat_off.png");
	private ImageIcon seatOff_1 = new ImageIcon("img/cookieimg/cookie1/seat_off_1.png");
	private ImageIcon displayOff_0 = new ImageIcon("img/cookieimg/cookie1/display_off.png");
	private ImageIcon displayOff_1 = new ImageIcon("img/cookieimg/cookie1/display_off_1.png");
	
	private ImageIcon displayLine_0 = new ImageIcon("img/cookieimg/cookie1/displayLine_0.png");
	private ImageIcon displayLine_1 = new ImageIcon("img/cookieimg/cookie1/displayLine_1.png");
	private ImageIcon displayLine_2 = new ImageIcon("img/cookieimg/cookie1/displayLine_2.png");
	private ImageIcon displayLine_3 = new ImageIcon("img/cookieimg/cookie1/displayLine_3.png");
	
	
	
	private CookieImg ci = new CookieImg(new ImageIcon("img/cookieimg/cookie1/hcar.png"),
			new ImageIcon("img/cookieimg/cookie1/hcar_left.png"),
			new ImageIcon("img/cookieimg/cookie1/hcar_right.png"));
	
	private ImageIcon backIc; // 제일 뒷 배경
	private ImageIcon secondBackIc; // 2번째 배경
	private ImageIcon backIc2;
	private ImageIcon secondBackIc2;
	private ImageIcon backIc3;
	private ImageIcon secondBackIc3;
	private ImageIcon backIc4;
	private ImageIcon secondBackIc4;
	
	private MyButton btn_light_left;
	private MyButton btn_light_right;
	
	private MyButton wiper;
	
	private MyButton chair;
	
	private MyButton LDWS;
	
	private MyButton soundOff;
	private MyButton seatOff;
	private MyButton displayOff;
	
	private MyButton displayLineL;
	private MyButton displayLineR;
	
	private File soundFile;
	
	private List<Integer> mapLengthList;
	
	
	private int mapLength = 0;
	private int gameSpeed = 5; // 게임 속도
	private int runPage = 0;
	
	private boolean escKeyOn = false;
	
	int left; // 쿠키의 머리
	int right; // 쿠키의 발밑
	int setRight = 0; // 우측 화살표 카운트
	int velo = 0; //실제 속도
	int lclicked = 0;
	int rclicked = 0;
	int wiper_mode = 0;
	int chair_mode = 0;
	int ldws_mode = 0;
	
	int cline_video = 0;
	int cbtn = 0;
	int csound_on = 1;
	int cseat_on = 1;
	int cdisplay_on = 1;
	int clight = 0;
	
	int csound_act = 0;
	int cdisplay_act = 0;
	int cseat_act = 0;
	
	private Image buffImage; // 더블버퍼 이미지
	private Graphics buffg; // 더블버퍼 g
	
	Cookie c1;
	
	Back b11; // 배경1-1 오브젝트
	Back b12; 
	Back b21; 
	Back b22; 
	Back broad;
	Back bdash;
	
	MapObjectImg mo1;
	MapObjectImg mo2;
	MapObjectImg mo3;
	MapObjectImg mo4;

	// 외부
	JFrame superFrame;
	CardLayout cl;
	Main main;
	
	public GamePanel(JFrame superFrame, CardLayout cl, Object o) {
		this.superFrame = superFrame;
		this.cl = cl;
		this.main = (Main) o;
		
	}
	
	public void gameSet() {
		setFocusable(true);
		
		initCookieImg(); // 쿠키이미지를 세팅
		
		initObject(); //  지형지물 인스턴스 생성

		initListener(); // 키리스너 추가

		runRepaint(); // 리페인트 무한반복 실행
	}
	public void gameStart() {
	
		//mapMove();

	}
	@Override
	protected void paintComponent(Graphics g) {
		if (buffg == null) {
			buffImage = createImage(this.getWidth(), this.getHeight());
			if (buffImage == null) {
				System.out.println("더블 버퍼링용 오프 스크린 생성 실패");
			} else {
				buffg = buffImage.getGraphics();
			}
		}
		
		Graphics2D g2 = (Graphics2D) buffg;
		
		super.paintComponent(buffg);
		
		buffg.drawImage(broad.getImage(),broad.getX(), 250, broad.getWidth(), broad.getHeight(), null);
		buffg.drawImage(bdash.getImage(),bdash.getX(), 450, bdash.getWidth(), bdash.getHeight(), null);
		buffg.drawImage(b11.getImage(), b11.getX(), 0, b11.getWidth(), b11.getHeight() * 5 / 4, null);
		buffg.drawImage(b12.getImage(), b12.getX(), 0, b12.getWidth(), b12.getHeight() * 5 / 4, null);
		buffg.drawImage(b21.getImage(), b21.getX(), 0, b21.getWidth(), b21.getHeight() * 5 / 4, null);
		buffg.drawImage(b22.getImage(), b22.getX(), 0, b22.getWidth(), b22.getHeight() * 5 / 4, null);
		
		//actuator 그린다
		Util.drawFancyString(g2, "Sd : ", 210, 490, 30, Color.WHITE);
		Util.drawFancyString(g2, ""+conn.getSoundsetActuator(), 270, 490, 30, Color.WHITE);
		Util.drawFancyString(g2, "St : ", 210, 520, 30, Color.WHITE);
		Util.drawFancyString(g2, ""+conn.getSeatsetActuator(), 260, 520, 30, Color.WHITE);
		Util.drawFancyString(g2, "Di : ", 210, 550, 30, Color.WHITE);
		Util.drawFancyString(g2, ""+conn.getDisplaysetActuator(), 260, 550, 30, Color.WHITE);
		
		//외부에서 받아온 설정 값
		Util.drawFancyString(g2, "Sd On : ", 330, 490, 30, Color.GRAY);
		Util.drawFancyString(g2, Integer.toString(csound_on), 440, 490, 30, Color.GRAY);
		Util.drawFancyString(g2, "St On : ", 330, 520, 30, Color.GRAY);
		Util.drawFancyString(g2, Integer.toString(cseat_on), 430, 520, 30, Color.GRAY);
		Util.drawFancyString(g2, "Di On : ", 330, 550, 30, Color.GRAY);
		Util.drawFancyString(g2, Integer.toString(cdisplay_on), 430, 550, 30, Color.GRAY);
		
		//속도 출력
		Util.drawFancyString(g2, "Speed : ", 200, 450, 30, Color.WHITE);
		Util.drawFancyString(g2, Integer.toString(velo), 310, 450, 30, Color.WHITE);
		
		//깜빡이 버튼 그린다
		buffg.drawImage(btn_light_left.getImage(), 210, 600, lightLeft.getImage().getWidth(null), lightLeft.getImage().getHeight(null), null);
		buffg.drawImage(btn_light_right.getImage(), 280, 600, lightRight.getImage().getWidth(null), lightRight.getImage().getHeight(null), null);
	
		//와이퍼 그린다
		buffg.drawImage(wiper.getImage(), 730, 460, wiper.getImage().getWidth(null), wiper.getImage().getHeight(null), null);
		
		//의자 그린다
		buffg.drawImage(chair.getImage(), 850, 460, chair.getImage().getWidth(null), chair.getImage().getHeight(null), null);
		
		//LDWS 버튼 그린다
		buffg.drawImage(LDWS.getImage(), 520, 460, LDWS.getImage().getWidth(null), LDWS.getImage().getHeight(null), null);
		
		//off 버튼 그린다
		buffg.drawImage(soundOff.getImage(), 800, 600, soundOff.getImage().getWidth(null), soundOff.getImage().getHeight(null), null);
		buffg.drawImage(seatOff.getImage(), 850, 600, seatOff.getImage().getWidth(null), seatOff.getImage().getHeight(null), null);
		buffg.drawImage(displayOff.getImage(), 900, 600, displayOff.getImage().getWidth(null), displayOff.getImage().getHeight(null), null);
		
		//display 라인 그린다
		buffg.drawImage(displayLineL.getImage(), 47, 530, displayLineL.getImage().getWidth(null), displayLineL.getImage().getHeight(null), null);
		buffg.drawImage(displayLineR.getImage(), 93, 530, displayLineR.getImage().getWidth(null), displayLineR.getImage().getHeight(null), null);
		
		// 자동차(c1)를 그린다
		buffg.drawImage(c1.getImage(), c1.getX() - 110, c1.getY() - 170 ,
				cookieIc.getImage().getWidth(null) * 8 / 10, cookieIc.getImage().getHeight(null) * 8 / 10, null);
		
		g.drawImage(buffImage, 0, 0, this);
	}
	private void makeMo() {
		mo1 = new MapObjectImg(new ImageIcon("img/Objectimg/map1img/imgbc1.png"),
				new ImageIcon("img/Objectimg/map1img/imgbc2.png"));
		mo2 = new MapObjectImg(new ImageIcon("img/Objectimg/map1img/imgbc1.png"),
				new ImageIcon("img/Objectimg/map1img/imgbc2.png"));
		mo3 = new MapObjectImg(new ImageIcon("img/Objectimg/map1img/imgbc1.png"),
				new ImageIcon("img/Objectimg/map1img/imgbc2.png"));
		mo4 = new MapObjectImg(new ImageIcon("img/Objectimg/map1img/imgbc1.png"),
				new ImageIcon("img/Objectimg/map1img/imgbc2.png"));
	}
	private void initCookieImg() {
		cookieIc = ci.getCookieIc();
		cookieLeft = ci.getCookieLeft();
		cookieRight = ci.getCookieRight();
	}
	private void initImageIcon(MapObjectImg mo) {
		
	}
	private void initMap(int num, int mapLength) {
		String tempMap = null;
		int tempMapLength = 0;
		
		this.mapLength = this.mapLength + tempMapLength;
	}
	private void initObject() {
		conn = new Connector();
		
		try {
			conn.setUp();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		conn.sendDisplaySettingSensorValue((Object)cdisplay_on); 
		conn.sendSeatSettingSensorValue((Object)cseat_on);
		conn.sendSoundSettingSensorValue((Object)csound_on);
		
		soundFile = new File("C:\\Users\\xnslq\\eclipse-workspace\\LDWS_TEAM4\\sound\\ppararam.wav");
		
		btn_light_left = new MyButton(lightLeftOff.getImage());
		btn_light_left.setName("leftLight");
		btn_light_left.setBounds(250, 530, 30, 30);
				
		btn_light_right = new MyButton(lightRightOff.getImage());
		btn_light_right.setName("rightLight");
		btn_light_right.setBounds(300, 530, lightRight.getImage().getWidth(null), lightRight.getImage().getHeight(null));
		
		wiper = new MyButton(wiper_0.getImage());
		wiper.setBounds(730, 460, wiper.getImage().getWidth(null), wiper.getImage().getHeight(null));
		
		chair = new MyButton(chair_0.getImage());
		chair.setBounds(850, 460, chair.getImage().getWidth(null), chair.getImage().getHeight(null));
		
		LDWS = new MyButton(LDWS_0.getImage());
		LDWS.setBounds(520, 460, LDWS.getImage().getWidth(null), LDWS.getImage().getHeight(null));
		
		soundOff = new MyButton(soundOff_0.getImage());
		soundOff.setBounds(800, 600, soundOff.getImage().getWidth(null), soundOff.getImage().getHeight(null));
		seatOff = new MyButton(seatOff_0.getImage());
		seatOff.setBounds(850, 600, seatOff.getImage().getWidth(null), seatOff.getImage().getHeight(null));
		displayOff = new MyButton(displayOff_0.getImage());
		displayOff.setBounds(900, 600, displayOff.getImage().getWidth(null), displayOff.getImage().getHeight(null));
		
		displayLineL = new MyButton(displayLine_0.getImage());
		displayLineL.setBounds(47, 530, displayLineL.getImage().getWidth(null), displayLineL.getImage().getHeight(null));
		displayLineR = new MyButton(displayLine_2.getImage());
		displayLineR.setBounds(93, 530, displayLineR.getImage().getWidth(null), displayLineR.getImage().getHeight(null));
		
		mapLengthList = new ArrayList<>();
		
		makeMo();
		
		initImageIcon(mo1);
		initMap(1, mapLength);
		mapLengthList.add(mapLength);
		
		initImageIcon(mo2);
		initMap(2, mapLength);
		mapLengthList.add(mapLength);

		initImageIcon(mo3);
		initMap(3, mapLength);
		mapLengthList.add(mapLength);

		initImageIcon(mo4);
		initMap(4, mapLength);
		
		backIc = mo1.getBackIc();
		secondBackIc = mo1.getSecondBackIc();
		
		backIc2 = mo2.getBackIc();
		secondBackIc2 = mo2.getSecondBackIc();

		backIc3 = mo3.getBackIc();
		secondBackIc3 = mo3.getSecondBackIc();

		backIc4 = mo4.getBackIc();
		secondBackIc4 = mo4.getSecondBackIc();
		
		c1 = new Cookie(new ImageIcon("img/cookieimg/cookie1/hcar.png").getImage());
		
		
		left = c1.getY() + c1.getHeight();
		right = c1.getY();
		
		// 배경1-1 인스턴스 생성
		b11 = new Back(backIc.getImage(), 0, 0, backIc.getImage().getWidth(null), backIc.getImage().getHeight(null));

		// 배경1-2 인스턴스 생성
		b12 = new Back(backIc.getImage(), backIc.getImage().getWidth(null), 0, // y 값 (조정 필요)
				backIc.getImage().getWidth(null), backIc.getImage().getHeight(null));

		// 배경2-1 인스턴스 생성
		b21 = new Back(secondBackIc.getImage(), 0, 0, secondBackIc.getImage().getWidth(null),
				secondBackIc.getImage().getHeight(null));

		// 배경2-2 인스턴스 생성
		b22 = new Back(secondBackIc.getImage(), secondBackIc.getImage().getWidth(null), 0, // y 값 (조정 필요)
				secondBackIc.getImage().getWidth(null), secondBackIc.getImage().getHeight(null));
		broad = new Back(gameRoad.getImage(),0,0,gameRoad.getImage().getWidth(null),gameRoad.getImage().getHeight(null));
		bdash = new Back(dashBoard.getImage(),0,0,dashBoard.getImage().getWidth(null),dashBoard.getImage().getHeight(null));
	}
	private void initListener() {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (!escKeyOn) {
					if (e.getKeyCode() == KeyEvent.VK_UP) {// 스페이스 키를 누르고 더블점프가 2가 아닐때
						goup();
						if(c1.getY() > 555 || (c1.getY() < 520 && c1.getY() > 456) || c1.getY() <417) {
							if(ldws_mode ==1 && velo >= 60 && lclicked ==0 && rclicked ==0 && wiper_mode != 3 && cseat_on == 1) {
								if(csound_on == 1) {
									outLineSound();
								}
								
								if(c1.getY() >456 && c1.getY() <= 487) {
									if(cseat_on == 1) {
										chair.setImage(chair_2.getImage());
									}
									if(cdisplay_on == 1) {
										displayLineR.setImage(displayLine_3.getImage());
										displayLineL.setImage(displayLine_0.getImage());
									}
									cline_video = 2;
									conn.sendVideoSensorValue((Object)cline_video);
								}else if(c1.getY() >487 && c1.getY() <= 520) {
									if(cseat_on == 1) {
										chair.setImage(chair_1.getImage());
									}
									if(cdisplay_on == 1) {
										displayLineL.setImage(displayLine_1.getImage());
										displayLineR.setImage(displayLine_2.getImage());
									}
									cline_video = 1;
									conn.sendVideoSensorValue((Object)cline_video);
								}else if(c1.getY() > 555) {
									if(cseat_on == 1) {
										chair.setImage(chair_2.getImage());
									}
									if(cdisplay_on == 1) {
										displayLineR.setImage(displayLine_3.getImage());
										displayLineL.setImage(displayLine_0.getImage());
									}
									cline_video = 2;
									conn.sendVideoSensorValue((Object)cline_video);
								}else if(c1.getY() < 417) {
									if(cseat_on == 1) {
										chair.setImage(chair_1.getImage());
									}
									if(cdisplay_on == 1) {
										displayLineL.setImage(displayLine_1.getImage());
										displayLineR.setImage(displayLine_2.getImage());
									}
									cline_video = 1;
									conn.sendVideoSensorValue((Object)cline_video);
								}
								chair_mode = 1;
							}
						}else {
							chair.setImage(chair_0.getImage());
							chair_mode = 0;
							cline_video = 3;
							conn.sendVideoSensorValue((Object)cline_video);
						}
					}
					if (e.getKeyCode() == KeyEvent.VK_DOWN) { // 다운키를 눌렀을 때
						godown();
						if(c1.getY() > 555 || (c1.getY() < 520 && c1.getY() > 456) || c1.getY() <417) {
							if(ldws_mode ==1 && velo >= 60 && lclicked ==0 && rclicked ==0 && wiper_mode != 3) {
								if(csound_on == 1) {
									outLineSound();
								}
								if(c1.getY() >456 && c1.getY() <= 487) {
									if(cseat_on == 1) {
										chair.setImage(chair_2.getImage());
									}
									if(cdisplay_on == 1) {
										displayLineR.setImage(displayLine_3.getImage());
										displayLineL.setImage(displayLine_0.getImage());
									}
									cline_video = 2;
									conn.sendVideoSensorValue((Object)cline_video);
								}else if(c1.getY() >487 && c1.getY() <= 520) {
									if(cseat_on == 1) {
										chair.setImage(chair_1.getImage());
									}
									if(cdisplay_on == 1) {
										displayLineL.setImage(displayLine_1.getImage());
										displayLineR.setImage(displayLine_2.getImage());
									}
									cline_video = 1;
									conn.sendVideoSensorValue((Object)cline_video);
								}else if(c1.getY() > 555) {
									if(cseat_on == 1) {
										chair.setImage(chair_2.getImage());
									}
									if(cdisplay_on == 1) {
										displayLineR.setImage(displayLine_3.getImage());
										displayLineL.setImage(displayLine_0.getImage());
									}
									cline_video = 2;
									conn.sendVideoSensorValue((Object)cline_video);
								}else if(c1.getY() < 417) {
									if(cseat_on == 1) {
										chair.setImage(chair_1.getImage());
									}
									if(cdisplay_on == 1) {
										displayLineL.setImage(displayLine_1.getImage());
										displayLineR.setImage(displayLine_2.getImage());
									}
									cline_video = 1;
									conn.sendVideoSensorValue((Object)cline_video);
								}
								chair_mode = 1;
							}
						}else {
							chair.setImage(chair_0.getImage());
							displayLineL.setImage(displayLine_0.getImage());
							displayLineR.setImage(displayLine_2.getImage());
							chair_mode = 0;
							cline_video = 3;
							conn.sendVideoSensorValue((Object)cline_video);
						}
					}
					if (e.getKeyCode() == KeyEvent.VK_RIGHT) { // 다운키를 눌렀을 때
						setRight++;
						if(setRight == 1) {
							velo = 30;
							conn.sendVelocitySensorValue((Object)velo);
							mapMove();
						}
						gameSpeed++;
						velo += 10;
						conn.sendVelocitySensorValue((Object)velo);
					}
					if (e.getKeyCode() == KeyEvent.VK_LEFT) { // 다운키를 눌렀을 때
						
					}
					if (e.getKeyCode() == KeyEvent.VK_1) { // 다운키를 눌렀을 때
						if(lclicked == 0) {
							btn_light_left.setImage(lightLeft.getImage());
							lclicked = 1;
							clight = 2;
							c1.setImage(cookieLeft.getImage());
							if(rclicked == 1) {
								btn_light_right.setImage(lightRightOff.getImage());
								rclicked = 0;
							}
						}else if(lclicked == 1){
							btn_light_left.setImage(lightLeftOff.getImage());
							lclicked = 0;
							clight = 0;
							c1.setImage(cookieIc.getImage());
						}
					}
					if (e.getKeyCode() == KeyEvent.VK_3) { // 다운키를 눌렀을 때
						if(rclicked == 0) {
							btn_light_right.setImage(lightRight.getImage());
							rclicked = 1;
							clight = 1;
							c1.setImage(cookieRight.getImage());
							if(lclicked == 1) {
								btn_light_left.setImage(lightLeftOff.getImage());
								lclicked = 0;
							}
						}else if(rclicked == 1){
							btn_light_right.setImage(lightRightOff.getImage());
							rclicked = 0;
							clight = 0;
							c1.setImage(cookieIc.getImage());
						}
					}
					if (e.getKeyCode() == KeyEvent.VK_4) {
						if(wiper_mode == 0) {
							wiper.setImage(wiper_1.getImage());
							wiper_mode = 1;
							conn.sendWiperSensorValue((Object)wiper_mode);
						}else if(wiper_mode == 1) {
							wiper.setImage(wiper_2.getImage());
							wiper_mode = 2;
							conn.sendWiperSensorValue((Object)wiper_mode);
						}else if(wiper_mode == 2) {
							wiper.setImage(wiper_3.getImage());
							wiper_mode = 3;
							conn.sendWiperSensorValue((Object)wiper_mode);
						}else if(wiper_mode == 3) {
							wiper.setImage(wiper_0.getImage());
							wiper_mode = 0;
							conn.sendWiperSensorValue((Object)wiper_mode);
						}
					}
					if (e.getKeyCode() == KeyEvent.VK_5) {
						if(ldws_mode == 0) {
							LDWS.setImage(LDWS_1.getImage());
							ldws_mode = 1;
							cbtn = 1;
							conn.sendButtonSensorValue((Object)cbtn);
						}else if(ldws_mode == 1) {
							LDWS.setImage(LDWS_0.getImage());
							ldws_mode = 0;
							cbtn = 0;
							conn.sendButtonSensorValue((Object)cbtn);
						}
					}
					if(e.getKeyCode() == KeyEvent.VK_7) {
						if(csound_on == 1) {
							soundOff.setImage(soundOff_1.getImage());
							csound_on = 0;
							conn.sendSoundSettingSensorValue((Object)csound_on);
						}else if(csound_on == 0) {
							soundOff.setImage(soundOff_0.getImage());
							csound_on = 1;
							conn.sendSoundSettingSensorValue((Object)csound_on);
						}
					}
					if(e.getKeyCode() == KeyEvent.VK_8) {
						if(cseat_on == 1) {
							seatOff.setImage(seatOff_1.getImage());
							cseat_on = 0;
							conn.sendSeatSettingSensorValue((Object)cseat_on);
						}else if(cseat_on == 0) {
							seatOff.setImage(seatOff_0.getImage());
							cseat_on = 1;
							conn.sendSeatSettingSensorValue((Object)cseat_on);
						}
					}
					if(e.getKeyCode() == KeyEvent.VK_9) {
						if(cdisplay_on == 1) {
							displayOff.setImage(displayOff_1.getImage());
							cdisplay_on = 0;
							conn.sendDisplaySettingSensorValue((Object)cdisplay_on);
						}else if(cdisplay_on == 0) {
							displayOff.setImage(displayOff_0.getImage());
							cdisplay_on = 1;
							conn.sendDisplaySettingSensorValue((Object)cdisplay_on);
						}
					}
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_DOWN) { // 다운키를 뗐을 때
					
				}
				if (e.getKeyCode() == KeyEvent.VK_UP) {
				
				}
				if (e.getKeyCode() == KeyEvent.VK_UP) {
				
				}

			}
		});
		
	}
	private void runRepaint() {
		// 리페인트 전용 쓰레드
				new Thread(new Runnable() {

					@Override
					public void run() {
						while (true) {
							repaint();

							if (escKeyOn) { // esc 키를 누를경우 리페인트를 멈춘다
								while (escKeyOn) {
									try {
										Thread.sleep(10);
									} catch (Exception e) {
										e.printStackTrace();
									}
								}
							}

							try {
								Thread.sleep(10);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}).start();
	}
	private void mapMove() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					
					runPage += gameSpeed; // 화면이 이동하면 runPage에 이동한 만큼 저장된다.

					// 배경 이미지 변경
					// 페이드아웃인 상태가 아닐때
						if (mapLength > mapLengthList.get(2) * 40 + 800 && b11.getImage() != backIc4.getImage()) {
					

							new Thread(new Runnable() {

								@Override
								public void run() {

									backFadeOut();

									b11 = new Back(backIc4.getImage(), 0, 0, backIc4.getImage().getWidth(null),
											backIc4.getImage().getHeight(null));

									b12 = new Back(backIc4.getImage(), backIc4.getImage().getWidth(null), 0,
											backIc4.getImage().getWidth(null), backIc4.getImage().getHeight(null));

									b21 = new Back(secondBackIc4.getImage(), 0, 0,
											secondBackIc4.getImage().getWidth(null),
											secondBackIc4.getImage().getHeight(null));

									b22 = new Back(secondBackIc4.getImage(), secondBackIc4.getImage().getWidth(null), 0,
											secondBackIc4.getImage().getWidth(null),
											secondBackIc4.getImage().getHeight(null));

						
							
								}
							}).start();

						} else if (mapLength > mapLengthList.get(1) * 40 + 800
								&& mapLength < mapLengthList.get(2) * 40 + 800
								&& b11.getImage() != backIc3.getImage()) {
					
							new Thread(new Runnable() {

								@Override
								public void run() {

									backFadeOut();

									b11 = new Back(backIc3.getImage(), 0, 0, backIc3.getImage().getWidth(null),
											backIc3.getImage().getHeight(null));

									b12 = new Back(backIc3.getImage(), backIc3.getImage().getWidth(null), 0,
											backIc3.getImage().getWidth(null), backIc3.getImage().getHeight(null));

									b21 = new Back(secondBackIc3.getImage(), 0, 0,
											secondBackIc3.getImage().getWidth(null),
											secondBackIc3.getImage().getHeight(null));

									b22 = new Back(secondBackIc3.getImage(), secondBackIc3.getImage().getWidth(null), 0,
											secondBackIc3.getImage().getWidth(null),
											secondBackIc3.getImage().getHeight(null));

									backFadeIn();
				
								}
							}).start();

						} else if (mapLength > mapLengthList.get(0) * 40 + 800
								&& mapLength < mapLengthList.get(1) * 40 + 800
								&& b11.getImage() != backIc2.getImage()) {
							

							new Thread(new Runnable() {

								@Override
								public void run() {

									backFadeOut();

									b11 = new Back(backIc2.getImage(), 0, 0, backIc2.getImage().getWidth(null),
											backIc2.getImage().getHeight(null));

									b12 = new Back(backIc2.getImage(), backIc2.getImage().getWidth(null), 0,
											backIc2.getImage().getWidth(null), backIc2.getImage().getHeight(null));

									b21 = new Back(secondBackIc2.getImage(), 0, 0,
											secondBackIc2.getImage().getWidth(null),
											secondBackIc2.getImage().getHeight(null));

									b22 = new Back(secondBackIc2.getImage(), secondBackIc2.getImage().getWidth(null), 0,
											secondBackIc2.getImage().getWidth(null),
											secondBackIc2.getImage().getHeight(null));

									backFadeIn();
									
								}
							}).start();
						}
					

					// 배경이미지 변경을 위한 맵이동 길이 측정
					mapLength += gameSpeed;

					if (b11.getX() < -(b11.getWidth() - 1)) { // 배경1-1 이 -(배경넓이)보다 작으면, 즉 화면밖으로 모두나가면 배경 1-2뒤에 붙음
						b11.setX(b11.getWidth());
					}
					if (b12.getX() < -(b12.getWidth() - 1)) { // 배경1-2 가 -(배경넓이)보다 작으면, 즉 화면밖으로 모두나가면 배경 1-1뒤에 붙음
						b12.setX(b12.getWidth());
					}

					if (b21.getX() < -(b21.getWidth() - 1)) { // 배경1-1 이 -(배경넓이)보다 작으면, 즉 화면밖으로 모두나가면 배경 1-2뒤에 붙음
						b21.setX(b21.getWidth());
					}
					if (b22.getX() < -(b22.getWidth() - 1)) { // 배경1-2 가 -(배경넓이)보다 작으면, 즉 화면밖으로 모두나가면 배경 1-1뒤에 붙음
						b22.setX(b22.getWidth());
					}

					// 배경의 x좌표를 -1 해준다 (왼쪽으로 흐르는 효과)
					b11.setX(b11.getX() - gameSpeed / 3);
					b12.setX(b12.getX() - gameSpeed / 3);

					b21.setX(b21.getX() - gameSpeed * 2 / 3);
					b22.setX(b22.getX() - gameSpeed * 2 / 3);
					
					if (escKeyOn) { // esc키를 누르면 게임이 멈춘다
						while (escKeyOn) {
							try {
								Thread.sleep(10);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}

					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			}
		}).start();
	}
	private void hit() {
		
	}
	private void fall() {
		
	}
	private void goup() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				long t1 = Util.getTime();
				long t2;
				int set = 1;
				int upY = 1;
				
				while(upY >= 0) {
					t2 = Util.getTime() - t1;
					upY = set - (int)((t2)/40);
					c1.setY(c1.getY()-upY);
					
					if (escKeyOn) {
						long tempT1 = Util.getTime();
						long tempT2 = 0;
						while (escKeyOn) {
							try {
								Thread.sleep(10);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						tempT2 = Util.getTime() - tempT1;
						t1 = t1 + tempT2;
					}

					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
			}
		}).start();
	}
	private void godown() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				long t1 = Util.getTime();
				long t2;
				int set = 1;
				int upY = 1;
				
				while(upY >= 0) {
					t2 = Util.getTime() - t1;
					upY = set - (int)((t2)/40);
					c1.setY(c1.getY()+ upY);
					
					if (escKeyOn) {
						long tempT1 = Util.getTime();
						long tempT2 = 0;
						while (escKeyOn) {
							try {
								Thread.sleep(10);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						tempT2 = Util.getTime() - tempT1;
						t1 = t1 + tempT2;
					}

					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
			}
		}).start();
	}
	private void outLineSound() {
		
        new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
		            
		        	AudioInputStream stream = AudioSystem.getAudioInputStream(soundFile);
			        Clip clip = AudioSystem.getClip();
			        clip.open(stream);
			        clip.start();
			            
			    } catch(Exception e) {
			            
			          e.printStackTrace();
			    }
				
			}
		}).start();
	    
        
	}
	
	private void outLineChair() {
		
	}
	private void outLineDisplay() {
		
	}
	
	
	private void backFadeOut() {
		
	}
	private void backFadeIn() {
		
	}
	
	
	
}