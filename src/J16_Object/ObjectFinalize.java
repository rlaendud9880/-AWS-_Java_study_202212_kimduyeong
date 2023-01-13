package J16_Object;

class Test {
	private int num;

	public Test(int num) {
		super();
		this.num = num;
		System.out.println(num + "생성");
	}

	@Override
	protected void finalize() throws Throwable { 
		System.out.println(num + "객체 소멸");
	}
}

public class ObjectFinalize {

	public static void main(String[] args) {
		Test test = null;
		
		for(int i = 0; i< 30; i++) {
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			test = new Test(i);
			
			test = null;
			
			System.gc(); // garbage collector 를 실행
		}
	}
}
