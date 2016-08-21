//------------------------------------------------------------------------------
// Name:        Basic text editor written in Java
// Purpose:     
//
// Created:     12/10/2014
// Copyright:   (c) Copyright Michy 2014
// Licence:     GNU GPL
/*
    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.
    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.
    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

// Class User Interface: manages the entire UI

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JMenuBar;
import javax.swing.border.Border;

public class UserInterface extends JFrame implements ActionListener
{
	// Class variables. Visual items for the UI
	static String D;
	private float boxFontsize = 10f;
	private String cwd = "C:\\users\\";
	private JMenuBar menubar = new JMenuBar();
	private JMenu menuFile = new JMenu("File");
	private JMenu menuAbout = new JMenu("About");
	private JMenu menuEdit = new JMenu("Edit");
	private JMenuItem item1 = new JMenuItem("Open");
	private JMenuItem item2 = new JMenuItem("Show current working directory");
	private JMenuItem item3 = new JMenuItem("Set current working directory");
	private JMenuItem item4 = new JMenuItem("Exit");
	private JMenuItem edit1 = new JMenuItem("Smaller fontsize");
	private JMenuItem edit2 = new JMenuItem("Bigger fontsize");
	private JMenuItem about = new JMenuItem("About");
	private JTextArea textBox = new JTextArea(20,5);
																		// A scroll panel containes the textBox to make it "scrollable"
	private JScrollPane textBoxContainer = new JScrollPane(textBox);
	private JTextField textField = new JTextField(20);
	private JButton loadButton = new JButton("Load .txt file data");
	private JButton saveButton = new JButton("Save .txt data");
	private JLabel label1 = new JLabel("Enter/edit the text below: ",JLabel.CENTER);
	private JLabel label2 = new JLabel("Enter the name of the file to save:");
	private Font font = new Font("Verdana",Font.BOLD,12);
	
	// Constructor: Loads the interface	
	public UserInterface()
	{
		// Main frame window
		JFrame window = new JFrame("Simple text editor 1.0");
		window.setBounds(40,40,700,600);
		
		// Window Layout
		BorderLayout mainLayout = new BorderLayout();
		window.setLayout(mainLayout);
		
		// Panels border
		Border simpleBorder = BorderFactory.createLineBorder(Color.BLUE);
		
		// Upper panel (with its own layout)
		JPanel upperPanel = new JPanel();
		upperPanel.setLayout(new GridLayout(1,3,2,10));
		JPanel middlePanel = new JPanel();
		
		// Labels
		label1.setForeground(Color.black);
		upperPanel.add(label1);
		upperPanel.add(middlePanel);
		upperPanel.add(loadButton);
		window.add(upperPanel,BorderLayout.NORTH);
		
		// Center panel
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BorderLayout());
		centerPanel.add(textBoxContainer,BorderLayout.CENTER);
		centerPanel.setBorder(simpleBorder);
		textBox.setFont(font);
		window.add(centerPanel,BorderLayout.CENTER);
		
		// Bottom panel
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(0,3,10,20));
		bottomPanel.add(label2);
		bottomPanel.add(textField);
		bottomPanel.add(saveButton);
		window.add(bottomPanel,BorderLayout.SOUTH);
		
		// Menu settings
		item1.addActionListener(this);
		item2.addActionListener(this);
		item3.addActionListener(this);
		item4.addActionListener(this);
		edit1.addActionListener(this);
		edit2.addActionListener(this);
		about.addActionListener(this);
		menuFile.add(item1);
		menuFile.add(item2);
		menuFile.add(item3);
		menuFile.add(item4);
		menuEdit.add(edit1);
		menuEdit.add(edit2);
		menuAbout.add(about);
		menubar.add(menuFile);
		menubar.add(menuEdit);
		menubar.add(menuAbout);
		window.setJMenuBar(menubar);
		
		// Button action listeners
		loadButton.addActionListener(this);
		saveButton.addActionListener(this);
		
		// Final main window frame settings
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	// Event handler: handles all the events occuring in the UI
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == item1)
		{
			JFileChooser chooser = new JFileChooser(".");
			if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
			{
				System.out.println("Loading text...");
				
				JFileChooser chooser1 = new JFileChooser(".");
				if(chooser1.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
				{
					this.openFile(chooser1.getSelectedFile().getAbsolutePath());
				}
			}
		}
		if(e.getSource() == item2)
		{
			JOptionPane.showMessageDialog(null,"Current working directory: "+this.cwd);
		}
		if(e.getSource() == item3)
		{
			JFileChooser dChooser = new JFileChooser();
			dChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			if(dChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
			{
				this.setCwd(dChooser);
			}
			
		}
		if(e.getSource() == item4)
		{
			System.exit(0);
		}
		if(e.getSource() == edit1)
		{
			if(this.boxFontsize > 8)
			{
				this.boxFontsize -= 2f;
				textBox.setFont(textBox.getFont().deriveFont(this.boxFontsize));
			}
		}
		if(e.getSource() == edit2)
		{
			if(this.boxFontsize < 65)
			{
				this.boxFontsize += 2f;
				textBox.setFont(textBox.getFont().deriveFont(this.boxFontsize));
			}
		}
		if(e.getSource() == about)
		{
			// Show about
			this.showAbout();
		}
		if(e.getSource() == loadButton)
		{
			System.out.println("Loading text...");
			
			JFileChooser chooser = new JFileChooser(".");
			if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
			{
				this.openFile(chooser.getSelectedFile().getAbsolutePath());
			}
		}
		if(e.getSource() == saveButton)
		{
			System.out.println("Saving data to file...");
			String filename = textField.getText();
			if("".equals(filename))
			{
				filename = "file1";
			}
			
			int dialogResult = JOptionPane.showConfirmDialog(null,"Save file in cwd?");
			
			if(dialogResult == JOptionPane.YES_OPTION)
			{
				this.checkBeforeSave(this.cwd+"\\"+filename+".txt");
			}else if(dialogResult == JOptionPane.NO_OPTION)
			{
				System.out.println("No clicked");
				JFileChooser dChooser2 = new JFileChooser();
				dChooser2.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				if(dChooser2.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
				{
					String p = dChooser2.getSelectedFile().getAbsolutePath();
					// System.out.println(p);
					this.checkBeforeSave(p+"\\"+filename+".txt");
				}
			}
		}
	}
	
	// Change working directory method
	private void setCwd(JFileChooser directChooser)
	{
		this.cwd = directChooser.getSelectedFile().getAbsolutePath();
		String confirmation = "Current working directory changed to "+this.cwd;
		JOptionPane.showMessageDialog(null,confirmation);
	}
	
	// Open file method: gets the path and loads the file on the screen
	private void openFile(String path)
	{
		ReadData r = new ReadData(path);
		r.open();
		r.read();
		D = ReadData.data;
		textBox.setText(D);
		System.out.println("Text loaded succesfully!");
	}
	
	// Save file method
	private void saveFile(String path)
	{
		SaveData myFile = new SaveData(path);
		String textToSave = textBox.getText();
		myFile.save(textToSave);
		System.out.println("Data saved to file succesfully!");
		JOptionPane.showMessageDialog(null,"File succesfully saved into "+path);
	}
	
	// Checks if the file already exists and handles the problem in case it occurs
	private void checkBeforeSave(String path)
	{
		File f = new File(path);
		if(f.exists())
		{
			int dialogResult = JOptionPane.showConfirmDialog(null,"A file with this name seems to exist already. Overwrite it?");
			if(dialogResult == JOptionPane.YES_OPTION)
			{
				this.saveFile(path);
			}else
			{
				JOptionPane.showMessageDialog(null,"Please change filename before saving");
			}
		}else
		{
			this.saveFile(path);
		}
	}
	
	// Show about method
	private void showAbout()
	{
		String aboutInfo = "About:\n"
				+ "Application name: Simple Text Editor Java application\n"
				+ "Author: Mic\n"
				+ "Version: 1.0\n"
				+ "License: GNU GPL\n"
				+ "For more information and to contact the author, please visit: www.firsttimeprogrammer.blogspot.com\n";
		JOptionPane.showMessageDialog(null,aboutInfo);
	}
}
