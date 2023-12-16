package example.myapp.model;


import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Set;

@RequestScoped
@Named
@Entity

public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Boolean gfnStatusReported;
    private Boolean gfnStatusRescuePending;
    private Boolean gfnStatusRescued;
    private Boolean gfnStatusLost;

    @OneToOne (fetch = FetchType.EAGER, mappedBy = "status", cascade = CascadeType.ALL)
    private GhostFishingNet ghostFishingNet;

    public Boolean getGfnStatusReported() {
        return gfnStatusReported;
    }

    public void setGfnStatusReported(Boolean gfnStatusReported) {
        this.gfnStatusReported = gfnStatusReported;
    }

    public Boolean getGfnStatusRescuePending() {
        return gfnStatusRescuePending;
    }

    public void setGfnStatusRescuePending(Boolean gfnStatusRescuePending) {
        this.gfnStatusRescuePending = gfnStatusRescuePending;
    }

    public Boolean getGfnStatusRescued() {
        return gfnStatusRescued;
    }

    public void setGfnStatusRescued(Boolean gfnStatusRescued) {
        this.gfnStatusRescued = gfnStatusRescued;
    }

    public Boolean getGfnStatusLost() {
        return gfnStatusLost;
    }

    public void setGfnStatusLost(Boolean gfnStatusLost) {
        this.gfnStatusLost = gfnStatusLost;
    }


    public GhostFishingNet getGhostFishingNet() {
        return ghostFishingNet;
    }

    public void setGhostFishingNet(GhostFishingNet ghostFishingNet) {
        this.ghostFishingNet = ghostFishingNet;
    }
}
