package com.joyque.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Random;

public class FileUtil {

	//活动图片文件夹
	public static final String HeaderActivity = "D:\\ActivityPic\\";
	//问题图片文件夹
	public static final String HeaderQuestion = "D:\\QuestionPic\\";
	//上传图片文件夹
	public static final String HeaderUplord = "D:\\UplordPic\\";
	
	//保存活动图片
	public static String SaveActivityStringAsMedia(String uid, byte [] mediaBytes, String Type) throws IOException
	{
		Date date = new Date();
		long current = date.getTime();
		Random random = new Random();
		int r = random.nextInt(10000);
		String Url = HeaderActivity + DefaultValue.PicActivity + 
		uid + "_" + current + "_" + r + "." + Type;
		File f=new File(Url); 
		if (f.exists()==false) 
		{  
			f.createNewFile();
		}  
		FileOutputStream fos=new FileOutputStream(f);  
		fos.write(mediaBytes); 
		fos.flush();  
		fos.close();
		return Url;
	}
	
	//保存活动说明图片
	public static String SaveActivityDescStringAsMedia(String uid, byte [] mediaBytes, String Type) throws IOException
	{
		Date date = new Date();
		long current = date.getTime();
		Random random = new Random();
		int r = random.nextInt(10000);
		String Url = HeaderActivity + DefaultValue.PicActivityDesc + 
		uid + "_" + current + "_" + r + "." + Type;
		File f=new File(Url); 
		if (f.exists()==false) 
		{  
			f.createNewFile();
		}  
		FileOutputStream fos=new FileOutputStream(f);  
		fos.write(mediaBytes); 
		fos.flush();  
		fos.close();
		return Url;
	}
	
	//保存问题说明图片
	public static String SaveQuestionStringAsMedia(String uid, byte [] mediaBytes, String Type) throws IOException
	{
		Date date = new Date();
		long current = date.getTime();
		Random random = new Random();
		int r = random.nextInt(10000);
		String Url = HeaderQuestion + DefaultValue.PicQuestion + 
		uid + "_" + current + "_" + r + "." + Type;
		File f=new File(Url); 
		if (f.exists()==false) 
		{  
			f.createNewFile();
		}  
		FileOutputStream fos=new FileOutputStream(f);  
		fos.write(mediaBytes); 
		fos.flush();  
		fos.close();
		return Url;
	}
	
	//保存用户上传的图片
	public static String SaveUplordStringAsMedia(String uid, byte [] mediaBytes, String Type) throws IOException
	{
		Date date = new Date();
		long current = date.getTime();
		Random random = new Random();
		int r = random.nextInt(10000);
		String Url = HeaderUplord + DefaultValue.PicUplord + 
		uid + "_" + current + "_" + r + "." + Type;
		File f=new File(Url); 
		if (f.exists()==false) 
		{  
			f.createNewFile();
		}  
		FileOutputStream fos=new FileOutputStream(f);  
		fos.write(mediaBytes); 
		fos.flush();  
		fos.close();
		return Url;
	}
}
