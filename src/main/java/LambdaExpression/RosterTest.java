package LambdaExpression;


import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by zhangyue-k on 2017/7/2.
 * User:zhangyue-k
 * Date:2017/7/2
 */
public class RosterTest {

    interface CheckPerson{
        boolean test(Person p);
    }

    //第一步：创建一个方法来寻找匹配的人物
    public static void printPersonsOlderThan(List<Person> roster,int age){
        for(Person p:roster){
            if(p.getAge() >= age){
                p.printPerson();
            }
        }
    }

    //第二步：创建更多普遍的所查方法
    public static void printPersonsWithinAgeRange(List<Person> roster,int low,int high){

        for(Person p:roster){
            if(low <= p.getAge() && p.getAge() < high ){
                p.printPerson();
            }
        }
    }

    public static void printPersons(List<Person> roster, CheckPerson tester){
        for(Person p:roster){
            if(tester.test(p)){
                p.printPerson();
            }
        }

    }

    public static void printPersonsWithPredicate(List<Person> roster,Predicate<Person> tester){
        for(Person p:roster){
            if(tester.test(p)){
                p.printPerson();
            }
        }
    }

    public static void processPersons(List<Person> roster, Predicate<Person> tester, Consumer<Person> block){
        for(Person p:roster){
            if(tester.test(p)){
                block.accept(p);
            }
        }
    }

    public static void processPersonsWithFunction(List<Person> roster, Predicate<Person> tester, Function<Person,String> mapper,Consumer<Person> block){
        for(Person p:roster){
            if(tester.test(p)){
                String data = mapper.apply(p);
                block.accept(p);
            }
        }


    }


    public static <X,Y> void processElements(Iterable<X> source,Predicate<X> tester,Function<X,Y> mapper,Consumer<Y> block){
        for(X p:source){
            if(tester.test(p)){
                Y data = mapper.apply(p);
                block.accept(data);
            }
        }
    }


    public static void main(String[] args) {
        List<Person> roster = Person.createRoster();
        for(Person p:roster){
            p.printPerson();
        }
        System.out.println("Persons older than 20:");
        printPersonsOlderThan(roster,20);
        System.out.println();

        System.out.println("Persons between the ages of 14 and 30:");
        printPersonsWithinAgeRange(roster,14,30);
        System.out.println();

        System.out.println("Persons who are eligible for Selective Service:");
        class CheckPersonEligibleForSelectiveService implements CheckPerson{
            public boolean test(Person p){
                return p.getGender() == Person.Sex.MALE
                        && p.getAge() >= 18
                        && p.getAge() <= 25;
            }
        }
        printPersons(roster,new CheckPersonEligibleForSelectiveService());
        System.out.println();

        System.out.println("Perosns who are aligible for Selective Service " + "(anonymoue class):");
        printPersons(roster, new CheckPerson() {
            public boolean test(Person p) {
                return p.getGender() == Person.Sex.MALE
                        && p.getAge() >= 18
                        && p.getAge() <= 25;
            }
        });

        System.out.println();

        System.out.println("Persons who are aligible for Selective Service " + "(lambda expression):");

        printPersons(roster,(Person p) -> p.getGender() == Person.Sex.MALE && p.getAge() >= 18 && p.getAge() <= 25);

        System.out.println();

        System.out.println("Perosns who are sligible for Selective Service " + "(with Predicate parameter):");

        printPersonsWithPredicate(roster,p -> p.getGender() == Person.Sex.MALE && p.getAge() >= 18 && p.getAge() <= 25);

        System.out.println();

        System.out.println("Persons who are eligible for Selective Service " + "(with Predicate and Consumer parameter):");
        processPersonsWithFunction(roster,p -> p.getGender() == Person.Sex.MALE && p.getAge() >= 18 && p.getAge() <= 25,p -> p.getEmailAddress(),email -> System.out.println(email));

        System.out.println();

        System.out.println("Perosns who are eligible for Se;lective Service " + "(generic version):");
        processElements(roster,p -> p.getGender() == Person.Sex.MALE && p.getAge() >= 18 && p.getAge() <= 25,p -> p.getEmailAddress(),email -> System.out.println(email));

        System.out.println();

        System.out.println("Persons weho are eligible for Selective Service " + "(with bulk data operations):");
       roster.stream().filter(
               p -> p.getGender() == Person.Sex.MALE && p.getAge() >= 18 && p.getAge() <= 25
       ).map(p -> p.getEmailAddress())
               .forEach(email -> System.out.println(email));
    }
}
