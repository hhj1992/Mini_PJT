<%@ page contentType="text/html; charset=euc-kr"%>
<%@ page pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
	<meta charset="EUC-KR">
	
	<!-- ���� : http://getbootstrap.com/css/   ���� -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	
	<!--  ///////////////////////// Bootstrap, jQuery CDN ////////////////////////// -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" >
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>
	
	
	<!-- Bootstrap Dropdown Hover CSS -->
   <link href="/css/animate.min.css" rel="stylesheet">
   <link href="/css/bootstrap-dropdownhover.min.css" rel="stylesheet">
    <!-- Bootstrap Dropdown Hover JS -->
   <script src="/javascript/bootstrap-dropdownhover.min.js"></script>
   
   
   <!-- jQuery UI toolTip ��� CSS-->
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <!-- jQuery UI toolTip ��� JS-->
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	
	<!--  ///////////////////////// CSS ////////////////////////// -->
	<style>
	  body {
            padding-top : 50px;
        }
    </style>
    
     <!--  ///////////////////////// JavaScript ////////////////////////// -->
	<script type="text/javascript">
<link rel="stylesheet" href="/css/admin.css" type="text/css">
	

<title>��ǰ �����ȸ</title>


	function fncGetProductList(currentPage) {
		document.getElementById("currentPage").value = currentPage;
		//currentPage��� ID���� ���� value���� �Ű������� �޾ƿ� currentPage���� ����
		//document.detailForm.currentPage.value = currentPage;  
		//currentPage��� name���� ���� value���� �Ű������� �޾ƿ� currentPage���� ����
		document.detailForm.submit(); //detailForm submit ����? form���ִ� action���� => /listUser.do
	}
</script>
</head>

<body >
	
	<!--  ȭ�鱸�� div Start /////////////////////////////////////-->
	
		<div class="page-header text-info">
	       <h3>��ǰ����</h3>
	    </div>
	    
	    <!-- table ���� �˻� Start /////////////////////////////////////-->
	    <div class="row">

		<div class="col-md-6 text-left">
			<p class="text-primary">
		    		��ü  ${resultPage.totalCount } �Ǽ�, ���� ${resultPage.currentPage}  ������
		    	</p>
		    </div>

		<form name="detailForm" action="/product/listProduct?menu=${menu}" method="post">
			<input type="hidden" name="menu" value="${menu}" />

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
					</select> <input type="text" name="searchKeyword" class="ct_input_g"
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
							href="/product/updateProductView?prodNo=${value.prodNo}">${value.prodName}</a>
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
								<a href="javascript:fncGetProductList('${resultPage.endUnitPage+1}')"> ���� ��</a>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
			</table>


		</form>

	</div>
</body>
</html>
