package org.source.cipher.keylisteners.dialogs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

public class SaveSCKFFile implements ActionListener
{
	private JTextField jtfSaveSCKFFile;
	private JDialog jdParent;
	
	public SaveSCKFFile(JTextField jtfSaveSCKFFile, JDialog jdParent)
	{
		this.jdParent = jdParent;
		this.jtfSaveSCKFFile = jtfSaveSCKFFile;
	}
	
	@Override
	public void actionPerformed(ActionEvent AE)
	{
		JFileChooser jFileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		
		jFileChooser.setDialogTitle("Save encryption key to");
		jFileChooser.setAcceptAllFileFilterUsed(false);
		
		FileNameExtensionFilter fnefSCKFFilter = new FileNameExtensionFilter("Source Cipher Key File (*.sckf)", "sckf");
		
		jFileChooser.addChoosableFileFilter(fnefSCKFFilter);
		
		int nCapture = jFileChooser.showSaveDialog(this.jdParent);
		
		if(nCapture == JFileChooser.APPROVE_OPTION)
		{
			File file = jFileChooser.getSelectedFile();
			this.jtfSaveSCKFFile.setText(file.getAbsolutePath());
		}
	}
}