package com.mgj.base;

/**
 * 排序的方法：升序或降序
 */
public enum Dir implements BaseEnum {

	ASC("从小到大"),

	DESC("从大到小");

	private String display;

	private Dir(String display) {
		this.display = display;
	}

	@Override
	public String display() {
		return display;
	}
}
