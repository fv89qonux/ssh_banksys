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
@Table(name="t_Zhanghu")
public class Zhanghu implements Serializable{

	private static final long serialVersionUID = -7141419035239709511L;

	private long id;
	
	private String zhanghuhao;
	
	private User user;
	
	private double jine;
	
	private Date createtime;
	
	private int zhanghulock;
	
	private String beizhu;
	
	private String truename;
	
	private String lianxifangshi;
	
	private String dizhi;
	
	private String time;
	
	private String password;
	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getTruename() {
		return truename;
	}

	public void setTruename(String truename) {
		this.truename = truename;
	}

	public String getLianxifangshi() {
		return lianxifangshi;
	}

	public void setLianxifangshi(String lianxifangshi) {
		this.lianxifangshi = lianxifangshi;
	}

	public String getDizhi() {
		return dizhi;
	}

	public void setDizhi(String dizhi) {
		this.dizhi = dizhi;
	}

	public String getBeizhu() {
		return beizhu;
	}

	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}

	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getZhanghuhao() {
		return zhanghuhao;
	}

	public void setZhanghuhao(String zhanghuhao) {
		this.zhanghuhao = zhanghuhao;
	}

	@ManyToOne
	@JoinColumn(name="userid")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public double getJine() {
		return jine;
	}

	public void setJine(double jine) {
		this.jine = jine;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public int getZhanghulock() {
		return zhanghulock;
	}

	public void setZhanghulock(int zhanghulock) {
		this.zhanghulock = zhanghulock;
	}

	
	
}
