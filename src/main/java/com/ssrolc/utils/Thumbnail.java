package com.ssrolc.utils;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ssrolc.controller.ssrolcmanager.LoginController;


/*
 * 
 * 만든이 : 변준영
 * 
 * 일자   : 2015-08-13
 * 
 * 용도   : 썸네일 생성
 * 
 */
public class Thumbnail {
	private static final Logger logger = LoggerFactory.getLogger(Thumbnail.class);
	
	public Map<String, Object> thumbnailMake(String path, String fileName, int thumbWidth) {
		Map<String, Object> resultMap = new HashMap<>(); 
		String thumbPath = path+"\\thumb";
		
		try {
			//디렉토리 없으면 생성
			File thumbDir = new File(thumbPath);
			if(!thumbDir.exists()) {
				thumbDir.mkdirs();
			}
			
			//원본파일
			File originFile = new File(path+"\\"+fileName);
			
			//원본파일 버퍼 생성
			BufferedImage originFileBuffer = ImageIO.read(originFile);
			
			//썸네일 높이 계산
			int originWidth = originFileBuffer.getWidth();
			int originHeight = originFileBuffer.getHeight();
			int thumbHeight = originHeight * thumbWidth / originWidth;
			
			//썸네일파일 버퍼 생성
			BufferedImage thumbFileBuffer = new BufferedImage(thumbWidth, thumbHeight, BufferedImage.TYPE_3BYTE_BGR); 
			
			//확장자 체크 및 원본 경로
			int lastIndex = originFile.getName().lastIndexOf(".");
			String fileExt = originFile.getName().substring(lastIndex + 1);
			
			//썸네일 파일 생성
			File thumbFile = new File(thumbPath+"\\"+"thumb_"+fileName);
			Graphics2D graphic = thumbFileBuffer.createGraphics();
			graphic.drawImage(originFileBuffer, 0, 0, thumbWidth, thumbHeight, null);
			ImageIO.write(thumbFileBuffer, fileExt, thumbFile);
			
			//원본파일 정보 맵에 입력
			resultMap.put("originFileName", fileName);
			resultMap.put("originWidth", originWidth);
			resultMap.put("originHeight", originHeight);
			resultMap.put("originFileSize", originFile.length());
			
			//썸네일파일 정보 맵에 입력
			resultMap.put("thumbFileName", "thumb_"+fileName);
			resultMap.put("thumbWidth", thumbWidth);
			resultMap.put("thumbHeight", thumbHeight);
			resultMap.put("thumbFileSize", thumbFile.length());
			
			//logger.debug(String.valueOf(thumbFile.length()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultMap;
	}
}
