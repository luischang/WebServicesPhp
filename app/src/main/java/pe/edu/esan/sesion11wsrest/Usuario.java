package pe.edu.esan.sesion11wsrest;

/**
 * Created by lchang on 15/11/2017.
 */

public class Usuario {

    public Usuario(String usuario, String clave) {
        this.setUsuario(usuario);
        this.setClave(clave);
    }

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

    private String usuario;
    private String clave;

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

    private String nombre;

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    private String correo;

}
