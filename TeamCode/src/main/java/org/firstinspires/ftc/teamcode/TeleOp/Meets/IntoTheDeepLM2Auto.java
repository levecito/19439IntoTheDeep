package org.firstinspires.ftc.teamcode.TeleOp.Meets;

import androidx.annotation.NonNull;

// RR imports
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
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

import org.firstinspires.ftc.teamcode.rr.MecanumDrive;


// Does anyone know how a linear OpMode works????? help
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
                packet.put("slideRPos", posL);

                if (posL < 2250 & posR < 2250) {
                    return true;
                } else {
                    slidesL.setPower(0);
                    slidesR.setPower(0);
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
                packet.put("slideRPos", posL);

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

    // TODO: Complete Servo class
    public static class Servos {

    }

    @Override
    public void runOpMode() {

        // TODO: Fix starting position, make sure LM2Concept is adjusted with this
        Pose2d initPose = new Pose2d(-4, -62, 0);
        MecanumDrive drive = new MecanumDrive(hardwareMap, initPose);
        Slides slides = new Slides(hardwareMap);

        TrajectoryActionBuilder preloadPlace = drive.actionBuilder(initPose)
                .strafeTo(new Vector2d(-4, -58))
                .strafeToLinearHeading(new Vector2d(-48, -58), Math.PI/4);

        Action finishProg = preloadPlace.endTrajectory().fresh().build();

        Action preloading = preloadPlace.build();

        waitForStart();

        Actions.runBlocking(
                new SequentialAction(
                        preloading,
                        slides.slidesUp(),
                        slides.slidesDown(),
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
