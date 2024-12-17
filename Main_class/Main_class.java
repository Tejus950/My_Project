package com.hibernate.main;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.hibernate.entity.Users;
import com.hibernate.service.CategoriesService;
import com.hibernate.service.MenuItemsService;
import com.hibernate.service.OrderItemsService;
import com.hibernate.service.OrdersService;
import com.hibernate.service.PaymentsService;
import com.hibernate.service.RestaurantsService;
import com.hibernate.service.ReviewsService;
import com.hibernate.service.UsersService;
import com.hibernate.serviceImpl.CategoriesServiceImpl;
import com.hibernate.serviceImpl.MenuItemsServiceImpl;
import com.hibernate.serviceImpl.OrderItemsServiceImpl;
import com.hibernate.serviceImpl.OrdersServiceImpl;
import com.hibernate.serviceImpl.PaymentsServiceImpl;
import com.hibernate.serviceImpl.RestaurantsServiceImpl;
import com.hibernate.serviceImpl.ReviewsServiceImpl;
import com.hibernate.serviceImpl.UsersServiceImpl;
import com.hibernate.utility.ConfigurationUtility;

public class Main_class {

		public static void main(String[] args) {

			SessionFactory getsFactory = ConfigurationUtility.getsFactory();

			// Session openSession = getsFactory.openSession();

			// Transaction transaction = openSession.beginTransaction();

			Scanner sc = new Scanner(System.in);

			UsersService userService = new UsersServiceImpl();
			RestaurantsService restaurantService = new RestaurantsServiceImpl();
			MenuItemsService menuItemsService = new MenuItemsServiceImpl();
			OrdersService ordersService = new OrdersServiceImpl();
			OrderItemsService orderItemsService = new OrderItemsServiceImpl();
			PaymentsService paymentsService = new PaymentsServiceImpl();
			ReviewsService reviewsService = new ReviewsServiceImpl();
			CategoriesService categoriesService = new CategoriesServiceImpl();

			while (true) {
				System.out.println("Welcome to Online Food Ordering :");
				System.out.println("1. Manage Users");
				System.out.println("2. Manage Restaurants");
				System.out.println("3. Manage Menu Items");
				System.out.println("4. Manage Orders");
				System.out.println("5. Manage Order Items");
				System.out.println("6. Manage Payments");
				System.out.println("7. Manage Reviews");
				System.out.println("8. Manage Categories");
				System.out.println("9. Exit");
				System.out.print("Choose an option: ");

				int choice = sc.nextInt();

				Session openSession = getsFactory.openSession();

				Transaction transaction = openSession.beginTransaction();

				switch (choice) {

				case 1:
					manageUsers(getsFactory, userService);
					break;
				case 2:
					manageRestaurants(getsFactory, restaurantService);
					break;
				case 3:
					manageMenuItems(getsFactory, menuItemsService, categoriesService);
					break;
				case 4:
					manageOrders(getsFactory, ordersService);
					break;
				case 5:
					manageOrderItems(getsFactory, orderItemsService);
					break;
				case 6:
					managePayments(getsFactory, paymentsService);
					break;
				case 7:
					manageReviews(getsFactory, reviewsService);
					break;
				case 8:
					manageCategories(getsFactory, categoriesService);
					break;
				case 9:
					System.out.println("Exiting...");
					transaction.commit();
					openSession.close();
					getsFactory.close();
					sc.close();
					return;
				default:
					System.out.println("Invalid option. Please try again.");
				}

				transaction.commit();
				openSession.close();
			}
		}

