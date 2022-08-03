package trip.daeun.lodging.utill;

import java.util.Scanner;

public class InputUtill {

	public static Scanner sc = new Scanner(System.in);

	public static int getInt() {
		String s = sc.nextLine();
		return Integer.parseInt(s);

	}

}