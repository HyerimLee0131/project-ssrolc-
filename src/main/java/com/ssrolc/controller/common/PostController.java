package com.ssrolc.controller.common;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.ssrolc.domain.post.Post;
import com.ssrolc.utils.PageUtil;



@Controller
public class PostController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PostController.class);
	
	@Value("${openapi.post.key}")
	private String serviceKey;
	
	@Value("${openapi.post.url}")
	private String postOpenApiUrl;
	

	@RequestMapping(value="/post",method={RequestMethod.GET,RequestMethod.HEAD})
	public String view(Model model){
		//해더에 스크립트 추가
		List<String> headerScript = new ArrayList<>();
		headerScript.add("post");
		
		model.addAttribute("headerScript",headerScript);
		
		return "common/post";
	}
	
	@RequestMapping(value="/post/{srchwrd}/{pageNum}",method={RequestMethod.GET,RequestMethod.HEAD},produces="application/json")
	@ResponseBody
	public ResponseEntity<Post> getAddress(@PathVariable String srchwrd,@PathVariable int pageNum){
		RestTemplate restTemplate = new RestTemplate();
		Post post = restTemplate.getForObject(postOpenApiUrl,Post.class,serviceKey,10,pageNum,srchwrd);
		
		PageUtil pageUtil = new PageUtil(pageNum,post.getPostHeader().getTotalCount(),post.getPostHeader().getCountPerPage(),10);
		post.setStartPageNum(pageUtil.getStartPageNum());
		post.setEndPageNum(pageUtil.getEndPageNum());
		
		return ResponseEntity.ok(post);
	}
	
	
}
