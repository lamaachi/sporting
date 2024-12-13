//package org.projet.centreservice.dtos;
//
//import com.fasterxml.jackson.annotation.JsonProperty;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.io.Serializable;
//
///**
// * @author lamaachi
// **/
//
//public class TerrainAssignmentEvent implements Serializable {
//    @JsonProperty("terrainId")
//    private int terrainId;
//
//    @JsonProperty("centreId")
//    private int centreId;
//
//    public TerrainAssignmentEvent(){}
//
//    public TerrainAssignmentEvent(int terrainId, int centreId) {
//        this.terrainId = terrainId;
//        this.centreId = centreId;
//    }
//
//    public int getTerrainId() {
//        return terrainId;
//    }
//
//    public void setTerrainId(int terrainId) {
//        this.terrainId = terrainId;
//    }
//
//    public int getCentreId() {
//        return centreId;
//    }
//
//    public void setCentreId(int centreId) {
//        this.centreId = centreId;
//    }
//}
