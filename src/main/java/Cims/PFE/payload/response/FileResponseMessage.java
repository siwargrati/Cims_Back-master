package Cims.PFE.payload.response;

public class FileResponseMessage {
		  private String message;

		  public FileResponseMessage(String message) {
		    this.message = message;
		  }

		  public String getMessage() {
		    return message;
		  }

		  public void setMessage(String message) {
		    this.message = message;
		  }
}
