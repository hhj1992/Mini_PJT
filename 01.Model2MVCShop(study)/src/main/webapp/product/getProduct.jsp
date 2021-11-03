<%@page import="org.apache.tomcat.util.http.Cookies"%>
<%@page import="com.model2.mvc.service.product.vo.ProductVO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%> 
<!DOCTYPE html>  
<html>
<head> 
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>

<%ProductVO vo = (ProductVO)request.getAttribute("product"); %>
<%session.setAttribute("Product","vo");%>

<% String value = null;						
	Cookie[] cookies = request.getCookies(); //request.getCookies의 리턴타입은 Cookie[]이다. 
	if (cookies!=null && cookies.length > 0) {   
		for (int i = 0; i < cookies.length; i++) {	
			Cookie cookie = cookies[i];	  //쿠키는 우리가 지정을 해주거나 or  JSESSIONID의 이름의 세션으로 쿠기가 들어간다.  JSESSIONID에 모든 session의 집합이다. 결과적으로는 JSESSIONID가 나온다. 
			System.out.println(cookie.getName());  // 모든지 
			if (cookie.getName().equals("history")) { 
				value = cookie.getValue(); 
			}
		}	
	}
	
	value +=  "," + vo.getProdNo(); 
	Cookie cookie = new Cookie("history",value);
	response.addCookie(cookie); %> 

    <link rel="stylesheet" href="/css/admin.css" type="text/css">
    
    <title>상품 상세 조회</title>
    </head>
    
    <body bgcolor="#ffffff" text="#000000">
    
    <form name="detailForm" method="post">
    
    <table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
        <tr>
            <td width="15" height="37"><img src="/images/ct_ttl_img01.gif"	width="15" height="37"></td>
            <td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left: 10px;">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td width="93%" class="ct_ttl01">상품상세조회</td>
                        <td width="20%" align="right">&nbsp;</td>
                    </tr>
                </table>
            </td>
            <td width="12" height="37">
                <img src="/images/ct_ttl_img03.gif"  width="12" height="37"/>
            </td>
        </tr>
    </table>
    
    <table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top: 13px;">
        <tr>
            <td height="1" colspan="3" bgcolor="D6D6D6"></td>
        </tr>
        <tr>
            <td width="104" class="ct_write">
                상품번호 <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
            </td>
            <td bgcolor="D6D6D6" width="1"></td>
            <td class="ct_write01">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td width="105"><%=vo.getProdNo()%></td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr>
            <td height="1" colspan="3" bgcolor="D6D6D6"></td>
        </tr>
        <tr>
            <td width="104" class="ct_write">
                상품명 <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
            </td>
            <td bgcolor="D6D6D6" width="1"></td>
            <td class="ct_write01"><%=vo.getProdName() %></td>
        </tr>
        <tr>
            <td height="1" colspan="3" bgcolor="D6D6D6"></td>
        </tr>
        <tr>
            <td width="104" class="ct_write">
                상품이미지 <img 	src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
            </td>
            <td bgcolor="D6D6D6" width="1"></td>
            <td class="ct_write01">
                <img src = "/images/uploadFiles/../../images/empty.GIF"/>
            </td>
        </tr>
        <tr>
            <td height="1" colspan="3" bgcolor="D6D6D6"></td>
        </tr>
        <tr>
            <td width="104" class="ct_write">
                상품상세정보 <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
            </td>
            <td bgcolor="D6D6D6" width="1"></td>
            <td class="ct_write01"><%=vo.getProdDetail() %></td>
        </tr>
        <tr>
            <td height="1" colspan="3" bgcolor="D6D6D6"></td>
        </tr>
        <tr>
            <td width="104" class="ct_write">제조일자</td>
            <td bgcolor="D6D6D6" width="1"></td>
            <td class="ct_write01"><%=vo.getManuDate() %></td>
        </tr>
        <tr>
            <td height="1" colspan="3" bgcolor="D6D6D6"></td>
        </tr>
        <tr>
            <td width="104" class="ct_write">가격</td>
            <td bgcolor="D6D6D6" width="1"></td>
            <td class="ct_write01"><%=vo.getPrice() %></td>
        </tr>
        <tr>
            <td height="1" colspan="3" bgcolor="D6D6D6"></td>
        </tr>
        <tr>
            <td width="104" class="ct_write">등록일자</td>
            <td bgcolor="D6D6D6" width="1"></td>
            <td class="ct_write01"><%=vo.getRegDate() %></td>
        </tr>
        <tr>
            <td height="1" colspan="3" bgcolor="D6D6D6"></td>
        </tr>
    </table>
    
    <table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top: 10px;">
        <tr>
            <td width="53%"></td>
            <td align="right">
    
            <table border="0" cellspacing="0" cellpadding="0">
                <tr>
            
                    <td width="17" height="23">
                        <img src="/images/ct_btnbg01.gif" width="17" height="23"/>
                    </td>
                    <td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top: 3px;">
                        <a href="/addPurchaseView.do?prod_no=<%=vo.getProdNo()%>">구매</a>
                    </td>
                    <td width="14" height="23">
                        <img src="/images/ct_btnbg03.gif" width="14" height="23">
                    </td>
                    <td width="30"></td>
            
                    <td width="17" height="23">
                        <img src="/images/ct_btnbg01.gif" width="17" height="23"/>
                    </td>
                    <td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top: 3px;">
                        <a href="javascript:history.go(-1)">이전</a>
                    </td>
                    <td width="14" height="23">
                        <img src="/images/ct_btnbg03.gif" width="14" height="23">
                    </td>
                </tr>
            </table>
    
            </td>
        </tr>
    </table>
    </form>
    
</body>
</html>