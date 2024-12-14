package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class LM2Concept2 {
    public static void main(String[] args) {
        com.noahbres.meepmeep.MeepMeep meepMeep = new com.noahbres.meepmeep.MeepMeep(600);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Do not change!
                .setConstraints(50, 50, Math.PI, Math.PI, 10.5)
                .build();

        // strafeToLinearHeading(new Vector2d(-52, -54), Math.PI/4)
        // is the BEST way to get back to score samples.
        // Sample X positions are at -48, -57, and -62
        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(-4, -62, 0))
                .strafeTo(new Vector2d(-4, -56))
                .strafeToLinearHeading(new Vector2d(-52, -54), Math.PI/4)
                .strafeToLinearHeading(new Vector2d(-48, -54), Math.PI/2)
                // extendo grab #1
                .strafeToLinearHeading(new Vector2d(-57, -48), Math.PI/4)
                .turn(Math.PI/4)
                // extendo grab #2
                .strafeToLinearHeading(new Vector2d(-52, -54), Math.PI/4)
                .build());

        meepMeep.setBackground(com.noahbres.meepmeep.MeepMeep.Background.FIELD_INTO_THE_DEEP_OFFICIAL)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}