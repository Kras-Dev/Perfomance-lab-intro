import java.io.FileReader;
import java.io.FileWriter;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;


public class Task3 {
    public static void main(String[] args) {
        if (args.length > 0) {  				
            String workingDir = System.getProperty("user.dir");			
            String testsFile = workingDir + "/" + args[0];			
            String valuesFile = workingDir + "/" + args[1];
            String reportFile = "report.json";

            // Читаем содержимое tests.json
            JSONObject testsJson = readJsonFile(testsFile);
            if (testsJson == null) {
                System.out.println("Ошибка чтения tests.json");
                return;
            } 
            // Читаем содержимое values.json
            JSONObject valuesJson = readJsonFile(valuesFile);
            if (valuesJson == null) {
                System.out.println("Ошибка чтения values.json");
                return;
            } 

            JSONObject reportJson = generateReport(testsJson, valuesJson);

            // Сохранение результата в файл report.json
            try (FileWriter file = new FileWriter(reportFile)) {
                file.write(reportJson.toString());
                file.flush();
                System.out.println("report.json создан");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("Файл не указан.");
        }		
    }

    // Метод для чтения JSON-файла
    private static JSONObject readJsonFile(String filePath){
        //блок try-with-resources, чтобы автоматически закрыть FileReader, когда чтение файла завершено
        try (FileReader reader = new FileReader(filePath)){ 
            return new JSONObject(new JSONTokener(reader));            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }    
    // Метод для обработки JSON и заполнения значениями
    private static void processJSON(JSONArray jsonArray, JSONObject jsonObject) {
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject testObject = jsonArray.getJSONObject(i);
            int id = testObject.getInt("id");                      
            JSONObject jsonValue = findValueById(jsonObject,id);   
            if (jsonValue != null) {
                // Если значение найдено, получаем значение поля "value"
                String value = jsonValue.optString("value");    
                // Добавляем значение к текущему объекту в массиве          
                testObject.put("value", value);                
            }
            // Если есть вложенные элементы, обработаем их рекурсивно
            if (testObject.has("values")) {                
                // Получаем вложенный массив
                JSONArray nestedArray = testObject.getJSONArray("values");                   
                // Рекурсивно вызываем метод для обработки вложенного массива и того же JSON объекта        
                processJSON(nestedArray, jsonObject);                
            }
        }
    }
    // Метод для поиска значения по id
    private static JSONObject findValueById(JSONObject jsonObject, int id) {
        String key="values";
        JSONArray valuesArray = jsonObject.getJSONArray(key);
        for (int i = 0; i < valuesArray.length(); i++) {
            JSONObject valueObject = valuesArray.getJSONObject(i);
            if (valueObject.getInt("id") == id) {
                return valueObject;
            }
        }
        return null;
    }
    // Метод для генерации отчета
    private static JSONObject generateReport(JSONObject testsJson, JSONObject valuesJson) {
        JSONArray testsArray = testsJson.getJSONArray("tests");
        processJSON(testsArray, valuesJson);
        //возвращает изначальный testsJson, который теперь содержит обновленные значения из valuesJson, после обработки 
        return testsJson;
    }


}