package team.com.ibus.Dominio;

/**
 * Created by User on 17/11/2016.
 */

public class Posicao {

    private Integer id;
	private Double latitude;
    private Double longitude;

    public Posicao(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
