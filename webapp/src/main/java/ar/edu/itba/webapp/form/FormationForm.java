package ar.edu.itba.webapp.form;

import ar.edu.itba.model.Player;
import ar.edu.itba.model.utils.Point;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FormationForm {
    private long goalkeeper;
    private long leftBack, leftCenterBack, centerBack, rightCenterBack, rightBack;
    private long leftMid, leftCenterMid, centerDefensiveMid, centerAttackingMid, rightCenterMid, rightMid;
    private long leftWing, leftForward, striker, rightForward, rightWing;
    private long substitute1, substitute2, substitute3, substitute4, substitute5, substitute6, substitute7;
    private long captain, freeKickTaker, penaltyTaker;
    private int formation, pressure, attitude;

    public long getGoalkeeper() {
        return goalkeeper;
    }

    public void setGoalkeeper(long goalkeeper) {
        this.goalkeeper = goalkeeper;
    }

    public long getLeftBack() {
        return leftBack;
    }

    public void setLeftBack(long leftBack) {
        this.leftBack = leftBack;
    }

    public long getLeftCenterBack() {
        return leftCenterBack;
    }

    public void setLeftCenterBack(long leftCenterBack) {
        this.leftCenterBack = leftCenterBack;
    }

    public long getRightCenterBack() {
        return rightCenterBack;
    }

    public void setRightCenterBack(long rightCenterBack) {
        this.rightCenterBack = rightCenterBack;
    }

    public long getRightBack() {
        return rightBack;
    }

    public void setRightBack(long rightBack) {
        this.rightBack = rightBack;
    }

    public long getLeftMid() {
        return leftMid;
    }

    public void setLeftMid(long leftMid) {
        this.leftMid = leftMid;
    }

    public long getLeftCenterMid() {
        return leftCenterMid;
    }

    public void setLeftCenterMid(long leftCenterMid) {
        this.leftCenterMid = leftCenterMid;
    }

    public long getRightCenterMid() {
        return rightCenterMid;
    }

    public void setRightCenterMid(long rightCenterMid) {
        this.rightCenterMid = rightCenterMid;
    }

    public long getRightMid() {
        return rightMid;
    }

    public void setRightMid(long rightMid) {
        this.rightMid = rightMid;
    }

    public long getLeftForward() {
        return leftForward;
    }

    public void setLeftForward(long leftForward) {
        this.leftForward = leftForward;
    }

    public long getRightForward() {
        return rightForward;
    }

    public void setRightForward(long rightForward) {
        this.rightForward = rightForward;
    }

    public long getCenterBack() {
        return centerBack;
    }

    public void setCenterBack(long centerBack) {
        this.centerBack = centerBack;
    }

    public long getCenterDefensiveMid() {
        return centerDefensiveMid;
    }

    public void setCenterDefensiveMid(long centerDefensiveMid) {
        this.centerDefensiveMid = centerDefensiveMid;
    }

    public long getCenterAttackingMid() {
        return centerAttackingMid;
    }

    public void setCenterAttackingMid(long centerAttackingMid) {
        this.centerAttackingMid = centerAttackingMid;
    }

    public long getLeftWing() {
        return leftWing;
    }

    public void setLeftWing(long leftWing) {
        this.leftWing = leftWing;
    }

    public long getStriker() {
        return striker;
    }

    public void setStriker(long striker) {
        this.striker = striker;
    }

    public long getRightWing() {
        return rightWing;
    }

    public void setRightWing(long rightWing) {
        this.rightWing = rightWing;
    }

    public long getSubstitute1() {
        return substitute1;
    }

    public void setSubstitute1(long substitute1) {
        this.substitute1 = substitute1;
    }

    public long getSubstitute2() {
        return substitute2;
    }

    public void setSubstitute2(long substitute2) {
        this.substitute2 = substitute2;
    }

    public long getSubstitute3() {
        return substitute3;
    }

    public void setSubstitute3(long substitute3) {
        this.substitute3 = substitute3;
    }

    public long getSubstitute4() {
        return substitute4;
    }

    public void setSubstitute4(long substitute4) {
        this.substitute4 = substitute4;
    }

    public long getSubstitute5() {
        return substitute5;
    }

    public void setSubstitute5(long substitute5) {
        this.substitute5 = substitute5;
    }

    public long getSubstitute6() {
        return substitute6;
    }

    public void setSubstitute6(long substitute6) {
        this.substitute6 = substitute6;
    }

    public long getSubstitute7() {
        return substitute7;
    }

    public void setSubstitute7(long substitute7) {
        this.substitute7 = substitute7;
    }

    public long getCaptain() {
        return captain;
    }

    public void setCaptain(long captain) {
        this.captain = captain;
    }

    public long getFreeKickTaker() {
        return freeKickTaker;
    }

    public void setFreeKickTaker(long freeKickTaker) {
        this.freeKickTaker = freeKickTaker;
    }

    public long getPenaltyTaker() {
        return penaltyTaker;
    }

    public void setPenaltyTaker(long penaltyTaker) {
        this.penaltyTaker = penaltyTaker;
    }

    public int getFormation() {
        return formation;
    }

    public void setFormation(int formation) {
        this.formation = formation;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public int getAttitude() {
        return attitude;
    }

    public void setAttitude(int attitude) {
        this.attitude = attitude;
    }
}
