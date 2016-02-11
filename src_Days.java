/**
 * Created by Victor on 11.02.2016.
 */
public class Days {
    private int day=0;//поле будет хранить в себе день по счёту в месяце
    private String color = "\33[39;1m";//цвет для вывода

    public Days(int a, String b){//конструктор
        day=a;
        color=b;
    }
    public int getDay(){//геттер дня
        return day;
    }
    public  void setDay(int d){//сеттер дня
        day=d;
    }
    public String getColor(){//геттер цвета
        return color;
    }
    public void setColor(String s){//сеттер цвета
        color = s;
    }
    public Days(int a){
        day = a;
        color = "\33[39;1m";
    }
    public Days(){
        day = 0;
        color = "\33[39;1m";
    }
    public String  toStr(){

        return color+day+"\33[39;1m";
    }
}
