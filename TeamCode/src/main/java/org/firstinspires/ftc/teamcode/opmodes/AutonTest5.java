package org.firstinspires.ftc.teamcode.opmodes;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.subsystems.TankDriveTrain;

import java.util.List;


@Autonomous(group = "21836 Autonomous", preselectTeleOp = "AutonTest5")
public class AutonTest5 extends LinearOpMode {

    MultipleTelemetry myTelemetry;
    List<LynxModule> hubs;

    Servo yourServo;
    boolean servoPositionIsZero = true;
    TankDriveTrain drive;

    @Override
    public void runOpMode() throws InterruptedException {

        myTelemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        GamepadEx Gamepad1 = new GamepadEx(gamepad1);
        hubs = hardwareMap.getAll(LynxModule.class);
        drive = new TankDriveTrain(hardwareMap);
        for (LynxModule hub : hubs) hub.setBulkCachingMode(LynxModule.BulkCachingMode.MANUAL);

        waitForStart();

        blueBackboard();

        //long durationTurnLeft = 1000; // 1 second (adjust as needed)
        //long durationForwardAgain = 3000; // 3 seconds

//        while (opModeIsActive() && System.currentTimeMillis() - startTime < durationTurnLeft) {
//            for (LynxModule hub : hubs) hub.clearBulkCache();
//            drive.setPower(0.0, -1.0); // Turn left in place (adjust power as needed)
//            myTelemetry.update();
//        }
//
//        drive.setPower(0.0, 0.0); // Stop the robot
//        sleep(500); // Add a delay to prevent rapid toggling
//
//        startTime = System.currentTimeMillis(); // Reset the start time
//
//        while (opModeIsActive() && System.currentTimeMillis() - startTime < durationForwardAgain) {
//            for (LynxModule hub : hubs) hub.clearBulkCache();
//            drive.setPower(1.0, 0.0); // Go forward at full power
//            myTelemetry.update();
//        }
//
//        drive.setPower(0.0, 0.0); // Stop the robot
//        sleep(500); // Add a delay to prevent rapid toggling
    }
    public void blueBackboard() {
        long startTime = System.currentTimeMillis();
        long durationForward = 800; // 2 seconds
        long durationTurn = 1300; // 1 second (adjust as needed)
        long durationForwardAfterTurnBackboard = 700;

        while (opModeIsActive() && System.currentTimeMillis() - startTime < durationForward) {
            for (LynxModule hub : hubs) hub.clearBulkCache();
            drive.setPower(1.0, 0.0); // Go forward at full power
            myTelemetry.update();
        }

        drive.setPower(0.0, 0.0); // Stop the robot
        sleep(500); // Add a delay to prevent rapid toggling

        startTime = System.currentTimeMillis(); // Reset the start time

        while (opModeIsActive() && System.currentTimeMillis() - startTime < durationTurn) {
            for (LynxModule hub : hubs) hub.clearBulkCache();
            drive.setPower(0.0, 1.0); // Turn right in place (adjust power as needed)
            myTelemetry.update();
        }

        drive.setPower(0.0, 0.0); // Stop the robot
        sleep(500); // Add a delay to prevent rapid toggling

        startTime = System.currentTimeMillis(); // Reset the start time

        while (opModeIsActive() && System.currentTimeMillis() - startTime < durationForwardAfterTurnBackboard) {
            for (LynxModule hub : hubs) hub.clearBulkCache();
            drive.setPower(1.0, 0.0); // Turn right in place (adjust power as needed)
            myTelemetry.update();
        }

        drive.setPower(0.0, 0.0); // Stop the robot
        sleep(500); // Add a delay to prevent rapid toggling
    }
    public void blueZone() {
        long startTime = System.currentTimeMillis();
        long durationForward = 800; // 2 seconds
        long durationTurn = 1300; // 1 second (adjust as needed)
        long durationForwardAfterTurnBackboard = 1300;

        while (opModeIsActive() && System.currentTimeMillis() - startTime < durationForward) {
            for (LynxModule hub : hubs) hub.clearBulkCache();
            drive.setPower(1.0, 0.0); // Go forward at full power
            myTelemetry.update();
        }

        drive.setPower(0.0, 0.0); // Stop the robot
        sleep(500); // Add a delay to prevent rapid toggling

        startTime = System.currentTimeMillis(); // Reset the start time

        while (opModeIsActive() && System.currentTimeMillis() - startTime < durationTurn) {
            for (LynxModule hub : hubs) hub.clearBulkCache();
            drive.setPower(0.0, 1.0); // Turn right in place (adjust power as needed)
            myTelemetry.update();
        }

        drive.setPower(0.0, 0.0); // Stop the robot
        sleep(500); // Add a delay to prevent rapid toggling

        startTime = System.currentTimeMillis(); // Reset the start time

        while (opModeIsActive() && System.currentTimeMillis() - startTime < durationForwardAfterTurnBackboard) {
            for (LynxModule hub : hubs) hub.clearBulkCache();
            drive.setPower(1.0, 0.0); // Turn right in place (adjust power as needed)
            myTelemetry.update();
        }

        drive.setPower(0.0, 0.0); // Stop the robot
        sleep(500); // Add a delay to prevent rapid toggling
    }

