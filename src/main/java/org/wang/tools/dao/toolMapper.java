package org.wang.tools.dao;

import java.util.List;
import org.wang.tools.vo.Tables;

public interface toolMapper {
	List<Tables> getTableInfo(String tableName);
}
