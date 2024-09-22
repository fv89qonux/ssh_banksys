package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;
import model.Yewu;
import model.Zhanghu;

import org.apache.struts2.ServletActionContext;

import util.Arith;
import util.Util;


import com.opensymphony.xwork2.ActionSupport;

import dao.UserDao;
import dao.YewuDao;
import dao.ZhanghuDao;

public class ManageAction extends ActionSupport {

	private static final long serialVersionUID = -4304509122548259589L;

	private UserDao userDao;

	private String url = "./";

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	
//程序入口
	public String index() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Util.init(request);
		if (request.getSession().getAttribute("user") == null) {
			return "success2";

		} else {
			return "success1";
		}

	}
//用户登录操作
	public String login() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String role = request.getParameter("role");
		User user = userDao.selectBean(" where username = '" + username
				+ "' and password= '" + password + "' and userlock=0 and role="+role);
		if (user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			this.setUrl("index");
			return "redirect";
		} else {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
			response
					.getWriter()
					.print(
							"<script language=javascript>alert('员工号或者密码错误');window.location.href='index';</script>");
		}
		return null;
	}
//用户退出操作
	public String loginout() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		this.setUrl("login.jsp");
		return SUCCESS;
	}
//跳转到修改密码页面
	public String changepwd() {
		this.setUrl("user/user.jsp");
		return SUCCESS;
	}
//修改密码操作
	public void changepwd2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		String password3 = request.getParameter("password3");
		User bean = userDao.selectBean(" where username= '" + u.getUsername()
				+ "' and password= '" + password1 + "'");
		if (!password2.equals(password3)) {
			out.print(Util.tiaozhuan2("两次输入密码不一致", "index", ""));
			out.flush();
			out.close();
		} else if (bean != null) {
			bean.setPassword(password2);
			userDao.updateBean(bean);
			out.print(Util.tiaozhuan2("操作成功", "index", ""));
			out.flush();
			out.close();
		} else {
			out.print(Util.tiaozhuan2("原密码错误", "index", ""));
			out.flush();
			out.close();
		}
	}


	private ZhanghuDao zhanghuDao;

	public ZhanghuDao getZhanghuDao() {
		return zhanghuDao;
	}

	public void setZhanghuDao(ZhanghuDao zhanghuDao) {
		this.zhanghuDao = zhanghuDao;
	}
//账户信息列表
	public String zhanghulist() {

		HttpServletRequest request = ServletActionContext.getRequest();
		
		
		String zhanghuhao = request.getParameter("zhanghuhao");
		String truename = request.getParameter("truename");
		String zhanghulock = request.getParameter("zhanghulock");
		String time1 = request.getParameter("time1");

		StringBuffer sb = new StringBuffer();
		sb.append(" where ");
		if(truename!=null&&!"".equals(truename)){
			sb.append("truename like '%"+truename+"%'");
			sb.append(" and ");

			request.setAttribute("truename", truename);
		}
		
		if(zhanghulock!=null&&!"".equals(zhanghulock)){
			sb.append("zhanghulock like '%"+zhanghulock+"%'");
			sb.append(" and ");

			request.setAttribute("zhanghulock", zhanghulock);
		}
		
		if(zhanghuhao!=null&&!"".equals(zhanghuhao)){
			sb.append("zhanghuhao like '%"+zhanghuhao+"%'");
			sb.append(" and ");

			request.setAttribute("zhanghuhao", zhanghuhao);
		}
		
		if(time1!=null&&!"".equals(time1)){
			sb.append("time = '"+time1+"'"  );
			sb.append(" and ");

			request.setAttribute("time1", time1);
		}
		
		sb.append("  zhanghulock=0 order by id desc ");
		String where = sb.toString();
		
		int currentpage = 1;
		int pagesize = 20;
		if (request.getParameter("pageNum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pageNum"));
			pagesize = Integer.parseInt(request.getParameter("numPerPage"));
		}
		
		int total = zhanghuDao.selectBeanCount(where.replaceAll("order by id desc", ""));
		request.setAttribute("list", zhanghuDao.selectBeanList(
				(currentpage - 1) * pagesize, pagesize, where));
		request.setAttribute("totalCount", total);
		request.setAttribute("ps", pagesize);
		request.setAttribute("pn", currentpage);
		request.setAttribute("url", "method!zhanghulist");
		this.setUrl("zhanghu/zhanghulist.jsp");
		return SUCCESS;
	}
