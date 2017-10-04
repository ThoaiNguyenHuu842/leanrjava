package com.ohhay.piepme.mongo.entities.other;

import java.io.Serializable;
import java.util.List;

/**
 * @author ThoaiVt
 * @date 07-07-2017
 */
public class R100VAMG implements Serializable {
	private int totalSent;
	private List<R100VMG> r100vSent;
	private int totalView;
	private List<R100VMG> r100vView;
	private int totalUsed;
	private List<R100VMG> r100vUsed;

	public int getTotalSent() {
		return totalSent;
	}

	public void setTotalSent(int totalSent) {
		this.totalSent = totalSent;
	}

	public List<R100VMG> getR100vmgsSent() {
		return r100vSent;
	}

	public void setR100vmgsSent(List<R100VMG> r100vmgsSent) {
		this.r100vSent = r100vmgsSent;
	}

	public int getTotalView() {
		return totalView;
	}

	public void setTotalView(int totalView) {
		this.totalView = totalView;
	}

	public List<R100VMG> getR100vmgsView() {
		return r100vView;
	}

	public void setR100vmgsView(List<R100VMG> r100vmgsView) {
		this.r100vView = r100vmgsView;
	}

	public int getTotalUsed() {
		return totalUsed;
	}

	public void setTotalUsed(int totalUsed) {
		this.totalUsed = totalUsed;
	}

	public List<R100VMG> getR100vmgsUsed() {
		return r100vUsed;
	}

	public void setR100vmgsUsed(List<R100VMG> r100vmgsUsed) {
		this.r100vUsed = r100vmgsUsed;
	}

	@Override
	public String toString() {
		return "R100VAMG [totalSent=" + totalSent + ", r100vSent=" + r100vSent + ", totalView=" + totalView
				+ ", r100vView=" + r100vView + ", totalUsed=" + totalUsed + ", r100vUsed=" + r100vUsed + "]";
	}
}
