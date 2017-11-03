package com.store.common.pojo;

import java.util.List;
/**
 * 此类用于返回符合让EasyUi数据格式(total:4;rows:{.......})
 * @author 麦苗
 *
 */
public class EUDataGridResult {
	private long total;
	private List<?> rows;
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public List<?> getRows() {
		return rows;
	}
	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	
}
