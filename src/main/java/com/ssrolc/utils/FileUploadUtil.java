package com.ssrolc.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
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

import com.ssrolc.domain.board.AttachFile;
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
	
	private MultipartHttpServletRequest mhsRequest;
	private String uploadFileTypeMode;
	private String uploadPath;
	private List<AttachFile> attachFileList;
	private String boardTable;
	private int articleNo;
	private boolean editFlag;
	private String fileFormat;
	private String regId;
	private String regIp;
	private Timestamp regDate;
	
	
	
	public List<AttachFile> doFileUpload(){
		Map<String,MultipartFile>  mhsMap = mhsRequest.getFileMap();	
		if(!mhsMap.isEmpty()){
			Iterator<String> uploadFileIterator = mhsMap.keySet().iterator();
			
			int sort = 1;
			
			while (uploadFileIterator.hasNext()) {
				String uploadFileParamName = (String) uploadFileIterator.next();
				MultipartFile multipartFile = mhsRequest.getFile(uploadFileParamName);
				
				if(!doUpload(multipartFile,sort)){
					throw new FileCanNotUploadException();
				}
				
				sort++;
			}
		}
		
		return attachFileList;
	}
	
	private boolean doUpload(MultipartFile uploadFile,int sort){
		
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
				Map<String,Object> fileInfoMap = fileInfo(saveFilePath,fileContentType);
				attachFileList.add(new AttachFile(boardTable, articleNo, editFlag, sort
						, originalFilename, newFileName,0,Integer.parseInt(fileInfoMap.get("fileSize").toString())
						,Integer.parseInt(fileInfoMap.get("fileWidth").toString())
						,Integer.parseInt(fileInfoMap.get("fileHeight").toString()),ext,fileFormat, regId, regIp,regDate));
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return true;
		}
	}
	
	private Map<String,Object> fileInfo(File saveFilePath,String fileContentType){
		Map<String,Object> fileInfoMap = new HashMap<>();
		if(saveFilePath.canRead()){
			fileInfoMap.put("fileSize",saveFilePath.length());
			
			if(Arrays.asList(mineTypeImages()).contains(fileContentType)){
				try {
					BufferedImage saveFileBuffer = ImageIO.read(saveFilePath);
					
					fileInfoMap.put("fileWidth",saveFileBuffer.getWidth());
					fileInfoMap.put("fileHeight",saveFileBuffer.getWidth());
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				fileInfoMap.put("fileWidth",0);
				fileInfoMap.put("fileHeight",0);
			}
		}
		
		return fileInfoMap;
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
