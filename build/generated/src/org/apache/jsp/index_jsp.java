package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_forEach_varStatus_var_items;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_c_forEach_varStatus_var_items = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_c_forEach_varStatus_var_items.release();
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
      out.write("        <meta name=\"viewport\" content=\"initial-scale=1\">\r\n");
      out.write("        <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\">\r\n");
      out.write("        <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js\"></script>\r\n");
      out.write("        <script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js\"></script>\r\n");
      out.write("        <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js\"></script>\r\n");
      out.write("        <!--<link rel='stylesheet' href='bootstrap/css/bootstrap.min.css'>\r\n");
      out.write("        <link rel='stylesheet' href='bootstrap/js/bootstrap.min.js'>\r\n");
      out.write("        <link rel='stylesheet' href='bootstrap/css/bootstrap-grid.min.css'>-->\r\n");
      out.write("        <link rel=\"stylesheet\" href=\"./css/style.css\">\r\n");
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
      out.write("\r\n");
      out.write("        <div class=\"container\">\r\n");
      out.write("            <div id=\"demo\" class=\"carousel slide\" data-ride=\"carousel\">\r\n");
      out.write("\r\n");
      out.write("                <!-- Indicators -->\r\n");
      out.write("                <ul class=\"carousel-indicators\">\r\n");
      out.write("                    <li data-target=\"#demo\" data-slide-to=\"0\" class=\"active\"></li>\r\n");
      out.write("                    <li data-target=\"#demo\" data-slide-to=\"1\"></li>\r\n");
      out.write("                    <li data-target=\"#demo\" data-slide-to=\"2\"></li>\r\n");
      out.write("                </ul>\r\n");
      out.write("\r\n");
      out.write("                <!-- The slideshow -->\r\n");
      out.write("                <div class=\"carousel-inner\">\r\n");
      out.write("                    <div class=\"carousel-item active\">\r\n");
      out.write("                        <img src=\"./images/picture.jpg\" alt=\"pic\" width=\"1500px\" height=\"200px\">\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <div class=\"carousel-item\">\r\n");
      out.write("                        <img src=\"./images/amazon.jpg\" alt=\"amazon\" width=\"1500px\" height=\"200px\">\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <div class=\"carousel-item\">\r\n");
      out.write("                        <img src=\"./images/universe.jpg\" alt=\"universe\" width=\"1500px\" height=\"200px\">\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("\r\n");
      out.write("                <!-- Left and right controls -->\r\n");
      out.write("                <a class=\"carousel-control-prev\" href=\"#demo\" data-slide=\"prev\">\r\n");
      out.write("                    <span class=\"carousel-control-prev-icon\"></span>\r\n");
      out.write("                </a>\r\n");
      out.write("                <a class=\"carousel-control-next\" href=\"#demo\" data-slide=\"next\">\r\n");
      out.write("                    <span class=\"carousel-control-next-icon\"></span>\r\n");
      out.write("                </a>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("\r\n");
      out.write("        <br>\r\n");
      out.write("\r\n");
      out.write("            <div class=\"container\">\r\n");
      out.write("                <div class=\"bg-info text-white font-weight-bold pl-3 pt-2 pb-1 mb-4 rounded\"><h5>New Quiz!</h5></div>\r\n");
      out.write("                <div class=\" bg-light p-5 rounded\">\r\n");
      out.write("                    <div class=\"row\">\r\n");
      out.write("                        ");
      if (_jspx_meth_c_forEach_0(_jspx_page_context))
        return;
      out.write(" \r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
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

  private boolean _jspx_meth_c_forEach_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_forEach_0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _jspx_tagPool_c_forEach_varStatus_var_items.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_forEach_0.setPageContext(_jspx_page_context);
    _jspx_th_c_forEach_0.setParent(null);
    _jspx_th_c_forEach_0.setItems((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${quizes}", java.lang.Object.class, (PageContext)_jspx_page_context, null));
    _jspx_th_c_forEach_0.setVar("q");
    _jspx_th_c_forEach_0.setVarStatus("s");
    int[] _jspx_push_body_count_c_forEach_0 = new int[] { 0 };
    try {
      int _jspx_eval_c_forEach_0 = _jspx_th_c_forEach_0.doStartTag();
      if (_jspx_eval_c_forEach_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n");
          out.write("                            <div class=\"col-3 mb-4\">\r\n");
          out.write("                                <div class=\"card-deck\">\r\n");
          out.write("                                    <div class=\"card\">\r\n");
          out.write("                                        <a href=\"/Quiz.jsp\">\r\n");
          out.write("                                            <img class=\"card-img-top\" src=\"./images/picture.jpg\" alt=\"Card image\" style=\"width:100%\">\r\n");
          out.write("                                            <div class=\"card-body\">\r\n");
          out.write("                                                <h4 class=\"card-title\">Quiz's name ");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${q}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("</h4>\r\n");
          out.write("                                                <h5 class=\"card-text\">Subject</h5>\r\n");
          out.write("                                                <h6 class=\"card-text\">Teacher</h6>\r\n");
          out.write("                                                <p class=\"card-text\">Amount of question</p>\r\n");
          out.write("                                            </div>\r\n");
          out.write("                                            <div class=\"overlay\"></div>\r\n");
          out.write("                                            <div class=\"text\">Start Quiz</div>\r\n");
          out.write("                                        </a>\r\n");
          out.write("                                    </div>\r\n");
          out.write("                                </div>\r\n");
          out.write("                            </div>\r\n");
          out.write("                        ");
          int evalDoAfterBody = _jspx_th_c_forEach_0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_forEach_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_c_forEach_0[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_c_forEach_0.doCatch(_jspx_exception);
    } finally {
      _jspx_th_c_forEach_0.doFinally();
      _jspx_tagPool_c_forEach_varStatus_var_items.reuse(_jspx_th_c_forEach_0);
    }
    return false;
  }
}
