package org.source.cipher;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import org.source.cipher.disablers.DisableCommit;
import org.source.cipher.helpfile.HelpFile;
import org.source.cipher.keylisteners.BrowseForSCKFFile;
import org.source.cipher.keylisteners.BrowseForTargetFile;
import org.source.cipher.keylisteners.CommitCiphering;
import org.source.cipher.keylisteners.OpenCreateSCKFDialog;
import org.source.cipher.keylisteners.WarnAboutRAM;
import org.source.cipher.keylisteners.menusliteners.Exit;
import org.source.cipher.keylisteners.menusliteners.SpawnAboutDialog;
import org.source.cipher.keylisteners.menusliteners.SpawnGenerateDialog;
import org.source.cipher.keylisteners.menusliteners.SpawnKeyInfo;

public class MainClass extends JFrame
{
	private static final long serialVersionUID = 0x8679FEDDL;
	
	public MainClass()
	{
		setSize(344, 216);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Source Cipher 5.4.0");
		
		JMenuBar jMenuBar = new JMenuBar();
		JMenu jmFile = new JMenu("File");
		JMenuItem jmiCreateSCKFFile = new JMenuItem("Create Encryption Key");
		JMenuItem jmiKeyInfo = new JMenuItem("View Encryption Key data");
		JMenuItem jmiExit = new JMenuItem("Exit");
		JMenu jmHelp = new JMenu("Help");
		JMenuItem jmiHelpTopics = new JMenuItem("Help topics");
		JMenuItem jmiAbout = new JMenuItem("About");
		JLabel jlTargetFile = new JLabel("Target File:");
		JTextField jtfTargetFile = new JTextField(15);
		JButton jbBrowseForTargetFile = new JButton("Browse");
		JLabel jlSCKFFile = new JLabel("Encryption Key File:");
		JTextField jtfSCKFFile = new JTextField(15);
		JButton jbBrowseForSCKFFile = new JButton("Browse");
		JCheckBox jcbFitToRAM = new JCheckBox("Fit Target File to RAM");
		JButton jbCreateSCKFFile = new JButton("Create");
		JButton jbEncode = new JButton("Encode");
		
		jbEncode.setEnabled(false);
		
		jMenuBar.add(jmFile);
		jMenuBar.add(jmHelp);
		jmFile.add(jmiCreateSCKFFile);
		jmFile.add(jmiKeyInfo);
		jmFile.addSeparator();
		jmFile.add(jmiExit);
		jmHelp.add(jmiHelpTopics);
		jmHelp.addSeparator();
		jmHelp.add(jmiAbout);
		
		
		jmiCreateSCKFFile.addActionListener(new SpawnGenerateDialog(this));
		jmiKeyInfo.addActionListener(new SpawnKeyInfo(this));
		jmiExit.addActionListener(new Exit());
		jmiHelpTopics.addActionListener(new HelpFile(this));
		jmiAbout.addActionListener(new SpawnAboutDialog(this));
		
		jtfTargetFile.getDocument().addDocumentListener(new DisableCommit(jtfTargetFile, jtfSCKFFile, jbEncode));
		jtfSCKFFile.getDocument().addDocumentListener(new DisableCommit(jtfTargetFile, jtfSCKFFile, jbEncode));
		
		jbBrowseForTargetFile.addActionListener(new BrowseForTargetFile(this, jtfTargetFile));
		jbBrowseForSCKFFile.addActionListener(new BrowseForSCKFFile(this, jtfSCKFFile));
		jbCreateSCKFFile.addActionListener(new OpenCreateSCKFDialog(this));		
		jcbFitToRAM.addActionListener(new WarnAboutRAM(this, jcbFitToRAM));
		jbEncode.addActionListener(new CommitCiphering(jtfTargetFile, jtfSCKFFile, jcbFitToRAM, this));		
		
		JPanel jPanel = new JPanel();
		
		jPanel.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		setJMenuBar(jMenuBar);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(15, 15, 15, 15);
		gbc.anchor = GridBagConstraints.EAST;
		jPanel.add(jlTargetFile, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.CENTER;
		jPanel.add(jtfTargetFile, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		jPanel.add(jbBrowseForTargetFile, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.EAST;
		jPanel.add(jlSCKFFile, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.CENTER;
		jPanel.add(jtfSCKFFile, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.WEST;
		jPanel.add(jbBrowseForSCKFFile, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.anchor = GridBagConstraints.CENTER;
		jPanel.add(jcbFitToRAM, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.anchor = GridBagConstraints.EAST;
		jPanel.add(jbCreateSCKFFile, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 2;
		gbc.anchor = GridBagConstraints.WEST;
		jPanel.add(jbEncode, gbc);
		
		getContentPane().add(jPanel, BorderLayout.NORTH);
		pack();
		setVisible(true);
	}
	
	public static void GUI()
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception E)
		{
			E.printStackTrace();
		}
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		MainClass.GUI();
		MainClass MC = new MainClass();
	}
}