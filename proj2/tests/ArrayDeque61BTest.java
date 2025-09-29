import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static com.google.common.truth.Truth.assertThat;

public class ArrayDeque61BTest {
    @Test
    /** In this test, we have three different assert statements that verify that addFirst works correctly. */
    public void addFirstTestBasic() {
        Deque61B<String> ad1 = new ArrayDeque61B<>();

        ad1.addFirst("back"); // after this call we expect: ["back"]
        assertThat(ad1.toList()).containsExactly("back").inOrder();

        ad1.addFirst("middle"); // after this call we expect: ["middle", "back"]
        assertThat(ad1.toList()).containsExactly("middle", "back").inOrder();

        ad1.addFirst("front"); // after this call we expect: ["front", "middle", "back"]
        assertThat(ad1.toList()).containsExactly("front", "middle", "back").inOrder();
    }

    @Test
    /** In this test, we use only one assertThat statement. IMO this test is just as good as addFirstTestBasic.
     *  In other words, the tedious work of adding the extra assertThat statements isn't worth it. */
    public void addLastTestBasic() {
        Deque61B<String> lld1 = new ArrayDeque61B<>();

        lld1.addLast("front"); // after this call we expect: ["front"]
        lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
        lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
        assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();
    }

    @Test
    /** This test performs interspersed addFirst and addLast calls. */
    public void addFirstAndAddLastTest() {
        Deque61B<Integer> lld1 = new ArrayDeque61B<>();

        /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
        lld1.addLast(0);   // [0]
        lld1.addLast(1);   // [0, 1]
        lld1.addFirst(-1); // [-1, 0, 1]
        lld1.addLast(2);   // [-1, 0, 1, 2]
        lld1.addFirst(-2); // [-2, -1, 0, 1, 2]

        assertThat(lld1.toList()).containsExactly(-2, -1, 0, 1, 2).inOrder();
    }

    @Test
    /** This test validates the get method implemented by iteration, index i out of range. */
    public void  testGetOutOfIndex() {
        Deque61B<Integer> lld1 = new ArrayDeque61B<>();
        lld1.addFirst(5);
        lld1.addLast(6);
        assertThat(lld1.get(-1)).isNull();
        assertThat(lld1.get(2)).isNull();
        lld1.addLast(6);
        assertThat(lld1.get(2)).isEqualTo(6);
        assertThat(lld1.get(3)).isNull();

    }

    @Test
    /** This test validates the get method implemented by iteration, index i in range. */
    public void  testGet() {
        Deque61B<Integer> lld1 = new ArrayDeque61B<>();
        lld1.addFirst(5);
        lld1.addLast(6);
        lld1.addFirst(3);
        assertThat(lld1.get(0)).isEqualTo(3);
        assertThat(lld1.get(1)).isEqualTo(5);
        assertThat(lld1.get(2)).isEqualTo(6);
    }

    // Below, you'll write your own tests for LinkedListDeque61B.
    @Test
    /** this test makes assertions about isEmpty method. */
    public void testIsEmpty() {
        Deque61B<Integer> lld1 = new ArrayDeque61B<>();
        assertThat(lld1.isEmpty()).isTrue();
        lld1.addFirst(5);
        assertThat(lld1.isEmpty()).isFalse();
    }

    @Test
    /** this test makes assertion about the size of an empty deque. */
    public void testSizeZero() {
        Deque61B<Integer> lld1 = new ArrayDeque61B<>();
        assertThat(lld1.size()).isEqualTo(0);
    }

    @Test
    /** this test makes assertion about the size of an empty deque. */
    public void testSizeFour() {
        Deque61B<Integer> lld1 = new ArrayDeque61B<>();
        lld1.addFirst(5);
        lld1.addLast(6);
        lld1.addFirst(9);
        lld1.addFirst(0);
        assertThat(lld1.size()).isEqualTo(4);
    }

    @Test
    /** In this test, we test the toList method functionality on a non-empty liat. */
    public void toListNonempty() {
        Deque61B<String> lld1 = new ArrayDeque61B<>();
        lld1.addFirst("1"); // after this call we expect: ["1"]
        lld1.addLast("2"); // after this call we expect: ["1", "2"]
        lld1.addFirst("0"); // after this call we expect: ["0", "1", "2"]

        assertThat(lld1.toList().size()).isEqualTo(3);
        assertThat(lld1.toList()).containsExactly("0", "1", "2").inOrder();
    }

    @Test
    /** In this test, we test the toList method functionality on a empty liat. */
    public void toListEmpty() {
        Deque61B<String> lld1 = new ArrayDeque61B<>();

        assertThat(lld1.toList().size()).isEqualTo(0);
        assertThat(lld1.toList()).isEmpty();
    }

