package org.projet.terainservice.dtos;

public class TerrainAssignmentEvent {

    private int terrainId;
    private int centreId;

    public TerrainAssignmentEvent() {}

    public TerrainAssignmentEvent(int terrainId, int centreId) {
        this.terrainId = terrainId;
        this.centreId = centreId;
    }

    public int getTerrainId() {
        return terrainId;
    }

    public void setTerrainId(int terrainId) {
        this.terrainId = terrainId;
    }

    public int getCentreId() {
        return centreId;
    }

    public void setCentreId(int centreId) {
        this.centreId = centreId;
    }
}

