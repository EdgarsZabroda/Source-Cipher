package org.source.cipher.dialogs;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.source.cipher.keylisteners.dialogs.CancelKeyGeneration;

public class AboutDialog extends JDialog
{
	private static final long serialVersionUID = 0x8679FEDDL;
	
	public AboutDialog(Frame Parent, boolean bModule)
	{
		super(Parent, bModule);
		
		setTitle("About Source Cipher");
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		JLabel jlAbout1 = new JLabel("Source Cipher written in Java");
		JLabel jlAbout2 = new JLabel("Version 5.1");
		JButton jbOK = new JButton("OK");
		
		jbOK.addActionListener(new CancelKeyGeneration(this));
		
		JPanel jPanel = new JPanel();
		jPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(15, 15, 0, 0);
		gbc.anchor = GridBagConstraints.CENTER;
		jPanel.add(jlAbout1, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.CENTER;
		jPanel.add(jlAbout2, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.anchor = GridBagConstraints.CENTER;
		jPanel.add(jbOK, gbc);
		
		getContentPane().add(jPanel, BorderLayout.NORTH);
		pack();
		setVisible(true);
	}
}