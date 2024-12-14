package org.firstinspires.ftc.teamcode.TeleOp.Meets;

import androidx.annotation.NonNull;

// RR imports
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;

import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;


// Non-RR Imports
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.rr.MecanumDrive;


@Config
@Autonomous(name = "LM2 Auto", group = "Autonomous")
public class IntoTheDeepLM2Auto extends LinearOpMode {


    public static class Slides {
        private final DcMotorEx slidesL;
        private final DcMotorEx slidesR;

        public Slides(HardwareMap hwMap) {
            slidesL = hwMap.get(DcMotorEx.class, "slidesL");
            slidesR = hwMap.get(DcMotorEx.class, "slidesR");
            slidesL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            slidesR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            slidesL.setDirection(DcMotorSimple.Direction.FORWARD);
            slidesR.setDirection(DcMotorSimple.Direction.REVERSE);
        }


        public class SlidesUp implements Action {
            private boolean slidesInit = false;

            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                if (!slidesInit) {
                    slidesL.setPower(1);
                    slidesR.setPower(1);
                    slidesInit = true;
                }

                double posL = slidesL.getCurrentPosition();
                packet.put("slideLPos", posL);

                double posR = slidesR.getCurrentPosition();
                packet.put("slideRPos", posR);

                if (posL < 2250 & posR < 2250) {
                    return true;
                } else {
                    slidesL.setPower(0.01);
                    slidesR.setPower(0.01);
                    return false;
                }
            }
        }

        public Action slidesUp() {
            return new SlidesUp();
        }

        public class SlidesDown implements Action {
            private boolean init = false;

            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                if (!init) {
                    slidesL.setPower(-1);
                    slidesR.setPower(-1);
                    init = true;
                }

                double posL = slidesL.getCurrentPosition();
                packet.put("slideLPos", posL);

                double posR = slidesR.getCurrentPosition();
                packet.put("slideRPos", posR);

