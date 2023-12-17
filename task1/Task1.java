public class Task1 {
    public static void main(String[] args) {
		if (args.length != 2) {
            System.out.println("Укажите два аргумента командной строки: число n и длину обхода m.");
            return;
        }
        int n = Integer.parseInt(args[0]); // Размер кругового массива
        int m = Integer.parseInt(args[1]); // Длина интервала обхода
	// Заполняем массив числами от 1 до n
        int[] circularArray = new int[n];
        for (int i = 0; i < n; i++) {
            circularArray[i] = i + 1;
        }
        int currentIndex = 0; 
        do {
            System.out.print(circularArray[currentIndex] + " ");
	//обновляем currentIndex для определения следующего элемента
            current = (currentIndex + m - 1) % n;
        } while (currentIndex != 0);
    }
}
