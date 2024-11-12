package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class BackLift {
    private DcMotor slidesR;
    private DcMotor slidesL;
    private Servo slidePivot;
    private Servo slideClaw;
    private Servo specimenClaw;

    public void init(HardwareMap hwMap) {
        slidesR = hwMap.get(DcMotor.class, "slidesR");
        slidesL = hwMap.get(DcMotor.class, "slidesL");
        slidePivot = hwMap.get(Servo.class, "slide pivot");
        slideClaw = hwMap.get(Servo.class, "slide claw");
        specimenClaw = hwMap.get(Servo.class, "specimen claw");

        //Slides
        slidesR.setDirection(DcMotor.Direction.REVERSE);
        slidesL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slidesR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slidePivot.setDirection(Servo.Direction.REVERSE);
    }

    public void setSlides(int slidePos) {
        slidesL.setTargetPosition(slidePos);
        slidesR.setTargetPosition(slidePos);
        slidesL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        slidesR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        slidesL.setPower(1);
        slidesR.setPower(1);
    }

    public void setSlidePivot(double pos) {
        slidePivot.setPosition(pos);
    }

    public void setSlideClaw(double pos) {
        slideClaw.setPosition(pos);
    }

    public void setSpecimenClaw(double pos) {
        specimenClaw.setPosition(pos);
    }
}
