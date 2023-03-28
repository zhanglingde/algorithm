package basic.class13_dp;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 一间教室，尽可能安排多的课程
 */
public class Code03_ClassroomCourse {

    static class Course {
        public double start;
        public double end;

        public Course(double start, double end) {
            this.start = start;
            this.end = end;
        }
    }

    public static int bestArrange(Course[] courses) {
        Arrays.sort(courses, new CourseComparator());
        int result = 0;
        double timeLine = 0;
        for (int i = 0; i < courses.length; i++) {
            if (timeLine <= courses[i].start) {
                result++;
                timeLine = courses[i].end;
            }
        }
        return result;
    }

    static class CourseComparator implements Comparator<Course> {

        @Override
        public int compare(Course o1, Course o2) {
            if (o1.end - o2.end < 0) {
                return -1;
            } else if (o1.end - o2.end > 0) {
                return 1;
            }else{
                return 0;
            }
        }
    }

    public static void main(String[] args) {
        Course[] courses = new Course[]{new Course(9, 10), new Course(9.5, 10.5), new Course(10, 11), new Course(10.5, 11.5), new Course(11, 12)};
        int result = bestArrange(courses);
        System.out.println("result = " + result);
    }
}
