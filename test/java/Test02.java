import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author zhangling
 * @date 2022/12/3 2:05 PM
 */
public class Test02 {
    public static void main(String[] args) {
        Class class1 = new Class("class 1");
        Class class2 = new Class("class 2");
        List<Student> list1 = Arrays.asList(new Student("A"), new Student("B"));
        class1.setStudentList(list1);
        List<Student> list2 = Arrays.asList(new Student("C"), new Student("D"));
        class2.setStudentList(list2);

        List<Class> classList = Arrays.asList(class1,class2);
        Class class3 = classList.stream().reduce((i1, i2) -> {
            Class aClass = new Class("class 3");
            List<Student> temp = new ArrayList<>();
            aClass.setStudentList(temp);
            temp.addAll(i1.getStudentList());
            temp.addAll(i2.getStudentList());

            List<String> stringList = new ArrayList<>();
            stringList.addAll(i1.getStrList());
            stringList.addAll(i2.getStrList());

            return aClass;
        }).get();

        System.out.println("class1 = " + class1);
        System.out.println("class2 = " + class2);
        System.out.println("class3 = " + class3);


    }
}
