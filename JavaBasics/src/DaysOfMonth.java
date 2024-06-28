import java.util.Scanner;
public class DaysOfMonth {
    public static boolean checkLeapYear(int year){
        if (year % 4 == 0){
            if (year % 100 == 0 & year % 400 != 0){
                return false;
            } else return true;
        } else return false;
    }

    static String[][] lookup = {
            {"January", "Jan.", "Jan", "1", "31"},
            {"February", "Feb.", "Feb", "2", "28"},
            {"March", "Mar.", "Mar", "3", "31"},
            {"April", "Apr.", "Apr", "4", "30"},
            {"May", "May.", "May", "5", "31"},
            {"June", "Jun.", "Jun", "6", "30"},
            {"July", "Jul.", "Jul", "7", "31"},
            {"August", "Aug.", "Aug", "8", "31"},
            {"September", "Sep.", "Sep", "9", "30"},
            {"October", "Oct.", "Oct", "10", "31"},
            {"November", "Nov.", "Nov", "11", "30"},
            {"December", "Dec.", "Dec", "12", "31"}
    };

    public static int checkMonth(String month){
        int check_month = -1;
        int i = 0;
        while (i < 12){
            if (month.equals(lookup[i][0]) | month.equals(lookup[i][1]) | month.equals(lookup[i][2]) | month.equals(lookup[i][3])) {
                check_month = i;
                break;
            } else {
                i += 1;
            }
        }
        return check_month;
    }

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the month: ");
        String month = input.nextLine();
        while (checkMonth(month) == -1){
            System.out.println("Invalid month. Try again!");
            System.out.print("Enter the month: ");
            month = input.nextLine();
        }

        System.out.print("Enter the year: ");
        String strYear = input.nextLine();
        while (true){
            try {
                int year = Integer.parseInt(strYear);
                while (year <=0){
                    System.out.println("Invalid year. Try again!");
                    System.out.print("Enter the year: ");
                    year = input.nextInt();
                }
                if ( checkLeapYear(year) == true & checkMonth(month) == 1) {
                    System.out.print("The number of days of this month is " + 29);
                } else {
                    System.out.print("The number of days of this month is " + lookup[checkMonth(month)][4]);
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid year.Try again!");
                System.out.print("Enter the year: ");
                strYear = input.nextLine();
            }
        }
        System.exit(0);
    }
}
