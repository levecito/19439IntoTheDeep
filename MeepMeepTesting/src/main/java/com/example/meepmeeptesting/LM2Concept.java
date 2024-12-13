package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class LM2Concept {
    public static void main(String[] args) {
        com.noahbres.meepmeep.MeepMeep meepMeep = new com.noahbres.meepmeep.MeepMeep(600);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                /*
                  Do not change!
                 */
                .setConstraints(50, 50, Math.PI, Math.PI, 10.5)
                .build();

        // FINALLY!!!
        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(-4, -62, 0))
                .strafeTo(new Vector2d(-4, -56))
                .strafeToLinearHeading(new Vector2d(-52, -54), Math.PI/4)
                // slides up! Deposit sample high! Down!
                        .strafeToLinearHeading(new Vector2d(-48,-32), Math.PI/2)
                // Grab! (Sample #1)
                        .strafeToLinearHeading(new Vector2d(-52, -54), Math.PI/4)
                // Slides Up! Deposit! Down!
                .strafeToLinearHeading(new Vector2d(-57, -50), Math.PI/4)
                        .turn(Math.toRadians(45))
                                .lineToY(-32)
                // Grab! (Sample #2)
                .strafeToLinearHeading(new Vector2d(-52, -54), Math.PI/4)
                // Slides Up! Deposit! Down!
                .strafeToLinearHeading(new Vector2d(-48, -25), Math.PI/4)
                        .turn(Math.toRadians(135))
                        .lineToX(-58)
                // Grab! (Sample #3)
                .strafeToLinearHeading(new Vector2d(-52, -54), Math.PI/4)
                // Slides Up! Deposit! Down!
                .strafeToLinearHeading(new Vector2d(-16, 0), 0)
                //slides up (ASCENT LEVEL 1), or...
                        .strafeToLinearHeading(new Vector2d(58, -60), 0)
                // PARKED in OBSERVATION ZONE
                .build());

        meepMeep.setBackground(com.noahbres.meepmeep.MeepMeep.Background.FIELD_INTO_THE_DEEP_OFFICIAL)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}