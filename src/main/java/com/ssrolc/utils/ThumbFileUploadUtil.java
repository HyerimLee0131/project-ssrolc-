package com.ssrolc.utils;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ssrolc.domain.prmedia.ThumbUpdateInfo;
import com.ssrolc.exception.FileCanNotUploadException;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class ThumbFileUploadUtil {
	private static final Logger logger = LoggerFactory.getLogger(ThumbFileUploadUtil.class);
	
	private MultipartHttpServletRequest mhsRequest;
	private String uploadPath;
	private List<ThumbUpdateInfo> thumbInfoList;
	private int thumbWidth;
	
	public List<ThumbUpdateInfo> doFileUpload(){
		Map<String,MultipartFile>  mhsMap = mhsRequest.getFileMap();	
		if(!mhsMap.isEmpty()){
			Iterator<String> uploadFileIterator = mhsMap.keySet().iterator();
			
			while (uploadFileIterator.hasNext()) {
				String uploadFileParamName = (String) uploadFileIterator.next();
				MultipartFile multipartFile = mhsRequest.getFile(uploadFileParamName);
				
				if(!multipartFile.isEmpty()){
					if(!doUpload(multipartFile)){
						throw new FileCanNotUploadException();
					}
					
		
				}
			}
		}
		
		return thumbInfoList;
	}
	
	private boolean doUpload(MultipartFile uploadFile){
		
		String fileContentType =  uploadFile.getContentType();

		if(!canUploadType(fileContentType)){
			throw new FileCanNotUploadException();
		}else{
			String originalFilename = uploadFile.getOriginalFilename();
			
			String ext = fileExt(originalFilename);
			String newFileName = getNewFileName()+"."+ext;
			
			addDir(uploadPath);
			
			String filePath = uploadPath+File.separator+newFileName;
			
			try {
				File saveFilePath = new File(filePath);
				uploadFile.transferTo(saveFilePath);
				
				if(saveFilePath.canRead()){
					
					Map<String,Object> thumbNailInfo = thumbnailMake(uploadPath,newFileName,thumbWidth);
					
					ThumbUpdateInfo thumbInfo  = new ThumbUpdateInfo(originalFilename, newFileName, (int)thumbNailInfo.get("originWidth"), (int)thumbNailInfo.get("originWidth")
							, Integer.parseInt(thumbNailInfo.get("originFileSize").toString())
							, thumbNailInfo.get("thumbFileName").toString()
							, (int)thumbNailInfo.get("thumbWidth")
							, (int)thumbNailInfo.get("thumbHeight")
							, Integer.parseInt(thumbNailInfo.get("thumbFileSize").toString()));
					thumbInfoList.add(thumbInfo);
				}
			
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return true;
		}
	}
	
	private String getNewFileName(){
		return UUID.randomUUID().toString().replaceAll("-","");
	}
	
	/**
	 * 확장자 구하기
	 */
	private String fileExt(String originalFilename){
		int pos = originalFilename.lastIndexOf(".");
		
		String ext = originalFilename.substring(pos+1);
		return ext;
	}
	
	private boolean canUploadType(String fileContentType){
		
			if(Arrays.asList(mineTypeImages()).contains(fileContentType)){
				return true;
			}else{
				return false;
			}
	}
	
	
	private void addDir(String uploadPath){
		File dir = new File(uploadPath);
		if(!dir.exists()){
			dir.mkdirs();
		}
	}
		
	/**
	 * 이미지 mine type
	 * @return
	 */
	private String[] mineTypeImages(){
		String images[] = new String[4];
		images[0] = "image/gif";
		images[1] = "image/jpeg";
		images[2] = "image/pjpeg";
		images[3] = "image/png";
		return images;
	}
	
	private Map<String, Object> thumbnailMake(String path, String fileName, int thumbWidth) {
		Map<String, Object> resultMap = new HashMap<>(); 
		
		String thumbPath = path+File.separator+"thumb";
		
		try {
			//디렉토리 없으면 생성
			File thumbDir = new File(thumbPath);
			if(!thumbDir.exists()) {
				thumbDir.mkdirs();
			}
			
			//원본파일
			File originFile = new File(path+File.separator+fileName);
			
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
			File thumbFile = new File(thumbPath+File.separator+"thumb_"+fileName);
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
