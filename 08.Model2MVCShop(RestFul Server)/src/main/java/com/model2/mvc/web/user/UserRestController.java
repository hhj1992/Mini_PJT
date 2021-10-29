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

		return userService.getUser(user.getUserId());
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
	public Map<String, Object> listUser(@ModelAttribute Search search) throws Exception {

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
}