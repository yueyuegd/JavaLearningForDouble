package LambdaExpression;

import java.time.LocalDate;
import java.time.chrono.IsoChronology;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangyue-k on 2017/7/2.
 * User:zhangyue-k
 * Date:2017/7/2
 */
public class Person {


    public enum Sex{
        MALE,FEMALE
    }

    String name;
    LocalDate birthday;
    Sex gender;
    String emailAddress;

    Person(String nameArg,LocalDate birthdayArg,Sex genderArg,String emailArg){

        name = nameArg;
        birthday = birthdayArg;
        gender = genderArg;
        emailAddress = emailArg;

    }

    public int getAge(){
        return birthday.until(IsoChronology.INSTANCE.dateNow())
                .getYears();
    }

    public void printPerson(){
        System.out.println(name + ", " + this.getAge());
    }

    public Sex getGender(){
        return gender;
    }

    public String getName(){
        return name;
    }

    public String getEmailAddress(){
        return emailAddress;
    }

    public LocalDate getBirthday(){
        return birthday;
    }

    public static int comparebyAge(Person a,Person b){
        return a.birthday.compareTo(b.birthday);
    }

    public static List<Person> createRoster(){
        List<Person> roster = new ArrayList<Person>();
        roster.add(
                new Person("Fred",IsoChronology.INSTANCE.date(1980,6,20), Sex.MALE,"fred@example.com")
        );
        roster.add(
                new Person("Jane",IsoChronology.INSTANCE.date(1990,7,15), Sex.FEMALE,"jane@example.com")
        );
        roster.add(
                new Person("George",IsoChronology.INSTANCE.date(1991,8,13), Sex.MALE,"george@example.com")
        );
        roster.add(
                new Person("Bob",IsoChronology.INSTANCE.date(2000,9,12), Sex.MALE,"bob@example,com")
        );
        return roster;
    }
}
