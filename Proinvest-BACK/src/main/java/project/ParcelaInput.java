package project;

public class ParcelaInput {

	private String urbanistickaZona; // "CENTAR", "NOVI_BEOGRAD", "SIRI_CENTAR", "PERIFERIJA"
    private double povrsinaParcele;
    private boolean zeliLokaleUPrizemlju;
    private double povrsinaLokala;
    private double sirinaPristupneUlice;
    private double parkingNormativ;
    private boolean preferiraParkingNaTlu;
    private String stabilnostTerena; // "STABILAN", "NAGIB", "KLIZISTE"
    private boolean blizinaJavnogPrevoza;

    private double procenatZauzetosti;
    private double indeksIzgradjenosti;

    public ParcelaInput() {}

	public String getUrbanistickaZona() {
		return urbanistickaZona;
	}

	public void setUrbanistickaZona(String urbanistickaZona) {
		this.urbanistickaZona = urbanistickaZona;
	}

	public double getPovrsinaParcele() {
		return povrsinaParcele;
	}

	public void setPovrsinaParcele(double povrsinaParcele) {
		this.povrsinaParcele = povrsinaParcele;
	}


	public boolean isZeliLokaleUPrizemlju() {
		return zeliLokaleUPrizemlju;
	}

	public void setZeliLokaleUPrizemlju(boolean zeliLokaleUPrizemlju) {
		this.zeliLokaleUPrizemlju = zeliLokaleUPrizemlju;
	}

	public double getPovrsinaLokala() {
		return povrsinaLokala;
	}

	public void setPovrsinaLokala(double povrsinaLokala) {
		this.povrsinaLokala = povrsinaLokala;
	}

	public double getSirinaPristupneUlice() {
		return sirinaPristupneUlice;
	}

	public void setSirinaPristupneUlice(double sirinaPristupneUlice) {
		this.sirinaPristupneUlice = sirinaPristupneUlice;
	}

	public double getParkingNormativ() {
		return parkingNormativ;
	}

	public void setParkingNormativ(double parkingNormativ) {
		this.parkingNormativ = parkingNormativ;
	}

	public boolean isPreferiraParkingNaTlu() {
		return preferiraParkingNaTlu;
	}

	public void setPreferiraParkingNaTlu(boolean preferiraParkingNaTlu) {
		this.preferiraParkingNaTlu = preferiraParkingNaTlu;
	}

	public String getStabilnostTerena() {
		return stabilnostTerena;
	}

	public void setStabilnostTerena(String stabilnostTerena) {
		this.stabilnostTerena = stabilnostTerena;
	}

	public boolean isBlizinaJavnogPrevoza() {
		return blizinaJavnogPrevoza;
	}

	public void setBlizinaJavnogPrevoza(boolean blizinaJavnogPrevoza) {
		this.blizinaJavnogPrevoza = blizinaJavnogPrevoza;
	}

	public double getProcenatZauzetosti() {
		return procenatZauzetosti;
	}

	public void setProcenatZauzetosti(double procenatZauzetosti) {
		this.procenatZauzetosti = procenatZauzetosti;
	}

	public double getIndeksIzgradjenosti() {
		return indeksIzgradjenosti;
	}

	public void setIndeksIzgradjenosti(double indeksIzgradjenosti) {
		this.indeksIzgradjenosti = indeksIzgradjenosti;
	}
    
    
}
