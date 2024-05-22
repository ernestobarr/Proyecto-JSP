package Model;

public class UserModel {
    private int id;
    private String nombre;
    private String apellido;
    private String usuario;
    private String clave;

    public UserModel() {
    }

    public UserModel(int id, String nombre, String apellido, String usuario) {
    this.id = id;
    this.nombre = nombre;
    this.apellido = apellido;
    this.usuario = usuario;
}
    
    public UserModel(int id, String nombre, String apellido, String usuario, String clave) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.usuario = usuario;
        this.clave = clave;
    }

    // Getters y setters para todos los campos
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
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
}