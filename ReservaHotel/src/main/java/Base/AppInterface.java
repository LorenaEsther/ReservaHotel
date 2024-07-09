
package Base;

public interface AppInterface {
    void onLoginSuccess(int Usuario, String rol);
    void onLoginFailed(String username);
    void onLogout(int usuarioID);
    
}
