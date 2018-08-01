package org.q10viking.nine;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Stack;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.event.HyperlinkEvent;

import org.q10viking.eight.Whois;
import org.q10viking.eight.WhoisGUI;

public class EditorPaneFrame extends JFrame {
	private static final int DEFAULT_WIDTH = 700;
	private static final int DEFAULT_HEIGHT = 600;
	
	public EditorPaneFrame() {
		super("Q10Viking:Client");
		setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
		final Stack<String> urlStack = new Stack<>();
		final JEditorPane editorPane = new JEditorPane();
		final JTextField url = new JTextField(30);
		
		//setup hyperlink listener
		editorPane.setEditable(false);
		editorPane.addHyperlinkListener(event->{
			if(event.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
				try {
					//remember URL for back button
					urlStack.push(event.getURL().toString());
					//show URL in text field
					url.setText(event.getURL().toString());
					editorPane.setPage(event.getURL());
				}catch(IOException e) {
					editorPane.setText("Exception: "+e);
				}
				
			}
		});
		
		final JCheckBox editable = new JCheckBox();
		editable.addActionListener(event->
			editorPane.setEditable(editable.isSelected()));
		
		//set up load button for loading URL
		ActionListener listener = event->{
			try {
				//remember URL for back button
				urlStack.push(url.getText());
				editorPane.setPage(url.getText());
			}catch(IOException e) {
				editorPane.setText("Exception: "+e);
			}
		};
		
		
		JButton loadButton = new JButton("Load");
		loadButton.addActionListener(listener);
		url.addActionListener(listener);
		
		//set up back button and button action
		JButton backButton = new JButton("Back");
		backButton.addActionListener(event->{
			if(urlStack.size()<=1) return;
			try {
				//get URL from back button
				urlStack.pop();
				//show URL in text field
				String urlString = urlStack.peek();
				url.setText(urlString);
				editorPane.setPage(urlString);
			}catch(IOException e) {
				editorPane.setText("Exception: "+e);
			}
		});
		
		//develop button
		JButton developButton = new JButton("Develop");
		developButton.addActionListener(event->{
	
			      System.out.println(url.getText());
			      DevelopGUI a = new DevelopGUI(url.getText());
			      a.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			      a.pack();
			      a.setVisible(true);

		});
		
		add(new JScrollPane(editorPane),BorderLayout.CENTER);
		//put all control components in a panel
		JPanel panel = new JPanel();
		
		panel.add(new JLabel("URL"));
		panel.add(url);
		panel.add(loadButton);
		panel.add(backButton);
		panel.add(new JLabel("Editable"));
		panel.add(editable);
		panel.add(developButton);
		
		add(panel,BorderLayout.SOUTH);
	}
	
	
	public static void main(String[] args) {
		EditorPaneFrame browser = new EditorPaneFrame();
		browser.setVisible(true);
	}
}
