// THIS IS OUR MAIN CODE!!!

package org.firstinspires.ftc.teamcode.opmodes;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.List;

@TeleOp(group = "21836 TeleOp")
public class TeleOpFIX extends LinearOpMode {

    MultipleTelemetry myTelemetry;
    List<LynxModule> hubs;

    Servo yourServo;
    boolean servoPositionIsZero = true;

    @Override
    public void runOpMode() throws InterruptedException {

        myTelemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        DcMotor leftMotor = hardwareMap.get(DcMotor.class, "left");
        DcMotor rightMotor = hardwareMap.get(DcMotor.class, "rightd");
        GamepadEx Gamepad1 = new GamepadEx(gamepad1);
        hubs = hardwareMap.getAll(LynxModule.class);
        // Initialize your hardware components here
        yourServo = hardwareMap.servo.get("your_servo");

        // Set the initial position of the servo
        if (servoPositionIsZero) {
            yourServo.setPosition(0.0);  // 0 degrees
        } else {
            yourServo.setPosition(0.25);  // 90 degrees
        }
        for (LynxModule hub : hubs) hub.setBulkCachingMode(LynxModule.BulkCachingMode.MANUAL);

        waitForStart();

        while (opModeIsActive()) {
            for (LynxModule hub : hubs) hub.clearBulkCache();
            double leftPower = Gamepad1.getLeftY();
            double rightPower = Gamepad1.getRightY();
            leftMotor.setPower(leftPower);
            rightMotor.setPower(rightPower);

            // Check if the "A" button on gamepad2 is pressed
            if (gamepad2.a) {
                // Toggle the servo position
                if (servoPositionIsZero) {
                    yourServo.setPosition(0.25);  // 90 degrees
                } else {
                    yourServo.setPosition(0.0);  // 0 degrees
                }
                servoPositionIsZero = !servoPositionIsZero; // Toggle the state
                sleep(500); // Add a delay to prevent rapid toggling
            }
            myTelemetry.update();
        }
    }
}