//跳转到添加账户页面
	public String zhanghuadd() {
		this.setUrl("zhanghu/zhanghuadd.jsp");
		return SUCCESS;
	}
//添加账户操作
	public void zhanghuadd2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String beizhu = request.getParameter("beizhu");
		String dizhi = request.getParameter("dizhi");
		String truename = request.getParameter("truename");
		String lianxifangshi = request.getParameter("lianxifangshi");
		String password = request.getParameter("password");
		Zhanghu bean = new Zhanghu();
		bean.setCreatetime(new Date());
		bean.setJine(0);
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		bean.setUser(user);
		bean.setZhanghuhao(new Date().getTime() + "");
		bean.setBeizhu(beizhu);
		bean.setDizhi(dizhi);
		bean.setPassword(password);
		bean.setTruename(truename);
		bean.setLianxifangshi(lianxifangshi);
		bean.setTime(Util.getTime2());
		zhanghuDao.insertBean(bean);
		Yewu yewu = new Yewu();
		yewu.setContent("创建了"+bean.getZhanghuhao()+"的账户号");
		yewu.setCreatetime(new Date());
		yewu.setType("创建账户");
		yewu.setZhanghu(bean);
		yewu.setUser(user);
		yewuDao.insertBean(yewu);
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		out.print(Util.tiaozhuan("操作成功", "method!zhanghulist", "zhanghulist"));
		out.flush();
		out.close();
	}
//跳转到修改账户信息页面
	public String zhanghuupdate() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Zhanghu bean = zhanghuDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		this.setUrl("zhanghu/zhanghuupdate.jsp");
		return SUCCESS;
	}
//修改账户信息操作
	public void zhanghuupdate2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String beizhu = request.getParameter("beizhu");
		String dizhi = request.getParameter("dizhi");
		String truename = request.getParameter("truename");
		String lianxifangshi = request.getParameter("lianxifangshi");
		String password = request.getParameter("password");
		Zhanghu bean = zhanghuDao.selectBean(" where id= "
				+ request.getParameter("id"));
		bean.setBeizhu(beizhu);
		bean.setDizhi(dizhi);
		bean.setTruename(truename);
		bean.setLianxifangshi(lianxifangshi);
		bean.setPassword(password);
		zhanghuDao.updateBean(bean);
		Yewu yewu = new Yewu();
		yewu.setContent("更新了"+bean.getZhanghuhao()+"的账户信息");
		yewu.setCreatetime(new Date());
		yewu.setType("更新账户");
		yewu.setZhanghu(bean);
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		yewu.setUser(user);
		yewuDao.insertBean(yewu);
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		out.print(Util.tiaozhuan("操作成功", "method!zhanghulist", "zhanghulist"));
		out.flush();
		out.close();
	}
//销户操作
	public void zhanghudelete() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Zhanghu bean = zhanghuDao.selectBean(" where id= "
				+ request.getParameter("id"));
		if (bean.getJine() != 0) {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.print(Util.tiaozhuan2("销户失败，该账户有余额", "method!zhanghulist",
					"zhanghulist"));
			out.flush();
			out.close();
		} 
		
		else {
			bean.setZhanghulock(1);
			zhanghuDao.updateBean(bean);
			Yewu yewu = new Yewu();
			yewu.setContent("对"+bean.getZhanghuhao()+"的账户进行销户操作");
			yewu.setCreatetime(new Date());
			yewu.setType("销户");
			yewu.setZhanghu(bean);
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			yewu.setUser(user);
			yewuDao.insertBean(yewu);
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.print(Util.tiaozhuan2("操作成功", "method!zhanghulist",
					"zhanghulist"));
			out.flush();
			out.close();
		}

	}
//跳转到存款页面
	public String zhanghuupdate3() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Zhanghu bean = zhanghuDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		this.setUrl("zhanghu/zhanghuupdate3.jsp");
		return SUCCESS;
	}
//存款操作
	public void zhanghuupdate4() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String jine = request.getParameter("jine");
		Zhanghu bean = zhanghuDao.selectBean(" where id= "
				+ request.getParameter("id"));
		bean.setJine(bean.getJine() + Double.parseDouble(jine));
		zhanghuDao.updateBean(bean);
		Yewu yewu = new Yewu();
		yewu.setContent("对"+bean.getZhanghuhao()+"的账户进行存款操作，存入"+Double.parseDouble(jine)+"元");
		yewu.setCreatetime(new Date());
		yewu.setType("存款");
		yewu.setZhanghu(bean);
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		yewu.setUser(user);
		yewuDao.insertBean(yewu);
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		out.print(Util.tiaozhuan("操作成功", "method!zhanghulist", "zhanghulist"));
		out.flush();
		out.close();
	}
