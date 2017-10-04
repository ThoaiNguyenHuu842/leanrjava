package com.ohhay.core.criteria;

/**
 * @author: ThoaiVt
 * @create: 30-05-2017
 */
public class AdmininstrationComponent {
	private int id;
	private int libSort;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLibSort() {
		return libSort;
	}
	public void setLibSort(int libSort) {
		this.libSort = libSort;
	}
	@Override
	public String toString() {
		return "AdmininstrationComponent [id=" + id + ", libSort=" + libSort + "]";
	}
	
	
}
