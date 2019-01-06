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
import javax.swing.JTextField;

import org.source.cipher.disablers.DisableGetInfo;
import org.source.cipher.keylisteners.dialogs.BrowseForKeyFile;
import org.source.cipher.keylisteners.dialogs.CancelKeyGeneration;
import org.source.cipher.keylisteners.dialogs.KeyInfoListener;

public class BeforeKeyInfo extends JDialog
{
	private static final long serialVersionUID = 0x8679FEDDL;
	
	public BeforeKeyInfo(Frame Parent, boolean bModal)
	{
		super(Parent, bModal);
		
		setTitle("SCKF Information");
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		JLabel jlSCKFFile = new JLabel("SCKF File:");
		JTextField jtfSCKFFile = new JTextField(15);
		JButton jbBrowse = new JButton("Browse");
		JButton jbGetInfo = new JButton("Get info");
		JButton jbCancel = new JButton("Cancel");
		
		jbGetInfo.setEnabled(false);
		jtfSCKFFile.getDocument().addDocumentListener(new DisableGetInfo(jtfSCKFFile, jbGetInfo));
		
		jbBrowse.addActionListener(new BrowseForKeyFile(jtfSCKFFile, this));
		jbGetInfo.addActionListener(new KeyInfoListener(jtfSCKFFile, this));
		jbCancel.addActionListener(new CancelKeyGeneration(this));
		
		JPanel jPanel = new JPanel();
		jPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.insets = new Insets(15, 15, 0, 15);
		jPanel.add(jlSCKFFile, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.CENTER;
		jPanel.add(jtfSCKFFile, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		jPanel.add(jbBrowse, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gbc.anchor = GridBagConstraints.EAST;
		jPanel.add(jbGetInfo, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.WEST;
		jPanel.add(jbCancel, gbc);
		
		getContentPane().add(jPanel, BorderLayout.NORTH);
		pack();
		setVisible(true);
	}
}