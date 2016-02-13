import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * Created by Victor on 11.02.2016.
 */
public class Calendar {
    private String black = "\33[39;1m"; //цвета***************
    private String red = "\33[31;1m";
    private String green = "\33[32;1m";//*********************

    private static LocalDate date;//объект хранящий дату

    private static Days[][] month = new Days[6][7];//календарик отрисовываться будет из массива

    private int[] Jan = {1,7};//праздники**************
    private int[] Mar = {8};
    private int[] May = {1,2,9};
    private int[] Jun = {19,28};
    private int[] Aug = {24};
    private int[] Oct = {14};//************************

    public void colorDrawHolidays(int[] arr){//праздницному дню укажем краасный цвет******
        int n=0;
        int len = arr.length;

        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 7; j++){

                if(month[i][j].getDay() == arr[n]){
                    month[i][j].setColor(red);//установка цвета
                    n++;
                }
                if(n == len)break;
            }
            if(n == len)break;
        }
    }
    public void colorDrawToday(){//текущему дню укажем зелёный цвет
        int n=date.getDayOfMonth();

        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 7; j++){
                if(month[i][j].getDay() == n){
                    month[i][j].setColor(green);//установка цвета
                    n = 0;
                    break;
                }
                if(n == 0) break;
            }

        }
    }

    public   Calendar(){
        date = LocalDate.now();//присваиваем объекту текущие данные о дате

        for(int i = 0; i < 6; i++) {//инициализация каждого элемента массива
            for (int j = 0; j < 7; j++) {//с джавой не работал ранее, а на тестах
                month[i][j]= new Days();//NullPointerException выбивало
            }//потому решил прибегнуть к такому костылю
        }

        int firstDay;                   //день недели, который есть первым днём месяца
        firstDay = (date.getDayOfWeek().minus(date.getDayOfMonth() - 1)).getValue();

        int len= date.lengthOfMonth();  //длинна месяца
        boolean start = true;           //при заполлнении массива отметим прохождения стартовой позиции
        boolean finish = true;          //конечной позиции

        int day = 1; //счётчик дней
        for(int i = 0; i < 6; i++){//цикл для строк

            for (int j = 0; j < 7; j++) {//для столбцов
                if (start) {//если первое вхождение в цикл
                    j = firstDay - 1;//сместим первый день месяца на нужную позицию
                    start = false;//ведь не каждый месяц начинается с понедельника
                }
                month[i][j] = new Days(day);//присваиваем значения

                day++;//увеличиваем день
                if (day > len) {//если день имеет значение превосходящее кол-во дней в месяце
                    finish = false;//ставим метку окончания
                    break;
                }
            }
            if (!finish)//и выходим
                break;
        }
        int M = date.getMonth().getValue();
        switch (M) {//узнаем, какой месяц и если праздницный, закрасим нужные дни
            case 1:
                colorDrawHolidays(Jan);
                break;
            case 3:
                colorDrawHolidays(Mar);
                break;
            case 5:
                colorDrawHolidays(May);
                break;
            case 6:
                colorDrawHolidays(Jun);
                break;
            case 8:
                colorDrawHolidays(Aug);
                break;
            case 10:
                colorDrawHolidays(Oct);
                break;
            default: break;
        }
        colorDrawToday();//закрасим текущий день
    }


    public  void printNowDate(){//выводим текущую дату
        System.out.println(
                date.getDayOfWeek() +" "+ green +
                        date.getDayOfMonth() +" "+
                        date.getMonth() +" "+red +
                        date.getYear() +black
        );
    }



    public void printCalendar(){//выводим календарик
        for(int i = 1; i <= 5; i++)//сначала названия дней недели
            System.out.print(DayOfWeek.of(i).getDisplayName(TextStyle.SHORT, Locale.ENGLISH) + "\t\t");//чёрным цветом в короткой форме будни
        for(int i = 6; i <= 7; i++)
            System.out.print(red + DayOfWeek.of(i).getDisplayName(TextStyle.SHORT, Locale.ENGLISH) + "\t\t");//красным выходные
        System.out.println(black);

        for(int i = 0; i< 6;i++){
            for (int j = 0; j < 5; j++) {
                if (month[i][j].getDay() != 0 )
                    System.out.print(month[i][j].toStr() + black + "\t\t");
                else
                    System.out.print("\t\t");
            }
            for (int j = 5; j < 7; j++) {
                if (month[i][j].getDay() != 0) {
                    System.out.print(red+month[i][j].getDay() + "\t\t");
                } else {
                    System.out.print("\t\t");
                }
            }
            System.out.print(black);
            System.out.println();
        }
    }

}
