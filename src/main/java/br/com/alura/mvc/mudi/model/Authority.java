package br.com.alura.mvc.mudi.model;

import javax.persistence.Embeddable;

@Embeddable
public class Authority {
	private String authority;

	public Authority() {
	}

	public Authority(String authority) {
		this.authority = authority;

	}
}