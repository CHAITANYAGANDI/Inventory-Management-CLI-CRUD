import java.util.Scanner;

public class InventoryApp {

    public static void menu(){

        System.out.println("1. Add Product");
        System.out.println("2. View All Products");
        System.out.println("3. Search Product by ID");
        System.out.println("4. Update Product Quantity");
        System.out.println("5. Delete Product");
        System.out.println("6. View Low Stock Products");
        System.out.println("7. Search Product by Name");
        System.out.println("8. View Product by Category ID");
        System.out.println("9. View Product with Category Name");
        System.out.println("10. Exit");
    }

    public static Product addProduct(Scanner input){

        input.nextLine();

        System.out.println("Enter Product ID");
        int productId = input.nextInt();
        input.nextLine();

        System.out.println("Enter Product Name");
        String productName = input.nextLine();

        System.out.println("Enter Product Quantity");
        int productQuantity = input.nextInt();

        System.out.println("Enter Product Price");
        double productPrice = input.nextDouble();

        System.out.println("Enter Category ID");
        int categoryId = input.nextInt();

        return new Product(productId,productName,productQuantity,productPrice,categoryId);
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        ProductDAO productDAO = new ProductDAO();

        while(true){

            menu();

            int userInput = input.nextInt();

            if(userInput == 1){

                Product newProduct = addProduct(input);

                productDAO.addProduct(newProduct);
            }

            else if(userInput == 2){

                productDAO.viewAllProducts();
            }

            else if(userInput == 3){

                System.out.println("Enter Product ID");
                int searchProductID = input.nextInt();

                Product foundProduct = productDAO.searchProductById(searchProductID);

                if (foundProduct != null) {
                    foundProduct.displayProductInfo();
                } else {
                    System.out.println("Product not found");
                }
            }

            else if(userInput == 4){

                System.out.println("Enter Product ID");
                int updateProductId = input.nextInt();

                System.out.println("Enter Quantity");
                int updateQuantity = input.nextInt();

                productDAO.updateProductQuantity(updateProductId,updateQuantity);
            }

            else if(userInput == 5){

                System.out.println("Enter Product ID");
                int deleteProductId = input.nextInt();

                productDAO.deleteProduct(deleteProductId);
            }

            else if(userInput == 6){

                System.out.println("Enter threshold");
                int threshold = input.nextInt();

                productDAO.viewLowStockProducts(threshold);

            }

            else if(userInput == 7){

                input.nextLine();

                System.out.println("Enter product name");
                String productName = input.nextLine();

                productDAO.searchProductByName(productName);
            }

            else if(userInput == 8){

                System.out.println("Enter category ID");
                int categoryID = input.nextInt();

                productDAO.viewProductsByCategory(categoryID);
            }

            else if(userInput == 9){

                input.nextLine();

                System.out.println("Enter category name");
                String categoryName = input.nextLine();

                productDAO.viewProductsWithCategoryName(categoryName);
            }

            else if(userInput == 10){

                System.out.println("Exiting...");
                break;
            }

            else{

                System.out.println("Invalid choice");
            }
        }

        input.close();
    }
}
