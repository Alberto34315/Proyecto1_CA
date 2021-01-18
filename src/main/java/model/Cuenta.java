
package model;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author jorog
 */
public class Cuenta {
    protected int codigoCuenta;
    protected float saldo;
    protected Date fechaHoraC;
    protected Date fechaHoraUM;
    protected ArrayList<Cliente> ListClient;
    protected Date fechaHoraUA;
    
    public Cuenta() {
    }

    public Cuenta(float saldo, Date fechaHoraC, Date fechaHoraUM) {
        this.saldo = saldo;
        this.fechaHoraC = fechaHoraC;
        this.fechaHoraUM = fechaHoraUM;
    }

    public Cuenta(int codigoCuenta, float saldo, Date fechaHoraC, Date fechaHoraUM) {
        this.codigoCuenta = codigoCuenta;
        this.saldo = saldo;
        this.fechaHoraC = fechaHoraC;
        this.fechaHoraUM = fechaHoraUM;
    }

    public Cuenta(int codigoCuenta, float saldo, Date fechaHoraC, Date fechaHoraUM, ArrayList<Cliente> ListClient, Date fechaHoraUA) {
        this.codigoCuenta = codigoCuenta;
        this.saldo = saldo;
        this.fechaHoraC = fechaHoraC;
        this.fechaHoraUM = fechaHoraUM;
        this.ListClient = ListClient;
        this.fechaHoraUA = fechaHoraUA;
    }

    public ArrayList<Cliente> getListClient() {
        return ListClient;
    }

    public void setListClient(ArrayList<Cliente> ListClient) {
        this.ListClient = ListClient;
    }

    public Date getFechaHoraUA() {
        return fechaHoraUA;
    }

    public void setFechaHoraUA(Date fechaHoraUA) {
        this.fechaHoraUA = fechaHoraUA;
    }

    public int getCodigoCuenta() {
        return codigoCuenta;
    }

    public void setCodigoCuenta(int codigoCuenta) {
        this.codigoCuenta = codigoCuenta;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public Date getFechaHoraC() {
        return fechaHoraC;
    }

    public void setFechaHoraC(Date fechaHoraC) {
        this.fechaHoraC = fechaHoraC;
    }

    public Date getFechaHoraUM() {
        return fechaHoraUM;
    }

    public void setFechaHoraUM(Date fechaHoraUM) {
        this.fechaHoraUM = fechaHoraUM;
    }

    @Override
    public String toString() {
        return "Cuenta{" + "codigoCuenta=" + codigoCuenta + ", saldo=" + saldo + ", fechaHoraC=" + fechaHoraC + ", fechaHoraUM=" + fechaHoraUM + ", ListClient=" + ListClient + ", fechaHoraUA=" + fechaHoraUA + '}';
    }


    
}
