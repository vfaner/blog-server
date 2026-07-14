import com.rgh.GoodsItem;

public class ShoppingCart {
    private GoodsItem[] cart;

    public ShoppingCart() {
        // 初始时，数组有3个商品信息（自己虚拟数据）
        cart = new GoodsItem[3];
        cart[0] = new GoodsItem("001", "可乐", 2.5f, 2);
        cart[1] = new GoodsItem("002", "饼干", 3.0f, 3);
        cart[2] = new GoodsItem("003", "洗发水", 25.0f, 1);
    }

    public float settleAccounts() {
        float totalPrice = 0.0f;
        for (GoodsItem item : cart) {
            totalPrice += item.getTotalPrice();
        }
        return totalPrice;
    }

    public void printInfo() {
        for (GoodsItem item : cart) {
            System.out.println(item);
        }
    }

    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();
        cart.printInfo();
        System.out.println("商品总金额：" + cart.settleAccounts());
    }
}
