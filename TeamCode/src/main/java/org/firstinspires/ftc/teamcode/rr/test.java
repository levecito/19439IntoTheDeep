package org.firstinspires.ftc.teamcode.rr;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="test #1")
public class test extends LinearOpMode {

    @Override
    public void runOpMode() {
        Pose2d initPose = new Pose2d(6, -62, 0);
        MecanumDrive drive = new MecanumDrive(hardwareMap, initPose);


        waitForStart();

        while (opModeIsActive()) {

            Action test1 = drive.actionBuilder(initPose)
                    .lineToX(-48)
                    .turn(Math.PI/2)
                    .lineToY(-20)
                    .endTrajectory()
                    .build();

            Actions.runBlocking(
                    new SequentialAction(
                          test1
                    )
            );
        }
    }
}
