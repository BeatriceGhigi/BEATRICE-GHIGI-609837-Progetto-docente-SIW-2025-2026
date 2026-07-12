// src/components/ClassificaTable.tsx
import type { ClassificaRiga } from "../types/types";

interface ClassificaTableProps {
  righe: ClassificaRiga[];
}

// Componente "dumb": riceve solo props, non fa fetch, non ha stato proprio.
// Tutta la logica di caricamento dati sta nel componente padre (App.tsx).
function ClassificaTable({ righe }: ClassificaTableProps) {
  if (righe.length === 0) {
    return <p className="loading-message">Nessuna squadra iscritta a questo torneo.</p>;
  }

  return (
    <div style={{ overflowX: "auto" }}>
      <table className="classifica-table">
        <thead>
          <tr>
            <th>#</th>
            <th>Squadra</th>
            <th className="numeric-cell">PG</th>
            <th className="numeric-cell">V</th>
            <th className="numeric-cell">S</th>
            <th className="numeric-cell">Punti</th>
          </tr>
        </thead>
        <tbody>
          {righe.map((riga, indice) => (
            <tr key={riga.nomeSquadra || indice}>
              <td style={{ fontWeight: "bold", color: "#7f8c8d", width: "50px" }}>
                {indice + 1}°
              </td>
              <td style={{ fontWeight: 600, color: "#2c3e50" }}>
                {riga.nomeSquadra}
              </td>
              <td className="numeric-cell">{riga.partiteGiocate}</td>
              <td className="numeric-cell">{riga.vittorie}</td>
              <td className="numeric-cell">{riga.sconfitte}</td>
              <td className="numeric-cell" style={{ fontWeight: "bold", color: "#2ecc71" }}>
                {riga.punti}
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default ClassificaTable;