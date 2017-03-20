package mathieu.com.outerspacemanager.outerspacemanager.classObjet;

/**
 * Created by Piou on 20/03/2017.
 */

public class AttackResponse {


    private long attackTime;
    private String fleetSend;

    public AttackResponse(long attackTime, String fleetSend) {
        this.attackTime = attackTime;
        this.fleetSend = fleetSend;
    }
    public AttackResponse() {

    }
    public long getAttackTime() {
        return attackTime;
    }

    public void setAttackTime(long attackTime) {
        this.attackTime = attackTime;
    }

    public String getFleetSend() {
        return fleetSend;
    }

    public void setFleetSend(String fleetSend) {
        this.fleetSend = fleetSend;
    }
}
