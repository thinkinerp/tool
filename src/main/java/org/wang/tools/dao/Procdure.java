package org.wang.tools.dao;

import java.util.List;

import org.wang.tools.vo.PocedureVo;


public interface Procdure {
	List<PocedureVo> getProcedureInfo(String procedureName);
}
