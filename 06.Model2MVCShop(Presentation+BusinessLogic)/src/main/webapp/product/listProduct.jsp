<%-- <%@page import="com.model2.mvc.common.util.CommonUtil"%>
<%@page import="com.model2.mvc.common.Page"%>
<%@page import="com.model2.mvc.service.domain.Product"%>
<%@page import="com.model2.mvc.common.Search"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%> --%>
<%@ page contentType="text/html; charset=euc-kr" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>



<head>
<title>��ǰ �����ȸ</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
	function fncGetProductList(currentPage) {
		console.log(currentPage);
		document.getElementById("currentPage").value = currentPage;
		//currentPage��� ID���� ���� value���� �Ű������� �޾ƿ� currentPage���� ����
		//document.detailForm.currentPage.value = currentPage;  
		//currentPage��� name���� ���� value���� �Ű������� �޾ƿ� currentPage���� ����
		document.detailForm.submit(); //detailForm submit ����? form���ִ� action���� => /listUser.do
	}
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

	<div style="width: 98%; margin-left: 10px;">

		<form name="detailForm" action="/listProduct.do?menu=${menu}"
			method="post">
			<input type="hidden" name="menu" value="${menu}" />
			<%--���繹��!  --%>

			<table width="100%" height="37" border="0" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="15" height="37"><img src="/images/ct_ttl_img01.gif"
						width="15" height="37" /></td>
					<td background="/images/ct_ttl_img02.gif" width="100%"
						style="padding-left: 10px;">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="93%" class="ct_ttl01">��ǰ ��� ��ȸ</td>
							</tr>
						</table>
					</td>
					<td width="12" height="37"><img src="/images/ct_ttl_img03.gif"
						width="12" height="37" /></td>
				</tr>
			</table>


			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				style="margin-top: 10px;">
				<tr>
					<td align="right"><select name="searchCondition"
						class="ct_input_g" style="width: 80px">
							<option value="0"
								${ ! empty search.searchCondition && search.searchCondition==0 ? "selected" : "" }>��ǰ��ȣ</option>
							<option value="1"
								${ ! empty search.searchCondition && search.searchCondition==1 ? "selected" : "" }>��ǰ��</option>
							<option value="2"
								${ ! empty search.searchCondition && search.searchCondition==2 ? "selected" : "" }>��ǰ����</option>
					</select> <input type="text" name="searchKeyword"
						value="${search.searchKeyword}" class="ct_input_g"
						style="width: 200px; height: 19px" /></td>
					<td align="right" width="70">
						<table border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="17" height="23"><img
									src="/images/ct_btnbg01.gif" width="17" height="23"></td>
								<td background="/images/ct_btnbg02.gif" class="ct_btn01"
									style="padding-top: 3px;"><a
									href="javascript:fncGetProductList();">�˻�</a></td>
								<td width="14" height="23"><img
									src="/images/ct_btnbg03.gif" width="14" height="23"></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>


			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				style="margin-top: 10px;">
				<tr>
					<td colspan="11">��ü ${resultPage.totalCount} �Ǽ�, ����
						${resultPage.currentPage} ������</td>
				</tr>
				<tr>
					<td class="ct_list_b" width="100">No</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b" width="150">��ǰ��</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b" width="150">����</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b">�����</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b">�������</td>
				</tr>
				<tr>
					<td colspan="11" bgcolor="808285" height="1"></td>
				</tr>

				<c:forEach var="value" items="${list}" varStatus="status">




					<tr class="ct_list_pop">
						<td align="center">${status.count}</td>
						<td></td>
						<td align="left"><a
							href="/getProduct.do?prodNo=${value.prodNo}">${value.prodName}</a>
						</td>
						<td></td>
						<td align="left">${value.price}</td>
						<td></td>
						<td align="left">${value.regDate}</td>
						<td>
						<td align="left">${value.proTranCode}</td>
					</tr>
					<tr>
						<td colspan="11" bgcolor="D6D7D6" height="1"></td>
					</tr>


				</c:forEach>
			</table>


			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				style="margin-top: 10px;">
				<tr>
					<td align="center"><input type="hidden" id="currentPage"
						name="currentPage" value="" /> 
						<c:choose>
							<c:when test="${resultPage.currentPage <= resultPage.pageUnit}"> <%--EL������ ����� ${}�ȿ� �ϼ���.--%>
 					            �� ����  <%--���� �������� 1���� 5�������ϰ�쿡�� ������������ ���ڸ� ������ �մϴ�. ���� �� �����. --%>
							</c:when>
							<c:otherwise>
								<a href="javascript:fncGetProductList('${resultPage.currentPage-1}')">
								�� ����</a>
								<%--���� �������� 6������ �̻��ϰ�� �ٷ� ���������� �� ���ִ� ��ũ�� �޾��ش�.--%>
							</c:otherwise>
					   	</c:choose> <c:forEach var="i" begin="${resultPage.beginUnitPage}"
							end="${resultPage.endUnitPage}">
							<a href="javascript:fncGetProductList('${i}');">${i}</a>
						</c:forEach> 
						<c:choose>
							<c:when test="${resultPage.endUnitPage >= resultPage.maxPage}">  
 								���� ��  
 							</c:when>
							<c:otherwise>
								<a href="javascript:fncGetProductList('${resultPage.endUnitPage+1}')">���� ��</a>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
			</table>


		</form>

	</div>
</body>
</html>
