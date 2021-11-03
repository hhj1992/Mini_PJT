package com.model2.mvc.web.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.user.UserService;

//==> ȸ������ RestController Spring���� Controller �� View�� �������� �ʴ� Controller�� �ǹ� 
//==> ��ȯŸ���� ������ �� ��ȯŸ���� json ���� �� return.

//��ü���� ��縸 JSON / DaTa Tyoe�� String [ json { : } ] ��ȯ�Ǵ� Data type String

@RestController
@RequestMapping("/user/*")
public class UserRestController {

	/// Field

	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	// setter Method ���� ����

	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	@Value("#{commonProperties['pageSize']}")
	int pageSize;

	public UserRestController() {
		System.out.println(this.getClass());
	}

	/*
	 * @RequestMapping( value="json/addUser", method=RequestMethod.GET ) public
	 * String addUser() throws Exception{
	 * 
	 * System.out.println("/user/addUser : GET");
	 * 
	 * return "redirect:/user/addUserView.jsp"; }
	 *
	 */
	@RequestMapping(value = "json/addUser", method = RequestMethod.POST)
	public User addUser(@RequestBody User user) throws Exception { 

		System.out.println("/user/addUser : POST:");

		userService.addUser(user);
		
		User user01 = new User();
		user01 = userService.getUser(user.getUserId()); 
		return user01;
	}	

//	@RequestMapping(value = "json/updateUser/{userId}/{userName}/{phone}/{addr}/{email}", method = RequestMethod.GET)
//	@RequestMapping(value = "json/updateUser/{userId}/{userName}/{phone}/{addr}/{email}", method = RequestMethod.GET)
	public User updateUser(@PathVariable String userId, User user) throws Exception { 
/*	public User updateUser(
							@PathVariable("userId") String userId,
							@PathVariable("userName") String userName,
							@PathVariable("phone") String phone,
							@PathVariable("addr")String addr,
							@PathVariable("email") String email
							) throws Exception { 
		
		System.out.println("/user/json/updateUser : GET:");		
		System.out.println("userId:"+userId);
		System.out.println("userName:"+userName);
		System.out.println("phone:"+phone);
		System.out.println("addr:"+addr);
		System.out.println("email:"+email);*/
		
		System.out.println("User:"+user);
		
		//user.setUserId(userId);			
		userService.updateUser(user);
		
		User user01 = new User();
		
		user01 = userService.getUser(userId);
		return user01;
	}	
	
	@RequestMapping(value = "json/updateUser", method = RequestMethod.POST)
	public User updateUser(@RequestBody User user) throws Exception { 
		
		System.out.println("/user/updateUser : GET:");		
		System.out.println("UserName:"+user.getUserId());
		System.out.println("User:"+user);
		
		userService.updateUser(user);
		
		User user01 = new User();
		
		user01 = userService.getUser(user.getUserId());
		return user01;
	}	
	
	@RequestMapping(value = "json/getUser/{userId}", method = RequestMethod.GET)
	public User getUser(@PathVariable String userId) throws Exception {

		System.out.println("/user/json/getUser : GET");

		// Business Logic
		return userService.getUser(userId);
	}

	@RequestMapping(value = "json/login", method = RequestMethod.POST)
	public User login(@RequestBody User user, HttpSession session) throws Exception {

		System.out.println("/user/json/login : POST");
		// Business Logic
		System.out.println("::" + user);
		
		User dbUser = userService.getUser(user.getUserId());

		if (user.getPassword().equals(dbUser.getPassword())) {
			session.setAttribute("user", dbUser);
		}

		return dbUser;
	}

	@RequestMapping(value = "json/listUser")
	public Map<String, Object> listUser(Search search) throws Exception {

		System.out.println("/json/listUser : GET / POST");

		if (search.getCurrentPage() == 0) {
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);

		// Business logic ����
		Map<String, Object> map = userService.getUserList(search);

		Page resultPage = new Page(search.getCurrentPage(), ((Integer) map.get("totalCount")).intValue(), pageUnit,
				pageSize);
		System.out.println(resultPage);

		// controller �� clinet�� ����
		Map<String, Object> Ain = new HashMap<String, Object>();

		Ain.put("list", map.get("list"));
		Ain.put("resultPage", resultPage);
		Ain.put("search", search);

		// UserController�� RestCoutroller�� ����
		// Model�� �ְ� Map�� �ְ��� ����? model�� ui�� wep�� �ƴ� ����̽����� ����� �Ұ�?

		return Ain;
	}
	
	@RequestMapping( value="/json/checkDuplication", method=RequestMethod.POST )
	public Map<String, Object> checkDuplication(@RequestBody User user ) throws Exception{
		
		System.out.println("/user//json/checkDuplication: POST");
		
		boolean result = userService.checkDuplication(user.getUserId());
		
		System.out.println("result:"+result);
		// Model �� View ����
		

		Map<String, Object> Ain = new HashMap<String, Object>();
		
		Ain.put("result", new Boolean(result));
		Ain.put("userId", user.getUserId());

		return Ain; //"forward:/user/checkDuplication.jsp";
	}
}