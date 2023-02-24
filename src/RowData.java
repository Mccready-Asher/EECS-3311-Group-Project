public class RowData {
    private String refDate;
    private String geo;
    private String dguid;
    private String newHousingPriceIndexes;
    private String uom;
    private int uomId;
    private String scalarFactor;
    private String scalarId;
    private String vector;
    private double coordinate;
    private String value;
    private String status;
    private String symbol;
    private String terminated;
    private String decimals;

    public String getRefDate() {
        return refDate;
    }

    public void setRefDate(String refDate) {
        this.refDate = refDate;
    }

    public String getGeo() {
        return geo;
    }

    public void setGeo(String geo) {
        this.geo = geo;
    }

    public String getDguid() {
        return dguid;
    }

    public void setDguid(String dguid) {
        this.dguid = dguid;
    }

    public String getNewHousingPriceIndexes() {
        return newHousingPriceIndexes;
    }

    public void setNewHousingPriceIndexes(String newHousingPriceIndexes) {
        this.newHousingPriceIndexes = newHousingPriceIndexes;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public int getUomId() {
        return uomId;
    }

    public void setUomId(int uomId) {
        this.uomId = uomId;
    }

    public String getScalarFactor() {
        return scalarFactor;
    }

    public void setScalarFactor(String scalarFactor) {
        this.scalarFactor = scalarFactor;
    }

    public String getScalarId() {
        return scalarId;
    }

    public void setScalarId(String scalarId) {
        this.scalarId = scalarId;
    }

    public String getVector() {
        return vector;
    }

    public void setVector(String vector) {
        this.vector = vector;
    }

    public double getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(double coordinate) {
        this.coordinate = coordinate;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    public void setValue(double value) {
    	this.value = String.valueOf(value);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getTerminated() {
        return terminated;
    }

    public void setTerminated(String terminated) {
        this.terminated = terminated;
    }

    public String getDecimals() {
        return decimals;
    }

    public void setDecimals(String decimals) {
        this.decimals = decimals;
    }
}
