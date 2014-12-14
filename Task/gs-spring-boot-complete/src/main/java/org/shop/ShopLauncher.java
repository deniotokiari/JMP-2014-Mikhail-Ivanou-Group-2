package org.shop;

import org.shop.api.ItemService;
import org.shop.api.OrderService;
import org.shop.api.ProductService;
import org.shop.api.ProposalService;
import org.shop.api.UserService;
import org.shop.data.Item;
import org.shop.data.Order;
import org.shop.data.Product;
import org.shop.data.Proposal;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * The ShopLauncher class.
 */
public class ShopLauncher {

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"context.xml");
		ProductService productService = context.getBean(ProductService.class);
		OrderService orderService = context.getBean(OrderService.class);
		ItemService itemService = context.getBean(ItemService.class);
		UserService userService = context.getBean(UserService.class);
		ProposalService proposalService = context
				.getBean(ProposalService.class);
		Product galaxy = productService.getProductsByName("Samsung Galaxy Tab")
				.get(0);
		Proposal proposal = proposalService.getProposalsByProduct(galaxy)
				.get(0);
		orderService.createOrder(userService.getUserById((long) 1), proposal);
		for (Order order : orderService.getOrdersByUserId((long) 1)) {
			System.out.println(order);
			for (Item item : itemService.getItemsByOrderId(order.getId())) {
				System.out.println(item);
			}
		}
	}
}
