package com.ssrolc.repository;

import com.ssrolc.domain.disclosure.Disclosure;


@PrimaryRepositoryAnnoInterface
public interface DisclosureRepository {
	/*
	 * 정보공개서 열람증 열기(팝업)
	 */
	public Disclosure findDisclosure(int aidx);
}
