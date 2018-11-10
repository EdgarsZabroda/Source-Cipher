package org.source.cipher.algorithm;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import javax.swing.JLabel;

public class Encode
{
	private String sKey;
	private JLabel jlProgress;
	
	public Encode(String sKey, JLabel jlProgress)
	{
		this.sKey = sKey;
		this.jlProgress = jlProgress;
	}
	
	public Encode(String sKey)
	{
		this.sKey = sKey;
		this.jlProgress = null;
	}
	
	void Commit(String sFile) throws IOException
	{
		byte bSingle[] = new byte[1];
		byte bSplittedByteInfo[];
		short sBytes;
		byte Key[];
		byte bSplittedByteShuffledKey[];
		short sShuffledOffsets[];
		File KeyFile = new File(this.sKey);
		File TargetFile = new File(sFile);
		RandomAccessFile rafKey = new RandomAccessFile(KeyFile, "r");
		RandomAccessFile rafTargetFile = new RandomAccessFile(TargetFile, "rw");
		
		rafKey.read(bSingle, 0, 1);
		
		if(bSingle[0] == 1)
		{
			bSplittedByteInfo = new byte[2];
			rafKey.read(bSplittedByteInfo, 1, 2);
			
			sBytes = (short)(((bSplittedByteInfo[0])<<8) | bSplittedByteInfo[1] & 0x00FF);
			Key = new byte[sBytes];
			
			rafKey.read(Key, 3, (int)sBytes);
			
			int nOffset = 0;
			int nDone = 0;
			int nRemaining = (int)TargetFile.length();
			int nSize = (int)TargetFile.length();
			byte bChunk[] = new byte[sBytes*1024*1024];
			
			while(nRemaining != 0)
			{	
				if(nRemaining >= (sBytes*1024*1024))
				{
					rafTargetFile.read(bChunk, nOffset, (sBytes*1024*1024));
					short sKeyOffset = 0;
					
					for(int x=0;x<bChunk.length;x++)
					{
						if(sKeyOffset == sBytes)
						{
							sKeyOffset = 0;
						}
						
						bChunk[x] ^= Key[sKeyOffset];
						
						sKeyOffset++;
						
						if(jlProgress != null && sKeyOffset == sBytes)
						{
							nDone += sBytes;
							String sDone = Integer.toString(nDone);
							String sAll = Integer.toString(nSize);
							this.jlProgress.setText("Processing " +
									sDone + "/" + sAll);
						}
					}
					
					rafTargetFile.seek(nOffset);
					rafTargetFile.write(bChunk);
					
					nOffset += sBytes*1024*1024;
					nRemaining -= sBytes*1024*1024;
				}
				else
				{
					rafTargetFile.read(bChunk, nOffset, nRemaining);
					short sKeyOffset = 0;
					
					for(int x=0;x<nRemaining;x++)
					{
						if(sKeyOffset == sBytes)
						{
							sKeyOffset = 0;
						}
						
						bChunk[x] ^= Key[sKeyOffset];
						
						sKeyOffset++;
						
						if(jlProgress != null && sKeyOffset == sBytes)
						{
							nDone += sBytes;
							String sDone = Integer.toString(nDone);
							String sAll = Integer.toString(nSize);
							this.jlProgress.setText("Processing " +
									sDone + "/" + sAll);
						}
					}
					
					rafTargetFile.seek(nOffset);
					rafTargetFile.write(bChunk);
					
					nRemaining = 0;
				}
			}
		}
		else
		{
			bSplittedByteInfo = new byte[2];
			rafKey.read(bSplittedByteInfo, 1, 2);
			
			sBytes = (short)(((bSplittedByteInfo[0])<<8) | bSplittedByteInfo[1] & 0x00FF);
			Key = new byte[sBytes];
			bSplittedByteShuffledKey = new byte[sBytes * 2];
			sShuffledOffsets = new short[sBytes];
			
			
			rafKey.read(Key, 3, sBytes);
			rafKey.read(bSplittedByteShuffledKey, (3+sBytes), (sBytes*2));
			
			short sMergeToShortIndex = 0;
			
			for(int x=0;x<sBytes;x++)
			{
				sShuffledOffsets[x] = (short)(((bSplittedByteShuffledKey[sMergeToShortIndex]) << 8) | 0x00FF & bSplittedByteShuffledKey[sMergeToShortIndex]);
				sMergeToShortIndex += 2;
			}
			
			int nOffset = 0;
			int nDone = 0;
			int nRemaining = (int)TargetFile.length();
			int nTotal = (int)TargetFile.length();
			byte bChunk[] = new byte[sBytes*1024*1024];
			
			while(nRemaining != 0)
			{
				if(nRemaining >= (sBytes*1024*1024))
				{
					rafTargetFile.read(bChunk, nOffset, (sBytes*1024*1024));
					short sKeyIndex = 0;
					
					for(int x=0;x<bChunk.length;x++)
					{
						if(sKeyIndex == sBytes)
						{
							sKeyIndex = 0;
						}
						
						bChunk[x] ^= Key[sKeyIndex];
						bChunk[x] ^= Key[sShuffledOffsets[sKeyIndex]];
						
						sKeyIndex++;
						
						if(this.jlProgress != null && sKeyIndex == sBytes)
						{
							nDone += sBytes;
							String sDone = Integer.toString(nDone);
							String sAll = Integer.toString(nTotal);
							this.jlProgress.setText("Processing " +
									sDone + "/" + sAll);
						}
					}
					
					rafTargetFile.seek(nOffset);
					rafTargetFile.write(bChunk);
					
					nOffset += sBytes*1024*1024;
					nRemaining -= sBytes*1024*1024;
				}
				else
				{
					rafTargetFile.read(bChunk, nOffset, nRemaining);
					short sKeyIndex = 0;
					
					for(int x=0;x<nRemaining;x++)
					{
						if(sKeyIndex == sBytes)
						{
							sKeyIndex = 0;
						}
						
						bChunk[x] ^= Key[sKeyIndex];
						bChunk[x] ^= Key[sShuffledOffsets[sKeyIndex]];
						
						sKeyIndex++;
						
						if(this.jlProgress != null && sKeyIndex == sBytes)
						{
							nDone += sBytes;
							String sDone = Integer.toString(nDone);
							String sAll = Integer.toString(nTotal);
							this.jlProgress.setText("Processing " +
									sDone + "/" + sAll);
						}
					}
					
					rafTargetFile.seek(nOffset);
					rafTargetFile.write(bChunk);
					
					nRemaining = 0;
				}
			}
		}
		
		rafKey.close();
		rafTargetFile.close();
	}
	
	public GenerateKey CreateKey(short sBytes)
	{
		return new GenerateKey(this.sKey, sBytes);
	}
}