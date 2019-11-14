package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class Login_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("    <head>\r\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("        <title>QuizAnt Login</title>\r\n");
      out.write("        <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\" integrity=\"sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\" crossorigin=\"anonymous\">\r\n");
      out.write("        <script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js\" integrity=\"sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM\" crossorigin=\"anonymous\"></script>\r\n");
      out.write("        <script src=\"https://code.jquery.com/jquery-3.3.1.slim.min.js\" integrity=\"sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo\" crossorigin=\"anonymous\"></script>\r\n");
      out.write("        <script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js\" integrity=\"sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1\" crossorigin=\"anonymous\"></script>\r\n");
      out.write("\r\n");
      out.write("        <!--<link rel='stylesheet' href='bootstrap/css/bootstrap.min.css'>\r\n");
      out.write("        <link rel='stylesheet' href='bootstrap/js/bootstrap.min.js'>\r\n");
      out.write("        <link rel='stylesheet' href='bootstrap/css/bootstrap-grid.min.css'>-->\r\n");
      out.write("        <style>\r\n");
      out.write("            .login{\r\n");
      out.write("                position:relative;\r\n");
      out.write("                top:17em;\r\n");
      out.write("            }\r\n");
      out.write("            body {\r\n");
      out.write("                background-color: lightgray;\r\n");
      out.write("            }\r\n");
      out.write("            input {\r\n");
      out.write("                font-size:18px;\r\n");
      out.write("                padding:10px 10px 10px 5px;\r\n");
      out.write("                display:block;\r\n");
      out.write("                width:300px;\r\n");
      out.write("                border:none;\r\n");
      out.write("                border-bottom:1px solid #757575;\r\n");
      out.write("                box-sizing: border-box;\r\n");
      out.write("            }\r\n");
      out.write("            input:focus{ \r\n");
      out.write("                outline:none; \r\n");
      out.write("            }\r\n");
      out.write("            .group{ \r\n");
      out.write("                position:relative; \r\n");
      out.write("                margin-top: 20px;\r\n");
      out.write("                margin-bottom:20px; \r\n");
      out.write("            }\r\n");
      out.write("            label{\r\n");
      out.write("                color:#999; \r\n");
      out.write("                font-size:18px;\r\n");
      out.write("                font-weight:normal;\r\n");
      out.write("                position:absolute;\r\n");
      out.write("                pointer-events:none;\r\n");
      out.write("                left:5px;\r\n");
      out.write("                top:10px;\r\n");
      out.write("                transition:0.2s ease all; \r\n");
      out.write("                -moz-transition:0.2s ease all; \r\n");
      out.write("                -webkit-transition:0.2s ease all;\r\n");
      out.write("            }\r\n");
      out.write("            input:focus ~ label, input:valid ~ label   {\r\n");
      out.write("                top:-20px;\r\n");
      out.write("                font-size:14px;\r\n");
      out.write("                color:#5264AE;\r\n");
      out.write("            }\r\n");
      out.write("            .bar{ \r\n");
      out.write("                position:relative; \r\n");
      out.write("                display:block; \r\n");
      out.write("                width:300px; \r\n");
      out.write("            }\r\n");
      out.write("            .bar:before, .bar:after  {\r\n");
      out.write("                content:'';\r\n");
      out.write("                height:2px; \r\n");
      out.write("                width:0;\r\n");
      out.write("                bottom:1px; \r\n");
      out.write("                position:absolute;\r\n");
      out.write("                background:#5264AE; \r\n");
      out.write("                transition:0.2s ease all; \r\n");
      out.write("                -moz-transition:0.2s ease all; \r\n");
      out.write("                -webkit-transition:0.2s ease all;\r\n");
      out.write("            }\r\n");
      out.write("            .bar:before {\r\n");
      out.write("                left:50%;\r\n");
      out.write("            }\r\n");
      out.write("            .bar:after {\r\n");
      out.write("                right:50%; \r\n");
      out.write("            }\r\n");
      out.write("            input:focus ~ .bar:before, input:focus ~ .bar:after {\r\n");
      out.write("                width:50%;\r\n");
      out.write("            }\r\n");
      out.write("        </style>\r\n");
      out.write("    </head>\r\n");
      out.write("    <body>\r\n");
      out.write("        <div class='container-fluid login'>\r\n");
      out.write("            <div class='row'>\r\n");
      out.write("                <div class='col-sm-4 col-md-4 col-lg-4 col-xl-4'></div>\r\n");
      out.write("                <div class='col-sm-4 col-md-4 col-lg-4 col-xl-4'>\r\n");
      out.write("                    <div class='container-fluid'>\r\n");
      out.write("                        <div style='text-align:center'><img src='images/amazon.jpg' style='height:5cm; width:6cm;'></div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <div class='container-fluid text-center'>\r\n");
      out.write("                        <form method='post' action='Login' >\r\n");
      out.write("                            <hr width='50%'>\r\n");
      out.write("                            <div style='position:relative; left:140px'>\r\n");
      out.write("                                <div>\r\n");
      out.write("                                    <div class='group'>\r\n");
      out.write("                                        <input type='text' style='width:8cm' name='userName' required>\r\n");
      out.write("                                        <span class='bar'></span>\r\n");
      out.write("                                        <label>Username</label>\r\n");
      out.write("                                    </div>\r\n");
      out.write("                                </div>\r\n");
      out.write("                                <div class='group'>\r\n");
      out.write("                                    <input type='password' style='width:8cm' name='password' required>\r\n");
      out.write("                                    <span class='bar'></span>\r\n");
      out.write("                                    <label>Password</label>\r\n");
      out.write("                                </div>\r\n");
      out.write("                            </div>\r\n");
      out.write("                            <div class='container' style='position:relative; left:138px'>\r\n");
      out.write("                                <input type='submit' style='width: 50%;'>\r\n");
      out.write("                            </div>\r\n");
      out.write("                            <div class='text-center'>\r\n");
      out.write("                                <br><h6 style='color:red;'>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${message}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</h6>\r\n");
      out.write("                            </div>\r\n");
      out.write("                        </form>\r\n");
      out.write("<!--                        <div class=\"container-fluid\">\r\n");
      out.write("                            Click here to register  <a href=\"Register.jsp\">Register</a>\r\n");
      out.write("                        </div>-->\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("                <div class='col-sm-4 col-md-4 col-lg-4 col-xl-4'></div>\r\n");
      out.write("            </div>\r\n");
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
