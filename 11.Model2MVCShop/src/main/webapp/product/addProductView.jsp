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
body>div.container {
        	margin-top: 10px;
            color: #000;
}

body {		padding-top: 50px;
			background: #eee;
			color: #000;
			font-family: 'Gowun Batang', serif;	
    		padding-top : 50px;
}
</style>



<title>��ǰ���</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript" src="../javascript/calendar.js"></script>
<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
<script type="text/javascript">
    function fncAddProduct(){
    	//var forms = document.detailForm; // from(derailFrom)�� �ִ� data�� �� �����ٰ� forms��� ������ ��Ƴ����ž�.
    	//var name = forms.prodName.value;
    	//var detail = forms.prodDetail.value;
    	//var manuDate = forms.manuDate.value;
    	//manuDate = manuDate.replaceAll("-",""); 
    	//document.getElementById("manuDate").value = manuDate;  	
 		<%--$('#manuDate').val(manuDate); --%>  
    	//var price = forms.price.value;
    	//var fileName = forms.fileName.value; 
    	
    	var forms = $("form[name='detailForm']");
    	var name = $("input[name='prodName']").val();
    	var detail = $("input[name='prodDetail']").val();
    	var manuDate = $("input[name='manuDate']").val();
    	$("#manuDate").val(manuDate.replace("-",""));  
    	//$("input[name:'manuDate']").val(manuDate);
    	var price = $("input[name='price']").val();
    	var fileName = $("input[name='file']").val();
    	
    	
    	if(name == ''){
    		alert("��ǰ���� �Է����ּ���");
    		//forms.prodName.focus();
    		$("input:text[name='prodName']").focus();
    		return;
    	}
    	if(detail == ''){
    		alert("��ǰ�������� �Է����ּ���");
    		//forms.prodDetail.focus();
    		$("input:text[name='prodDetail']").focus();
    		return;
    	}
    	if(manuDate == ''){
    		alert("�������ڸ� �Է����ּ���");
    		//forms.manuDate.focus();
    		$("input:text[name='manuDate']").focus();
    		return;
    	}
    	if(price == ''){
    		alert("������ �Է����ּ���");
    		//forms.price.focus();
    		$("input:text[name='price']").focus();
    		return;
    	}
    	
/*     	if(fileName == ""){
    		alert('��ǰ�̹����� �Է����ּ���');
 			//forms.fileName.focus();
 			$("input:text[name='fileName']").focus();
 			
 			return; 
		}*/
    	
    		
    	if(confirm("����Ͻðڽ��ϱ�?")) { // confirm�� ��ũ��Ʈ�� ������ִ� �Լ� alert�� ���� â�� �߰��Ѵ�.action�̶� summit�̶� ��Ʈ. confirm�� Ȯ�ι�ư ��ҹ�ư �ڵ� ����. �� �ȿ� �ֳ���.? 
    		
    		//forms.action ="/product/addProduct";
    		//forms.submit(); //submit�� ������ action�� ����ȴ�. 
    		
    		$("form").attr("method" , "POST").attr("action","/product/addProduct").attr("enctype","multipart/form-data").submit();	
    		
    		
    	}
    }//end function
    
    $(function() {
		 $("button:button[name='1']").on("click" , function() {
			//Debug..
			fncAddProduct();
		 });
			
		 $("button:button[name='2']").on("click" , function() {
			 $("form")[0].reset();
		 });
    });
    
    </script>
</head>

<body>


	<!-- ToolBar Start /////////////////////////////////////-->
	<jsp:include page="/layout/toolbar.jsp" />
	<!-- ToolBar End /////////////////////////////////////-->



	<div class="container">
		<form class="form-horizontal" name="detailForm" enctype="multipart/form-data">
			<h1 class=" text-center">�� ǰ �� ��</h1>

			<div class="form-group">
				<label for="prodName" class="col-sm-offset-1 col-sm-3 control-label">��
					ǰ �� </label>
				<div class="col-sm-4">
					<input type="text" class="form-control" id="prodName"
						name="prodName">
				</div>
			</div>

			<div class="form-group">
				<label for="prodDetail"
					class="col-sm-offset-1 col-sm-3 control-label">�� ǰ �� �� �� ��</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" id="prodDetail"
						name="prodDetail">
				</div>
			</div>

			<div class="form-group">
				<label for="manuDate" class="col-sm-offset-1 col-sm-3 control-label">��
					�� �� ��</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" id="manuDate"
						name="manuDate" readonly="readonly">
				</div>
				<div class="col-sm-2">
					<img src="../images/ct_icon_date.gif" width="30" height="30"
						onclick="show_calendar('document.detailForm.manuDate', document.detailForm.manuDate.value)" />

				</div>
			</div>

			<div class="form-group">
				<label for="price" id="123"
					class="col-sm-offset-1 col-sm-3 control-label">�� ��</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" id="price" name="price">
				</div>
				<div class="col-sm-2">
					<label class="control-label">��</label>
				</div>
			</div>

			<div class="form-group">
				<label for="file" class="col-sm-offset-1 col-sm-3 control-label">��
					ǰ �� �� �� </label>
				<div class="col-sm-4">
					<input type="file" class="form-control" id="ch" name="ch">
				</div> 
			</div>


			<div class="form-group">
				<div class="col-sm-offset-4  col-sm-4 text-center">
					<button type="button" name="1" class="btn btn-default">��&nbsp;��</button>
					<button type="button" name="2" class="btn btn-default">��&nbsp;��</button>
				</div>
			</div>



		</form>
	</div>
</body>
</html>