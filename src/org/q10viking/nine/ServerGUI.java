package org.q10viking.nine;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ServerGUI extends JFrame {
	private JTextField port = new JTextField(20);
	private JTextArea names = new JTextArea(15,80);
	private Logger logger = Logger.getLogger(ServerGUI.class.getCanonicalName());
	public ServerGUI() {
		super("Q10Viking-SERVER.2.0.0");
		
		//names
		Font f = new Font("Monospaced", Font.PLAIN, 12);
	    names.setFont(f);
	    names.setEditable(false);

	    
	    JPanel centerPanel = new JPanel();
	    centerPanel.setLayout(new GridLayout(1, 1, 10, 10));
	    JScrollPane jsp = new JScrollPane(names);
	    centerPanel.add(jsp);
	    
	    //port
	    port.setEditable(true);
		JPanel north = new JPanel();
		north.setLayout(new FlowLayout(FlowLayout.LEFT));
		north.add(new JLabel("port: "));
		north.add(port);
		
		
		//startButton
		JButton startButton = new JButton("start");
		north.add(startButton);
		startButton.addActionListener(event->{

			//得到文档根
			File docroot;
			try {
				docroot = new File("./");
			}catch(ArrayIndexOutOfBoundsException ex) {
				
				return;
			}
			int ported = Integer.parseInt(port.getText());
			
			try {
				JHTTP webServer = new JHTTP(docroot,ported);
				webServer.start();
			}catch(IOException e) {
				logger.log(Level.SEVERE,"Server could not start",e);
			}
			
		});
		//closeButton
		JButton closeButton = new JButton("close");
		north.add(closeButton);
		closeButton.addActionListener(event->{
				System.exit(0);
		});
		
		
		Container pane = this.getContentPane();
		pane.add("North",north);
		pane.add("Center", centerPanel);
	    
	}
	
	public static void main(String[] args) {
		ServerGUI gui = new ServerGUI();
		gui.pack();
		gui.setVisible(true);
	}
}
