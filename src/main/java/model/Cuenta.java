
package model;

import java.sql.Date;

/**
 *
 * @author jorog
 */
public class Cuenta {
    protected int codigoCuenta;
    protected float saldo;
    protected Date fechaHoraC;
    protected Date fechaHoraUM;

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
        return "Cuenta{" + "codigoCuenta=" + codigoCuenta + ", saldo=" + saldo + ", fechaHoraC=" + fechaHoraC + ", fechaHoraUM=" + fechaHoraUM + '}';
    }
    
    
}
