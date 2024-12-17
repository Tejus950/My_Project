package com.hibernate.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.hibernate.entity.Users;
import com.hibernate.service.UsersService;

public class UsersServiceImpl implements UsersService {

	Scanner sc = new Scanner(System.in);
	Session session;
	
		@Override
		public void insertUser(SessionFactory sf) {
			
			session = sf.openSession();
			Transaction transaction = session.beginTransaction();
			
			Users user = new Users();
			
			System.out.println("Enter User details :");
			/* System.out.println("Enter User ID :");
			int num = sc.nextInt();
			user.setUserID(num); */
			
			System.out.println("Enter User Name :");
			String name = sc.next();
			user.setName(name);
			
			System.out.println("Enter User Email :");
			String email = sc.next();
			user.setEmail(email);
			
			System.out.println("Enter User PhoneNumber :");
			String phnnum = sc.next();
			user.setPhoneNumber(phnnum);
			
			System.out.println("Enter User Password :");
			String pswrd = sc.next();
			user.setPassword(pswrd);
			
			System.out.println("Enter User Address :");
			String address = sc.next();
			user.setAddress(address);
			
			session.persist(user);
			transaction.commit();
			session.close();
		}
		
		@Override
		public Users login(SessionFactory sf, String email, String password) {
			
			session = sf.openSession();
	        Users user = null;
	        try {
	            Query<Users> query = session.createQuery("FROM Users WHERE Email = :email AND Password = :password", Users.class);
	            query.setParameter("email", email);
	            query.setParameter("password", password);
	            user = query.uniqueResult();
	        } catch (Exception e) {
	            System.out.println("An error occurred while logging in: " + e.getMessage());
	        } finally {
	            session.close();
	        }
	        return user; // Returns null if not found
		}

		@Override
		public void updateUser(SessionFactory sf, Integer userId) {
			
			session = sf.openSession();
			Transaction transaction = session.beginTransaction();
			
			try {
				while (true) {
					System.out.println(
							"Choose option for update \n1.Update Name\n2.Update Email\n3.Update PhoneNumber\n4.Password\n5.Address\n6.Exit");

			  int option = sc.nextInt();
			  sc.nextLine(); 

			 Users user = session.get(Users.class, userId);
			 
			  if (user == null)
			  {
			  System.out.println("User not found with ID: " + userId);
			  break; // Exit if user not found
		      }

		   switch (option)
		   {
		   
           case 1:
               System.out.println("Current Name: " + user.getName());
               System.out.print("Enter new User Name (or press Enter to keep current): ");
               String name = sc.nextLine().trim();
               if (!name.isEmpty()) {
                   user.setName(name);
               }
               break;

           case 2:
               System.out.println("Current Email: " + user.getEmail());
               System.out.print("Enter new User Email (or press Enter to keep current): ");
               String email = sc.nextLine().trim();
               if (!email.isEmpty()) {
                   user.setEmail(email);
               }
               break;

           case 3:
               System.out.println("Current Phone Number: " + user.getPhoneNumber());
               System.out.print("Enter new User Phone Number (or press Enter to keep current): ");
               String phoneNumber = sc.nextLine().trim();
               if (!phoneNumber.isEmpty()) {
                   user.setPhoneNumber(phoneNumber);
               }
               break;

           case 4:
               System.out.println("Current Address: " + user.getAddress());
               System.out.print("Enter new User Address (or press Enter to keep current): ");
               String address = sc.nextLine().trim();
               if (!address.isEmpty()) {
                   user.setAddress(address);
               }
               break;

           case 5:
               System.out.print("Enter new User Password (or press Enter to keep current): ");
               String password = sc.nextLine().trim();
               if (!password.isEmpty()) {
                   user.setPassword(password);
               }
               break;

           case 6:
               System.out.println("Exiting from update user");
               transaction.commit(); // Commit the transaction before exiting
               return; // Exit the method

           default:
               System.out.println("Invalid option. Please choose again.");
               continue; // Continue the loop for valid input
       }
       
       // Optionally, you can commit here if you want to save changes after each update
       // However, it's better to commit after exiting the loop to ensure all updates are batched
       // session.saveOrUpdate(user); // This line is not needed if you commit later
   }

} catch (Exception e) {
   if (transaction != null) {
       transaction.rollback(); // Rollback on error
   }
   System.out.println("An error occurred while updating the user: " + e.getMessage());
} finally {
   session.close(); // Ensure the session is closed
}
}
		@Override
		public void deleteUser(SessionFactory sf, Integer userId) {

			    Session session = sf.openSession();
			    Transaction transaction = null;

			    try {
			        transaction = session.beginTransaction();
			        
			      //  System.out.print("Enter User ID to delete: ");
			      //  int UserID = sc.nextInt();
			     //   sc.nextLine(); // Consume the newline

			        // Fetch the user by ID
			        Users user = session.get(Users.class, userId);
			        if (user != null) {
			            session.delete(user); // Delete the user
			            System.out.println("User with ID " + userId + " has been deleted successfully.");
			        } else {
			            System.out.println("User not found with ID: " + userId);
			        }

			        transaction.commit(); // Commit the transaction
			    } catch (Exception e) {
			        if (transaction != null) {
			            transaction.rollback(); // Rollback on error
			        }
			        System.out.println("An error occurred while deleting the user: " + e.getMessage());
			    } finally {
			        session.close(); // Ensure the session is closed
			    }
			}

		@Override
		public Users getUserById(SessionFactory sf, Integer userId) {
			
			    Session session = sf.openSession();
			    Users user = null; // Initialize user to null

			    try {
			        user = session.get(Users.class, userId);
			        if (user != null) {
			            System.out.println("User found: " + user); // Display user details
			        } else {
			            System.out.println("User not found with ID: " + userId);
			        }
			    } catch (Exception e) {
			        System.out.println("An error occurred while retrieving the user: " + e.getMessage());
			    } finally {
			        session.close(); // Ensure the session is closed
			    }

			    return user; // Return the user object (null if not found)
			}

		@Override
		public List<Users> getAllUser(SessionFactory sf) {
			
			    Session session = sf.openSession();
			    List<Users> userList = new ArrayList<>(); // Initialize an empty list

			    try {
			        Query<Users> query = session.createQuery("from Users", Users.class); // Create query to fetch all users
			        userList = query.getResultList(); // Get the result list

			        // Print each user
			        for (Users user : userList) {
			            System.out.println(user);
			        }
			    } catch (Exception e) {
			        System.out.println("An error occurred while retrieving users: " + e.getMessage());
			    } finally {
			        session.close(); // Ensure the session is closed
			    }

			    return userList; // Return the list of users
			}
			
}