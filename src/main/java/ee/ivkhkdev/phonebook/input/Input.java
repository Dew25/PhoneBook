package ee.ivkhkdev.phonebook.input;

import java.util.Scanner;

public interface Input {

    default String nextLine(){
        return new Scanner(System.in).nextLine();
    }

    default int nextInt(){
        do{
            try {
                return Integer.parseInt(this.nextLine());
            }catch (Exception e){
                System.out.println("Введите число: ");
            }
        }while(true);
    }
}
