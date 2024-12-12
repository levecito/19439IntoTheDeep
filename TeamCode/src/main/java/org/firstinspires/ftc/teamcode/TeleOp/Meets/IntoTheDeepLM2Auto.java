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


@Config
@Autonomous(name = "LM2 Auto", group = "Autonomous")
public class IntoTheDeepLM2Auto extends LinearOpMode {

    public class Slides {
        private DcMotorEx slidesL;
        private DcMotorEx slidesR;

        public Slides(HardwareMap hwMap) {
            slidesL = hwMap.get(DcMotorEx.class, "slidesL");
            slidesR = hwMap.get(DcMotorEx.class, "slidesR");
            slidesL.setDirection(DcMotorSimple.Direction.FORWARD);
            slidesR.setDirection(DcMotorSimple.Direction.REVERSE);
            slidesL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            slidesR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }

        public class SlidesUp implements Action {
            private boolean init = false;

            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                if (!init) {
                    slidesL.setPower(1);
                    slidesR.setPower(1);
                    init = true;
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
                    slidesL.setPower(1);
                    slidesR.setPower(1);
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

    @Override
    public void runOpMode() {
        Pose2d initPose = new Pose2d(6, -62, 0);
        MecanumDrive drive = new MecanumDrive(hardwareMap, initPose);
        Slides slides = new Slides(hardwareMap);

        TrajectoryActionBuilder preloadPlace = drive.actionBuilder(initPose)
                .lineToX(-48)
                .turn(Math.PI/2)
                .lineToY(-20);

        Action finishProg = preloadPlace.endTrajectory().fresh()
                .build();

        Action preloading = preloadPlace.build();

        Actions.runBlocking(
                new SequentialAction(preloading, slides.slidesUp(), slides.slidesDown(), finishProg)
        );
    }
}
