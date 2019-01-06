package org.source.cipher.keylisteners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

public class BrowseForSCKFFile implements ActionListener
{
	private JFrame jfParent;
	private JTextField jtfSCKFFileField;
	
	public BrowseForSCKFFile(JFrame jfParent, JTextField jtfSCKFFileField)
	{
		this.jfParent = jfParent;
		this.jtfSCKFFileField = jtfSCKFFileField;
	}
	
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		JFileChooser jfcSCKFFile = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		
		FileNameExtensionFilter SCKF = new FileNameExtensionFilter("Source Cipher Encryption File (*.sckf)", "sckf");
		
		jfcSCKFFile.setAcceptAllFileFilterUsed(false);
		jfcSCKFFile.setFileFilter(SCKF);
		
		int nChoice = jfcSCKFFile.showOpenDialog(this.jfParent);
		
		if(nChoice == JFileChooser.APPROVE_OPTION)
		{
			File fSCKFFile = jfcSCKFFile.getSelectedFile();
			String SCKFFile = fSCKFFile.getAbsolutePath();
			this.jtfSCKFFileField.setText(SCKFFile);
		}
	}
}