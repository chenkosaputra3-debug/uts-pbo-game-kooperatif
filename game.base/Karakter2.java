package game.base;
class Karakter2 extends Karakter {
    private int totalMusuhDiinjak;
    private int jarakTempuhLari;

    public Karakter2(String nama) {
        super(nama, "Runner & Striker (Karakter 2)");
        this.totalMusuhDiinjak = 0;
        this.jarakTempuhLari = 0;
    }

    @Override
    public void tampilkanStatus() {
        System.out.println("â”Œâ”€â”€ [STATUS KARAKTER 2 : " + getNama() + "]");
        System.out.println("â”‚ Role            : " + getRole());
        System.out.println("â”‚ Koordinat (X,Y) : (" + posisiX + ", " + posisiY + ") | Jarak Tempuh: " + jarakTempuhLari + "m");
        System.out.println("â”‚ Stamina & Tali  : Stamina " + stamina + "% | Ketegangan Tali: " + keteganganTali + "%");
        System.out.println("â”‚ Statistik Aksi  : Musuh Diinjak " + totalMusuhDiinjak + " ekor | Status: " + statusAksi);
        System.out.println("â””â”€â”€ Skor Tim      : " + skorTim + " Poin");
    }

    public void lari() {
        this.posisiX += 3;
        this.jarakTempuhLari += 3;
        setStamina(this.stamina - 4);
        this.statusAksi = "Berlari Maju (+3m)";

        System.out.println("\n[AKSI P2] " + getNama() + " berlari maju 3 meter ke depan!");

        if (partner != null) {
            partner.setPosisiX(this.posisiX); 
            partner.setStamina(partner.getStamina() - 2);
            int tegangBaru = this.keteganganTali + 10;
            this.setKeteganganTali(tegangBaru);
            partner.setKeteganganTali(tegangBaru);
            partner.setStatusAksi("Ikut tertarik maju ke X=" + this.posisiX + " oleh " + getNama());
            System.out.println("   â””â”€â”€> [EFEK IKATAN KE P1] " + partner.getNama() + " tertarik maju ke posisi X=" + partner.getPosisiX() + "!");
        }
    }

    public void lari(int jarakMeter) {
        this.posisiX += jarakMeter;
        this.jarakTempuhLari += jarakMeter;
        setStamina(this.stamina - (jarakMeter * 2));
        this.statusAksi = "Sprint Cepat (+" + jarakMeter + "m)";

        System.out.println("\n[AKSI P2] " + getNama() + " melakukan SPRINT sejauh " + jarakMeter + " meter mendekati gerombolan musuh!");

        if (partner != null) {
            partner.setPosisiX(this.posisiX);
            partner.setStamina(partner.getStamina() - jarakMeter);
            int tegangBaru = this.keteganganTali + (jarakMeter * 3);
            this.setKeteganganTali(tegangBaru);
            partner.setKeteganganTali(tegangBaru);
            partner.setStatusAksi("Terseret Sprint cepat ke X=" + this.posisiX);
            System.out.println("   â””â”€â”€> [EFEK IKATAN KE P1] " + partner.getNama() + " terseret tali hingga ke posisi X=" + partner.getPosisiX() + " (Ketegangan Tali: " + this.keteganganTali + "%)");
        }
    }


    public void injakMusuh() {
        System.out.println("\n[AKSI P2] " + getNama() + " mencoba menginjak 1 musuh!");

        if (this.posisiY <= 0) {
            System.out.println("   â””â”€â”€> [PERINGATAN] Posisi Y masih 0! Tapi " + getNama() + " tetap menginjak musuh darat (Skor standar +50).");
            this.totalMusuhDiinjak += 1;
            setStamina(this.stamina - 6);
            tambahSkorBersama(50);
        } else {
            System.out.println("   â””â”€â”€> [SERANGAN UDARA SUKSES] Karena sudah ditarik loncat oleh " + partner.getNama() + ", serangan udara menghasilkan Critical (+100 Skor)!");
            this.totalMusuhDiinjak += 1;
            setStamina(this.stamina - 4);
            tambahSkorBersama(100);
        }

        this.statusAksi = "Selesai menginjak 1 musuh";
        if (partner != null) {
            partner.setStatusAksi("Mendapat tambahan skor dari serangan " + getNama());
        }
    }

    public void injakMusuh(int kali) {
        System.out.println("\n[AKSI P2] " + getNama() + " mengeksekusi COMBO INJAK MUSUH sebanyak " + kali + " kali berturut-turut!");

        if (this.posisiY > 0) {
            int bonusSkor = kali * 150; 
            this.totalMusuhDiinjak += kali;
            setStamina(this.stamina - (5 * kali));
            tambahSkorBersama(bonusSkor);
            this.statusAksi = "Airborne Combo Stomp (" + kali + "x Musuh Rata!)";

            System.out.println("   â””â”€â”€> [SYNERGY COMBO BERHASIL!] Berkat bantuan loncatan " + partner.getNama() + " (Y=" + this.posisiY + "), " + getNama() + " memantul di atas " + kali + " kepala musuh!");

            if (partner != null) {
                this.setKeteganganTali(this.keteganganTali - 10);
                partner.setKeteganganTali(this.keteganganTali);
                partner.setStatusAksi("Menahan tali pantulan serangan (" + kali + "x Combo) | +" + bonusSkor + " Poin!");
                System.out.println("   â””â”€â”€> [EFEK IKATAN KE P1] Skor kedua karakter melonjak +" + bonusSkor + " Poin (Total Skor Tim: " + this.skorTim + ")!");
            }
        } else {
            int skorBiasa = kali * 50;
            this.totalMusuhDiinjak += kali;
            setStamina(this.stamina - (8 * kali));
            tambahSkorBersama(skorBiasa);
            System.out.println("   â””â”€â”€> [KURANG KOORDINASI] " + getNama() + " menginjak " + kali + " musuh tanpa bantuan loncatan P1. Stamina terkuras lebih banyak!");
        }
    }

    public int getTotalMusuhDiinjak() { return totalMusuhDiinjak; }
    public int getJarakTempuhLari() { return jarakTempuhLari; }
}
