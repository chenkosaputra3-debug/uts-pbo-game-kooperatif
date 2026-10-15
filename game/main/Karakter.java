package game.main;

public class Karakter {
    private String nama;
    private String role;
    protected int posisiX;
    protected int posisiY;
    protected int stamina;
    protected int keteganganTali;
    protected int skorTim;
    protected String statusAksi;
    protected Karakter partner;

    public Karakter(String nama, String role) {
        this.nama = nama;
        this.role = role;
        this.posisiX = 0;
        this.posisiY = 0;
        this.stamina = 100;
        this.keteganganTali = 10;
        this.skorTim = 0;
        this.statusAksi = "Standby";
    }

    public void hubungkanTali(Karakter partnerBaru) {
        this.partner = partnerBaru;
        if (partnerBaru.getPartner() != this) {
            partnerBaru.hubungkanTali(this);
        }
    }

    public void tampilkanStatus() {
        System.out.println("Karakter: " + nama + " (" + role + ") | Posisi: (" + posisiX + "," + posisiY + ")");
    }

    protected void tambahSkorBersama(int poin) {
        this.skorTim += poin;
        if (this.partner != null) {
            this.partner.skorTim = this.skorTim;
        }
    }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public int getPosisiX() { return posisiX; }
    public void setPosisiX(int posisiX) { this.posisiX = posisiX; }
    public int getPosisiY() { return posisiY; }
    public void setPosisiY(int posisiY) { this.posisiY = posisiY; }
    public int getStamina() { return stamina; }
    public void setStamina(int stamina) { this.stamina = Math.max(0, Math.min(100, stamina)); }
    public int getKeteganganTali() { return keteganganTali; }
    public void setKeteganganTali(int keteganganTali) { this.keteganganTali = Math.max(0, Math.min(100, keteganganTali)); }
    public int getSkorTim() { return skorTim; }
    public void setSkorTim(int skorTim) { this.skorTim = skorTim; }
    public String getStatusAksi() { return statusAksi; }
    public void setStatusAksi(String statusAksi) { this.statusAksi = statusAksi; }
    public Karakter getPartner() { return partner; }
}