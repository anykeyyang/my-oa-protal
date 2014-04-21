package org.ssh.vo;

import java.util.List;

public class Page<T> {
	/**
	 * 总页数
	 */
	int totalcount;
	/**
	 * 頁數
	 */
	int pageNo;
	/**
	 * 每页条数
	 */
	int pageSize;
	/**
	 * 结果
	 */
	List<T> result;

	public int getTotalcount() {
		return totalcount;
	}

	public void setTotalcount(int totalcount) {
		this.totalcount = totalcount;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}

}
