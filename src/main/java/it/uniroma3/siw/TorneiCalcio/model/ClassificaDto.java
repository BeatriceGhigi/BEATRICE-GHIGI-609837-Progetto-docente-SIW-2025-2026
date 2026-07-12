package it.uniroma3.siw.TorneiCalcio.model;

public class ClassificaDto {
    private String nomeSquadra;
    private int partiteGiocate;
    private int vittorie;
    private int sconfitte;
    private int punti;

    public ClassificaDto(String nomeSquadra, int partiteGiocate, int vittorie, int sconfitte, int punti) {
        this.nomeSquadra = nomeSquadra;
        this.partiteGiocate = partiteGiocate;
        this.vittorie = vittorie;
        this.sconfitte = sconfitte;
        this.punti = punti;
    }

    // Getter e Setter
    public String getNomeSquadra() { return nomeSquadra; }
    public void setNomeSquadra(String nomeSquadra) { this.nomeSquadra = nomeSquadra; }

    public int getPartiteGiocate() { return partiteGiocate; }
    public void setPartiteGiocate(int partiteGiocate) { this.partiteGiocate = partiteGiocate; }

    public int getVittorie() { return vittorie; }
    public void setVittorie(int vittorie) { this.vittorie = vittorie; }

    public int getSconfitte() { return sconfitte; }
    public void setSconfitte(int sconfitte) { this.sconfitte = sconfitte; }

    public int getPunti() { return punti; }
    public void setPunti(int punti) { this.punti = punti; }
}