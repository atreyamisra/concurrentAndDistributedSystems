import java.util.*;

public class HelloWorld{
	public static void main(String args[]){
		String Welcome = "Hello World";
		Welcome = "Dogs are cool";
		Welcome = Welcome + " I'm fucking awesome";
		int yearOfBirth = 97;
		System.out.println(Welcome);
		Welcome = Welcome + " I'm " + yearOfBirth + " years old.";
		System.out.println(Welcome);
		String[][] array = new String[10][10];
		array[0][0] = "yo";
		System.out.println(array[0][0]);
		int[] arraytwo = new int[1];
		arraytwo[0] = 9;
		System.out.println(arraytwo[0]);
		int[] sumThis = new int[3];
		sumThis[0] = 5;
		sumThis[1] = 9;
		sumThis[2] = 4;
		int sumTotal;
		sumTotal = sumThis[0] + sumThis[1] + sumThis[2];
		System.out.println(sumTotal);
		int[] sumThis2 = new int[100];
		int putInArray = 13;
		for(int i = 0; i< sumThis2.length; i=i+1){
			sumThis2[i]=i;
		}
		System.out.println(sumThis2[50]);
		int sum = 0;
		int KatherineIsStupid = 0;
		int[] hi = new int[2];
		hi[0] = 5;
		hi[1] = 15;
		KatherineIsStupid = hi[0] + KatherineIsStupid;
		KatherineIsStupid = hi[1] + KatherineIsStupid;
		System.out.println("Katherine is stupid = " + KatherineIsStupid);
		for(int i=0;i<sumThis2.length;i=i+1){
			sum = sumThis2[i]+ sum;
		}	
		System.out.println(sum);
		
		int dogs = 0;
		for(int i=0;i<sumThis2.length;i++){
		dogs=sumThis2[i]+dogs/2;
		}
		System.out.println(sum);
		System.out.println(dogs);	
		int i =0;
		sum=0;
		
		/*while(i<sumThis2.length){
			sum += 2*sumThis2[i];
			System.out.println("Katherine is stupid");
			i++;
		}
		System.out.println(sum);*/
		
		while(i<50){
			sum -= 2*sumThis2[i];
			i++;
			System.out.println(sum);
		}
		long sum1;
		long a = 4;
		long b = 600000000;
		sum1=sumTwoElements(6,b);
		System.out.println(sum1);
		
		int[] cats = new int[10];
		dogs = 0;
		
		for(i=0;i<cats.length;i++){
		cats[i]=i+1;
		}
		int catsSuck;
		catsSuck=sumAlotOfElements(cats);
		System.out.println(catsSuck);
		
	}
	private static long sumTwoElements(int a, long b){
		return a+b;
	}
	private static int sumAlotOfElements(int[] cats){
		int dogs = 0;
		for(int i=0;i<cats.length;i++){
			dogs=cats[i]+dogs;
			//System.out.println(dogs);
		}
		return dogs;
	}
}