public class Product {

    private int productId;
    private String productName;
    private int quantity;
    private double price;
    private int categoryId;

    Product(int productId,String productName,int quantity,double price, int categoryId){

        setProductId(productId);
        setProductName(productName);
        setQuantity(quantity);
        setPrice(price);
        setCategoryId(categoryId);
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        if (productId > 0) {

            this.productId = productId;
        }
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {

        if(productName!=null && !(productName.trim().isEmpty())){

            this.productName = productName;
        }
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if(quantity>=0){

            this.quantity = quantity;
        }
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {

        if(price>=0.0){

            this.price = price;
        }
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {

        if(categoryId>0){

            this.categoryId = categoryId;
        }
    }

    public void displayProductInfo(){

        System.out.println("Product ID: "+getProductId());
        System.out.println("Product Name: "+getProductName());
        System.out.println("Product Quantity: "+getQuantity());
        System.out.println("Product Price: "+getPrice());
        System.out.println("Category ID: "+getCategoryId());
    }
}
