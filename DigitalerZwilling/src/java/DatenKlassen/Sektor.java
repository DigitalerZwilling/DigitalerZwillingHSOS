/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatenKlassen;


import java.time.LocalTime;
import java.util.List;

/**
 *
 * @author user
 */
public class Sektor extends Element{

    private int stoerung;
    private int x;
    private int y;
    private int z;
    private int ausrichtung;
    private List<Long> warentraegerIDs;
    private List<Long> hubpodestIDs;
    private List<Long> hubquerpodestIDs;
    private List<Long> roboterIDs;
    private List<Long> sensorIDs;
    private List<Long> vorTransportbandIDs;
    private List<Long> nachTransportbandIDs;

    public Sektor(int stoerung, int x, int y, int z, int ausrichtung,  Long id, String bezeichnung, String user_Parameter, LocalTime zeitstempel) {
        super(id, bezeichnung, user_Parameter, zeitstempel);
        this.stoerung = stoerung;
        this.x = x;
        this.y = y;
        this.z = z;
        this.ausrichtung = ausrichtung;
        
    }

    

    public int getStoerung() {
        return stoerung;
    }

    public void setStoerung(int stoerung) {
        this.stoerung = stoerung;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public int getAusrichtung() {
        return ausrichtung;
    }

    public void setAusrichtung(int ausrichtung) {
        this.ausrichtung = ausrichtung;
    }

    public List<Long> getWarentraegerIDs() {
        return warentraegerIDs;
    }

    public void setWarentraegerIDs(List<Long> warentraegerIDs) {
        this.warentraegerIDs = warentraegerIDs;
    }

    public List<Long> getHubpodestIDs() {
        return hubpodestIDs;
    }

    public void setHubpodestIDs(List<Long> hubpodestIDs) {
        this.hubpodestIDs = hubpodestIDs;
    }

    public List<Long> getHubquerpodestIDs() {
        return hubquerpodestIDs;
    }

    public void setHubquerpodestIDs(List<Long> hubquerpodestIDs) {
        this.hubquerpodestIDs = hubquerpodestIDs;
    }

    public List<Long> getRoboterIDs() {
        return roboterIDs;
    }

    public void setRoboterIDs(List<Long> roboterIDs) {
        this.roboterIDs = roboterIDs;
    }

    public List<Long> getSensorIDs() {
        return sensorIDs;
    }

    public void setSensorIDs(List<Long> sensorIDs) {
        this.sensorIDs = sensorIDs;
    }

    public List<Long> getVorTransportbandIDs() {
        return vorTransportbandIDs;
    }

    public void setVorTransportbandIDs(List<Long> vorTransportbandIDs) {
        this.vorTransportbandIDs = vorTransportbandIDs;
    }

    public List<Long> getNachTransportbandIDs() {
        return nachTransportbandIDs;
    }

    public void setNachTransportbandIDs(List<Long> nachTransportbandIDs) {
        this.nachTransportbandIDs = nachTransportbandIDs;
    }
    
    
    

    
    
}
