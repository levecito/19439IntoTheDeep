package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class FrontExt {
    private Servo backPivot;
    private Servo frontPivot;
    private Servo wristClaw;
    private Servo frontClaw;
    private Servo leftTransfer;
    private Servo rightTransfer;

    public void init(HardwareMap hwMap) {
        wristClaw = hwMap.get(Servo.class, "wrist");
        backPivot = hwMap.get(Servo.class, "backpivot");
        frontPivot = hwMap.get(Servo.class, "frontpivot");
        frontClaw = hwMap.get(Servo.class, "frontclaw");
        leftTransfer = hwMap.get(Servo.class, "left");
        rightTransfer = hwMap.get(Servo.class, "right");

        //front claw system
        backPivot.setDirection(Servo.Direction.REVERSE);
        frontPivot.setDirection(Servo.Direction.REVERSE);
        frontClaw.setDirection(Servo.Direction.REVERSE);
        leftTransfer.setDirection(Servo.Direction.REVERSE);
    }

    public void setTransfer(double L, double R) {
        leftTransfer.setPosition(L);
        rightTransfer.setPosition(R);
    }

    public void setBackPivot(double pos) {
        backPivot.setPosition(pos);
    }

    public void setFrontPivot(double pos) {
        frontPivot.setPosition(pos);
    }

    public void setFrontClaw(double pos) {
        frontClaw.setPosition(pos);
    }

    public void setWristClaw(double pos) {
        wristClaw.setPosition(pos);
    }


}


/*
    public void transferServo(double pos) {
        transferServo.setPosition(pos);
    }
 */
