<%@ page contentType="text/html; charset=EUC-KR" %>

<html>
<head>

<title>��� ��ǰ ����</title>

</head>
<body>
	����� ��� ��ǰ�� �˰� �ִ�
	
<br>
<br>
<%
	request.setCharacterEncoding("euc-kr");
	response.setCharacterEncoding("euc-kr");
	
	String history = null;						// history ����. (���� �ʱ�ȭ�Ѵ�.)
		Cookie[] cookies = request.getCookies();    // request.getCookies�� ���� �� cookies �ִ´�. 
		if (cookies!=null && cookies.length > 0) {   // ��Ű�� �����ϸ� 
		for (int i = 0; i < cookies.length; i++) {	// 0���� ��Ű ���̸�ŭ �ݺ�
		Cookie cookie = cookies[i];	//��Ű []�� �ִ´�. 
		// ��Ű�� getName�� history�ɰž�. 
		if (cookie.getName().equals("history")) { // ���࿡ ��Ű�� �̸��� history�̸� 
		history = cookie.getValue(); // cookie�� value�� ������ history�� �ִ´�. 
			}
		}
		if (history != null) {						// history�� �����ϸ�
			String[] h = history.split(",");		//history�� , �������� �߶� h�� []�� �ִ´�. 
			for (int i = 0; i < h.length; i++) {	// 0���� h[] ���̸�ŭ �ϳ��� �����ؼ�
				if (!h[i].equals("null")) {	// h[]��°�� null�� ���ؼ� null�� �ƴϸ� 
					
%>											<!-- �ؿ� ��ũ�� ��Ÿ�����Ѵ�.  -->
<a href="/getProduct.do?prodNo=<%=h[i]%>&menu=search"	target="rightFrame"><%=h[i]%></a> <!-- h[i] ��ǰ��ȣ�� ������.  -->
<br>
<%
				}
			}
		}
	}
%>


</body>
</html>