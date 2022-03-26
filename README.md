**<center style ="font-size:30px">Mini Shopping mall Project.</center>**

<br>

Encapsulation 되어있던 Spring Framework을 뜯어 분석했던 **01번 Project**을 시작으로  
<br>

> 02.Refactor & Page Navigation  
> 03.EL,JSTL  
> 04.Business Logic, MyBatis Spring  
> 05.AOP,Transaction  
> 06.Presentation+BusinessLogic  
> 07.URI,pattern  
> 08.ResFul Client&Server (Json)  
> 09.jQuery  
> 10.Ajax  
> 11.Bootstrap CDN

총 11번의 Refactoring을 통해 Admin 계정으로 상품을 등록, User에 의해 구매될 수 있는 **Mini Shopping mall Project.** 구현  
Model 1 Web Architecture에 MVC 개념이 추가된 **Model 2 Web Architecture**
유저(User) 관리, 상품(Product) 관리, 구매(Purchase) 관리의 3개의 module 구성

## MVC (Model-View-Controller) Pattern

**Contoller** : Business logic 수행, work flow control, M/V 연결, 단일 인입점  
Navigation (ForWard / SendRedirect)  
**Model** : DB data 처리  
**View** : View

end user, admin 로 접속했을 때 접근할 수 있는 page를 다르게 설정  
 (get 방식으로 key = value, value 값을 다르게 넘겨 작동하도록 함)

**계정별 접근 가능한 page**

- 비회원 : 상품검색, 최근 본 상품
- end user : 개인정보조회, 상품검색, 구매이력조회, 최근 본 상품
- admin : 개인정보조회, 회원정보조회, 판매 상품 등록, 판매 상품 관리, 상품검색,
  최근 본 상품
