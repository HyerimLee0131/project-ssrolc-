package com.ssrolc.service;

import java.util.*;

import javax.mail.internet.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.mail.javamail.*;
import org.springframework.stereotype.*;
import org.springframework.ui.freemarker.*;

import com.ssrolc.utils.mail.*;

import freemarker.template.*;

@Service
public class MailService implements RegistrationNotifier {
	
//	private final static String name = "##NAME##";
//	private final static String securityKey = "##SECURITY_KEY##";
	private final static String INFO_TITLE = " [안내]스스로러닝센터입니다. ";
//	private final static String INFO_BODY = "<style> p {color:red} </style> 안녕하세요. "
//			+ name + "님!<br/><p>인증번호는 " + securityKey + " 입니다.</p> ";

	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private Configuration freemarkerConfiguration;

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	@Override
	public String sendMail(String pMemName,String pEmailId, String pEmailAdd1, String hostName) {
		String name = pMemName;
		String mailAddress = "";
		String template = "/ssrolcmanager/mail/emailContent.ftl";
		mailAddress = pEmailId + "@" + pEmailAdd1;
		String authKey = "";
		authKey = makeAuthKey();
		
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message,
					true, "utf-8");
			messageHelper.setSubject(MailService.INFO_TITLE);
			messageHelper.setFrom("mail@jei.com", "스스로러닝센터");
			HashMap<String, String> model = new HashMap<String, String>();
			model.put("name", name);
			model.put("securityKey", authKey);
			model.put("hostName", hostName);

			//String text = MailService.INFO_BODY.replaceAll(MailService.name,
			//		name);
			//text = text.replaceAll(MailService.securityKey, makeAuthKey());
			
			String text = FreeMarkerTemplateUtils.processTemplateIntoString(
					freemarkerConfiguration.getTemplate(template, "UTF-8"),
					model);
			message.setContent(text, "text/html; charset=utf-8");
			messageHelper.setTo(new InternetAddress(mailAddress, "utf-8"));
			mailSender.send(message);
	
			//sendMail2(model,"/ssrolcmanager/mail/emailContent.ftl",mailAddress);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return authKey;
	}

	
	/*private void sendMail2(Map model, String template,String mailAddress) {

		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message,
					true, "utf-8");
			messageHelper.setSubject(MailService.INFO_TITLE);
			messageHelper.setFrom("mail@jei.com", "스스로러닝센터");
       
			String text = FreeMarkerTemplateUtils.processTemplateIntoString(
					freemarkerConfiguration.getTemplate(template, "UTF-8"),
					model);

			message.setContent(text, "text/html; charset=utf-8");
			messageHelper.setTo(new InternetAddress(mailAddress, "TEST"));
			mailSender.send(message);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	

	// 인증번호 생성함수
	public String makeAuthKey() {
		String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghizklmnopqrstuvwxyz";
		Random rnd = new Random();

		StringBuilder sb = new StringBuilder(8);
		for (int i = 0; i < 8; i++) {
			sb.append(AB.charAt(rnd.nextInt(AB.length())));
		}
		return sb.toString();
	}
	



}