		private static void manageUsers(SessionFactory sessionFactory, UsersService userService) {
			Scanner sc = new Scanner(System.in);
			while (true) {
				System.out.println("Manage Users Menu:");
				System.out.println("1. Enter User Details");
				System.out.println("2. Update User");
				System.out.println("3. Delete User");
				System.out.println("4. Get All Users");
				System.out.println("5. Get User by ID");
				System.out.println("6. Login");
				System.out.println("7. Exit User Management");
				System.out.print("Choose an option: ");

				int choice = sc.nextInt();
				sc.nextLine(); // Consume newline

				switch (choice) {
				case 1:
					userService.insertUser(sessionFactory);
					break;
				case 2:
					System.out.print("Enter User ID to update: ");
					int userIdToUpdate = sc.nextInt();
					userService.updateUser(sessionFactory, userIdToUpdate);
					break;
				case 3:
					System.out.print("Enter User ID to delete: ");
					int userIdToDelete = sc.nextInt();
					userService.deleteUser(sessionFactory, userIdToDelete);
					break;
				case 4:
					userService.getAllUser(sessionFactory);
					break;
				case 5:
					System.out.print("Enter User ID to get: ");
					int userIdToGet = sc.nextInt();
					userService.getUserById(sessionFactory, userIdToGet);
					break;
				case 6:
					System.out.print("Enter email: ");
					String email = sc.next();
					System.out.print("Enter password: ");
					String password = sc.next();
					Users loggedInUser = userService.login(sessionFactory, email, password);
					if (loggedInUser != null) {
						System.out.println("Login successful: " + loggedInUser);
					} else {
						System.out.println("Invalid email or password.");
					}
					break;
				case 7:
					return; // Exit user management
				default:
					System.out.println("Invalid option. Please try again.");
				}
			}
		}

		private static void manageRestaurants(SessionFactory sessionFactory, RestaurantsService restaurantService) {
			Scanner sc = new Scanner(System.in);
			while (true) {
				System.out.println("Manage Restaurants Menu:");
				System.out.println("1. Insert Restaurant");
				System.out.println("2. Update Restaurant");
				System.out.println("3. Delete Restaurant");
				System.out.println("4. Get All Restaurants");
				System.out.println("5. Get Restaurant by ID");
				System.out.println("6. Exit Restaurant Management");
				System.out.print("Choose an option: ");

				int choice = sc.nextInt();
				sc.nextLine(); // Consume newline

				switch (choice) {
				case 1:
					restaurantService.insertRestaurant(sessionFactory);
					break;
				case 2:
					System.out.print("Enter Restaurant ID to update: ");
					int restaurantIdToUpdate = sc.nextInt();
					restaurantService.updateRestaurant(sessionFactory, restaurantIdToUpdate);
					break;
				case 3:
					System.out.print("Enter Restaurant ID to delete: ");
					int restaurantIdToDelete = sc.nextInt();
					restaurantService.deleteRestaurant(sessionFactory, restaurantIdToDelete);
					break;
				case 4:
					restaurantService.getAllRestaurants(sessionFactory);
					break;
				case 5:
					System.out.print("Enter Restaurant ID to get: ");
					int restaurantIdToGet = sc.nextInt();
					restaurantService.getRestaurantById(sessionFactory, restaurantIdToGet);
					break;
				case 6:
					return; // Exit restaurant management
				default:
					System.out.println("Invalid option. Please try again.");
				}
			}
		}

		private static void manageMenuItems(SessionFactory sessionFactory, MenuItemsService menuItemsService,
				CategoriesService categoriesService) {
			Scanner sc = new Scanner(System.in);
			while (true) {
				System.out.println("Manage Menu Items Menu:");
				System.out.println("1. Insert Menu Item");
				System.out.println("2. Update Menu Item");
				System.out.println("3. Delete Menu Item");
				System.out.println("4. Get All Menu Items");
				System.out.println("5. Get Menu Item by ID");
				System.out.println("6. Exit Menu Management");
				System.out.print("Choose an option: ");

				int choice = sc.nextInt();
				sc.nextLine(); // Consume newline

				switch (choice) {
				case 1:
					categoriesService.getAllCategories(sessionFactory); // Show available categories
					menuItemsService.insertMenuItem(sessionFactory);
					break;
				case 2:
					System.out.print("Enter Menu Item ID to update: ");
					int itemIdToUpdate = sc.nextInt();
					menuItemsService.updateMenuItem(sessionFactory, itemIdToUpdate);
					break;
				case 3:
					System.out.print("Enter Menu Item ID to delete: ");
					int itemIdToDelete = sc.nextInt();
					menuItemsService.deleteMenuItem(sessionFactory, itemIdToDelete);
					break;
				case 4:
					menuItemsService.getAllMenuItems(sessionFactory);
					break;
				case 5:
					System.out.print("Enter Menu Item ID to get: ");
					int itemIdToGet = sc.nextInt();
					menuItemsService.getMenuItemById(sessionFactory, itemIdToGet);
					break;
				case 6:
					return; // Exit menu management
				default:
					System.out.println("Invalid option. Please try again.");
				}
			}
		}

