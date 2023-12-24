package org.firstinspires.ftc.teamcode.opmodes;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.drivetrains.MecanumDrivetrain;

/*
 * This is a simple routine to test translational drive capabilities.
 */
@Config
@Autonomous(group = "testRoadRunner")
public class TestBlueRoadRunner extends LinearOpMode {
    public static double DISTANCE = 60; // in

    @Override
    public void runOpMode() throws InterruptedException {
        Telemetry telemetry = new MultipleTelemetry(this.telemetry, FtcDashboard.getInstance().getTelemetry());

        MecanumDrivetrain drive = new MecanumDrivetrain(hardwareMap);

        Trajectory traj1 = drive.trajectoryBuilder(new Pose2d())
                .strafeRight(3.75)
                .build();

        Trajectory traj2 = drive.trajectoryBuilder(new Pose2d())
                .forward(7.916)
                .build();

        waitForStart();

        if (isStopRequested()) return;

        drive.followTrajectory(traj1);
        drive.followTrajectory(traj2);

        Pose2d poseEstimate = drive.getPoseEstimate();
        telemetry.addData("finalX", poseEstimate.getX());
        telemetry.addData("finalY", poseEstimate.getY());
        telemetry.addData("finalHeading", poseEstimate.getHeading());
        telemetry.update();

        while (!isStopRequested() && opModeIsActive()) ;
    }
}
