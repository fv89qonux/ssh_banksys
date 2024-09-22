package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="t_Yewu")
public class Yewu implements Serializable{

	private static final long serialVersionUID = -7141419035239709511L;

	private long id;
	
	private Zhanghu zhanghu;
	
	private String type;

	
	private String content;
	
	private Date createtime;
	
	
	private User user;
	

	
	@ManyToOne
	@JoinColumn(name="userid")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	@ManyToOne
	@JoinColumn(name="zhanghuid")
	public Zhanghu getZhanghu() {
		return zhanghu;
	}

	public void setZhanghu(Zhanghu zhanghu) {
		this.zhanghu = zhanghu;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}



	
	
}
