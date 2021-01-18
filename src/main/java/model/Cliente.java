package model;

import java.sql.Date;

/**
 *
 * @author jorog
 */
public class Cliente {
    
    protected int codigoCliente;
    protected String nombre;
    protected String apellidos;
    protected String dni;
    protected String login;
    protected String password;
    protected Date fecha_nac;
    protected String telefono;
    protected String email;
    protected Cuenta cuenta;
    public Cliente() {
    }

    public Cliente(String nombre, String apellidos, String dni, String login, String password, Date fecha_nac, String telefono, String email) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.login = login;
        this.password = password;
        this.fecha_nac = fecha_nac;
        this.telefono = telefono;
        this.email = email;
    }

    public Cliente(int codigoCliente, String nombre, String apellidos, String dni, String login, String password, Date fecha_nac, String telefono, String email) {
        this.codigoCliente = codigoCliente;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.login = login;
        this.password = password;
        this.fecha_nac = fecha_nac;
        this.telefono = telefono;
        this.email = email;
    }

    public Cliente(int codigoCliente, String nombre, String apellidos, String dni, String login, String password, Date fecha_nac, String telefono, String email, Cuenta cuenta) {
        this.codigoCliente = codigoCliente;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.login = login;
        this.password = password;
        this.fecha_nac = fecha_nac;
        this.telefono = telefono;
        this.email = email;
        this.cuenta = cuenta;
    }

    public int getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(int codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getFecha_nac() {
        return fecha_nac;
    }

    public void setFecha_nac(Date fecha_nac) {
        this.fecha_nac = fecha_nac;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    @Override
    public String toString() {
        return "Cliente{" + "codigoCliente=" + codigoCliente + ", nombre=" + nombre + ", apellidos=" + apellidos + ", dni=" + dni + ", login=" + login + ", password=" + password + ", fecha_nac=" + fecha_nac + ", telefono=" + telefono + ", email=" + email + ", cuenta=" + cuenta + '}';
    }


    
            
    
}
