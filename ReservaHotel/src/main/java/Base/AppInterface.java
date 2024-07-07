
package Base;

public interface AppInterface {
    void onLoginSuccess(int Usuario, String role);
    void onLoginFailed(String username);
    void onLogout(int usuarioID);
    
}
