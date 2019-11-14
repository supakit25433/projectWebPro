package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("    <head>\r\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("\r\n");
      out.write("        <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\" integrity=\"sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\" crossorigin=\"anonymous\">\r\n");
      out.write("        <script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js\" integrity=\"sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM\" crossorigin=\"anonymous\"></script>\r\n");
      out.write("        <script src=\"https://code.jquery.com/jquery-3.3.1.slim.min.js\" integrity=\"sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo\" crossorigin=\"anonymous\"></script>\r\n");
      out.write("        <script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js\" integrity=\"sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1\" crossorigin=\"anonymous\"></script>\r\n");
      out.write("\r\n");
      out.write("        <!--<link rel='stylesheet' href='bootstrap/css/bootstrap.min.css'>\r\n");
      out.write("        <link rel='stylesheet' href='bootstrap/js/bootstrap.min.js'>\r\n");
      out.write("        <link rel='stylesheet' href='bootstrap/css/bootstrap-grid.min.css'>-->\r\n");
      out.write("\r\n");
      out.write("        <title>Welcome ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${user.username}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write(" to Quiz Ant</title>\r\n");
      out.write("    </head>\r\n");
      out.write("    <body>\r\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/WEB-INF/view/PageHeader.jsp?page=Home Page", out, false);
      out.write("\r\n");
      out.write("        <br />\r\n");
      out.write("        <div class=\"container-fluid\">\r\n");
      out.write("            <div class=\"text-center\">\r\n");
      out.write("                <img height=\"150px\" width=\"1000px\">\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("        <div class=\"slidershow middle\">\r\n");
      out.write("            \r\n");
      out.write("            <div class=\"sliders\">\r\n");
      out.write("                <input type=\"radio\" name=\"r\" id=\"r1\" checked>\r\n");
      out.write("                <input type=\"radio\" name=\"r\" id=\"r2\">\r\n");
      out.write("                <input type=\"radio\" name=\"r\" id=\"r3\">\r\n");
      out.write("                <div class=\"slide\">\r\n");
      out.write("                    <img src=\"images/amazon.jpg\" alt=\"\">\r\n");
      out.write("                </div>\r\n");
      out.write("                <div class=\"slide\">\r\n");
      out.write("                    <img src=\"images/picture.jpg\" alt=\"\">\r\n");
      out.write("                </div>\r\n");
      out.write("                <div class=\"slide\">\r\n");
      out.write("                    <img src=\"images/universe.jpg\" alt=\"\">\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("            \r\n");
      out.write("            <div class=\"navigation\">\r\n");
      out.write("                <label for=\"r1\" class=\"bar\"></label>\r\n");
      out.write("                <label for=\"r2\" class=\"bar\"></label>\r\n");
      out.write("                <label for=\"r3\" class=\"bar\"></label>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("        <br />\r\n");
      out.write("        <div class=\"container-fluid\">\r\n");
      out.write("            Each Subject will be show in here.\r\n");
      out.write("            ");
      out.write("\r\n");
      out.write("            <!-- ใช้การวนลูป list ออกมาจากข้อมูลที่ได้ อาจจะใช้รูปแบบเดียวกันกับที่สอบรายบุคคล -->\r\n");
      out.write("            ");
      out.write("\r\n");
      out.write("        </div>\r\n");
      out.write("    </body>\r\n");
      out.write("</html>\r\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}