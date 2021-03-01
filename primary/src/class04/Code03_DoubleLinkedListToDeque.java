package class04;

/**
 * 双链表实现双端队列
 *
 * @author zhangling 2021/3/1 15:12
 */
public class Code03_DoubleLinkedListToDeque {

    public static class Node<V> {
        private V value;
        private Node<V> last;
        private Node<V> next;

        public Node(V v) {
            last = null;
            next = null;
            value = v;
        }
    }

    public static class MyDeque<V> {
        private Node<V> head;
        private Node<V> tail;
        private int size;

        public MyDeque() {
            head = null;
            tail = null;
            size = 0;
        }

        /**
         * 从头部取数据，头部指针指向下一个节点，且下一个节点的前指针变为null
         * @param v
         */
        private void pushHead(V v) {
            Node cur = new Node(v);
            if (head == null) {
                head = cur;
                tail = cur;
            } else {
                cur.next = head;
                head.last = cur;
                head = cur;
            }
            size++;
        }

        private void pushTail(V v) {
            Node cur = new Node(v);
            if (head == null) {
                head = cur;
                tail = cur;
            } else {
                tail.next = cur;
                cur.last = tail;
                tail = cur;
            }
            size++;
        }

        private V pollHead() {
            V ans = null;
            if (head != null) {
                ans = head.value;
                head = head.next;
                if (head != null) {
                    head.last = null;
                }
                size --;
            }
            return ans;
        }

        private V pollTail() {
            V ans = null;
            if (head != null) {
                ans = tail.value;
                tail = tail.last;
                if (tail != null) {
                    tail.next = null;
                }
                size--;
            }
            return ans;
        }
    }
}
