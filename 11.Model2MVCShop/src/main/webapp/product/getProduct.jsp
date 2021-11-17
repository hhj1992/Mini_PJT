<%@ page contentType="text/html; charset=euc-kr"%>

<html>
<head>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Gowun+Batang:wght@400;700&display=swap" rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script>
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style>

       body > div.container{
        	margin-top: 10px;
            color: #000;
        }
        
        body {
			padding-top: 50px;
			background: #eee;
			color: #000;
			font-family: 'Gowun Batang', serif;	
    		padding-top : 50px;
}
    </style>
   



<title>상품수정</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript" src="../javascript/calendar.js"></script>
<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
<script type="text/javascript">

    
    $(function() {
		 $("button:button[name='1']").on("click" , function() {
			//Debug..
			alert('구매구현');
		 });
			
		 $("button:button[name='2']").on("click" , function() {
			 history.go(-1);
		 });
    });
    
    </script>
</head>

<body>


	<!-- ToolBar Start /////////////////////////////////////-->
	<jsp:include page="/layout/toolbar.jsp" />
	<!-- ToolBar End /////////////////////////////////////-->



	<div class="container">
		<form class="form-horizontal" name="detailForm"
			enctype="multipart/form-data">
			<input type="hidden" name="prodNo" value="${product.prodNo}"/>
			<h1 class=" text-center">상 품 정 보</h1>

			<div class="form-group">
				<label for="prodName" class="col-sm-offset-1 col-sm-3 control-label">상
					품 명 </label>
				<div class="col-sm-4">
					${product.prodName}
				</div>
			</div>

			<div class="form-group">
				<label for="prodDetail"
					class="col-sm-offset-1 col-sm-3 control-label">상 품 상 세 정 보</label>
				<div class="col-sm-4">
					${product.prodDetail}
				</div>
			</div>

			<div class="form-group">
				<label for="manuDate" class="col-sm-offset-1 col-sm-3 control-label">제
					조 일 자</label>
				<div class="col-sm-4">
					${product.manuDate}
				</div>
			</div>

			<div class="form-group">
				<label for="price" id="123"
					class="col-sm-offset-1 col-sm-3 control-label">가 격</label>
				<div class="col-sm-4">
					${product.price}
				</div>
				<div class="col-sm-2">
					<label class="control-label">원</label>
				</div>
			</div>

			<div class="form-group">
				<label for="file" class="col-sm-offset-1 col-sm-3 control-label">상
					품 이 미 지 </label>
				<div class="col-sm-4">
				<img src="${product.fileName}"/>
				</div>
			</div>


			<div class="form-group">
				<div class="col-sm-offset-4  col-sm-4 text-center">
					<button type="button" name="1" class="btn btn-default">구&nbsp;매</button>
					<button type="button" name="2" class="btn btn-default">취&nbsp;소</button>
				</div>
			</div>



		</form>
	</div>
</body>
</html>