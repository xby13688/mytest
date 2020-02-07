package com.itheima.web;

import com.itheima.bean.LinkMan;
import com.itheima.bean.PageBean;
import com.itheima.bean.Result;
import com.itheima.service.LinkManService;
import com.itheima.utils.JsonUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: yp
 */
@WebServlet("/linkMan")
public class LinkManServlet extends HttpServlet {

    private LinkManService linkManService = new LinkManService();

    //找到对应的方法,调用 可以使用反射
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //0.处理乱码
            request.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=utf-8");

            //1.获得method请求参数【说白了就是方法名】 eg:findAll
            String methodStr = request.getParameter("method");
            //2. 获得字节码对象
            Class clazz = this.getClass();
            //3.根据方法名, 反射获得Method
            Method method = clazz.getMethod(methodStr, HttpServletRequest.class, HttpServletResponse.class);
            //4.调用
            method.invoke(this,request,response);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    /**
     * 查询所有的联系人
     * @param request
     * @param response
     */
    public void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            //1.调用业务 获得List<LinkMan> list
            List<LinkMan> list =  linkManService.findAll();
            //2.封装成result
            Result result = new Result(true,"查询所有联系人成功",list);
            JsonUtils.printResult(response,result);
        } catch (Exception e) {
            Result result = new Result(false,"查询所有联系人失败");
            JsonUtils.printResult(response,result);
        }
    }

    /**
     * 分页查询联系人
     * @param request
     * @param response
     */
    public void findPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //1.获得请求参数 curPage
            int curPage = Integer.parseInt(request.getParameter("curPage"));
            //2.调用业务 获得分页数据PageBean
            PageBean<LinkMan> pageBean =  linkManService.findPage(curPage);
            //3.把PageBean存request,转发list_page.jsp
            request.setAttribute("pageBean",pageBean);
            request.getRequestDispatcher("list_page.jsp").forward(request,response);
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().print("<h1>服务器异常!</h1>");

        }
    }

    /**
     * 根据id删除联系人
     * @param request
     * @param response
     */
    public void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            //1.获得请求参数 id
            int id = Integer.parseInt(request.getParameter("id"));
            //2.调用业务根据id删除
            linkManService.deleteById(id);
            //3.查询所有展示
            response.sendRedirect(request.getContextPath()+"/linkMan?method=findAll");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().print("<h1>服务器异常!</h1>");
        }
    }

    /**
     * 新增联系人
     * @param request
     * @param response
     */
    public void add(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {
            //1.获得请求参数,封装成LinkMan对象(BeanUtils: map的key必须和javaBean属性一致)
//            Map<String, String[]> map = request.getParameterMap();
//            LinkMan linkMan = new LinkMan();
//            BeanUtils.populate(linkMan,map);
            LinkMan linkMan = JsonUtils.parseJSON2Object(request, LinkMan.class);
            JsonUtils.printResult(response,new Result(true,"添加成功"));
            //2.调用业务, 进行新增
            linkManService.add(linkMan);
            //3.查询所有展示  方式一:转发  方式二:直接调用findAll() 等价于转发的  方式三:重定向【选择】
            //request.getRequestDispatcher("linkMan?method=findAll").forward(request,response);
            //findAll(request,response);
            response.sendRedirect(request.getContextPath()+"/linkMan?method=findAll");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().print("<h1>服务器异常!</h1>");
        }
    }



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
