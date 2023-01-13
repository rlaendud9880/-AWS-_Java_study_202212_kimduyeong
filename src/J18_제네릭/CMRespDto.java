package J18_제네릭;

// commit Message Response Data Transfer Object
// 클러이언트가 서버에게 요청을 날리면 동일한 형식으로 응답해주는 응답인터페이스
public class CMRespDto<T> {  // 공통 응답 객체
	private int code;
	private String message;
	private T data;
	
	public CMRespDto(int code, String message, T data) {
		super();
		this.code = code;
		this.message = message;
		this.data = data;
	}

	@Override
	public String toString() {
		return "CMRespDto [code=" + code + ", message=" + message + ", data=" + data + "]";
	}
	
	

}
