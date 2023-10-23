package example.myapp;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@RequestScoped
@Named

public class Status {
    private Boolean gfnStatusReported;
    private Boolean gfnStatusRescuePending;
    private Boolean gfnStatusRescued;
    private Boolean gfnStatusLost;

   /* @Inject
    public com.example.myapp.Status(Boolean gfnStatusReported, Boolean gfnStatusRescuePending, Boolean gfnStatusRescued, Boolean gfnStatusLost) {
        this.gfnStatusReported = gfnStatusReported;
        this.gfnStatusRescuePending = gfnStatusRescuePending;
        this.gfnStatusRescued = gfnStatusRescued;
        this.gfnStatusLost = gfnStatusLost;
    }

    */

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
}
