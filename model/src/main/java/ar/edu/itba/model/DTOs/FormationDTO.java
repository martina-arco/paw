package ar.edu.itba.model.DTOs;

import ar.edu.itba.model.Formation;
import ar.edu.itba.model.Player;

import java.util.List;

public class FormationDTO {

    private Long id;
    private Player gk, lb, lcb, cb, rcb, rb, lm, lcm, cdm, cam, rcm, rm, lw, lf, st, rf, rw;
    private List<Integer> options; //nose si deberia ir en este dto o hacer llamado aparte
    private Integer formation;

    //  TODO: de donde saco la formation
    public FormationDTO(Formation formation) {
        this(formation.getId(), formation.getGk(), formation.getLb(), formation.getLcb(), formation.getCb(), formation.getRcb(), formation.getRb(),
            formation.getLm(), formation.getLcm(), formation.getCdm(), formation.getCam(), formation.getRcm(), formation.getRm(),
            formation.getLw(), formation.getLf(), formation.getSt(), formation.getRf(), formation.getRw(), formation.getPossibleFormations(),
            formation.getPossibleFormations().get(0));
    }

    public FormationDTO(Long id, Player gk, Player lb, Player lcb, Player cb, Player rcb, Player rb, Player lm, Player lcm,
                        Player cdm, Player cam, Player rcm, Player rm, Player lw, Player lf, Player st, Player rf,
                        Player rw, List<Integer> options, Integer formation) {
        this.id = id;
        this.gk = gk;
        this.lb = lb;
        this.lcb = lcb;
        this.cb = cb;
        this.rcb = rcb;
        this.rb = rb;
        this.lm = lm;
        this.lcm = lcm;
        this.cdm = cdm;
        this.cam = cam;
        this.rcm = rcm;
        this.rm = rm;
        this.lw = lw;
        this.lf = lf;
        this.st = st;
        this.rf = rf;
        this.rw = rw;
        this.options = options;
        this.formation = formation;
    }

    public Player getGk() {
        return gk;
    }

    public Player getLb() {
        return lb;
    }

    public Player getLcb() {
        return lcb;
    }

    public Player getCb() {
        return cb;
    }

    public Player getRcb() {
        return rcb;
    }

    public Player getRb() {
        return rb;
    }

    public Player getLm() {
        return lm;
    }

    public Player getLcm() {
        return lcm;
    }

    public Player getCdm() {
        return cdm;
    }

    public Player getCam() {
        return cam;
    }

    public Player getRcm() {
        return rcm;
    }

    public Player getRm() {
        return rm;
    }

    public Player getLw() {
        return lw;
    }

    public Player getLf() {
        return lf;
    }

    public Player getSt() {
        return st;
    }

    public Player getRf() {
        return rf;
    }

    public Player getRw() {
        return rw;
    }

    public List<Integer> getOptions() {
        return options;
    }

    public Integer getFormation() {
        return formation;
    }

    public void setGk(Player gk) {
        this.gk = gk;
    }

    public void setLb(Player lb) {
        this.lb = lb;
    }

    public void setLcb(Player lcb) {
        this.lcb = lcb;
    }

    public void setCb(Player cb) {
        this.cb = cb;
    }

    public void setRcb(Player rcb) {
        this.rcb = rcb;
    }

    public void setRb(Player rb) {
        this.rb = rb;
    }

    public void setLm(Player lm) {
        this.lm = lm;
    }

    public void setLcm(Player lcm) {
        this.lcm = lcm;
    }

    public void setCdm(Player cdm) {
        this.cdm = cdm;
    }

    public void setCam(Player cam) {
        this.cam = cam;
    }

    public void setRcm(Player rcm) {
        this.rcm = rcm;
    }

    public void setRm(Player rm) {
        this.rm = rm;
    }

    public void setLw(Player lw) {
        this.lw = lw;
    }

    public void setLf(Player lf) {
        this.lf = lf;
    }

    public void setSt(Player st) {
        this.st = st;
    }

    public void setRf(Player rf) {
        this.rf = rf;
    }

    public void setRw(Player rw) {
        this.rw = rw;
    }

    public void setOptions(List<Integer> options) {
        this.options = options;
    }

    public void setFormation(Integer formation) {
        this.formation = formation;
    }
}
