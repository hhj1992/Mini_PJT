<%@ page contentType="text/html; charset=EUC-KR" %>

<html>
<head>

<title>열어본 상품 보기</title>

</head>
<body>
	당신이 열어본 상품을 알고 있다
	
<br>
<br>
<%
	request.setCharacterEncoding("euc-kr");
	response.setCharacterEncoding("euc-kr");
	
	String history = null;						// history 정의. (값을 초기화한다.)
		Cookie[] cookies = request.getCookies();    // request.getCookies로 받은 걸 cookies 넣는다. 
		if (cookies!=null && cookies.length > 0) {   // 쿠키가 존재하면 
		for (int i = 0; i < cookies.length; i++) {	// 0부터 쿠키 길이만큼 반복
		Cookie cookie = cookies[i];	//쿠키 []에 넣는다. 
		// 쿠키의 getName은 history될거야. 
		if (cookie.getName().equals("history")) { // 만약에 쿠키의 이름이 history이면 
		history = cookie.getValue(); // cookie의 value를 꺼내서 history에 넣는다. 
			}
		}
		if (history != null) {						// history가 존재하면
			String[] h = history.split(",");		//history를 , 기준으로 잘라서 h에 []어 넣는다. 
			for (int i = 0; i < h.length; i++) {	// 0부터 h[] 길이만큼 하나씩 증가해서
				if (!h[i].equals("null")) {	// h[]번째를 null과 비교해서 null이 아니면 
					
%>											<!-- 밑에 링크가 나타나게한다.  -->
<a href="/getProduct.do?prodNo=<%=h[i]%>&menu=search"	target="rightFrame"><%=h[i]%></a> <!-- h[i] 상품번호가 들어가야함.  -->
<br>
<%
				}
			}
		}
	}
%>


</body>
</html>