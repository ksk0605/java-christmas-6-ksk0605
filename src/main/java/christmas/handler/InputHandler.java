package christmas.handler;

import java.util.function.Supplier;

public class InputHandler {
    public static <T> T createWithRetry(Supplier<T> creator) {
        while (true) {
            try {
                return creator.get();
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }
}
