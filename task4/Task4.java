import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;


public class Task4 {
    public static void main(String[] args) {        
        if (args.length > 0) {             
            String workingDir = System.getProperty("user.dir");
            String file = workingDir + "/" + args[0];                                     
            int[] ls = readFile(file);   
            System.out.println(minMoves(ls));            
        }else{
            System.out.println("Путь к файлу не указан.");
        }		
    }

    public static int[] readFile(String fileName){           
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            int lineCount = 0;
            String fileLine;    
            // Определение количества строк в файле
            while ((fileLine = reader.readLine()) != null) {
                lineCount++;
            }    
            // Создание массива для хранения числовых значений
            int[] intValues = new int[lineCount];
            reader.close();    
            // Повторное открытие файла и чтение значений для заполнения массива
            reader = new BufferedReader(new FileReader(fileName));
            int index = 0;  
            //Читает строку файла до тех пор, пока не достигнут конец файла.  
            while ((fileLine = reader.readLine()) != null) {
                String[] stringValues = fileLine.split(" ");
                for (String value : stringValues) {
                    int intValue = Integer.parseInt(value);
                    //Присваивает преобразованное целочисленное значение массиву intValues и увеличивает переменную index на единицу.
                    intValues[index++] = intValue;
                }
            }    
            reader.close();
            return intValues;            
            }catch (IOException e) {
                e.printStackTrace();
                return null;
            }            
    }

    public static int minMoves(int[] nums) {
        try {
            Arrays.sort(nums);
            int median = nums[nums.length / 2];  
            int moves = 0;
             // Вычисляем сумму абсолютных разностей каждого числа массива с медианой
            for (int num : nums) {            
                moves += Math.abs(num - median);            
            }        
            return moves;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    
}