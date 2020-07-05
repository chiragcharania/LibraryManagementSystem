package lms;

import java.sql.*;
import java.util.Scanner;

public class LibraryManagementSystem {
	public static void bookSearch() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", "root", "12345");
			Statement stmt = con.createStatement();
			System.out.println("Enter the name of the book that you want to search: ");
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(System.in);
			String book = sc.nextLine();
			ResultSet rs = stmt.executeQuery("select * from books where title = '" + book + "'");
			String bookName = null;
			while (rs.next()) {
				bookName = rs.getString("title");
			}
			try {
				if (bookName.equalsIgnoreCase(book)) {
					System.out.println("Book is available in the library");
				} else {
					System.out.println("else executed");
				}
			} catch (NullPointerException npe) {
				System.out.println("Book not available");
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void delayStatus() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", "root", "12345");
			Statement stmt = con.createStatement();
			ResultSet rs1 = stmt.executeQuery("select * from history where due_date<curdate();");
			System.out.println("Report of the list with details about the item that are late:");
			System.out.println("---------------------------------------------------------------");
			while (rs1.next()) {
				String due_date = rs1.getString("due_date");
				String item_id = rs1.getString("item_id");
				String item_name = rs1.getString("item_name");
				String author = rs1.getString("author");
				String item_type = rs1.getString("item_type");
				String member_name = rs1.getString("member_name");
				String member_title = rs1.getString("member_title");
				String issue_date = rs1.getString("issue_date");
				System.out.println("Item ID: " + item_id + ", Item Name: " + item_name + ", Author: " + author
						+ ", Item Type: " + item_type + ", Member Name: " + member_name + ", Member Type: "
						+ member_title + ", Issue Date: " + issue_date + " & Due Date: " + due_date);
			}
			System.out.println("---------------------------------------------------------------");
			ResultSet rs2 = stmt.executeQuery("select * from cd_history where due_time<curtime();");
			while (rs2.next()) {
				String due_time = rs2.getString("due_time");
				String item_id = rs2.getString("item_id");
				String item_name = rs2.getString("item_name");
				String course = rs2.getString("course");
				String member_name = rs2.getString("member_name");
				String member_title = rs2.getString("member_title");
				String issue_time = rs2.getString("issue_time");
				System.out.println("For CDs: ");
				System.out.println("Item ID: " + item_id + ", Item Name: " + item_name + ", Course: " + course
						+ ", Member Name: " + member_name + ", Member Type: " + member_title + ", Issue Time: "
						+ issue_time + " & Due Time: " + due_time);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void popularBooks() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", "root", "12345");
			Statement stmt = con.createStatement();
			ResultSet rs1 = stmt.executeQuery("SELECT count(item_id), item_name, author FROM history "
					+ "WHERE item_type='books' GROUP BY item_id ORDER BY COUNT(item_id) " + "DESC LIMIT 10");
			System.out.println("Top 10 most popular books & authors are: ");
			while (rs1.next()) {
				String count = rs1.getString("count(item_id)");
				String item_name = rs1.getString("item_name");
				String author = rs1.getString("author");
				System.out
						.println("Count: " + count + ". Name of the book: " + item_name + " & the author is " + author);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void popularJournals() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", "root", "12345");
			Statement stmt = con.createStatement();
			ResultSet rs1 = stmt.executeQuery("SELECT count(item_id), item_name, author FROM history "
					+ "WHERE item_type='journal' GROUP BY item_id ORDER BY COUNT(item_id) " + "DESC LIMIT 5");
			System.out.println("Top 5 most popular journals are: ");
			while (rs1.next()) {
				String count = rs1.getString("count(item_id)");
				String item_name = rs1.getString("item_name");
				String author = rs1.getString("author");
				System.out.println(
						"Count: " + count + ". Name of the journal: " + item_name + " & the author is " + author);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void summary() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", "root", "12345");
			Statement stmt = con.createStatement();
			int count1 = 0, count2 = 0, count3 = 0, count4 = 0, count5 = 0, count6 = 0, count7 = 0;
			ResultSet rs1 = stmt.executeQuery("select * from books");
			while (rs1.next()) {
				count1 = rs1.getRow();
			}
			ResultSet rs2 = stmt.executeQuery("select * from cd");
			while (rs2.next()) {
				count2 = rs2.getRow();
			}
			ResultSet rs3 = stmt.executeQuery("select * from conference");
			while (rs3.next()) {
				count3 = rs3.getRow();
			}
			ResultSet rs4 = stmt.executeQuery("select * from journals");
			while (rs4.next()) {
				count4 = rs4.getRow();
			}
			ResultSet rs5 = stmt.executeQuery("select * from members");
			while (rs5.next()) {
				count5 = rs5.getRow();
			}
			ResultSet rs6 = stmt.executeQuery("select * from referencebook");
			while (rs6.next()) {
				count6 = rs6.getRow();
			}
			ResultSet rs7 = stmt.executeQuery("select * from history");
			while (rs7.next()) {
				count7 = rs7.getRow();
			}
			System.out.println("****************************SUMMARY*****************************");
			System.out.println("Total books: " + count1);
			System.out.println("Total CDs: " + count2);
			System.out.println("Total Conference Proceedings: " + count3);
			System.out.println("Total Journals: " + count4);
			System.out.println("Total Members: " + count5);
			System.out.println("Total Reference Books: " + count6);
			int total = count1 + count2 + count3 + count4 + count5 + count6;
			System.out.println("----------------------------------------------------------------");
			System.out.println("Total items originally present in the library are: " + total);
			System.out.println("Total items rented out from the library are: " + count7);
			System.out.println("----------------------------------------------------------------");
			System.out.println("Total items currently present in the library are: " + (total - count7));
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@SuppressWarnings("resource")
	public static void main(String args[]) {
		System.out.println("LIBRARY MANAGEMENT SYSTEM");
		while (true) {
			System.out.println("----------------------------------------------------------------");
			System.out.println("1. Search for a book");
			System.out.println("2. List of delayed books");
			System.out.println("3. Top 10 popular books & authors");
			System.out.println("4. Top 5 most read journals");
			System.out.println("5. Summary");
			System.out.println("6. Exit");
			System.out.println("Enter your choice: ");
			Scanner sc = new Scanner(System.in);
			int choice = sc.nextInt();
			if (choice == 1) {
				bookSearch();
			} else if (choice == 2) {
				delayStatus();
			} else if (choice == 3) {
				popularBooks();
			} else if (choice == 4) {
				popularJournals();
			} else if (choice == 5) {
				summary();
			} else if (choice == 6) {
				System.out.println("Thank you!");
				break;
			} else {
				System.out.println("Enter correct choice!");
			}
		}
	}
}
