package primary.class06;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 自定义比较器比较对象大小
 */
public class ShowComparator {

    static class Student {

        Integer id;
        String name;
        public Student(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

    }

    /**
     * 实现类的比较器
     */
    public static class IdComparator implements Comparator<Student> {

        // 如果返回负数，认为第一个参数应该排在前面
        // 如果返回正数，认为第二个参数应该排在前面
        // 如果返回0，认为谁放前面无所谓
        @Override
        public int compare(Student s1, Student s2) {
            if (s1.id < s2.id) {
                return -1;
            }else if(s1.id>s2.id){
                return 1;
            }else {
                return 0;
            }
        }
    }

    public static void main(String[] args) {



        Student s1 = new Student(5, "张三");
        Student s2 = new Student(4, "张三");
        Student s3 = new Student(6, "张三");
        Student s4 = new Student(2, "张三");
        List<Student> list = Arrays.asList(new Student[]{s1, s2, s3, s4});
        list.forEach(s-> System.out.println(s.id+","+s.name));
        System.out.println("============");
        list.sort(new IdComparator());  // 对象按自定义的比较器排序
        list.forEach(s-> System.out.println(s.id+","+s.name));
    }
}
