import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Task2 {
    public static void main(String[] args) {
        if (args.length > 0) {   
			// Получение текущей директории		
            String workingDir = System.getProperty("user.dir");
			 // Формирование пути к файлу окружности
            String circleFile = workingDir + "/" + args[0];
			// Формирование пути к файлу точек
            String pointsFile = workingDir + "/" + args[1];
            
            try{
				 // Чтение файла окружности
                BufferedReader reader = new BufferedReader(new FileReader(circleFile));
				 // Чтение строки из файла и разделение ее на элементы массива по пробелу
                String fileLine = reader.readLine();                               
                String[] circle = fileLine.split(" ");
				// Извлечение координат				
                float circleX = Float.parseFloat(circle[0]);
                float circleY = Float.parseFloat(circle[1]); 
                float radius = Float.parseFloat(reader.readLine());    
                reader.close();
				 // Чтение файла точек
                BufferedReader reader2 = new BufferedReader(new FileReader(pointsFile));
                String fileLine2;
                while ((fileLine2 = reader2.readLine()) != null) {
                    String[] points = fileLine2.split(" ");
                    float pointX = Float.parseFloat(points[0]);
                    float pointY = Float.parseFloat(points[1]);   
                    System.out.println(getPointPosition(circleX, circleY, radius, pointX,pointY));              
                }
                reader2.close();
                
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("Путь к файлу не указан.");
        }		
    }
	// Определение позиции точки относительно окружности
    public static int getPointPosition(float circleX, float circleY, float radius, float pointX, float pointY){
		// Вычисление выражения для определения расстояния между точкой и центром окружности
        float expression = (float) Math.sqrt(Math.pow(pointX - circleX, 2) + Math.pow(pointY - circleY, 2));
        if (expression == radius) {
            return 0; // Точка лежит на окружности
        } else if (expression < radius) {
            return 1; // Точка лежит внутри окружности
        } else {
            return 2; // Точка лежит вне окружности
        }       
    }


}