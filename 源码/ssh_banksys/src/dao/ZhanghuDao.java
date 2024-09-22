package dao;

import java.util.List;

import model.Zhanghu;


public interface ZhanghuDao  {
	
	
	
	public void insertBean(Zhanghu Zhanghu);
	
	public void deleteBean(Zhanghu Zhanghu);
	
	public void updateBean(Zhanghu Zhanghu);

	public Zhanghu selectBean(String where);
	
	public List<Zhanghu> selectBeanList(final int start, final int limit,final String where);
	
	public int selectBeanCount(final String where);
	
	
}