    @Test
    /** In this test, we have three different assert statements that verify that removeFirst works correctly. */
    public void removeFirstTestBasic() {
        Deque61B<String> lld1 = new ArrayDeque61B<>();
        lld1.addFirst("1"); // after this call we expect: ["1"]
        lld1.addLast("2"); // after this call we expect: ["1", "2"]
        lld1.addFirst("0"); // after this call we expect: ["0", "1", "2"]

        String removedItem = lld1.removeFirst(); // after this call we expect: ["1", "2"]
        assertThat(removedItem).isEqualTo("0");
        assertThat(lld1.toList()).containsExactly("1", "2").inOrder();

        removedItem = lld1.removeFirst(); // after this call we expect: ["2"]
        assertThat(removedItem).isEqualTo("1");
        assertThat(lld1.toList()).containsExactly("2").inOrder();

        removedItem = lld1.removeFirst(); // after this call we expect: [""]
        assertThat(removedItem).isEqualTo("2");
        assertThat(lld1.toList()).isEmpty();

        removedItem = lld1.removeFirst(); // after this call we expect: ["2"]
        assertThat(removedItem).isNull();
        assertThat(lld1.toList()).isEmpty();
    }

    @Test
    /** In this test, we have different assert statements that verify that removeLast works correctly. */
    public void removeLastTestBasic() {
        Deque61B<String> lld1 = new ArrayDeque61B<>();

        lld1.addFirst("1"); // after this call we expect: ["1"]
        lld1.addLast("2"); // after this call we expect: ["1", "2"]

        String removedItem = lld1.removeLast(); // after this call we expect: ["1"]
        assertThat(removedItem).isEqualTo("2");
        assertThat(lld1.toList()).containsExactly("1").inOrder();

        removedItem = lld1.removeLast(); // after this call we expect: []
        assertThat(removedItem).isEqualTo("1");
        assertThat(lld1.toList()).isEmpty();

        removedItem = lld1.removeFirst(); // after this call we expect: ["2"]
        assertThat(removedItem).isNull();
        assertThat(lld1.toList()).isEmpty();
    }

    @Test
    /** This test performs interspersed addFirst and addLast calls. */
    public void removeFirstAndRemoveLastTest() {
        Deque61B<Integer> lld1 = new ArrayDeque61B<>();

        lld1.addLast(0);   // [0]
        lld1.addLast(1);   // [0, 1]
        lld1.addFirst(-1); // [-1, 0, 1]

        int removedItem = lld1.removeLast(); // after this call we expect: // [-1, 0]
        assertThat(removedItem).isEqualTo(1);
        assertThat(lld1.toList()).containsExactly(-1, 0).inOrder();

        removedItem = lld1.removeFirst(); // after this call we expect: // [0]
        assertThat(removedItem).isEqualTo(-1);
        assertThat(lld1.toList()).containsExactly(0).inOrder();
    }


    @Test
    /** In this test, we dd some elements to a deque and remove them all, then check that addFirst still works. */
    public void addFirstAfterRemoveToEmpty() {
        Deque61B<String> ad1 = new ArrayDeque61B<>();

        ad1.addLast("front"); // after this call we expect: ["front"]
        ad1.addLast("middle"); // after this call we expect: ["front", "middle"]
        ad1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
        ad1.removeFirst();
        ad1.removeFirst();
        ad1.removeFirst();
        assertThat(ad1.isEmpty()).isTrue();
        ad1.addFirst("new"); // after this call we expect: ["back"]
        assertThat(ad1.toList()).containsExactly("new").inOrder();
    }


    @Test
    /** In this test, we dd some elements to a deque and remove them all, then check that addFirst still works. */
    public void addLastAfterRemoveToEmpty() {
        Deque61B<String> ad1 = new ArrayDeque61B<>();

        ad1.addLast("front"); // after this call we expect: ["front"]
        ad1.addLast("middle"); // after this call we expect: ["front", "middle"]
        ad1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
        ad1.removeLast();
        ad1.removeLast();
        ad1.removeLast();
        assertThat(ad1.isEmpty()).isTrue();
        ad1.addLast("new"); // after this call we expect: ["back"]
        assertThat(ad1.toList()).containsExactly("new").inOrder();
    }

    @Test
    /** Check that addFirst works when called on a full underlying array */
    public void addFirstTriggerResizeTest() {
        Deque61B<Integer> ad1 = new ArrayDeque61B<>();

        for (int i = 8; i > 0; i--) {
            ad1.addFirst( i);
        }

        assertThat(ad1.size()).isEqualTo(8);

        ad1.addFirst(0);
        assertThat(ad1.toList()).containsExactly(0, 1, 2, 3, 4, 5, 6, 7, 8);
        ad1.addFirst(-1);
        assertThat(ad1.toList()).containsExactly(-1, 0, 1, 2, 3, 4, 5, 6, 7, 8);
    }

    @Test
    /** Check that addLast works when called on a full underlying array */
    public void addLastTriggerResizeTest() {
        Deque61B<Integer> ad1 = new ArrayDeque61B<>();

        for (int i = 8; i > 0; i--) {
            ad1.addFirst( i);
        }

        assertThat(ad1.size()).isEqualTo(8);

        ad1.addLast(9);
        assertThat(ad1.toList()).containsExactly(1, 2, 3, 4, 5, 6, 7, 8, 9);
        ad1.addLast(10);
        assertThat(ad1.toList()).containsExactly(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    }
}
