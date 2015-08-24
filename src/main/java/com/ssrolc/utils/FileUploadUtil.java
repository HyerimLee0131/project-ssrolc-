package com.ssrolc.utils;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
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

import com.google.common.primitives.Ints;
import com.ssrolc.domain.common.UploadFileInfo;
import com.ssrolc.exception.FileCanNotUploadException;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class FileUploadUtil {
	private static final Logger logger = LoggerFactory.getLogger(FileUploadUtil.class);
	private static final String IMAGETYPEMODE = "image";
	private static final String DOCTYPEMODE = "doc";
	
	private MultipartHttpServletRequest mhRequest;
	private String uploadFileTypeMode;
	/*
	 * 업로드될 path   /home/upload/boards/notice 여기까지 받아와야함
	 */
	private String uploadPath;
	private int thumbWidth;
	private List<UploadFileInfo> UploadFileInfoList;
	
	
	public List<UploadFileInfo> doFileUpload(){
		Map<String,MultipartFile>  mhsMap = mhRequest.getFileMap();	
		if(!mhsMap.isEmpty()){
			Iterator<String> uploadFileIterator = mhsMap.keySet().iterator();
			
			while (uploadFileIterator.hasNext()) {
				String uploadFileParamName = (String) uploadFileIterator.next();
				MultipartFile multipartFile = mhRequest.getFile(uploadFileParamName);
				
				if(!multipartFile.isEmpty()){
					boolean thumbType = false;
					if(uploadFileParamName.startsWith("thumb")){
						thumbType = true;
					}					
					
					if(!doUpload(multipartFile,thumbType)){
						throw new FileCanNotUploadException();
					}
					
				}
			}
		}
		
		return UploadFileInfoList;
	}
	
	private boolean doUpload(MultipartFile uploadFile,boolean thumbType){
		
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
	
					UploadFileInfo uploadFileInfo = getFileInfo(uploadPath,originalFilename,newFileName,fileContentType,thumbWidth,thumbType);
					UploadFileInfoList.add(uploadFileInfo);
				}
				
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return true;
		}
	}
	
	private UploadFileInfo getFileInfo(String path,String originalFilename, String newFileName
										,String fileContentType, int thumbWidth,boolean thumbType) {
		
		UploadFileInfo uploadFileInfo = new UploadFileInfo();
		uploadFileInfo.setOriginalFilename(originalFilename);
		
		if(thumbType && Arrays.asList(mineTypeImages()).contains(fileContentType)){
			
			String thumbPath = path+File.separator+"thumb";
			
			try {
				//디렉토리 없으면 생성
				addDir(thumbPath);
				
				//원본파일
				File originFile = new File(path+File.separator+newFileName);
				
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
				
				//썸네일 파일명
				String thumFileName = "thumb_"+newFileName;
								
				//썸네일 파일 생성	
				File thumbFile = new File(thumbPath+File.separator+thumFileName);
				Graphics2D graphic = thumbFileBuffer.createGraphics();
				graphic.drawImage(originFileBuffer, 0, 0, thumbWidth, thumbHeight, null);
				ImageIO.write(thumbFileBuffer, fileExt, thumbFile);
				
				//썸네일파일 정보 맵에 입력
				uploadFileInfo.setConvertFileName(thumFileName);
				uploadFileInfo.setWidth(thumbWidth);
				uploadFileInfo.setHeight(thumbHeight);
				uploadFileInfo.setSize(Ints.checkedCast(thumbFile.length()));
				uploadFileInfo.setFileType(IMAGETYPEMODE);
				uploadFileInfo.setThumbType(thumbType);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			File saveFile = new File(path+File.separator+newFileName);

			uploadFileInfo.setSize(Ints.checkedCast(saveFile.length()));
			uploadFileInfo.setConvertFileName(newFileName);

			if (Arrays.asList(mineTypeImages()).contains(fileContentType)) {
				try {
					BufferedImage saveFileBuffer = ImageIO.read(saveFile);
					
					uploadFileInfo.setWidth(saveFileBuffer.getWidth());
					uploadFileInfo.setHeight(saveFileBuffer.getHeight());
					uploadFileInfo.setFileType(IMAGETYPEMODE);
					uploadFileInfo.setThumbType(thumbType);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				uploadFileInfo.setWidth(0);
				uploadFileInfo.setHeight(0);
				uploadFileInfo.setFileType(DOCTYPEMODE);
				uploadFileInfo.setThumbType(thumbType);
			}
		}
		
		return uploadFileInfo;
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
		if(IMAGETYPEMODE.equals(uploadFileTypeMode)){
			if(Arrays.asList(mineTypeImages()).contains(fileContentType)){
				return true;
			}
		}else if(DOCTYPEMODE.equals(uploadFileTypeMode)){
			if(Arrays.asList(mineTypeDoc()).contains(fileContentType)){
				return true;
			}
		}else{
			if(Arrays.asList(mineTypeImages()).contains(fileContentType) || Arrays.asList(mineTypeDoc()).contains(fileContentType)){
				return true;
			}
		}
		return false;
	}
	
	
	private void addDir(String uploadPath){
		File dir = new File(uploadPath);
		if(!dir.exists()){
			dir.mkdirs();
		}
	}
	/**
	 * 문서 타입 mine type
	 * @return
	 */
	private String[] mineTypeDoc(){
		String doc[] = new String[20]; 
		doc[0] = "application/msword";
		doc[1] = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
		doc[2] = "application/excel";
		doc[3] = "application/vnd.ms-excel";
		doc[4] = "application/x-excel";
		doc[5] = "application/x-msexcel";
		doc[6] = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
		doc[7] = "application/vnd.ms-powerpoint";
		doc[8] = "application/powerpoint";
		doc[9] = "application/mspowerpoint";
		doc[10] = "application/x-mspowerpoint";
		doc[11] = "application/vnd.openxmlformats-officedocument.presentationml.presentation";
		doc[12] = "application/zip";
		doc[13] = "application/x-zip-compressed";
		doc[14] = "application/x-compressed";
		doc[15] = "multipart/x-zip";
		doc[16] = "application/haansofthwp";
		doc[17] = "application/x-hwp";
		doc[18] = "application/octet-stream";
		doc[19] = "application/pdf";
		return doc;
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
}
