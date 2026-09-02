import { useState } from "react";
import "./App.css";

function App() {
  // Stanje sa tačnim nazivima polja iz Java modela (ParcelaInput)
  const [formData, setFormData] = useState({
    urbanistickaZona: "CENTAR",
    povrsinaParcele: "",
    zeliLokaleUPrizemlju: false,
    povrsinaLokala: "",
    sirinaPristupneUlice: "",
    parkingNormativ: "1.2",
    preferiraParkingNaTlu: false,
    stabilnostTerena: "STABILAN",
    blizinaJavnogPrevoza: false,
  });

  const [loading, setLoading] = useState(false);
  const [isCalculating, setIsCalculating] = useState(false);
  const [message, setMessage] = useState("");
  const [rezultatGradnje, setRezultatGradnje] = useState(null);

  const NAZIVI_TIPA_GRADNJE = {
    STRIP_MALL: "Tržni centar",
  };

  const handleChange = (e) => {
    const { name, type, checked, value } = e.target;
    setFormData((prevData) => ({
      ...prevData,
      [name]: type === "checkbox" ? checked : value,
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setIsCalculating(true);
    setMessage("");
    setRezultatGradnje(null);

    const parcelaInput = {
      urbanistickaZona: formData.urbanistickaZona,
      povrsinaParcele: Number(formData.povrsinaParcele),
      zeliLokaleUPrizemlju: formData.zeliLokaleUPrizemlju,
      povrsinaLokala: formData.zeliLokaleUPrizemlju
        ? Number(formData.povrsinaLokala)
        : 0.0,
      sirinaPristupneUlice: Number(formData.sirinaPristupneUlice),
      parkingNormativ: Number(formData.parkingNormativ),
      preferiraParkingNaTlu: formData.preferiraParkingNaTlu,
      stabilnostTerena: formData.stabilnostTerena,
      blizinaJavnogPrevoza: formData.blizinaJavnogPrevoza,
      procenatZauzetosti: 0.0,
      indeksIzgradjenosti: 0.0,
    };

    try {
      const [response] = await Promise.all([
        fetch("http://localhost:8080/api/provera", {
          method: "POST",
          headers: { "Content-Type": "application/json; charset=UTF-8" },
          body: JSON.stringify(parcelaInput),
        }),
        new Promise((resolve) => setTimeout(resolve, 1000)),
      ]);

      if (response.ok) {
        const podaci = await response.json();
        setRezultatGradnje(podaci);
        setMessage("Drools je uspešno obradio parcelu!");
      } else {
        const errData = await response.json().catch(() => ({}));
        setMessage(
          `Greška prilikom obrade: ${errData.error || response.statusText}`,
        );
      }
    } catch (error) {
      setMessage(`Greška u povezivanju sa serverom: ${error.message}`);
    } finally {
      setLoading(false);
      setIsCalculating(false);
    }
  };

  return (
    <div
      className={`app-layout-container ${rezultatGradnje || isCalculating ? "has-results" : ""}`}
    >
      {/* LEVA POLOVINA tj FORMA */}
      <div className="panel-form-side">
        <h2>Ekspertski Sistem - Analiza Parcele (ProinvestBG)</h2>

        <form onSubmit={handleSubmit} className="parcela-form">
          <div className="form-group">
            <label>Urbanistička zona parcele:</label>
            <div className="radio-options">
              {["CENTAR", "NOVI_BEOGRAD", "SIRI_CENTAR", "PERIFERIJA"].map(
                (zona) => (
                  <label key={zona} className="radio-label">
                    <input
                      type="radio"
                      name="urbanistickaZona"
                      value={zona}
                      checked={formData.urbanistickaZona === zona}
                      onChange={handleChange}
                    />
                    {zona.replace("_", " ")}
                  </label>
                ),
              )}
            </div>
          </div>

          <div className="form-group">
            <label>Površina parcele (m²):</label>
            <input
              type="number"
              name="povrsinaParcele"
              value={formData.povrsinaParcele}
              onChange={handleChange}
              required
              min="1"
            />
          </div>

          <div className="form-group">
            <label>Širina pristupne ulice (m):</label>
            <input
              type="number"
              step="0.1"
              name="sirinaPristupneUlice"
              value={formData.sirinaPristupneUlice}
              onChange={handleChange}
              required
              min="1"
            />
          </div>

          <div className="form-group">
            <label>Zakonska normativa za parking (mesta po stanu):</label>
            <input
              type="number"
              step="0.1"
              name="parkingNormativ"
              value={formData.parkingNormativ}
              onChange={handleChange}
              required
              min="0.5"
            />
          </div>

          <div className="form-group">
            <label>Konfiguracija i stabilnost terena:</label>
            <select
              name="stabilnostTerena"
              value={formData.stabilnostTerena}
              onChange={handleChange}
              className="form-select"
            >
              <option value="STABILAN">Stabilan ravan teren</option>
              <option value="NAGIB">Teren u nagibu (kaskadna gradnja)</option>
              <option value="KLIZISTE">
                Registrovano / potencijalno klizište
              </option>
            </select>
          </div>

          <div className="checkbox-group">
            <label>
              <input
                type="checkbox"
                name="preferiraParkingNaTlu"
                checked={formData.preferiraParkingNaTlu}
                onChange={handleChange}
              />
              Investitor izričito preferira parking na tlu (izbegavanje podzemne
              garaže)?
            </label>
          </div>

          <div className="checkbox-group">
            <label>
              <input
                type="checkbox"
                name="blizinaJavnogPrevoza"
                checked={formData.blizinaJavnogPrevoza}
                onChange={handleChange}
              />
              Parcela je u blizini stanice javnog prevoza?
            </label>
          </div>

          <div className="checkbox-group">
            <label>
              <input
                type="checkbox"
                name="zeliLokaleUPrizemlju"
                checked={formData.zeliLokaleUPrizemlju}
                onChange={handleChange}
              />
              Da li objekat treba da sadrži poslovne lokale u prizemlju?
            </label>
          </div>

          {formData.zeliLokaleUPrizemlju && (
            <div className="form-group indent">
              <label>Ukupna planirana površina lokala (m²):</label>
              <input
                type="number"
                name="povrsinaLokala"
                value={formData.povrsinaLokala}
                onChange={handleChange}
                required={formData.zeliLokaleUPrizemlju}
                min="1"
              />
            </div>
          )}

          <button type="submit" disabled={loading}>
            {loading ? "Slanje u Drools..." : "Generiši Preporuku Gradnje"}
          </button>
        </form>

        {message && <div className="status-message">{message}</div>}
      </div>

      {/* DESNA POLOVINA*/}
      <div className="panel-results-side">
        {/* Spinner*/}
        {isCalculating && (
          <div className="loading-container-center fade-in">
            <div className="drools-spinner"></div>
            <p>Sistem analizira podatke...</p>
          </div>
        )}

        {/* Prozor sa podacima*/}
        {!isCalculating && rezultatGradnje && (
          <div className="rezultat-kontejner-flat fade-in">
            <h3>Proračun i Preporuka sistema:</h3>
            <div className="rezultat-mreza">
              <p>
                <strong>Tip gradnje:</strong>{" "}
                {NAZIVI_TIPA_GRADNJE[rezultatGradnje.tipGradnje] ||
                  rezultatGradnje.tipGradnje ||
                  "Nije određen"}
              </p>
              <p>
                <strong>Maksimalna površina (BRGP):</strong>{" "}
                {rezultatGradnje.maxPovrsina} m²
              </p>
              <p>
                <strong>Maksimalna osnova zgrade:</strong>{" "}
                {rezultatGradnje.maxOsnova} m²
              </p>
              <p>
                <strong>Preporučena spratnost:</strong> P +{" "}
                {rezultatGradnje.brojSpratova}
              </p>
              <p>
                <strong>Ukupan broj stanova:</strong>{" "}
                {rezultatGradnje.ukupanBrojStanova}
              </p>
              <p>
                <strong>Potreban broj parking mesta:</strong>{" "}
                {rezultatGradnje.ukupanBrojParkingMesta}
              </p>
              <p>
                <strong>Potrebna podzemna garaža:</strong>{" "}
                {rezultatGradnje.potrebnaPodzemnaGaraza ? "DA" : "NE"}
              </p>
              <p>
                <strong>Sistem grejanja:</strong>{" "}
                {rezultatGradnje.grejanje?.replace("_", " ") ||
                  "Nije proračunato"}
              </p>
              <p>
                <strong>Ugradnja lifta:</strong>{" "}
                {rezultatGradnje.potrebanLift ? "OBAVEZNA" : "Nije obavezna"}
              </p>
            </div>

            {rezultatGradnje.napomeneIOgranicenja &&
              rezultatGradnje.napomeneIOgranicenja.length > 0 && (
                <div className="napomene-sekcija">
                  <h4>Urbanističke i tehničke napomene:</h4>
                  <ul>
                    {rezultatGradnje.napomeneIOgranicenja.map(
                      (napomena, index) => (
                        <li key={index}>{napomena}</li>
                      ),
                    )}
                  </ul>
                </div>
              )}
          </div>
        )}
      </div>
    </div>
  );
}

export default App;
