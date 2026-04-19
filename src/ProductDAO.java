import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDAO {

    public void addProduct(Product product){

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

    public void updateProductQuantity(int productId, int newQuantity){

        String query = "UPDATE products SET quantity = ? WHERE product_id = ?";

        try(Connection conn = DBConnection.getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        ){

            statement.setInt(1,productId);
            statement.setInt(2,newQuantity);

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
