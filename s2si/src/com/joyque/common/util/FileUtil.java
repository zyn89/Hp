package com.joyque.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Random;

public class FileUtil {

	//活动图片文件夹
	public static final String HeaderActivity = "D:\\ActivityPic\\";
	//问题图片文件夹
	public static final String HeaderQuestion = "D:\\QuestionPic\\";
	//上传图片文件夹
	public static final String HeaderUplord = "D:\\UplordPic\\";
	//调研图片文件夹
	public static final String HeaderSurvey = "D:\\SurveyPic\\";
	//礼品图片文件夹
	public static final String HeaderExchange = "D:\\ExchangePic\\";
	//轮播图片文件夹
	public static final String HeaderCarousel = "D:\\CarouselPic\\";
	//一级图片文件夹
	public static final String HeaderOneLevel = "D:\\OneLevelPic\\";
	//二级图片文件夹
	public static final String HeaderTwoLevel = "D:\\TwoLevelPic\\";
	//三级图片文件夹
	public static final String HeaderThreeLevel = "D:\\ThreeLevelPic\\";
	//四级图片文件夹
	public static final String HeaderFourLevel = "D:\\FourLevelPic\\";
	
	private static String SaveFile(String uid, File file, String Type, String header, String pic) throws IOException
	{
		InputStream rtInStream;
		Date date = new Date();
		long current = date.getTime();
		Random random = new Random();
		int r = random.nextInt(10000);
		String Url = header + pic + 
		uid + "_" + current + "_" + r + "." + Type.substring(6);
		rtInStream = new FileInputStream(file);
		File outFile = new File(Url);
		FileOutputStream fos = new FileOutputStream(outFile);
		byte buffer[] = new byte[1024];
		int temp = 0;
		while((temp = rtInStream.read(buffer)) != -1) {
			fos.write(buffer, 0, temp);
		}		
		
		fos.flush();  
		fos.close();
		return Url;
	}
	
	//保存活动图片
	public static String SaveActivityStringAsMedia(String uid, File file, String Type) throws IOException
	{
		return SaveFile(uid, file, Type, HeaderActivity, DefaultValue.PicActivity);
	}
	
	//保存活动说明图片
	public static String SaveActivityDescStringAsMedia(String uid, File file, String Type) throws IOException
	{
	    return SaveFile(uid, file, Type, HeaderActivity, DefaultValue.PicActivityDesc);
	}
	
	//保存问题说明图片
	public static String SaveQuestionStringAsMedia(String uid, File file, String Type) throws IOException
	{
		return SaveFile(uid, file, Type, HeaderQuestion, DefaultValue.PicQuestion);
	}
	
	//保存用户上传的图片
	public static String SaveUplordStringAsMedia(String uid, File file, String Type) throws IOException
	{
		return SaveFile(uid, file, Type, HeaderUplord, DefaultValue.PicUplord);
	}
	
	//保存调研图片
	public static String SaveSurveyAsMedia(String uid, File file, String Type) throws IOException
	{
		return SaveFile(uid, file, Type, HeaderSurvey, DefaultValue.PicSurvey);
	}
	
	//保存调研问题图片
	public static String SaveSurveyQuestionAsMedia(String uid, File file, String Type) throws IOException
	{
		return SaveFile(uid, file, Type, HeaderSurvey, DefaultValue.PicSurveyQuestion);
	}
	
	//保存兑换图片
	public static String SaveExchangeAsMedia(String uid, File file, String Type) throws IOException
	{
		return SaveFile(uid, file, Type, HeaderExchange, DefaultValue.PicExchange);
	}
	
	//保存兑换说明图片
	public static String SaveExchangeDescAsMedia(String uid, File file, String Type) throws IOException
	{
		return SaveFile(uid, file, Type, HeaderExchange, DefaultValue.PicExchangeDesc);
	}
	
	//保存奖品图片
	public static String SavePrizeAsMedia(String uid, File file, String Type) throws IOException
	{
		return SaveFile(uid, file, Type, HeaderExchange, DefaultValue.PicPrize);
	}
	
	//保存电子奖卷图片
	public static String SavePrizeDescAsMedia(String uid, File file, String Type) throws IOException
	{
		return SaveFile(uid, file, Type, HeaderExchange, DefaultValue.PicPrizeDesc);
	}
	
	//保存抽奖说明
	public static String SaveLotteryAsMedia(String uid, File file, String Type) throws IOException
	{
		return SaveFile(uid, file, Type, HeaderExchange, DefaultValue.PicLottery);
	}
	
	//保存抽奖底牌
	public static String SaveLotteryBgAsMedia(String uid, File file, String Type) throws IOException
	{
		return SaveFile(uid, file, Type, HeaderExchange, DefaultValue.PicLotteryBg);
	}
	
	//保存轮播图片
	public static String SaveCarouselAsMedia(String uid, File file, String Type) throws IOException
	{
		return SaveFile(uid, file, Type, HeaderCarousel, DefaultValue.PicCarousel);
	}
	
	//保存一级图片
	public static String SaveOneLevelAsMedia(String uid, File file, String Type) throws IOException
	{
		return SaveFile(uid, file, Type, HeaderOneLevel, DefaultValue.PicOneLevel);
	}
	
	//保存二级图片
	public static String SaveTwoLevelAsMedia(String uid, File file, String Type) throws IOException
	{
		return SaveFile(uid, file, Type, HeaderTwoLevel, DefaultValue.PicTwoLevel);
	}
	
	//保存三级图片
	public static String SaveThreeLevelAsMedia(String uid, File file, String Type) throws IOException
	{
		return SaveFile(uid, file, Type, HeaderThreeLevel, DefaultValue.PicThreeLevel);
	}
	
	//保存四级图片
	public static String SaveFourLevelAsMedia(String uid, File file, String Type) throws IOException
	{
		return SaveFile(uid, file, Type, HeaderFourLevel, DefaultValue.PicFourLevel);
	}
}
