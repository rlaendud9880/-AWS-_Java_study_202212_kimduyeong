package j03_항변항;

public class Casting2 {

	public static void main(String[] args) {
		char char_a = 'a';
		int num = (int) char_a;
		
		char char_b = (char) (num + 1);
		
		System.out.println('a' + 1);
		System.out.println(char_a);
		System.out.println(char_b);

	}

}