		private static void manageOrders(SessionFactory sessionFactory, OrdersService ordersService) {
			Scanner sc = new Scanner(System.in);
			while (true) {
				System.out.println("Manage Orders Menu:");
				System.out.println("1. Insert Order");
				System.out.println("2. Update Order");
				System.out.println("3. Delete Order");
				System.out.println("4. Get All Orders");
				System.out.println("5. Get Order by ID");
				System.out.println("6. Exit Order Management");
				System.out.print("Choose an option: ");

				int choice = sc.nextInt();
				sc.nextLine(); // Consume newline

				switch (choice) {
				case 1:
					ordersService.insertOrder(sessionFactory);
					break;
				case 2:
					System.out.print("Enter Order ID to update: ");
					int orderIdToUpdate = sc.nextInt();
					ordersService.updateOrder(sessionFactory, orderIdToUpdate);
					break;
				case 3:
					System.out.print("Enter Order ID to delete: ");
					int orderIdToDelete = sc.nextInt();
					ordersService.deleteOrder(sessionFactory, orderIdToDelete);
					break;
				case 4:
					ordersService.getAllOrders(sessionFactory);
					break;
				case 5:
					System.out.print("Enter Order ID to get: ");
					int orderIdToGet = sc.nextInt();
					ordersService.getOrderById(sessionFactory, orderIdToGet);
					break;
				case 6:
					return; // Exit order management
				default:
					System.out.println("Invalid option. Please try again.");
				}
			}
		}

		private static void manageOrderItems(SessionFactory sessionFactory, OrderItemsService orderItemsService) {
			Scanner sc = new Scanner(System.in);
			while (true) {
				System.out.println("Manage Order Items Menu:");
				System.out.println("1. Insert Order Item");
				System.out.println("2. Update Order Item");
				System.out.println("3. Delete Order Item");
				System.out.println("4. Get All Order Items");
				System.out.println("5. Get Order Item by ID");
				System.out.println("6. Exit Order Items Management");
				System.out.print("Choose an option: ");

				int choice = sc.nextInt();
				sc.nextLine(); // Consume newline

				switch (choice) {
				case 1:
					orderItemsService.insertOrderItem(sessionFactory);
					break;
				case 2:
					System.out.print("Enter Order Item ID to update: ");
					int orderItemIdToUpdate = sc.nextInt();
					orderItemsService.updateOrderItem(sessionFactory, orderItemIdToUpdate);
					break;
				case 3:
					System.out.print("Enter Order Item ID to delete: ");
					int orderItemIdToDelete = sc.nextInt();
					orderItemsService.deleteOrderItem(sessionFactory, orderItemIdToDelete);
					break;
				case 4:
					orderItemsService.getAllOrderItems(sessionFactory);
					break;
				case 5:
					System.out.print("Enter Order Item ID to get: ");
					int orderItemIdToGet = sc.nextInt();
					orderItemsService.getOrderItemById(sessionFactory, orderItemIdToGet);
					break;
				case 6:
					return; // Exit order items management
				default:
					System.out.println("Invalid option. Please try again.");
				}
			}
		}

