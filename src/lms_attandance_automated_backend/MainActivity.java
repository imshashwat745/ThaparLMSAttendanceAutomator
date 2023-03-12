package lms_attandance_automated_backend;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.geom.Ellipse2D;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.Shape;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import java.awt.Frame;
import java.awt.Window.Type;
import javax.swing.border.EmptyBorder;
import java.awt.Cursor;
import java.awt.ComponentOrientation;
import javax.swing.border.LineBorder;

import org.checkerframework.checker.initialization.qual.Initialized;

import io.netty.handler.codec.compression.FastLzFrameDecoder;

import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;




public class MainActivity {
	
	
	private int mouseX,mouseY;
	private JFrame frame;
	private boolean canLogin;//semaphore
	public static JLabel loginBtn ;
	public static JTextField textField;
	public static int subjectIndex;
	private JComboBox comboBox;
	private JButton submitBtn;
	private static JLabel marked;
	public static int numberOfMarked;
	public static String servers[]={"ada","ramanujan","archimedes","murphy","lms"};
	public static JComboBox serverMenu;
	private JLabel txtLmsMultipleAccounts;
	private JButton txtI;
	private JLabel lblNewLabel_2;
	private Panel panel;
	private Panel panel_1;
	private JButton close;
	private JButton exit;
	private JButton maximize;
	private JButton minimize;
	private JLabel label;
	private JButton contact;
	public static void updateMarked() {
		marked.setText("Number of maked:"+numberOfMarked+"/"+MarkMultipleAccounts.list.size());
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainActivity window = new MainActivity();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainActivity() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		canLogin=true;
		numberOfMarked=0;
		App.init();
		frame = new JFrame();
		frame.setUndecorated(true);
		frame.getContentPane().setBackground(new Color(255, 255, 255));;
		
		
		loginBtn = new JLabel("All Accounts not logged in",JLabel.CENTER);
		loginBtn.setBounds(178, 60, 268, 28);
		loginBtn.setFont(new Font("Tahoma", Font.PLAIN, 17));
		loginBtn.setForeground(new Color(255, 0, 0));
		frame.setBounds(100, 100, 905, 585);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		JButton btnNewButton = new JButton("Login Accounts");
		btnNewButton.setBounds(336, 215, 123, 30);
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(0, 0, 0));
		btnNewButton.setBorderPainted(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(canLogin) {
					//if already logged in then dont login
					subjectIndex=comboBox.getSelectedIndex();
					if(subjectIndex==4) {
						JOptionPane.showMessageDialog(frame, "Please Select a subject",
					               "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					App.login(null);
					canLogin=false;				}
				
			}
		});
		
		String subjects[]= {"TOC","CG","OT","MicroProcessor","Select Subject"};
		comboBox = new JComboBox(subjects);
		comboBox.setBounds(191, 115, 268, 30);
		comboBox.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		comboBox.setBackground(new Color(255, 255, 255));
		comboBox.setBorder(null);
		comboBox.setSelectedIndex(4);
		
		textField = new JTextField();
		textField.setBounds(191, 315, 118, 30);
		textField.setText("Enter Code");  
		textField.setForeground(Color.GRAY);  
		
		
		
		
		textField.addFocusListener(new FocusAdapter() {  
			   
		       @Override  
		       public void focusGained(FocusEvent e) {  
		         if (textField.getText().equals("Enter Code")) {  
		        	 textField.setText("");   
		         } else {  
		        	 textField.setText(textField.getText());  
		         }  
		       }  
		   
		       @Override  
		       public void focusLost(FocusEvent e) {  
		         if (textField.getText().equals("Enter Code")|| textField.getText().length()==0) {  
		        	 textField.setText("Enter Code");  
		        	 textField.setForeground(Color.GRAY);  
		         } else {  
		        	 textField.setText(textField.getText());  
		        	 textField.setForeground(Color.BLACK);  
		         }  
		       }  
		     });
		textField.setColumns(10);
		
		
//		marked=new JLabel("Number of maked:/n"+numberOfMarked+"/"+MarkMultipleAccounts.list.size());
//		marked.setForeground(new Color(255, 128, 0));
//		marked.setBounds(60, 152, 89, 23);
		
		submitBtn = new JButton("Submit");
		submitBtn.setBounds(336, 315, 123, 30);
		submitBtn.setBorderPainted(false);
		submitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(canLogin) {
					//if not logged in then dont mark
					JOptionPane.showMessageDialog(frame, "First Login all accounts",
				               "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if(LoginAccounts.semaphore<MarkMultipleAccounts.list.size()) {
					//if not all accounts logged in then dont mark
					JOptionPane.showMessageDialog(frame, "App is setting up login Please Wait||",
							"Wait", JOptionPane.ERROR_MESSAGE);
					return;
				}
				loginBtn.setText("All Accounts Logged in");
				loginBtn.setForeground(new Color(0, 255, 0));
				App.mark();
			}
		});;
		submitBtn.setForeground(new Color(255, 255, 255));
		submitBtn.setBackground(new Color(30, 144, 255));
		
		serverMenu = new JComboBox(servers);
		serverMenu.setBounds(191, 215, 118, 30);
		serverMenu.setBackground(new Color(255, 255, 255));
		serverMenu.setSelectedIndex(2);
		
		panel = new Panel();
		panel.setBounds(159, 455, 746, 186);
		panel.setBackground(new Color(0, 0, 0));
		
		txtLmsMultipleAccounts = new JLabel("LMS Multiple Accounts Attendance Marker",JLabel.CENTER);
		txtLmsMultipleAccounts.setBounds(0, 25, 746, 54);
		txtLmsMultipleAccounts.setFont(new Font("Palatino Linotype", Font.PLAIN, 20));
		txtLmsMultipleAccounts.setBackground(new Color(0, 0, 0));
