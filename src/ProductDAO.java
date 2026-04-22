import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDAO {

    public void addProduct(Product product){

        Product existingProduct = searchProductById(product.getProductId());

        if(existingProduct!=null){

            System.out.println("Product ID already exists");
            return;
        }

        String query = "INSERT INTO products (product_id,product_name,quantity,price,category_id) VALUES (?,?,?,?,?)";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement statement = conn.prepareStatement(query);){

            statement.setInt(1,product.getProductId());
            statement.setString(2,product.getProductName());
            statement.setInt(3,product.getQuantity());
            statement.setDouble(4,product.getPrice());
            statement.setInt(5,product.getCategoryId());

            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0){
                System.out.println("Product added successfully");
            }
        }

        catch(Exception e){

            System.out.println(e.getMessage());
        }

    }

    public void viewAllProducts(){

        String query = "SELECT * FROM products";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet result = statement.executeQuery()){

            while (result.next()) {
                Product product = new Product(
                        result.getInt("product_id"),
                        result.getString("product_name"),
                        result.getInt("quantity"),
                        result.getDouble("price"),
                        result.getInt("category_id")
                );

                product.displayProductInfo();
                System.out.println("--------------------");
            }
        }

        catch(SQLException e){

            System.out.println(e.getMessage());
        }
    }

    public Product searchProductById(int productId){

        String query = "SELECT * FROM products WHERE product_id = ?";

        try(Connection conn = DBConnection.getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        ){

            statement.setInt(1,productId);

            try(ResultSet result = statement.executeQuery();){

                if (result.next()) {
                    return new Product(
                            result.getInt("product_id"),
                            result.getString("product_name"),
                            result.getInt("quantity"),
                            result.getDouble("price"),
                            result.getInt("category_id")
                    );
                }
            }

        }

        catch(SQLException e){

            System.out.println(e.getMessage());
        }

        return null;
    }

    public void searchProductByName(String name){

        String query = "SELECT * FROM products where product_name = ?";
        boolean found = false;

        try(Connection conn = DBConnection.getConnection();
        PreparedStatement statement = conn.prepareStatement(query)){

            statement.setString(1,name);

            try(ResultSet result = statement.executeQuery()){

                while(result.next()){

                    Product product = new Product(
                            result.getInt("product_id"),
                            result.getString("product_name"),
                            result.getInt("quantity"),
                            result.getDouble("price"),
                            result.getInt("category_id")
                    );

                    product.displayProductInfo();
                    System.out.println();
                    found = true;
                }

                if(!found){

                    System.out.println("no products found");
                }
            }

        }

        catch(SQLException e){

            System.out.println(e.getMessage());
        }
    }

    public void updateProductQuantity(int productId, int newQuantity){

        String query = "UPDATE products SET quantity = ? WHERE product_id = ?";

        try(Connection conn = DBConnection.getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        ){

            statement.setInt(1,newQuantity);
            statement.setInt(2,productId);

            int result = statement.executeUpdate();
            if(result>0){

                System.out.println("product updated successfully");
            }
            else{

                System.out.println("Product not found");
            }
        }

        catch(SQLException e){

            System.out.println(e.getMessage());
        }
    }

    public void viewLowStockProducts(int threshold){

        String query = "SELECT * FROM products WHERE quantity < ?";

        try(Connection conn = DBConnection.getConnection();
        PreparedStatement statement = conn.prepareStatement(query);) {

            statement.setInt(1, threshold);

            try (ResultSet result = statement.executeQuery();) {
                boolean found = false;

                while (result.next()) {
                    Product productResults =  new Product(
                            result.getInt("product_id"),
                            result.getString("product_name"),
                            result.getInt("quantity"),
                            result.getDouble("price"),
                            result.getInt("category_id")
                    );

                    productResults.displayProductInfo();
                    System.out.println();
                    found = true;
                }

                if(!found){
                    System.out.println("No low stock products found");
                }
            }

        }

        catch(SQLException e){

            System.out.println(e.getMessage());
        }
    }

    public void viewProductsByCategory(int category_id){

        String query = "SELECT * FROM products WHERE category_id = ?";
        boolean found = false;

        try(Connection conn  = DBConnection.getConnection();
        PreparedStatement statement = conn.prepareStatement(query);){

            statement.setInt(1,category_id);

            try(ResultSet result = statement.executeQuery()){

                while(result.next()){

                    Product productResults =  new Product(
                            result.getInt("product_id"),
                            result.getString("product_name"),
                            result.getInt("quantity"),
                            result.getDouble("price"),
                            result.getInt("category_id")
                    );

                    productResults.displayProductInfo();
                    System.out.println();
                    found = true;

                }

                if(!found){
                    System.out.println("No products found in this category");
                }
            }
        }

        catch (SQLException e){

            System.out.println(e.getMessage());
        }
    }

    public void viewProductsWithCategoryName(String categoryName){

        String query =
                """
                SELECT p.product_id, p.product_name, p.quantity, p.price,
                c.category_name FROM products p JOIN categories c
                ON p.category_id = c.category_id where c.category_name = ? 
                """;
        boolean found = false;

        try(Connection conn = DBConnection.getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        ){
            statement.setString(1,categoryName);
            try(ResultSet result = statement.executeQuery();){

                while(result.next()){

                    Product productResults =  new Product(
                            result.getInt("product_id"),
                            result.getString("product_name"),
                            result.getInt("quantity"),
                            result.getDouble("price"),
                            result.getInt("category_id")
                    );

                    productResults.displayProductInfo();
                    System.out.println();
                    found = true;

                }

                if(!found){
                    System.out.println("No products found");
                }
            }
        }

        catch (SQLException e){

            System.out.println(e.getMessage());
        }



    }

    public void deleteProduct(int productId){

        String query = "DELETE FROM products WHERE product_id = ?";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement statement = conn.prepareStatement(query)){

            statement.setInt(1,productId);

            int result = statement.executeUpdate();

            if(result>0){

                System.out.println("Product successfully deleted");
            }
            else{
                System.out.println("Product not found");
            }
        }

        catch (SQLException e){

            System.out.println(e.getMessage());
        }

    }
}
