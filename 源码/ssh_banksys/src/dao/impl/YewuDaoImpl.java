package dao.impl;

import java.sql.SQLException;
import java.util.List;

import model.Yewu;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import dao.YewuDao;









public class YewuDaoImpl extends HibernateDaoSupport implements  YewuDao{


	public void deleteBean(Yewu Yewu) {
		this.getHibernateTemplate().delete(Yewu);
		
	}

	public void insertBean(Yewu Yewu) {
		this.getHibernateTemplate().save(Yewu);
		
	}

	@SuppressWarnings("unchecked")
	public Yewu selectBean(String where) {
		List<Yewu> list = this.getHibernateTemplate().find("from Yewu " +where);
		if(list.size()==0){
			return null;
		}
		return list.get(0);
	}

	public int selectBeanCount(String where) {
		long count = (Long)this.getHibernateTemplate().find("select count(*) from Yewu "+where).get(0);
		return (int)count;
	}

	@SuppressWarnings("unchecked")
	public List<Yewu> selectBeanList(final int start,final int limit,final String where) {
		return (List<Yewu>)this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(final Session session)throws HibernateException, SQLException {				
				List<Yewu> list = session.createQuery("from Yewu "+where)
				.setFirstResult(start)
				.setMaxResults(limit)
				.list();
				return list;
			}
		});
	}

	public void updateBean(Yewu Yewu) {
		this.getHibernateTemplate().update(Yewu);
		
	}
	
	
}