//		txtLmsMultipleAccounts.setText();
		txtLmsMultipleAccounts.setForeground(new Color(255, 255, 255));
		
		JLabel lblNewLabel_3 = new JLabel("© 2023 - 2024 TheOriginals - All Rights Reserved.",JLabel.CENTER);
		lblNewLabel_3.setBounds(0, 89, 746, 30);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		
		label = new JLabel("New label");
		label.setBounds(653, 122, 45, 13);
		
		panel_1 = new Panel();
		panel_1.setBounds(0, 20, 159, 575);
		panel_1.setBackground(new Color(30, 144, 255));
		
		JLabel lblNewLabel = new JLabel("Subject",JLabel.CENTER);
		lblNewLabel.setBounds(0, 95, 159, 30);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		
		txtI = new JButton();
		txtI.setBounds(0, 40, 57, 28);
		txtI.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(frame, "1.First select subject then server \n2.Then click login accounts \n3.Then enter "
						+ "code\n4.Click Submit(No.of marked will show how many people are marked\n"
						+ "4.Now for next subject to mark attandance,first restart app,then do the process again:)",
						"Guide", JOptionPane.NO_OPTION);
			}
			
		});
		txtI.setFont(new Font("Segoe Print", Font.PLAIN, 20));
		txtI.setBackground(new Color(0, 0, 0));
		txtI.setForeground(new Color(255, 255, 255));
		txtI.setText("i");
		
		 txtI.setBorderPainted(false); 
		 txtI.setContentAreaFilled(false); 
		 txtI.setFocusPainted(false); 
		 txtI.setOpaque(false);
		 
		 JLabel lblNewLabel_1 = new JLabel("Enter Code",JLabel.CENTER);
		 lblNewLabel_1.setBounds(0, 295, 159, 30);
		 lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 21));
		 lblNewLabel_1.setForeground(new Color(255, 255, 255));
		 
		 lblNewLabel_2 = new JLabel("Server",JLabel.CENTER);
		 lblNewLabel_2.setBounds(0, 195, 159, 30);
		 lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 21));
		 lblNewLabel_2.setForeground(new Color(255, 255, 255));
		 
		 marked = new JLabel("Number of marked: "+numberOfMarked+"/"+App.list.size(),JLabel.CENTER);
		 marked.setBounds(0, 481, 159, 30);
		 marked.setFont(new Font("Tahoma", Font.PLAIN, 12));
		 marked.setForeground(new Color(255, 255, 0));
		 
		 contact = new JButton();
		 contact.setBounds(0, 521, 159, 28);
		 contact.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		JOptionPane.showMessageDialog(frame, "Contact \"theoriginals.software@gmail.com\" for feedback and queries",
		 				"Contact",JOptionPane.NO_OPTION);
		 	}
		 });
		 contact.setText("Contact");
		 
		 contact.setOpaque(false);
		 contact.setForeground(Color.WHITE);
		 contact.setFont(new Font("Tahoma", Font.PLAIN, 20));
		 contact.setFocusPainted(false);
		 contact.setContentAreaFilled(false);
		 contact.setBorderPainted(false);
		 contact.setBackground(Color.BLACK);
		 frame.getContentPane().setLayout(null);
		 frame.getContentPane().add(panel_1);
		 panel_1.setLayout(null);
		 panel_1.add(txtI);
		 panel_1.add(lblNewLabel);
		 panel_1.add(lblNewLabel_2);
		 panel_1.add(lblNewLabel_1);
		 panel_1.add(marked);
		 panel_1.add(contact);
		 frame.getContentPane().add(loginBtn);
		 frame.getContentPane().add(comboBox);
		 frame.getContentPane().add(panel);
		 panel.setLayout(null);
		 panel.add(txtLmsMultipleAccounts);
		 panel.add(lblNewLabel_3);
		 panel.add(label);
		 frame.getContentPane().add(serverMenu);
		 frame.getContentPane().add(textField);
		 frame.getContentPane().add(btnNewButton);
		 frame.getContentPane().add(submitBtn);
		 
		 Panel panel_2 = new Panel();
		 panel_2.addMouseMotionListener(new MouseMotionAdapter() {
		 	@Override
		 	public void mouseDragged(MouseEvent e) {
		 		frame.setLocation(frame.getX()+e.getX()-mouseX,frame.getY()+e.getY()-mouseY);
		 	}
		 });
		 panel_2.addMouseListener(new MouseAdapter() {
		 	@Override
		 	public void mousePressed(MouseEvent e) {
		 		mouseX=e.getX();
		 		mouseY=e.getY();
		 	}
		 });
		 panel_2.setBackground(new Color(0, 0, 0));
		 panel_2.setBounds(0, 0, 905, 20);
		 frame.getContentPane().add(panel_2);
		 panel_2.setLayout(null);
		 
		 minimize = new JButton("");
		 minimize.setBounds(864, 5, 10, 10);
		 panel_2.add(minimize);
		 minimize.setBorderPainted(false);
		 minimize.setBackground(new Color(255, 255, 0));
		 
		 exit = new JButton("");
		 exit.setBounds(884, 5, 10, 10);
		 panel_2.add(exit);
		 exit.setBorderPainted(false);
		 exit.setBackground(new Color(255, 0, 0));
		 
		 
		 exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(!canLogin)App.close();
				System.exit(0);
				
			}
		});
		 
		 
		 
		 
		 minimize.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		        	
		            frame.setExtendedState(JFrame.ICONIFIED);
//		        	FadeUtilityClass.fade(frame, false);
		        }
		    });
		 
		 
		 
	}
}
