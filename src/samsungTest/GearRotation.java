package samsungTest;
import java.util.*;
public class GearRotation {
		static int[][]gear = new int[4][8];
		static int k;
		static int[] selectedGear = new int[100]; //1~4
		static int[] dir = new int[100]; //-1, 1
	public static void main(String[] args) {

		Input();
		FullRotate();
		int point = 0;
		for(int i=0; i<4; i++) {
			if(gear[i][0]==1) {
				point+=(int)Math.pow(2, i);
			}
		}
		System.out.print("point : "+point);
		
	}
	public static void Input() {
		Scanner sc = new Scanner(System.in);
		//System.out.println("Input the data of gears : "); // N:0, S:1
		for(int i=0; i<gear.length; i++) {
			String str = sc.nextLine();
			String [] arrStr = str.split("");
			
			for(int j=0; j<gear[i].length; j++) {
				gear[i][j]= Integer.parseInt(arrStr[j]);
			}
		}
		//System.out.println("Number of trial : ");
		k = sc.nextInt();
		//System.out.println("Number of gear and direction : ");
		for(int i=0; i<k; i++) {
			selectedGear[i] = sc.nextInt()-1; // 1~4 -1 = 0~3
			dir[i] = sc.nextInt(); // 1: clockwise , -1 : counterclockwise
		}
	}
	public static void FullRotate() {
		// Rotate K times
		for(int i=0; i<k; i++) {
			//select the gear which you chose to rotate
			int s = selectedGear[i];
			int wise = dir[i];
			int start = s;
			
			CheckLeftGear(gear, s, wise, start);
			CheckRightGear(gear, s, wise, start);
			Rotate(wise, s);
			/*
			for(int j=0; j<4; j++) {
				for(int t=0; t<8; t++) {
					System.out.print(gear[j][t]);
				}
				System.out.println("");
			}*/
		}
	}
	public static void CheckLeftGear(int[][] gear, int s, int wise, int start) {
		//System.out.println("checkLeft"+s);
		//System.out.println("wise : "+wise);
		if(s>0) {
			if(gear[s][6]!=gear[s-1][2])
				CheckLeftGear(gear, s-1, wise*(-1), start);
		}
		if(s!=start)
			Rotate(wise, s);
	}
	public static void CheckRightGear(int[][] gear, int s, int wise, int start) {
		//System.out.println("checkRight"+s);
		//System.out.println("wise : "+wise);
		if(s<3) {
			if(gear[s][2]!=gear[s+1][6])
				CheckRightGear(gear, s+1, wise*(-1), start);
		}
		if(s!=start)
			Rotate(wise, s);
	}
	public static void Clockwise(int n) {
		//System.out.println("clockwise"+n);
		int temp = gear[n][7];
		for(int i = 7; i>0; i--) 
			gear[n][i] = gear[n][i-1]; 
		gear[n][0] = temp;
	}
	public static void CounterClockwise(int n) {
		//System.out.println("counterclockwise"+n);
		int temp = gear[n][0];
		for(int i=0; i<7; i++)
			gear[n][i] = gear[n][i+1];
		gear[n][7] = temp;
	}
	public static void Rotate(int wise, int s){
		switch(wise) {
		case -1 : CounterClockwise(s); break;
		case 1 : Clockwise(s); break;
		}
	}
}
