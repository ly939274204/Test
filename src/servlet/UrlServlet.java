package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import entity.Longurl;
import service.UrlService;

/**
 * Servlet implementation class UrlServlet
 */
@Controller
@RequestMapping("/UrlServlet")
public class UrlServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private UrlService urlService;

	@RequestMapping("changeUrl")
	@ResponseBody
	public List<String> changeUrl(HttpServletRequest request) {
		Longurl longurl = new Longurl();
		longurl.setLongurl(request.getParameter("longurl"));
		String key = request.getParameter("key");
		urlService.ChangeUrl(longurl, key);
		List<String> shorturls = urlService.getallUrl(longurl.getLongurl());
		return shorturls;
	}

	@RequestMapping("getUrl")
	public ModelAndView getUrl(HttpServletRequest request) {
		String shorturl = request.getParameter("shorturl");
		String longurl = urlService.getUrl(shorturl);
		return  new ModelAndView(new RedirectView("http://"+longurl));
	}

	@RequestMapping("count")
	public void count(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ServletContext appliaton = getServletContext();
		// 获取当前web应用项目的context对象
		Integer count = (Integer) appliaton.getAttribute("count");
		// 获取Context对象的count属性
		/*
		 *对count属性值进行判断，如果为空值，说明该网站是第一次被访问，count赋值为1
		 * 如果不为空值，则进行累加操作
		 */
		if (count == null) {
			count = 1;
		} else {
			count++;
		}
		// 设置编码格式为utf-8
		response.setContentType("text/html;charset=utf-8");
		// 创建一个响应流
		PrintWriter out = response.getWriter();
		// 输出访问次数
		out.print("该网站共被访问了" + count + "次");
		// 保存访问次数
		appliaton.setAttribute("count", count);
	}
}
