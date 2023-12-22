import Classes.Subscription;
import Repositories.SubscriptionRepository;

public class test {
    public static void main(String[] args) {
        SubscriptionRepository subscriptionRepository = new SubscriptionRepository();

        Subscription newSubscription = new Subscription(1, 59.99, 2000.00); // Assuming _ID is auto-incremented
        subscriptionRepository.insertSubscription(newSubscription);

//        int id = 0;
//        Subscription result = subscriptionRepository.findSubscriptionById(id);
//        System.out.println(result.toString());
    }
}
