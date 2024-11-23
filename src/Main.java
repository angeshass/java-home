import java.io.InputStream;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.Period;

public class Main {
    public static void main(String[] args) {
        String str = Initials.setValues();
        Initials.getInitials(str);
    }
}

class Initials {
    public static String setValues() {
        /*сохраняет введеные данные*/
        Scanner scan = new Scanner(System.in);
        System.out.println("введите фамилию имя отчество число/месяц/год рождения");
        String string = scan.nextLine();
        return string.trim();
    }

    public static void getInitials(String str) {
        /*выводит нужную информацию*/
        String[] words = str.split(" "); //пробелами разделены ф и о дата
        //для красивого вывода фамилии с заглавной буквы
        StringBuffer surname = new StringBuffer(words[0].toLowerCase());
        surname.deleteCharAt(0);
        surname.insert(0, words[0].toUpperCase().charAt(0));

        System.out.println("Hello, " + surname + " " +
                words[1].toUpperCase().charAt(0) + ". " + //имя
                words[2].toUpperCase().charAt(0) + ". " + //отчество
                ", пол: " + Initials.gender(words[2]) + //от отчества
                ", возраст: " + Initials.age(words[3]));  //от даты
    }

    public static String gender(String dadname) {
        /* пол по отчеству*/
        dadname = dadname.toLowerCase();
        if (dadname.endsWith("ич")) {
            return "муж.";
        } else {
            if (dadname.endsWith("на"))
                return "жен.";
        }
        return "ОПРЕДЕЛИТЬ_НЕ_УДАЛОСЬ";
    }

    public static String age(String word) {
        /*возвращает возраст с правильным словом*/
        String[] date = word.split("/");
        int year = Integer.parseInt(date[2]);
        int month = Integer.parseInt(date[1]);
        int day = Integer.parseInt(date[0]);
        LocalDate birthDate = LocalDate.of(year, month, day);
        int ages;
        if (birthDate != null) {
            ages = Period.between(birthDate, LocalDate.now()).getYears();
        } else {
            return "неправильный формат даты";
        }
        
        if (ages % 100 <= 20 && ages % 100 >= 5) {
            return ages + " лет";
        } else {
            if (ages % 10 == 1) {
                return ages + " год";
            } else {
                if ((ages % 10 == 2) || (ages % 10 == 3) || (ages % 10 == 4)) {
                    return ages + " годa";
                }
            }
        }
        return "" ;
    }
}