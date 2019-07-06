package ar.edu.itba.webapp.model.DTOs;

import ar.edu.itba.model.Formation;
import ar.edu.itba.model.Player;

import java.util.List;

public class FormationDTO {

    private long id;
    private PlayerDTO gk, lb, lcb, cb, rcb, rb, lm, lcm, cdm, cam, rcm, rm, lw, lf, st, rf, rw;
    private List<Integer> options; //nose si deberia ir en este dto o hacer llamado aparte
    private Integer formation;


    public FormationDTO(){}

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
        this.gk = gk != null? new PlayerDTO(gk) : null;
        this.lb = lb != null? new PlayerDTO(lb) : null;
        this.lcb = lcb != null? new PlayerDTO(lcb) : null;
        this.cb = cb != null? new PlayerDTO(cb) : null;
        this.rcb = rcb != null? new PlayerDTO(rcb) : null;
        this.rb = rb != null? new PlayerDTO(rb) : null;
        this.lm = lm != null? new PlayerDTO(lm) : null;
        this.lcm = lcm != null? new PlayerDTO(lcm) : null;
        this.cdm = cdm != null? new PlayerDTO(cdm) : null;
        this.cam = cam != null? new PlayerDTO(cam) : null;
        this.rcm = rcm != null? new PlayerDTO(rcm) : null;
        this.rm = rm != null? new PlayerDTO(rm) : null;
        this.lw = lw != null? new PlayerDTO(lw) : null;
        this.lf = lf != null? new PlayerDTO(lf) : null;
        this.st = st != null? new PlayerDTO(st) : null;
        this.rf = rf != null? new PlayerDTO(rf) : null;
        this.rw = rw != null? new PlayerDTO(rw) : null;
        this.options = options;
        this.formation = formation;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public PlayerDTO getGk() {
        return gk;
    }

    public void setGk(PlayerDTO gk) {
        this.gk = gk;
    }

    public PlayerDTO getLb() {
        return lb;
    }

    public void setLb(PlayerDTO lb) {
        this.lb = lb;
    }

    public PlayerDTO getLcb() {
        return lcb;
    }

    public void setLcb(PlayerDTO lcb) {
        this.lcb = lcb;
    }

    public PlayerDTO getCb() {
        return cb;
    }

    public void setCb(PlayerDTO cb) {
        this.cb = cb;
    }

    public PlayerDTO getRcb() {
        return rcb;
    }

    public void setRcb(PlayerDTO rcb) {
        this.rcb = rcb;
    }

    public PlayerDTO getRb() {
        return rb;
    }

    public void setRb(PlayerDTO rb) {
        this.rb = rb;
    }

    public PlayerDTO getLm() {
        return lm;
    }

    public void setLm(PlayerDTO lm) {
        this.lm = lm;
    }

    public PlayerDTO getLcm() {
        return lcm;
    }

    public void setLcm(PlayerDTO lcm) {
        this.lcm = lcm;
    }

    public PlayerDTO getCdm() {
        return cdm;
    }

    public void setCdm(PlayerDTO cdm) {
        this.cdm = cdm;
    }

    public PlayerDTO getCam() {
        return cam;
    }

    public void setCam(PlayerDTO cam) {
        this.cam = cam;
    }

    public PlayerDTO getRcm() {
        return rcm;
    }

    public void setRcm(PlayerDTO rcm) {
        this.rcm = rcm;
    }

    public PlayerDTO getRm() {
        return rm;
    }

    public void setRm(PlayerDTO rm) {
        this.rm = rm;
    }

    public PlayerDTO getLw() {
        return lw;
    }

    public void setLw(PlayerDTO lw) {
        this.lw = lw;
    }

    public PlayerDTO getLf() {
        return lf;
    }

    public void setLf(PlayerDTO lf) {
        this.lf = lf;
    }

    public PlayerDTO getSt() {
        return st;
    }

    public void setSt(PlayerDTO st) {
        this.st = st;
    }

    public PlayerDTO getRf() {
        return rf;
    }

    public void setRf(PlayerDTO rf) {
        this.rf = rf;
    }

    public PlayerDTO getRw() {
        return rw;
    }

    public void setRw(PlayerDTO rw) {
        this.rw = rw;
    }

    public List<Integer> getOptions() {
        return options;
    }

    public void setOptions(List<Integer> options) {
        this.options = options;
    }

    public Integer getFormation() {
        return formation;
    }

    public void setFormation(Integer formation) {
        this.formation = formation;
    }
}
