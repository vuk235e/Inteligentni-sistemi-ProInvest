package project;

import java.util.ArrayList;
import java.util.List;

public class PreporukaGradnje {
    private String tipGradnje = "Zgrada"; 
    private int brojSpratova;
    private int prosecnaPovrsinaStana;
    private int ukupanBrojStanova;
    private double maxPovrsina;
    private int ukupanBrojParkingMesta;
    private boolean potrebnaPodzemnaGaraza;
    private boolean potrebanLift;
    private String mogucnostLokala;
    private List<String> napomeneIOgranicenja = new ArrayList<>();
    private double maxOsnova;
    private String grejanje; // GRADSKI TOPLOVOD, TOPLOTNE PUMPE, ELEKTRIČNA ENERGIJA, PRIRODNI GAS
    //mogli bismo da dodamo br tipova stanova npr x grasonjera, x jednosobnih, x dvosobnih
    private boolean pogodnaZaIzgradnju = true;

    // Interni markeri za Drools engine (sprečavaju višestruko izvršavanje korekcionih pravila
    // usled ponovnog update() od strane drugih pravila); ne serijalizuju se u JSON odgovor.
    private transient boolean stanoviKorigovaniZbogLokala = false;
    private transient boolean parkingKorigovanZbogLokala = false;
    private transient boolean parkingKorigovanZbogPrevoza = false;

    public PreporukaGradnje() {}

    public boolean isStanoviKorigovaniZbogLokala() {
        return stanoviKorigovaniZbogLokala;
    }

    public void setStanoviKorigovaniZbogLokala(boolean stanoviKorigovaniZbogLokala) {
        this.stanoviKorigovaniZbogLokala = stanoviKorigovaniZbogLokala;
    }

    public boolean isParkingKorigovanZbogLokala() {
        return parkingKorigovanZbogLokala;
    }

    public void setParkingKorigovanZbogLokala(boolean parkingKorigovanZbogLokala) {
        this.parkingKorigovanZbogLokala = parkingKorigovanZbogLokala;
    }

    public boolean isParkingKorigovanZbogPrevoza() {
        return parkingKorigovanZbogPrevoza;
    }

    public void setParkingKorigovanZbogPrevoza(boolean parkingKorigovanZbogPrevoza) {
        this.parkingKorigovanZbogPrevoza = parkingKorigovanZbogPrevoza;
    }

    public boolean isPogodnaZaIzgradnju() {
        return pogodnaZaIzgradnju;
    }

    public void setPogodnaZaIzgradnju(boolean pogodnaZaIzgradnju) {
        this.pogodnaZaIzgradnju = pogodnaZaIzgradnju;
    }
    
    
    
    
	@Override
	public String toString() {
		return "PreporukaGradnje [tipGradnje=" + tipGradnje + ", brojSpratova=" + brojSpratova
				+ ", prosecnaPovrsinaStana=" + prosecnaPovrsinaStana + ", ukupanBrojStanova=" + ukupanBrojStanova
				+ ", maxPovrsina=" + maxPovrsina + ", ukupanBrojParkingMesta=" + ukupanBrojParkingMesta
				+ ", potrebnaPodzemnaGaraza=" + potrebnaPodzemnaGaraza + ", potrebanLift=" + potrebanLift
				+ ", mogucnostLokala=" + mogucnostLokala + ", napomeneIOgranicenja=" + napomeneIOgranicenja
				+ ", maxOsnova=" + maxOsnova + ", grejanje=" + grejanje + "]";
	}




	public String getGrejanje() {
		return grejanje;
	}




	public void setGrejanje(String grejanje) {
		this.grejanje = grejanje;
	}




	public int getProsecnaPovrsinaStana() {
		return prosecnaPovrsinaStana;
	}



	public void setProsecnaPovrsinaStana(int prosecnaPovrsinaStana) {
		this.prosecnaPovrsinaStana = prosecnaPovrsinaStana;
	}


	public int getUkupanBrojStanova() {
		return ukupanBrojStanova;
	}




	public void setUkupanBrojStanova(int ukupanBrojStanova) {
		this.ukupanBrojStanova = ukupanBrojStanova;
	}




	public double getMaxOsnova() {
		return maxOsnova;
	}



	public void setMaxOsnova(double maxOsnova) {
		this.maxOsnova = maxOsnova;
	}



	public void dodajNapomenu(String napomena) {
        this.napomeneIOgranicenja.add(napomena);
    }

	public String getTipGradnje() {
		return tipGradnje;
	}

	public void setTipGradnje(String tipGradnje) {
		this.tipGradnje = tipGradnje;
	}

	public int getBrojSpratova() {
		return brojSpratova;
	}

	public void setBrojSpratova(int brojSpratova) {
		this.brojSpratova = brojSpratova;
	}

	public double getMaxPovrsina() {
		return maxPovrsina;
	}

	public void setMaxPovrsina(double maxPovrsinaBRGP) {
		this.maxPovrsina = maxPovrsinaBRGP;
	}

	public int getUkupanBrojParkingMesta() {
		return ukupanBrojParkingMesta;
	}

	public void setUkupanBrojParkingMesta(int ukupanBrojParkingMesta) {
		this.ukupanBrojParkingMesta = ukupanBrojParkingMesta;
	}

	public boolean isPotrebnaPodzemnaGaraza() {
		return potrebnaPodzemnaGaraza;
	}

	public void setPotrebnaPodzemnaGaraza(boolean potrebnaPodzemnaGaraza) {
		this.potrebnaPodzemnaGaraza = potrebnaPodzemnaGaraza;
	}

	public boolean isPotrebanLift() {
		return potrebanLift;
	}

	public void setPotrebanLift(boolean potrebanLift) {
		this.potrebanLift = potrebanLift;
	}

	public String getMogucnostLokala() {
		return mogucnostLokala;
	}

	public void setMogucnostLokala(String mogucnostLokala) {
		this.mogucnostLokala = mogucnostLokala;
	}

	public List<String> getNapomeneIOgranicenja() {
		return napomeneIOgranicenja;
	}

	public void setNapomeneIOgranicenja(List<String> napomeneIOgranicenja) {
		this.napomeneIOgranicenja = napomeneIOgranicenja;
	}

    
}