//跳转到取款页面
	public String zhanghuupdate5() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Zhanghu bean = zhanghuDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		this.setUrl("zhanghu/zhanghuupdate5.jsp");
		return SUCCESS;
	}
//取款操作
	public void zhanghuupdate6() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String jine = request.getParameter("jine");
		Zhanghu bean = zhanghuDao.selectBean(" where id= "
				+ request.getParameter("id"));
		if ( Arith.sub(bean.getJine(), Double.parseDouble(jine))<0) {
			HttpServletResponse resp = ServletActionContext.getResponse();
			resp.setCharacterEncoding("utf-8");
			PrintWriter out = resp.getWriter();
			out.print(Util.tiaozhuan("操作失败，该账户没有这么多金额可提取",
					"method!zhanghulist", "zhanghulist"));
			out.flush();
			out.close();
		} else {
			bean.setJine(bean.getJine() - Double.parseDouble(jine));
			zhanghuDao.updateBean(bean);
			Yewu yewu = new Yewu();
			yewu.setContent("对"+bean.getZhanghuhao()+"的账户进行取款操作，取出"+Double.parseDouble(jine)+"元");
			yewu.setCreatetime(new Date());
			yewu.setType("取款");
			yewu.setZhanghu(bean);
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			yewu.setUser(user);
			yewuDao.insertBean(yewu);
			HttpServletResponse resp = ServletActionContext.getResponse();
			resp.setCharacterEncoding("utf-8");
			PrintWriter out = resp.getWriter();
			out.print(Util.tiaozhuan("操作成功", "method!zhanghulist",
					"zhanghulist"));
			out.flush();
			out.close();
		}

	}
//跳转到转账页面
	public String zhanghuupdate7() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Zhanghu bean = zhanghuDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		request.setAttribute("list", zhanghuDao.selectBeanList(0, 9999,
				" where zhanghulock=0 "));

		this.setUrl("zhanghu/zhanghuupdate7.jsp");
		return SUCCESS;
	}
//转账操作
	public void zhanghuupdate8() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String jine = request.getParameter("jine");
		Zhanghu bean = zhanghuDao.selectBean(" where id= "
				+ request.getParameter("id"));

		Zhanghu bean2 = zhanghuDao.selectBean(" where zhanghuhao= '"+request.getParameter("zhanghu")+"'");
		if(bean2==null){
			HttpServletResponse resp = ServletActionContext.getResponse();
			resp.setCharacterEncoding("utf-8");
			PrintWriter out = resp.getWriter();
			out.print(Util.tiaozhuan("操作失败，转账账户不存在",
					"method!zhanghulist", "zhanghulist"));
			out.flush();
			out.close();
		}
		else if (bean.getJine() < Double.parseDouble(jine)) {
			HttpServletResponse resp = ServletActionContext.getResponse();
			resp.setCharacterEncoding("utf-8");
			PrintWriter out = resp.getWriter();
			out.print(Util.tiaozhuan("操作失败，该账户没有这么多金额可提取",
					"method!zhanghulist", "zhanghulist"));
			out.flush();
			out.close();
		} else {
			

			bean.setJine(bean.getJine() - Double.parseDouble(jine));
			zhanghuDao.updateBean(bean);
			bean2.setJine(bean2.getJine() + Double.parseDouble(jine));
			zhanghuDao.updateBean(bean2);
			Yewu yewu = new Yewu();
			yewu.setContent(bean.getZhanghuhao()+"对"+bean2.getZhanghuhao()+"转入"+jine);
			yewu.setCreatetime(new Date());
			yewu.setType("转账");
			yewu.setZhanghu(bean);
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			yewu.setUser(user);
			yewuDao.insertBean(yewu);
			HttpServletResponse resp = ServletActionContext.getResponse();
			resp.setCharacterEncoding("utf-8");
			PrintWriter out = resp.getWriter();
			out.print(Util.tiaozhuan("操作成功", "method!zhanghulist",
					"zhanghulist"));
			out.flush();
			out.close();
		}
	}
