<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row">
    <div class="col-sm-1 col-lg-1"></div>
    <div class="col-sm-10 col-lg-10">
        <div class="row">
            <div class="col-sm-2 col-lg-2">
                <a href="index.jsp" title="Goto Home Page"><img src="images/logo.png" height="90"></a>
            </div>
            <div class="col-sm-8 col-lg-8">
                <br>
                <h3>${param.title} ::</h3>
            </div>
            <div class="col-sm-2 col-lg-2 align-bottom align-text-bottom" style="text-align: center">
                <a href="${user==null?'Logout':'Login'}" title="${user==null?'Logout':'Login'}">
                    <img src="images/user.png" width="50"/>
                </a>
                <br>
                Welcome ${user==null?"Guest!":user.name}
                <br>
                <c:if test="${cart.size>0}">
                    <a href="ViewCart" title="View Your Cart">
                        <img src="images/bag.png" width="20"/>
                    </a>
                    (${cart.size})
                </c:if>
            </div>
        </div>
    </div>
    <div class="col-sm-1 col-lg-1"></div>
</div>

                