package api.entities;

public class LoginSuccess {
    private String token;

    public LoginSuccess(String token) {
        this.token = token;
    }
    public LoginSuccess( ) {
    }

    public String getToken() {
        return token;
    }
}
