package dao.impl;

import java.sql.SQLException;
import java.util.List;

import model.Zhanghu;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import dao.ZhanghuDao;









public class ZhanghuDaoImpl extends HibernateDaoSupport implements  ZhanghuDao{


	public void deleteBean(Zhanghu Zhanghu) {
		this.getHibernateTemplate().delete(Zhanghu);
		
	}

	public void insertBean(Zhanghu Zhanghu) {
		this.getHibernateTemplate().save(Zhanghu);
		
	}

	@SuppressWarnings("unchecked")
	public Zhanghu selectBean(String where) {
		List<Zhanghu> list = this.getHibernateTemplate().find("from Zhanghu " +where);
		if(list.size()==0){
			return null;
		}
		return list.get(0);
	}

	public int selectBeanCount(String where) {
		long count = (Long)this.getHibernateTemplate().find("select count(*) from Zhanghu "+where).get(0);
		return (int)count;
	}

	@SuppressWarnings("unchecked")
	public List<Zhanghu> selectBeanList(final int start,final int limit,final String where) {
		return (List<Zhanghu>)this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(final Session session)throws HibernateException, SQLException {				
				List<Zhanghu> list = session.createQuery("from Zhanghu "+where)
				.setFirstResult(start)
				.setMaxResults(limit)
				.list();
				return list;
			}
		});
	}

	public void updateBean(Zhanghu Zhanghu) {
		this.getHibernateTemplate().update(Zhanghu);
		
	}
	
	
}
