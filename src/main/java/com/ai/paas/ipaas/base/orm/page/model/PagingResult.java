package com.ai.paas.ipaas.base.orm.page.model;

import java.util.ArrayList;
import java.util.List;

public class PagingResult<T> {
	// 当前页
	private int currentPage;
	// 总共记录条数
	private int totalSize;
	// 结果集
	private List<T> resultList = new ArrayList<T>();

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}

	public List<T> getResultList() {
		return resultList;
	}

	public void setResultList(List<T> resultList) {
		this.resultList = resultList;
	}
}
