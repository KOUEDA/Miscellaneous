import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws IOException, FileNotFoundException {
		File file = new File(args[0]);
		try{
		Scanner lineReader = new Scanner(new BufferedReader(new FileReader(file)));
		String[] numList = {"negative", "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety", "hundred", "thousand", "million"};
		int[] nums = {-1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 30, 40, 50, 60 ,70, 80, 90, 100, 1000, 1000000};
		while (lineReader.hasNextLine()){
			String[] line = lineReader.nextLine().split(" ");
			int finalNum = 0; int thou = 0; int mil = 0; int cent = 0;
			int[] lineNums = new int[line.length];
			for (int a = 0; a < line.length; a++){
				lineNums[a] = nums[indexOf(line[a], numList)];
			}
			int index = 0;
			if (contains(lineNums, -1)) index++;
			if(contains(lineNums, 1000)){
				thou += makeThou(lineNums);
			}
			if (contains(lineNums, 1000000)){
				mil = makeMil(lineNums); 
			}
			if (contains(lineNums, 1000)){
				for (int k = indexOf("thousand", line) + 1; k < lineNums.length; k++){
					if (k != lineNums.length - 1){
						if (lineNums[k] < lineNums[k+1]){
							cent += lineNums[k]*lineNums[k+1];
							k++;
						}
						else cent += lineNums[k];
					}
					else cent += lineNums[k];
				}
			}
			else if (contains(lineNums, 1000000)) {
				for (int k = indexOf("million", line) + 1; k < lineNums.length; k++){
					if (k != lineNums.length - 1){
						if (lineNums[k] < lineNums[k+1]){
							cent += lineNums[k]*lineNums[k+1];
							k++;
						}
						else cent += lineNums[k];
					}
					else cent += lineNums[k];
				}
			}
			else {
				for (int k = index; k < lineNums.length; k++){
					if (k != lineNums.length - 1){
						if (lineNums[k] < lineNums[k+1]){
							cent += lineNums[k]*lineNums[k+1];
							k++;
						}
						else cent += lineNums[k];
					}
					else cent += lineNums[k];
				}
			}
			finalNum += mil + thou + cent;
			if (index == 1) finalNum*= -1;
			//System.out.println(mil+", "+thou+", "+cent );
			System.out.println(finalNum);
		}
		}
		
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static int makeMil(int[] use){
		int milIndex = 0;
		int finalMil = 0;
		int index = 0;
		if (use[0] == -1) { index++; milIndex++;}
		for (int a = index; use[a] != 1000000; a++){
			milIndex++;
		}
		ArrayList<Integer> million = new ArrayList<Integer>();
		for (int b = index; b < milIndex; b++){
			million.add(use[b]);
		}
		//System.out.println(million);
		for (int a = 0; a < million.size(); a++){
			if (a + 1 != million.size()){
				if (million.get(a) < million.get(a+1)){
					finalMil += million.get(a)*million.get(a+1);
					//System.out.println(finalMil);
					a++;
				}
				else finalMil += million.get(a);
				//System.out.println(finalMil);
			}
			else finalMil += million.get(a);
			//System.out.println(finalMil);
		}
		finalMil *= 1000000;
		return finalMil;
	}
	
	public static int makeThou(int[] use){
		int milIndex = 0;
		int finalThou = 0;
		int neg = 0;
		boolean containsMil = false;
		ArrayList<Integer> thousand = new ArrayList<Integer>();
		if (use[0] == -1) neg++;
		for (int i = 0; i < use.length; i++){
			if (use[i] == 1000000){
				containsMil = true;
				milIndex = i;
			}
		}
		if (containsMil){
			for (int j = milIndex + 1; use[j] != 1000; j++){
				thousand.add(use[j]);
			}
		}
		else {
			for (int i = neg; use[i] != 1000; i++){
				thousand.add(use[i]);
			}
		}
		//System.out.print(thousand);
		for (int a = 0; a < thousand.size(); a++){
			if (a + 1 != thousand.size()){
				if (thousand.get(a) < thousand.get(a+1)){
					finalThou += thousand.get(a)*thousand.get(a+1);
					a++;
				}
				else finalThou += thousand.get(a);
			}
			else finalThou += thousand.get(a);
		}
		return finalThou*1000;
	}

	
	public static int indexOf(String s, String[] words) {
		int i = 0;
		while (!words[i].equals(s)){
			i++;
		}
		return i;
	}
	public static boolean contains(int[] use, int p){
		for (int n : use){
			if (n == p){
				return true;
			}
		}
		return false;
	}
}
