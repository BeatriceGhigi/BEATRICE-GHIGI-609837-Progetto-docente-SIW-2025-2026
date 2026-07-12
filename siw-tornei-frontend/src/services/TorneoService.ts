// src/services/TorneoService.ts
import api from "./api"; // Importa l'istanza Axios che mi hai appena mostrato
import type { ClassificaRiga, TorneoSummary } from "../types/types";

// 1. Carica l'elenco dei tornei per la select
export async function getTornei(): Promise<TorneoSummary[]> {
  // Axios mette la risposta dentro l'oggetto .data automaticamente
  const response = await api.get<TorneoSummary[]>("/tornei");
  return response.data;
}

// 2. Carica la classifica in base al tuo ClassificaDto (5 campi)
export async function getClassifica(torneoId: number): Promise<ClassificaRiga[]> {
  const response = await api.get<ClassificaRiga[]>(`/tornei/${torneoId}/classifica`);
  return response.data;
}