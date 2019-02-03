package org.source.cipher.helpfile;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class HelpFile implements ActionListener
{
	private JFrame jParent;
	
	public HelpFile(JFrame jParent)
	{
		this.jParent = jParent;
	}
	
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		String JarFileName = HelpFile.class.getProtectionDomain().getCodeSource().getLocation().getPath().replaceAll("%20", " ");
			
		try
		{
			@SuppressWarnings("resource")
			JarFile jarFile = new JarFile(JarFileName, true);
			ZipEntry jarEntry = jarFile.getEntry("org/source/cipher/helpfile/manual.pdf");
			
			if(jarEntry != null)
			{
				String JarPath = System.getProperty("user.dir");
				File Dest = new File(JarPath, jarEntry.getName());
				Dest = new File(JarPath, Dest.getName());
				
				if(Dest.exists())
				{
					if(Desktop.isDesktopSupported())
					{
						Desktop.getDesktop().open(Dest);
					}
					else
					{
						JOptionPane.showMessageDialog(this.jParent, "Unable to launch the manual, try opening the \"manual.pdf\" manually", "Error opening the manual file", JOptionPane.ERROR_MESSAGE);
					}
				}
				else
				{
					InputStream Input = new BufferedInputStream(jarFile.getInputStream(jarEntry));
					OutputStream Output = new BufferedOutputStream(new FileOutputStream(Dest));
					
					byte Buffer[] = new byte[2048];
					
					for(;;)
					{
						int nBytes = Input.read(Buffer);
						
						if(nBytes <= 0)
						{
							break;
						}
						
						Output.write(Buffer, 0, nBytes);
					}
					
					Output.flush();
					Output.close();
					Input.close();
					
					if(Desktop.isDesktopSupported())
					{
						Desktop.getDesktop().open(Dest);
					}
					else
					{
						JOptionPane.showMessageDialog(this.jParent, "Unable to launch the manual, try opening the \"manual.pdf\" manually", "Error opening the manual file", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
			else
			{
				JOptionPane.showMessageDialog(this.jParent, "Jar handle returned null", "Critical Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		catch(IOException IOE)
		{
			IOE.printStackTrace();
		}
	}
}