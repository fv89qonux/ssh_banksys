package dao;

import java.util.List;

import model.Yewu;


public interface YewuDao  {
	
	
	
	public void insertBean(Yewu Yewu);
	
	public void deleteBean(Yewu Yewu);
	
	public void updateBean(Yewu Yewu);

	public Yewu selectBean(String where);
	
	public List<Yewu> selectBeanList(final int start, final int limit,final String where);
	
	public int selectBeanCount(final String where);
	
	
}
