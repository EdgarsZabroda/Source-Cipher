package org.source.cipher.keyinfo;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class KeyInfo
{
	private String sFile;
	private JTextArea jtaPrimaryKey;
	private JTextArea jtaSecondaryKey;
	private JTextField jtfKeyLenght;
	private JCheckBox jcbSingle;
	
	public KeyInfo(String sFile, JCheckBox jcbSingle, JTextField jtfKeyLength,
			JTextArea jtaPrimaryKey, JTextArea jtaSecondaryKey)
	{
		this.sFile = sFile;
		this.jcbSingle = jcbSingle;
		this.jtfKeyLenght = jtfKeyLength;
		this.jtaPrimaryKey = jtaPrimaryKey;
		this.jtaSecondaryKey = jtaSecondaryKey;
	}
	
	public void fetch() throws IOException
	{
		File KeyFile = new File(this.sFile);
		RandomAccessFile rafKeyFile = new RandomAccessFile(KeyFile, "r");
		byte bSingle[] = new byte[1];
		byte bSplitedBytes[] = new byte[2];
		short sBytes;
		byte Key[];
		byte bOffsetsInBytes[];
		short sOffsets[];
		
		rafKeyFile.read(bSingle, 0, 1);
		rafKeyFile.read(bSplitedBytes, 1, 2);
		
		sBytes = (short)(((bSplitedBytes[1])<<8)|(bSplitedBytes[0]&0x00FF));
		
		if(bSingle[0] == 0x01)
		{
			this.jcbSingle.setSelected(true);
		}
		
		String strBytes = Integer.toString((int)sBytes);
		
		this.jtfKeyLenght.setText(strBytes);
		
		Key = new byte[sBytes];
		
		rafKeyFile.read(Key, 3, ((int)sBytes));
		
		StringBuilder sbKey = new StringBuilder();
		
		for(byte bByte : Key)
		{
			sbKey.append(bByte);
		}
		
		this.jtaPrimaryKey.setText(sbKey.toString());
		
		if(bSingle[0] == 0x00)
		{
			bOffsetsInBytes = new byte[sBytes*2];
			sOffsets = new short[sBytes];
			
			rafKeyFile.read(bOffsetsInBytes, 2+sBytes, sBytes*2);
			
			int nSplittedIndex = 0;
			
			for(int x=0;x<sBytes;x++)
			{
				sOffsets[x] = (short)(((bOffsetsInBytes[nSplittedIndex+1])<<8)|(bOffsetsInBytes[nSplittedIndex]&0x00FF));
				nSplittedIndex += 2;
			}
			
			byte bSecondaryKey[] = new byte[sBytes];
			
			for(int x=0;x<sBytes;x++)
			{
				bSecondaryKey[x] = Key[sOffsets[x]];
			}
			
			StringBuilder sbSKey = new StringBuilder();
			
			for(byte Byte : bSecondaryKey)
			{
				sbSKey.append(Byte);
			}
			
			this.jtaSecondaryKey.setText(sbSKey.toString());
		}
		
		rafKeyFile.close();
	}
}