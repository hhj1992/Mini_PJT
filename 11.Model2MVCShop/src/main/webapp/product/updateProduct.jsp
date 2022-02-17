<%@ page contentType="text/html; charset=euc-kr"%>

<html>
<head>

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Gowun+Batang:wght@400;700&display=swap" rel="stylesheet">
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
    function fncUpdateProduct(){

    	
    	var forms = $("form[name='detailForm']");
    	var name = $("input[name='prodName']").val();
    	var detail = $("input[name='prodDetail']").val();
    	var manuDate = $("input[name='manuDate']").val();
    	$("#manuDate").val(manuDate.replace("-",""));  
    	//$("input[name:'manuDate']").val(manuDate);
    	var price = $("input[name='price']").val();
    	var fileName = $("input[name='file']").val();
    	
    	
    	if(name == ''){
    		alert("상품명을 입력해주세요");
    		//forms.prodName.focus();
    		$("input:text[name='prodName']").focus();
    		return;
    	}
    	if(detail == ''){
    		alert("상품상세정보를 입력해주세요");
    		//forms.prodDetail.focus();
    		$("input:text[name='prodDetail']").focus();
    		return;
    	}
    	if(manuDate == ''){
    		alert("제조일자를 입력해주세요");
    		//forms.manuDate.focus();
    		$("input:text[name='manuDate']").focus();
    		return;
    	}
    	if(price == ''){
    		alert("가격을 입력해주세요");
    		//forms.price.focus();
    		$("input:text[name='price']").focus();
    		return;
    	}
    	
/*     	if(fileName == ""){
    		alert('상품이미지를 입력해주세요');
 			//forms.fileName.focus();
 			$("input:text[name='fileName']").focus();
 			
 			return; 
		}*/
    	
    		
    	if(confirm("수정하시겠습니까?")) { // confirm은 스크립트에 내장되있는 함수 alert와 같이 창이 뜨게한다.action이랑 summit이랑 셋트. confirm에 확인버튼 취소버튼 자동 생성. 이 안에 있나봄.? 
    		
    		//forms.action ="/product/addProduct";
    		//forms.submit(); //submit을 누르면 action이 실행된다. 
    		
    		$("form").attr("method" , "POST").attr("action","/product/updateProduct").attr("enctype","multipart/form-data").submit();	
    		
    		
    	}
    }//end function
    
    $(function() {
		 $("button:button[name='1']").on("click" , function() {
			//Debug..
			fncUpdateProduct();
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
			<h1 class=" text-center">상 품 수 정</h1>

			<div class="form-group">
				<label for="prodName" class="col-sm-offset-1 col-sm-3 control-label">상
					품 명 </label>
				<div class="col-sm-4">
					<input type="text" class="form-control" id="prodName"
						name="prodName" value="${product.prodName}">
				</div>
			</div>

			<div class="form-group">
				<label for="prodDetail"
					class="col-sm-offset-1 col-sm-3 control-label">상 품 상 세 정 보</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" id="prodDetail"
						name="prodDetail" value="${product.prodDetail}">
				</div>
			</div>

			<div class="form-group">
				<label for="manuDate" class="col-sm-offset-1 col-sm-3 control-label">제
					조 일 자</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" id="manuDate"
						name="manuDate" readonly="readonly" value="${product.manuDate}">
				</div>
				<div class="col-sm-2">
					<img src="../images/ct_icon_date.gif" width="30" height="30"
						onclick="show_calendar('document.detailForm.manuDate', document.detailForm.manuDate.value)" />

				</div>
			</div>

			<div class="form-group">
				<label for="price" id="123"
					class="col-sm-offset-1 col-sm-3 control-label">가 격</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" id="price" name="price" value="${product.price}">
				</div>
				<div class="col-sm-2">
					<label class="control-label">원</label>
				</div>
			</div>

			<div class="form-group">
				<label for="file" class="col-sm-offset-1 col-sm-3 control-label">상
					품 이 미 지 </label>
				<div class="col-sm-4">
					<input type="file" class="form-control" id="ch" name="ch">
				</div>
			</div>
			
			<div class="form-group">
				<div class="col-sm-4">
					<img src="${product.fileName}"/>
				</div>
			</div>


			<div class="form-group">
				<div class="col-sm-offset-4  col-sm-4 text-center">
					<button type="button" name="1" class="btn btn-default">수&nbsp;정</button>
					<button type="button" name="2" class="btn btn-default">취&nbsp;소</button>
				</div>
			</div>



		</form>
	</div>
</body>
</html>