		private static void managePayments(SessionFactory sessionFactory, PaymentsService paymentsService) {
			Scanner sc = new Scanner(System.in);
			while (true) {
				System.out.println("Manage Payments Menu:");
				System.out.println("1. Insert Payment");
				System.out.println("2. Update Payment");
				System.out.println("3. Delete Payment");
				System.out.println("4. Get All Payments");
				System.out.println("5. Get Payment by ID");
				System.out.println("6. Exit Payments Management");
				System.out.print("Choose an option: ");

				int choice = sc.nextInt();
				sc.nextLine(); // Consume newline

				switch (choice) {
				case 1:
					paymentsService.insertPayment(sessionFactory);
					break;
				case 2:
					System.out.print("Enter Payment ID to update: ");
					int paymentIdToUpdate = sc.nextInt();
					paymentsService.updatePayment(sessionFactory, paymentIdToUpdate);
					break;
				case 3:
					System.out.print("Enter Payment ID to delete: ");
					int paymentIdToDelete = sc.nextInt();
					paymentsService.deletePayment(sessionFactory, paymentIdToDelete);
					break;
				case 4:
					paymentsService.getAllPayment(sessionFactory);
					break;
				case 5:
					System.out.print("Enter Payment ID to get: ");
					int paymentIdToGet = sc.nextInt();
					paymentsService.getPaymentById(sessionFactory, paymentIdToGet);
					break;
				case 6:
					return; // Exit payments management
				default:
					System.out.println("Invalid option. Please try again.");
				}
			}
		}

		private static void manageReviews(SessionFactory sessionFactory, ReviewsService reviewsService) {

			Scanner sc = new Scanner(System.in);
			while (true) {
				System.out.println("Manage Reviews Menu:");
				System.out.println("1. Insert Review");
				System.out.println("2. Update Review");
				System.out.println("3. Delete Review");
				System.out.println("4. Get All Reviews");
				System.out.println("5. Get Review by ID");
				System.out.println("6. Exit Review Management");
				System.out.print("Choose an option: ");

				int choice = sc.nextInt();
				sc.nextLine(); // Consume newline

				switch (choice) {
				case 1:
					reviewsService.insertReview(sessionFactory);
					break;
				case 2:
					System.out.print("Enter Review ID to update: ");
					int reviewIdToUpdate = sc.nextInt();
					reviewsService.updateReview(sessionFactory, reviewIdToUpdate);
					break;
				case 3:
					System.out.print("Enter Review ID to delete: ");
					int reviewIdToDelete = sc.nextInt();
					reviewsService.deleteReview(sessionFactory, reviewIdToDelete);
					break;
				case 4:
					reviewsService.getAllReviews(sessionFactory);
					break;
				case 5:
					System.out.print("Enter Review ID to get: ");
					int reviewIdToGet = sc.nextInt();
					reviewsService.getReviewById(sessionFactory, reviewIdToGet);
					break;
				case 6:
					return; // Exit review management
				default:
					System.out.println("Invalid option. Please try again.");
				}
			}
		}

		private static void manageCategories(SessionFactory sessionFactory, CategoriesService categoriesService) {
			Scanner sc = new Scanner(System.in);
			while (true) {
				System.out.println("Manage Categories Menu:");
				System.out.println("1. Insert Category");
				System.out.println("2. Update Category");
				System.out.println("3. Delete Category");
				System.out.println("4. Get All Categories");
				System.out.println("5. Get Category by ID");
				System.out.println("6. Exit Category Management");
				System.out.print("Choose an option: ");

				int choice = sc.nextInt();
				sc.nextLine(); // Consume newline

				switch (choice) {
				case 1:
					categoriesService.insertCategory(sessionFactory);
					break;
				case 2:
					System.out.print("Enter Category ID to update: ");
					int categoryIdToUpdate = sc.nextInt();
					categoriesService.updateCategory(sessionFactory, categoryIdToUpdate);
					break;
				case 3:
					System.out.print("Enter Category ID to delete: ");
					int categoryIdToDelete = sc.nextInt();
					categoriesService.deleteCategory(sessionFactory, categoryIdToDelete);
					break;
				case 4:
					categoriesService.getAllCategories(sessionFactory);
					break;
				case 5:
					System.out.print("Enter Category ID to get: ");
					int categoryIdToGet = sc.nextInt();
					categoriesService.getCategoryById(sessionFactory, categoryIdToGet);
					break;
				case 6:
					return; // Exit category management
				default:
					System.out.println("Invalid option. Please try again.");
				}
			}
		}
	}