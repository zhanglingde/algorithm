package basic.class18;

import java.util.Stack;

/**
 * 不申请额外的数据结构，逆序这个栈（使用递归方式）
 */
public class Code05_ReverseStackUsingRecursive {

	public static void reverse(Stack<Integer> stack) {
		if (stack.isEmpty()) {
			return;
		}
		int bottom = f(stack);
		reverse(stack);
		// 栈中元素都取完后，然后再放进去，放和取的顺序相反
		stack.push(bottom);
	}

	// 递归移除栈底元素并返回，其他元素顺序不变
	public static int f(Stack<Integer> stack) {
		// 用一个变量保存栈顶元素，栈还有元素就递归在另一个方法中用一个变量保存栈顶元素，直到到栈底
		int result = stack.pop();
		if (stack.isEmpty()) {
			return result;
		} else {
			int last = f(stack);
			stack.push(result);
			return last;
		}
	}

	public static void main(String[] args) {
		Stack<Integer> test = new Stack<Integer>();
		test.push(1);
		test.push(2);
		test.push(3);
		test.push(4);
		test.push(5);
		reverse(test);
		while (!test.isEmpty()) {
			System.out.println(test.pop());
		}

	}

}