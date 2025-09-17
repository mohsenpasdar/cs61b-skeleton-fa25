public class StackClient {
    public static Stack flipped(Stack s) {
        Stack output = new Stack();
        while (s.size() > 0) {
            output.push(s.pop());
        }
        return output;
    }
}
