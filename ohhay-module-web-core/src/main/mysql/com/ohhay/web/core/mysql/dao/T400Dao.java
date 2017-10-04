package com.ohhay.web.core.mysql.dao;

public interface T400Dao {
	int insertTabT400(int pt400, String tv401, String tv402, String tv403, String tv404, String tv405, int fo100, int fc800, int fd000, String pvLogin);
	int applyNewTemplate(int fo100, int pt400, String pvLogin);
}
