
package Base;

public interface AppInterface {
    void onLoginSucces(int Usuario, String role);
    void onLoginFailed(String username);
    void onLogout(int usuarioID);
    
}
