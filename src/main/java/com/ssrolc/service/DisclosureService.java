package com.ssrolc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssrolc.domain.disclosure.Disclosure;
import com.ssrolc.repository.DisclosureRepository;

@Service
public class DisclosureService {

	@Autowired
	private DisclosureRepository disclosureRepository;
	/*
	 * 정보공개서 열람증 열기(팝업)
	 * */
	public Disclosure getDisclosureByIdx(int aidx) {

		return disclosureRepository.findDisclosure(aidx);
	}
		

}
