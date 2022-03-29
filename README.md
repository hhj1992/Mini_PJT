<div align="center">

 # Mini Shopping mall 🛒
 
</div>

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
* Model 1 Web Architecture에 MVC 개념이 추가된 **Model 2 Web Architecture**  
* 유저(User) 관리, 상품(Product) 관리, 구매(Purchase) 관리의 3개의 module 구성

## Model 2 Web Architecture
### MVC (Model-View-Controller) Pattern 
>Model, View, Controller으로 분리한 디자인 패턴  
presentation view(UI)와 Business logic(Domain)의 분리로 서로 영향을 받지않고 각각의 유지보수가 가능.

* **Model**
   * 어플리케이션 정보, Data를 가지며 처음 정의 상수, 초기화 값, 변수 등을 뜻하며 Pojo로 구성된다.
   * DataBase에 접근하여 Business logic을 처리하고 결과값을 Contoller, View에 전달
   * View와 Contoller와 decoupling 관계
 * **View**  
   * Model에게 Data를 전달받아 화면에 표시해준다.
   * Model의 Data를 저장하지 않는다.
   * Data변경시 Model에게 전달하여 Model을 변경한다.
   * Model과 Contoller와 decoupling 관계 
   * presentation View 
 * **Contoller**    
   * 단일 인입점(Single Point of Entry)
   * Business logic 수행한다.
   * Work Flow Control 선처리 / 공통처리
   * M/V 연결(인터페이스의 역활)
   * Navigation (ForWard / SendRedirect)
   * Java 코드 중심의 Servlet

end user, admin 로 접속했을 때 접근할 수 있는 page를 다르게 설정  
 (get 방식으로 key = value, value 값을 다르게 넘겨 작동하도록 함)

**계정별 접근 가능한 page**

- 비회원 : 상품검색, 최근 본 상품
- end user : 개인정보조회, 상품검색, 구매이력조회, 최근 본 상품
- admin : 개인정보조회, 회원정보조회, 판매 상품 등록, 판매 상품 관리, 상품검색,
  최근 본 상품
