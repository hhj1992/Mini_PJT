package com.model2.mvc.web.product;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.prouduct.ProductService;
import com.model2.mvc.service.prouduct.impl.ProductServiceImpl;
import com.model2.mvc.service.user.UserService;

//==> 상품관리 Controller
//==> 반환타입이 있으면 
// 그 반환타입을 json 객체 변경 후 return.

@RestController
@RequestMapping("/product/*")
public class ProductRestController {
	
	///Field
	
	@Autowired
	@Qualifier("ProductServiceImpl")
	private ProductService productService;
	//setter Method 구현 않음
	
	@Value("#{commonProperties['pageUnit']}")
	//@Value("#{commonProperties['pageUnit'] ?: 3}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	//@Value("#{commonProperties['pageSize'] ?: 2}")
	int pageSize;
		
	public ProductRestController(){
		System.out.println(this.getClass());
	}
		
	@RequestMapping(value="json/addProduct", method=RequestMethod.POST)
	public int addProduct( @RequestBody Product product) throws Exception {

		System.out.println("json/addProduct");
		
		//Business Logic
		
//		String string01 = new String();
//		string01 = productService.addProduct(product);
//		System.out.println(string01);
		return productService.addProduct(product); //"redirect:/product/listProduct?menu=manage";
	}
	
	/*
	 * @RequestMapping( value="getProduct",method=RequestMethod.GET ) public String
	 * getProduct( @RequestParam("prodNo") int prodNo , Model model ) throws
	 * Exception {
	 * 
	 * System.out.println("getProduct"); //Business Logic Product product =
	 * productService.getProduct(prodNo); // Model 과 View 연결
	 * model.addAttribute("product", product);
	 * 
	 * return "forward:/product/getProduct.jsp"; }
	 * 
	 * @RequestMapping(value="updateProduct",method=RequestMethod.POST) public
	 * String updateProduct( @ModelAttribute("product") Product product , Model
	 * model) throws Exception{
	 * 
	 * System.out.println("updateProduct"); //Business Logic
	 * productService.updateProduct(product);
	 * 
	 * return "forward:/product/listProduct?menu=manage"; }
	 * 
	 * @RequestMapping(value="updateProductView",method=RequestMethod.GET) public
	 * String updateProductView( @RequestParam("prodNo") int prodNo , Model model ,
	 * HttpSession session) throws Exception{
	 * 
	 * System.out.println("updateProductView"); //Business Logic // Model modell =
	 * (Model) productService.getProduct(prodNo); Product product =
	 * productService.getProduct(prodNo); // Model 과 View 연결
	 * model.addAttribute("product", product);
	 * 
	 * return "forward:/product/updateProduct.jsp"; } //
	 * // @RequestMapping("/loginView.do") // public String loginView() throws
	 * Exception{ // // System.out.println("/loginView.do"); // // return
	 * "redirect:/user/loginView.jsp"; // } // // @RequestMapping("/login.do") //
	 * public String login(@ModelAttribute("user") User user , HttpSession session )
	 * throws Exception{ // // System.out.println("/login.do"); // //Business Logic
	 * // User dbUser=productService.getUser(user.getUserId()); // // if(
	 * user.getPassword().equals(dbUser.getPassword())){ //
	 * session.setAttribute("user", dbUser); // } // // return
	 * "redirect:/index.jsp"; // } // // @RequestMapping("/logout.do") // public
	 * String logout(HttpSession session ) throws Exception{ // //
	 * System.out.println("/logout.do"); // // session.invalidate(); // // return
	 * "redirect:/index.jsp"; // } // // @RequestMapping("/checkDuplication.do") //
	 * public String checkDuplication( @RequestParam("userId") String userId , Model
	 * model ) throws Exception{ // // System.out.println("/checkDuplication.do");
	 * // //Business Logic // boolean result=userService.checkDuplication(userId);
	 * // // Model 과 View 연결 // model.addAttribute("result", new Boolean(result));
	 * // model.addAttribute("userId", userId); // // return
	 * "forward:/user/checkDuplication.jsp"; // } //
	 * 
	 * @RequestMapping( value="listProduct") public String
	 * listProduct(@ModelAttribute("search") Search search , Model model ,
	 * HttpServletRequest request) throws Exception{
	 * 
	 * System.out.println("listProduct");
	 * 
	 * if(search.getCurrentPage() ==0 ){ search.setCurrentPage(1); }
	 * search.setPageSize(pageSize);
	 * 
	 * // Business logic 수행 Map<String , Object>
	 * map=productService.getProductList(search);
	 * 
	 * Page resultPage = new Page( search.getCurrentPage(),
	 * ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
	 * System.out.println(resultPage);
	 * 
	 * // Model 과 View 연결 model.addAttribute("list", map.get("list"));
	 * model.addAttribute("resultPage", resultPage); model.addAttribute("search",
	 * search); model.addAttribute("menu","search");
	 * 
	 * String returnurl = "forward:/product/listProduct.jsp";
	 * 
	 * if (request.getParameter("menu").equals("manage")){
	 * model.addAttribute("menu","manage"); returnurl =
	 * "forward:/product/updateProductView.jsp"; // forward로 넘길때는 requestAttribute
	 * or addAttribute로 setting해야함. } return returnurl ; }
	 */

}