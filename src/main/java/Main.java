import entity.*;
import service.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ShoppingCardService shoppingCardService = new ShoppingCardService();
        CustomerService customerService = new CustomerService();
        CategoryService categoryService = new CategoryService();
        ProductService productService = new ProductService();
        AdminService adminService = new AdminService();
        OrderService orderService = new OrderService();
        Scanner scanner = new Scanner(System.in);
        ShoppingCard shoppingCard;
        String description;
        Category category ;
        Product product;
        String title;
        Admin admin;
        String name;
        int order1;
        int qtyBeforeBuy;
        int number;
        int price;
        int role;
        int qty;
        int id = 0;
        Customer customer;
        String username;
        String password;
        String address;
        String nationalCode;
        Date time = Date.valueOf(LocalDate.now());


        System.out.println("1.Login \n2.Signup");
        order1 = scanner.nextInt();
        System.out.println("What are you? \n1.Customer \n2.Admin ");
        role = scanner.nextInt();
        scanner.nextLine();
        if (order1 == 1 && role == 1) {
            System.out.println("Enter your username:");
            username = scanner.nextLine();
            System.out.println("Enter your password: ");
            password = scanner.nextLine();
            customer = customerService.login(username, password);
            if (customer != null) {
                System.out.println("1.show all products \n2.show product by Id \n3.show all categories" +
                        "\n4.show product by category Id \n5.show your orders \n6.buy product \n7.Exit ");
                switch (scanner.nextInt()) {
                    case 1:
                        productService.findAll();
                        break;

                    case 2:
                        System.out.println("Enter product's Id: ");
                        System.out.println(productService.findById(scanner.nextInt()).toString());
                        break;

                    case 3:
                        categoryService.findAll();
                        break;

                    case 4:
                        System.out.println("Enter category id: ");
                        System.out.println(categoryService.findById(scanner.nextInt()));
                        break;

                    case 5:
                        customerService.findShoppingCardByUserId(customer.getId());
                        break;

                    case 6:
                        System.out.println("Enter id of product: ");
                        id = scanner.nextInt();
                        System.out.println("Enter your Id: ");
                        customer=new Customer(scanner.nextInt());
                        System.out.println(customer);
                        System.out.println("How many you want? ");
                        number = scanner.nextInt();
                        product = productService.findById(id);
                        if (number < product.getQty()) {
                            qtyBeforeBuy=product.getQty();
                            System.out.println("do you want to pay? \n1.YES  2.NO ");
                            int order2 = scanner.nextInt();
                            time = Date.valueOf(LocalDate.now());
                            if (order2 == 1) {
                                shoppingCard = new ShoppingCard(time, true);
                            } else shoppingCard = new ShoppingCard(time, false);
                            Order order = new Order(product, customer,shoppingCard);
                            orderService.save(order);
                            System.out.println("Done ");
                            product.setQty(qtyBeforeBuy-number);
                            productService.update(product);
                        } else System.out.println("out of qty! ");
                        break;
                    case 7:
                        break;

                }
            }
        }
        if (order1 == 2 && role == 1) {
            System.out.println("enter your username: ");
            username = scanner.nextLine();
            System.out.println("enter your password: ");
            password = scanner.nextLine();
            System.out.println("enter your address: ");
            address = scanner.nextLine();
            customer = new Customer(username, password, address);
            customerService.save(customer);
            System.out.println("Done");
        }
        if (order1 == 2 && role == 2) {
            System.out.println("enter your username: ");
            username = scanner.nextLine();
            System.out.println("enter your password: ");
            password = scanner.nextLine();
            System.out.println("enter your nationalCode: ");
            nationalCode = scanner.nextLine();
            admin = new Admin(username, password, nationalCode);
            adminService.save(admin);
            System.out.println("Done");
        }
        if (order1 == 1 && role == 2) {
            System.out.println("Enter your username:");
            username = scanner.nextLine();
            System.out.println("Enter your password: ");
            password = scanner.nextLine();
            admin = adminService.login(username, password);
            if (admin != null) {
                System.out.println("1.add product \n2.update product by Id \n3.Delete a Product" +
                        "\n4.Add Category \n5.delete category \n6.Exit ");
                switch (scanner.nextInt()) {
                    case 1:
                        scanner.nextLine();
                        System.out.println("enter product's name:");
                        name = scanner.nextLine();
                        System.out.println("enter product's description: ");
                        description = scanner.nextLine();
                        System.out.println("enter category id: ");
                        id = scanner.nextInt();
                        System.out.println("enter quantity of products: ");
                        qty = scanner.nextInt();
                        System.out.println("enter product's price: ");
                        price = scanner.nextInt();
                        category = new Category(id);
                        product = new Product(name, description, category, qty, price);
                        productService.save(product);
                        System.out.println("Done! ");
                        break;
                    case 2:
                        System.out.println("Enter product id: ");
                        product = productService.findById(scanner.nextInt());
                        System.out.println(product);
                        System.out.println("enter product Quantity: ");
                        product.setQty(scanner.nextInt());
                        scanner.nextLine();
                        System.out.println("enter new Description: ");
                        product.setDescription(scanner.nextLine());
                        System.out.println("enter new price: ");
                        product.setPrice(scanner.nextInt());
                        productService.update(product);
                        System.out.println("Done");
                        break;

                    case 3:
                        System.out.println("Enter product id: ");
                        productService.delete(scanner.nextInt());
                        System.out.println("product deleted! ");
                        break;
                    case 4:
                        categoryService.findAll();
                        scanner.nextLine();
                        System.out.println("Enter Category title: ");
                        title = scanner.nextLine();
                        System.out.println("Enter Category description: ");
                        description = scanner.nextLine();
                        System.out.println("Is it sub category? 1.Yes  2.No");
                        if (scanner.nextInt() == 1) {
                            System.out.println("Enter FatherCategory id: ");
                            id = scanner.nextInt();
                        }
                        category = new Category(id,title, description);
                        categoryService.save(category);
                        System.out.println("Done");
                        break;

                    case 5:
                        System.out.println("Enter category id: ");
                        categoryService.delete(scanner.nextInt());
                        System.out.println("Done! ");
                        break;

                    case 6:
                        break;

                }
            }
        }
    }
}

