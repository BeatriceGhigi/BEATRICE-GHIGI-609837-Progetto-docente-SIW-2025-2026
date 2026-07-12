import { useEffect, useState } from "react";
import "./App.css"; 
import ClassificaTable from "./components/ClassificaTable";
import { getClassifica, getTornei } from "./services/TorneoService";
import type { ClassificaRiga, TorneoSummary } from "./types/types"; 

function App() {
  const [tornei, setTornei] = useState<TorneoSummary[]>([]);
  const [torneoSelezionato, setTorneoSelezionato] = useState<number | null>(null);
  const [classifica, setClassifica] = useState<ClassificaRiga[]>([]);
  const [caricamentoTornei, setCaricamentoTornei] = useState(true);
  const [caricamentoClassifica, setCaricamentoClassifica] = useState(false);
  const [errore, setErrore] = useState<string | null>(null);

  useEffect(() => {
    getTornei()
      .then((dati) => {
        setTornei(dati);
        setCaricamentoTornei(false);
        if (dati.length > 0) {
          setTorneoSelezionato(dati[0].id);
        }
      })
      .catch((err) => {
        setErrore("Errore nel caricamento dei tornei: " + err.message);
        setCaricamentoTornei(false);
      });
  }, []);

  useEffect(() => {
    if (torneoSelezionato === null) {
      return;
    }

    setCaricamentoClassifica(true);
    setErrore(null);

    getClassifica(torneoSelezionato)
      .then((dati) => {
        setClassifica(dati);
        setCaricamentoClassifica(false);
      })
      .catch((err) => {
        setErrore("Errore nel caricamento della classifica: " + err.message);
        setCaricamentoClassifica(false);
      });
  }, [torneoSelezionato]);

  if (caricamentoTornei) {
    return (
      <div className="app-container">
        <p className="loading-message">Caricamento tornei in corso...</p>
      </div>
    );
  }

  return (
    <div className="app-container">
      <h1 className="app-title">Classifica del Torneo</h1>

      <div className="selection-card">
        <label htmlFor="torneo-select" className="selection-label">
          Scegli un torneo:
        </label>
        <select
          id="torneo-select"
          className="selection-select"
          value={torneoSelezionato ?? ""}
          onChange={(e) => setTorneoSelezionato(Number(e.target.value))}
        >
          {tornei.map((torneo) => (
            <option key={torneo.id} value={torneo.id}>
              {torneo.nome} ({torneo.anno})
            </option>
          ))}
        </select>
      </div>

      {errore && <p className="error-message">{errore}</p>}

      <div className="classifica-container">
        {caricamentoClassifica ? (
          <p className="loading-message">Aggiornamento della classifica...</p>
        ) : (
          <ClassificaTable righe={classifica} />
        )}
      </div>
    </div>
  );
}

export default App;