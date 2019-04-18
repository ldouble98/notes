/**
 * @Author: small_double
 * @Date: 19-4-9 上午10:08
 */
public class 指引用 {
    public static void main(String[] args) {


        Person p = new Person("张三");

        change(p);

        System.out.println(p.name);

    }

    static class Person {
        String name;

        public Person(String name) {
            this.name = name;
        }
    }

    public static void change(Person p) {
        p.name = "as";
        // Person person = new Person("李四");
        // p = person;
    }
}
