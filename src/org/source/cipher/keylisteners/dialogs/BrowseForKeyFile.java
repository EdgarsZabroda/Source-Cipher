package org.source.cipher.keylisteners.dialogs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

public class BrowseForKeyFile implements ActionListener
{
	private JTextField jtfBrowseKeyFileField;
	private JDialog jdParent;
	
	public BrowseForKeyFile(JTextField jtfBrowseKeyFileField, JDialog jdParent)
	{
		this.jtfBrowseKeyFileField = jtfBrowseKeyFileField;
		this.jdParent = jdParent;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		JFileChooser jfcKeyFileBrowse = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		
		jfcKeyFileBrowse.setAcceptAllFileFilterUsed(false);
		
		FileNameExtensionFilter SCKFFilter = new FileNameExtensionFilter("Source Cipher Key File (*.sckf)", "sckf");
		
		jfcKeyFileBrowse.addChoosableFileFilter(SCKFFilter);
		
		int nChoice = jfcKeyFileBrowse.showOpenDialog(this.jdParent);
		
		if(nChoice == JFileChooser.APPROVE_OPTION)
		{
			File KeyFile = jfcKeyFileBrowse.getSelectedFile();
			String sKeyFile = KeyFile.getAbsolutePath();
			jtfBrowseKeyFileField.setText(sKeyFile);
		}
	}
}