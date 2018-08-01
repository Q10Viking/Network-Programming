package org.q10viking.nine;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class DevelopGUI extends JFrame {
	private JTextField searchString = new JTextField(30);
	private JTextArea names = new JTextArea(15, 80);
	private String url;
	private Develop server;
	
	public DevelopGUI(String url){
		super("Develop tools");
		searchString.setEditable(false);
		//names
		Font f = new Font("Monospaced", Font.PLAIN, 12);
	    names.setFont(f);
	    names.setEditable(false);

	    
	    JPanel centerPanel = new JPanel();
	    centerPanel.setLayout(new GridLayout(1, 1, 10, 10));
	    JScrollPane jsp = new JScrollPane(names);
	    centerPanel.add(jsp);
	    
	    //searchString
	    searchString.setText(url);
	    JPanel northPanelTop = new JPanel();
	    northPanelTop.setLayout(new FlowLayout(FlowLayout.LEFT));
	    northPanelTop.add(new JLabel("url: "));
	    northPanelTop.add("North", searchString);
	    
	    
	    Container pane = this.getContentPane();
	    pane.add("North", northPanelTop);
	    pane.add("Center", centerPanel);   
	    
	    try {
			URL ll = new URL(url);
			String filename;
			if(ll.getFile().equals("")) {
				filename="/";
			}else {
				 filename = ll.getFile();
			}
			String host = ll.getHost();
			int port = ll.getPort();
			server = new Develop(host,port,filename);
			
			System.out.println(filename);
			System.out.println(host);
			System.out.println(port);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	   String result = server.sendRequest();
	   names.setText(result);
	  // System.out.println(result);
	}
	
	
	public static void main(String[] args) {
		DevelopGUI develop = new DevelopGUI("http://localhost:8083/testIndex.html");
		develop.pack();
		develop.setVisible(true);
	}

}