    public void redBackboard() {
        long startTime = System.currentTimeMillis();
        long durationForward = 800; // 2 seconds
        long durationTurn = 1300; // 1 second (adjust as needed)
        long durationForwardAfterTurnBackboard = 700;

        while (opModeIsActive() && System.currentTimeMillis() - startTime < durationForward) {
            for (LynxModule hub : hubs) hub.clearBulkCache();
            drive.setPower(1.0, 0.0); // Go forward at full power
            myTelemetry.update();
        }

        drive.setPower(0.0, 0.0); // Stop the robot
        sleep(500); // Add a delay to prevent rapid toggling

        startTime = System.currentTimeMillis(); // Reset the start time

        while (opModeIsActive() && System.currentTimeMillis() - startTime < durationTurn) {
            for (LynxModule hub : hubs) hub.clearBulkCache();
            drive.setPower(0.0, -1.0); // Turn right in place (adjust power as needed)
            myTelemetry.update();
        }

        drive.setPower(0.0, 0.0); // Stop the robot
        sleep(500); // Add a delay to prevent rapid toggling

        startTime = System.currentTimeMillis(); // Reset the start time

        while (opModeIsActive() && System.currentTimeMillis() - startTime < durationForwardAfterTurnBackboard) {
            for (LynxModule hub : hubs) hub.clearBulkCache();
            drive.setPower(1.0, 0.0); // Turn right in place (adjust power as needed)
            myTelemetry.update();
        }

        drive.setPower(0.0, 0.0); // Stop the robot
        sleep(500); // Add a delay to prevent rapid toggling
    }
    public void redZone() {
        long startTime = System.currentTimeMillis();
        long durationForward = 800; // 2 seconds
        long durationTurn = 1300; // 1 second (adjust as needed)
        long durationForwardAfterTurnBackboard = 1300;

        while (opModeIsActive() && System.currentTimeMillis() - startTime < durationForward) {
            for (LynxModule hub : hubs) hub.clearBulkCache();
            drive.setPower(1.0, 0.0); // Go forward at full power
            myTelemetry.update();
        }

        drive.setPower(0.0, 0.0); // Stop the robot
        sleep(500); // Add a delay to prevent rapid toggling

        startTime = System.currentTimeMillis(); // Reset the start time

        while (opModeIsActive() && System.currentTimeMillis() - startTime < durationTurn) {
            for (LynxModule hub : hubs) hub.clearBulkCache();
            drive.setPower(0.0, -1.0); // Turn left in place (adjust power as needed)
            myTelemetry.update();
        }

        drive.setPower(0.0, 0.0); // Stop the robot
        sleep(500); // Add a delay to prevent rapid toggling

        startTime = System.currentTimeMillis(); // Reset the start time

        while (opModeIsActive() && System.currentTimeMillis() - startTime < durationForwardAfterTurnBackboard) {
            for (LynxModule hub : hubs) hub.clearBulkCache();
            drive.setPower(1.0, 0.0); // Turn right in place (adjust power as needed)
            myTelemetry.update();
        }

        drive.setPower(0.0, 0.0); // Stop the robot
        sleep(500); // Add a delay to prevent rapid toggling
    }
}
