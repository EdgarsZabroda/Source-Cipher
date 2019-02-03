package org.source.cipher.dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import org.source.cipher.keyinfo.KeyInfo;
import org.source.cipher.keylisteners.dialogs.CancelKeyGeneration;

public class KeyInfoDialog extends JDialog
{
	private static final long serialVersionUID=0x8679FEDDL;
	
	private JCheckBox jcbYesNo;
	private JTextField jtfKeyLength;
	private JTextArea jtaPrimaryKey;
	private JTextArea jtaSecondaryKey;
	private String sKeyFile;
	
	public KeyInfoDialog(Dialog fParent, boolean bModule, String sKeyFile)
	{
		super(fParent, bModule);
		
		this.sKeyFile = sKeyFile;
		
		setTitle("KeyInformation");
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		JLabel jlSingleKey = new JLabel("Single key:");
		this.jcbYesNo = new JCheckBox();
		JLabel jlKeyLength = new JLabel("Key length:");
		this.jtfKeyLength = new JTextField(10);
		JLabel jlPrimaryKey = new JLabel("Primary key:");
		this.jtaPrimaryKey = new JTextArea(3, 15);
		JLabel jlSecondaryKey = new JLabel("Secondary Key:");
		this.jtaSecondaryKey = new JTextArea(3, 15);
		JScrollPane jscPrimaryKey = new JScrollPane(jtaPrimaryKey,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		JScrollPane jscSecondaryKey = new JScrollPane(jtaSecondaryKey,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		JButton jbOK = new JButton("OK");
		
		jcbYesNo.setEnabled(false);
		jtfKeyLength.setEditable(false);
		jtfKeyLength.setBackground(Color.GRAY);
		jtaPrimaryKey.setLineWrap(true);
		jtaPrimaryKey.setWrapStyleWord(true);
		jtaPrimaryKey.setEditable(false);
		jtaPrimaryKey.setBackground(Color.GRAY);
		jtaSecondaryKey.setEditable(false);
		jtaSecondaryKey.setBackground(Color.GRAY);
		jtaSecondaryKey.setLineWrap(true);
		jtaSecondaryKey.setWrapStyleWord(true);
		jbOK.addActionListener(new CancelKeyGeneration(this));
		
		setTitle("Key information");
		
		JPanel jPanel = new JPanel();
		jPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.insets = new Insets(15, 15, 0, 15);
		jPanel.add(jlSingleKey, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.CENTER;
		jPanel.add(jcbYesNo, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.EAST;
		jPanel.add(jlKeyLength, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.CENTER;
		jPanel.add(jtfKeyLength, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.anchor = GridBagConstraints.EAST;
		jPanel.add(jlPrimaryKey, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.anchor = GridBagConstraints.CENTER;
		jPanel.add(jscPrimaryKey, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.anchor = GridBagConstraints.EAST;
		jPanel.add(jlSecondaryKey, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.anchor = GridBagConstraints.CENTER;
		jPanel.add(jscSecondaryKey, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gbc.anchor = GridBagConstraints.CENTER;
		jPanel.add(jbOK, gbc);
		
		getContentPane().add(jPanel, BorderLayout.NORTH);
		pack();
		setVisible(true);
	}
	
	public void GetInfo()
	{
		try
		{
			KeyInfo keyInfo = new KeyInfo(this.sKeyFile, this.jcbYesNo, this.jtfKeyLength, this.jtaPrimaryKey, this.jtaSecondaryKey);
			keyInfo.fetch();
		}
		catch(FileNotFoundException FNFE)
		{
			FNFE.printStackTrace();
		}
		catch(IOException IO)
		{
			IO.printStackTrace();
		}
	}
}