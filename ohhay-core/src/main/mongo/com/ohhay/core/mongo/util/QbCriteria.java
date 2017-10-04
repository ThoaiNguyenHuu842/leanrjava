package com.ohhay.core.mongo.util;

/**
 * @author ThoaiNH
 *
 */
public class QbCriteria {
	public static final String TYPE_NE = "$ne";
	public static final String TYPE_EXISTS = "$exists";
	private String conditionField;
	private Object value;
	private String type;//$ne: not equal
	public QbCriteria(String conditionField, Object value) {
		super();
		this.conditionField = conditionField;
		this.value = value;
	}
	public String getConditionField() {
		return conditionField;
	}
	public void setConditionField(String conditionField) {
		this.conditionField = conditionField;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "QbCriteria [conditionField=" + conditionField + ", value="
				+ value + ", type=" + type + "]";
	}
	
}	
