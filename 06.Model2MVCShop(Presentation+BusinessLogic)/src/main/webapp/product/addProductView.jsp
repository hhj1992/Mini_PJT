<%@ page contentType="text/html; charset=euc-kr" %>

<html>
    <head>
    <title>상품등록</title>
    
    <link rel="stylesheet" href="/css/admin.css" type="text/css">
    
    <script type="text/javascript" src="../javascript/calendar.js"></script>
    
    <script type="text/javascript">
    function fncAddProduct(){
    	var forms = document.detailForm; // from(derailFrom)에 있는 data를 다 가져다가 forms라는 변수에 담아놓은거야.
    	var name = forms.prodName.value;
    	var detail = forms.prodDetail.value;
    	var manuDate = forms.manuDate.value;
    	manuDate = manuDate.replaceAll("-",""); //20210821
    	document.getElementById("manuDate").value = manuDate;  	
 		<%--$('#manuDate').val(manuDate); --%>  
    	var price = forms.price.value;
    	var fileName = forms.fileName.value; 
    	
    	if(name == ''){
    		alert("상품명을 입력해주세요");
    		forms.prodName.focus();
    		return;
    	}
    	if(detail == ''){
    		alert("상품상세정보를 입력해주세요");
    		forms.prodDetail.focus();
    		return;
    	}
    	if(manuDate == ''){
    		alert("제조일자를 입력해주세요");
    		forms.manuDate.focus();
    		return;
    	}
    	if(price == ''){
    		alert("가격을 입력해주세요");
    		forms.price.focus();
    		return;
    	}
    	
    	if(forms.fileName.value == ""){
    		alert('상품이미지를 입력해주세요');
 			forms.fileName.focus();
 			return;
		}
    	
    		
    	if(confirm("등록하시겠습니까?")) { // confirm은 스크립트에 내장되있는 함수 alert와 같이 창이 뜨게한다.action이랑 summit이랑 셋트. confirm에 확인버튼 취소버튼 자동 생성. 이 안에 있나봄.? 
    		forms.action ="/addProduct.do";
    		forms.submit(); //submit을 누르면 action이 실행된다. 
    	}
    }
    
    function resetData(){
        document.detailForm.reset();
    }
    </script>
    </head>
    
    <body bgcolor="#ffffff" text="#000000">
    
     <form name="detailForm" method="post"> 
    
    <table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
        <tr>
            <td width="15" height="37">
                <img src="/images/ct_ttl_img01.gif" width="15" height="37"/>
            </td>
            <td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left: 10px;">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td width="93%" class="ct_ttl01">상품등록</td>
                        <td width="20%" align="right">&nbsp;</td>
                    </tr>
                </table>
            </td>
            <td width="12" height="37">
                <img src="/images/ct_ttl_img03.gif"	width="12" height="37"/>
            </td>
        </tr>
    </table>
    
    <table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top: 13px;">
        <tr>
            <td height="1" colspan="3" bgcolor="D6D6D6"></td>
        </tr>
        <tr>
            <td width="104" class="ct_write">
                상품명 <imgsrc="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle">
            </td>
            <td bgcolor="D6D6D6" width="1"></td>
            <td class="ct_write01">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td width="105">
                            <input type="text" name="prodName" class="ct_input_g" 
                                        style="width: 100px; height: 19px" maxLength="20">
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr>
            <td height="1" colspan="3" bgcolor="D6D6D6"></td>
        </tr>
        <tr>
            <td width="104" class="ct_write">
                상품상세정보 <img	src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
            </td>
            <td bgcolor="D6D6D6" width="1"></td>
            <td class="ct_write01">
                <input type="text" name="prodDetail" class="ct_input_g" 
                            style="width: 100px; height: 19px" maxLength="10" minLength="6"/>
            </td>
        </tr>
        <tr>
            <td height="1" colspan="3" bgcolor="D6D6D6"></td>
        </tr>
        <tr>
            <td width="104" class="ct_write">
                제조일자 <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
            </td>
            <td bgcolor="D6D6D6" width="1"></td>
            <td class="ct_write01">
                <input type="text" name="manuDate" id="manuDate" readonly="readonly" class="ct_input_g"  
                            style="width: 100px; height: 19px"	maxLength="10" minLength="6"/>
               <!-- &nbsp는 스페이스바 -->&nbsp;<img src="../images/ct_icon_date.gif" width="15" height="15" 
                                            onclick="show_calendar('document.detailForm.manuDate', document.detailForm.manuDate.value)"/>
            </td>
        </tr>
        <tr>
            <td height="1" colspan="3" bgcolor="D6D6D6"></td>
        </tr>
        <tr>
            <td width="104" class="ct_write">
                가격 <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
            </td>
            <td bgcolor="D6D6D6" width="1"></td>
            <td class="ct_write01"> <!-- Class는 Css적용을 의미함. --> 
                <input type="text" name="price" 	class="ct_input_g" 
                            style="width: 100px; height: 19px" maxLength="10">&nbsp;원
            </td>
        </tr>
        <tr>
            <td height="1" colspan="3" bgcolor="D6D6D6"></td>
        </tr>
       <tr>
            <td width="104" class="ct_write">상품이미지</td>
            <td bgcolor="D6D6D6" width="1"></td>
            <td class="ct_write01">
                <input	type="text" name="fileName" class="ct_input_g" 
                                style="width: 200px; height: 19px" maxLength="13"/>
            </td>
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
                    <td background="/images/ct_btnbg02.gif" class="ct_btn01"  style="padding-top: 3px;">
                        <a href="javascript:fncAddProduct()">등록</a>
                    </td>
                    <td width="14" height="23">
                        <img src="/images/ct_btnbg03.gif" width="14" height="23"/>
                    </td>
                    <td width="30"></td>
                    <td width="17" height="23">
                        <img src="/images/ct_btnbg01.gif" width="17" height="23"/>
                    </td>
                    <td background="/images/ct_btnbg02.gif" class="ct_btn01"	 style="padding-top: 3px;">
                        <a href="javascript:resetData();">취소</a>
                    </td>
                    <td width="14" height="23">
                        <img src="/images/ct_btnbg03.gif" width="14" height="23"/>
                    </td>
                </tr>
            </table>
            </td>
        </tr>
    </table>
    
    </form>
    </body>
    </html>