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
<title>상품 목록조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
	function fncGetProductList(currentPage) {
		console.log(currentPage);
		document.getElementById("currentPage").value = currentPage;
		//currentPage라는 ID값을 가진 value값에 매개변수로 받아온 currentPage값을 세팅
		//document.detailForm.currentPage.value = currentPage;  
		//currentPage라는 name값을 가진 value값에 매개변수로 받아온 currentPage값을 세팅
		document.detailForm.submit(); //detailForm submit 어디로? form에있는 action으로 => /listUser.do
	}
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

	<div style="width: 98%; margin-left: 10px;">

		<form name="detailForm" action="/listProduct.do?menu=${menu}"
			method="post">
			<input type="hidden" name="menu" value="${menu}" />
			<%--히든뭐야!  --%>

			<table width="100%" height="37" border="0" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="15" height="37"><img src="/images/ct_ttl_img01.gif"
						width="15" height="37" /></td>
					<td background="/images/ct_ttl_img02.gif" width="100%"
						style="padding-left: 10px;">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="93%" class="ct_ttl01">상품 목록 조회</td>
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
								${ ! empty search.searchCondition && search.searchCondition==0 ? "selected" : "" }>상품번호</option>
							<option value="1"
								${ ! empty search.searchCondition && search.searchCondition==1 ? "selected" : "" }>상품명</option>
							<option value="2"
								${ ! empty search.searchCondition && search.searchCondition==2 ? "selected" : "" }>상품가격</option>
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
									href="javascript:fncGetProductList();">검색</a></td>
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
					<td colspan="11">전체 ${resultPage.totalCount} 건수, 현재
						${resultPage.currentPage} 페이지</td>
				</tr>
				<tr>
					<td class="ct_list_b" width="100">No</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b" width="150">상품명</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b" width="150">가격</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b">등록일</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b">현재상태</td>
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
							<c:when test="${resultPage.currentPage <= resultPage.pageUnit}"> <%--EL끼리의 계산은 ${}안에 하세요.--%>
 					            ◀ 이전  <%--현재 페이지가 1부터 5페이지일경우에는 이전페이지가 글자만 나오게 합니다. 누를 수 없어요. --%>
							</c:when>
							<c:otherwise>
								<a href="javascript:fncGetProductList('${resultPage.currentPage-1}')">
								◀ 이전</a>
								<%--현재 페이지가 6페이지 이상일경우 바로 전페이지로 갈 수있는 링크를 달아준다.--%>
							</c:otherwise>
					   	</c:choose> <c:forEach var="i" begin="${resultPage.beginUnitPage}"
							end="${resultPage.endUnitPage}">
							<a href="javascript:fncGetProductList('${i}');">${i}</a>
						</c:forEach> 
						<c:choose>
							<c:when test="${resultPage.endUnitPage >= resultPage.maxPage}">  
 								이후 ▶  
 							</c:when>
							<c:otherwise>
								<a href="javascript:fncGetProductList('${resultPage.endUnitPage+1}')">이후 ▶</a>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
			</table>


		</form>

	</div>
</body>
</html>
