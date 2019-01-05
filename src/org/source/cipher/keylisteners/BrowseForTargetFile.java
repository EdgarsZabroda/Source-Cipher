package org.source.cipher.keylisteners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

public class BrowseForTargetFile implements ActionListener
{
	private JFrame jfParent;
	private JTextField jfTargetFileField;
	
	public BrowseForTargetFile(JFrame jfParent, JTextField jfTargetFileField)
	{
		this.jfParent = jfParent;
		this.jfTargetFileField = jfTargetFileField;
	}
	
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		JFileChooser jfcTargetFile = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		
		FileNameExtensionFilter txt = new FileNameExtensionFilter("Text files (*.txt)", "txt");
		FileNameExtensionFilter OfficeFiles = new FileNameExtensionFilter("Office files", "doc", "dot",
				"wbk", "docx", "docm", "dotx", "dotm", "docb", ".xls", "xlt", "xlm", "xlsx", "xlsm",
				"xltx", "xltm", "xlsb", "xla", "xlam", "xll", "xlw", "ppt", "pot", "pps", "pptx", "pptm",
				"potx", "potm", "ppam", "ppsx", "ppsm", "sldx", "sldm", "accdb", "accde", "accdt", "accdr",
				"mdw", "laccdb", "pub", "xps");
		FileNameExtensionFilter AudioFiles = new FileNameExtensionFilter("Audio files", "aac", "act", "amr",
				"mid", "awb", "flac", "gsm", "m4a", "m4b", "mp3", "mpc", "ogg", "oga", "mogg", "wav", "wma",
				"wv", "webm");
		FileNameExtensionFilter ImageFiles = new FileNameExtensionFilter("Image files", "jpeg", "jpg", "tiff",
				"tif", "gif", "bmp", "png", "ico", "tga", "ppm", "pgm", "pbm", "pnm", "hdr", "bpg", "xisf");
		
		jfcTargetFile.addChoosableFileFilter(txt);
		jfcTargetFile.addChoosableFileFilter(OfficeFiles);
		jfcTargetFile.addChoosableFileFilter(AudioFiles);
		jfcTargetFile.addChoosableFileFilter(ImageFiles);
		
		int nChoice = jfcTargetFile.showOpenDialog(this.jfParent);
		
		if(nChoice == JFileChooser.APPROVE_OPTION)
		{
			File fTargetFile = jfcTargetFile.getSelectedFile();
			String TargetFile = fTargetFile.getAbsolutePath();
			this.jfTargetFileField.setText(TargetFile);
		}
	}
}