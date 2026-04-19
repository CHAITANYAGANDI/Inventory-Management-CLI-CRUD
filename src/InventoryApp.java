import java.util.Scanner;

public class InventoryApp {

    public static void menu(){

        System.out.println("1. Add Product");
        System.out.println("2. View All Products");
        System.out.println("3. Search Product by ID");
        System.out.println("4. Update Product Quantity");
        System.out.println("5. Delete Product");
        System.out.println("6. Exit");
    }

    public static Product addProducts(Scanner input){

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
        ProductDAO productdao = new ProductDAO();

        while(true){

            menu();

            int userInput = input.nextInt();

            if(userInput == 1){

                Product newProduct = addProducts(input);

                productdao.addProduct(newProduct);
            }

            else if(userInput == 2){

                productdao.viewAllProducts();
            }

            else if(userInput == 3){

                input.nextLine();

                System.out.println("Enter Product ID");
                int searchProductID = input.nextInt();

                Product foundProduct = productdao.searchProductById(searchProductID);

                if (foundProduct != null) {
                    foundProduct.displayProductInfo();
                } else {
                    System.out.println("Product not found");
                }
            }

            else if(userInput == 4){

                input.nextLine();

                System.out.println("Enter Product ID");
                int updateProductId = input.nextInt();

                System.out.println("Enter Quantity");
                int updateQuantity = input.nextInt();

                productdao.updateProductQuantity(updateProductId,updateQuantity);
            }

            else if(userInput == 5){

                input.nextLine();

                System.out.println("Enter Product ID");
                int deleteProductId = input.nextInt();

                productdao.deleteProduct(deleteProductId);
            }

            else if(userInput == 6){

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
