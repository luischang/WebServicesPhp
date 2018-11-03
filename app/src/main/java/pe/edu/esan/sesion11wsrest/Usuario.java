package pe.edu.esan.sesion11wsrest;

/**
 * Created by lchang on 15/11/2017.
 */

public class Usuario {
    private String usuario;
    private String clave;
    private String nombre;
    private String correo;

    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public String getClave() {
        return clave;
    }
    public void setClave(String clave) {
        this.clave = clave;
    }
    public Usuario(String ID, String nombre, String correo) {
        this.usuario = ID;
        this.nombre = nombre;
        this.correo = correo;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