                if (posL > 50 & posR > 50) {
                    return true;
                } else {
                    slidesL.setPower(0);
                    slidesR.setPower(0);
                    return false;
                }
            }
        }

        public Action slidesDown() {
            return new SlidesDown();
        }
    }

    public static class ExtFront {
        private final Servo backPivot;
        private final Servo frontPivot;
        private final Servo wristClaw;
        private final Servo frontClaw;
        private final Servo leftTransfer;
        private final Servo rightTransfer;
        public ExtFront(HardwareMap hwMap) {
            leftTransfer = hwMap.get(Servo.class, "left");
            rightTransfer = hwMap.get(Servo.class, "right");
            frontClaw = hwMap.get(Servo.class, "frontclaw");
            wristClaw = hwMap.get(Servo.class, "wrist");
            frontPivot = hwMap.get(Servo.class, "frontpivot");
            backPivot = hwMap.get(Servo.class, "backpivot");


            //front claw system
            leftTransfer.setDirection(Servo.Direction.REVERSE);
            frontClaw.setDirection(Servo.Direction.REVERSE);
            frontPivot.setDirection(Servo.Direction.REVERSE);
            backPivot.setDirection(Servo.Direction.REVERSE);
        }



        public class TransferIn implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                leftTransfer.setPosition(0.4);
                rightTransfer.setPosition(0.45);
                return false;
            }
        }

        public Action transferIn() {
            return new TransferIn();
        }
        public class TransferExtend implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                leftTransfer.setPosition(1);
                rightTransfer.setPosition(0.75);
                return false;
            }
        }

        public Action transferExtend() {
            return new TransferExtend();
        }

        public class ClawOpen implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                frontClaw.setPosition(0.5);
                return false;
            }
        }

        public Action clawOpen() {
            return new ClawOpen();
        }

        public class ClawClose implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                frontClaw.setPosition(0.0);
                return false;
            }
        }

        public Action clawClose() {
            return new ClawClose();
        }

        public class WristInit implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                wristClaw.setPosition(0.43);
                return false;
            }
        }

        public Action wristInit() {
            return new WristInit();
        }

        public class WristRotate implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                wristClaw.setPosition(0.8);
                return false;
            }
        }

        public Action wristRotate() {
            return new WristRotate();
        }

        public class FrontPivotBase implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                frontPivot.setPosition(0.7);
                return false;
            }
        }

        public Action frontPivotBase() {
            return new FrontPivotBase();
        }

        public class FrontPivotGrab implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                frontPivot.setPosition(1.0);
                return false;
            }
        }

        public Action frontPivotGrab() {
            return new FrontPivotGrab();
        }

        public class FrontPivotTransfer implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                frontPivot.setPosition(0.4);
                return false;
            }
        }

        public Action frontPivotTransfer() {
            return new FrontPivotTransfer();
        }

        public class BackPivotBase implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                backPivot.setPosition(0.34);
                return false;
            }
        }



        public Action backPivotBase() {
            return new BackPivotBase();
        }

        public class BackPivotTransfer implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                backPivot.setPosition(0.0);
                return false;
            }
        }

        public Action backPivotTransfer() {
            return new BackPivotTransfer();
        }

    }

    public static class ExtBack {

        private final Servo slidePivot;
        private final Servo slideClaw;
        public ExtBack(HardwareMap hwMap) {
            slidePivot = hwMap.get(Servo.class, "slide pivot");
            slideClaw = hwMap.get(Servo.class, "slide claw");

            slidePivot.setDirection(Servo.Direction.REVERSE);
        }

        public class SlidePivotBase implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                slidePivot.setPosition(0.0);
                return false;
            }
        }

        public Action slidePivotBase() {
            return new SlidePivotBase();
        }

        public class SlidePivotDrop implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                slidePivot.setPosition(0.8);
                return false;
            }
        }

        public Action slidePivotDrop() {
            return new SlidePivotDrop();
        }

        public class SlideClawOpen implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                slideClaw.setPosition(1.0);
                return false;
            }
        }

        public Action slideClawOpen() {
            return new SlideClawOpen();
        }

        public class SlideClawClose implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                slideClaw.setPosition(0.2);
                    return false;
            }
        }

        public Action slideClawClose() {
            return new SlideClawClose();
        }

    }

    @Override
    public void runOpMode() {


        Pose2d initPose = new Pose2d(-4, -62, 0);
        MecanumDrive drive = new MecanumDrive(hardwareMap, initPose);
        Slides slides = new Slides(hardwareMap);
        ExtFront extFront = new ExtFront(hardwareMap);
        ExtBack extBack = new ExtBack(hardwareMap);

        // FIXME: oh jeebz...
        // Note: strafeToLinearHeading(new Vector2d(-52, -54), Math.PI/4)
        // is the BEST way to get back to score samples.
        // Sample X positions are at -48, -52, and -58
        TrajectoryActionBuilder preloadPlaceSample = drive.actionBuilder(initPose)
                .strafeTo(new Vector2d(-4, -56))
                .strafeToLinearHeading(new Vector2d(-52, -54), Math.PI/4);

        TrajectoryActionBuilder sampleRight = preloadPlaceSample.endTrajectory().fresh()
                .strafeToLinearHeading(new Vector2d(-44, -50), Math.PI/2);

        TrajectoryActionBuilder ascent1 = preloadPlaceSample.endTrajectory().fresh()
                .strafeToLinearHeading(new Vector2d(-16, 0), 0);



        Action finishProg = ascent1.endTrajectory().fresh().build();


        Action preloadPlace = preloadPlaceSample.build();
        Action sampleRightGrab = sampleRight.build();

        Action ascentStrafe = ascent1.build();

        Actions.runBlocking(
                new ParallelAction(
                        extFront.clawClose(),
                        extFront.transferIn(),
                        extFront.backPivotBase(),
                        extFront.frontPivotTransfer(),
                        extFront.wristInit(),
                        extBack.slidePivotBase(),
                        extBack.slideClawClose()
                )
        );

        waitForStart();

        if (isStopRequested()) return;

        Actions.runBlocking(
                new SequentialAction(
                        new ParallelAction(
                                preloadPlace,
                                slides.slidesUp(),
                                extBack.slidePivotDrop()
                        ),
                        extBack.slideClawOpen(),
                        new ParallelAction(
                                sampleRightGrab,
                                extBack.slidePivotBase(),
                                slides.slidesDown()
                        ),
                        extFront.transferExtend(),
                        extFront.frontPivotGrab(),
                        extFront.clawClose(),
                        ascentStrafe,
                        finishProg
                )
        );
    }
}

/*
  Reminder of Autonomous Values

    PARKING
  PARK or ASCENT LEVEL 1: 3 pts.

    SAMPLES
  NET ZONE (FLOOR): 2 pts.
  LOW BASKET:       4 pts.
  HIGH BASKET:      8 pts.

    SPECIMENS
  LOW CHAMBER:  6 pts.
  HIGH CHAMBER: 10 pts.

  SCENARIOS
    SCENARIO #1: PRELOAD SAMPLE
    - 32 pts. from SAMPLE (3 +1)
    - 3 pts. from PARK
    - TOTAL: 35

    SCENARIO #2: PRELOAD SPECIMEN
    - 10 pts. from HIGH CHAMBER
    - 24 pts. from SAMPLES (3)
    - 3 pts. from PARK
    - TOTAL: 37

    HYPOTHETICAL 61 POINT AUTO IS POSSIBLE, RISKY
    - 10 pts. from HIGH CHAMBER
    - 48 pts. from SAMPLES (6)
    - 3 pts. from PARK
    - TOTAL: 61


 */
