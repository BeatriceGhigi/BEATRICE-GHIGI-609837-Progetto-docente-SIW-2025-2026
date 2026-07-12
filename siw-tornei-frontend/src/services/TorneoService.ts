import api from "./api"; 
import type { ClassificaRiga, TorneoSummary } from "../types/types";


export async function getTornei(): Promise<TorneoSummary[]> {
  const response = await api.get<TorneoSummary[]>("/tornei");
  return response.data;
}


export async function getClassifica(torneoId: number): Promise<ClassificaRiga[]> {
  const response = await api.get<ClassificaRiga[]>(`/tornei/${torneoId}/classifica`);
  return response.data;
}