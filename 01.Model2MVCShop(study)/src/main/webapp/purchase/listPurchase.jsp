<%@ page contentType="text/html; charset=euc-kr" %>

<%@ page import="java.util.*"  %>
<%@ page import="com.model2.mvc.service.product.vo.*" %>
<%@ page import="com.model2.mvc.service.purchase.vo.*" %>
<%@ page import="com.model2.mvc.service.user.vo.*" %>
<%@ page import="com.model2.mvc.common.*" %>

<%
System.out.println("jsp 실행");
HashMap<String,Object> map=(HashMap<String,Object>)request.getAttribute("map");
SearchVO searchVO=(SearchVO)request.getAttribute("searchVO");

/* String urlMenu = request.getParameter("menu"); */

System.out.println(map);
System.out.println(searchVO);
/* System.out.println(urlMenu); */

int total=0;
ArrayList <PurchaseVO> list= null;

if(map != null){
	total = ((Integer)map.get("count")).intValue();
	list = (ArrayList<PurchaseVO>)map.get("list");
}

int currentPage = searchVO.getPage();

int totalPage=0;

if(total > 0) {
	totalPage= total / searchVO.getPageUnit() ;
	
	if(total%searchVO.getPageUnit() >0)
		
		totalPage += 1;
}
System.out.println(list);
%>



<html>
    <head>
    <title>구매 목록조회</title>
    
    <link rel="stylesheet" href="/css/admin.css" type="text/css">
    
    <script type="text/javascript">
        function fncGetUserList() {
            document.detailForm.submit();
        }
    </script>
    </head>
    
    <body bgcolor="#ffffff" text="#000000">
    
    <div style="width: 98%; margin-left: 10px;">
    
    <form name="detailForm" action="/listPurchase.do" method="post">
    
    <table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
        <tr>
            <td width="15" height="37"><img src="/images/ct_ttl_img01.gif"width="15" height="37"></td>
            <td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left: 10px;">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td width="93%" class="ct_ttl01">구매 목록조회</td>
                    </tr>
                </table>
            </td>
            <td width="12" height="37"><img src="/images/ct_ttl_img03.gif"	width="12" height="37"></td>
        </tr>
    </table>
    
    <table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top: 10px;">
        <tr>
            <td colspan="11">전체  <%= total%> 건수, 현재 <%=currentPage %> 페이지</td>
        </tr>
        <tr>
            <td class="ct_list_b" width="100">No</td>
            <td class="ct_line02"></td>
            <td class="ct_list_b" width="150">회원ID</td>
            <td class="ct_line02"></td>
            <td class="ct_list_b" width="150">회원명</td>
            <td class="ct_line02"></td>
            <td class="ct_list_b">전화번호</td>
            <td class="ct_line02"></td>
            <td class="ct_list_b">배송현황</td>
            <td class="ct_line02"></td>
            <td class="ct_list_b">정보수정</td>
        </tr>
        <tr>
            <td colspan="11" bgcolor="808285" height="1"></td>
        </tr>
    
        <% 	
        if(list != null){
			int no = list.size();
			for(int i=0; i<list.size(); i++) {
				PurchaseVO vo = (PurchaseVO)list.get(i);
				UserVO userVo = vo.getBuyer();
				ProductVO productVo = vo.getPurchaseProd();
		%>
        
        <tr class="ct_list_pop">
            <td align="center">
            	<%if(vo.getTranCode().equals("1")){ %>
						<a href="/getPurchase.do?tranNo=<%=vo.getTranNo()%>"><%=no--%></a> 
					<% }else{%>
					<%=no--%>
				<%}%>
            </td>
            <td></td>
            <td align="left">
                <a href="/getUser.do?userId=<%=userVo.getUserId()%>"><%=userVo.getUserId()%></a>
            </td>
            <td></td>
            <td align="left"><%=vo.getReceiverName()%></td>
            <td></td>
            <td align="left"><%=vo.getReceiverPhone()%></td>
            <td></td>
            <td align="left">
            <%if(vo.getTranCode().equals("1")){%>
            	구매 완료된 상태 입니다.
            <%}else if(vo.getTranCode().equals("2")){%>
            	배송중 입니다. <a href ="#"></a>
            <%}else{%>
            	배송완료
            <%} %>
            </td>
            <td></td>
            <td align="left"> 
            <%if(vo.getTranCode().equals("2")){%>           
            	<a href ="updateTranCodeByProd.do?tran_no=<%=vo.getTranNo()%>" onclick='return confirm("물픔을 수령 하셨습니까 ?");'>물건도착</a>
            <%}%>
            </td> 
        </tr>
        <tr>
            <td colspan="11" bgcolor="D6D7D6" height="1"></td>
        </tr>
        <% }
		} %> 
    </table>
    
    
    <table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 10px;">
        <tr>
            <td align="center">
            <%
			for(int i=1;i<=totalPage;i++){
			%>
				<a href="/listPurchase.do?page=<%=i%>"><%=i %></a>
			<%
				}
			%>	
            </td>
        </tr>
    </table>
    
    <!--  페이지 Navigator 끝 -->
    </form>
    
    </div>
    
    </body>
</html>