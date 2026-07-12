import api from "./api"; // Assicurati che api.ts abbia baseURL: "http://localhost:8080/rest"
import type { ClassificaRiga, TorneoSummary } from "../types/types";

// Chiama il metodo list() -> /rest/tornei
export async function getTornei(): Promise<TorneoSummary[]> {
  const response = await api.get<TorneoSummary[]>("/tornei");
  return response.data;
}

// Chiama il metodo classifica() -> /rest/tornei/{id}/classifica
export async function getClassifica(torneoId: number): Promise<ClassificaRiga[]> {
  const response = await api.get<ClassificaRiga[]>(`/tornei/${torneoId}/classifica`);
  return response.data;
}