//账户信息
	public String zhanghulist2() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String zhanghuhao = request.getParameter("zhanghuhao");
		String truename = request.getParameter("truename");
		String zhanghulock = request.getParameter("zhanghulock");
		String time1 = request.getParameter("time1");

		StringBuffer sb = new StringBuffer();
		sb.append(" where ");
		if(truename!=null&&!"".equals(truename)){
			sb.append("truename like '%"+truename+"%'");
			sb.append(" and ");

			request.setAttribute("truename", truename);
		}
		
		if(zhanghulock!=null&&!"".equals(zhanghulock)){
			sb.append("zhanghulock like '%"+zhanghulock+"%'");
			sb.append(" and ");

			request.setAttribute("zhanghulock", zhanghulock);
		}
		
		if(zhanghuhao!=null&&!"".equals(zhanghuhao)){
			sb.append("zhanghuhao like '%"+zhanghuhao+"%'");
			sb.append(" and ");

			request.setAttribute("zhanghuhao", zhanghuhao);
		}
		
		if(time1!=null&&!"".equals(time1)){
			sb.append("time = '"+time1+"'"  );
			sb.append(" and ");

			request.setAttribute("time1", time1);
		}
		
		
		sb.append(" 1=1 order by id desc");
		String where = sb.toString();
		
		int currentpage = 1;
		int pagesize = 20;
		if (request.getParameter("pageNum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pageNum"));
			pagesize = Integer.parseInt(request.getParameter("numPerPage"));
		}
		int total = zhanghuDao.selectBeanCount(where.replaceAll("order by id desc", ""));
		request.setAttribute("list", zhanghuDao.selectBeanList(
				(currentpage - 1) * pagesize, pagesize, where));
		request.setAttribute("totalCount", total);
		request.setAttribute("ps", pagesize);
		request.setAttribute("pn", currentpage);
		request.setAttribute("url", "method!zhanghulist2");
		this.setUrl("zhanghu/zhanghulist2.jsp");
		return SUCCESS;
	}
//冻结账户操作
	public void zhanghudelete2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Zhanghu bean = zhanghuDao.selectBean(" where id= "
				+ request.getParameter("id"));
		if(bean.getZhanghulock()==0){
			bean.setZhanghulock(2);
			zhanghuDao.updateBean(bean);
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.print(Util.tiaozhuan2("操作成功", "method!zhanghulist2", "zhanghulist2"));
			out.flush();
			out.close();
		}else{

			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.print(Util.tiaozhuan2("非法操作", "method!zhanghulist2", "zhanghulist2"));
			out.flush();
			out.close();
		}

		

	}
	//撤销冻结操作
	public void zhanghudelete3() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Zhanghu bean = zhanghuDao.selectBean(" where id= "
				+ request.getParameter("id"));
		if(bean.getZhanghulock()!=0){
			bean.setZhanghulock(0);
			zhanghuDao.updateBean(bean);
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.print(Util.tiaozhuan2("操作成功", "method!zhanghulist2", "zhanghulist2"));
			out.flush();
			out.close();
		}else{
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.print(Util.tiaozhuan2("非法操作", "method!zhanghulist2", "zhanghulist2"));
			out.flush();
			out.close();
		}
	}
	
	//业务员列表
	public String userlist() {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String username = request.getParameter("username");


		StringBuffer sb = new StringBuffer();
		sb.append(" where ");
		if(username!=null&&!"".equals(username)){
			sb.append("username like '%"+username+"%'");
			sb.append(" and ");

			request.setAttribute("username", username);
		}
		
		
		
		
		sb.append(" userlock=0 and role=0 order by id desc");
		String where = sb.toString();
		int currentpage = 1;
		int pagesize = 20;
		if (request.getParameter("pageNum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pageNum"));
			pagesize = Integer.parseInt(request.getParameter("numPerPage"));
		}

		int total = userDao.selectBeanCount(where.replaceAll("order by id desc", ""));
		request.setAttribute("list", userDao.selectBeanList(
				(currentpage - 1) * pagesize, pagesize, where));
		request.setAttribute("totalCount", total);
		request.setAttribute("ps", pagesize);
		request.setAttribute("pn", currentpage);
		request.setAttribute("url", "method!userlist");
		this.setUrl("user/userlist.jsp");
		return SUCCESS;
	}
//跳转到添加业务员页面
	public String useradd() {
		this.setUrl("user/useradd.jsp");
		return SUCCESS;
	}
