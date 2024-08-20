package FootballApp.enums;

public enum ERegion {

    PREMIER_LEAGUE("Premier League"),
    LA_LIGA("La Liga"),
    BUNDESLIGA("Bundesliga"),
    SERIE_A("Serie A"),
    LIGUE_1("Ligue 1"),
    EREDIVISIE("Eredivisie"),
    PORTUGUESE_PRIMEIRA_LIGA("Primeira Liga"),
    RUSSIAN_PREMIER_LEAGUE("Russian Premier League"),
    TURKISH_LEAGUE("Turkish Super League"),
    SCOTTISH_PREMIER_LEAGUE("Scottish Premier League");

    private final String ligAdi;

    ERegion(String ligAdi) {
        this.ligAdi = ligAdi;
    }
    public String getLigAdi() {
        return ligAdi;
    }
}