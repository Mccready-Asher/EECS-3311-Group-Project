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


    /**
     * Get the reference date.
     * 
     * @return the reference date.
    */
    public String getRefDate() {
        return refDate;
    }

    /**
     *Set the reference date.
     *
     *@param refDate the reference date to set for the row data.
    */
    public void setRefDate(String refDate) {
        this.refDate = refDate;
    }

    /**
     *Get the geographical location.
     *
     *@return the geographical location.
    */
    public String getGeo() {
        return geo;
    }


    /**
     *Set the geographical location.
     *
     *@param geo the geographical location to set for the row data.
    */
    public void setGeo(String geo) {
        this.geo = geo;
    }

    /**
     * Get the data guide.
     * 
     * @return the data guide.
    */
    public String getDguid() {
        return dguid;
    }

    /**
     *Set the data guide.
     *
     *@param dguid the data guide to set for the row data.
    */
    public void setDguid(String dguid) {
        this.dguid = dguid;
    }



    /**
     *Get the new housing price indexes.
     *
     *@return the new housing price indexes.
    */
    public String getNewHousingPriceIndexes() {
        return newHousingPriceIndexes;
    }


    /**
    *Set the new housing price indexes.
    *
    *@param newHousingPriceIndexes the new housing price indexes to set for the row data.
    */
    public void setNewHousingPriceIndexes(String newHousingPriceIndexes) {
        this.newHousingPriceIndexes = newHousingPriceIndexes;
    }


    /**
    *Get the unit of measurement.
    *
    *@return the unit of measurement.
    */
    public String getUom() {
        return uom;
    }


    /**
    *Set the unit of measurement.
    *
    *@param uom the unit of measurement to set for the row data.
    */
    public void setUom(String uom) {
        this.uom = uom;
    }


    /**
    *Get the unit of measurement ID.
    *
    *@return the unit of measurement ID.
    */
    public int getUomId() {
        return uomId;
    }


    /**
    *Set the unit of measurement ID.
    *
    *@param uomId the unit of measurement ID to set for the row data.
    */
    public void setUomId(int uomId) {
        this.uomId = uomId;
    }


    /**
    *Get the scalar factor.
    *
    *@return the scalar factor.
    */
    public String getScalarFactor() {
        return scalarFactor;
    }

    /**
    *Set the scalar factor.
    *
    *@param scalarFactor the scalar factor to set for the row data.
    */
    public void setScalarFactor(String scalarFactor) {
        this.scalarFactor = scalarFactor;
    }

    /**
    *Get the scalar ID.
    *
    *@return the scalar ID.
    */
    public String getScalarId() {
        return scalarId;
    }

    /**
    *Set the scalar ID.
    *
    *@param scalarId the scalar ID to set for the row data.
    */
    public void setScalarId(String scalarId) {
        this.scalarId = scalarId;
    }


    /**
    *Get the vector.
    *
    *@return the vector.
    */
    public String getVector() {
        return vector;
    }


    /**
    *Set the vector.
    *
    *@param vector the vector to set for the row data.
    */
    public void setVector(String vector) {
        this.vector = vector;
    }


    /**
    *Get the coordinate.
    *
    *@return the coordinate.
    */
    public double getCoordinate() {
        return coordinate;
    }

    /**
    *Set the coordinate.
    *
    *@param coordinate the coordinate to set for the row data.
    */
    public void setCoordinate(double coordinate) {
        this.coordinate = coordinate;
    }


    /**
     *Gets the current value.
     *
     *@return The current value.
    */
    public String getValue() {
        return value;
    }

    /**
     *Sets the value.
     *
     *@param value The new value to set.
    */
    public void setValue(String value) {
        this.value = value;
    }
    
    /**
     *Sets the value to a double, converting it to a String.
     *
     *@param value The new value to set.
    */
    public void setValue(double value) {
    	this.value = String.valueOf(value);
    }

    /**
     *Gets the current status.
     *
     *@return The current status.
    */
    public String getStatus() {
        return status;
    }

    /**
     *Sets the status.
     *
     *@param status The new status to set.
    */
    public void setStatus(String status) {
        this.status = status;
    }
    /**
     *Gets the current symbol.
     *
     *@return The current symbol.
    */
    public String getSymbol() {
        return symbol;
    }

    /**
     *Sets the symbol.
     *
     *@param symbol The new symbol to set.
    */
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }


    /**
     *Gets the current terminated status.
     *
     *@return The current terminated status.
    */
    public String getTerminated() {
        return terminated;
    }


    /**
     *Sets the terminated status.
     *
     *@param terminated The new terminated status to set.
    */
    public void setTerminated(String terminated) {
        this.terminated = terminated;
    }


    /**
     *Gets the current decimals.
     *
     *@return The current decimals.
    */
    public String getDecimals() {
        return decimals;
    }


    /**
     *Sets the decimals.
     *
     *@param decimals The new decimals to set.
    */
    public void setDecimals(String decimals) {
        this.decimals = decimals;
    }
}