//添加业务员操作
	public void useradd2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String dizhi = request.getParameter("dizhi");
		String truename = request.getParameter("truename");
		String lianxifangshi = request.getParameter("lianxifangshi");
		String username = request.getParameter("username");
		User bean = userDao.selectBean(" where username='" + username + "' ");
		if(bean==null){
			bean = new User();
			bean.setCreatetime(new Date());
			bean.setDizhi(dizhi);
			bean.setLianxifangshi(lianxifangshi);
			bean.setPassword("111111");
			bean.setRole(0);
			bean.setTruename(truename);
			bean.setUsername(username);
			bean.setCreatetime(new Date());
			userDao.insertBean(bean);
			HttpServletResponse resp = ServletActionContext.getResponse();
			resp.setCharacterEncoding("utf-8");
			PrintWriter out = resp.getWriter();
			out.print(Util.tiaozhuan("操作成功", "method!userlist", "userlist"));
			out.flush();
			out.close();
		}else{
			HttpServletResponse resp = ServletActionContext.getResponse();
			resp.setCharacterEncoding("utf-8");
			PrintWriter out = resp.getWriter();
			out.print(Util.tiaozhuan("改用户名已存在，请勿重复添加", "method!userlist", "userlist"));
			out.flush();
			out.close();
		}
		
	}
//跳转到更新业务员页面
	public String userupdate() {
		HttpServletRequest request = ServletActionContext.getRequest();
		User bean = userDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		this.setUrl("user/userupdate.jsp");
		return SUCCESS;
	}
//更新操作
	public void userupdate2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String dizhi = request.getParameter("dizhi");
		String truename = request.getParameter("truename");
		String lianxifangshi = request.getParameter("lianxifangshi");
		User bean = userDao.selectBean(" where id= "
				+ request.getParameter("id"));

		bean.setDizhi(dizhi);
		bean.setLianxifangshi(lianxifangshi);
		bean.setTruename(truename);
		userDao.updateBean(bean);
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		out.print(Util.tiaozhuan("操作成功", "method!userlist", "userlist"));
		out.flush();
		out.close();
	}
//删除业务员操作
	public void userdelete() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		User bean = userDao.selectBean(" where id= "
				+ request.getParameter("id"));
		bean.setUsername(bean.getUsername()+"_delete");
		bean.setUserlock(1);
		userDao.updateBean(bean);
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.print(Util.tiaozhuan2("操作成功", "method!userlist",
				"userlist"));
		out.flush();
		out.close();

	}
	
	private YewuDao yewuDao;

	public YewuDao getYewuDao() {
		return yewuDao;
	}

	public void setYewuDao(YewuDao yewuDao) {
		this.yewuDao = yewuDao;
	}
	
	//业务信息列表
	public String yewulist() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String type = request.getParameter("type");
		String zhanghuhao = request.getParameter("zhanghuhao");
		String username = request.getParameter("username");

		StringBuffer sb = new StringBuffer();
		sb.append(" where ");
		if(type!=null&&!"".equals(type)){
			sb.append("type like '%"+type+"%'");
			sb.append(" and ");

			request.setAttribute("type", type);
		}
		
		if(zhanghuhao!=null&&!"".equals(zhanghuhao)){
			sb.append(" zhanghu.zhanghuhao like '%"+zhanghuhao+"%'");
			sb.append(" and ");

			request.setAttribute("zhanghuhao", zhanghuhao);
		}
		
		if(username!=null&&!"".equals(username)){
			sb.append(" user.username like '%"+username+"%'");
			sb.append(" and ");

			request.setAttribute("username", username);
		}
		
		
		
		
		sb.append(" 1=1 order by id desc");
		String where = sb.toString();
		
		int currentpage = 1;
		int pagesize = 15;
		if (request.getParameter("pageNum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pageNum"));
			pagesize = Integer.parseInt(request.getParameter("numPerPage"));
		}
		int total = yewuDao.selectBeanCount(where.replaceAll("order by id desc", ""));
		request.setAttribute("list", yewuDao.selectBeanList(
				(currentpage - 1) * pagesize, pagesize, where));
		request.setAttribute("totalCount", total);
		request.setAttribute("ps", pagesize);
		request.setAttribute("pn", currentpage);
		request.setAttribute("url", "method!yewulist");
		this.setUrl("yewu/yewulist.jsp");
		return SUCCESS;
	}
	
}
