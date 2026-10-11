public class RazorpayPaymentStrategy implements PaymentStrategy {
    @Override
    public boolean pay(int cost) {
        try {
            Thread.sleep(500);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }
}
